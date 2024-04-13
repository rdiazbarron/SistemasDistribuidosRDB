package org.example.EjerciciosEnClases.RMIEjer2parcial;


import java.io.Serializable;

public class Cuenta implements Serializable {
    private Banco2 banco;
    private String nrocuenta;
    private String ci;
    private String nombres;
    private String apellidos;

    private double monto;

    public Cuenta(Banco2 banco, String nrocuenta, String ci, String nombres, String apellidos, double monto) {
        this.banco = banco;
        this.nrocuenta = nrocuenta;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.monto = monto;
    }


    public Banco2 getBanco() {
        return banco;
    }


    public String getNrocuenta() {
        return nrocuenta;
    }

    public void setNrocuenta(String nrocuenta) {
        this.nrocuenta = nrocuenta;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    //get monto
    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "banco=" + banco +
                ", nrocuenta='" + nrocuenta + '\'' +
                ", ci='" + ci + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                '}';
    }
}