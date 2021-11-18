package com.itca.crud_finalproyect;

public class UrlVar {
    public static final String TAG = "InicioActivity";
    public static final String mensajex="";



    //URL para registrar una nueva categoria de productos.
    public static final String URL_registrar_categorias = "https://jamt29.000webhostapp.com/guardar_categorias.php";
    //URL para cargar valores en forma de lista de las categorias en Spinner (ForeignKey / Llave foranea)

    //Para la mostrar en spinner del layout productos.
    public static final String URL_consultaAllCategorias = "https://jamt29.000webhostapp.com/buscar_Categorias.php";

    //URL para registrar productos nuevos.
    public static final String URL_registrar_productos = "https://jamt29.000webhostapp.com/guardar_productos.php";

    //URL consulta de productos registrados por categoria (doble tabla)
    public static final String URL_consulta_catproducto = "https://jamt29.000webhostapp.com/consulta_tb2.php";

    //URL actualizar productos
    public static final String URL_actualizar_producto = "https://jamt29.000webhostapp.com/actualizar.php";
    //URL eliminar prodcutos
    public static final String URL_eliminar_producto = "https://jamt29.000webhostapp.com/eliminar.php";
}
