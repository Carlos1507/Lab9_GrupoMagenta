package com.example.lab9_grupomagenta.Beans;

public class BVirus {
    private int idVirus;
    private String nombre;
    private int casosEncontrados;
    private BVariante bVariante;


    public int getIdVirus() {
        return idVirus;
    }

    public void setIdVirus(int idVirus) {
        this.idVirus = idVirus;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCasosEncontrados() {
        return casosEncontrados;
    }

    public void setCasosEncontrados(int casosEncontrados) {
        this.casosEncontrados = casosEncontrados;
    }

    public BVariante getbVariante() {
        return bVariante;
    }

    public void setbVariante(BVariante bVariante) {
        this.bVariante = bVariante;
    }
}
