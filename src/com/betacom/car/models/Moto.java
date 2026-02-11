package com.betacom.car.models;

import java.time.LocalDate;
/*
public class Moto extends Veicoli{
	
	public Moto(int intValue, String string, int intValue2, String string2, String string3, String string4,
			String string5, LocalDate annoProduzione2, String modello2) {
		super(intValue, string, intValue2, string2, string3, string4, string5, annoProduzione2, modello2);
		// TODO Auto-generated constructor stub
	}

	private String targa;  // deve essere univoca
	private Integer cc;
	
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}
	public Integer getCc() {
		return cc;
	}
	public void setCc(Integer cc) {
		this.cc = cc;
	}

	@Override
	public String toString() {
		return "Moto [ Id=" + getId() + ", TipoVeicolo=" + getId_TipoVeicolo()
				+ ", NumeroRuote=" + getNumeroRuote() + ", TipoAlimentazione=" + getId_TipoAlimentazione()
				+ ", Categoria=" + getId_Categoria() + ", Colore=" + getId_Colore() + ", Marca=" + getId_Marca()
				+ ", AnnoProduzione=" + getAnnoProduzione() + ", Modello=" + getModello() 
				+ ", targa=" + targa + ", cc=" + cc  + "]";
	}
}
*/


import java.time.LocalDate;

public class Moto extends Veicoli {

    private Integer cilindrata;
    private String targa;

    // Costruttore
    public Moto(Integer id, String tipoVeicolo, Integer numeroRuote, String alimentazione,
                String categoria, String colore, String marca, Integer annoProduzione, String modello,
                Integer cilindrata, String targa) {
        super(id, tipoVeicolo, numeroRuote, alimentazione, categoria, colore, marca, annoProduzione, modello);
        this.cilindrata = cilindrata;
        this.targa = targa;
    }

    // Getter e Setter
    public Integer getCilindrata() {
        return cilindrata;
    }
    public void setCilindrata(Integer cilindrata) {
        this.cilindrata = cilindrata;
    }

    public String getTarga() {
        return targa;
    }
    public void setTarga(String targa) {
        this.targa = targa;
    }

    @Override
    public String toString() {
        return super.toString() +
               String.format(" | Cilindrata: %d | Targa: %s", cilindrata, targa);
    }
}
