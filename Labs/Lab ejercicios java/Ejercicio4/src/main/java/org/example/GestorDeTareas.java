package org.example;

import java.util.ArrayList;
import java.util.List;

public class GestorDeTareas {
    private List<Tarea> tareas;


    public GestorDeTareas() {
        this.tareas = new ArrayList<>();
    }


    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
    }


    public void marcarTareaComoCompletada(String descripcion) {
        for (Tarea tarea : tareas) {
            if (tarea.getDescripcion().equals(descripcion)) {
                tarea.completar();
                return;
            }
        }
        System.out.println("Tarea no encontrada: " + descripcion);
    }


    public void mostrarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas pendientes.");
            return;
        }
        for (Tarea tarea : tareas) {
            System.out.println(tarea);
        }
    }
}