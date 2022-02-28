package com.TablasModificadas;

import com.entity.Proveedores;

import java.util.List;

public class ProveedorComparativaPrecio {
    public Proveedores proveedor;
    public List<Comparativa> productos;

    public ProveedorComparativaPrecio(Proveedores proveedor, List<Comparativa> productos) {
        this.proveedor = proveedor;
        this.productos = productos;
    }
}
