package org.example.EjerciciosEnClases.RMIEjer1parcial;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServidorASFI {
    public static void main(String[] args) throws  RemoteException, MalformedURLException, AlreadyBoundException {

        Asfi asfi = new Asfi();
        LocateRegistry.createRegistry(1099);
        Naming.bind("Asfi", asfi);

    }
}
