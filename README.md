# User_Stories
# MiniTienda - Proyecto en Java

**Autora:** María Gabriela Yanes  
**Clan:** Ciénaga  
**Zona:** Autor  

---

## Descripción
**MiniTienda** es un sistema de gestión de inventario desarrollado en **Java**, que permite realizar operaciones básicas sobre productos utilizando una interfaz gráfica basada en **JOptionPane**.  
El proyecto simula el funcionamiento de una pequeña tienda donde se pueden agregar productos, realizar compras, consultar inventario y generar un ticket final.

---

## Funcionalidades Principales
- **Agregar productos:** Permite registrar nuevos productos con su nombre, precio y cantidad disponible.  
- **Listar inventario:** Muestra todos los productos con su respectiva información.  
- **Comprar productos:** Simula una venta, descuenta unidades del stock y suma el valor al ticket final.  
- **Buscar productos:** Localiza un producto por su nombre y muestra sus datos.  
- **Salir del sistema:** Finaliza el programa mostrando el total acumulado de compras.

---

## Requisitos
- **Lenguaje:** Java 8 o superior  
- **IDE recomendado:** IntelliJ IDEA, Eclipse o NetBeans  

---

## Estructura del Proyecto
**Clase principal:** `MiniTienda.java`  
Contiene la lógica del sistema y las operaciones del menú principal.

### Variables principales
- `ArrayList<String> nombres` → Almacena los nombres de los productos.  
- `HashMap<String, Integer> productos` → Guarda el stock de cada producto.  
- `double[] precios` → Guarda los precios de los productos.  
- `double ticket` → Acumula el valor total de las compras.  
- `int contador` → Lleva el registro de los productos agregados.  

---

## Ejemplo de uso
**Menú principal:**
```
1. Agregar producto  
2. Listar inventario  
3. Comprar producto  
4. Mostrar estadísticas (pendiente)  
5. Buscar producto por nombre  
6. Salir con ticket final
```

**Ejemplo de agregar producto:**
```
Nombre: Camisa  
Precio: 80000  
Cantidad: 5  
Producto agregado correctamente.
```

**Ejemplo de compra:**
```
Producto a comprar: Camisa  
Cantidad: 2  
Compra realizada. Nuevo stock: 3
```

**Ejemplo de salida:**
```
Total del ticket: $160000
Gracias por su compra.
```

---

## Mejoras Futuras
- Implementar estadísticas del producto más barato y más caro.  
- Añadir persistencia de datos con archivos o base de datos.  
- Crear una interfaz gráfica más avanzada con JavaFX o Swing.
