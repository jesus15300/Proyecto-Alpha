package com.example.crud.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column(name = "fecha_de_alta")
    private Date fechaAlta;
    
    @Column(name = "fecha_de_actualizacion")
    private Date fechaActualizacion;
    
    @Column
    private Boolean activo;
    
    @Column
    private Boolean accesoPermitido;
    @ManyToOne
    @JoinColumn(name = "id_club")
    private Club club;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Boolean getAccesoPermitido() {
        return accesoPermitido;
    }

    public void setAccesoPermitido(Boolean accesoPermitido) {
        this.accesoPermitido = accesoPermitido;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", fechaAlta=" + fechaAlta + ", fechaActualizacion="
                + fechaActualizacion + ", activo=" + activo + ", accesoPermitido=" + accesoPermitido + ", club=" + club
                + "]";
    }

    
}
