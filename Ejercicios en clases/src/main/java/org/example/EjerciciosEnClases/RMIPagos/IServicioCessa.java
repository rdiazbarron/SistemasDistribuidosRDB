package org.example.EjerciciosEnClases.RMIPagos;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IServicioCessa extends Remote {


    public List<Factura> calcularFacturas(int idcliente) throws RemoteException;
    public String pagarFacturas(int idcliente, ArrayList<Integer> facturasAPagar) throws RemoteException;
}
