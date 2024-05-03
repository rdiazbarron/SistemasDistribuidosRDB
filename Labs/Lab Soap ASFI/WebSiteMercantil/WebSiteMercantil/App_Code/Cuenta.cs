using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Cuenta
/// </summary>
public class Cuenta
{

    public String Banco;
    public String nrocuenta;
    public String ci;
    public String nombres;
    public String apellidos;
    public Double saldo;

    public Cuenta(string Banco, string nrocuenta, string ci, string nombres, string apellidos, double saldo)
    {
        this.Banco = Banco;
        this.nrocuenta = nrocuenta;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.saldo = saldo;
    }

    public Cuenta()
    {
        this.Banco = "";
        this.nrocuenta = "";
        this.ci = "";
        this.nombres = "";
        this.apellidos = "";
        this.saldo = 0;
    }



}


