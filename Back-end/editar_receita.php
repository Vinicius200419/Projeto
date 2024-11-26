<?php
// Configurar o cabeçalho para JSON
header('Content-Type: application/json');

try {
    // Verificar se todos os dados foram enviados
    if (!isset($_POST['id'], $_POST['nome'], $_POST['tempo'], $_POST['serve'], $_POST['dificuldade'], $_POST['ingredientes'], $_POST['preparo'])) {
        throw new Exception("Dados incompletos para atualizar a receita.");
    }

    // Sanitizar e obter os dados do formulário
    $id = intval($_POST['id']);
    $nome = htmlspecialchars($_POST['nome']);
    $tempo = intval($_POST['tempo']);
    $serve = intval($_POST['serve']);
    $dificuldade = htmlspecialchars($_POST['dificuldade']);
    $ingredientes = htmlspecialchars($_POST['ingredientes']);
    $preparo = htmlspecialchars($_POST['preparo']);

    // Conectar ao banco de dados SQLite com PDO
    $pdo = new PDO('sqlite:receitas.db');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION); // Modo de erros como exceções

    // Preparar a consulta SQL para atualizar os dados da receita
    $sql = "
        UPDATE receitas
        SET nome = :nome, tempo = :tempo, serve = :serve, dificuldade = :dificuldade, 
            ingredientes = :ingredientes, preparo = :preparo
        WHERE id = :id
    ";
    $stmt = $pdo->prepare($sql);

    // Vincular os valores aos parâmetros da consulta
    $stmt->bindValue(':id', $id, PDO::PARAM_INT);
    $stmt->bindValue(':nome', $nome, PDO::PARAM_STR);
    $stmt->bindValue(':tempo', $tempo, PDO::PARAM_INT);
    $stmt->bindValue(':serve', $serve, PDO::PARAM_INT);
    $stmt->bindValue(':dificuldade', $dificuldade, PDO::PARAM_STR);
    $stmt->bindValue(':ingredientes', $ingredientes, PDO::PARAM_STR);
    $stmt->bindValue(':preparo', $preparo, PDO::PARAM_STR);

    // Executar a consulta
    if ($stmt->execute()) {
        echo json_encode(['success' => true]);
    } else {
        throw new Exception("Erro ao atualizar a receita no banco de dados.");
    }
} catch (Exception $e) {
    // Retornar uma resposta de erro
    echo json_encode(['success' => false, 'error' => $e->getMessage()]);
}
?>
