package org.example.EjerciciosEnClases.RMIPagos2;

import org.example.EjerciciosEnClases.RMIPagos.IServicioCessa;
import org.example.EjerciciosEnClases.RMIPagos.IServicioCotes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Cliente {
    static Scanner sc = new Scanner(System.in);
    static IBanco banco;
    static int idcliente;
    public static void main(String[] args) throws IOException, NotBoundException
    {
        banco =(IBanco) Naming.lookup("rmi://localhost/Banco");//insntiaciar un obj remoto
        System.out.println("Introduzca su id de cliente: ");
        idcliente = sc.nextInt();
        listarMenu();
    }

    private static void listarMenu() throws RemoteException, MalformedURLException, NotBoundException
    {
        int opcion = 1;
        while(opcion!=3)
        {
            System.out.println("que desea jacer");
            System.out.println("1 consultar facutras pendients");
            System.out.println("2 Pagar todas las factiras");
            System.out.println("3 salir");
            opcion = sc.nextInt();
            switch(opcion)
            {
                case 1:consultar();
                    break;
                case 2:pagar();
                    break;
                case 3:
                    break;

                default:break;
            }
        }

    }
    private static void consultar() throws MalformedURLException, NotBoundException, RemoteException {
        Factura[] facturass = banco.calcularFacturas(idcliente);
        System.out.println("Facturas pendientes: ");
        for (Factura f : facturass){
            System.out.println(f);
        }

    }

    private static void pagar() throws RemoteException, MalformedURLException, NotBoundException
    {
        Factura[] facturass = banco.calcularFacturas(idcliente);
        String respuesta = banco.pagarFacturas(facturass,idcliente);
        System.out.println(respuesta);
    }
}

