package com.betacom.car.models;

import java.time.LocalDate;

public class Bici extends Veicoli {

    private Integer numeroMarce;
    private String tipoFreno;        // meccanico, a disco, ecc.
    private String tipoSospensione;  // senza, mono, bi, ecc.
    private Boolean piegevole;
    
    //per la scrittura su db
    private Integer idFreno;
    private Integer idSospensione;


    // Costruttore
    public Bici(Integer id, String tipoVeicolo, Integer numeroRuote, String alimentazione,
                      String categoria, String colore, String marca, Integer annoProduzione, String modello,
                      Integer numeroMarce, String tipoFreno, String tipoSospensione, Boolean piegevole) {
        super(id, tipoVeicolo, numeroRuote, alimentazione, categoria, colore, marca, annoProduzione, modello);
        this.numeroMarce = numeroMarce;
        this.tipoFreno = tipoFreno;
        this.tipoSospensione = tipoSospensione;
        this.piegevole = piegevole;
    }

    // Getter e Setter
    public Integer getNumeroMarce() {
        return numeroMarce;
    }
    public void setNumeroMarce(Integer numeroMarce) {
        this.numeroMarce = numeroMarce;
    }

    public String getTipoFreno() {
        return tipoFreno;
    }
    public void setTipoFreno(String tipoFreno) {
        this.tipoFreno = tipoFreno;
    }

    public String getTipoSospensione() {
        return tipoSospensione;
    }
    public void setTipoSospensione(String tipoSospensione) {
        this.tipoSospensione = tipoSospensione;
    }

    public Boolean getPiegevole() {
        return piegevole;
    }
    public void setPiegevole(Boolean piegevole) {
        this.piegevole = piegevole;
    }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Marce: %d | Freno: %s | Sospensione: %s | Pieghevole: %s",
                             numeroMarce, tipoFreno, tipoSospensione, piegevole ? "Sì" : "No");
    }

	public Integer getIdFreno() {
		return idFreno;
	}

	public void setIdFreno(Integer idFreno) {
		this.idFreno = idFreno;
	}

	public Integer getIdSospensione() {
		return idSospensione;
	}

	public void setIdSospensione(Integer idSospensione) {
		this.idSospensione = idSospensione;
	}
    
	public Bici(Integer id, String tipoVeicolo, Integer numeroRuote, String alimentazione,
            String categoria, String colore, String marca, Integer annoProduzione, String modello,
            Integer numeroMarce, Integer idFreno, Integer idSospensione, String tipoFreno,
            String tipoSospensione, Boolean piegevole) {
			super(id, tipoVeicolo, numeroRuote, alimentazione, categoria, colore, marca, annoProduzione, modello);
		    this.numeroMarce = numeroMarce;
		    this.idFreno = idFreno;
		    this.idSospensione = idSospensione;
		    this.tipoFreno = tipoFreno;
		    this.tipoSospensione = tipoSospensione;
		    this.piegevole = piegevole;
	}

	public Bici() {
		super();
	}
	
	

}
