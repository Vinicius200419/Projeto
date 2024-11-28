// Função para buscar as receitas do backend
async function carregarReceitas() {
    try {
        // Fazer requisição para obter as receitas
        const response = await fetch('get_receitas.php');

        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.statusText}`);
        }

        const receitas = await response.json();

        if (receitas.error) {
            throw new Error(receitas.error);
        }

        // Selecionar o contêiner de receitas
        const receitasDiv = document.getElementById('receitas');

        // Limpar o conteúdo atual
        receitasDiv.innerHTML = '';

        // Verificar se há receitas
        if (receitas.length === 0) {
            receitasDiv.innerHTML = '<p>Nenhuma receita encontrada.</p>';
            return;
        }

        // Exibir receitas resumidas
        receitas.forEach(receita => {
            const receitaDiv = document.createElement('div');
            receitaDiv.classList.add('receita');

            receitaDiv.innerHTML = `
                <h3>${receita.nome}</h3>
                <img src="${receita.imagem}" alt="${receita.nome}" width="200">
                <p>Tempo: ${receita.tempo} min</p>
                <p>Porções: ${receita.serve}</p>
                <p>Dificuldade: ${receita.dificuldade}</p>
                <button class="editar-btn" onclick="editarReceita(${receita.id})">Editar</button>
                <button class="excluir-btn" onclick="excluirReceita(${receita.id})">Excluir</button>
                <a href="detalhes.html?id=${receita.id}" class="detalhesLink">Ver detalhes</a>
            `;

            receitasDiv.appendChild(receitaDiv);
        });
    } catch (error) {
        console.error('Erro ao carregar receitas:', error);

        // Exibir mensagem de erro para o usuário
        const receitasDiv = document.getElementById('receitas');
        receitasDiv.innerHTML = `<p>Erro ao carregar receitas: ${error.message}</p>`;
    }
}

// Função para redirecionar para a página de edição
function editarReceita(id) {
    window.location.href = `editar.html?id=${id}`;
}

// Função para excluir uma receita
async function excluirReceita(id) {
    if (confirm('Tem certeza que deseja excluir esta receita?')) {
        try {
            // Requisição para excluir a receita no backend
            const response = await fetch(`delete_receita.php?id=${id}`, { method: 'DELETE' });

            if (!response.ok) {
                throw new Error(`Erro na requisição: ${response.statusText}`);
            }

            const resultado = await response.json();

            if (resultado.success) {
                alert('Receita excluída com sucesso!');
                carregarReceitas(); // Recarregar a lista de receitas
            } else {
                throw new Error(resultado.error || 'Erro ao excluir a receita.');
            }
        } catch (error) {
            console.error('Erro ao excluir receita:', error);
            alert(`Erro ao excluir a receita: ${error.message}`);
        }
    }
}

// Carregar as receitas ao carregar a página
window.onload = carregarReceitas;
