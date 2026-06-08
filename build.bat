@echo off
REM Script para compilar e executar o projeto Android (Windows)
REM Uso: build.bat [clean|build|run|test|help]

setlocal enabledelayedexpansion

set PROJECT_NAME=Receitas
set GRADLE_WRAPPER=gradlew.bat

if not exist "%GRADLE_WRAPPER%" (
    echo Gradle wrapper nao encontrado. Certifique-se de estar na raiz do projeto.
    exit /b 1
)

if "%1%"=="" goto help

if /i "%1%"=="clean" (
    echo [*] Limpando build anterior...
    call %GRADLE_WRAPPER% clean
    if %errorlevel% equ 0 (
        echo [OK] Build limpo com sucesso
    )
    exit /b %errorlevel%
)

if /i "%1%"=="build" (
    echo [*] Compilando projeto...
    call %GRADLE_WRAPPER% build
    if %errorlevel% equ 0 (
        echo [OK] Projeto compilado com sucesso
    )
    exit /b %errorlevel%
)

if /i "%1%"=="run" (
    echo [*] Instalando em dispositivo...
    call %GRADLE_WRAPPER% installDebug
    if %errorlevel% equ 0 (
        echo [OK] Instalado com sucesso
    )
    exit /b %errorlevel%
)

if /i "%1%"=="test" (
    echo [*] Executando testes...
    call %GRADLE_WRAPPER% test
    if %errorlevel% equ 0 (
        echo [OK] Testes concluidos
    )
    exit /b %errorlevel%
)

if /i "%1%"=="full" (
    echo [*] Executando: Clean + Build + Install...
    call %GRADLE_WRAPPER% clean build installDebug
    if %errorlevel% equ 0 (
        echo [OK] Processo completo finalizado
    )
    exit /b %errorlevel%
)

:help
cls
echo.
echo %PROJECT_NAME% - Build Script (Windows)
echo.
echo Uso: build.bat [comando]
echo.
echo Comandos:
echo   clean     - Limpar builds anteriores
echo   build     - Compilar o projeto
echo   run       - Instalar em dispositivo/emulador
echo   test      - Executar testes
echo   full      - Clean + Build + Install
echo   help      - Mostrar esta mensagem
echo.
exit /b 0
