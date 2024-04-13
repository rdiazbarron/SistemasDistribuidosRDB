package org.example.EjerciciosEnClases.RMIEjer1parcial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class Asfi extends UnicastRemoteObject  implements IAsfi {
    protected Asfi() throws RemoteException {
        super();
    }

    @Override
    public ArrayList<Cuenta> ConsultarCuenta(String ci, String nombre, String apellido) throws RemoteException, MalformedURLException, NotBoundException {
        ArrayList<Cuenta> cuentasGlobales = new ArrayList<Cuenta>();

        ArrayList<Cuenta> resultado1 = consultarCuentasUDP(ci, nombre,apellido);//
        ArrayList<Cuenta> resultado2 = consultarCuentasTCP(ci, nombre,apellido);//

        cuentasGlobales.addAll(resultado1);
        cuentasGlobales.addAll(resultado2);

        return cuentasGlobales;


    }

    @Override
    public boolean retenerMonto(Cuenta cuenta, double monto, String glosa) throws RemoteException, MalformedURLException, NotBoundException {

        ArrayList<Cuenta> cuentasGlobales2 = new ArrayList<Cuenta>();

        ArrayList<Cuenta> resultado3 = consultarCuentasUDP(cuenta.getCi(), cuenta.getNombres(), cuenta.getApellidos());//
        ArrayList<Cuenta> resultado4 = consultarCuentasTCP(cuenta.getCi(), cuenta.getNombres(), cuenta.getApellidos());//

        if(resultado3.size()!=0){
            boolean resultado = retenerMontoUDP(Integer.parseInt(cuenta.getNrocuenta()), monto);
            return resultado;
        }
        if(resultado4.size()!=0){
            boolean resultado = retenerMontoTCP(Integer.parseInt(cuenta.getNrocuenta()), monto);
            return resultado;
        }
        return false;

    }

    private boolean retenerMontoTCP(int cuenta, double monto) {
        int port = 5002;
        try
        {
            Socket client = new Socket("localhost", port);

            String dato = "congelar:"+cuenta+"-"+monto;

            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

            toServer.println(dato);//mandamos solocitud

            String result = fromServer.readLine();

            String[] comandos = result.toString().split("-");

            if (comandos[0].equals("no"))
            {
                return false;
            }

        }
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        return true;

    }

    private boolean retenerMontoUDP(int cuenta, double monto) {
        Scanner sc = new Scanner(System.in);
        int puerto = 6789;
        ArrayList <Cuenta> cuentasUDP = new ArrayList<>();
        try
        {

            //System.out.print("Introduzca un valor");
            //String dato = sc.next();
            String ip = "localhost";
            //buscar:5663818-ricardo-diaz
            String entradas = "congelar:"+cuenta+"-"+monto;
            DatagramSocket socketUDP = new DatagramSocket();
            byte[] mensaje = entradas.getBytes();
            InetAddress hostServidor = InetAddress.getByName(ip);

            // Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket peticion = new DatagramPacket(mensaje, entradas.length(), hostServidor, puerto);

            // Enviamos el datagrama
            socketUDP.send(peticion);

            // Construimos el DatagramPacket que contendrá la respuesta
            byte[] bufer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(respuesta);

            String respuestaString = new String(respuesta.getData(), 0, respuesta.getLength());
            String[]procesado = respuestaString.split("-");//si-56638 o no-noencnontrado
            if(procesado[0].equals("no"))
            {
                return false;
            }
            return true;

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Cuenta> consultarCuentasUDP(String ci, String nombre, String apellido) {
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
                byte[] bufer = new byte[1000];
                DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
                socketUDP.receive(respuesta);

                String respuestaString = new String(respuesta.getData(), 0, respuesta.getLength());
                String[]procesado = respuestaString.split(":");//5616-5000
                    if(!procesado[0].equals("no"))
                    {
                        String[] nuevoProcesado = procesado[0].split("-");
                        Cuenta cuenta1 = new Cuenta(Banco.MERCANTIL,nuevoProcesado[0],ci,nombre,apellido,Double.parseDouble(nuevoProcesado[1]));
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

     public ArrayList<Cuenta> consultarCuentasTCP(String ci, String nombres, String apellidos) {

         ArrayList<Cuenta> cuentasTCP = new ArrayList<>();

         int port = 5002;
         try {
             Socket client = new Socket("localhost", port);

             String dato = "buscar:"+ci+"-"+nombres+"-"+apellidos;

             PrintStream toServer = new PrintStream(client.getOutputStream());
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

             toServer.println(dato);

             String result = fromServer.readLine();

             String[] comandos = result.toString().split(":");
             if (!comandos[0].equals("no")) {
                 String[] datosCuenta = comandos[0].split("-");
                 Cuenta cuentaMercantil = new Cuenta(Banco.BCP, datosCuenta[0], ci, nombres, apellidos, Double.parseDouble(datosCuenta[1]));
                 cuentasTCP.add(cuentaMercantil);
             }


         } catch (IOException ex) {
             System.out.println(ex.getMessage());
         }
         return cuentasTCP;
     }
}
