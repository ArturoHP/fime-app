package com.example.fimeapp;

//A&E

public class Writer4carrito {

    String nombreproducto;
    int costodelproducto;
    String cantidadproducto;

    public Writer4carrito(String Nombreproducto, String Cantidadproducto, int Costodelproducto){
        nombreproducto = Nombreproducto;
        costodelproducto = Costodelproducto;
        cantidadproducto = Cantidadproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public int getCostodelproducto() {
        return costodelproducto;
    }

    public void setCostodelproducto(int costodelproducto) {
        this.costodelproducto = costodelproducto;
    }

    public String getCantidadproducto() {
        return cantidadproducto;
    }

    public void setCantidadproducto(String cantidadproducto) {
        this.cantidadproducto = cantidadproducto;
    }





}
