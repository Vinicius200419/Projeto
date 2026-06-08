# README - Projeto Android de Receitas

## Transformação de Projeto Web para Android

Este projeto foi transformado de uma aplicação web (HTML/CSS/JavaScript/PHP) para um aplicativo Android nativo em Kotlin.

## Estrutura do Projeto

```
Projeto_Android/
├── app/
│   ├── build.gradle                    # Configuração de build do app
│   ├── proguard-rules.pro             # Regras de ofuscação
│   ├── src/main/
│   │   ├── AndroidManifest.xml        # Manifesto Android
│   │   ├── java/com/receitas/
│   │   │   ├── MainActivity.kt        # Tela principal com lista de receitas
│   │   │   ├── DetailActivity.kt      # Detalhes da receita
│   │   │   ├── AddEditActivity.kt     # Adicionar/Editar receita
│   │   │   ├── adapter/
│   │   │   │   └── RecipeAdapter.kt   # Adapter para RecyclerView
│   │   │   ├── database/
│   │   │   │   ├── RecipeDao.kt       # Data Access Object
│   │   │   │   └── RecipeDatabase.kt  # Database do Room
│   │   │   ├── models/
│   │   │   │   └── Recipe.kt          # Modelo de dados
│   │   │   └── viewmodel/
│   │   │       └── RecipeViewModel.kt # ViewModel
│   │   └── res/
│   │       ├── layout/
│   │       │   ├── activity_main.xml      # Layout da tela principal
│   │       │   ├── activity_detail.xml    # Layout de detalhes
│   │       │   ├── activity_add_edit.xml  # Layout do formulário
│   │       │   └── item_recipe.xml        # Layout do item da lista
│   │       ├── values/
│   │       │   ├── strings.xml            # Strings do app
│   │       │   ├── colors.xml             # Cores
│   │       │   └── styles.xml             # Estilos
│   │       └── mipmap/                    # Ícones do app
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Funcionalidades

O aplicativo mantém todas as funcionalidades do projeto web:

- ✅ **Listar Receitas** - Exibe todas as receitas em uma lista
- ✅ **Adicionar Receita** - Formulário para criar nova receita
- ✅ **Editar Receita** - Modifica receita existente
- ✅ **Excluir Receita** - Remove receita com confirmação
- ✅ **Ver Detalhes** - Visualiza todos os detalhes da receita

## Tecnologias Utilizadas

### Frontend (Android):
- **Kotlin** - Linguagem de programação
- **AndroidX** - Bibliotecas de suporte
- **Material Components** - UI Material Design
- **RecyclerView** - Lista de receitas
- **CardView** - Cards para cada receita

### Backend (Local):
- **Room Database** - SQLite com abstração
- **LiveData** - Observação de dados
- **Coroutines** - Programação assíncrona
- **ViewModel** - Gerenciamento de estado
- **Picasso** - Carregamento de imagens

## Requisitos

- Android SDK 24+
- Android Studio Flamingo ou superior
- Kotlin 1.9.0+
- Gradle 8.0.0+

## Como Compilar e Executar

### Opção 1: Android Studio

1. Abra Android Studio
2. Selecione "Open Project" e escolha a pasta `Projeto_Android`
3. Aguarde o Gradle sincronizar
4. Clique em "Run" ou pressione Shift+F10

### Opção 2: Terminal

```bash
cd Projeto_Android
./gradlew build          # Compilar
./gradlew installDebug  # Instalar em emulador/dispositivo
```

## Estrutura de Dados

### Modelo Recipe (Receita)
```kotlin
@Entity(tableName = "receitas")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nome: String,           // Nome da receita
    val tempo: Int,             // Tempo em minutos
    val serve: Int,             // Número de porções
    val dificuldade: String,    // Fácil, Médio, Difícil
    val imagem: String = "",    // URL da imagem
    val ingredientes: String,   // Lista de ingredientes
    val modoPreparo: String,    // Modo de preparo
    val dataCriacao: Long = System.currentTimeMillis()
)
```

## Fluxo da Aplicação

1. **MainActivity** - Exibe lista de receitas com RecyclerView
2. **AddEditActivity** - Tela para adicionar ou editar receita
3. **DetailActivity** - Exibe detalhes completos da receita

## Dados Persistentes

Os dados são armazenados localmente usando Room Database (SQLite). O banco de dados é criado automaticamente na primeira execução.

## Próximos Passos (Opcional)

- Adicionar sincronização com servidor PHP
- Integração com Firebase Cloud Firestore
- Compartilhar receitas entre usuários
- Adicionar fotos da câmera
- Sistema de favoritos
- Categorias de receitas
- Busca e filtros

## Aluno

Nome: Vinícius Alves Batista
RA: 2024.0360.0188

## Licença

Este projeto é fornecido como está para fins educacionais.
