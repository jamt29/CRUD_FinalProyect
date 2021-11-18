<?php
    include("connection_db1.php");
    if (isset($_POST["nombre_categoria"])){
        $nombre = $_POST['nombre_categoria'];

    //$query = "SELECT id_categoria,nom_categoria FROM tb_categorias";
    //SELECT tb_categorias.nom_categoria, tb_productos.nom_producto, des_producto, stock, precio, categoria from tb_categorias, tb_productos WHERE tb_categorias.id_categoria = tb_productos.categoria order by nom_categoria DESC
    //$query = "SELECT tb_categorias.nom_categoria, tb_productos.nom_producto, des_producto, stock, precio, categoria from tb_categorias, tb_productos WHERE tb_categorias.id_categoria = tb_productos.categoria order by id_categoria DESC";
    
    $query = "SELECT tb_categorias.nom_categoria, tb_productos.id_producto, nom_producto, des_producto, stock, precio, categoria, fecha_entrada from tb_categorias, tb_productos WHERE nom_categoria='".$nombre."' AND tb_categorias.id_categoria = tb_productos.categoria order by nom_categoria DESC";
        try {
            $link=conexion();    
            $comando = $link->prepare($query);
            // Ejecutar sentencia preparada
            $comando->execute();
            $data = array(); 
            while ($temp = $comando->fetch(PDO::FETCH_ASSOC)) {
                    $temp['nom_categoria'];
                    $temp['id_producto'];
                    $temp['nom_producto'];
                    $temp['des_producto'];
                    $temp['stock'];
                    $temp['precio'];
                    $temp['categoria'];
                    $temp['fecha_entrada'];
                    //array_push($data, $temp);
                    $datos[] = array_map("utf8_encode", $temp);
                    header('Content-type: application/json; charset=utf-8');
                }
                
                echo json_encode($datos, JSON_UNESCAPED_UNICODE);

        } catch (PDOException $e) {
            return false;
        }

    }

?>