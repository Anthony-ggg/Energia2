package com.example.controls.dao.services;

import com.example.controls.dao.PersonaDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Persona;


public class PersonaServices {
    private PersonaDao obj;

    public PersonaServices() {
        obj = new PersonaDao();
    }
    
    
    public Boolean save() throws Exception {
        return obj.save();
    }
    

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public Persona getPersona() {
        return obj.getPersona();
    }
    public void setPersona(Persona persona) {
        obj.setPersona(persona);
    }
    public Persona get(Integer id) throws Exception {
        return obj.get(id);
    }
   /*  public LinkedList order(Integer type_order, String atributo) {
        return obj.order(type_order,atributo);
    }
    public LinkedList<Persona> buscar_apellidos(String texto) {
        return obj.buscar_apellidos(texto);
    }
    public Persona buscar_identificacion(String texto) {
        return obj.buscar_identificacion(texto);
    } */
     public LinkedList<Persona> ordenar(String atributo, Integer tipo, String algoritmo) throws Exception {
        return obj.ordenar(atributo, tipo, algoritmo);
    }

    // Nuevo método para búsqueda por atributo
    public LinkedList<Persona> buscarPorAtributo(String atributo, Object valor) throws Exception {
        return obj.buscarPorAtributo(atributo, valor);
    }

    // Nuevo método para búsqueda binaria
    public Persona buscarBinaria(String atributo, Object valor) throws Exception {
        return obj.buscarBinaria(atributo, valor);
    }

    // Método específico para buscar por apellidos
    public LinkedList<Persona> buscarPorApellidos(String texto) throws Exception {
        return obj.buscarPorApellidos(texto);
    }

    // Método específico para buscar por DNI
    public Persona buscarPorDni(String dni) throws Exception {
        return obj.buscarPorDni(dni);
    }
   
    
    
    
    
}
