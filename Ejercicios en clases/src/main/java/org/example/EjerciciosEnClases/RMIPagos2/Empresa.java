package org.example.EjerciciosEnClases.RMIPagos2;

import java.io.Serializable;

public class Empresa implements Serializable {
    public String nombre;
    public long nit;

    public Empresa(String nombre, long nit) {
        this.nombre = nombre;
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNit() {
        return nit;
    }

    public void setNit(long nit) {
        this.nit = nit;
    }
}
