# MiniTienda - Semana 3

## Descripción del Proyecto
**MiniTienda** es un sistema de gestión de inventario desarrollado en **Java**, diseñado para simular el funcionamiento básico de una tienda.  
Durante esta **semana 3**, se implementaron nuevas funcionalidades como **persistencia de datos en archivos**, **registro de ventas** y un **reporte estadístico** con el producto más vendido.

El sistema funciona mediante una interfaz sencilla con **JOptionPane**, que permite al usuario interactuar con el inventario, realizar compras y visualizar estadísticas.

---

##️ Funcionalidades Principales
1. **Agregar productos al inventario**  
   Permite ingresar alimentos o electrodomésticos, con su nombre, precio, cantidad y características específicas.

2. **Listar inventario completo**  
   Muestra en consola todos los productos almacenados, su precio, cantidad disponible y descripción.

3. **Realizar compras**  
   Permite comprar productos existentes, actualizando el stock automáticamente y registrando la venta en un archivo.

4. **Ver ventas registradas**  
   Muestra todas las ventas realizadas durante la ejecución del programa.

5. **Generar reporte de ventas**  
   Calcula el total vendido, número de ventas realizadas y muestra el producto más vendido.

6. **Persistencia de datos**  
   Los inventarios y las ventas se guardan automáticamente en archivos de texto (`inventario.txt` y `ventas.txt`), permitiendo conservar la información entre ejecuciones.

---

## Estructura del Proyecto
src/
└── main/
└── java/
└── com/minitienda/minitienda/
├── model/
│ ├── Producto.java
│ ├── Alimento.java
│ ├── Electrodomestico.java
│ └── Venta.java
├── service/
│ └── InventarioService.java
└── ui/
└── MainApp.java


---

## 💡 Clases Principales

### 🔹 Producto (abstracta)
Clase base que define los atributos comunes:  
`nombre`, `precio`, `cantidad`, y un método abstracto `getDescripcion()`.

### Alimento
Hereda de `Producto` e incluye el tipo de alimento (ej. grano, lácteo, vegetal).

### Electrodomestico
Hereda de `Producto` e incluye el atributo `potencia` (en watts).

### Venta
Registra la información de cada venta realizada: nombre del producto, precio unitario, cantidad vendida y subtotal.

### InventarioService
Contiene la lógica principal del sistema:
- Agregar, listar, buscar y comprar productos.  
- Registrar y guardar ventas.  
- Generar un reporte de ventas con estadísticas.  
- Guardar y cargar datos desde archivos `.txt`.

###  MainApp
Interfaz de usuario que muestra un menú mediante **JOptionPane** para ejecutar todas las operaciones.

---

## Ejemplo de Interacción
=== MINI TIENDA WEEK 3 ===

1-Agregar producto
2-Listar inventario
3-Comprar producto
4-Ver ventas registradas
5-Generar reporte de ventas
6-Salir

**Caso de compra:**
Ingrese el nombre del producto: Leche
Ingrese la cantidad a comprar: 2
Compra realizada con éxito.


---

## Requisitos Técnicos
- **Java 17 o superior**  
- IDE recomendado: **NetBeans**, **IntelliJ IDEA** o **Eclipse**  
- Sistema operativo compatible con Java (Windows, Linux, macOS)

---

## Ejecución
1. Clonar el repositorio:
   ```bash
   https://github.com/Gabrielayanesp/User_Stories.git

2. Abrir el proyecto en tu IDE.

3. Ejecutar la clase principal:
src/main/java/com/minitienda/minitienda/ui/MainApp.java


## Autora

Nombre: María Gabriela Yanes
Clan: Ciénaga