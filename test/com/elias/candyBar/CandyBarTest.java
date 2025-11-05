package com.elias.candyBar;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class CandyBarTest {

    private CandyBar candyBar;
    private Snack papas;
    private Bebida coca;
    private Bebida agua;

    @Before
    public void setUp() {
        candyBar = new CandyBar();
        papas = new Snack("Papas", 5.0, Tamanio.GRANDE, 10);
        coca = new Bebida("Coca Cola", 3.0, Contenedor.BOTELLA, 8);
        agua = new Bebida("Agua", 2.0, Contenedor.VASO, 12);
    }

    @Test
    public void testAgregarProducto() throws Exception {
        candyBar.agregarProducto(papas);
        candyBar.agregarProducto(coca);

        assertEquals(2, candyBar.obtenerInventario().size());
    }

    @Test(expected = ProductoDuplicadoException.class)
    public void testAgregarProductoDuplicadoLanzaExcepcion() throws Exception {
        candyBar.agregarProducto(coca);
        candyBar.agregarProducto(coca);
    }

    @Test
    public void testEliminarProductoExistente() throws Exception {
        candyBar.agregarProducto(agua);
        candyBar.eliminarProducto("Agua");
        assertEquals(0, candyBar.obtenerInventario().size());
    }

    @Test(expected = ProductoNoEncontradoException.class)
    public void testEliminarProductoInexistenteLanzaExcepcion() throws Exception {
        candyBar.eliminarProducto("Fanta");
    }

    @Test
    public void testOrdenarBebidasPorPrecio() throws Exception {
        candyBar.agregarProducto(coca);
        candyBar.agregarProducto(agua);

        List<Bebida> bebidas = candyBar.obtenerBebidasOrdenadasPorPrecio();

        assertEquals("Agua", bebidas.get(0).getNombre());
        assertEquals("Coca Cola", bebidas.get(1).getNombre());
    }

    @Test
    public void testOrdenarSnacksPorNombre() throws Exception {
        Snack maní = new Snack("Maní", 4.0, Tamanio.PEQUENIO, 5);
        Snack alfajor = new Snack("Alfajor", 3.0, Tamanio.MEDIANO, 8);

        candyBar.agregarProducto(papas);
        candyBar.agregarProducto(maní);
        candyBar.agregarProducto(alfajor);

        List<Snack> snacksOrdenados = candyBar.obtenerSnacksOrdenadosPorNombre();

        assertEquals("Alfajor", snacksOrdenados.get(0).getNombre());
        assertEquals("Maní", snacksOrdenados.get(1).getNombre());
        assertEquals("Papas", snacksOrdenados.get(2).getNombre());
    }
}
