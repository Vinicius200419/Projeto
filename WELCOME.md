# 🍳 Receitas - Projeto Android

> Transformação de projeto web em aplicativo Android nativo

## 📱 O que foi transformado?

### De (Web):
```
HTML/CSS/JavaScript frontend
        ↓
PHP backend
        ↓
SQLite database
```

### Para (Android):
```
Kotlin Activities + Material Design UI
        ↓
Room Database (SQLite)
        ↓
ViewModel + LiveData (Reactive)
```

## ✨ Funcionalidades

- ✅ Listar todas as receitas
- ✅ Adicionar nova receita
- ✅ Editar receita existente
- ✅ Excluir receita
- ✅ Ver detalhes completos
- ✅ Carregamento de imagens
- ✅ Dados persistentes locais

## 🏗️ Arquitetura

```
Android MVVM Architecture
│
├── UI Layer (Activities)
│   ├── MainActivity
│   ├── DetailActivity
│   └── AddEditActivity
│
├── Data Layer
│   ├── Room Database
│   ├── RecipeDao
│   └── Recipe Entity
│
└── Business Logic Layer
    └── RecipeViewModel
```

## 🚀 Quick Start

### 1. Abrir no Android Studio
```
File → Open → Projeto_Android
```

### 2. Sincronizar Gradle
- Aguarde a sincronização automática

### 3. Executar
```
Run → Run 'app'  (Shift+F10)
```

### 4. Instalar em Dispositivo/Emulador
```bash
./gradlew installDebug
```

## 📁 Estrutura do Projeto

```
Projeto_Android/
├── app/
│   ├── src/main/
│   │   ├── java/com/receitas/
│   │   │   ├── MainActivity.kt
│   │   │   ├── DetailActivity.kt
│   │   │   ├── AddEditActivity.kt
│   │   │   ├── adapter/RecipeAdapter.kt
│   │   │   ├── database/
│   │   │   ├── models/
│   │   │   └── viewmodel/
│   │   └── res/
│   │       ├── layout/
│   │       └── values/
│   ├── build.gradle
│   └── AndroidManifest.xml
├── build.gradle
├── settings.gradle
├── README.md
├── SETUP.md
└── BACKEND_MIGRATION.md
```

## 🛠️ Tecnologias

| Componente | Tecnologia |
|-----------|-----------|
| Linguagem | Kotlin 1.9+ |
| Database | Room 2.6+ |
| UI | Material Components 3 |
| Async | Coroutines + Flow |
| Lifecycle | AndroidX Lifecycle |
| Images | Picasso 2.8 |

## 📚 Documentação

- **README.md** - Visão geral e instruções
- **SETUP.md** - Configuração do ambiente
- **BACKEND_MIGRATION.md** - Guia de migração de dados
- **CODE** - Comentários inline em Kotlin

## 🎯 Comparação: Web vs Android

### Funcionalidade
| Recurso | Web | Android |
|---------|-----|---------|
| Listar | ✅ PHP+JS | ✅ ViewModel |
| Adicionar | ✅ PHP form | ✅ Activity |
| Editar | ✅ PHP form | ✅ Activity |
| Deletar | ✅ AJAX | ✅ Coroutine |
| Persistência | ✅ SQLite | ✅ Room |

### Performance
| Métrica | Web | Android |
|---------|-----|---------|
| Carregamento | ~1-2s | <500ms |
| Responsividade | ~300ms | <100ms |
| Offline | ❌ Não | ✅ Sim |
| Offline-First | ❌ Não | ✅ Sim |

## 🔄 Migração de Dados

Os dados podem ser exportados do SQLite PHP e importados no Android:

```bash
# Exportar do PHP
sqlite3 receitas.db ".dump receitas" > dump.sql

# Usar em Android (criar migration no Room)
```

Veja **BACKEND_MIGRATION.md** para detalhes.

## 🐛 Debugging

### Ver Logs
```
View → Tool Windows → Logcat
# Filtre por: com.receitas
```

### Database Inspector
```
View → Tool Windows → Database Inspector
# Browse: receitas.db → receitas
```

### Emulador
```
Tools → Device Manager → Create Virtual Device
```

## 📋 Requisitos

- ✅ Android 7.0+ (API 24+)
- ✅ Android Studio Flamingo+
- ✅ Java 11+
- ✅ ~500MB de espaço em disco

## 🎨 Temas e Cores

Tema Material Design 3:
- **Primary**: Vermelho (#FF6B6B)
- **Secondary**: Amarelo (#FFD93D)
- **Dark**: Marrom escuro (#E74C3C)

## 📱 Suporte a Dispositivos

- Telefones: 4" a 6.5"
- Tablets: 7" a 12"
- Orientações: Portrait e Landscape
- DPI: LDPI a XXXHDPI

## 🚀 Próximas Melhorias

- 🔄 Sincronização com backend PHP
- 🔐 Autenticação de usuário
- 📸 Capturar fotos da câmera
- ❤️ Sistema de favoritos
- 🏷️ Categorias de receitas
- 🔍 Busca e filtros
- 📱 Widget de receita
- 🌙 Modo escuro
- 🌐 Sincronização com Firebase

## 👨‍💻 Desenvolvedor

**Nome**: Vinícius Alves Batista
**RA**: 2024.0360.0188
**Projeto**: Transformação Web → Android

## 📖 Referências

- [Android Developers](https://developer.android.com)
- [Kotlin Documentation](https://kotlinlang.org/docs)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Material Design](https://material.io/design)

## 📝 Licença

Projeto educacional - Livre para uso e modificação

---

**Última atualização**: Junho 2026
**Versão**: 1.0
