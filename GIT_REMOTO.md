# 🚀 Guia: Subir Projeto Android para Git Remoto

## ✅ Status Atual

```
✅ Repositório Git local criado
✅ 37 arquivos commitados
✅ Commit inicial: "Projeto Android Receitas - Transformação..."
✅ Branch: master
```

### Último Commit
```
716063c - Projeto Android Receitas - Transformação Web para Android completa...
```

---

## 🔗 Opção 1: GitHub

### 1. Criar Repositório no GitHub

1. Acesse https://github.com/new
2. **Repository name**: `Receitas-Android` (ou outro nome)
3. **Description**: "Transformação de projeto web para app Android nativo"
4. **Visibility**: Public (ou Private)
5. Clique em "Create repository"
6. **Copie** a URL remota fornecida (ex: `https://github.com/seu-usuario/Receitas-Android.git`)

### 2. Adicionar Remoto e Fazer Push

```bash
cd c:\Projetos\Projeto_Android

# Adicionar remoto (substitua URL)
git remote add origin https://github.com/seu-usuario/Receitas-Android.git

# Verificar remoto
git remote -v

# Fazer push (primeira vez)
git branch -M main
git push -u origin main
```

### 3. Pronto! 🎉

Seu projeto estará em: `https://github.com/seu-usuario/Receitas-Android`

---

## 🔗 Opção 2: GitLab

### 1. Criar Projeto no GitLab

1. Acesse https://gitlab.com/projects/new
2. **Project name**: `receitas-android`
3. **Visibility**: Public (ou Private)
4. Clique em "Create project"
5. **Copie** a URL remota fornecida

### 2. Adicionar Remoto e Fazer Push

```bash
cd c:\Projetos\Projeto_Android

git remote add origin https://gitlab.com/seu-usuario/receitas-android.git
git branch -M main
git push -u origin main
```

---

## 🔗 Opção 3: Gitea (Auto-hospedado)

Se você tem um servidor Gitea próprio:

```bash
cd c:\Projetos\Projeto_Android

git remote add origin https://seu-servidor.com/seu-usuario/receitas-android.git
git push -u origin master
```

---

## 🔗 Opção 4: Bitbucket

### 1. Criar Repositório

1. Acesse https://bitbucket.org/
2. Crie um novo repositório
3. Copie a URL

### 2. Fazer Push

```bash
cd c:\Projetos\Projeto_Android

git remote add origin https://bitbucket.org/seu-usuario/receitas-android.git
git push -u origin master
```

---

## 📝 Comandos Úteis

### Verificar Remoto
```bash
git remote -v
```

### Remover Remoto (se errar)
```bash
git remote remove origin
```

### Ver Status
```bash
git status
```

### Ver Histórico
```bash
git log --oneline
```

---

## 🔐 Autenticação

### SSH (Recomendado)

1. Gere chave SSH:
```bash
ssh-keygen -t ed25519 -C "seu-email@example.com"
```

2. Adicione a chave pública em GitHub/GitLab
3. Use URL SSH:
```bash
git remote add origin git@github.com:seu-usuario/receitas-android.git
```

### HTTPS com Token

Para GitHub:
```bash
# Use token ao invés de senha
git remote add origin https://seu-token@github.com/seu-usuario/receitas-android.git
```

---

## 🌳 Branches Adicionais (Opcional)

### Criar Branch de Desenvolvimento
```bash
git checkout -b develop
git push -u origin develop
```

### Criar Branch de Feature
```bash
git checkout -b feature/nova-funcionalidade
git push -u origin feature/nova-funcionalidade
```

---

## 📊 Próximos Commits

### Adicionar Mudanças
```bash
git add arquivo.kt
# ou adicionar tudo
git add .
```

### Fazer Commit
```bash
git commit -m "Descrição clara da mudança"
```

### Fazer Push
```bash
git push origin master
```

### Exemplo com Padrão Conventonal
```bash
git commit -m "feat: adicionar busca de receitas"
git commit -m "fix: corrigir layout em tablets"
git commit -m "docs: atualizar README"
git commit -m "refactor: melhorar performance do RecyclerView"
```

---

## 📋 Template de Commit

Sugiro usar este padrão:

```
<tipo>: <descrição curta>

<corpo opcional com mais detalhes>

<footer opcional com referências>
```

### Tipos de Commit
- `feat` - Nova funcionalidade
- `fix` - Correção de bug
- `docs` - Documentação
- `style` - Mudanças de estilo (sem lógica)
- `refactor` - Refatoração de código
- `test` - Testes
- `chore` - Tarefas de build/deps

### Exemplos
```bash
git commit -m "feat: implementar darkMode"
git commit -m "fix: corrigir crash ao deletar receita"
git commit -m "docs: atualizar SETUP.md"
```

---

## 🔄 Sincronizar com Remoto

### Atualizar seu repositório local
```bash
git fetch origin
git pull origin master
```

### Enviar para remoto
```bash
git push origin master
```

---

## 👥 Colaboração

### Clonar repositório (para outros)
```bash
git clone https://github.com/seu-usuario/Receitas-Android.git
cd Receitas-Android
```

### Fazer contribuição
```bash
git checkout -b feature/minha-feature
# ... fazer mudanças ...
git commit -m "feat: adicionar funcionalidade"
git push origin feature/minha-feature
# Criar Pull Request no GitHub/GitLab
```

---

## 📝 .gitignore já configurado

O projeto já possui `.gitignore` configurado para:
- `.gradle/`
- `.idea/`
- `*.iml`
- `build/`
- `*.apk`
- `local.properties`
- `.DS_Store`

---

## 🎯 Checklist Final

- [ ] Criar repositório remoto (GitHub/GitLab/etc)
- [ ] Adicionar remoto: `git remote add origin <URL>`
- [ ] Fazer push: `git push -u origin master`
- [ ] Adicionar README no GitHub (já temos!)
- [ ] Adicionar description e topics
- [ ] Adicionar license (opcional)
- [ ] Ativar GitHub Pages (opcional)

---

## 🚀 Após o Push

1. **Adicione badge no README**
```markdown
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen)]()
[![Android API](https://img.shields.io/badge/API-24%2B-brightgreen)]()
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.0-blue)]()
```

2. **Adicione Topics** no GitHub:
   - `android`
   - `kotlin`
   - `recipe-app`
   - `material-design`
   - `mvvm`

3. **Configure GitHub Pages** (opcional):
   - Docs → GitHub Pages → Branch: main

---

## 💡 Dicas

- Faça commits frequentes
- Use mensagens claras
- Mantenha branches organizadas
- Revise código antes de push
- Use `.gitignore` corretamente
- Sincronize com remoto regularmente

---

## 🔗 Recursos

- [GitHub Documentation](https://docs.github.com)
- [GitLab Documentation](https://docs.gitlab.com)
- [Git Documentation](https://git-scm.com/doc)
- [Conventional Commits](https://www.conventionalcommits.org)

---

**Próximo passo**: Escolha sua plataforma e siga as instruções acima! 🚀

*Última atualização: Junho 2026*
