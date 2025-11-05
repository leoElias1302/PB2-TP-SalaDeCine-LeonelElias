package com.elias.candyBar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CandyBar {
	private List<Producto> productos;
	
	public CandyBar() {
	    this.productos = new ArrayList<>();
	}

	public void agregarProducto(Producto producto) throws ProductoDuplicadoException {
        boolean agregado = productos.add(producto);
        if (!agregado) {
            throw new ProductoDuplicadoException("❌ El producto '" + producto.getNombre() + "' ya existe en el CandyBar.");
        }
    }

	public void eliminarProducto(String productoABuscar) throws ProductoNoEncontradoException {
        boolean eliminado = productos.removeIf(p -> p.getNombre().equalsIgnoreCase(productoABuscar));
        if (!eliminado) {
            throw new ProductoNoEncontradoException("❌ No se encontró el producto '" + productoABuscar + "'.");
        }
    }

	public List<Producto> obtenerInventario() {
	    return this.productos;
	}
	
	public List<Bebida> obtenerBebidasOrdenadasPorPrecio() {
        return productos.stream()
                .filter(p -> p instanceof Bebida)
                .map(p -> (Bebida) p)
                .sorted(Comparator.comparingDouble(Producto::getPrecioBase))
                .collect(Collectors.toList());
    }
	
	public List<Snack> obtenerSnacksOrdenadosPorNombre() {
	    return productos.stream()
	            .filter(p -> p instanceof Snack)
	            .map(p -> (Snack) p)
	            .sorted(Comparator.comparing(Producto::getNombre))
	            .collect(Collectors.toList());
	}
}
