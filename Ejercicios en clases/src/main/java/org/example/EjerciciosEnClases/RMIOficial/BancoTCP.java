package org.example.EjerciciosEnClases.RMIOficial;

import org.example.EjerciciosEnClases.RMIEjer1parcial.IAsfi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class BancoTCP {
    static PrintStream toClient;
    static BufferedReader fromClient;

    static IRuatRMI ruat;
    static String mandar;

    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {

    ruat = (IRuatRMI) Naming.lookup("rmi://localhost/RuatRMI");//insntiaciar un obj remoto
    int port = 5002;

        try (ServerSocket server = new ServerSocket(port)) { // Try-with-resources para cerrar automáticamente
            System.out.println("Servidor iniciado en el puerto " + port);

            while (true) {
                try (Socket client = server.accept();
                     BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintStream toClient = new PrintStream(client.getOutputStream())) {

                    System.out.println("Cliente conectado");

                    String recibido = fromClient.readLine();
                    System.out.println("carnet recibido en banco tcp");
                    System.out.println(recibido);
                    String[] operacion = recibido.split(":");
                    System.out.println("Operacion"+operacion[0]);
                    if (operacion[0].equals("Deuda")){
                        System.out.println("Carnet mandado: +"+operacion[1]);
                        Deuda[] deudasRecibidas = ruat.buscar(operacion[1]);//aqui estan Deudas pero no jala
                        //asi devolver: deudas:año,impuesto,monto; año2,impuesto2,monto2
                        String devolver ="deudas:";
                        for( Deuda d: deudasRecibidas){
                            devolver = devolver + d.getAnio()+",";
                            devolver = devolver + d.getImpuesto()+",";
                            devolver = devolver + d.getMonto();
                            devolver = devolver +";";
                        }

                        toClient.println(devolver);
                    }
                    if (operacion[0].equals("Pagar")){
                        //System.out.println("Carnet mandado: +"+operacion[1]);
                        //operacionGlobal = +ci2+","+anio+","+monto+","+impuesto;
                        String[] datos = operacion[1].split(",");
                        Deuda mideuda = new Deuda(datos[0],Integer.parseInt(datos[1]),datos[3],Double.parseDouble(datos[2]));

                        boolean resultado = ruat.pagar(mideuda);
                        if (resultado==false){
                            mandar = "transaccion:false";
                        }
                        if (resultado==true){
                            mandar = "transaccion:true";
                        }

                        toClient.println(mandar);
                    }



                } catch (IOException e) {
                    System.out.println("Error al manejar la conexión del cliente: " + e.getMessage());
                }
            }
        } catch (IOException ex) {
            System.out.println("Error al iniciar el servidor: " + ex.getMessage());
        }
}
}

