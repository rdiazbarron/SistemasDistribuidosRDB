package org.example.Ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ServerURL {
    public static void main(String[] args) {

        int port = 5002;//declaramos puerto
        ServerSocket server;//
        try{
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con éxito....");
            while(true){
                //conexion entre cliente y servidor para comunicacion bidireccional se establece
                Socket client = server.accept();
                System.out.println("Cliente se conecto....");
                //crear objeto de recepcion y la otra liena para luego leer lo recibido
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String recibido=fromClient.readLine();
                System.out.println(recibido);


                String respuesta="";
                try {
                    URL url = new URL(recibido);//se instancia objeto de clase URL
                   URLConnection connection = url.openConnection();// el objeto instanciado se usa metodo openconection y se guarda en conection
                    connection.connect();//se conecta
                     respuesta = "URL activo";
                } catch (IOException e) {
                     respuesta = "URL inactivo";
                }
                //preparar objeto para mandar mensaje al cliente
                PrintStream toClient  = new PrintStream(client.getOutputStream());
                //mandar respuesta al cliente
                toClient.println(respuesta);
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());


        }


    }
}