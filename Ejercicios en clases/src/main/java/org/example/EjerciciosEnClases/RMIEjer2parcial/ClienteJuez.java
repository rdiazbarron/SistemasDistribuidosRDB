package org.example.EjerciciosEnClases.RMIEjer2parcial;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteJuez {

    //static IAsfi asfi;
    static ArrayList<Cuenta> cuentasRecibidas = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, NotBoundException
    {
        //asfi = (IAsfi) Naming.lookup("rmi://localhost/Asfi");//insntiaciar un obj remoto
        listarMenu();
    }
    public static void listarMenu() throws MalformedURLException, NotBoundException, RemoteException
    {
        int option =0;
        while(option!=3){
            System.out.println("1 Consultar cuenta");
            System.out.println("2 Retener cuenta");
            System.out.println("3 SALIR");
            option = sc.nextInt();
            switch(option){
                case 1:
                    consultarCuenta1();
                    break;
                case 2:
                    //retenerMonto();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    }

    private static void consultarCuenta1() {
        Scanner sc = new Scanner(System.in);
        int puerto = 6790;
        ArrayList <Cuenta> cuentasUDP = new ArrayList<>();
        try
        {

            String ip = "localhost";
            //buscar:5663818-ricardo-diaz
            System.out.println("Introduce nombre");
            String nombre = sc.next();
            System.out.println("Introduce apellido");
            String apellido = sc.next();
            System.out.println("Introduce ci");
            String ci = sc.next();
            String entradas = "buscar:"+ci+"-"+nombre+"-"+apellido;

            DatagramSocket socketUDP = new DatagramSocket();
            byte[] mensaje = entradas.getBytes();
            InetAddress hostServidor = InetAddress.getByName(ip);

            // Construimos un datagrama para enviar el mensaje al servidor
            DatagramPacket peticion = new DatagramPacket(mensaje, entradas.length(), hostServidor, puerto);

            // Enviamos el datagrama
            socketUDP.send(peticion);//devolvera un String que volveremos en arraylist
            //banco-nrocuenta-monto que meteremos en arraylist


            // Construimos el DatagramPacket que contendrá la respuesta
            byte[] bufer = new byte[65536];
            DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length);
            socketUDP.receive(respuesta);

            ByteArrayInputStream bais = new ByteArrayInputStream(bufer);
            ObjectInputStream ois = new ObjectInputStream(bais);
            cuentasRecibidas = (ArrayList<Cuenta>) ois.readObject();
            if (cuentasRecibidas.isEmpty())
            {
                System.out.println("No se encontraron cuentas");
            }
            else
            {
                System.out.println("Cuentas encontradas");

                //imprimir
                for (Cuenta cuenta : cuentasRecibidas) {
                    System.out.println(cuenta.getNombres());
                    System.out.println(cuenta.getApellidos());
                    System.out.println(cuenta.getCi());
                    System.out.println(cuenta.getMonto());
                }

            }

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
