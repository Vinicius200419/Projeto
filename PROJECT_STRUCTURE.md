# 📊 Estrutura Completa do Projeto Android

## 🎯 Visão Geral da Arquitetura

```
┌─────────────────────────────────────────────────────────────┐
│                    USER INTERFACE LAYER                     │
├─────────────────────────────────────────────────────────────┤
│  MainActivity → RecipeAdapter → RecyclerView                 │
│       ↓                                                      │
│  DetailActivity (Exibe detalhes)                             │
│       ↓                                                      │
│  AddEditActivity (Adiciona/Edita)                            │
└─────────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────┐
│                  PRESENTATION LAYER                         │
├─────────────────────────────────────────────────────────────┤
│  RecipeViewModel                                             │
│    • getAllRecipes() → Flow<List<Recipe>>                   │
│    • addRecipe(recipe)                                       │
│    • updateRecipe(recipe)                                    │
│    • deleteRecipe(recipe)                                    │
└─────────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────┐
│                    DATA LAYER                               │
├─────────────────────────────────────────────────────────────┤
│  RecipeDatabase (Singleton)                                  │
│    ↓                                                         │
│  RecipeDao                                                   │
│    • getAllRecipes(): Flow<List<Recipe>>                    │
│    • getRecipeById(id): Recipe                              │
│    • insertRecipe(recipe): Long                             │
│    • updateRecipe(recipe)                                    │
│    • deleteRecipe(recipe)                                    │
│    • deleteRecipeById(id)                                    │
└─────────────────────────────────────────────────────────────┘
                           ↓
┌─────────────────────────────────────────────────────────────┐
│                   DATABASE LAYER                            │
├─────────────────────────────────────────────────────────────┤
│  SQLite (Room Database)                                      │
│    • receitas table                                          │
│      - id (PRIMARY KEY)                                      │
│      - nome                                                  │
│      - tempo                                                 │
│      - serve                                                 │
│      - dificuldade                                           │
│      - imagem                                                │
│      - ingredientes                                          │
│      - modoPreparo                                           │
│      - dataCriacao                                           │
└─────────────────────────────────────────────────────────────┘
```

## 📁 Árvore de Arquivos

```
Projeto_Android/
│
├── 📄 build.gradle                    # Build script root
├── 📄 settings.gradle                 # Project settings
├── 📄 build.gradle.kts                # Kotlin DSL (alternativo)
├── 📄 gradle.properties               # Configuração Gradle
├── 📄 local.properties.example        # Exemplo de propriedades locais
│
├── 📄 README.md                       # Documentação principal
├── 📄 WELCOME.md                      # Bem-vindo - Quick start
├── 📄 SETUP.md                        # Guia de configuração
├── 📄 BACKEND_MIGRATION.md            # Migração de dados
├── 📄 PROJECT_STRUCTURE.md            # Este arquivo
│
├── 🔨 build.sh                        # Script build (Linux/Mac)
├── 🔨 build.bat                       # Script build (Windows)
│
├── 📂 .gitignore                      # Git ignore patterns
│
└── 📂 app/
    ├── 📄 build.gradle                # App build configuration
    ├── 📄 proguard-rules.pro          # ProGuard rules
    │
    └── 📂 src/
        └── 📂 main/
            ├── 📄 AndroidManifest.xml # App manifest
            │
            ├── 📂 java/com/receitas/
            │   ├── 📄 MainActivity.kt
            │   │   └── Lista de receitas com RecyclerView
            │   │
            │   ├── 📄 DetailActivity.kt
            │   │   └── Exibe detalhes completos da receita
            │   │
            │   ├── 📄 AddEditActivity.kt
            │   │   └── Formulário para adicionar/editar
            │   │
            │   ├── 📂 adapter/
            │   │   └── 📄 RecipeAdapter.kt
            │   │       └── Adapter para RecyclerView
            │   │
            │   ├── 📂 database/
            │   │   ├── 📄 RecipeDatabase.kt
            │   │   │   └── Singleton Database (Room)
            │   │   │
            │   │   └── 📄 RecipeDao.kt
            │   │       └── Data Access Object
            │   │
            │   ├── 📂 models/
            │   │   └── 📄 Recipe.kt
            │   │       └── Entity data class
            │   │
            │   └── 📂 viewmodel/
            │       └── 📄 RecipeViewModel.kt
            │           └── Business logic & state
            │
            └── 📂 res/
                ├── 📂 layout/
                │   ├── 📄 activity_main.xml        # Lista recipes
                │   ├── 📄 activity_detail.xml      # Detalhes
                │   ├── 📄 activity_add_edit.xml    # Formulário
                │   └── 📄 item_recipe.xml          # Item list
                │
                ├── 📂 values/
                │   ├── 📄 strings.xml              # Strings
                │   ├── 📄 colors.xml               # Color palette
                │   ├── 📄 styles.xml               # Estilos
                │   └── 📄 dimens.xml               # Dimensões
                │
                ├── 📂 drawable/                     # Vector drawables
                │   └── (ícones e imagens vetoriais)
                │
                ├── 📂 mipmap/                       # App icons
                │   └── ic_launcher.png
                │
                └── 📂 menu/                         # Menu files
                    └── (opcional - menus)
```

## 🔄 Fluxo de Dados

### 1. Listar Receitas
```
MainActivity
    ↓
RecipeViewModel.recipes (Flow<List<Recipe>>)
    ↓
RecipeDao.getAllRecipes()
    ↓
SQLite SELECT * FROM receitas
    ↓
RecipeAdapter.submitList()
    ↓
RecyclerView exibe items
```

