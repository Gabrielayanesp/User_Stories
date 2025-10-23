package com.minitienda.minitienda.model;

public abstract class Product {
    protected String nombre;
    protected double precio;
    protected int cantidad;

    public Product(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public abstract String getDescripcion();

    @Override
    public String toString() {
        return nombre + " - $" + precio + " - " + cantidad + " unidades - " + getDescripcion();
    }
}
