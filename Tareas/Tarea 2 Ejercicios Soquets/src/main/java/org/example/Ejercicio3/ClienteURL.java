package org.example.Ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteURL {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner sc = new Scanner(System.in);
        int port = 5002;

        try{

            Socket client = new Socket("192.168.192.112",port);//creamos socket para comunicar con server
            while(true) {
                //creacion de objetos stream para mandar y recibir
                PrintStream toServer = new PrintStream(client.getOutputStream());
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
                //envio de cadena
                System.out.println("Introduce una URL de un sitio web:");
                String cadena = sc.nextLine();
                toServer.println(cadena);//enviamos al servidor
                //luego de enviar el servidor devuelve
                String result = fromServer.readLine();
                System.out.println("La cadena devuelta por el servidor es:" + result);
                // Close the resources to allow new input in the next iteration

            }

        }catch (IOException ex){
            System.out.println(ex.getMessage());

        }

    }
}
