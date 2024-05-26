package com.example.proyecto;

public class ListaElementosClientes {
    private String id;
    private String nombreOferta;
    private String empresa;
    private String cantidadOfertaPorUsuario;
    private String precio;

    public ListaElementosClientes(String id,String nombreOferta, String empresa, String cantidadOfertaPorUsuario, String precio) {
        this.id = id;
        this.nombreOferta = nombreOferta;
        this.empresa = empresa;
        this.cantidadOfertaPorUsuario = cantidadOfertaPorUsuario;
        this.precio = precio;
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
}
