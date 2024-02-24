package org.example;

import java.time.LocalDate;

public class Tarea {
    private String descripcion;
    private LocalDate fechaLimite;
    private Estado estado;

    public Tarea(String descripcion, LocalDate fechaLimite) {
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.estado = Estado.PENDIENTE;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void completar() {
        setEstado(Estado.COMPLETADA);
    }

    @Override
    public String toString() {
        return "Tarea: " + descripcion + " | Fecha Límite: " + fechaLimite + " | Estado: " + estado;
    }
}