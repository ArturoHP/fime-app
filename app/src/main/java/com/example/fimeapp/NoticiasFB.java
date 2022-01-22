package com.example.fimeapp;

//A&E

public class NoticiasFB {

    public NoticiasFB(){}

    String fechanoticia;
    String imagennoticia;
    String encabezadonoticia;
    String descnoticia;
    String leermasnoticia;

    public NoticiasFB(String fechanoticia, String imagennoticia, String encabezadonoticia, String descnoticia, String leermasnoticia) {
        this.fechanoticia = fechanoticia;
        this.imagennoticia = imagennoticia;
        this.encabezadonoticia = encabezadonoticia;
        this.descnoticia = descnoticia;
        this.leermasnoticia = leermasnoticia;
    }

    public String getFechanoticia() {
        return fechanoticia;
    }

    public void setFechanoticia(String fechanoticia) {
        this.fechanoticia = fechanoticia;
    }

    public String getImagennoticia() {
        return imagennoticia;
    }

    public void setImagennoticia(String imagennoticia) {
        this.imagennoticia = imagennoticia;
    }

    public String getEncabezadonoticia() {
        return encabezadonoticia;
    }

    public void setEncabezadonoticia(String encabezadonoticia) {
        this.encabezadonoticia = encabezadonoticia;
    }

    public String getDescnoticia() {
        return descnoticia;
    }

    public void setDescnoticia(String descnoticia) {
        this.descnoticia = descnoticia;
    }

    public String getLeermasnoticia() {
        return leermasnoticia;
    }

    public void setLeermasnoticia(String leermasnoticia) {
        this.leermasnoticia = leermasnoticia;
    }

}
