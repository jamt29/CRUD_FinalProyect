<?php
//require 'main_class.php';
include('manto_usuarios.php');

if (isset($_POST["email"]) && ($_POST["clave"])){
 	$email = $_POST['email'];
 	$clave = $_POST['clave'];

	$resultado = manto_Users::getLogin($email, $clave);
	//echo $retorno;
	if ($resultado) {
			
		//PRUEBA FUNCIONAL.
		//echo "1";   //solo esta línea para funcionamiento actual.
		$datos = array();
		$datos[] = array_map("utf8_encode", $resultado);
  		header('Content-type: application/json; charset=utf-8');
		//$json_array = json_encode($datos);
		//echo $json_array;

    echo json_encode($datos, JSON_UNESCAPED_UNICODE);

	}else{ 
        //envio un 0 para decirle a android que no existe el usuario MASTER introducido en la activity de LOGIN.
        echo "0";                 //solo esta línea para funcionamiento actual. 
    } 
}