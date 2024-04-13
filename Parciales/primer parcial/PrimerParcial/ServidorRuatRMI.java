package org.example.EjerciciosEnClases.PrimerParcial;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServidorRuatRMI {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, RemoteException, AlreadyBoundException {

        RuatRMI r = new RuatRMI();
        LocateRegistry.createRegistry(1099);
        Naming.bind("RuatRMI", r);

    }
}
