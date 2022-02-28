package com.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Comparativaprecio {
    private int id;
    private double precio;
    private Date fecha;
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
        return id == that.id && Double.compare(that.precio, precio) == 0 && fkProducto == that.fkProducto && fkProveedor == that.fkProveedor && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, precio, fecha, fkProducto, fkProveedor);
    }
}
