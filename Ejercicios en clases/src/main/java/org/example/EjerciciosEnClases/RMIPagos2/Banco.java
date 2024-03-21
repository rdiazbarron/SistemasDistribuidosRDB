package org.example.EjerciciosEnClases.RMIPagos2;


import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Banco extends UnicastRemoteObject implements IBanco {



    protected Banco() throws RemoteException, MalformedURLException, NotBoundException {
        super();
//        cotes = (IEmpresa) Naming.lookup("rmi://localhost/Cotes");
//        cessa = (IEmpresa) Naming.lookup("rmi://localhost/Cessa");
    }

    @Override
    public Factura[] calcularFacturas(int idcliente) throws RemoteException, MalformedURLException, NotBoundException {
        IEmpresa cotes;
        IEmpresa cessa;
        cotes = (IEmpresa) Naming.lookup("rmi://localhost/Cotes");
        cessa = (IEmpresa) Naming.lookup("rmi://localhost/Cessa");

        Factura[] facturasCotes = cotes.calcularFacturas(idcliente);
        Factura[] facturasCessa = cessa.calcularFacturas(idcliente);

        Factura[] facturas = new Factura[facturasCotes.length + facturasCessa.length];
        int i = 0;
        for (Factura factura : facturasCotes) {
            facturas[i] = factura;
            i++;
        }
        for(Factura factura : facturasCessa){
            facturas[i] = factura;
            i++;
        }
        //System.arraycopy(facturasCotes, 0, facturas, 0, facturasCotes.length);
        //System.arraycopy(facturasCessa, 0, facturas, facturasCotes.length, facturasCessa.length);
        return facturas;

    }

    @Override
    public String pagarFacturas(Factura[] facturasAPagar,int idcliente) throws RemoteException, MalformedURLException, NotBoundException {
        IEmpresa cotes;
        IEmpresa cessa;
        cotes = (IEmpresa) Naming.lookup("rmi://localhost/Cotes");
        cessa = (IEmpresa) Naming.lookup("rmi://localhost/Cessa");

        int countCessa = 0;
        int countCotes = 0;
        for (Factura f : facturasAPagar) {
            if (f.getEmpresa().getNombre().equals("Cessa")) {
                countCessa++;
                System.out.println("Cantidad facturas de cessa:"+countCessa);
            }
            if (f.getEmpresa().getNombre().equals("Cotes")) {
                countCotes++;
                System.out.println("Cantidad facturas de cessa:"+countCotes);

            }
        }

        Factura[] facturasCessa = new Factura[countCessa];
        Factura[] facturasCotes = new Factura[countCotes];
        int contce = 0;
        int contco = 0;

        for (Factura fact : facturasAPagar) {
            if (fact.getEmpresa().getNombre().equals("Cessa")) {
                facturasCessa[contce++] = fact;
            } else if (fact.getEmpresa().getNombre().equals("Cotes")) {
                facturasCotes[contco++] = fact;
            }
        }

        String result = cotes.pagarFacturas(facturasCotes,idcliente);
        String result2 = cessa.pagarFacturas(facturasCessa,idcliente);
        return result + " " + result2;
    }
}
