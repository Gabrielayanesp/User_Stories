package com.minitienda.minitienda.ui;

import com.minitienda.minitienda.model.*;
import com.minitienda.minitienda.service.InventarioService;
import javax.swing.JOptionPane;

public class MainApp {
    public static void main(String[] args) {
        InventarioService inventario = new InventarioService();

        // Productos iniciales
        inventario.agregarProducto(new Alimento("Arroz", 5000, 10, "Grano"));
        inventario.agregarProducto(new Alimento("Leche", 4500, 15, "Lácteo"));
        inventario.agregarProducto(new Electrodomestico("Licuadora", 120000, 5, 500));
        inventario.agregarProducto(new Electrodomestico("Nevera", 1800000, 3, 800));

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog("""
                === MENÚ PRINCIPAL ===
                1. Agregar producto
                2. Listar productos
                3. Buscar producto
                4. Comprar producto
                5. Mostrar producto más caro y más barato
                6. Salir
                Elige una opción:
                """);

            if (opcion == null) break;

            switch (opcion) {
                case "1" -> {
                    String tipo = JOptionPane.showInputDialog("¿Qué tipo de producto deseas agregar? (alimento / electrodomestico)");
                    if (tipo == null || tipo.isBlank()) {
                        JOptionPane.showMessageDialog(null, "Debes escribir un tipo de producto.");
                        break;
                    }

                    String nombre = JOptionPane.showInputDialog("Nombre del producto:");
                    if (nombre == null || nombre.isBlank()) {
                        JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío.");
                        break;
                    }

                    double precio = 0;
                    try {
                        precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
                        if (precio <= 0) {
                            JOptionPane.showMessageDialog(null, "El precio debe ser un número positivo.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Ingresa un número válido para el precio.");
                        break;
                    }

                    int cantidad = 0;
                    try {
                        cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
                        if (cantidad <= 0) {
                            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número positivo.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Ingresa un número válido para la cantidad.");
                        break;
                    }

                    if (tipo.equalsIgnoreCase("alimento")) {
                        String tipoAl = JOptionPane.showInputDialog("Tipo de alimento:");
                        if (tipoAl == null || tipoAl.isBlank()) {
                            JOptionPane.showMessageDialog(null, "El tipo no puede estar vacío.");
                            break;
                        }
                        inventario.agregarProducto(new Alimento(nombre, precio, cantidad, tipoAl));

                    } else if (tipo.equalsIgnoreCase("electrodomestico")) {
                        int potencia = 0;
                        try {
                            potencia = Integer.parseInt(JOptionPane.showInputDialog("Potencia en watts:"));
                            if (potencia <= 0) {
                                JOptionPane.showMessageDialog(null, "La potencia debe ser positiva.");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Ingresa un número válido para la potencia.");
                            break;
                        }
                        inventario.agregarProducto(new Electrodomestico(nombre, precio, cantidad, potencia));
                    } else {
                        JOptionPane.showMessageDialog(null, "Tipo no válido. Escribe 'alimento' o 'electrodomestico'.");
                    }
                }

                case "2" -> inventario.listarProductos();

                case "3" -> {
                    String nombre = JOptionPane.showInputDialog("Nombre a buscar:");
                    Product encontrado = inventario.buscarProducto(nombre);
                    if (encontrado != null)
                        JOptionPane.showMessageDialog(null, "Producto encontrado:\n" + encontrado);
                    else
                        JOptionPane.showMessageDialog(null, "No se encontró el producto.");
                }

                case "4" -> {
                    String nombre = JOptionPane.showInputDialog("Producto a comprar:");
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a comprar:"));
                    inventario.comprarProducto(nombre, cantidad);
                }

                case "5" -> {
                    Product caro = inventario.productoMasCaro();
                    Product barato = inventario.productoMasBarato();
                    if (caro != null && barato != null) {
                        JOptionPane.showMessageDialog(null,
                            "Más caro: " + caro.getNombre() + " - $" + caro.getPrecio() +
                            "\nMás barato: " + barato.getNombre() + " - $" + barato.getPrecio());
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay productos en el inventario.");
                    }
                }

                case "6" -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");

                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }

        } while (!"6".equals(opcion));
    }
}
