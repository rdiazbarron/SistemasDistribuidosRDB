package org.example.EjerciciosEnClases;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;

public class ManejadorJugadorP3 implements Runnable {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    public ManejadorJugadorP3(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    public void run() {
        try {
            // Enviar preguntas y esperar respuestas
            for (Pregunta pregunta : preguntas) {
                dos.writeUTF(pregunta.getPregunta());
                String respuesta = dis.readUTF();
                if (respuesta.equalsIgnoreCase(pregunta.getRespuesta())) {
                    int puntos = ServerMultihiloP3.puntuaciones.getOrDefault(this, 0) + 1;
                    ServerMultihiloP3.puntuaciones.put(this, puntos);
                    dos.writeUTF("Correcto! Tu puntuación es: " + puntos);
                } else {
                    dos.writeUTF("Incorrecto. La respuesta correcta es: " + pregunta.getRespuesta());
                }
            }
            dos.writeUTF("Fin del juego. Tu puntuación final es: " + ServerMultihiloP3.puntuaciones.get(this));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
                dos.close();
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final List<Pregunta> preguntas = Arrays.asList(
            new Pregunta("¿Cuál es la capital de Francia?", "París"),
            new Pregunta("¿En qué año llegó el hombre a la Luna?", "1969"),
            new Pregunta("¿Cuál es la capital de Perú?", "Lima"),
            new Pregunta("¿Cuál es la capital de Argentina?", "Buenos Aires")
    );
}
