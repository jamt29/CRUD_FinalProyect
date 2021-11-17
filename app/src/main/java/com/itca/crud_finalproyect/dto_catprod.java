package com.itca.crud_finalproyect;

import android.os.Parcelable;

public class dto_catprod {
    String nombre_categoria;
    String id_producto;
    String nombre_producto;
    String des_producto;
    double stock;
    double precio;
    int categoria;
    String fecha;

    public dto_catprod(Parcelable data) {
    }


    public dto_catprod(String nombre_categoria, String id_producto, String nombre_producto, String des_producto, double stock, double precio, int categoria, String fecha) {
        this.nombre_categoria = nombre_categoria;
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.des_producto = des_producto;
        this.stock = stock;
        this.precio = precio;
        this.categoria = categoria;
        this.fecha = fecha;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDes_producto() {
        return des_producto;
    }

    public void setDes_producto(String des_producto) {
        this.des_producto = des_producto;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
