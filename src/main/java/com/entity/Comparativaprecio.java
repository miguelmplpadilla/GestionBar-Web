package com.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Comparativaprecio {
    private int id;
    private double precio;
    private Date fecha;
    private double iva;
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
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "iva")
    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
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

        Comparativaprecio that = (Comparativaprecio) o;

        if (id != that.id) return false;
        if (Double.compare(that.precio, precio) != 0) return false;
        if (Double.compare(that.iva, iva) != 0) return false;
        if (fkBar != that.fkBar) return false;
        if (fkProducto != that.fkProducto) return false;
        if (fkProveedor != that.fkProveedor) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(precio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        temp = Double.doubleToLongBits(iva);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + fkBar;
        result = 31 * result + fkProducto;
        result = 31 * result + fkProveedor;
        return result;
    }
}
