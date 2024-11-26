// Função para buscar os detalhes da receita
async function carregarDetalhes() {
    const urlParams = new URLSearchParams(window.location.search);
    const receitaId = urlParams.get('id'); // Obtém o ID da receita da URL

    try {
        const response = await fetch(`Back-end/get_receita.php?id=${receitaId}`); // Modificação aqui
        const receita = await response.json();

        if (receita.error) {
            document.getElementById('detalhesReceita').innerHTML = `Erro ao carregar a receita: ${receita.error}`;
            return;
        }

        const detalhesDiv = document.getElementById('detalhesReceita');
        detalhesDiv.innerHTML = `
            <h2>${receita.nome}</h2>
            <img src="${receita.imagem}" alt="${receita.nome}" width="200">
            <p><strong>Tempo de Preparo:</strong> ${receita.tempo} minutos</p>
            <p><strong>Porções:</strong> ${receita.serve}</p>
            <p><strong>Dificuldade:</strong> ${receita.dificuldade}</p>
            <h3>Ingredientes:</h3>
            <p>${receita.ingredientes}</p>
            <h3>Modo de Preparo:</h3>
            <p>${receita.preparo}</p>
        `;
    } catch (error) {
        console.error('Erro ao carregar detalhes da receita:', error);
    }
}

// Chama a função para carregar os detalhes da receita
window.onload = carregarDetalhes;
