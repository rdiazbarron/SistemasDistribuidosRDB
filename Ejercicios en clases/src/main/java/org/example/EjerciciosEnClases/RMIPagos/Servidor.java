package org.example.EjerciciosEnClases.RMIPagos;

import org.example.EjerciciosEnClases.RMIGestorAlumnos.RegistroAlumnos;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException {

        ServicioCessa servicioCessa = new ServicioCessa();
        LocateRegistry.createRegistry(1099);
        Naming.bind("servicioCessa", servicioCessa);
        ServicioCotes servicioCotes = new ServicioCotes();
        LocateRegistry.createRegistry(1100);
        Naming.bind("rmi://localhost:1100/servicioCotes", servicioCotes);

    }
}
