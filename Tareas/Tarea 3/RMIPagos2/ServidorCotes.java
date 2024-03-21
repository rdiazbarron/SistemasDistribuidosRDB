package org.example.EjerciciosEnClases.RMIPagos2;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ServidorCotes {
    public static void main(String[] args) throws MalformedURLException, AlreadyBoundException, RemoteException {
        Cotes cotes = new Cotes();
        Naming.bind("Cotes",cotes);
    }
}
