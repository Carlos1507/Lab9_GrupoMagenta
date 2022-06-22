package com.example.lab9_grupomagenta.Beans;

public class BSuperviviente extends BHumano{
    private int idSuperviviente;
    private double peso;
    private double fuerza;
    private BHumano pareja;
    private String numIdentificacion;
    private double pesoCargado;

    public int getIdSuperviviente() {
        return idSuperviviente;
    }

    public void setIdSuperviviente(int idSuperviviente) {
        this.idSuperviviente = idSuperviviente;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getFuerza() {
        return fuerza;
    }

    public void setFuerza(double fuerza) {
        this.fuerza = fuerza;
    }

    public BHumano getPareja() {
        return pareja;
    }

    public void setPareja(BHumano pareja) {
        this.pareja = pareja;
    }

    @Override
    public String getNumIdentificacion() {
        return numIdentificacion;
    }

    @Override
    public void setNumIdentificacion(String numIdentificacion) {
        this.numIdentificacion = numIdentificacion;
    }

    public double getPesoCargado() {
        return pesoCargado;
    }

    public void setPesoCargado(double pesoCargado) {
        this.pesoCargado = pesoCargado;
    }
}
