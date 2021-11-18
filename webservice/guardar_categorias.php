<?php
include('main_class.php');
//require 'main_class.php';
if(isset($_POST['id']) && (@$_POST['nombre'])){
    $id_categoria = $_POST['id'];
    @$nombre_categoria = $_POST["nombre"];
    $estado_categoria = $_POST["estado"];

    $resultado = Mantenimiento::guardar_categoria($id_categoria, $nombre_categoria, $estado_categoria);

	if ($resultado==1) {
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 1,"mensaje" => "Registro guardado correctamente."));
        echo $json_string;
        //echo "Registro guardado...";
    } else {
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 2,"mensaje" => "ERROR. NO SE PUEDO GUARDAR..."));
		echo $json_string;
    }

}else{
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 3,"mensaje" => "No se ha enviado toda la información"));
		echo $json_string;
}
?>