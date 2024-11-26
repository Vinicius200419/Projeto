// Obter os parâmetros da URL para identificar a receita a ser editada
function getURLParameter(name) {
    return new URLSearchParams(window.location.search).get(name);
}

const receitaId = getURLParameter('id');

async function carregarDadosReceita() {
    try {
        const response = await fetch(`Back-end/get_receita.php?id=${receitaId}`); // Modificação aqui
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
        const response = await fetch('Back-end/editar_receita.php', {  // Modificação aqui
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
