package org.example.EjerciciosEnClases.RMIEjer2parcial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerASFIudp {
    //static IBanco Banco;
    public static void main(String args[]) throws MalformedURLException, NotBoundException, RemoteException {

        //Banco = (IBanco) Naming.lookup("rmi://localhost/BancoRMI");//insntiaciar un obj remoto


        int port = 6790;
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

                String[] nuevacadena = cadena.split(":");
                if(nuevacadena[0].equals("buscar")){

                    String ci = nuevacadena[1];
                    String nombre = nuevacadena[2];
                    String apellido = nuevacadena[3];
                    ArrayList<Cuenta> cuentasRecoplidas = consultarCuentaUDP(ci,nombre,apellido);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);

                    oos.writeObject(cuentasRecoplidas); // cuentasUDP es el ArrayList que quieres enviar
                    oos.flush();
                    byte[] bytesToSend = baos.toByteArray();
                    DatagramPacket peticion2 = new DatagramPacket(bytesToSend, bytesToSend.length, peticion.getAddress(), peticion.getPort());
                    socketUDP.send(peticion2);
                }
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    private static ArrayList<Cuenta>  consultarCuentaUDP(String ci, String nombre, String apellido) {
        ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

        ArrayList<Cuenta> resultados = consultarUDP(ci, nombre,apellido);//
        //ArrayList<Cuenta> resultadob = consultarTCP(ci, nombre,apellido);//

        cuentas.addAll(resultados);
        //cuentas.addAll(resultado2);

        return cuentas;
    }



    private static ArrayList<Cuenta> consultarUDP(String ci, String nombre, String apellido) {
        Scanner sc = new Scanner(System.in);
        int puerto = 6789;
        ArrayList <Cuenta> cuentasUDP = new ArrayList<>();
        try {

                //System.out.print("Introduzca un valor");
                //String dato = sc.next();
                String ip = "localhost";
                //buscar:5663818-ricardo-diaz
                String entradas = "buscar:"+ci+"-"+nombre+"-"+apellido;
                DatagramSocket socketUDP = new DatagramSocket();
                byte[] mensaje = entradas.getBytes();
                InetAddress hostServidor = InetAddress.getByName(ip);

                // Construimos un datagrama para enviar el mensaje al servidor
                DatagramPacket peticion = new DatagramPacket(mensaje, entradas.length(), hostServidor, puerto);

                // Enviamos el datagrama
                socketUDP.send(peticion);

                // Construimos el DatagramPacket que contendrá la respuesta
                byte[] bufer = new byte[65536];
                DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
                socketUDP.receive(respuesta);

                String respuestaString = new String(respuesta.getData(), 0, respuesta.getLength());
                String[]procesado = respuestaString.split(":");//5616-5000
                    if(!procesado[0].equals("no"))
                    {
                        String[] nuevoProcesado = procesado[0].split("-");
                        Cuenta cuenta1 = new Cuenta(Banco2.MERCANTIL,nuevoProcesado[0],ci,nombre,apellido,Double.parseDouble(nuevoProcesado[1]));
                        cuentasUDP.add(cuenta1);

                    }


                // Enviamos la respuesta del servidor a la salida estandar
                //System.out.println("Respuesta: " + new String(respuesta.getData()));

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cuentasUDP;
    }


}
