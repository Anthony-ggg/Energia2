package com.example.controls.tda.list;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.example.controls.exception.ListEmptyException;

public class LinkedList<E> {
    private Node<E> header;
    private Node<E> last;
    private Integer size;

    public LinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    public Boolean isEmpty() {
        return (this.header == null || this.size == 0);
    }

    private void addHeader(E dato) {
        Node<E> help;
        if (isEmpty()) {
            help = new Node<>(dato);
            header = help;
            this.size++;
        } else {
            Node<E> healpHeader = this.header;
            help = new Node<>(dato, healpHeader);
            this.header = help;
        }
        this.size++;
    }

    private void addLast(E info) {
        Node<E> help;
        if (isEmpty()) {
            help = new Node<>(info);
            header = help;
            last = help;
        } else {
            help = new Node<>(info, null);
            last.setNext(help);
            last = help;
        }
        this.size++;
    }

    public void add(E info) {
        addLast(info);
    }

    private Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, List empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == (this.size - 1)) {
            return last;
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search;
        }
    }

    private E getFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        }
        return last.getInfo();
    }

    public E getLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, Lista Vacia");
        }
        return last.getInfo();
    }

    public E get(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, list empty");
        } else if (index.intValue() < 0 || index.intValue() >= this.size.intValue()) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (index.intValue() == 0) {
            return header.getInfo();
        } else if (index.intValue() == (this.size - 1)) {
            return last.getInfo();
        } else {
            Node<E> search = header;
            int cont = 0;
            while (cont < index.intValue()) {
                cont++;
                search = search.getNext();
            }
            return search.getInfo();
        }
    }

    public void add(E info, Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if (isEmpty() || index.intValue() == 0) {
            addHeader(info);
        } else if (index.intValue() == this.size.intValue()) {
            addLast(info);
        } else {
            Node<E> search_preview = getNode(index - 1);
            Node<E> search = getNode(index);
            Node<E> help = new Node<>(info, search);
            search_preview.setNext(help);
            this.size++;
        }
    }

    /*** END BYPOSITION */
    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("List data");
        try {
            Node<E> help = header;
            while (help != null) {
                sb.append(help.getInfo()).append(" ->");
                help = help.getNext();
            }
        } catch (Exception e) {
            sb.append(e.getMessage());
        }
        return sb.toString();
    }

    public Integer getSize() {
        return this.size;
    }

    public Node<E> getHeader() {
        return header;
    }

    public E[] toArray() {
        E[] matrix = null;
        if (!isEmpty()) {
            Class clazz = header.getInfo().getClass();
            matrix = (E[]) java.lang.reflect.Array.newInstance(clazz, size);
            Node<E> aux = header;
            for (int i = 0; i < this.size; i++) {
                matrix[i] = aux.getInfo();
                aux = aux.getNext();
            }

        }
        return matrix;
    }

    public LinkedList<E> toList(E[] matrix) {
        reset();
        for (int i = 0; i < matrix.length; i++) {
            this.add(matrix[i]);
        }
        return this;
    }

    public void update(E data, Integer post) throws Exception {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, fuera de rango");
        } else if (post == 0) {
            header.setInfo(data);
        } else if (post == (size - 1)) {
            last.setInfo(data);
        } else {
            Node<E> search = header;
            Integer cont = 0;
            while (cont < post) {
                cont++;
                search = search.getNext();
            }
            search.setInfo(data);
        }

    }

    public E deleteFirst() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        } else {
            E element = header.getInfo();
            Node<E> aux = header.getNext();
            header = aux;
            if (size.intValue() == 1) {
                last = null;
            }
            size--;
            return element;
        }
    }

    public E deleteLast() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        } else {
            E element = last.getInfo();
            Node<E> aux = getNode(size - 2);
            if (aux == null) {
                last = null;
                if (size == 2) {
                    last = header;
                } else {
                    header = null;
                }
            } else {
                last = null;
                last = aux;
                last.setNext(null);
            }
            size--;
            return element;
        }
    }

    public E delete(Integer post) throws Exception {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        } else if (post < 0 || post >= size) {
            throw new IndexOutOfBoundsException("Error, esta fuera de los limites de la lista");
        } else if (post == 0) {
            return deleteFirst();
        } else if (post == (size - 1)) {
            return deleteLast();
        } else {
            Node<E> preview = getNode(post - 1);
            Node<E> actually = getNode(post);
            E element = preview.getInfo();
            Node<E> next = actually.getNext();
            actually = null;
            preview.setNext(next);
            size--;
            return element;
        }
    } 

    public LinkedList<E> order(String attribute, Integer type) throws Exception {
        if (!isEmpty()) {
            E data = this.header.getInfo();
            if (data instanceof Object) {
                E[] lista = this.toArray();
                reset();
                for (int i = 1; i < lista.length; i++) {
                    E aux = lista[i]; //valor a ordenar
                    int j = i - 1;// indice anterior
                    while (j >= 0 && atrribute_compare(attribute,lista[j], aux,type)) {
                        lista[j + 1] = lista[j--];
                    }
                    lista[j + 1] = aux;
                }
                this.toList(lista);
            } else {
                System.out.println("Error, no se puede ordenar la lista");
            }
        }
        return this;
    }

    private Boolean compare(Object a, Object b, Integer type) {
        switch (type) {
            case 0:
                if(a instanceof Number){
                    Number a1 = (Number) a;
                    Number b1 = (Number) b;
                    return a1.doubleValue() > b1.doubleValue();
                }else{
                    // "casa" > "pedro"
                    return a.toString().compareTo(b.toString()) > 0;
                }
                //break;
        
            default:
                //mayor a menor
                if(a instanceof Number){
                    Number a1 = (Number) a;
                    Number b1 = (Number) b;
                    return a1.doubleValue() < b1.doubleValue();
                }else{
                    // "casa" < "pedro"
                    return a.toString().compareTo(b.toString()) < 0;
                }
                //break;
        }
    }
    // compare class
    private Boolean atrribute_compare(String attribute, E a, E b, Integer type) throws Exception {
        return compare(exist_attribute(a, attribute), exist_attribute(b, attribute), type);
    }

    public Object exist_attribute(E a, String attribute) throws Exception {
        Method method = null;
        attribute = attribute.substring(0,1).toUpperCase() + attribute.substring(1);
        attribute = "get" + attribute;
        for (Method aux : a.getClass().getMethods()){
            if (aux.getName().contains(attribute)){
                method = aux;
                break;
            }
        }
        if (method == null){
            for(Method aux: a.getClass().getSuperclass().getMethods()){
                if(aux.getName().contains(attribute)){
                    method = aux;
                    break;
                }
            } 
        }
        if (method != null){
            return method.invoke(a);
        }
        return null;


    }
