package com.betacom.car.models;

public class Marca {

    private Integer idMarca;
    private String marca;

    // Costruttore
    public Marca(Integer idMarca, String marca) {
        this.idMarca = idMarca;
        this.marca = marca;
    }
    
    

    public Marca() {
		
	}



	// Getter e Setter
    public Integer getIdMarca() {
        return idMarca;
    }
    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return String.format("Marca [ID: %d | Marca: %s]", idMarca, marca);
    }
}