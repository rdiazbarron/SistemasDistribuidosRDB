package org.example.EjerciciosEnClases.RMIPagos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicioCotes extends UnicastRemoteObject implements IServicioCotes {

    private Map<Integer, List<Factura>> facturasCotes;

    protected ServicioCotes() throws RemoteException {
        super();
        facturasCotes = new HashMap<>();
        agregarFactura(1, new Factura(new Empresa("Cotes", 563878L), 1111, 2021, 15000.0, Mes.SEPTIEMBRE));
        agregarFactura(2, new Factura(new Empresa("Cotes", 563878L), 1112, 2022, 61000.0, Mes.SEPTIEMBRE));
        agregarFactura(1, new Factura(new Empresa("Cotes", 563878L), 1113, 2022, 70100.0, Mes.SEPTIEMBRE));
        agregarFactura(2, new Factura(new Empresa("Cotes", 563878L), 1114, 2022, 70101.0, Mes.SEPTIEMBRE));
        agregarFactura(1, new Factura(new Empresa("Cotes", 563878L), 1115, 2022, 70100.0, Mes.SEPTIEMBRE));
    }

    private void agregarFactura(int i, Factura cotes) {
        if (facturasCotes.containsKey(i)) {
            facturasCotes.get(i).add(cotes);
        } else {
            List<Factura> facturas = new ArrayList<>();
            facturas.add(cotes);
            facturasCotes.put(i, facturas);
        }
    }

    @Override
    public List<Factura> calcularFacturas(int idcliente) throws RemoteException {

        //devolver todas las facturas del idcliente

        return facturasCotes.get(idcliente);

    }

    public String pagarFacturas(int idcliente, ArrayList<Integer> facturasAPagar) throws RemoteException {
        double monto_total = 0;
        List<Factura> facturasAEliminar = new ArrayList<>();
        if( facturasCotes.containsKey(idcliente))
        {
            List<Factura> facturasDelCliente = facturasCotes.get(idcliente);
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

