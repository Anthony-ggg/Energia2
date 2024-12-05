package com.example.rest;

import java.util.HashMap;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.example.controls.tda.list.LinkedList;
import com.example.controls.dao.services.PersonaServices;
import com.example.models.*;
import com.google.gson.Gson;

@Path("person")
public class PersonaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getallPersons() {
        HashMap map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        map.put("msg", "OK");
        map.put("data", ps.listAll().toArray());
       

        if  (ps.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }

    /*
     * @Path ("/list")
     * 
     * @GET
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public Response save (HashMap map){
     * //todo
     * //validar
     * PersonaServices ps = new PersonaServices();
     * ps.getPersona().setApellido(map.get(("apellido")).toString());
     * ps.getPersona().setNombre(map.get(("nombre")).toString());
     * ps.getPersona().setDni(map.get(("dni")).toString());
     * ps.getPersona().setFechaNacimiento(map.get(("fechaNacimiento")).toString());
     * ps.getPersona().setNumCelular(map.get(("numCelular")).toString());
     * ps.getPersona().setSexo(map.get(("seco")).toString());
     * ps.getPersona().setTipo(map.get(("tipo")).toString());
     * 
     * 
     * HashMap<String, String> response = new HashMap<>();
     * response.put("msg", "Persona saved successfully");
     * return Response.ok(response).build();
     * }
     */

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        // TODO
        // VALIDATION ------BAD RESQUEST
        PersonaServices ps = new PersonaServices();
        ps.getPersona().setApellido(map.get(("apellido")).toString());
        ps.getPersona().setNombre(map.get(("nombre")).toString());
        ps.getPersona().setDni(map.get(("dni")).toString());
        ps.getPersona().setNumCelular(map.get(("numCelular")).toString());
        ps.getPersona().setFechaNacimiento(map.get(("fechaNacimiento")).toString());
        ps.getPersona().setSexo(map.get(("sexo")).toString());
        ps.getPersona().setTipo(map.get("tipo").toString());
        HashMap res = new HashMap<>();

        try {
            ps.save();
            res.put("msg", "ok");
            res.put("data", "Persona registrada");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Erroreeeeeeeeeeeeee" + e.toString());
            res.put("msg", "ERROR");
            res.put("data", e.toString());
            return Response.status(Status.BAD_REQUEST).entity(res).build();

        }

        // TODO
        // VALIDATION ------BAD RESQUEST
        // ps.getPersona().setApellido(map.get(("apellido")).toString());
        // ps.getPersona().setNombre(map.get(("nombre")).toString());
        // ps.getPersona().setDni(map.get(("dni")).toString());
        // ps.getPersona().setNumCelular(map.get(("celular")).toString());
        // ps.getPersona().setFechaNacimiento(map.get(("fechaNacimiento")).toString());
        // ps.getPersona().setSexo(map.get(("sexo")).toString());
        // ps.getPersona().setTipo(ps.getTipoIdentificacion(map.get("tipo").toString()));

    }

    @Path("/listType")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getType() {
        HashMap map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        map.put("msg", "OK");
        return Response.ok(map).build();
    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        try {
            ps.setPersona(ps.get(id));
        } catch (Exception e) {
            
        }
        map.put("msg", "Ok");
        map.put("data", ps.getPersona());
        
        if (ps.getPersona().getId() == null) {
            
            map.put("data", "Persona no encontrada");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
        
        return Response.ok(map).build();

    }
    @Path("/list/search/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsLastName(@PathParam ("texto")String texto) {
        HashMap map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        map.put("msg", "Ok");
        try {
            LinkedList lista = ps.buscarPorApellidos(texto);
            map.put("data", lista.toArray());
            if (lista.isEmpty()) {
                map.put("data", new Object[]{});
            }
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "ERROR");
            map.put("data", e.toString());
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
    }
@Path("/list/search/{attribute}/{texto}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response searchPerson(@PathParam("attribute") String attribute, @PathParam("texto") String texto) {
    HashMap<String, Object> map = new HashMap<>();
    PersonaServices ps = new PersonaServices();
    map.put("msg", "Ok");

    try {
        LinkedList lista = ps.buscarPorAtributo(attribute, texto); // Método genérico para buscar
        map.put("data", lista.toArray());

        if (lista.isEmpty()) {
            map.put("data", new Object[] {});
            return Response.ok(map).build();
        }

        return Response.ok(map).build();
    } catch (Exception e) {
        map.put("msg", "ERROR");
        map.put("data", e.toString());
        return Response.status(Status.BAD_REQUEST).entity(map).build();
    }
}

    @Path("/list/search/ident/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonsIdent(@PathParam ("texto")String texto) {
        HashMap map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        map.put("msg", "Ok");

        try {
            ps.setPersona(ps.buscarPorDni(texto));
            map.put("data",ps.getPersona());
            if (ps.getPersona().getId() == null) {
                map.put("data", "No existe la persona con ese identificador");
                return Response.status(Status.BAD_REQUEST).entity(map).build();
            }
            return Response.ok(map).build();
        } catch (Exception e) {
            map.put("msg", "ERROR");
            map.put("data", e.toString());
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
    }

   
    
    @Path("/list/order/{attribute}/{type}/{algoritmo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderPersons(@PathParam("attribute") String attribute, @PathParam("type") Integer type, @PathParam("algoritmo") String algoritmo) {
        HashMap<String, Object> map = new HashMap<>();
        PersonaServices ps = new PersonaServices();
        map.put("msg", "OK");
        System.out.println("Algoritmo recibido: " + algoritmo);

        try {
            LinkedList<Persona> listaOrdenada = ps.ordenar(attribute, type, algoritmo);

            if (listaOrdenada != null && !listaOrdenada.isEmpty()) {
                map.put("data", listaOrdenada.toArray());
            } else {
                map.put("data", new Object[] {});
            }
            System.out.println("Algoritmo recibdsfdido: " + algoritmo);
            return Response.ok(map).build();
        } catch (IllegalArgumentException e) {
            map.put("msg", "ERROR");
            map.put("data", "Algoritmo no reconocido. Intenta con 'shellsort', 'quicksort' o 'mergesort'.");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        } catch (Exception e) {
            map.put("msg", "ERROR");
            map.put("data", e.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }
    }
    
    

}