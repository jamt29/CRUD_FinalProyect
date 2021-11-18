<?php
  //require_once("connection_db.php");
class Mantenimiento{
    
    public static function guardar_categoria($id_categoria, $nombre_categoria, $estado_categoria){
        include("connection_db.php");
        $query = "INSERT INTO  tb_categorias (id_categoria, nom_categoria, estado_categoria)
                                VALUES (?, ?, ?)";
        try{    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($id_categoria, $nombre_categoria, $estado_categoria));
          $count = $comando->rowCount();
        
          if($count > 0){
              return 1;
          }else{
              return 0;
          }
        } catch (PDOException $e) {
            return -1;
        }                        
    }
    

    public static function guardar_producto($a, $b, $c, $d, $e, $f, $g, $h){
        include("connection_db.php");
        $query = "INSERT INTO  tb_productos (id_producto, nom_producto, des_producto, stock, precio, unidad_medida, estado_producto, categoria)
                                VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try{    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($a, $b, $c, $d, $e, $f, $g, $h));
          $count = $comando->rowCount();
        
          if($count > 0){
              return 1;
          }else{
              return 0;
          }
        } catch (PDOException $e) {
            return -1;
        }                        
    }
    
    public static function getAllCategorias() {
        include("connection_db1.php");
        $query = "SELECT id_categoria, nom_categoria FROM tb_categorias";
        try {
            $link=conexion();
            $comando = $link->prepare($query);
            // Ejecutar sentencia preparada
            $comando->execute();
            $rows_array = array();
            while($result = $comando->fetch(PDO::FETCH_ASSOC))
                {
                     $array [] = array('id' => $result['id_categoria'], 
                                       'categoria' => $result['nom_categoria']);
                }
                //array_map("utf8_encode", $array);
  	            header('Content-type: application/json; charset=utf-8');
  	            return print_r(json_encode($array), JSON_UNESCAPED_UNICODE);
  	            //json_encode($datos, JSON_UNESCAPED_UNICODE);
                //return (var_dump($array));
                //return print_r($array);
        } catch (PDOException $e) {
            return false;
        }
    }

  
    public static function update_Productos($id, $nombre, $descripcion, $stock, $precio){
        include("connection_db.php");
        $query = "UPDATE tb_productos" .
            " SET nom_producto=?, des_producto=?, stock=?, precio=? " .
            "WHERE id_producto=?";

        try {    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($nombre, $descripcion, $stock, $precio, $id));
          //return $comando;
          $count = $comando->rowCount(); 
          if($count>0){
              return 1;
          }else{
              return 0;   
          }

        } catch (PDOException $e) {
            // Aqui puedes clasificar el error dependiendo de la excepcion
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
    

    public static function delete_Productos($id){
      include("connection_db.php");
      $query = "delete from tb_productos where id_producto=?";
      try{
          $link=conexion();
          $comando=$link->prepare($query);
          $comando->execute(array($id));
          //return $comando;
          $count = $comando->rowCount(); 
          if($count>0){
              return 1;
          }else{
              return 0;   
          }
          
      }catch (PDOException $e){
          return -1;
      }
  }
  
  
  
  
    
    
    
    











    //Estos métodos acontinuación no los he implementado aún.
    //Próximamente los utilizare.
    public static function getArticulosCodigo($codigo) {
        include("connection_db1.php");
        $query = "SELECT codigo,descripcion,precio from tb_articulos where codigo = ?";
    try {    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($codigo));
          $row = $comando->fetch(PDO::FETCH_ASSOC);
          return $row;

        } catch (PDOException $e) {
            // Aqui puedes clasificar el error dependiendo de la excepcion
            // para presentarlo en la respuesta Json
            return -1;
        }
  }
  
  
  public static function getArticulosDescripcion($desc) {
        include("connection_db1.php");
        $query = "SELECT codigo,descripcion,precio from tb_articulos where descripcion = ?";
    try {    
          $link=conexion();    
          $comando = $link->prepare($query);
          $comando->execute(array($desc));
          $row = $comando->fetch(PDO::FETCH_ASSOC);
          return $row;

        } catch (PDOException $e) {
            // Aqui puedes clasificar el error dependiendo de la excepcion
            // para presentarlo en la respuesta Json
            return -1;
        }
  }
  
  
  
      //by Prof. Gamez.
}
?>