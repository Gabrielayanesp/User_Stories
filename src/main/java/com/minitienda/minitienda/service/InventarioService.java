package com.minitienda.minitienda.service;

import com.minitienda.minitienda.model.Product;
import java.util.ArrayList;
import java.util.List;

public class InventarioService {
    private List<Product> productos = new ArrayList<>();

    public void agregarProducto(Product producto) {
        // Evita duplicados
        for (Product p : productos) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                System.out.println("El producto ya existe en el inventario.");
                return;
            }
        }
        productos.add(producto);
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            System.out.println("Inventario:");
            for (Product p : productos) {
                System.out.println(p);
            }
        }
    }

    public Product buscarProducto(String nombre) {
        for (Product p : productos) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                return p;
            }
        }
        return null;
    }

    public void comprarProducto(String nombre, int cantidad) {
        Product producto = buscarProducto(nombre);
        if (producto != null && producto.getCantidad() >= cantidad) {
            producto.setCantidad(producto.getCantidad() - cantidad);
            System.out.println("Compra realizada con éxito.");
        } else {
            System.out.println("No se pudo completar la compra. Verifique el nombre o la cantidad.");
        }
    }

    public Product productoMasCaro() {
        if (productos.isEmpty()) return null;
        Product max = productos.get(0);
        for (Product p : productos) {
            if (p.getPrecio() > max.getPrecio()) {
                max = p;
            }
        }
        return max;
    }

    public Product productoMasBarato() {
        if (productos.isEmpty()) return null;
        Product min = productos.get(0);
        for (Product p : productos) {
            if (p.getPrecio() < min.getPrecio()) {
                min = p;
            }
        }
        return min;
    }
}
