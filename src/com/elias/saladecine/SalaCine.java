package com.elias.saladecine;

import java.util.HashMap;
import java.util.Map;

public class SalaCine {
    private Map<String, Asiento> butacas;
    private Pelicula pelicula;
    private int filas;
    private int columnas;

    public SalaCine(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.butacas = new HashMap<>();

        for (int i = 0; i < filas; i++) {
            char letra = (char) ('A' + i);
            for (int j = 1; j <= columnas; j++) {
                String clave = letra + String.valueOf(j);
                butacas.put(clave, new Asiento());
            }
        }
    }

    public Map<String, Asiento> getButacas() {
        return butacas;
    }

    public Asiento getAsiento(String clave) {
        return butacas.get(clave);
    }

    public int contarAsientosOcupados() {
        int contador = 0;
        for (Asiento asiento : butacas.values()) {
            if (asiento.estaOcupado()) {
                contador++;
            }
        }
        return contador;
    }

    public int getTotalAsientos() {
        return butacas.size();
    }

    public void cambiarPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Pelicula getPeliculaActual() {
        return pelicula;
    }

    public boolean venderBoleto(String clave, int edad, String nombreComprador) {
        Asiento asiento = butacas.get(clave);

        if (asiento == null) return false;                    
        if (asiento.estaOcupado()) return false;              
        if (pelicula == null) return false;                  
        if (pelicula.getEdadMinima() > edad) return false;   
        if (nombreComprador == null || nombreComprador.isEmpty()) return false;

        asiento.ocupar(nombreComprador);
        return true;
    }

    public String getTitulo() {
        return (pelicula != null) ? pelicula.getTitulo() : null;
    }

    public boolean liberarAsiento(String clave) {
        Asiento asiento = butacas.get(clave);
        if (asiento == null) return false;
        asiento.liberar();
        return true;
    }

    public void reiniciarSala() {
        for (Asiento asiento : butacas.values()) {
            asiento.liberar();
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
