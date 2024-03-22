package org.example.EjerciciosEnClases.RMIOperaciones;


import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Cliente {
    static Scanner sc = new Scanner(System.in);
    static IOperaciones operaciones;
    public static void main(String[] args) throws IOException, NotBoundException {


        operaciones = (IOperaciones)Naming.lookup("rmi://localhost/Operaciones");
        listarMenu();

    }

    private static void listarMenu() throws RemoteException {
        int opcion = 1;
        while(opcion !=4){
            System.out.println("que desea hacer");
            System.out.println("1 anotar datos");
            System.out.println("2 restar datos");
            System.out.println("3  sumar datos");
            System.out.println("4 salir");
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("introduzca valor de a:");
                    int numa = sc.nextInt();
                    System.out.println("introduzca valor de b:");
                    int numb = sc.nextInt();
                    operaciones.anotar(numa,numb);
                    break;
                case 2:
                    int resultado = operaciones.restar();
                    System.out.println("Resta"+resultado);
                    break;
                case 3:
                    int resultado2 = operaciones.sumar();
                    System.out.println("Suma "+resultado2);
                    break;
                case 4: break;
            }
        }

    }
}
