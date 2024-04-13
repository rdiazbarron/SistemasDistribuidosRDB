package org.example.EjerciciosEnClases.RMIEjer1parcial;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerUDP {

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

                String cuentas1 = procesarEntradas(cadena); // Procesa la entrada recibida
                byte[] mensaje = cuentas1.getBytes();

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

    private static String procesarEntradas(String cadena) {//buscar:5663818-ricardo-diaz"

        String[] recibido = cadena.split(":");
        if (recibido[0].equals("buscar"))
        {
            return buscarCuentas(recibido[1]);//le paso solo 5663818-ricardo-diaz
        }
        if (recibido[0].equals("congelar"))
        {
            return congelarCuentas(recibido[1]);
        }
        return "no:encontrado";
    }

    private static String buscarCuentas(String ciNombreApellido) {//"5663818-ricardo-diaz"
        String[] ciNombreApellidoSeparado = ciNombreApellido.split("-");
        String ciEncontrado = ciNombreApellidoSeparado[0];
        String nombreEncontrado = ciNombreApellidoSeparado[1];
        String apellidoEncontrado = ciNombreApellidoSeparado[2];
       if(ciEncontrado.equals("7687682") && nombreEncontrado.equals("Juan") && apellidoEncontrado.equals("Segovia")){
           return "657654-2000";
       }
       if(ciEncontrado.equals("455787") && nombreEncontrado.equals("Ricardo") && apellidoEncontrado.equals("Centellas")){
           return "657255-5890";
       }
       return "no:encontrado";

    }
    private static String congelarCuentas(String cuentaYMonto) {//5638-5000
        String[] cuentaYMontoSeparados = cuentaYMonto.split("-");
        String cuenta = cuentaYMontoSeparados[0];
        double monto = Double.parseDouble(cuentaYMontoSeparados[1]);
        if((cuenta.equals("657654")) && (monto<=2000)){
            return "si-657654";
        }
        if((cuenta.equals("657255")) && (monto<=5890)){
            return "si-657255";
        }
        return "no-noencontrado";

    }


}
