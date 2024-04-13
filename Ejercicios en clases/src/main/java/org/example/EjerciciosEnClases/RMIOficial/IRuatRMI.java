package org.example.EjerciciosEnClases.RMIOficial;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRuatRMI extends Remote {
    public Deuda[] buscar(String ci) throws RemoteException, MalformedURLException, NotBoundException;
    public boolean pagar(Deuda deuda) throws RemoteException, MalformedURLException, NotBoundException;
}
