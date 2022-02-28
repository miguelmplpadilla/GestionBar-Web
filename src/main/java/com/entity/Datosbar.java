package com.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Datosbar {
    private int id;
    private Integer fkBar;
    private String fotoPerfil;
    private String correoElectronico;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "fk_bar")
    public Integer getFkBar() {
        return fkBar;
    }

    public void setFkBar(Integer fkBar) {
        this.fkBar = fkBar;
    }

    @Basic
    @Column(name = "fotoPerfil")
    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    @Basic
    @Column(name = "correoElectronico")
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datosbar datosbar = (Datosbar) o;
        return id == datosbar.id && Objects.equals(fkBar, datosbar.fkBar) && Objects.equals(fotoPerfil, datosbar.fotoPerfil) && Objects.equals(correoElectronico, datosbar.correoElectronico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkBar, fotoPerfil, correoElectronico);
    }
}
