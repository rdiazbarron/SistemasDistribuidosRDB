package org.example.EjerciciosEnClases.ExamenFinal;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class JugadorMultihiloP3B {
    public static void main(String[] args) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);
            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            System.out.println("Bienvenido al juego de preguntas y respuestas, espera la pregunta del servidor");
                    while (true) {
                        try {
                            String mensaje = dis.readUTF();
                            System.out.println("Servidor pregunta:  " + mensaje);
                            System.out.println("Respuesta: ");
                            String mensaje2 = sc.nextLine();
                            dos.writeUTF(mensaje2);
                            if(mensaje.equals("salir")){
                                System.out.println("Cerrando esta conexión : " + s);
                                s.close();
                                System.out.println("Conexión cerrada");
                                break;
                            }
                            String mensaje3 = dis.readUTF();
                            System.out.println("Servidor dice  resutlado de tu respuesta es:  " + mensaje3);


                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            catch (Exception e) {

        }}}