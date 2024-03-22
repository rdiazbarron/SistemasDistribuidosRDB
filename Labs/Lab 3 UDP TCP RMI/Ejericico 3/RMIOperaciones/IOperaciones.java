package org.example.EjerciciosEnClases.RMIOperaciones;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IOperaciones extends Remote {
    public void anotar(int a, int b)throws RemoteException;
    public int restar()throws  RemoteException;

    public int sumar()throws RemoteException;
}
