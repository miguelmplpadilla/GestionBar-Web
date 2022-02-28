package com.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Reservas {
    private int id;
    private String nombreCliente;
    private String numeroTelefono;
    private Integer cantidadComensales;
    private Date fecha;
    private Time hora;
    private String comentarios;
    private int fkMesa;
    private Mesas mesasByFkMesa;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombreCliente")
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Basic
    @Column(name = "numeroTelefono")
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    @Basic
    @Column(name = "cantidadComensales")
    public Integer getCantidadComensales() {
        return cantidadComensales;
    }

    public void setCantidadComensales(Integer cantidadComensales) {
        this.cantidadComensales = cantidadComensales;
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
    @Column(name = "hora")
    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    @Basic
    @Column(name = "comentarios")
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Basic
    @Column(name = "fk_mesa")
    public int getFkMesa() {
        return fkMesa;
    }

    public void setFkMesa(int fkMesa) {
        this.fkMesa = fkMesa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservas reservas = (Reservas) o;
        return id == reservas.id && fkMesa == reservas.fkMesa && Objects.equals(nombreCliente, reservas.nombreCliente) && Objects.equals(numeroTelefono, reservas.numeroTelefono) && Objects.equals(cantidadComensales, reservas.cantidadComensales) && Objects.equals(fecha, reservas.fecha) && Objects.equals(hora, reservas.hora) && Objects.equals(comentarios, reservas.comentarios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreCliente, numeroTelefono, cantidadComensales, fecha, hora, comentarios, fkMesa);
    }
}
