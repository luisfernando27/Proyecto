package com.example.proyecto;

public class ListaElementosClientes {
    private String id;
    private String nombreOferta;
    private String empresa;
    private String cantidadOfertaPorUsuario;
    private String precio;
    private String fechaI;
    private String fechaF;

    public ListaElementosClientes(String id,String nombreOferta, String empresa, String cantidadOfertaPorUsuario, String precio, String fechaI, String fechaF) {
        this.id = id;
        this.nombreOferta = nombreOferta;
        this.empresa = empresa;
        this.cantidadOfertaPorUsuario = cantidadOfertaPorUsuario;
        this.precio = precio;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
    }

    public String getID() {
        return id;
    }
    public String getNombreOferta() {
        return nombreOferta;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getCantidadOfertaPorUsuario() {
        return cantidadOfertaPorUsuario;
    }

    public String getPrecio() {
        return precio;
    }

    public String getFechaI() { return fechaI; }

    public String getFechaF() {
        return fechaF;
    }
}
