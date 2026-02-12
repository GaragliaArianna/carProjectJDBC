package com.betacom.car.models;

import java.time.LocalDate;

public class Macchina extends Veicoli {

    private Integer porte;    // numero di porte
    private String targa;     // deve essere univoca
    private Integer cilindrata; // cc

    // Costruttore
    public Macchina(Integer id, String tipoVeicolo, Integer numeroRuote, String alimentazione,
                    String categoria, String colore, String marca, Integer annoProduzione, String modello,
                    Integer porte, String targa, Integer cilindrata) {
        super(id, tipoVeicolo, numeroRuote, alimentazione, categoria, colore, marca, annoProduzione, modello);
        this.porte = porte;
        this.targa = targa;
        this.cilindrata = cilindrata;
    }

    // Getter e Setter
    public Integer getPorte() {
        return porte;
    }
    public void setPorte(Integer porte) {
        this.porte = porte;
    }

    public String getTarga() {
        return targa;
    }
    public void setTarga(String targa) {
        this.targa = targa;
    }

    public Integer getCilindrata() {
        return cilindrata;
    }
    public void setCilindrata(Integer cilindrata) {
        this.cilindrata = cilindrata;
    }

    @Override
    public String toString() {
        return super.toString() + 
               String.format(" | Porte: %d | Targa: %s | Cilindrata: %d", porte, targa, cilindrata);
    }

	public Macchina() {
		super();
	}
    
    
}

