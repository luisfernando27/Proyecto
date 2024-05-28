package com.example.proyecto;

public class ListaElementosVentasProductos {
    public String nombreProducto;
    public String usuario;
    public String cantidadUsuario;
    public String total;

    public ListaElementosVentasProductos(String nombreProducto, String usuario, String cantidadUsuario, String total) {
        this.nombreProducto = nombreProducto;
        this.usuario = usuario;
        this.cantidadUsuario = cantidadUsuario;
        this.total = total;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCantidadUsuario() {
        return cantidadUsuario;
    }

    public void setCantidadUsuario(String cantidadUsuario) {
        this.cantidadUsuario = cantidadUsuario;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
