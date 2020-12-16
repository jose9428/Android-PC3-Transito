package com.proyecto.practicacalificada;

public class Vehiculo {
    String nroPlaca;
    String nombre;
    String color;
    String modelo;

    @Override
    public String toString() {
        return "Vehiculo{" +
                "nroPlaca='" + nroPlaca + '\'' +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }

    public String getNroPlaca() {
        return nroPlaca;
    }

    public void setNroPlaca(String nroPlaca) {
        this.nroPlaca = nroPlaca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
