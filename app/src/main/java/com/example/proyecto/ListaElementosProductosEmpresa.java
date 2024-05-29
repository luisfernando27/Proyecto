package com.example.proyecto;

public class ListaElementosProductosEmpresa {
    public String idP;

    public String nombreProducto;
    public String cantidad;
    public String precioProducto;

    public ListaElementosProductosEmpresa(String idP, String nombreProducto, String cantidad, String precioProducto) {
        this.idP = idP;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioProducto = precioProducto;
    }

    public String getIdP() {
        return idP;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getCodigoProducto() {
        return cantidad;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }
}
