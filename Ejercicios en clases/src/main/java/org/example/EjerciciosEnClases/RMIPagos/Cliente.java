package org.example.EjerciciosEnClases.RMIPagos;

import org.example.EjerciciosEnClases.RMIGestorAlumnos.IRegistroAlumnos;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
    static IServicioCessa servicioCessa;

    static IServicioCotes servicioCotes;
    public static void main(String[] args) throws IOException, NotBoundException {

        servicioCessa =(IServicioCessa) Naming.lookup("rmi://localhost/servicioCessa");//insntiaciar un obj remoto
        servicioCotes =(IServicioCotes) Naming.lookup("rmi://localhost:1100/servicioCotes");//insntiaciar un obj remoto
        listarMenu();

    }
    public static void listarMenu() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        int opcion =1;
        while(opcion!=3){
            System.out.println("1. Consultar facturas(cessa y cotes)");
            System.out.println("2. Pagar facturas");
            System.out.println("3 Salir");
            System.out.println("Introduzca una opcion: ");

            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    consultarFacturas();
                    break;
                case 2:
                    pagarFacturas();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("opcion incorrecta ingresada");
                    break;
            }

    }

}
    public static void consultarFacturas() throws RemoteException {
        List<Factura> recibido = new ArrayList<>();
        List<Factura> recibido2 = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el id del cliente: ");
        int idcliente = sc.nextInt();
        System.out.println("Facturas de Cessa: ");
        recibido = servicioCessa.calcularFacturas(idcliente);
        for (Factura factura : recibido) {
            System.out.println(factura.toString());
        }
        System.out.println("Facturas de Cotes: ");

        recibido2 = servicioCotes.calcularFacturas(idcliente);
        for (Factura factura : recibido2) {
            System.out.println(factura.toString());
        }
    }
    public static void pagarFacturas() throws RemoteException {
        Scanner sc = new Scanner(System.in);
       System.out.println("Introduzca el id del cliente: ");
        int idcliente = sc.nextInt();
//
        System.out.println("de cessa, introduzca los ids de las facturas a pagar separados por comas: ");//11,22,31,42
        String facturasCessa = sc.next();
        String[] facturasCessaArray = facturasCessa.split(",");
        ArrayList<Integer> facturasAPagarCessa = new ArrayList<>();
        for (String s : facturasCessaArray) {
            facturasAPagarCessa.add(Integer.parseInt(s));
        }
        String result = servicioCessa.pagarFacturas(idcliente,facturasAPagarCessa);

        System.out.println("de cotes, introduzca los ids de las facturas a pagar separados por comas: ");//11,22,31,42
        String facturasCotes = sc.next();
        String[] facturasCotesArray = facturasCotes.split(",");
       ArrayList<Integer> facturasAPagarCotes = new ArrayList<>();
       for( String i : facturasCotesArray){
           facturasAPagarCotes.add(Integer.parseInt(i));
       }
        String result2= servicioCotes.pagarFacturas(idcliente,facturasAPagarCotes);
        System.out.println("El total a pagar de cessa es: " + result);
        System.out.println("El total a pagar de cotes es: " + result2);


    }}
