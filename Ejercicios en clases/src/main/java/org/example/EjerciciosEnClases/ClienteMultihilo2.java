package org.example.EjerciciosEnClases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMultihilo2 {
    public static void main(String[] args) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");

            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);

            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            String cadena;
            do{
                System.out.println("Introduce segun protocolo: salir / iniciar:sumar ");
                cadena = sc.nextLine();
                dos.writeUTF(cadena);
                if(cadena.equals("salir")){
                    break;
                }

                String received = dis.readUTF();
                System.out.println("Servidor: " + received);
                System.out.println("Mande posible respuesta: ");

                String cadena2 = sc.nextLine();
                dos.writeUTF(cadena2);//enviamos al servidor

                String received2 = dis.readUTF();
                String[] result3 = received2.split(":");
                System.out.println("Servidor" + result3[1]);
            }while(!cadena.equals("salir"));

            // closing resources
            sc.close();
            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}