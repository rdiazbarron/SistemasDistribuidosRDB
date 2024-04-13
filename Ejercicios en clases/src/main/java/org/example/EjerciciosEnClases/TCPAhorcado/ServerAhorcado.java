package org.example.EjerciciosEnClases.TCPAhorcado;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ServerAhorcado {
    static int respuestaMandar;
    static int numeroIntentos = 10;
    static String[] palabras = {"camisa", "computadora", "telefono", "lapiz", "libro", "mesa", "silla", "ventana", "puerta", "arbol"};
    static String palabraOculta;
    static String[] nuevaPalabra;

    static {
        // Seleccionar una palabra aleatoria de la lista
        Random rand = new Random();
        int indiceAleatorio = rand.nextInt(palabras.length);
        palabraOculta = palabras[indiceAleatorio];

        // Inicializar nuevaPalabra con guiones bajos
        nuevaPalabra = new String[palabraOculta.length()];
        for (int i = 0; i < palabraOculta.length(); i++) {
            nuevaPalabra[i] = "_";
        }
    }

    static BufferedReader fromClient;
    static PrintStream toClient;

    public static void main(String[] args) {
        int port = 5002; // declaramos puerto
        ServerSocket server; //
        try {
            server = new ServerSocket(port);
            System.out.println("Servidor creado....");

            Socket client = server.accept();
            System.out.println("Cliente se conecto....");

            fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            toClient = new PrintStream(client.getOutputStream());
            String nombre = fromClient.readLine(); // cliente envia nombre
            toClient.println("Empieza el juego" + nombre + "....Tu palabra es: " + devolverPalabra());

            while (true) {
                String recibido1 = fromClient.readLine();
                String resultadoProcesamiento = procesarPalabra(recibido1);
                if (String.join("", nuevaPalabra).equals(palabraOculta)) {
                    ganar();
                    break;
                }
                if (numeroIntentos == 0) {
                    perder();
                    break;
                }
                toClient.println("Resultado de tu prediccion: " + resultadoProcesamiento);
                server.close();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String devolverPalabra() {
        String espacios = "";
        for (int i = 0; i < palabraOculta.length(); i++) {
            espacios += "_";
        }
        int r = numeroIntentos();
        return espacios + ":" + r;
    }

    public static String procesarPalabra(String letra) {
        String[] palabraOcultaSeparada = palabraOculta.split("");
        for (int k = 0; k < palabraOculta.length(); k++) {
            if (palabraOcultaSeparada[k].equals(letra)) {
                nuevaPalabra[k] = letra;
            }
        }

        int r = numeroIntentos();
        String palabraMandar = "";
        for (String i : nuevaPalabra) {
            palabraMandar += i;
        }
        return palabraMandar + ":" + r;
    }

    public static int numeroIntentos() {
        return --numeroIntentos;
    }

    public static void ganar() {
        toClient.println("Ganaste campeon");
    }

    public static void perder() {
        toClient.println("Perdiste");
    }
}
