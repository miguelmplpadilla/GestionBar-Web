package com.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Mesas {
    private int id;
    private byte ocupada;
    private int fkBar;
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
    @Column(name = "ocupada")
    public byte getOcupada() {
        return ocupada;
    }

    public void setOcupada(byte ocupada) {
        this.ocupada = ocupada;
    }

    @Basic
    @Column(name = "fk_bar")
    public int getFkBar() {
        return fkBar;
    }

    public void setFkBar(int fkBar) {
        this.fkBar = fkBar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mesas mesas = (Mesas) o;
        return id == mesas.id && ocupada == mesas.ocupada && fkBar == mesas.fkBar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ocupada, fkBar);
    }
}
