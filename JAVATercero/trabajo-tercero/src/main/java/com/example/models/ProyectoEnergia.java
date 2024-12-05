package com.example.models;

import java.sql.Date;

import com.google.gson.Gson;

// ProyectoEnergia.java
public class ProyectoEnergia {
    private Integer id;
    private String nombre;
    private double inversion;
    private Integer tiempoVida;
    private String fechaInicio;
    private String fechaFin;
    private double capacidadDiaria;

    public ProyectoEnergia() {

    }

    public ProyectoEnergia(Integer id, String nombre, double inversion, Integer tiempoVida, String fechaInicio,
            String fechaFin,
            double capacidadDiaria) {
        this.id = id;
        this.nombre = nombre;
        this.inversion = inversion;
        this.tiempoVida = tiempoVida;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.capacidadDiaria = capacidadDiaria;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getInversion() {
        return inversion;
    }

    public Integer getTiempoVida() {
        return tiempoVida;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public double getCapacidadDiaria() {
        return capacidadDiaria;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setInversion(double inversion) {
        this.inversion = inversion;
    }

    public void setTiempoVida(Integer tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setCapacidadDiaria(double capacidadDiaria) {
        this.capacidadDiaria = capacidadDiaria;
    }

   

}
