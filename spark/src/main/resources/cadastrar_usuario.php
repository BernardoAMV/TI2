<?php

	$dbHost = 'Localhost';
	$dbUsername = 'root';
	$dbPassword = 'ti@cc';
	$dbName = 'ti2cc';
	
	$conexao = pg_connect($dbHost,$dbUsername,$dbPassword,$dbName);
	
	$codigo = $_POST['codigo'];
        $login = $_POST['login'];
        $senha = $_POST['senha'];
        $sexo = $_POST['sexo'];

$query = "INSERT INTO usuario (codigo, login, senha, sexo) VALUES ('$codigo', '$login', '$senha', '$sexo')";

$result = pg_query($conexao, $query);

if ($result) {
    echo "Dados inseridos com sucesso!";
} else {
    echo "Erro ao inserir dados: " . pg_last_error($dbconn);
}
	
	

?>
