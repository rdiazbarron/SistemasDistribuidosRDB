package RMIFirst;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISaludo extends Remote {
    public String saludar() throws RemoteException;
}
