// Obter os parâmetros da URL para identificar a receita a ser editada
function getURLParameter(name) {
    return new URLSearchParams(window.location.search).get(name);
}

const receitaId = getURLParameter('id');

async function carregarDadosReceita() {
    try {
        // Modificação: URL do servidor do Codespaces
        const response = await fetch(`get_receitas.php?id=${receitaId}`);
        const receita = await response.json();

        // Preencher o formulário com os dados da receita
        document.getElementById('editNome').value = receita.nome;
        document.getElementById('editTempo').value = receita.tempo;
        document.getElementById('editPorcoes').value = receita.serve;
        document.getElementById('editDificuldade').value = receita.dificuldade;
        document.getElementById('editIngredientes').value = receita.ingredientes;
        document.getElementById('editPreparo').value = receita.preparo;
    } catch (error) {
        console.error('Erro ao carregar os dados da receita:', error);
    }
}

document.getElementById('editForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const formData = new FormData(event.target);
    formData.append('id', receitaId);

    try {
        // Modificação: URL do servidor do Codespaces para editar a receita
        const response = await fetch(`editar_receita.php`, {
            method: 'POST',
            body: formData
        });
        const result = await response.json();

        if (result.success) {
            alert('Receita editada com sucesso!');
            window.location.href = 'index.html'; // Redireciona para a página principal
        } else {
            alert('Erro ao editar a receita.');
        }
    } catch (error) {
        console.error('Erro ao salvar as alterações:', error);
    }
});

// Chama a função para carregar os dados ao iniciar
window.onload = carregarDadosReceita;
