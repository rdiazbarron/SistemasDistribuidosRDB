package org.example.EjerciciosEnClases.PracticaFinal;

import org.apache.http.io.SessionOutputBuffer;
import org.example.EjerciciosEnClases.RMIOficial.IRuatRMI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Usuario {

    static IServidorReserva reserva;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {


        reserva = (IServidorReserva) Naming.lookup("rmi://localhost/ReservaRMI");//insntiaciar un obj remoto

        //asfi = (IAsfi) Naming.lookup("rmi://localhost/Asfi");//insntiaciar un obj remoto
        listarMenu();
    }

    public static void listarMenu() throws Exception {
        int option = 0;
        while (option != 3) {
            System.out.println("1 Cotizar (inicio, fin ,fechacotizacion)");
            System.out.println("2 Reservar (inicio, fin, idcliente,fechacompra)");
            System.out.println("3 SALIR");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("ingrese fecha inicio");
                    //en formato dd/mm/yyyy
                    String inicio = sc.next();
                    System.out.println("ingrese fecha fin");
                    //en formato dd/mm/yyyy
                    String fin = sc.next();
                    System.out.println("ingrese fecha cotizacion");
                    //en formato dd/mm/yyyy
                    String fechacotizacion = sc.next();
                    double cotizacion = reserva.cotizar(inicio, fin, fechacotizacion);
                    System.out.println("cotizacion: " + cotizacion);


                    break;
                case 2:
                    System.out.println("ingrese fecha inicio");
                    //en formato dd/mm/yyyy
                    String inicio2 = sc.next();
                    System.out.println("ingrese fecha fin");
                    //en formato dd/mm/yyyy
                    String fin2 = sc.next();

                    System.out.println("Ingrese id cliente");
                    int idcliente2 = sc.nextInt();
                    System.out.println("ingrese fecha compra");
                    //en formato dd/mm/yyyy
                    String fechacompra2 = sc.next();
                    boolean reservado = reserva.reservar(inicio2, fin2, idcliente2, fechacompra2);


                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    }
}
