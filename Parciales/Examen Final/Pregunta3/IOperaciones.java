package org.example.EjerciciosEnClases.ExamenFinal3;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;



public interface IOperaciones extends Remote {
    public boolean introducirValor(String cadena) throws RemoteException, MalformedURLException, NotBoundException;
    public String invertir() throws RemoteException, MalformedURLException, NotBoundException;//debe invertir cadena guardada por introducirValor

    public String aumentartresespacios(int n) throws RemoteException, MalformedURLException, NotBoundException;//aumenta 3 espacios a cadena guardada por introducir valor

    public String aumentar(String cadena) throws RemoteException, MalformedURLException, NotBoundException;//aumenta una nueva cadena a la cadena
}
