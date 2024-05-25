package com.example.proyecto;

public class ListaElementosOfertasEmpresa {
    public String nombreOferta;
    public String descripcionOferta;
    public String precioOferta;
    public String numTotalOferta;

    public ListaElementosOfertasEmpresa(String nombreOferta, String descripcionOferta, String precioOferta, String numTotalOferta) {
        this.nombreOferta = nombreOferta;
        this.descripcionOferta = descripcionOferta;
        this.precioOferta = precioOferta;
        this.numTotalOferta = numTotalOferta;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public String getDescripcionOferta() {
        return descripcionOferta;
    }

    public void setDescripcionOferta(String descripcionOferta) {
        this.descripcionOferta = descripcionOferta;
    }

    public String getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(String precioOferta) {
        this.precioOferta = precioOferta;
    }

    public String getNumTotalOferta() {
        return numTotalOferta;
    }

    public void setNumTotalOferta(String numTotalOferta) {
        this.numTotalOferta = numTotalOferta;
    }
}
