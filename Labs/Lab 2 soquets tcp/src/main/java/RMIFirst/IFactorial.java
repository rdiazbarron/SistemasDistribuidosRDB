package RMIFirst;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IFactorial extends Remote {
    public int calcularFactorial(int numero) throws RemoteException;
}
