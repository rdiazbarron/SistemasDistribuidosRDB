package org.example.EjerciciosEnClases.RMIOperaciones;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Operaciones extends UnicastRemoteObject implements IOperaciones {
    static int valora;
    static int valorb;
    protected Operaciones() throws RemoteException {
        super();
    }


    @Override
    public void anotar(int a, int b) throws RemoteException {
         valora = a;
         valorb = b;
    }

    @Override
    public int restar() throws RemoteException {
        return valora-valorb;
    }

    @Override
    public int sumar() throws RemoteException {
        return valora+valorb;
    }
}
