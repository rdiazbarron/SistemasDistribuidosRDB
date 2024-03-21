package org.example.EjerciciosEnClases;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JugadorMultihiloP3V2A {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 5058); // Asegúrate de usar el puerto correcto
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            // Escuchar mensajes del servidor
            Thread escuchar = new Thread(() -> {
                try {
                    while (true) {
                        String mensaje = dis.readUTF();//recibe la pregunta del servidor
                        if (mensaje.startsWith("Puntuaciones finales")) {
                            System.out.println(mensaje);
                            break; // Fin del juego
                        } else {
                            System.out.println("Servidor pregunta! : "+mensaje);
                            System.out.print("Respuesta: ");
                            String respuesta = sc.nextLine();
                            dos.writeUTF(respuesta);//envia la respuesta al servidor
                            //respuesta correcta o incorrecta:
                            String respuestaServidor = dis.readUTF();
                            System.out.println("Respuesta resultado! : "+respuestaServidor);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            escuchar.start();

            try {
                escuchar.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Cerrar recursos al final
            sc.close();
            dis.close();
            dos.close();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
