package org.example.EjerciciosEnClases.RMIPagos2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class Cotes extends UnicastRemoteObject implements IEmpresa {
    public Map<Integer,Factura[]> facturasPorCliente;

    protected Cotes() throws RemoteException
    {
        super();
        facturasPorCliente = new HashMap<>();//necesitas inicalizar
    }

    @Override
    public Factura[] calcularFacturas(int idcliente) throws RemoteException
    {

        if(facturasPorCliente.containsKey(idcliente)){
            return facturasPorCliente.get(idcliente);//devuleve directamente sin crear nada
        }
        else{

            Empresa e = new Empresa("Cotes",13555550);
            switch(idcliente){
                case 1 :
                    Factura[] facturas = new Factura[2];
                    facturas[0]= new Factura(e,10,2021,99, Mes.DICIEMBRE);
                    facturas[1]=new Factura(e,11,2022,99, Mes.ENERO);
                    facturasPorCliente.put(1,facturas);
                    break;
                case 2:
                    Factura[] facturas2 = new Factura[3];
                    facturas2[0]= new Factura(e,100,2023,25, Mes.DICIEMBRE);
                    facturas2[1]= new Factura(e,101,2022,25, Mes.DICIEMBRE);
                    facturas2[2]= new Factura(e,102,2023,25, Mes.DICIEMBRE);
                    facturasPorCliente.put(2,facturas2);
                    break;
                default:break;
            }
            return facturasPorCliente.get(idcliente);
        }

    }

    @Override
    public String pagarFacturas(Factura[] facturasAPagar,int idcliente) throws RemoteException {
        double montopagado=0;
        for(Factura f:facturasAPagar){
            f.setEstado(Estado.PAGADO);
            montopagado+=f.getMonto();
        }
        return "Se pago en total: "+montopagado+"bs de Cotes";
    }
//  public String pagarFacturas (Factura[] facturasAPagar, int idcliente) throws RemoteException {
//      Factura[] facturasBD = facturasPorCliente.get(idcliente);
//      double montopagado = 0;
//      for (Factura f : facturasAPagar){
//          for (Factura factBD : facturasBD){
//              if (f==factBD){
//                  factBD.setEstado(Estado.PAGADO);
//                  montopagado+= factBD.getMonto();
//              }
//          }
//      }
//      return "Monto pagado de Cottes es: "+montopagado +"bs";
//
//  }
}

