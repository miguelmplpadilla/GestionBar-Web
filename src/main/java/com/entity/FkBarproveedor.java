package com.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "fk_barproveedor", schema = "gestionbar", catalog = "")
public class FkBarproveedor {
    private int id;
    private int fkBar;
    private int fkProveedor;
    private Bar barByFkBar;

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
    public int getFkBar() {
        return fkBar;
    }

    public void setFkBar(int fkBar) {
        this.fkBar = fkBar;
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
        FkBarproveedor that = (FkBarproveedor) o;
        return id == that.id && fkBar == that.fkBar && fkProveedor == that.fkProveedor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fkBar, fkProveedor);
    }
}
