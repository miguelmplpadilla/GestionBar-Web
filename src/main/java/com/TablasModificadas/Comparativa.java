package com.TablasModificadas;

import java.util.ArrayList;

public class Comparativa {

    public int id;
    public String nombreProducto;
    public PorductoComparativa productos;

    public Comparativa(int id, String nombreProducto, PorductoComparativa productos) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.productos = productos;
    }
}
