package com.example.fimeapp;

//A&E
public class ComidasFB {

    public ComidasFB(){}

    String imagencomida;
    String nombrecomida;
    String descripcioncomida;
    String preciocomida;
    int precioenint;


    public ComidasFB(String imagencomida, String nombrecomida, String descripcioncomida, String preciocomida,int precioenint) {
        this.imagencomida = imagencomida;
        this.nombrecomida = nombrecomida;
        this.descripcioncomida = descripcioncomida;
        this.preciocomida = preciocomida;
        this.precioenint = precioenint;
    }

    public String getImagencomida() {
        return imagencomida;
    }

    public void setImagencomida(String imagencomida) {
        this.imagencomida = imagencomida;
    }

    public String getNombrecomida() {
        return nombrecomida;
    }

    public void setNombrecomida(String nombrecomida) {
        this.nombrecomida = nombrecomida;
    }

    public String getDescripcioncomida() {
        return descripcioncomida;
    }

    public void setDescripcioncomida(String descripcioncomida) {
        this.descripcioncomida = descripcioncomida;
    }

    public String getPreciocomida() {
        return preciocomida;
    }

    public void setPreciocomida(String preciocomida) {
        this.preciocomida = preciocomida;
    }

    public int getPrecioenint() {
        return precioenint;
    }

    public void setPrecioenint(int precioenint) {
        this.precioenint = precioenint;
    }
}