///ordenamiento

public LinkedList<E> ordenar(String attribute, Integer type, String algoritmo) throws Exception {
    if (isEmpty()) {
        throw new Exception("La lista está vacía. No se puede ordenar.");
    }

    E[] lista = this.toArray();

    if (algoritmo.equalsIgnoreCase("shellsort")) {
        shellSort(lista, attribute, type);
    } else if (algoritmo.equalsIgnoreCase("quicksort")) {
        quickSort(lista, 0, lista.length - 1, attribute, type);
    } else if (algoritmo.equalsIgnoreCase("mergesort")) {
        lista = mergeSort(lista, attribute, type);
    } else {
        throw new Exception("Algoritmo no reconocido: " + algoritmo + ". Usa 'shellsort', 'quicksort' o 'mergesort'.");
    }

    // Reconstruir la lista enlazada con los elementos ordenados
    reset();
    for (E elemento : lista) {
        add(elemento);
    }

    return this; 
}


private void shellSort(E[] lista, String attribute, Integer type) throws Exception {
    int n = lista.length;
    for (int gap = n / 2; gap > 0; gap /= 2) {
        for (int i = gap; i < n; i++) {
            E temp = lista[i];
            int j;
            for (j = i; j >= gap && atrribute_compare(attribute, lista[j - gap], temp, type); j -= gap) {
                lista[j] = lista[j - gap];
            }
            lista[j] = temp;
        }
    }
}


private void quickSort(E[] lista, int low, int high, String attribute, Integer type) throws Exception {
    if (low < high) {
        int pi = particion(lista, low, high, attribute, type);
        quickSort(lista, low, pi - 1, attribute, type);
        quickSort(lista, pi + 1, high, attribute, type);
    }
}

private int particion(E[] lista, int low, int high, String attribute, Integer type) throws Exception {
    E pivot = lista[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
        if (!atrribute_compare(attribute, pivot, lista[j], type)) {
            i++;
            E temp = lista[i];
            lista[i] = lista[j];
            lista[j] = temp;
        }
    }
    E temp = lista[i + 1];
    lista[i + 1] = lista[high];
    lista[high] = temp;
    return i + 1;
}


private E[] mergeSort(E[] lista, String attribute, Integer type) throws Exception {
    if (lista.length <= 1) return lista;

    int mid = lista.length / 2;
    E[] izquierda = Arrays.copyOfRange(lista, 0, mid);
    E[] derecha = Arrays.copyOfRange(lista, mid, lista.length);

    izquierda = mergeSort(izquierda, attribute, type);
    derecha = mergeSort(derecha, attribute, type);

    return merge(izquierda, derecha, attribute, type);
}

private E[] merge(E[] izquierda, E[] derecha, String attribute, Integer type) throws Exception {
    E[] result = Arrays.copyOf(izquierda, izquierda.length + derecha.length);
    int i = 0, j = 0, k = 0;

    while (i < izquierda.length && j < derecha.length) {
        if (!atrribute_compare(attribute, izquierda[i], derecha[j], type)) {
            result[k++] = izquierda[i++];
        } else {
            result[k++] = derecha[j++];
        }
    }
    while (i < izquierda.length) result[k++] = izquierda[i++];
    while (j < derecha.length) result[k++] = derecha[j++];

    return result;
}

///metodoooss de busquedaa//
public int busqueda_lineal(E[] lista, String attribute, Object value) throws Exception {
    for (int i = 0; i < lista.length; i++) {
        if (exist_attribute(lista[i], attribute).equals(value)) {
            return i;
        }
    }
    return -1;
}

public int busqueda_binaria(E[] lista, String attribute, Object value) throws Exception {
    int izquierda = 0, derecha = lista.length - 1;
    while (izquierda <= derecha) {
        int mid = izquierda + (derecha - izquierda) / 2;
        Object midValue = exist_attribute(lista[mid], attribute);

        if (midValue.equals(value)) return mid;
        if (((Comparable) midValue).compareTo(value) < 0) {
            izquierda = mid + 1;
        } else {
            derecha = mid - 1;
        }
    }
    return -1;
}


    
}