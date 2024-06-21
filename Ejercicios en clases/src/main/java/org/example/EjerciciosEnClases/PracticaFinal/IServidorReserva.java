package org.example.EjerciciosEnClases.PracticaFinal;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

public interface IServidorReserva extends Remote {

    public double cotizar(String fechainicio, String fechafin, String fechacotizacion) throws Exception;
    public boolean reservar(String fechainicio, String fechafin, int idcliente, String fechacompra) throws RemoteException, MalformedURLException, NotBoundException;


}
