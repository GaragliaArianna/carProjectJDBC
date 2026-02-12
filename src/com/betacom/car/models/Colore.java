package com.betacom.car.models;

public class Colore {

    private Integer idColore;
    private String colore;

    // Costruttore
    public Colore(Integer idColore, String colore) {
        this.idColore = idColore;
        this.colore = colore;
    }

    // Getter e Setter
    public Integer getIdColore() {
        return idColore;
    }
    public void setIdColore(Integer idColore) {
        this.idColore = idColore;
    }

    public String getColore() {
        return colore;
    }
    public void setColore(String colore) {
        this.colore = colore;
    }

    @Override
    public String toString() {
        return String.format("Colore [ID: %d | Colore: %s]", idColore, colore);
    }
}