package com.example.lab9_grupomagenta.Beans;

public class BZombie extends BHumano {
    private int idZombie;
    private int victimas;
    private String tiempoInfectado;
    private String tipoZombie;
    private String varianteVirus;

    public int getIdZombie() {
        return idZombie;
    }

    public void setIdZombie(int idZombie) {
        this.idZombie = idZombie;
    }

    public int getVictimas() {
        return victimas;
    }

    public void setVictimas(int victimas) {
        this.victimas = victimas;
    }

    public String getTipoZombie() {
        return tipoZombie;
    }

    public void setTipoZombie(String tipoZombie) {
        this.tipoZombie = tipoZombie;
    }

    public String getVarianteVirus() {
        return varianteVirus;
    }

    public void setVarianteVirus(String varianteVirus) {
        this.varianteVirus = varianteVirus;
    }

    public String getTiempoInfectado() {
        return tiempoInfectado;
    }

    public void setTiempoInfectado(String tiempoInfectado) {
        this.tiempoInfectado = tiempoInfectado;
    }
}
