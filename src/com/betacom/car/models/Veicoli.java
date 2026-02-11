package com.betacom.car.models;

public class Veicoli {

    private Integer id;               // id_veicolo (può restare per chiave tecnica)
    private String tipoVeicolo;       // Macchina, Moto, Bici
    private Integer numeroRuote;
    private String alimentazione;     // benzina, diesel, elettrica, ecc.
    private String categoria;         // strada, fuoristrada, SUV, ecc.
    private String colore;
    private String marca;
    private Integer annoProduzione;
    private String modello;

    // Costruttore
    public Veicoli(Integer id, String tipoVeicolo, Integer numeroRuote, String alimentazione,
                   String categoria, String colore, String marca, Integer annoProduzione, String modello) {
        this.id = id;
        this.tipoVeicolo = tipoVeicolo;
        this.numeroRuote = numeroRuote;
        this.alimentazione = alimentazione;
        this.categoria = categoria;
        this.colore = colore;
        this.marca = marca;
        this.annoProduzione = annoProduzione;
        this.modello = modello;
    }

    // Getter e Setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTipoVeicolo() { return tipoVeicolo; }
    public void setTipoVeicolo(String tipoVeicolo) { this.tipoVeicolo = tipoVeicolo; }
    public Integer getNumeroRuote() { return numeroRuote; }
    public void setNumeroRuote(Integer numeroRuote) { this.numeroRuote = numeroRuote; }
    public String getAlimentazione() { return alimentazione; }
    public void setAlimentazione(String alimentazione) { this.alimentazione = alimentazione; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getColore() { return colore; }
    public void setColore(String colore) { this.colore = colore; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public Integer getAnnoProduzione() { return annoProduzione; }
    public void setAnnoProduzione(Integer annoProduzione) { this.annoProduzione = annoProduzione; }
    public String getModello() { return modello; }
    public void setModello(String modello) { this.modello = modello; }

    @Override
    public String toString() {
        return String.format(
            "Veicolo [ID=%d] %s | Tipo: %s | Marca: %s | Alimentazione: %s | Categoria: %s | Colore: %s | Anno: %d | Ruote: %d",
            id, modello, tipoVeicolo, marca, alimentazione, categoria, colore, annoProduzione, numeroRuote
        );
    }
}
