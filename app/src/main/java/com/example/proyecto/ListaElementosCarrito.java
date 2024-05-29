package com.example.proyecto;

public class ListaElementosCarrito {
    public String nombreProducto;
    public String cantidad;
    public String precio;

    public ListaElementosCarrito(String nombreProducto, String cantidad, String precio) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }


    public String getCantidad() {
        return cantidad;
    }


    public String getPrecio() {
        return precio;
    }



}
