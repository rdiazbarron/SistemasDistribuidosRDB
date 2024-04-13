package org.example.EjerciciosEnClases.RMIEjer1parcial;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IAsfi extends Remote {
    public ArrayList<Cuenta> ConsultarCuenta(String ci, String nombre, String apellido) throws RemoteException, MalformedURLException, NotBoundException;
    public boolean retenerMonto(Cuenta cuenta, double monto, String glosa) throws RemoteException, MalformedURLException, NotBoundException;
}
