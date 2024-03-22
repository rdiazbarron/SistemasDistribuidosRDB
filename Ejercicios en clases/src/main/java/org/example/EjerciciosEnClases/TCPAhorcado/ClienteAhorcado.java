package org.example.EjerciciosEnClases.TCPAhorcado;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAhorcado {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int port = 5002;
        try{

            Socket client = new Socket("localhost",port);
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String cadena;
            System.out.println("Introduce tu nombre:");
            cadena = sc.nextLine();
            toServer.println(cadena);
            String result = fromServer.readLine();//servidor envia nombre y palbara
            System.out.println(result);//se muestra
            String resultadoPrediccion;
            do{
                System.out.println("Introduce una letra o vocal psibles: ");
                String cadena2 = sc.nextLine();
                toServer.println(cadena2);//enviamos al servidor para comprobar

                 resultadoPrediccion = fromServer.readLine();
                System.out.println(resultadoPrediccion);

            }while(!resultadoPrediccion.equals("Ganaste campeon"));
            toServer.println("salir");
            client.close();
        }catch (IOException ex){
            System.out.println(ex.getMessage());

        }

    }
}
