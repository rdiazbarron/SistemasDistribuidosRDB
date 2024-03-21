package org.example.EjerciciosEnClases;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServerMultihiloP3 {
    public static List<ManejadorJugadorP3> clientesConectados = Collections.synchronizedList(new ArrayList<>());
    public static Map<ManejadorJugadorP3, Integer> puntuaciones = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056);

        while (true) {
            try {

                Socket s = ss.accept();
                System.out.println("Un nuevo jugador se ha conectado: " + s);

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                ManejadorJugadorP3 mc = new ManejadorJugadorP3(s, dis, dos);
                Thread t = new Thread(mc);

                clientesConectados.add(mc);
                puntuaciones.put(mc, 0); // Inicializar puntuación de cada jugador

                t.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
