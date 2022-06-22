package com.example.lab9_grupomagenta.Beans;

public class BObjeto {
    private int idObjeto;
    private String nombre;
    private double masa;
    private int vacuna;
    private int cantidad;
    private int idVacuna;

    public int getIdVacuna() {
        return idVacuna;
    }

    public void setIdVacuna(int idVacuna) {
        this.idVacuna = idVacuna;
    }

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

    public int getVacuna() {
        return vacuna;
    }

    public void setVacuna(int vacuna) {
        this.vacuna = vacuna;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
