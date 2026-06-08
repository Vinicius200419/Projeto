# Guia de Migração: Backend PHP para Android

## Visão Geral

O projeto original utiliza PHP e SQLite no backend. O novo projeto Android implementa:
- **Database Local**: Room Database (SQLite nativo do Android)
- **API**: Sem necessidade de backend externo (dados salvos localmente)

## Banco de Dados

### Comparação

| Aspecto | PHP Original | Android |
|---------|-------------|---------|
| Database | SQLite (arquivo) | SQLite (nativo do Android) |
| Acesso | PDO/mysqli | Room DAO |
| Sincronização | HTTP | Em memória (LiveData) |
| Migração | Manual | Automática |

### Schema do Room (Kotlin)

```kotlin
@Entity(tableName = "receitas")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val tempo: Int,
    val serve: Int,
    val dificuldade: String,
    val imagem: String = "",
    val ingredientes: String,
    val modoPreparo: String,
    val dataCriacao: Long = System.currentTimeMillis()
)
```

## Endpoints PHP → Métodos Android

### Listar Receitas
**PHP**: `GET /get_receitas.php`
**Android**: 
```kotlin
recipeDao.getAllRecipes() // Retorna Flow<List<Recipe>>
```

### Adicionar Receita
**PHP**: `POST /adicionar.php`
**Android**:
```kotlin
recipeDao.insertRecipe(recipe) // Retorna Long (id)
```

### Editar Receita
**PHP**: `POST /editar_receita.php`
**Android**:
```kotlin
recipeDao.updateRecipe(recipe)
```

### Excluir Receita
**PHP**: `DELETE /delete_receita.php?id=X`
**Android**:
```kotlin
recipeDao.deleteRecipeById(id)
```

### Obter Detalhes
**PHP**: `GET /detalhes.php?id=X`
**Android**:
```kotlin
recipeDao.getRecipeById(id) // Retorna Recipe?
```

## Migração de Dados (PHP → Android)

### Exportar dados do SQLite PHP

```bash
# Verificar banco PHP
sqlite3 receitas.db "SELECT * FROM receitas;"

# Exportar SQL
sqlite3 receitas.db ".dump receitas" > dump.sql
```

### Importar para Android

Criar um arquivo migrations que execute os INSERTs:

```kotlin
// Em RecipeDatabase.kt - addMigrations()
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Executar INSERT commands
    }
}
```

## Integração com Backend PHP Existente (Opcional)

Se quiser manter sincronização com servidor PHP:

### 1. Adicionar Retrofit

```gradle
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

### 2. Criar Interface de API

```kotlin
interface RecipeApiService {
    @GET("get_receitas.php")
    suspend fun getRecipes(): List<Recipe>
    
    @POST("adicionar.php")
    suspend fun addRecipe(@Body recipe: Recipe): Recipe
    
    @PUT("editar_receita.php")
    suspend fun updateRecipe(@Body recipe: Recipe): Recipe
    
    @DELETE("delete_receita.php")
    suspend fun deleteRecipe(@Query("id") id: Int): Response<Void>
}
```

### 3. Usar em ViewModel

```kotlin
val apiService = Retrofit.Builder()
    .baseUrl("https://seu-servidor.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(RecipeApiService::class.java)
```

## Dados Estruturados

### Recipe (Receita)

```kotlin
@Entity(tableName = "receitas")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,              // ID único
    val nome: String,              // Nome da receita
    val tempo: Int,                // Tempo em minutos
    val serve: Int,                // Número de porções
    val dificuldade: String,       // Fácil/Médio/Difícil
    val imagem: String = "",       // URL ou caminho da imagem
    val ingredientes: String,      // Lista de ingredientes
    val modoPreparo: String,       // Instruções de preparo
    val dataCriacao: Long = System.currentTimeMillis()
)
```

## Exemplo: Converter dados PHP para Android

### PHP JSON Response
```json
{
    "id": 1,
    "nome": "Bolo de Chocolate",
    "tempo": 45,
    "serve": 8,
    "dificuldade": "Médio",
    "imagem": "https://...",
    "ingredientes": "2 xícaras de farinha...",
    "modoPreparo": "1. Preaqueça o forno..."
}
```

### Kotlin Object
```kotlin
val recipe = Recipe(
    id = 1,
    nome = "Bolo de Chocolate",
    tempo = 45,
    serve = 8,
    dificuldade = "Médio",
    imagem = "https://...",
    ingredientes = "2 xícaras de farinha...",
    modoPreparo = "1. Preaqueça o forno..."
)
```

## Considerações de Performance

1. **Sincronização**: Use `Flow` para atualizações automáticas
2. **Cache Local**: Room cach automaticamente
3. **Coroutines**: Operações de BD em thread separada
4. **LiveData**: Observa mudanças e atualiza UI

## Testando a Migração

1. Adicionar algumas receitas no app Android
2. Verificar dados no Database Inspector (Android Studio)
3. Exportar dados e comparar com formato PHP
4. Implementar sincronização se necessário

## Próximas Etapas

- [ ] Testar CRUD completo
- [ ] Implementar sincronização com servidor (se necessário)
- [ ] Adicionar autenticação do usuário
- [ ] Implementar backup/restore
- [ ] Adicionar categorias de receitas
- [ ] Sistema de favoritos

## Suporte

Documentação Android: https://developer.android.com/guide
Room Database: https://developer.android.com/training/data-storage/room
