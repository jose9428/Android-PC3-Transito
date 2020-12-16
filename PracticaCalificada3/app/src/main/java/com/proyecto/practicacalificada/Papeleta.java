package com.proyecto.practicacalificada;

public class Papeleta {
    String nroPap;
    String infraccion;
    double multa;
    String fecha;

    @Override
    public String toString() {
        return "Papeleta{" +
                "nroPap='" + nroPap + '\'' +
                ", infraccion='" + infraccion + '\'' +
                ", multa=" + multa +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    public String getNroPap() {
        return nroPap;
    }

    public void setNroPap(String nroPap) {
        this.nroPap = nroPap;
    }

    public String getInfraccion() {
        return infraccion;
    }

    public void setInfraccion(String infraccion) {
        this.infraccion = infraccion;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
