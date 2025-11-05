package com.elias.candyBar;

public class Snack extends Producto {
	private Tamanio tamanio; 
	//El precio final tiene 20% de recargo si es grande y 15% de descuento si es pequeño.
	
	public Snack(String nombre, double precioBase, Tamanio tamanio, int stock) {
		super(nombre, precioBase, stock);
		this.tamanio = tamanio;
	}

	@Override
	protected double calcularPrecioFinal() {
		
		switch (this.tamanio) {
		case PEQUENIO: {
			// Descuento
			return precioBase - (precioBase * 0.15);
			// precioBase  * 0.85;  // (1-0.15)
		}
		case GRANDE: {
			// Recargo
			return precioBase * 1.20;
			// (precioBase * 1) + precioBase * 0.20;
		}
		default:
			
		}
		return precioBase;
	}

}
