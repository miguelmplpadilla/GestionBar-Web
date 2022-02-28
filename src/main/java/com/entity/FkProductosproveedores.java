package com.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fk_productosproveedores", schema = "gestionbar", catalog = "")
public class FkProductosproveedores {
    private int id;
    private double precio;
    private int fkProducto;
    private int fkProveedor;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "precio")
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "fk_producto")
    public int getFkProducto() {
        return fkProducto;
    }

    public void setFkProducto(int fkProducto) {
        this.fkProducto = fkProducto;
    }

    @Basic
    @Column(name = "fk_proveedor")
    public int getFkProveedor() {
        return fkProveedor;
    }

    public void setFkProveedor(int fkProveedor) {
        this.fkProveedor = fkProveedor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FkProductosproveedores that = (FkProductosproveedores) o;
        return id == that.id && Double.compare(that.precio, precio) == 0 && fkProducto == that.fkProducto && fkProveedor == that.fkProveedor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, precio, fkProducto, fkProveedor);
    }
}
