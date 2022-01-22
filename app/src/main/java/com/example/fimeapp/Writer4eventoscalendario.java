package com.example.fimeapp;

public class Writer4eventoscalendario {

    public Writer4eventoscalendario(){
    }

    String nombre,desc,date,color;

    public Writer4eventoscalendario(String nombre, String desc, String date, String color) {
        this.nombre = nombre;
        this.desc = desc;
        this.date = date;
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }



}
