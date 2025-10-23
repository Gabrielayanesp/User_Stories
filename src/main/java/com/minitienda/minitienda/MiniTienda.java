package com.minitienda.minitienda;

import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;



public class MiniTienda {

    private static ArrayList<String> nombres = new ArrayList<>();
    private static double[] precios = new double[0];
    private static HashMap<String, Integer> stock = new HashMap<>();
    private static double totalSession = 0.0;
    private static DecimalFormat df = new DecimalFormat("#0.00");

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            String menu = "Mini-Tienda - Semana 1\n\n"
                    + "1. Agregar producto\n"
                    + "2. Listar inventario\n"
                    + "3. Comprar producto\n"
                    + "4. Mostrar estadísticas (más barato / más caro)\n"
                    + "5. Buscar producto por nombre\n"
                    + "6. Salir (mostrar ticket final)\n\n"
                    + "Ingrese opción (1-6):";
            String opt = JOptionPane.showInputDialog(null, menu);
            if (opt == null) {
                // Cancel pressed -> confirmar salida
                int c = JOptionPane.showConfirmDialog(null, "¿Desea salir?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (c == JOptionPane.YES_OPTION) {
                    showTicketFinal();
                    break;
                } else {
                    continue;
                }
            }
            try {
                int opcion = Integer.parseInt(opt.trim());
                switch (opcion) {
                    case 1 -> agregarProducto();
                    case 2 -> listarInventario();
                    case 3 -> comprarProducto();
                    case 4 -> mostrarEstadisticas();
                    case 5 -> buscarProducto();
                    case 6 -> {
                        showTicketFinal();
                        running = false;
                    }
                    default -> JOptionPane.showMessageDialog(null, "Opción inválida");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido");
            }
        }
        System.exit(0);
    }

    // TASK 1: Añadir producto (valida duplicados y entradas)
    private static void agregarProducto() {
        String nombre = JOptionPane.showInputDialog(null, "Nombre del producto:");
        if (nombre == null) return;
        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre no puede estar vacío");
            return;
        }
        if (nombres.contains(nombre)) {
            JOptionPane.showMessageDialog(null, "Producto ya existe");
            return;
        }
        String sPrecio = JOptionPane.showInputDialog(null, "Precio (ej: 12.50):");
        if (sPrecio == null) return;
        String sStock = JOptionPane.showInputDialog(null, "Stock (número entero):");
        if (sStock == null) return;
        try {
            double precio = Double.parseDouble(sPrecio.trim());
            int stk = Integer.parseInt(sStock.trim());
            if (precio < 0 || stk < 0) {
                JOptionPane.showMessageDialog(null, "Valores no pueden ser negativos");
                return;
            }
            // agregar nombre
            nombres.add(nombre);
            // expandir precios y asignar
            precios = expandPrecios(precios, precio);
            // agregar stock
            stock.put(nombre, stk);
            JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Precio o stock inválido");
        }
    }

    // Expande el arreglo de precios y añade el nuevo precio al final
    private static double[] expandPrecios(double[] original, double nuevoPrecio) {
        double[] nuevo = new double[original.length + 1];
        System.arraycopy(original, 0, nuevo, 0, original.length);
        nuevo[nuevo.length - 1] = nuevoPrecio;
        return nuevo;
    }

    // TASK 2: Listar inventario
    private static void listarInventario() {
        if (nombres.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inventario vacío");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Inventario:\n\n");
        for (int i = 0; i < nombres.size(); i++) {
            String nom = nombres.get(i);
            double pr = (i < precios.length) ? precios[i] : 0.0;
            int stk = stock.getOrDefault(nom, 0);
            sb.append(String.format("%d. %s - $%s - Stock: %d\n", i + 1, nom, df.format(pr), stk));
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // TASK 3: Comprar producto
    private static void comprarProducto() {
        if (nombres.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inventario vacío");
            return;
        }
        String nombre = JOptionPane.showInputDialog(null, "Nombre del producto a comprar:");
        if (nombre == null) return;
        nombre = nombre.trim();
        int idx = indexOfNombre(nombre);
        if (idx == -1) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado");
            return;
        }
        String sCantidad = JOptionPane.showInputDialog(null, "Cantidad a comprar:");
        if (sCantidad == null) return;
        try {
            int cantidad = Integer.parseInt(sCantidad.trim());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "Ingrese cantidad positiva");
                return;
            }
            String nomReal = nombres.get(idx);
            int stk = stock.getOrDefault(nomReal, 0);
            if (cantidad > stk) {
                JOptionPane.showMessageDialog(null, "Stock insuficiente");
                return;
            }
            double precio = (idx < precios.length) ? precios[idx] : 0.0;
            double subtotal = precio * cantidad;
            int conf = JOptionPane.showConfirmDialog(null, "Confirmar compra: " + nomReal + " x" + cantidad + " = $" + df.format(subtotal), "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                stock.put(nomReal, stk - cantidad);
                totalSession += subtotal;
                JOptionPane.showMessageDialog(null, "Compra realizada. Subtotal: $" + df.format(subtotal));
            } else {
                JOptionPane.showMessageDialog(null, "Compra cancelada");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida");
        }
    }

    // Devuelve índice por coincidencia exacta o -1
    private static int indexOfNombre(String nombre) {
        for (int i = 0; i < nombres.size(); i++) {
            if (nombres.get(i).equalsIgnoreCase(nombre)) return i;
        }
        return -1;
    }

    // TASK 3: Estadísticas (más barato y más caro)
    private static void mostrarEstadisticas() {
        if (precios.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay precios registrados");
            return;
        }
        double min = precios[0];
        double max = precios[0];
        int idxMin = 0, idxMax = 0;
        for (int i = 1; i < precios.length; i++) {
            if (precios[i] < min) {
                min = precios[i];
                idxMin = i;
            }
            if (precios[i] > max) {
                max = precios[i];
                idxMax = i;
            }
        }
        String msg = "Producto más barato: " + nombres.get(idxMin) + " - $" + df.format(min) + "\n"
                + "Producto más caro: " + nombres.get(idxMax) + " - $" + df.format(max);
        JOptionPane.showMessageDialog(null, msg);
    }

    // TASK 3: Buscar producto por coincidencia parcial
    private static void buscarProducto() {
        if (nombres.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Inventario vacío");
            return;
        }
        String q = JOptionPane.showInputDialog(null, "Buscar (texto parcial):");
        if (q == null) return;
        q = q.trim().toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nombres.size(); i++) {
            String nom = nombres.get(i);
            if (nom.toLowerCase().contains(q)) {
                double pr = (i < precios.length) ? precios[i] : 0.0;
                int stk = stock.getOrDefault(nom, 0);
                sb.append(String.format("%s - $%s - Stock: %d\n", nom, df.format(pr), stk));
            }
        }
        String res = sb.length() == 0 ? "No se encontraron coincidencias" : sb.toString();
        JOptionPane.showMessageDialog(null, res);
    }

    // Mostrar ticket final (total acumulado)
    private static void showTicketFinal() {
        JOptionPane.showMessageDialog(null, "Ticket final - Total acumulado en sesión: $" + df.format(totalSession));
    }
}
