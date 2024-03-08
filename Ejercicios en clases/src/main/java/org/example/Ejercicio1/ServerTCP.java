package org.example.Ejercicio1;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class ServerTCP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int port = 5002;
        Scanner sc = new Scanner(System.in);
        ServerSocket server;

        try {

            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con éxito");
            Socket client;
            client = server.accept();
            System.out.println("Cliente se conecto");

            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
            PrintStream toClient = new PrintStream(client.getOutputStream());

            toClient.println("Conexion establecida chateemos:");

            String recibido ="kami";
            while (!recibido.equals("terminar")) {
                //conexion entre cliente y servidor para comunicacion bidireccional

                recibido = fromClient.readLine();
                System.out.println("Cliente:"+recibido);

                System.out.print("Server:");
                String entrada = sc.nextLine();
                toClient.println(entrada);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}