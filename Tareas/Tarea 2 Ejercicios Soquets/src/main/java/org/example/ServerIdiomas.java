package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import net.suuft.libretranslate.Language;
import net.suuft.libretranslate.Translator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ServerIdiomas {
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

                //partir el mensaje del cliente
                String[] partes = recibido.split(":");

                Map<String,Language> mapaLenguajes = new HashMap<>();
                mapaLenguajes.put("en",Language.ENGLISH);
                mapaLenguajes.put("es",Language.SPANISH);
                mapaLenguajes.put("ch",Language.CHINESE);
                mapaLenguajes.put("fr",Language.FRENCH);
                String res;
                if((!mapaLenguajes.containsKey(partes[2]))||(!mapaLenguajes.containsKey(partes[1]))){
                    res = "no existe lenguaje introducido";
                }
                else{
                    Language lenguajeOrigen = mapaLenguajes.get(partes[1]);
                    Language lenguajeDestino = mapaLenguajes.get(partes[2]);

                    System.out.println("salida: ");
                    res = Translator.translate(lenguajeOrigen, lenguajeDestino, partes[0]);
                }
                //preparar objeto para mandar mensaje al cliente
                PrintStream toClient  = new PrintStream(client.getOutputStream());
                //mandar respuesta al cliente
                toClient.println(res);
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());


        }


    }
}