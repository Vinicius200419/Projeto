<?php
try {
    // Conectar ao banco de dados usando PDO (SQLite)
    $db = new PDO('sqlite:receitas.db');
    
    // Configura o PDO para lançar exceções em caso de erro
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Receber o ID da receita via GET
    $id = $_GET['id'];

    // Preparar a consulta de exclusão
    $query = $db->prepare('DELETE FROM receitas WHERE id = :id');
    
    // Vincular o valor do parâmetro :id
    $query->bindParam(':id', $id, PDO::PARAM_INT);

    // Executar a consulta e retornar o resultado
    if ($query->execute()) {
        echo json_encode(['success' => true]);
    } else {
        echo json_encode(['success' => false]);
    }

} catch (PDOException $e) {
    // Se ocorrer erro na conexão ou na execução
    echo json_encode(['success' => false, 'error' => $e->getMessage()]);
}
?>
