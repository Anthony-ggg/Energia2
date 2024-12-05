package com.example.rest;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controls.dao.PersonaDao;
import com.example.controls.dao.services.PersonaServices;

import java.util.Arrays;
import java.util.Random;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
        HashMap mapa = new HashMap<>();
        PersonaServices pd = new PersonaServices();
        //PersonaServices pd = new PersonaServices();
        String aux = "";
        try {
            pd.getPersona().setNombre("Pablo");
            pd.getPersona().setApellido("Morocho");
            pd.getPersona().setDni("1234567890");
            pd.getPersona().setNumCelular("0987654321");
            pd.getPersona().setFechaNacimiento("12/12/2020");
            pd.getPersona().setSexo("M");
            pd.getPersona().setTipo("Cedula");
            pd.save();
          
            aux = "La lista esta vasia"+pd.listAll().isEmpty();
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
        mapa.put("msg","Ok");
        mapa.put("data", "test"+aux);
        return Response.ok(mapa).build();
    }
    public static class SortingPerformanceTest {

        public static void main(String[] args) {
            // Tamaños de los arreglos a probar
            int[] sizes = {10000, 20000, 25000};
    
            // Probar cada tamaño de arreglo
            for (int size : sizes) {
                System.out.println("Prueba de los algoritmos: " + size);
    
                // Generar un arreglo aleatorio
                Integer[] originalArray = generateRandomArray(size);
    
                // Comparar los algoritmos
                PruebaAgoritmos(originalArray);
                System.out.println("---------------------------------------------------");
            }
        }
    
        private static Integer[] generateRandomArray(int size) {
            Random random = new Random();
            Integer[] array = new Integer[size];
            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(size * 10); // Números aleatorios hasta 10 veces el tamaño del arreglo
            }
            return array;
        }
    
        private static void PruebaAgoritmos(Integer[] originalArray) {
    try {
        // Probar ShellSort
        Integer[] shellArray = Arrays.copyOf(originalArray, originalArray.length);
        long start = System.currentTimeMillis();
        shellSort(shellArray);
        long end = System.currentTimeMillis();
        double timeInSeconds = (end - start) / 1000.0;
        System.out.printf("ShellSort time: %.5f seconds%n", timeInSeconds);

        // Probar QuickSort
        Integer[] quickArray = Arrays.copyOf(originalArray, originalArray.length);
        start = System.currentTimeMillis();
        quickSort(quickArray, 0, quickArray.length - 1);
        end = System.currentTimeMillis();
        timeInSeconds = (end - start) / 1000.0;
        System.out.printf("QuickSort time: %.5f seconds%n", timeInSeconds);

        // Probar MergeSort
        Integer[] mergeArray = Arrays.copyOf(originalArray, originalArray.length);
        start = System.currentTimeMillis();
        mergeSort(mergeArray);
        end = System.currentTimeMillis();
        timeInSeconds = (end - start) / 1000.0;
        System.out.printf("MergeSort time: %.5f seconds%n", timeInSeconds);
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}

        
    
        private static void shellSort(Integer[] array) {
            int n = array.length;
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    int temp = array[i];
                    int j;
                    for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                        array[j] = array[j - gap];
                    }
                    array[j] = temp;
                }
            }
        }
    
        private static void quickSort(Integer[] array, int low, int high) {
            if (low < high) {
                int pi = partition(array, low, high);
                quickSort(array, low, pi - 1);
                quickSort(array, pi + 1, high);
            }
        }
    
        private static int partition(Integer[] array, int low, int high) {
            int pivot = array[high];
            int i = low - 1;
            for (int j = low; j < high; j++) {
                if (array[j] <= pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;
            return i + 1;
        }
    
        private static void mergeSort(Integer[] array) {
            if (array.length <= 1) return;
    
            int mid = array.length / 2;
            Integer[] left = Arrays.copyOfRange(array, 0, mid);
            Integer[] right = Arrays.copyOfRange(array, mid, array.length);
    
            mergeSort(left);
            mergeSort(right);
    
            merge(array, left, right);
        }
    
        private static void merge(Integer[] array, Integer[] left, Integer[] right) {
            int i = 0, j = 0, k = 0;
    
            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    array[k++] = left[i++];
                } else {
                    array[k++] = right[j++];
                }
            }
    
            while (i < left.length) {
                array[k++] = left[i++];
            }
    
            while (j < right.length) {
                array[k++] = right[j++];
            }
        }
    } 
   

}
