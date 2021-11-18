<?php
include('main_class.php');

if (isset($_POST["codigo"])){
 	$codigo = $_POST['codigo'];

	$resultado = Mantenimiento::getAllCategorias();

	if ($resultado) {
	    
        echo $resultado;

    }else{ 
        //envio un 0 para decirle a android que no existe registros en la bd.
       echo "0";                 //solo esta línea para funcionamiento. 
       } 
}

?>