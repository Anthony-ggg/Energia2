package com.example.rest;

import java.sql.Date;
import java.util.Arrays;
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

import com.example.controls.dao.ProyectoEnergiaDao;
import com.example.controls.dao.services.PersonaServices;
import com.example.controls.dao.services.ProyectoEnergiaServices;
import com.example.controls.tda.list.LinkedList;
import com.google.gson.Gson;
import com.example.models.*;

@Path("energia")
public class ProyectoEnergiaApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProyectoEnergia() {
        HashMap map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
        map.put("msg", "OK");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[] {});

        }
        return Response.ok(map).build();
    }
    /*
     * @GET
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public Response getIt() {
     * HashMap mapa = new HashMap<>();
     * ProyectoEnergiaServices pd = new ProyectoEnergiaServices();
     * //PersonaServices pd = new PersonaServices();
     * String aux = "";
     * try {
     * pd.getProyectoEnergia().setNombre("Proyecto 1");
     * pd.getProyectoEnergia().setInversion(1000.0);
     * pd.getProyectoEnergia().setTiempoVida(10);
     * pd.getProyectoEnergia().setFechaInicio("12/12/2020");
     * pd.getProyectoEnergia().setFechaFin("12/12/2021");
     * pd.getProyectoEnergia().setCapacidadDiaria(100.0);
     * pd.save();
     * 
     * 
     * aux = "La lista esta vasia"+pd.listAll().isEmpty();
     * } catch (Exception e) {
     * System.out.println("Error"+e);
     * }
     * mapa.put("msg","Ok");
     * mapa.put("data", "test"+aux);
     * return Response.ok(mapa).build();
     * }
     */

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map) {
        HashMap res = new HashMap<>();

        /// TODO
        /// VALIDACION

        System.out.println("ASAS");
        ProyectoEnergiaDao ps = new ProyectoEnergiaDao();

        try {
            ps.getProyectoEnergia().setNombre(map.get("nombre").toString());
            ps.getProyectoEnergia().setInversion(Double.parseDouble(map.get("inversion").toString()));
            ps.getProyectoEnergia().setTiempoVida(Integer.parseInt(map.get("tiempoVida").toString()));
            ps.getProyectoEnergia().setFechaInicio(map.get("fechaInicio").toString());
            ps.getProyectoEnergia().setFechaFin(map.get("fechaFin").toString());
            ps.getProyectoEnergia().setCapacidadDiaria(Double.parseDouble(map.get("capacidadDiaria").toString()));

            System.out.println("ASs");

            ps.save();
            res.put("msg", "OK");
            res.put("data", "Proyecto Registarado");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.BAD_REQUEST).entity(res).build();

        }

    }

    @Path("/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyectoEnergia(@PathParam("id") Integer id) {
        HashMap map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();

        try {
            ps.setProyectoEnergia(ps.get(id));
        } catch (Exception e) {

        }
        map.put("msg", "Ok");
        map.put("data", ps.getProyectoEnergia());

        if (ps.getProyectoEnergia().getId() == null) {

            map.put("data", "Persona no encontrada");
            return Response.status(Status.BAD_REQUEST).entity(map).build();
        }

        return Response.ok(map).build();

    }

    @Path("/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(HashMap map) {
        HashMap res = new HashMap<>();

        /// TODO
        /// VALIDACION

        System.out.println("ASAS");

        try {
            ProyectoEnergiaDao ps = new ProyectoEnergiaDao();
            ps.setProyectoEnergia(ps.get(Integer.parseInt(map.get("id").toString())));
            ps.getProyectoEnergia().setNombre(map.get("nombre").toString());
            ps.getProyectoEnergia().setInversion(Double.parseDouble(map.get("inversion").toString()));
            ps.getProyectoEnergia().setTiempoVida(Integer.parseInt(map.get("tiempoVida").toString()));
            ps.getProyectoEnergia().setFechaInicio(map.get("fechaInicio").toString());
            ps.getProyectoEnergia().setFechaFin(map.get("fechaFin").toString());
            ps.getProyectoEnergia().setCapacidadDiaria(Double.parseDouble(map.get("capacidadDiaria").toString()));

            System.out.println("ASs");

            ps.update();
            res.put("msg", "OK");
            res.put("data", "Proyecto Registarado");
            return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error" + e.toString());
            res.put("msg", "Error");
            res.put("data", e.toString());
            return Response.status(Status.BAD_REQUEST).entity(res).build();

        }

    }

    /*
     * @Path("/list/search/{texto}")
     * 
     * @GET
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public Response getProyecto(@PathParam("texto") String texto) {
     * HashMap map = new HashMap<>();
     * ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
     * map.put("msg", "Ok");
     * LinkedList lista = ps.buscar_nombre(texto);
     * map.put("data", lista.toArray());
     * if (lista.isEmpty()) {
     * map.put("data", new Object[] {});
     * }
     * return Response.ok(map).build();
     * }
     * 
     * @Path("/list/search/inversion/{texto}")
     * 
     * @GET
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public Response getProyectoInver(@PathParam("texto") double texto) {
     * HashMap<String, Object> map = new HashMap<>();
     * ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
     * map.put("msg", "Ok");
     * 
     * LinkedList<ProyectoEnergia> proyectos = ps.buscar_inversion(texto);
     * if (proyectos.isEmpty()) {
     * map.put("data", "No existen proyectos con ese monto de inversión");
     * return Response.status(Status.BAD_REQUEST).entity(map).build();
     * }
     * 
     * map.put("data", proyectos.toArray()); // Devuelve todos los proyectos que
     * coinciden con la inversión
     * return Response.ok(map).build();
     * }
     * 
     * @Path("/list/search/tiempoVida/{texto}")
     * 
     * @GET
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public Response getProyectoTiempoVida(@PathParam("texto") Integer texto) {
     * HashMap<String, Object> map = new HashMap<>();
     * ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
     * map.put("msg", "Ok");
     * 
     * LinkedList<ProyectoEnergia> proyectos = ps.buscar_tiempoVida(texto);
     * if (proyectos.isEmpty()) {
     * map.put("data", "No existen proyectos con ese tiempo de vida");
     * return Response.status(Status.BAD_REQUEST).entity(map).build();
     * }
     * 
     * map.put("data", proyectos.toArray()); // Devuelve todos los proyectos con el
     * mismo tiempo de vida
     * return Response.ok(map).build();
     * }
     */
    @Path("/list/search/{tipoBusqueda}/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyecto(@PathParam("tipoBusqueda") String tipoBusqueda, @PathParam("texto") String texto) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();

        map.put("msg", "Ok");

        LinkedList<ProyectoEnergia> lista;

        // Verificar si el tipo de búsqueda es binaria o lineal
        if ("binaria".equalsIgnoreCase(tipoBusqueda)) {
            // Aquí realizamos una búsqueda binaria, pero retornamos todos los proyectos que
            // coinciden.
            lista = ps.buscar_nombre(texto); // Esto buscará todos los proyectos que coincidan con "texto".
            if (lista.isEmpty()) {
                map.put("data", new Object[] {}); // Si no se encuentra nada, devolvemos un arreglo vacío.
            } else {
                map.put("data", lista.toArray()); // Convertimos la lista a un arreglo y lo devolvemos.
            }
        } else {
            // Se realiza la búsqueda lineal
            lista = ps.buscar_nombre(texto);
            if (lista.isEmpty()) {
                map.put("data", new Object[] {}); // Si no se encuentra nada, devolvemos un arreglo vacío.
            } else {
                map.put("data", lista.toArray());
            }
        }

        return Response.ok(map).build();
    }

    @Path("/list/search/inversion/{tipoBusqueda}/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyectoInver(@PathParam("tipoBusqueda") String tipoBusqueda, @PathParam("texto") double texto) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();

        map.put("msg", "Ok");

        LinkedList<ProyectoEnergia> proyectos;

        // Verificar si la búsqueda es binaria o lineal
        if ("binaria".equalsIgnoreCase(tipoBusqueda)) {
            // Realizamos la búsqueda binaria, pero retornamos todos los proyectos que
            // coinciden con el texto.
            proyectos = ps.buscar_inversion(texto); // Esto buscará todos los proyectos con la inversión proporcionada.
            if (proyectos.isEmpty()) {
                map.put("data", "No existen proyectos con esa inversión");
                return Response.status(Status.BAD_REQUEST).entity(map).build();
            }
            map.put("data", proyectos.toArray()); // Convertimos la lista a un arreglo y lo devolvemos.
        } else {
            // Realizar búsqueda lineal para la inversión
            proyectos = ps.buscar_inversion(texto);
            if (proyectos.isEmpty()) {
                map.put("data", "No existen proyectos con ese monto de inversión");
                return Response.status(Status.BAD_REQUEST).entity(map).build();
            }
            map.put("data", proyectos.toArray());
        }

        return Response.ok(map).build();
    }

    @Path("/list/search/tiempoVida/{tipoBusqueda}/{texto}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyectoTiempoVida(@PathParam("tipoBusqueda") String tipoBusqueda,
            @PathParam("texto") Integer texto) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();

        map.put("msg", "Ok");

        LinkedList<ProyectoEnergia> proyectos;

        // Verificar si la búsqueda es binaria o lineal
        if ("binaria".equalsIgnoreCase(tipoBusqueda)) {
            // Realizamos la búsqueda binaria, pero retornamos todos los proyectos que
            // coinciden con el tiempo de vida.
            proyectos = ps.buscar_tiempoVida(texto); // Esto buscará todos los proyectos con el tiempo de vida
                                                     // proporcionado.
            if (proyectos.isEmpty()) {
                map.put("data", "No existen proyectos con ese tiempo de vida");
                return Response.status(Status.BAD_REQUEST).entity(map).build();
            }
            map.put("data", proyectos.toArray()); // Convertimos la lista a un arreglo y lo devolvemos.
        } else {
            // Realizar búsqueda lineal para el tiempo de vida
            proyectos = ps.buscar_tiempoVida(texto);
            if (proyectos.isEmpty()) {
                map.put("data", "No existen proyectos con ese tiempo de vida");
                return Response.status(Status.BAD_REQUEST).entity(map).build();
            }
            map.put("data", proyectos.toArray());
        }

        return Response.ok(map).build();
    }

    @Path("/list/order/{attribute}/{type}/{algoritmo}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderProyectos(@PathParam("attribute") String attribute, @PathParam("type") Integer type,
            @PathParam("algoritmo") String algoritmo) {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoEnergiaServices ps = new ProyectoEnergiaServices();
        map.put("msg", "OK");
        System.out.println("Algoritmo recibido: " + algoritmo);

        try {
            LinkedList<ProyectoEnergia> listaaOrdenada = ps.ordenar(attribute, type, algoritmo);

            if (listaaOrdenada != null && !listaaOrdenada.isEmpty()) {
                map.put("data", listaaOrdenada.toArray());
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