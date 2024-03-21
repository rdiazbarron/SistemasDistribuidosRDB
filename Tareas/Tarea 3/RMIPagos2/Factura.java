package org.example.EjerciciosEnClases.RMIPagos2;


import java.io.Serializable;

public class Factura implements Serializable {
    public Empresa empresa;
    public int idfactura;
    public Mes mes;
    public int anio;

    public double monto;

    public Estado estado;

    public Factura(Empresa empresa, int idfactura, int anio, double monto, Mes mes) {
        this.empresa = empresa;
        this.idfactura = idfactura;
        this.mes = mes;
        this.anio = anio;
        this.monto = monto;
        this.estado = Estado.NO_PAGADO;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "empresa=" + empresa +
                ", idfactura=" + idfactura +
                ", mes=" + mes +
                ", anio=" + anio +
                ", monto=" + monto +
                ", estado=" + estado +
                '}';
    }

    public Estado getEstado() {
        return estado;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
