package org.example.EjerciciosEnClases.RMIGestorAlumnos;

import RMIFirst.Factorial;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException {
//        Saludo s = new Saludo();
//        LocateRegistry.createRegistry(1099);
//        Naming.bind("Saludo",s);

        RegistroAlumnos registro = new RegistroAlumnos();
        LocateRegistry.createRegistry(1099);
        Naming.bind("Registro",registro);
    }
}
