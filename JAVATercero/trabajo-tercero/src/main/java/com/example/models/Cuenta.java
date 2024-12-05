package com.example.models;


public class Cuenta {
    private Integer id;
    private Integer id_Persona;
    private String email;
    private String password;
    private Boolean estado;

    public Cuenta(String email, Boolean estado, Integer id, Integer id_Persona, String password) {
        this.email = email;
        this.estado = estado;
        this.id = id;
        this.id_Persona = id_Persona;
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


}
