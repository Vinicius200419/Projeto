# Instruções de Configuração do Ambiente Android

## Pré-requisitos

1. **Java Development Kit (JDK)**
   - Java 11 ou superior
   - Download: https://www.oracle.com/java/technologies/downloads/

2. **Android Studio**
   - Download: https://developer.android.com/studio
   - Versão recomendada: Flamingo (2022.2.1) ou superior

3. **Android SDK**
   - API Level 24+ (recomendado: API 34)
   - Será instalado automaticamente pelo Android Studio

## Instalação

### 1. Instalar Android Studio

- Download em https://developer.android.com/studio
- Siga o assistente de instalação
- Durante a instalação, deixe marcar "Android SDK", "Android SDK Platform" e "Android Virtual Device"

### 2. Clonar/Abrir Projeto

```bash
# Opção 1: Via Git
git clone <url-do-repositorio>
cd Projeto_Android

# Opção 2: Abrir no Android Studio
# File → Open → Selecionar pasta Projeto_Android
```

### 3. Sincronizar Gradle

- Android Studio sincronizará automaticamente
- Ou execute manualmente: `./gradlew sync`

### 4. Executar a Aplicação

**Via Android Studio:**
- Conecte um dispositivo Android via USB ou inicie um emulador
- Clique em "Run" (Shift+F10)

**Via Terminal:**
```bash
./gradlew installDebug
# A app será instalada no dispositivo conectado
```

## Estrutura de Arquivos Importantes

### src/main/AndroidManifest.xml
- Declara atividades, permissões e configurações gerais

### src/main/java/com/receitas/
- Código-fonte Kotlin da aplicação

### src/main/res/
- **layout/** - Arquivos XML dos layouts
- **values/** - Strings, cores, dimensões
- **drawable/** - Imagens vetoriais
- **mipmap/** - Ícones da aplicação

## Tarefas Gradle Úteis

```bash
# Compilar
./gradlew build

# Executar testes
./gradlew test

# Instalar em emulador/dispositivo
./gradlew installDebug

# Executar com output detalhado
./gradlew build --info

# Limpar builds anteriores
./gradlew clean
```

## Debugging

### Logcat
- View → Tool Windows → Logcat
- Filtre por "com.receitas" para ver logs da app

### Android Profiler
- View → Tool Windows → Profiler
- Monitore CPU, memória e bateria

### Debug Mode
```bash
./gradlew installDebug
# Use breakpoints no Android Studio
```

## Emulador Android

### Criar um novo emulador:
1. Tools → Device Manager
2. "Create device"
3. Selecione o dispositivo desejado
4. Selecione o API Level (recomendado: 34)
5. Termine a configuração

### Iniciar emulador:
```bash
# Lista emuladores disponíveis
emulator -list-avds

# Inicia um emulador
emulator -avd <nome_do_emulador>
```

## Troubleshooting

### Erro: "SDK location not found"
```bash
# Crie o arquivo local.properties na raiz do projeto
echo "sdk.dir=/path/to/android-sdk" > local.properties
```

### Erro: "Gradle sync failed"
1. File → Invalidate Caches → Invalidate and Restart
2. Execute `./gradlew clean`
3. Abra o projeto novamente

### Erro: "No connected devices"
1. Ative "USB Debugging" no dispositivo (Configurações → Opções de desenvolvedor)
2. Ou crie um emulador via Device Manager

## Próximas Etapas

1. Explore as Activities em `src/main/java/com/receitas/`
2. Modifique os layouts em `src/main/res/layout/`
3. Adicione novas funcionalidades conforme necessário
4. Teste em diferentes tamanhos de tela

## Documentação Útil

- Android Developer: https://developer.android.com
- Kotlin Docs: https://kotlinlang.org/docs/
- Room Database: https://developer.android.com/training/data-storage/room
- Material Design: https://material.io/design
