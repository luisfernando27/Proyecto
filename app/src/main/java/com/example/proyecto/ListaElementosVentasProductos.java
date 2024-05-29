package com.example.proyecto;

public class ListaElementosVentasProductos {
    public String idvp;

    public String nombreProducto;
    public String totalC;

    public String cantidad;

    public String precio;

    public ListaElementosVentasProductos(String idvp, String nombreProducto, String totalC, String cantidad, String precio) {
        this.idvp = idvp;
        this.nombreProducto = nombreProducto;
        this.totalC = totalC;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public String getIDVP() {
        return idvp;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getTotalC() {
        return totalC;
    }


    public String getCantidad() {
        return cantidad;
    }

    public String getPrecio() {
        return precio;
    }

}
