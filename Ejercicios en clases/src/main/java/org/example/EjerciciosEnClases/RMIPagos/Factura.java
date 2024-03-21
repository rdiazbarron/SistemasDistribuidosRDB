package org.example.EjerciciosEnClases.RMIPagos;

import java.io.Serializable;

//serialible es para que se pueda enviar por la red
public class Factura implements Serializable {
 public Empresa empresa;
 public int idfactura;
 public Mes mes;
 public int anio;

 public double monto;

    public Factura(Empresa empresa, int idfactura, int anio, double monto, Mes mes) {
        this.empresa = empresa;
        this.idfactura = idfactura;
        this.anio = anio;
        this.monto = monto;
        this.mes = Mes.MARZO;
    }

    @Override
    public String toString() {
        return "Factura{" +

                ", idfactura=" + idfactura +
                ", mes=" + mes +
                ", anio=" + anio +
                ", monto=" + monto +
                '}';
    }

    public int getIdfactura() {
        return idfactura;
    }

    public double getMonto() {
        return monto;
    }
}
