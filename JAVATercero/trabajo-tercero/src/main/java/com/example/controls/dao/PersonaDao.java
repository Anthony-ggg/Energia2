package com.example.controls.dao;

import java.util.function.ToIntBiFunction;

import com.example.controls.dao.implement.AdapterDao;
import com.example.controls.tda.list.LinkedList;
import com.example.models.Persona;

public class PersonaDao extends AdapterDao<Persona> {
    private Persona persona;
    private LinkedList listAll;

    public PersonaDao() {
        super(Persona.class);
    }

    public Persona getPersona() {
        if (persona == null) {
            persona = new Persona();
        }
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public LinkedList getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize() + 1;
        persona.setId(id);
        this.persist(this.persona);
        this.listAll = listAll();
        return true;
    }

   /*  public LinkedList order(Integer type_order, String atributo) {
        LinkedList listita = listAll();
        if (!listAll().isEmpty()) {
            Persona[] lista = (Persona[]) listita.toArray();
            listita.reset();
            for (int i = 1; i < lista.length; i++) {
                Persona aux = lista[i];// valor a ordenar
                int j = i - 1;// indice anterior
                while (j >= 0 && (verify(lista[j], aux, type_order, atributo))) {
                    lista[j + 1] = lista[j--];// desplaza elementos hacia la derecha
                }
                lista[j + 1] = aux; // inserta el elemento en la posicion correcta
            }
            listita.toList(lista);
        }
        return listita;
    }

    private Boolean verify(Persona a, Persona b, Integer type_order, String atributo) {
        if (type_order == 1) {
            if (atributo.equalsIgnoreCase("apellido")) {
                return a.getApellido().compareTo(b.getApellido()) > 0;
            } else if (atributo.equalsIgnoreCase("nombre")) {
                return a.getNombre().compareTo(b.getNombre()) > 0;
            } else if (atributo.equalsIgnoreCase("id")) {
                return a.getId() > b.getId();
            }
        } else {
            if (atributo.equalsIgnoreCase("apellido")) {
                return a.getApellido().compareTo(b.getApellido()) < 0;
            } else if (atributo.equalsIgnoreCase("nombre")) {
                return a.getNombre().compareTo(b.getNombre()) < 0;
            } else if (atributo.equalsIgnoreCase("id")) {
                return a.getId() < b.getId();
            }

        }
        return false;
    }

    public LinkedList<Persona> buscar_apellidos(String texto) {
        LinkedList<Persona> lista = new LinkedList<>();
        LinkedList<Persona> listita = listAll();
        if (!listita.isEmpty()) {
            Persona[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {

                if (aux[i].getApellido().toLowerCase().startsWith(texto.toLowerCase())) {
                    // System.out.println("Encontrado" +aux[i].getApellido());
                    lista.add(aux[i]);
                }
            }

        }
        return lista;

    }

    public Persona buscar_identificacion(String texto) {
        Persona persona = null;
        LinkedList<Persona> listita = listAll();
        if (!listita.isEmpty()) {
            Persona[] aux = listita.toArray();
            for (int i = 0; i < aux.length; i++) {
                // equals para devolver un valor
                if (aux[i].getDni().equals(texto)) {
                    // System.out.println("Encontrado" +aux[i].getApellido());
                    persona = aux[i];
                    break;
                }
            }

        }
        return persona;

    } */

    public LinkedList<Persona> ordenar(String atributo, Integer tipo, String algoritmo) throws Exception {
        LinkedList<Persona> lista = listAll();
        if (!lista.isEmpty()) {
            return lista.ordenar(atributo, tipo, algoritmo);
        }
        return new LinkedList<>();
    }
    //busqueda por atributo lineal
    public LinkedList<Persona> buscarPorAtributo(String atributo, Object valor) throws Exception {
        LinkedList<Persona> resultados = new LinkedList<>();
        LinkedList<Persona> lista = listAll();
        if (!lista.isEmpty()) {
            Persona[] array = lista.toArray();
            for (Persona persona : array) {
                Object atributoValor = lista.exist_attribute(persona, atributo);
                if (atributoValor != null) {
                    if (atributoValor instanceof String && valor instanceof String) {
                        if (((String) atributoValor).toLowerCase().contains(((String) valor).toLowerCase())) {
                            resultados.add(persona);
                        }
                    } else if (atributoValor.equals(valor)) {
                        resultados.add(persona);
                    }
                }
            }
        }
        return resultados;
    }

   public Persona buscarBinaria(String atributo, Object valor) throws Exception {
    LinkedList<Persona> lista = listAll();
    if (!lista.isEmpty()) {
        lista.ordenar(atributo, null, atributo);
        Persona[] array = lista.toArray();
        int index = lista.busqueda_binaria(array, atributo, valor);
        if (index >= 0) {
            return array[index];
        }
    }
    return null;
}
    public LinkedList<Persona> buscarPorApellidos(String texto) throws Exception {
        LinkedList<Persona> resultados = new LinkedList<>();
        LinkedList<Persona> lista = listAll();
        if (!lista.isEmpty()) {
            Persona[] array = lista.toArray();
            for (Persona persona : array) {
                if (persona.getApellido().toLowerCase().startsWith(texto.toLowerCase())) {
                    resultados.add(persona);
                }
            }
        }
        return resultados;
    }
    public Persona buscarPorDni(String dni) throws Exception {
        return buscarBinaria("dni", dni);
    } 
  
    

}
