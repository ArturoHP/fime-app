package com.example.fimeapp;

//A&E

public class CarritoFB {

    public CarritoFB(){
    }

    String nombreproducto;
    int costodelproducto;
    String cantidadproducto;

    public CarritoFB(String nombreproducto, int costodelproducto, String cantidadproducto) {
        this.nombreproducto = nombreproducto;
        this.costodelproducto = costodelproducto;
        this.cantidadproducto = cantidadproducto;
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
