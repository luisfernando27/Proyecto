package com.example.proyecto;

public class ListaElementosProductosEmpresa {
    public String nombreProducto;
    public String codigoProducto;
    public String precioProducto;

    public ListaElementosProductosEmpresa(String nombreProducto, String codigoProducto, String precioProducto) {
        this.nombreProducto = nombreProducto;
        this.codigoProducto = codigoProducto;
        this.precioProducto = precioProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }
}
