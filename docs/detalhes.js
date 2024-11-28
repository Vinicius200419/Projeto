// Função para buscar os detalhes da receita
async function carregarDetalhes() {
    const urlParams = new URLSearchParams(window.location.search);
    const receitaId = urlParams.get('id'); // Obtém o ID da receita da URL

    try {
        const response = await fetch(`get_receitas.php?id=${receitaId}`);
        
        const receita = await response.json();

        // Se houver erro, exibe na tela
        if (receita.error) {
            document.getElementById('detalhesReceita').innerHTML = `Erro ao carregar a receita: ${receita.error}`;
            return;
        }

        // Exibe os detalhes da receita
        const detalhesDiv = document.getElementById('detalhesReceita');
        
        // Convertendo ingredientes em lista
        const ingredientes = receita.ingredientes.split('\n').map(item => `<li>${item}</li>`).join('');
        
        // Convertendo modo de preparo em lista
        const preparo = receita.preparo.split('\n').map(item => `<li>${item}</li>`).join('');
        
        detalhesDiv.innerHTML = `
            <h2>${receita.nome}</h2>
            <img src="${receita.imagem}" alt="${receita.nome}" width="200">
            <p><strong>Tempo de Preparo:</strong> ${receita.tempo} minutos</p>
            <p><strong>Porções:</strong> ${receita.serve}</p>
            <p><strong>Dificuldade:</strong> ${receita.dificuldade}</p>
            <h3>Ingredientes:</h3>
            <ul>${ingredientes}</ul>
            <h3>Modo de Preparo:</h3>
            <ol>${preparo}</ol>
        `;
    } catch (error) {
        console.error('Erro ao carregar detalhes da receita:', error);
    }
}

// Chama a função para carregar os detalhes da receita
window.onload = carregarDetalhes;
