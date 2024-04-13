package org.example.EjerciciosEnClases.PrimerParcial;

import org.example.EjerciciosEnClases.RMIEjer1parcial.Banco;
import org.example.EjerciciosEnClases.RMIEjer1parcial.Cuenta;

import java.io.IOException;
import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class RuatRMI extends UnicastRemoteObject implements IRuatRMI{
    protected RuatRMI() throws RemoteException {
        super();
    }

    @Override
    public Deuda[] buscar(String ci) {
        Deuda[] lasdeudas = buscarDeuda(ci);
        return lasdeudas;
    }

    @Override
    public boolean pagar(Deuda deuda) throws RemoteException, MalformedURLException, NotBoundException {

        boolean resultado = consultarObs(deuda.getCi());
        return resultado;
    }



    private Deuda[]  buscarDeuda(String ci) {

        if(ci.equals("1234567")){
            Deuda[] deudas = new Deuda[2];
            deudas[0] = new Deuda("1234567",2022,"vehiculo",2451);
            deudas[1] = new Deuda("1234567",2022,"inmueble",2500);
            return deudas;
        }
        if(ci.equals("555587")){
            Deuda[] deudas = new Deuda[1];
            deudas[0] = new Deuda("555587",2021,"vehiculo",2451);
            return deudas;
        }
        if(ci.equals("333357")){
            Deuda[] deudas = new Deuda[1];
            deudas[0] = new Deuda("555587",2023,"inmueble",24574);
            return deudas;
        }
        return null;
    }

    private boolean consultarObs(String ci) {
        Scanner sc = new Scanner(System.in);
        int puerto = 6789;
        ArrayList <Cuenta> cuentasUDP = new ArrayList<>();
        try {

            //System.out.print("Introduzca un valor");
            //String dato = sc.next();
            String ip = "localhost";
            //buscar:5663818-ricardo-diaz
            String entradas = "consulta:"+ci;
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
            String[]procesado = respuestaString.split(":");//respuesta:true
            if(procesado[1].equals("true"))
            {
                return true;
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
        return false;
    }
}
