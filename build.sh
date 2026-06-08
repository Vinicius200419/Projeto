#!/bin/bash

# Script para compilar e executar o projeto Android
# Uso: ./build.sh [clean|build|run|test|help]

set -e

PROJECT_NAME="Receitas"
GRADLE_WRAPPER="./gradlew"

# Cores para output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

print_header() {
    echo -e "${BLUE}========================================${NC}"
    echo -e "${BLUE}$1${NC}"
    echo -e "${BLUE}========================================${NC}"
}

print_success() {
    echo -e "${GREEN}✓ $1${NC}"
}

print_info() {
    echo -e "${YELLOW}ℹ $1${NC}"
}

# Verificar se gradle wrapper existe
if [ ! -f "$GRADLE_WRAPPER" ]; then
    print_info "Gradle wrapper não encontrado. Use ./gradlew para Windows ou gradlew para Linux"
    exit 1
fi

case "${1:-help}" in
    clean)
        print_header "Limpando build anterior"
        $GRADLE_WRAPPER clean
        print_success "Build limpo com sucesso"
        ;;
    
    build)
        print_header "Compilando projeto"
        $GRADLE_WRAPPER build
        print_success "Projeto compilado com sucesso"
        ;;
    
    run)
        print_header "Instalando em dispositivo"
        $GRADLE_WRAPPER installDebug
        print_success "Instalado com sucesso"
        ;;
    
    test)
        print_header "Executando testes"
        $GRADLE_WRAPPER test
        print_success "Testes concluídos"
        ;;
    
    full)
        print_header "Build limpo + Compilação + Instalação"
        $GRADLE_WRAPPER clean build installDebug
        print_success "Processo completo finalizado"
        ;;
    
    *)
        echo -e "${BLUE}$PROJECT_NAME - Build Script${NC}"
        echo ""
        echo "Uso: ./build.sh [comando]"
        echo ""
        echo "Comandos:"
        echo "  clean     - Limpar builds anteriores"
        echo "  build     - Compilar o projeto"
        echo "  run       - Instalar em dispositivo/emulador"
        echo "  test      - Executar testes"
        echo "  full      - Clean + Build + Install"
        echo "  help      - Mostrar esta mensagem"
        echo ""
        ;;
esac
