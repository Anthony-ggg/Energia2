package com.example.controls.dao.services;

import com.example.controls.dao.ProyectoEnergiaDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Persona;
import com.example.models.ProyectoEnergia;

public class ProyectoEnergiaServices {
    private ProyectoEnergiaDao obj;  


    public ProyectoEnergiaServices(){
        obj = new ProyectoEnergiaDao();
    }


    public Boolean save() throws Exception{
        return obj.save();
    }
    public Boolean update() throws Exception{
        return obj.update();
    }

    public LinkedList listAll() {
        return obj.getListAll();
    }

    public ProyectoEnergia getProyectoEnergia(){
        return obj.getProyectoEnergia();
    }

    public void setProyectoEnergia( ProyectoEnergia proyectoEnergia){
        obj.setProyectoEnergia(proyectoEnergia);    
    }
    public LinkedList order(Integer type_order, String atributo) {
        return obj.order(type_order,atributo);
    }
    public ProyectoEnergia get(Integer id) throws Exception{
        return obj.get(id);
    }
    public LinkedList<ProyectoEnergia> buscar_inversion(Double texto) {
        return obj.buscar_inversion(texto);  // Ahora devuelve una lista de proyectos
    }
    
    public LinkedList<ProyectoEnergia> buscar_nombre(String texto){
        return obj.buscar_nombre(texto);
    }
    public LinkedList<ProyectoEnergia> buscar_tiempoVida(Integer texto) {
        return obj.buscar_tiempoVida(texto);  
    }
 
    // Método para realizar la búsqueda binaria //
    public int busquedaBinaria(String atributo, Object valor) {
        return obj.busqueda_binaria(atributo, valor);
    }
     public LinkedList<ProyectoEnergia> ordenar(String atributo, Integer tipo, String algoritmo) throws Exception {
        return obj.ordenar(atributo, tipo, algoritmo);
    }


}   
