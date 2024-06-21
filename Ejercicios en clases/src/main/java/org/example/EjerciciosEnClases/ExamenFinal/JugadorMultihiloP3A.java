package org.example.EjerciciosEnClases.ExamenFinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class JugadorMultihiloP3A {
    public static void main(String[] args) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");

            // Establecer la conexión con el puerto del servidor 5056
            Socket s = new Socket(ip, 5056);

            // Obtener flujos de entrada y salida
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // Hilo para leer mensajes del servidor (preguntas y actualizaciones del marcador)
            Thread leerMensaje = new Thread(() -> {
                while (true) {
                    try {
                        String msg = dis.readUTF();
                        System.out.println(msg);
                        if (msg.equalsIgnoreCase("fin del juego")) {
                            System.out.println("El juego ha terminado. Cerrando conexión.");
                            dis.close();
                            dos.close();
                            s.close();
                            System.exit(0);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            });

            // Iniciar el hilo de lectura
            leerMensaje.start();

            // Loop principal para enviar respuestas
            while (true) {
                System.out.println("Tu respuesta (escribe 'salir' para terminar): ");
                String respuesta = sc.nextLine();
                dos.writeUTF(respuesta);

                if (respuesta.equalsIgnoreCase("salir")) {
                    System.out.println("Cerrando esta conexión: " + s);
                    s.close();
                    System.out.println("Conexión cerrada");
                    break;
                }
            }

            // Cerrando recursos
            sc.close();
            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
