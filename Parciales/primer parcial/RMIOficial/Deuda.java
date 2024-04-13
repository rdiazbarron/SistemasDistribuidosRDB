package org.example.EjerciciosEnClases.RMIOficial;

import java.io.Serializable;

public class Deuda implements Serializable {
    public String ci;
    public int anio;

    public String impuesto;

    public double monto;

    public Deuda(String ci, int anio, String impuesto, double monto) {
        this.ci = ci;
        this.anio = anio;
        this.impuesto = impuesto;
        this.monto = monto;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Deuda{" +
                "ci='" + ci + '\'' +
                ", anio=" + anio +
                ", impuesto=" + impuesto +
                ", monto=" + monto +
                '}';
    }
}
