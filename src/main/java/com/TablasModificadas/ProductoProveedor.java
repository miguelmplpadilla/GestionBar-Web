package com.TablasModificadas;

public class ProductoProveedor {

    public int id;
    public String nombre;
    public String img;
    public double precio;
    public double iva;
    public String fkCategoria;

    public ProductoProveedor(int id, String nombre, String img, double precio, String fkCategoria, double iva) {
        this.id = id;
        this.nombre = nombre;
        this.img = img;
        this.precio = precio;
        this.fkCategoria = fkCategoria;
        this.iva = iva;
    }
}
