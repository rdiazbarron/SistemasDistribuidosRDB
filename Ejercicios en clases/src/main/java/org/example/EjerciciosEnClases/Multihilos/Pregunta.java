package org.example.EjerciciosEnClases.Multihilos;

public class Pregunta {
    private String pregunta;
    private String respuesta;

    public Pregunta(String pregunta, String respuesta) {
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }
}