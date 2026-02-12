package com.betacom.car.models;

public class Alimentazione {

    private Integer idAlimentazione;
    private String tipoAlimentazione;

    // Costruttore
    public Alimentazione(Integer idAlimentazione, String tipoAlimentazione) {
        this.idAlimentazione = idAlimentazione;
        this.tipoAlimentazione = tipoAlimentazione;
    }

    // Getter e Setter
    public Integer getIdAlimentazione() {
        return idAlimentazione;
    }
    public void setIdAlimentazione(Integer idAlimentazione) {
        this.idAlimentazione = idAlimentazione;
    }

    public String getTipoAlimentazione() {
        return tipoAlimentazione;
    }
    public void setTipoAlimentazione(String tipoAlimentazione) {
        this.tipoAlimentazione = tipoAlimentazione;
    }

    @Override
    public String toString() {
        return String.format("Alimentazione [ID: %d | Tipo: %s]", idAlimentazione, tipoAlimentazione);
    }
}