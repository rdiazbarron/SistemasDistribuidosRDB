package RMIFirst;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Factorial extends UnicastRemoteObject implements IFactorial {

    protected Factorial() throws RemoteException {
        super();
    }

    @Override
    public int calcularFactorial(int numero) throws RemoteException {
    int fact=1;
    for (int i=1;i<=numero;i++){
            fact*=i;
        }
    return fact;
    }
}
