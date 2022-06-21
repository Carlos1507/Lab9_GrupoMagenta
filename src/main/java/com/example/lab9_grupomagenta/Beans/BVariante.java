package com.example.lab9_grupomagenta.Beans;

public class BVariante {
    private int idVirus;
    private int idVarianteVirus;
    private String nombre;
    private double gradoInfectividad;

    public int getIdVirus() {
        return idVirus;
    }

    public void setIdVirus(int idVirus) {
        this.idVirus = idVirus;
    }

    public int getIdVarianteVirus() {
        return idVarianteVirus;
    }

    public void setIdVarianteVirus(int idVarianteVirus) {
        this.idVarianteVirus = idVarianteVirus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGradoInfectividad() {
        return gradoInfectividad;
    }

    public void setGradoInfectividad(double gradoInfectividad) {
        this.gradoInfectividad = gradoInfectividad;
    }
}
