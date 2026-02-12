package com.betacom.car.models;

public class Sospensione {

    private Integer idSospensione;
    private String sospensione;

    // Costruttore
    public Sospensione(Integer idSospensione, String sospensione) {
        this.idSospensione = idSospensione;
        this.sospensione = sospensione;
    }

    // Getter e Setter
    public Integer getIdSospensione() {
        return idSospensione;
    }
    public void setIdSospensione(Integer idSospensione) {
        this.idSospensione = idSospensione;
    }

    public String getSospensione() {
        return sospensione;
    }
    public void setSospensione(String sospensione) {
        this.sospensione = sospensione;
    }

    @Override
    public String toString() {
        return String.format("Sospensione [ID: %d | Tipo: %s]", idSospensione, sospensione);
    }
}