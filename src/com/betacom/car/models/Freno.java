package com.betacom.car.models;

public class Freno {

    private Integer idFreno;
    private String freno;

    // Costruttore
    public Freno(Integer idFreno, String freno) {
        this.idFreno = idFreno;
        this.freno = freno;
    }

    // Getter e Setter
    public Integer getIdFreno() {
        return idFreno;
    }
    public void setIdFreno(Integer idFreno) {
        this.idFreno = idFreno;
    }

    public String getFreno() {
        return freno;
    }
    public void setFreno(String freno) {
        this.freno = freno;
    }

    @Override
    public String toString() {
        return String.format("Freno [ID: %d | Tipo: %s]", idFreno, freno);
    }
}