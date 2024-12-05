package com.example.models;

public class Rol {
    private Integer id;
    private Integer id_Persona;
    private String nombre;


    public Rol(Integer id, Integer id_Persona, String nombre) {
        this.id = id;
        this.id_Persona = id_Persona;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId_Persona() {
        return id_Persona;
    }
    public void setId_Persona(Integer id_Persona) {
        this.id_Persona = id_Persona;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
