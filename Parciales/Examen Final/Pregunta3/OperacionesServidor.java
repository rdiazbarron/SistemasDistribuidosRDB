package org.example.EjerciciosEnClases.ExamenFinal3;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class OperacionesServidor {
    public static void main(String[] args) throws MalformedURLException, RemoteException, AlreadyBoundException {

        Operaciones r = new Operaciones();
        LocateRegistry.createRegistry(1099);
        Naming.bind("OperacionesRMI", r);

    }
}