### 2. Adicionar Receita
```
AddEditActivity (formulário)
    ↓
saveRecipe() (validação)
    ↓
RecipeViewModel.addRecipe(recipe)
    ↓
RecipeDao.insertRecipe(recipe)
    ↓
SQLite INSERT
    ↓
LiveData notifica
    ↓
MainActivity recarga lista
```

### 3. Editar Receita
```
MainActivity (clique em editar)
    ↓
AddEditActivity carrega dados
    ↓
RecipeViewModel.getRecipeById(id)
    ↓
RecipeDao.getRecipeById(id)
    ↓
Preenche formulário
    ↓
RecipeViewModel.updateRecipe(recipe)
    ↓
SQLite UPDATE
    ↓
MainActivity atualiza
```

### 4. Deletar Receita
```
MainActivity (clique em deletar)
    ↓
AlertDialog confirmação
    ↓
RecipeViewModel.deleteRecipe(recipe)
    ↓
RecipeDao.deleteRecipe(recipe)
    ↓
SQLite DELETE
    ↓
MainActivity remove item
```

## 🏗️ Dependências

```gradle
// Core Android
androidx.core:core-ktx:1.12.0
androidx.appcompat:appcompat:1.6.1

// Material Design
com.google.android.material:material:1.11.0

// Lifecycle
androidx.lifecycle:lifecycle-*:2.7.0

// Room Database
androidx.room:room-runtime:2.6.1
androidx.room:room-ktx:2.6.1

// Coroutines
org.jetbrains.kotlinx:kotlinx-coroutines-*:1.7.3

// Image Loading
com.squareup.picasso:picasso:2.8
```

## 🎯 Casos de Uso

### Use Case 1: Visualizar Receitas
```kotlin
// Em MainActivity
val viewModel: RecipeViewModel by viewModels()
viewModel.recipes.collect { recipes ->
    adapter.submitList(recipes)
}
```

### Use Case 2: Salvar Receita
```kotlin
// Em AddEditActivity
val recipe = Recipe(
    nome = "Bolo",
    tempo = 45,
    serve = 8,
    dificuldade = "Médio",
    imagem = "...",
    ingredientes = "...",
    modoPreparo = "..."
)
viewModel.addRecipe(recipe)
```

### Use Case 3: Obter Detalhes
```kotlin
// Em DetailActivity
val recipe = viewModel.getRecipeById(id)
// Exibir em UI
```

## 📱 Telas da Aplicação

### 1. MainActivity (Lista de Receitas)
- RecyclerView com todas as receitas
- Cada item mostra: nome, tempo, porções, dificuldade
- Botões: Editar, Excluir
- FAB para adicionar nova receita

### 2. DetailActivity (Detalhes)
- Exibe imagem em destaque
- Informações: tempo, porções, dificuldade
- Seção de ingredientes
- Seção de modo de preparo

### 3. AddEditActivity (Formulário)
- Campos: nome, tempo, porções, dificuldade
- URL da imagem
- EditText para ingredientes
- EditText para modo de preparo
- Botões: Salvar, Cancelar

## 🔐 Permissões Necessárias

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 🎨 Design System

### Cores (Material Design 3)
- **Primary**: #FF6B6B (Vermelho)
- **Primary Dark**: #E74C3C (Marrom)
- **Accent**: #FFD93D (Amarelo)
- **Text**: #333333 (Cinza escuro)
- **Background**: #FFFFFF (Branco)

### Tipografia
- **Títulos**: Bold, 18-24sp
- **Corpo**: Regular, 14sp
- **Pequeno**: Regular, 12sp

### Espaçamento
- **Margem padrão**: 16dp
- **Padding interno**: 12-16dp
- **Gap entre items**: 8dp

## 📊 Banco de Dados (Schema)

```sql
CREATE TABLE receitas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    tempo INTEGER NOT NULL,
    serve INTEGER NOT NULL,
    dificuldade TEXT NOT NULL,
    imagem TEXT,
    ingredientes TEXT NOT NULL,
    modoPreparo TEXT NOT NULL,
    dataCriacao INTEGER
);

CREATE INDEX idx_data ON receitas(dataCriacao);
```

## 🚀 Performance

| Operação | Tempo Esperado |
|----------|---|
| Carregar lista | <500ms |
| Adicionar receita | <200ms |
| Editar receita | <200ms |
| Deletar receita | <100ms |
| Buscar detalhes | <100ms |
| Carregar imagem | 1-3s (depende da URL) |

## 🔧 Configurações

### build.gradle (app)
- compileSdk: 34
- minSdk: 24
- targetSdk: 34
- Kotlin 1.9.0
- View Binding habilitado

### AndroidManifest.xml
- 3 Activities declaradas
- 1 Permissão (INTERNET)
- Tema Material Design 3
- API Level 24+ suportado

## 📈 Estatísticas do Código

```
Total de Classes: 8
  - Activities: 3
  - Models: 1
  - ViewModels: 1
  - Adapters: 1
  - Database: 2

Total de Arquivos XML: 7
  - Layouts: 4
  - Resources: 3

Linhas de Código Kotlin: ~600
Linhas de Código XML: ~400
```

---

**Versão**: 1.0
**Data**: Junho 2026
**Desenvolvedor**: Vinícius Alves Batista
