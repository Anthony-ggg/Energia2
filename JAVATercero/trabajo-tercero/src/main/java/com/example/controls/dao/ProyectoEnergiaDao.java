package com.example.controls.dao;

import java.util.Arrays;
import java.util.Comparator;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Persona;
import com.example.models.ProyectoEnergia;

public class ProyectoEnergiaDao extends AdapterDao<ProyectoEnergia> {
    private ProyectoEnergia proyectoEnergia;
    private LinkedList listAll;

    public ProyectoEnergiaDao() {
        super(ProyectoEnergia.class);
    }

    public ProyectoEnergia getProyectoEnergia() {
        if (proyectoEnergia == null) {
            proyectoEnergia = new ProyectoEnergia();
        }
        return this.proyectoEnergia;
    }

    public void setProyectoEnergia(ProyectoEnergia proyectoEnergia) {
        this.proyectoEnergia = proyectoEnergia;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        proyectoEnergia.setId(id);
        this.persist(this.proyectoEnergia);
        this.listAll = listAll();
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getProyectoEnergia(), getProyectoEnergia().getId() - 1);
        this.listAll = listAll();
        return true;
    }

    public LinkedList order(Integer type_order, String atributo) {
        LinkedList listita = listAll();
        if (!listAll().isEmpty()) {
            ProyectoEnergia[] lista = (ProyectoEnergia[]) listita.toArray();
            listita.reset();
            for (int i = 1; i < lista.length; i++) {
                ProyectoEnergia aux = lista[i];
                int j = i - 1;
                while (j >= 0 && (verify(lista[j], aux, type_order, atributo))) {
                    lista[j + 1] = lista[j--];// desplaza elementos hacia la derecha
                }
                lista[j + 1] = aux; // inserta el elemento en la posicion correcta
            }
            listita.toList(lista);
        }
        return listita;
    }
    
    private Boolean verify(ProyectoEnergia a, ProyectoEnergia b, Integer type_order, String atributo){
        if (type_order == 1) {
            if (atributo.equalsIgnoreCase("nombre")) {
                return a.getNombre().compareTo(b.getNombre()) > 0;
            } else if (atributo.equalsIgnoreCase("inversion")) {
                return Double.compare(a.getInversion(), b.getInversion()) > 0;
            } else if (atributo.equalsIgnoreCase("tiempoVida")) {
                return a.getTiempoVida() > b.getTiempoVida();
            }
        } else {
            if (atributo.equalsIgnoreCase("nombre")) {
                return a.getNombre().compareTo(b.getNombre()) < 0;
            } else if (atributo.equalsIgnoreCase("inversion")) {
                return Double.compare(a.getInversion(), b.getInversion()) < 0;
           
            } else if (atributo.equalsIgnoreCase("tiempoVida")) {
                return a.getTiempoVida() < b.getTiempoVida();
            }
        }
        return false;
    }
    

    public LinkedList<ProyectoEnergia> buscar_nombre(String texto){
        LinkedList<ProyectoEnergia> lista = new LinkedList<>();
        LinkedList<ProyectoEnergia> listita = listAll();
        if(!listita.isEmpty()){
            ProyectoEnergia[] aux = listita.toArray();
            for(int i = 0; i< aux.length; i++){
                if (aux[i].getNombre().toLowerCase().startsWith(texto.toLowerCase())) {
                    lista.add(aux[i]);
                }
            }
        }
        return lista;
    }
    public LinkedList<ProyectoEnergia> buscar_inversion(Double texto) {
        LinkedList<ProyectoEnergia> proyectos = new LinkedList<>();
        LinkedList<ProyectoEnergia> listita = listAll();
        if (!listita.isEmpty()) {
            ProyectoEnergia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].getInversion() == texto) {
                    proyectos.add(aux[i]);
                }
            }
        }
        return proyectos;
    }
    
    public LinkedList<ProyectoEnergia> buscar_tiempoVida(Integer texto) {
        LinkedList<ProyectoEnergia> proyectos = new LinkedList<>();
        LinkedList<ProyectoEnergia> listita = listAll();
        if (!listita.isEmpty()) {
            ProyectoEnergia[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                if (aux[i].getTiempoVida().equals(texto)) {
                    proyectos.add(aux[i]);
                }
            }
        }
        return proyectos;
    }

    public int busqueda_binaria(String atributo, Object valor) {
        // Primero, aseguramos que la lista esté ordenada
        LinkedList<ProyectoEnergia> listaOrdenada = order(1, atributo); // Orden ascendente
        ProyectoEnergia[] listaArray = (ProyectoEnergia[]) listaOrdenada.toArray();
    
        // Realizamos la búsqueda binaria en el array
        int izquierda = 0, derecha = listaArray.length - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            ProyectoEnergia proyecto = listaArray[medio];
            Object valorAtributo = getValorAtributo(proyecto, atributo);
    
            // Si encontramos el valor, retornamos el índice
            if (valorAtributo.equals(valor)) {
                return medio;
            }
    
            // Si el valor en medio es menor que el valor buscado, buscamos a la derecha
            if (((Comparable) valorAtributo).compareTo(valor) < 0) {
                izquierda = medio + 1;
            } else { // Si es mayor, buscamos a la izquierda
                derecha = medio - 1;
            }
        }
    
        // Si no se encuentra el valor, retornamos -1
        return -1;
    }

    private Object getValorAtributo(ProyectoEnergia proyecto, String atributo) {
        if (atributo.equalsIgnoreCase("nombre")) {
            return proyecto.getNombre();
        } else if (atributo.equalsIgnoreCase("inversion")) {
            return proyecto.getInversion();
        } else if (atributo.equalsIgnoreCase("tiempovida")) {
            return proyecto.getTiempoVida();
        } else {
            throw new IllegalArgumentException("Atributo no reconocido");
        }
    }

    public LinkedList<ProyectoEnergia> ordenar(String atributo, Integer tipo, String algoritmo) throws Exception {
        LinkedList<ProyectoEnergia> lista = listAll();
        if (!lista.isEmpty()) {
            return lista.ordenar(atributo, tipo, algoritmo);
        }
        return new LinkedList<>();
    }
    
    

    
    
    
    
    
}
