package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int port = 5002;
        ServerSocket server;
        try{
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con éxito....");
            while(true){
            Socket client;
            PrintStream toClient;
            client = server.accept(); //conexion entre cliente y servidor para comunicacion bidireccional
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector



            System.out.println("Cliente se conecto....");
            String recibido=fromClient.readLine();
            System.out.println(recibido);

            toClient = new PrintStream(client.getOutputStream());

            String[] partes = recibido.split("[-+*/]");

            String operador = recibido.replaceAll("[^-+*/]", "");

            Calculadora cal = new Calculadora(Integer.parseInt(partes[0]),Integer.parseInt(partes[1]),operador);
                String resutlado = String.valueOf(cal.calcular());
                System.out.println(resutlado);
                toClient.println(resutlado);
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());

        }


    }
}