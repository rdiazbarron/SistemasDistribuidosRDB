package org.example.EjerciciosEnClases.RMIEjer1parcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
        static PrintStream toClient;
        static BufferedReader fromClient;

        public static void main(String[] args)
        {

        int port = 5002;

        try (ServerSocket server = new ServerSocket(port)) { // Try-with-resources para cerrar automáticamente
            System.out.println("Servidor iniciado en el puerto " + port);

            while (true) {
                try (Socket client = server.accept(); // Acepta una conexión del cliente
                     BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintStream toClient = new PrintStream(client.getOutputStream())) {

                    System.out.println("Cliente conectado");

                    String recibido = fromClient.readLine(); // Lee la entrada del cliente
                    toClient.println(procesarEntradas(recibido)); // Envía la respuesta al cliente

                } catch (IOException e) {
                    System.out.println("Error al manejar la conexión del cliente: " + e.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al iniciar el servidor: " + ex.getMessage());
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

//    private static void congelar() {
//    }

    private static String buscarCuentas(String ciNombreApellido) {//"5663818-ricardo-diaz"
        String[] ciNombreApellidoSeparado = ciNombreApellido.split("-");
        String ciEncontrado = ciNombreApellidoSeparado[0];
        String nombreEncontrado = ciNombreApellidoSeparado[1];
        String apellidoEncontrado = ciNombreApellidoSeparado[2];
        if(ciEncontrado.equals("7687682") && nombreEncontrado.equals("Juan") && apellidoEncontrado.equals("Segovia")){
            return "1112450-50000";
        }
        if(ciEncontrado.equals("54654") && nombreEncontrado.equals("Maria") && apellidoEncontrado.equals("Parra")){
            return "1121454-3000";
        }
        return "no:encontrado";

    }

    private static String congelarCuentas(String cuentaYMonto) {//5638-5000
        String[] cuentaYMontoSeparados = cuentaYMonto.split("-");
        String cuenta = cuentaYMontoSeparados[0];
        double monto = Double.parseDouble(cuentaYMontoSeparados[1]);
        if((cuenta.equals("1112450")) && (monto<=50000)){
            return "si-1112450";
        }
        if((cuenta.equals("1121454")) && (monto<=3000)){
            return "si-1121454";
        }
        return "no-noencontrado";

    }
}
