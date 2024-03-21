package org.example.EjerciciosEnClases.RMIPagos2;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Cessa extends UnicastRemoteObject implements IEmpresa {
    private Map<Integer, Factura[]> facturasPorCliente;
    protected Cessa() throws RemoteException {
        super();
        facturasPorCliente = new HashMap<>();
    }

    @Override
    public Factura[] calcularFacturas(int idcliente) throws RemoteException {

        if(facturasPorCliente.containsKey(idcliente)){
            return facturasPorCliente.get(idcliente);
        }
        else{
            Factura[] facturas = new Factura[2];
            Empresa e = new Empresa("Cessa",13555550);
            switch(idcliente){
                case 1 :

                    facturas[0]= new Factura(e,154,2021,5000, Mes.DICIEMBRE);
                    facturas[1]=new Factura(e,326,2022,5200, Mes.ENERO);
                    facturasPorCliente.put(1,facturas);
                    break;
                case 2:
                    facturas[0]= new Factura(e,325,2023,5000, Mes.DICIEMBRE);
                    facturas[1]= new Factura(e,325,2023,5000, Mes.DICIEMBRE);
                    facturasPorCliente.put(2,facturas);
                    break;
                default:break;
            }
            return facturasPorCliente.get(idcliente);
        }

    }

    @Override
    public String pagarFacturas(Factura[] facturasAPagar, int idcliente) throws RemoteException {
        double montopagado=0;
        for(Factura f:facturasAPagar){
            f.setEstado(Estado.PAGADO);
            montopagado+=f.getMonto();
        }
        return "Se pago en total: "+montopagado+"bs de Cessa";
    }

//    public String pagarFacturas (Factura[] facturasAPagar, int idcliente) throws RemoteException {
//        Factura[] facturasBD = facturasPorCliente.get(idcliente);
//        double montopagado = 0;
//        for (Factura f : facturasAPagar){
//            for (Factura fact : facturasBD){
//                if (f==fact){
//                    fact.setEstado(Estado.PAGADO);
//                    montopagado+= fact.getMonto();
//                }
//            }
//        }
//        return "Monto pagado de Cessa es: "+montopagado +"bs";
//
//    }
}
