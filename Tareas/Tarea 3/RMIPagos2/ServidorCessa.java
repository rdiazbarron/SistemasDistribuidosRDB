package org.example.EjerciciosEnClases.RMIPagos2;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServidorCessa {
    public static void main(String[] args) throws MalformedURLException, AlreadyBoundException, RemoteException {
        Cessa cessa = new Cessa();
        Naming.bind("Cessa",cessa);
    }
}
