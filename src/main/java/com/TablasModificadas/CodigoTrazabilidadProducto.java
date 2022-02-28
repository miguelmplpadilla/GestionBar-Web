package com.TablasModificadas;

import com.entity.Productos;

import java.util.ArrayList;

public class CodigoTrazabilidadProducto {

    public int id;
    public String nombreProducto;
    public ArrayList<String> codigoTrazabilidad;

    public CodigoTrazabilidadProducto(int id, String nombreProducto, ArrayList<String> codigoTrazabilidad) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.codigoTrazabilidad = codigoTrazabilidad;
    }
}
