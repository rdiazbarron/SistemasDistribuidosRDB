package org.example.EjerciciosEnClases.RMIPagos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioCessa extends UnicastRemoteObject implements IServicioCessa {

    private Map<Integer, List<Factura>> facturasCessa;
    protected ServicioCessa() throws RemoteException {
        super();
        facturasCessa = new HashMap<>();
        agregarFactura(1, new Factura(new Empresa("Cotes", 563878L), 11, 2021, 1500.0,Mes.SEPTIEMBRE));
        agregarFactura(2, new Factura(new Empresa("Cotes", 563878L), 12,  2022, 6100.0, Mes.SEPTIEMBRE));
        agregarFactura(1, new Factura(new Empresa("Cotes", 563878L), 13,  2022, 7010.0, Mes.SEPTIEMBRE));
        agregarFactura(2, new Factura(new Empresa("Cotes", 563878L), 14,  2022, 7011.0, Mes.SEPTIEMBRE));
        agregarFactura(1, new Factura(new Empresa("Cotes", 563878L), 15, 2022, 7010.0,Mes.SEPTIEMBRE ));
    }

    private void agregarFactura(int i, Factura cotes) {
        if (facturasCessa.containsKey(i)) {
            facturasCessa.get(i).add(cotes);
        } else {
            List<Factura> facturas = new ArrayList<>();
            facturas.add(cotes);
            facturasCessa.put(i, facturas);
        }
    }
    @Override
    public List<Factura> calcularFacturas(int idcliente) throws RemoteException {

        //recorrer el hasmao facturascotes y devolver un array de facturas del cliente
        return facturasCessa.get(idcliente);

    }

    @Override
    public String pagarFacturas(int idcliente, ArrayList<Integer> facturasAPagar) throws RemoteException {
        double monto_total = 0;
        List<Factura> facturasAEliminar = new ArrayList<>();
        if( facturasCessa.containsKey(idcliente))
        {
            List<Factura> facturasDelCliente = facturasCessa.get(idcliente);
            for (Integer i : facturasAPagar){//1.2.4
                for (Factura factura : facturasDelCliente){
                    if (factura.getIdfactura() == i){
                        facturasAEliminar.add(factura);
                        monto_total = monto_total + factura.getMonto();
                    }
                }
            }
            facturasDelCliente.removeAll(facturasAEliminar);
            return "El total a pagar es: " + monto_total;
        }
        else{
            return "El cliente no tiene facturas";
        }



    }}