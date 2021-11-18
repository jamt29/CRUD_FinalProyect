<?php
include('main_class.php');

if (isset($_POST["id"]) && (@$_POST["nombre_producto"]) && (@$_POST["descripcion_producto"])){
 	$id = $_POST['id'];
    @$nombre = $_POST['nombre_producto'];
    @$descripcion = $_POST['descripcion_producto'];
    @$stock = $_POST['stock_producto'];
    @$precio = $_POST['precio_producto'];

	$resultado = Mantenimiento::update_Productos($id, $nombre, $descripcion, $stock, $precio);
	
	if ($resultado==1) {
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 1,"mensaje" => "Registro Actualizado Correctamente."));
        echo $json_string;
        
        //$json_string = json_encode(array('estado' => '1','mensaje' => 'Actualizacion aplicada correctamente.'));
        //echo json_encode($json_string, JSON_UNESCAPED_UNICODE);
        
    } else {
        header('Content-type: application/json; charset=utf-8');
        $json_string = json_encode(array("estado" => 2,"mensaje" => "No hay cambios que actualizar."));
		echo $json_string;
    }
    
    
}

?>