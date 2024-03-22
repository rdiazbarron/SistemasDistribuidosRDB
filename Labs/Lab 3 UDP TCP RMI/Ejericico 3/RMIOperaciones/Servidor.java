package org.example.EjerciciosEnClases.RMIOperaciones;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException, NotBoundException {

        //        RegistroAlumnos registro = new RegistroAlumnos();
//        LocateRegistry.createRegistry(1099);
//        Naming.bind("Registro",registro);
        Operaciones op = new Operaciones();
        LocateRegistry.createRegistry(1099);
        Naming.bind("Operaciones",op);
    }
}
