package org.example.EjerciciosEnClases.RMIEjer2parcial;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServidorBanco {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, RemoteException {

        Banco banquito = new Banco();
        LocateRegistry.createRegistry(1099);
        Naming.bind("BancoRMI", banquito);

    }
}
