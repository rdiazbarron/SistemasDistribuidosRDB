package org.example.EjerciciosEnClases.RMIGestorAlumnos;

import java.io.Serializable;

public class Alumno implements Serializable {
    public String nombre;
    public String carrera;
    public int carnet;

    public Alumno(String nombre, String carrera, int carnet) {
        this.nombre = nombre;
        this.carrera = carrera;
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", carrera='" + carrera + '\'' +
                ", carnet=" + carnet +
                '}';
    }
}
