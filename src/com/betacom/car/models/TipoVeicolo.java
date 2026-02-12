package com.betacom.car.models;

public class TipoVeicolo {

    private Integer idTipoVeicolo;
    private String veicolo;

    // Costruttore
    public TipoVeicolo(Integer idTipoVeicolo, String veicolo) {
        this.idTipoVeicolo = idTipoVeicolo;
        this.veicolo = veicolo;
    }

    // Getter e Setter
    public Integer getIdTipoVeicolo() {
        return idTipoVeicolo;
    }
    public void setIdTipoVeicolo(Integer idTipoVeicolo) {
        this.idTipoVeicolo = idTipoVeicolo;
    }

    public String getVeicolo() {
        return veicolo;
    }
    public void setVeicolo(String veicolo) {
        this.veicolo = veicolo;
    }

    @Override
    public String toString() {
        return String.format("TipoVeicolo [ID: %d | Tipo: %s]", idTipoVeicolo, veicolo);
    }
}
