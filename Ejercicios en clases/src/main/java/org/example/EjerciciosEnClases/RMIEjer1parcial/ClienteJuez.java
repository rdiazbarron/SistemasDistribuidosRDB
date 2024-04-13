package org.example.EjerciciosEnClases.RMIEjer1parcial;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteJuez {
    static IAsfi asfi;
    static ArrayList<Cuenta> cuentasRecibidas = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, NotBoundException
    {
        asfi = (IAsfi) Naming.lookup("rmi://localhost/Asfi");//insntiaciar un obj remoto
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
                    consultarCuenta();
                    break;
                case 2:
                    retenerMonto();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    }

    private static void retenerMonto() throws MalformedURLException, NotBoundException, RemoteException {
        if(cuentasRecibidas!=null)
        {
            int i = 1;

            for (Cuenta c : cuentasRecibidas)
                {
                    System.out.println(i+".- Banco: " + c.getBanco().toString() +" Cuenta: "+ c.getNrocuenta()+" "+c.getMonto());
                    i++;
                }

            System.out.println("Introduzca el numero de cuenta que quiere retener");
            int op_retener = sc.nextInt();
            Cuenta cuenta = cuentasRecibidas.get(op_retener-1);
            System.out.println("Introduzca el monto a retener");
            double monto_retener = sc.nextDouble();
            System.out.println("Introduzca la glosa explicando el motivo de la retención");
            String glosa_retener = sc.nextLine();

                if (asfi.retenerMonto(cuenta, monto_retener, glosa_retener))
                {
                    System.out.println("se Retuvo el monto con exito");
                }
                 else
                {
                    System.out.println("NO es posible retener el monto con exito");
                }
        }

    }

    public static void consultarCuenta() throws MalformedURLException, NotBoundException, RemoteException
    {
        System.out.println("Introduzca ci del presunto infractor: ");

        int ci = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduzcao nombre");
        String nombre = sc.nextLine();
        System.out.println("Introduzca apelludos");
        String apelli = sc.nextLine();
        cuentasRecibidas = asfi.ConsultarCuenta(String.valueOf(ci), nombre,apelli);

        //impirmir cuentas recibidas
        if(cuentasRecibidas!=null){
            int i=1;
            for(Cuenta c: cuentasRecibidas){
                System.out.println(i+"Banco: "+c.getBanco().toString()+"nro de cuenta: "+c.getNrocuenta()+"monto: "+ c.getMonto());
                i++;
            }
        }
        if(cuentasRecibidas.size()==0){
            System.out.println("No se encontraron cuentas");
        }

    }

}
