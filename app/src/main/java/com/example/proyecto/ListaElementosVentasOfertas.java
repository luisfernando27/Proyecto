package com.example.proyecto;

public class ListaElementosVentasOfertas {
    public String nombreOferta;
    public String idVo;
    public String totalO;
    public String precio;
    public String restantes;

    public ListaElementosVentasOfertas(String nombreOferta, String idVO, String totalO, String precio, String restantes) {
        this.nombreOferta = nombreOferta;
        this.idVo = idVO;
        this.totalO = totalO;
        this.precio = precio;
        this.restantes = restantes;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public String getIdVo() {
        return idVo;
    }

    public String getTotalO() {
        return totalO;
    }


    public String getPrecio() {
        return precio;
    }


    public String getRestantes() {
        return restantes;
    }

}
