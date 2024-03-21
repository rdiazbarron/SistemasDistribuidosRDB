package org.example.EjerciciosEnClases.RMIPagos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface IServicioCotes extends Remote {
    public List<Factura> calcularFacturas(int idcliente) throws RemoteException;
    public String pagarFacturas(int idcliente, ArrayList<Integer> facturasAPagar) throws RemoteException;
}
