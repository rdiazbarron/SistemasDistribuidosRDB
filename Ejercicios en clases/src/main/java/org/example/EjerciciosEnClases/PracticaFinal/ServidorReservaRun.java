package org.example.EjerciciosEnClases.PracticaFinal;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServidorReservaRun {
        public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, RemoteException, AlreadyBoundException {

        ServidorReserva r = new ServidorReserva();
        LocateRegistry.createRegistry(1099);
        Naming.bind("ReservaRMI", r);

    }
}


