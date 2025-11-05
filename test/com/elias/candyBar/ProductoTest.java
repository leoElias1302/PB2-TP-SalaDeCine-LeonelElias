package com.elias.candyBar;

import static org.junit.Assert.*;
import org.junit.Test;

public class ProductoTest {

    @Test
    public void testCalculoPrecioFinalSnack() {
        // Snack pequeño (15% de descuento)
        Snack snackPequeno = new Snack("Palomitas Pequeñas", 4.0, Tamanio.PEQUENIO, 10);
        assertEquals(3.4, snackPequeno.calcularPrecioFinal(), 0.01);

        // Snack mediano (sin cambio)
        Snack snackMediano = new Snack("Palomitas Medianas", 4.0, Tamanio.MEDIANO, 10);
        assertEquals(4.0, snackMediano.calcularPrecioFinal(), 0.01);

        // Snack grande (20% de recargo)
        Snack snackGrande = new Snack("Palomitas Grandes", 4.0, Tamanio.GRANDE, 5);
        assertEquals(4.8, snackGrande.calcularPrecioFinal(), 0.01);
    }

    @Test
    public void testCalculoPrecioFinalBebida() {
        // Bebida sin recargo (VASO)
        Bebida bebidaVaso = new Bebida("Refresco Cola", 3.0, Contenedor.VASO, 15);
        assertEquals(3.0, bebidaVaso.calcularPrecioFinal(), 0.01);

        // Bebida con recargo (BOTELLA)
        Bebida bebidaBotella = new Bebida("Jugo de Naranja", 2.5, Contenedor.BOTELLA, 8);
        assertEquals(2.75, bebidaBotella.calcularPrecioFinal(), 0.01);
    }

    @Test
    public void testCalculoPrecioFinalCombo() {
        Snack snack = new Snack("Nachos", 6.0, Tamanio.MEDIANO, 20);
        Bebida bebida = new Bebida("Agua", 2.0, Contenedor.VASO, 25);

        // Combo con 20% de descuento
        Combo comboDescuento = new Combo("Combo Deluxe", snack, bebida, 0.20, 5);
        double precioEsperadoDescuento = (6.0 + 2.0) * (1 - 0.20);
        assertEquals(precioEsperadoDescuento, comboDescuento.calcularPrecioFinal(), 0.01);

        // Combo sin descuento
        Combo comboSinDescuento = new Combo("Combo Básico", snack, bebida, 0.0, 3);
        double precioEsperadoSinDescuento = 6.0 + 2.0;
        assertEquals(precioEsperadoSinDescuento, comboSinDescuento.calcularPrecioFinal(), 0.01);
    }
    
}
