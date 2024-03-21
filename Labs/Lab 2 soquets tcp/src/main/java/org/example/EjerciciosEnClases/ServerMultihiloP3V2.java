package org.example.EjerciciosEnClases;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ServerMultihiloP3V2 {
    public static List<ManejadorJugadorP3V2> clientesConectados = new ArrayList<>();
    public static Map<ManejadorJugadorP3V2, Integer> puntuaciones = new ConcurrentHashMap<>();
    public static CyclicBarrier nuevaPregunta;
    public static int indicePreguntaActual = 0;
    public static List<Pregunta> preguntas = Arrays.asList(
            new Pregunta("¿Cuál es la capital de Francia?", "Paris"),
            new Pregunta("¿En qué año llegó el hombre a la Luna?", "1969"),
            new Pregunta("¿Cuál es la capital de Perú?", "Lima")
    );

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5058);

        nuevaPregunta = new CyclicBarrier(2);

        while (true) {
            Socket s = ss.accept();
            System.out.println("un nuevo cliente se ha conectado..." + s);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            ManejadorJugadorP3V2 mc = new ManejadorJugadorP3V2(s, dis, dos, nuevaPregunta);
            Thread t = new Thread(mc);

            System.out.println("Asignando un nuevo hilo para este cliente...");
            clientesConectados.add(mc);
            puntuaciones.put(mc, 0);
            t.start();
        }
    }
}