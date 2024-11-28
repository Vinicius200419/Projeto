// Função genérica para carregar páginas com animações de transição
function carregarPagina(url) {
    const contentDiv = document.getElementById('content');

    // Adiciona a classe fade-out para iniciar a animação de saída
    contentDiv.classList.add('fade-out');

    // Espera a animação de fade-out terminar antes de substituir o conteúdo
    setTimeout(() => {
        // Modificação: URL completa do servidor do Codespaces
        fetch(url)
            .then(response => {
                if (response.ok) {
                    return response.text();
                } else {
                    throw new Error('Erro ao carregar a página');
                }
            })
            .then(html => {
                // Substitui o conteúdo de #content com o HTML carregado
                contentDiv.innerHTML = html;

                // Adiciona a classe fade-in para iniciar a animação de entrada
                contentDiv.classList.remove('fade-out');
                contentDiv.classList.add('fade-in');

                // Remove a classe fade-in após a transição
                setTimeout(() => contentDiv.classList.remove('fade-in'), 500);

                // Rolagem suave para o topo da página
                window.scrollTo({
                    top: 0,
                    behavior: 'smooth',
                });
            })
            .catch(error => {
                console.error('Erro ao carregar a página:', error);
            });
    }, 500); // Aguarda 500ms para a animação de fade-out acontecer antes de carregar o conteúdo
}

// Seleciona o botão de navegação para a página 'adicionar.html'
document.getElementById('loadAddPageBtn').addEventListener('click', function (e) {
    e.preventDefault();
    carregarPagina('adicionar.html');
});
