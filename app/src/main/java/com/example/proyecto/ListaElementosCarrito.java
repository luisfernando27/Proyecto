package com.example.proyecto;

public class ListaElementosCarrito {
    public String nombreVenta;
    public String empresa;
    public String cantidad;
    public String total;

    public ListaElementosCarrito(String nombreVenta, String empresa, String cantidad, String total) {
        this.nombreVenta = nombreVenta;
        this.empresa = empresa;
        this.cantidad = cantidad;
        this.total = total;
    }

    public String getNombreVenta() {
        return nombreVenta;
    }

    public void setNombreVenta(String nombreVenta) {
        this.nombreVenta = nombreVenta;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
