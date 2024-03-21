package org.example.EjerciciosEnClases;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JugadorMultihiloP3B {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5056);
             DataInputStream dis = new DataInputStream(socket.getInputStream());
             DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            String delServer;
            while (true) {
                delServer = dis.readUTF();
                // Verificar si el mensaje del servidor indica el fin del juego
                if (delServer.startsWith("Fin del juego")) {
                    System.out.println(delServer); // Muestra el mensaje de fin de juego y puntuación
                    break; // Salir del bucle cuando el juego termina
                } else {
                    System.out.println(delServer); // Muestra la pregunta o el resultado de la respuesta anterior
                    System.out.print("Tu respuesta: ");
                    String respuesta = scanner.nextLine();
                    dos.writeUTF(respuesta); // Envía la respuesta al servidor
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
