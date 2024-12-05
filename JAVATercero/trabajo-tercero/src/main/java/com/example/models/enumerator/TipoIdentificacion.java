package com.example.models.enumerator;


public enum TipoIdentificacion {
    CEDULA("CEDULA"), RUC("RUC"), PASAPORTE("PASAPORTE");
    private String name;

    TipoIdentificacion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static TipoIdentificacion fromString(String tipo) {
        for (TipoIdentificacion t : TipoIdentificacion.values()) {
            if (t.getName().equalsIgnoreCase(tipo)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Tipo de identificación no válido: " + tipo);
    }


}
