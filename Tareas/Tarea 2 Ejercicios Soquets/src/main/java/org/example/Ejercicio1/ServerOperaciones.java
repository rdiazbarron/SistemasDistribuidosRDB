package org.example.Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ServerOperaciones {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int port = 5002;//declaramos puerto
        ServerSocket server;//
        try{
            server = new ServerSocket(port);
            System.out.println("Se inicio el servidor con éxito....");
            while(true){
                //conexion entre cliente y servidor para comunicacion bidireccional se establece
                Socket client = server.accept();
                System.out.println("Cliente se conecto....");
                //crear objeto de recepcion para luego leer lo recibido
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                String recibido=fromClient.readLine();
                System.out.println(recibido);

                //partir el mensaje del cliente
                String[] partes = recibido.split("[-+*/]");
                String operador="";
                if(recibido.contains("+")){
                     operador="+";
                }
                if(recibido.contains("-")){
                     operador="-";
                }
                if(recibido.contains("/")){
                     operador="/";
                }
                if(recibido.contains("*")){
                     operador="*";
                }


//Mejor opacion:
//                Pattern pattern = Pattern.compile("[-+*/]");
//                Matcher matcher = pattern.matcher(recibido);
//
//                String operador="";
//                if (matcher.find()) {
//                     operador = matcher.group(0); // Esto captura el primer operador encontrado
//                    System.out.println("Operador encontrado: " + operador);
//                } else {
//                    System.out.println("Operador no encontrado");
//                }
//                System.out.println("Operador encontrado: " + operador);


                Calculadora cal = new Calculadora(Integer.parseInt(partes[0]),Integer.parseInt(partes[1]),operador);
                String resutlado = String.valueOf(cal.calcular());
                System.out.println(resutlado);
                //preparar objeto para mandar mensaje al cliente
                PrintStream toClient  = new PrintStream(client.getOutputStream());
                //mandar respuesta al cliente
                toClient.println(resutlado);
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());

        }
    }
}