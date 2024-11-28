<?php
try {
    // Conectar ao banco de dados SQLite com PDO
    $db = new PDO('sqlite:receitas.db');
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    if (isset($_GET['id'])) {
        // Obter receita específica
        $id = (int)$_GET['id'];
        $stmt = $db->prepare('SELECT * FROM receitas WHERE id = :id');
        $stmt->bindParam(':id', $id, PDO::PARAM_INT);
        $stmt->execute();
        $receita = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($receita) {
            echo json_encode($receita);
        } else {
            echo json_encode(['error' => 'Receita não encontrada.']);
        }
    } else {
        // Obter todas as receitas
        $stmt = $db->query('SELECT id, nome, tempo, serve, dificuldade, imagem FROM receitas');
        $receitas = $stmt->fetchAll(PDO::FETCH_ASSOC);
        echo json_encode($receitas);
    }
} catch (PDOException $e) {
    echo json_encode(['error' => $e->getMessage()]);
}
?>
