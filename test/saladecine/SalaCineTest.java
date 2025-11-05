package saladecine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import com.elias.saladecine.*;

public class SalaCineTest {

    private SalaCine sala;
    private Pelicula pelicula;

    @Before
    public void setUp() {
        sala = new SalaCine(2, 3);
        pelicula = new PeliculaAccion("Misión Imposible", 120, 13);
        sala.cambiarPelicula(pelicula);
    }

    @Test
    public void testCrearSalaOk() {
        assertEquals(6, sala.getTotalAsientos());
        assertEquals(pelicula, sala.getPeliculaActual());
        assertNotNull(sala.getAsiento("A1"));
        assertNotNull(sala.getAsiento("B3"));
    }

    @Test
    public void testVenderBoletoExitoso() {
        boolean venta1 = sala.venderBoleto("A1", 18, "Leonel Elias");
        boolean venta2 = sala.venderBoleto("B2", 20, "Juan Perez");

        assertTrue(venta1);
        assertTrue(venta2);
        assertEquals(2, sala.contarAsientosOcupados());
    }

    @Test
    public void testVenderBoletoNoExitosoPorEdad() {
        boolean venta = sala.venderBoleto("A2", 10, "Lucas Perez");
        assertFalse(venta);
        assertEquals(0, sala.contarAsientosOcupados());
    }

    @Test
    public void testVenderBoletoNoExitosoPorAsientoOcupado() {
        sala.venderBoleto("A1", 20, "Leonel Elias");
        boolean venta2 = sala.venderBoleto("A1", 25, "Maria Lopez");
        assertTrue(sala.getAsiento("A1").estaOcupado());
        assertFalse(venta2);
    }

    @Test
    public void testVenderBoletoAsientoInexistente() {
        boolean venta = sala.venderBoleto("Z9", 25, "Pedro");
        assertFalse(venta);
    }

    @Test
    public void testLiberarAsiento() {
        sala.venderBoleto("A1", 20, "Leonel");
        assertTrue(sala.liberarAsiento("A1"));
        assertFalse(sala.getAsiento("A1").estaOcupado());
    }

    @Test
    public void testLiberarAsientoInexistente() {
        assertFalse(sala.liberarAsiento("Z5"));
    }

    @Test
    public void testReiniciarSala() {
        sala.venderBoleto("A1", 20, "Leonel");
        sala.venderBoleto("B3", 20, "Esteban");
        assertEquals(2, sala.contarAsientosOcupados());

        sala.reiniciarSala();
        assertEquals(0, sala.contarAsientosOcupados());
    }
}
