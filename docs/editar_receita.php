<?php
// Verifica se todos os dados necessários foram enviados
if (!isset($_POST['id'], $_POST['nome'], $_POST['tempo'], $_POST['serve'], $_POST['dificuldade'], $_POST['ingredientes'], $_POST['preparo'])) {
    echo json_encode(['success' => false, 'error' => 'Dados incompletos para atualizar a receita.']);
    exit;
}

try {
    // Converte os dados recebidos e faz a sanitização
    $id = intval($_POST['id']); // Sanitiza o ID
    $nome = htmlspecialchars($_POST['nome']);
    $tempo = intval($_POST['tempo']);
    $serve = intval($_POST['serve']);
    $dificuldade = htmlspecialchars($_POST['dificuldade']);
    $ingredientes = htmlspecialchars($_POST['ingredientes']);
    $preparo = htmlspecialchars($_POST['preparo']);

    // Conecta ao banco de dados SQLite
    $pdo = new PDO('sqlite:receitas.db');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    

    // SQL para atualizar os dados
    $sql = "
        UPDATE receitas
        SET nome = :nome, tempo = :tempo, serve = :serve, dificuldade = :dificuldade, 
            ingredientes = :ingredientes, preparo = :preparo
        WHERE id = :id
    ";

    $stmt = $pdo->prepare($sql);

    // Vincula os parâmetros
    $stmt->bindValue(':id', $id, PDO::PARAM_INT);
    $stmt->bindValue(':nome', $nome, PDO::PARAM_STR);
    $stmt->bindValue(':tempo', $tempo, PDO::PARAM_INT);
    $stmt->bindValue(':serve', $serve, PDO::PARAM_INT);
    $stmt->bindValue(':dificuldade', $dificuldade, PDO::PARAM_STR);
    $stmt->bindValue(':ingredientes', $ingredientes, PDO::PARAM_STR);
    $stmt->bindValue(':preparo', $preparo, PDO::PARAM_STR);

    // Executa a consulta e verifica se foi bem-sucedida
    if ($stmt->execute()) {
        echo json_encode(['success' => true]);
    } else {
        echo json_encode(['success' => false, 'error' => 'Erro ao atualizar a receita.']);
    }
} catch (Exception $e) {
    // Captura exceções e retorna o erro
    echo json_encode(['success' => false, 'error' => $e->getMessage()]);
}
?>
