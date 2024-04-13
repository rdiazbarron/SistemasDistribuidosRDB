package org.example.EjerciciosEnClases.RMIOficial;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class AlcaldiaUDP {
    static String result;
    public static void main(String args[]) {
        int port = 6789;
        try {
            // Creamos el socket una vez fuera del bucle
            DatagramSocket socketUDP = new DatagramSocket(port);

            while (true) {
                byte[] bufer = new byte[1000];

                // Construimos el DatagramPacket para recibir peticiones
                DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
                socketUDP.receive(peticion);

                // Extraemos la cadena recibida del paquete, usando la longitud de los datos recibidos
                String cadena = new String(peticion.getData(), 0, peticion.getLength());

                String[] resultados = cadena.split(":");
                if(resultados[1].equals("1234567"))
                {
                    result = "respuesta:true";
                }
                if(!resultados[1].equals("1234567"))
                {
                    result = "respuesta:false";
                }

                byte[] mensaje = result.getBytes();

                // Creamos el paquete de respuesta utilizando la misma longitud de mensaje que el enviado
                DatagramPacket respuesta = new DatagramPacket(mensaje, mensaje.length, peticion.getAddress(), peticion.getPort());

                // Enviamos la respuesta
                socketUDP.send(respuesta);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
