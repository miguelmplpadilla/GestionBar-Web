package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "fk_productosproveedores", schema = "sql11475961", catalog = "")
public class FkProductosproveedores {
    private int id;
    private double precio;
    private int fkBar;
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
    @Column(name = "fk_bar")
    public int getFkBar() {
        return fkBar;
    }

    public void setFkBar(int fkBar) {
        this.fkBar = fkBar;
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

        if (id != that.id) return false;
        if (Double.compare(that.precio, precio) != 0) return false;
        if (fkBar != that.fkBar) return false;
        if (fkProducto != that.fkProducto) return false;
        if (fkProveedor != that.fkProveedor) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(precio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + fkBar;
        result = 31 * result + fkProducto;
        result = 31 * result + fkProveedor;
        return result;
    }
}
