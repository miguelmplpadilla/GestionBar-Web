package com.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Codigostrazabilidad {
    private int id;
    private int fkProducto;
    private String codigoTrazabilidad;
    private Integer fkBar;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "codigo_trazabilidad")
    public String getCodigoTrazabilidad() {
        return codigoTrazabilidad;
    }

    public void setCodigoTrazabilidad(String codigoTrazabilidad) {
        this.codigoTrazabilidad = codigoTrazabilidad;
    }

    @Basic
    @Column(name = "fk_bar")
    public Integer getFkBar() {
        return fkBar;
    }

    public void setFkBar(Integer fkBar) {
        this.fkBar = fkBar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Codigostrazabilidad that = (Codigostrazabilidad) o;
        return id == that.id && fkProducto == that.fkProducto && Objects.equals(codigoTrazabilidad, that.codigoTrazabilidad) && Objects.equals(fkBar, that.fkBar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkProducto, codigoTrazabilidad, fkBar);
    }
}
