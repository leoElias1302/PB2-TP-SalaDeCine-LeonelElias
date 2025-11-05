package com.elias.candyBar;

public abstract class Producto implements Comparable<Producto> {
    String nombre;
    double precioBase;
    int stock;

    protected abstract double calcularPrecioFinal();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Producto(String nombre, double precioBase, int stock) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;
    }

    @Override
    public int compareTo(Producto otro) {
        return Integer.compare(otro.stock, this.stock);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Producto)) return false;
        Producto otro = (Producto) obj;
        return this.nombre.equalsIgnoreCase(otro.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }
}

