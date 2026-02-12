package com.betacom.car.models;

public class Categoria {

    private Integer idCategoria;
    private String categoria;

    // Costruttore
    public Categoria(Integer idCategoria, String categoria) {
        this.idCategoria = idCategoria;
        this.categoria = categoria;
    }

    // Getter e Setter
    public Integer getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("Categoria [ID: %d | Categoria: %s]", idCategoria, categoria);
    }
}