package com.betacom.car.models;

public class Veicoli {
	
	private Integer id;               // id_veicolo (può restare per chiave tecnica)
    
    //per lettura e scrittura
    private Integer annoProduzione;
    private Integer numeroRuote;
    
    //per scrittura dati su db
    private Integer idTipoVeicolo;
	private Integer idAlimentazione;
    private Integer idCategoria;
    private Integer idColore;
    private Integer idMarca;
    
    //per lettura dati
    private String tipoVeicolo;       // Macchina, Moto, Bici
    private String alimentazione;     // benzina, diesel, elettrica, ecc.
    private String categoria;         // strada, fuoristrada, SUV, ecc.
    private String colore;
    private String marca;
   
    private String modello;
    
    

    public Veicoli() {
		super();
	}

	public Veicoli(Integer id, Integer annoProduzione, Integer numeroRuote, Integer idTipoVeicolo,
			Integer idAlimentazione, Integer idCategoria, Integer idColore, Integer idMarca) {
		super();
		this.id = id;
		this.annoProduzione = annoProduzione;
		this.numeroRuote = numeroRuote;
		this.idTipoVeicolo = idTipoVeicolo;
		this.idAlimentazione = idAlimentazione;
		this.idCategoria = idCategoria;
		this.idColore = idColore;
		this.idMarca = idMarca;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAnnoProduzione() {
		return annoProduzione;
	}

	public void setAnnoProduzione(Integer annoProduzione) {
		this.annoProduzione = annoProduzione;
	}

	public Integer getNumeroRuote() {
		return numeroRuote;
	}

	public void setNumeroRuote(Integer numeroRuote) {
		this.numeroRuote = numeroRuote;
	}

	public Integer getIdTipoVeicolo() {
		return idTipoVeicolo;
	}

	public void setIdTipoVeicolo(Integer idTipoVeicolo) {
		this.idTipoVeicolo = idTipoVeicolo;
	}

	public Integer getIdAlimentazione() {
		return idAlimentazione;
	}

	public void setIdAlimentazione(Integer idAlimentazione) {
		this.idAlimentazione = idAlimentazione;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdColore() {
		return idColore;
	}

	public void setIdColore(Integer idColore) {
		this.idColore = idColore;
	}

	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public String getTipoVeicolo() {
		return tipoVeicolo;
	}

	public void setTipoVeicolo(String tipoVeicolo) {
		this.tipoVeicolo = tipoVeicolo;
	}

	public String getAlimentazione() {
		return alimentazione;
	}

	public void setAlimentazione(String alimentazione) {
		this.alimentazione = alimentazione;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}



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


	@Override
	public String toString() {
	    return String.format(
	        "Veicolo [ID=%d] %s | Tipo: %s | Marca: %s | Alimentazione: %s | Categoria: %s | Colore: %s | Anno: %d | Ruote: %d",
	        id, modello, tipoVeicolo, marca, alimentazione, categoria, colore, annoProduzione, numeroRuote
	    );
	}

	public Veicoli(Integer id, Integer annoProduzione, Integer numeroRuote,
            Integer idTipoVeicolo, Integer idAlimentazione, Integer idCategoria,
            Integer idColore, Integer idMarca,
            String tipoVeicolo, String alimentazione, String categoria,
            String colore, String marca, String modello) {
		 this.id = id;
		 this.annoProduzione = annoProduzione;
		 this.numeroRuote = numeroRuote;
		 this.idTipoVeicolo = idTipoVeicolo;
		 this.idAlimentazione = idAlimentazione;
		 this.idCategoria = idCategoria;
		 this.idColore = idColore;
		 this.idMarca = idMarca;
		 this.tipoVeicolo = tipoVeicolo;
		 this.alimentazione = alimentazione;
		 this.categoria = categoria;
		 this.colore = colore;
		 this.marca = marca;
		 this.modello = modello;
}



}
