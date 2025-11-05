package com.elias.saladecine;

import java.util.Objects;

public class LetraYNumero {
    private char letra;
    private int numero;

    public LetraYNumero(char letra, int numero) {
        this.letra = Character.toUpperCase(letra);
        this.numero = numero;
    }

    public char getLetra() {
        return letra;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LetraYNumero that = (LetraYNumero) o;
        return letra == that.letra && numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letra, numero);
    }

    @Override
    public String toString() {
        return letra + String.valueOf(numero);
    }
}
