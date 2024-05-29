package com.example.proyecto;

public class ListaElementosOfertasEmpresa {
    public String nombreOferta;
    public String cantidadMaxima;
    public String totalOfertas;
    public String precio;
    public String fechaInicio;
    public String fechaFin;

    public ListaElementosOfertasEmpresa(String nombreOferta, String cantidadMaxima, String totalOfertas, String precio, String fechaInicio, String fechaFin) {
        this.nombreOferta = nombreOferta;
        this.cantidadMaxima = cantidadMaxima;
        this.totalOfertas = totalOfertas;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public String getCantidadMaxima() {
        return cantidadMaxima;
    }

    public void setCantidadMaxima(String cantidadMaxima) {
        this.cantidadMaxima = cantidadMaxima;
    }

    public String getTotalOfertas() {
        return totalOfertas;
    }

    public void setTotalOfertas(String totalOfertas) {
        this.totalOfertas = totalOfertas;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
}