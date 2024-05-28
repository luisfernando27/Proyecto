package com.example.proyecto;

public class ListaElementosProductosClientes {
    public String nombreProducto;
    public String empresa;
    public String precio;

    public ListaElementosProductosClientes(String nombreProducto, String empresa, String precio) {
        this.nombreProducto = nombreProducto;
        this.empresa = empresa;
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
