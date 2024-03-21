package org.example.EjerciciosEnClases;

import java.io.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class ManejadorJugadorP3V2 implements Runnable {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    static int indicePreguntaActual = 0;
    final CyclicBarrier barrera;
    public static List<Pregunta> preguntas = Arrays.asList(
            new Pregunta("¿Cuál es la capital de Francia?", "París"),
            new Pregunta("¿En qué año llegó el hombre a la Luna?", "1969"),
            new Pregunta("¿Cuál es la capital de Perú?", "Lima"),
            new Pregunta("¿Cuál es la capital de Argentina?", "Buenos Aires")
    );

    public ManejadorJugadorP3V2(Socket s, DataInputStream dis, DataOutputStream dos, CyclicBarrier barrera) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.barrera = barrera;
    }

    @Override
    public void run() {
        try {
            // Asegura que el juego comience solo cuando todos los jugadores estén conectados.
            synchronized (ServerMultihiloP3V2.clientesConectados) {
                if (ServerMultihiloP3V2.clientesConectados.size() == 2) {
                    ManejadorJugadorP3V2.enviarPreguntaActualATodos();
                }
            }

            while (indicePreguntaActual < preguntas.size()) {
                String respuesta = dis.readUTF(); // Recibe la respuesta del cliente
                procesarRespuesta(respuesta); // Procesa la respuesta del cliente y envía feedback

                barrera.await(); // Espera a que todos los jugadores hayan respondido antes de continuar.

                // Este bloque garantiza que solo un hilo incremente el índice y envíe la siguiente pregunta.
                synchronized (ManejadorJugadorP3V2.class) {
                    if (indicePreguntaActual < preguntas.size() - 1) {
                        // Asegura que la pregunta se envíe solo una vez después de que todos los jugadores respondan.
                        if (barrera.getNumberWaiting() == 0) { // Esto verifica si es el último hilo en llegar a la barrera.
                            indicePreguntaActual++;
                            enviarPreguntaActualATodos();
                        }
                    } else if (barrera.getNumberWaiting() == 0) { // Verifica si es el último hilo en la última ronda.
                        // Enviar puntuaciones finales solo una vez.
                        enviarPuntuacionesFinales();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                this.dis.close();
                this.dos.close();
                this.s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void procesarRespuesta(String respuesta) throws IOException {
        boolean esCorrecta = respuesta.equalsIgnoreCase(preguntas.get(indicePreguntaActual).getRespuesta());
        if (esCorrecta) {
            ServerMultihiloP3V2.puntuaciones.merge(this, 1, Integer::sum);
            dos.writeUTF("Correcto!");
        } else {
            dos.writeUTF("Incorrecto. La respuesta correcta era: " + preguntas.get(indicePreguntaActual).getRespuesta());
        }

    }

    public static void enviarPreguntaActualATodos() {

        for (ManejadorJugadorP3V2 jugador : ServerMultihiloP3V2.clientesConectados) {
            try {
                jugador.dos.writeUTF(preguntas.get(indicePreguntaActual).getPregunta());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void enviarPuntuacionesFinales() throws IOException {
        for (ManejadorJugadorP3V2 jugador : ServerMultihiloP3V2.clientesConectados) {
            jugador.dos.writeUTF("Puntuaciones finales: " + ServerMultihiloP3V2.puntuaciones.get(jugador));
            jugador.s.close(); // Cierra la conexión una vez enviadas las puntuaciones finales
        }
    }
}
