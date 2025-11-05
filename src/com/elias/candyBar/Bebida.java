package com.elias.candyBar;

public class Bebida extends Producto{
	Contenedor contenedor;
	
	public Bebida(String nombre, double precioBase, Contenedor contenedor, int stock) {
		super(nombre, precioBase, stock);
		this.contenedor = contenedor;
	}

	@Override
	protected double calcularPrecioFinal() {
		double precioFinal = this.precioBase;
		
		if(this.contenedor == Contenedor.BOTELLA)
			 precioFinal *= 1.1;
		
		return precioFinal;
	}
	
}
