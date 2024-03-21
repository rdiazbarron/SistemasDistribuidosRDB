package org.example.EjerciciosEnClases.RMIPagos2;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEmpresa extends Remote {
    public Factura[] calcularFacturas(int idcliente) throws RemoteException;
    public String pagarFacturas(Factura[] facturasAPagar, int idcliente) throws RemoteException;
}
