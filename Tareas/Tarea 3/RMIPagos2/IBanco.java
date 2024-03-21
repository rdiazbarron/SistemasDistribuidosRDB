package org.example.EjerciciosEnClases.RMIPagos2;


import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface IBanco extends Remote {

    public Factura[] calcularFacturas(int idcliente) throws RemoteException, MalformedURLException, NotBoundException;
    public String pagarFacturas(Factura [] facturasAPagar,int idcliente) throws RemoteException, MalformedURLException, NotBoundException;
}
