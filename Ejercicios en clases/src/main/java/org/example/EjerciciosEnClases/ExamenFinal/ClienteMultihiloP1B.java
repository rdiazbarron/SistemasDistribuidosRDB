package org.example.EjerciciosEnClases.ExamenFinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMultihiloP1B {
    public static void main(String[] args) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);
            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            Thread leerMensaje = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String mensaje = dis.readUTF();
                            System.out.println("Servidor dice: " + mensaje);
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            });

            // Hilo para enviar mensajes al servidor
            Thread enviarMensaje = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String mensaje = sc.nextLine();
                        try {
                            dos.writeUTF(mensaje);
                            if(mensaje.equals("salir")){
                                System.out.println("Cerrando esta conexión : " + s);
                                s.close();
                                System.out.println("Conexión cerrada");
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            leerMensaje.start();
            enviarMensaje.start();
        } catch (Exception e) {
            e.printStackTrace();
        }}}