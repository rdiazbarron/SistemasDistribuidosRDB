package org.example.EjerciciosEnClases.PrimerParcial;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Usuario {
    static Scanner sc = new Scanner(System.in);
    static String operacionGlobal ="";
    public static void main(String[] args) throws IOException, NotBoundException {
        //asfi = (IAsfi) Naming.lookup("rmi://localhost/Asfi");//insntiaciar un obj remoto
        listarMenu();
    }

    public static void listarMenu() throws MalformedURLException, NotBoundException, RemoteException {
        int option = 0;
        while (option != 3) {
            System.out.println("1 Consultar deuda(ci)");
            System.out.println("2 Pagar (ci,anio,impuesto,monto)");
            System.out.println("3 SALIR");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("ingrese ci del supuesto deudor");
                    String ci = sc.next();
                    operacionGlobal = "Deuda:"+ci;
                    realizarAccion();

                    break;
                case 2:
                    System.out.println("Ingrese ci");
                    String ci2 = sc.next();
                    System.out.println("Ingrese anio");
                    int anio = sc.nextInt();
                    System.out.println("Ingrese monto");
                    double monto = sc.nextDouble();
                    System.out.println("Ingrese impuesto");
                    String impuesto = sc.next();
                    operacionGlobal = "Pagar:"+ci2+","+anio+","+monto+","+impuesto;
                    realizarAccion();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    }


    //deudas:año,impuesto,monto; año2,impuesto2,monto2
    public static void  realizarAccion()
    {
        int port = 5002;
        try {
            Socket client = new Socket("localhost", port);

            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

            toServer.println(operacionGlobal);//enviamos hasta aqui ya esta algo

            String result = fromServer.readLine();
            //deudas:año,impuesto,monto; año2,impuesto2,monto2;

            System.out.println("Resultados: ");
            System.out.println(result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
