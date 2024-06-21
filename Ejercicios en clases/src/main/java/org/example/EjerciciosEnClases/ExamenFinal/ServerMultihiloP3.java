package org.example.EjerciciosEnClases.ExamenFinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServerMultihiloP3 {
    public static List<ManejadorJugadorP3> clientesConectados = new ArrayList<>();
    public static List<Pregunta> preguntas = Arrays.asList(
            new Pregunta("¿Cuál es la capital de Francia?", "París"),
            new Pregunta("¿En qué año llegó el hombre a la Luna?", "1969")

    );
    public static Map<ManejadorJugadorP3, Integer> puntuaciones = new ConcurrentHashMap<>();
    static int indicePreguntaActual = 0;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056);

        while (true) {
            Socket s = null;
            try {
                s = ss.accept();
                System.out.println("Un nuevo jugador se ha conectado: " + s);

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                ManejadorJugadorP3 mc = new ManejadorJugadorP3(s, dis, dos);
                Thread t = new Thread(mc);

                clientesConectados.add(mc);
                puntuaciones.put(mc, 0); // Inicializar puntuación del jugador
                t.start();

                if(clientesConectados.size() == 2) { // Comenzar juego cuando al menos hay un jugador
                    iniciarJuego();
                }
            } catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }

    public static void iniciarJuego() throws IOException {
        for (int i = 0; i < preguntas.size(); i++) {
            for (ManejadorJugadorP3 cliente : clientesConectados) {
                cliente.enviarPregunta(preguntas.get(i).getPregunta());
            }
            // Esperar a que todos respondan o agregar algún mecanismo de temporización
        }
        // Envío de puntuaciones finales
    }
}
