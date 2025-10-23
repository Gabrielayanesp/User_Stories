package com.minitienda.minitienda.ui;

import com.minitienda.minitienda.model.*;
import com.minitienda.minitienda.service.InventarioService;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        InventarioService service = new InventarioService();
        String opcion;

        do {
            opcion = JOptionPane.showInputDialog("""
                    === MINI TIENDA WEEK 3 ===
                    1. Agregar producto
                    2. Listar inventario
                    3. Comprar producto
                    4. Ver ventas registradas
                    5. Generar reporte de ventas
                    6. Salir
                    """);

            if (opcion == null) break;

            switch (opcion) {
                case "1" -> {
                    String tipo = JOptionPane.showInputDialog("Tipo de producto (alimento/electrodomestico):");
                    String nombre = JOptionPane.showInputDialog("Nombre:");
                    double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));

                    if (tipo.equalsIgnoreCase("alimento")) {
                        String tipoAlimento = JOptionPane.showInputDialog("Tipo de alimento:");
                        service.agregarProducto(new Alimento(nombre, precio, cantidad, tipoAlimento));
                    } else {
                        int potencia = Integer.parseInt(JOptionPane.showInputDialog("Potencia (W):"));
                        service.agregarProducto(new Electrodomestico(nombre, precio, cantidad, potencia));
                    }
                }
                case "2" -> service.mostrarInventario();
                case "3" -> {
                    String nombre = JOptionPane.showInputDialog("Nombre del producto:");
                    int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad a comprar:"));
                    service.comprarProducto(nombre, cantidad);
                }
                case "4" -> service.mostrarVentas();
                case "5" -> service.generarReporteVentas();
                case "6" -> JOptionPane.showMessageDialog(null, "¡Gracias por usar MiniTienda!");
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (!"6".equals(opcion));
    }
}
