package com.minitienda.minitienda.model;

public class Electrodomestico extends Product {
    private int potencia;

    public Electrodomestico(String nombre, double precio, int cantidad, int potencia) {
        super(nombre, precio, cantidad);
        this.potencia = potencia;
    }

    @Override
    public String getDescripcion() {
        return "Electrodoméstico de " + potencia + "W";
    }
}
