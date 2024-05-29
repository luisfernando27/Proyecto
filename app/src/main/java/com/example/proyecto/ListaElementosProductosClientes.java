package com.example.proyecto;

public class ListaElementosProductosClientes {
    public String id;
    public String nombreProducto;
    public String empresa;
    public String cantidad;
    public String precio;

    public ListaElementosProductosClientes(String id, String nombreProducto, String empresa, String cantidad,String precio) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.empresa = empresa;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getID() {
        return id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }


    public String getEmpresa() {
        return empresa;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getPrecio() {
        return precio;
    }

}
