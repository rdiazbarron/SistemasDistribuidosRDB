package org.example.EjerciciosEnClases.RMIEjer2parcial;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Banco extends UnicastRemoteObject implements IBanco {
    protected Banco() throws RemoteException {
    }

    @Override
    public ArrayList<Cuenta> ConsultarCuenta(String ci, String nombre, String apellido) throws RemoteException, MalformedURLException, NotBoundException {
        return null;
    }

//    @Override
//    public boolean retenerMonto(Cuenta cuenta, double monto, String glosa) throws RemoteException, MalformedURLException, NotBoundException {
//        return false;
//    }
}
