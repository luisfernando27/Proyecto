package com.example.proyecto;

public class ListaElementosClientes {
    public String nombreOferta;
    public String nombreEmpresa;
    public String descripcion;
    public String precio;

    public ListaElementosClientes(String nombreOferta, String nombreEmpresa, String descripcion, String precio) {
        this.nombreOferta = nombreOferta;
        this.nombreEmpresa = nombreEmpresa;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
