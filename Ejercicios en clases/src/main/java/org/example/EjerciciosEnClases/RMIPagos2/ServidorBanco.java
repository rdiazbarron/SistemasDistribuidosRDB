package org.example.EjerciciosEnClases.RMIPagos2;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServidorBanco {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException {

        Banco banco = new Banco();
        LocateRegistry.createRegistry(1099);
        Naming.bind("Banco", banco);

    }

}
