package org.example.EjerciciosEnClases.ExamenFinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ManejadorJugadorP3 implements Runnable {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    public ManejadorJugadorP3(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String respuesta = dis.readUTF();
                procesarRespuesta(respuesta, this);
            }
        } catch (IOException e) {
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
    private void procesarRespuesta(String respuesta, ManejadorJugadorP3 jugador) throws IOException {
        if (respuesta.equalsIgnoreCase(ServerMultihiloP3.preguntas.get(ServerMultihiloP3.indicePreguntaActual).getRespuesta())) {
            ServerMultihiloP3.puntuaciones.merge(jugador, 1, Integer::sum);
            jugador.dos.writeUTF("Correcto! Tu puntuación es: " + ServerMultihiloP3.puntuaciones.get(jugador));
        } else {
            jugador.dos.writeUTF("Incorrecto. La respuesta correcta era: " + ServerMultihiloP3.preguntas.get(ServerMultihiloP3.indicePreguntaActual).getRespuesta());
        }
        ServerMultihiloP3.indicePreguntaActual++;
        // Verificar fin del juego
        if (ServerMultihiloP3.indicePreguntaActual >= ServerMultihiloP3.preguntas.size()) {
            jugador.dos.writeUTF("Fin del juego.");
            // Enviar puntuaciones finales a todos
            enviarPuntuacionesFinales();
        }
    }

    private static void enviarPuntuacionesFinales() throws IOException {
        for (ManejadorJugadorP3 jugador : ServerMultihiloP3.clientesConectados) {
            jugador.dos.writeUTF("Puntuaciones finales: " + ServerMultihiloP3.puntuaciones.entrySet());
        }
    }

    public void enviarPregunta(String pregunta) throws IOException {
        dos.writeUTF(pregunta);
    }
}
