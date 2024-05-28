package com.example.proyecto;

public class ListaElementosVentasOfertas {
    public String nombreOferta;
    public String usuario;
    public String cantidadUsuario;
    public String total;

    public ListaElementosVentasOfertas(String nombreOferta, String usuario, String cantidadUsuario, String total) {
        this.nombreOferta = nombreOferta;
        this.usuario = usuario;
        this.cantidadUsuario = cantidadUsuario;
        this.total = total;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
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
