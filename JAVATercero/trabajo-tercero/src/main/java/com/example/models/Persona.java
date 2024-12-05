package com.example.models;
import java.io.Serializable;

public class Persona {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaNacimiento;
    private String sexo;
    private String numCelular;
    private String tipo;

    public Persona() {
    }
    
    public Persona(String apellido, String dni, String fechaNacimiento, Integer id, String nombre, String numCelular, String sexo, String tipo) {
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.id = id;
        this.nombre = nombre;
        this.numCelular = numCelular;
        this.sexo = sexo;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id; 
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getNumCelular() {
        return numCelular;
    }
    public void setNumCelular(String numCelular) {
        this.numCelular = numCelular;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    

   
    
}
