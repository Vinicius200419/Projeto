<?php
try {
    // Conectar ao banco de dados SQLite com PDO
    $db = new PDO('sqlite:receitas.db');
    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Receber dados do formulário
    $nome = $_POST['nome'];
    $tempo = $_POST['tempo'];
    $serve = $_POST['serve'];
    $dificuldade = $_POST['dificuldade'];
    $ingredientes = $_POST['ingredientes'];
    $preparo = $_POST['preparo'];

    // Salvar a imagem (se houver)
    $target_dir = "uploads/";
    $image_path = $target_dir . basename($_FILES["image"]["name"]);
    move_uploaded_file($_FILES["image"]["tmp_name"], $image_path);

    // Inserir a receita no banco de dados
    $stmt = $db->prepare("INSERT INTO receitas (nome, tempo, serve, dificuldade, ingredientes, preparo, imagem) 
                          VALUES (:nome, :tempo, :serve, :dificuldade, :ingredientes, :preparo, :imagem)");
    $stmt->bindParam(':nome', $nome);
    $stmt->bindParam(':tempo', $tempo, PDO::PARAM_INT);
    $stmt->bindParam(':serve', $serve, PDO::PARAM_INT);
    $stmt->bindParam(':dificuldade', $dificuldade);
    $stmt->bindParam(':ingredientes', $ingredientes);
    $stmt->bindParam(':preparo', $preparo);
    $stmt->bindParam(':imagem', $image_path);
    $stmt->execute();

    // Redirecionar para a página inicial
    header("Location: index.html");
    exit;

} catch (PDOException $e) {
    // Em caso de erro, exibe a mensagem de erro
    echo "Erro: " . $e->getMessage();
}
?>
