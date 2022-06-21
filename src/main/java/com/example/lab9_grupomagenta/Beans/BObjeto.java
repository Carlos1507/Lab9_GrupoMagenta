package com.example.lab9_grupomagenta.Beans;

public class BObjeto {
    private int idObjeto;
    private String nombre;
    private double masa;
    private boolean vacuna;
    private int cantidad;

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMasa() {
        return masa;
    }

    public void setMasa(double masa) {
        this.masa = masa;
    }

    public boolean isVacuna() {
        return vacuna;
    }

    public void setVacuna(boolean vacuna) {
        this.vacuna = vacuna;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
