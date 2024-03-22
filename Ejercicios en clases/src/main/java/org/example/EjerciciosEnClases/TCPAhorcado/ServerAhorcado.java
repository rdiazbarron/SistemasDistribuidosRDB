package org.example.EjerciciosEnClases.TCPAhorcado;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerAhorcado {
    static int respuestaMandar;
    static int numeroIntentos=10;

    static String palabraOculta = "camisa";
    static String[] nuevaPalabra = new String[6];
    static {
        for (int i = 0; i < palabraOculta.length(); i++) {
            nuevaPalabra[i] = "_";
        }
    }

    static BufferedReader fromClient;
    static PrintStream toClient;
    public static void main(String[] args) {
        int port = 5002;//declaramos puerto
        ServerSocket server;//
            try {
                server = new ServerSocket(port);
                System.out.println("Servidor creado....");


                    Socket client = server.accept();
                    System.out.println("Cliente se conecto....");

                    fromClient = new BufferedReader(new InputStreamReader(client.getInputStream())); // el lector
                    toClient = new PrintStream(client.getOutputStream());
                    String nombre = fromClient.readLine();//cliente envia nobmre
                    toClient.println("Empieza el juego"+nombre+"....Tu palabra es: "+devolverPalabra());

                while(true){
                        String recibido1 = fromClient.readLine();
                        String resultadoProcesamiento = procesarPalabra(recibido1);
                        if (String.join("", nuevaPalabra).equals(palabraOculta)) {
                            ganar();
                            break; // Salir del bucle una vez que el jugador gana
                        }
                        if(numeroIntentos==0){
                            perder();
                            break;
                        }
                        toClient.println("Resultado de tu prediccion: "+resultadoProcesamiento);
                        server.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }
    }
    public static String devolverPalabra(){
        String palabra = "camisa";
        String espacios="";
        String[] palabraseparada = palabra.split("");
        int cantidadletras = palabraseparada.length;
        for (int i=0; i<cantidadletras;i++){
            espacios+="_";
        }
        int r= numeroIntentos();
        return espacios+":"+r;
    }
    public static String procesarPalabra(String letra){

        String[] palabraOcultaSeparada = palabraOculta.split("");
            for(int k=0;k<palabraOculta.length();k++){
                if((palabraOcultaSeparada[k].equals(letra))){
                    nuevaPalabra[k]=letra;
                }

            }

            int r= numeroIntentos();
            String palabraMandar ="";
            for (String i : nuevaPalabra){
                palabraMandar+=i;

            }
            return palabraMandar+":"+r;

    }
    public static int numeroIntentos(){
        return numeroIntentos--;
    }
    public static void ganar(){
        toClient.println("Ganaste campeon");
    }
    public static void perder(){
        toClient.println("Perdiste");
    }

}

