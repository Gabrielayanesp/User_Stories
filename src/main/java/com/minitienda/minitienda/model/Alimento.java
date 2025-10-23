package com.minitienda.minitienda.model;

public class Alimento extends Product {
    private String tipo;

    public Alimento(String nombre, double precio, int cantidad, String tipo) {
        super(nombre, precio, cantidad);
        this.tipo = tipo;
    }

    @Override
    public String getDescripcion() {
        return "Alimento tipo " + tipo;
    }
}
