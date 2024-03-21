package RMIFirst;

import RMIFirst.ISaludo;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class Saludo  extends UnicastRemoteObject implements ISaludo {


    protected Saludo() throws RemoteException {
        super();
    }

    @Override
    public String saludar() throws RemoteException {
        return "hola mundo";
    }
}
