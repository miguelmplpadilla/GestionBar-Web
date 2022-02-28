package com.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Productos {
    private int id;
    private String nombre;
    private Integer fkCategoria;
    private String ingredientes = "";
    private String alergenos = "";
    private String trazas = "";
    private String codigoTrazabilidad = "";
    private String img = "";

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "fk_categoria")
    public Integer getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(Integer fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    @Basic
    @Column(name = "ingredientes")
    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    @Basic
    @Column(name = "alergenos")
    public String getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(String alergenos) {
        this.alergenos = alergenos;
    }

    @Basic
    @Column(name = "trazas")
    public String getTrazas() {
        return trazas;
    }

    public void setTrazas(String trazas) {
        this.trazas = trazas;
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
    @Column(name = "img")
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productos productos = (Productos) o;
        return id == productos.id && Objects.equals(nombre, productos.nombre) && Objects.equals(fkCategoria, productos.fkCategoria) && Objects.equals(ingredientes, productos.ingredientes) && Objects.equals(alergenos, productos.alergenos) && Objects.equals(trazas, productos.trazas) && Objects.equals(codigoTrazabilidad, productos.codigoTrazabilidad) && Objects.equals(img, productos.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fkCategoria, ingredientes, alergenos, trazas, codigoTrazabilidad, img);
    }
}
