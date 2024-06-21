package org.example.EjerciciosEnClases.ExamenFinal3;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class usuario {
    static IOperaciones operaciones;
    static Scanner sc = new Scanner(System.in);

    static String cadenaGuardada = "a";

    public static void main(String[] args) throws Exception {


        operaciones = (IOperaciones) Naming.lookup("rmi://localhost/OperacionesRMI");//insntiaciar un obj remoto


        listarMenu();
    }

    private static void listarMenu() throws MalformedURLException, NotBoundException, RemoteException {
        int option = 0;
        while (option != 5) {
            System.out.println("1 introducir ");
            System.out.println("2 invertir ");
            System.out.println("3 aumentar espacios");
            System.out.println("4 aumentar");
            System.out.println("5 salir");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("ingrese cadena");
                    cadenaGuardada = sc.next();
                    boolean resultado = operaciones.introducirValor(cadenaGuardada);
                    System.out.println("resultado: " + resultado);
                    break;
                case 2:
                    String resultado2 = operaciones.invertir();
                    System.out.println("resultado: " + resultado2);
                    break;
                case 3:
                    System.out.println("ingrese cantidad de estpacios");
                    int n = sc.nextInt();

                    String resultado3 = operaciones.aumentartresespacios(n);
                    System.out.println("resultado: " + resultado3);
                    break;
                case 4:
                    System.out.println("ingrese cadena a auamntear");
                    String cadena2 = sc.next();
                    String resultad4 = operaciones.aumentar(cadena2);
                    System.out.println("resultado: " + resultad4);
                    break;
                default:
                    break;
            }


        }

    }
}