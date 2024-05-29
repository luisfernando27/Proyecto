package com.example.proyecto;

public class ListaElementosOfertasEmpresa {
    public String idO;

    public String nombreOferta;
    public String precioOferta;
    public String totalOfertas;

    public String maximaCantidad;
    public String fechaInicio;
    public String fechaFin;

    public ListaElementosOfertasEmpresa(String idO, String nombreOferta, String precioOferta, String totalOfertas, String maximaCantidad, String fechaInicio, String fechaFin) {
        this.idO = idO;
        this.nombreOferta = nombreOferta;
        this.precioOferta = precioOferta;
        this.totalOfertas = totalOfertas;
        this.maximaCantidad = maximaCantidad;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getIDO() {
        return idO;
    }
    public String getNombreOferta() {
        return nombreOferta;
    }

    public String getPrecioOferta() {
        return precioOferta;
    }


    public String getTotalOfertas() {
        return totalOfertas;
    }


    public String getMaximaCantidad() {
        return maximaCantidad;
    }


    public String getFechaInicio() {
        return fechaInicio;
    }


    public String getFechaFin() {
        return fechaFin;
    }

}