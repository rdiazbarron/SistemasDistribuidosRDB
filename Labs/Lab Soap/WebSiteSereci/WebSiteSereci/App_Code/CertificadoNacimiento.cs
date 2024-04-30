using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de Persona2
/// </summary>
public class CertificadoNacimiento
{
    public String ci;
    public String nombres;
    public String primer_apellido;
    public String segundo_apellido;
    public String fecha_nacimiento;
    public String nombre_padre;
    public String apellidos_padre;
    public String nombre_madre;
    public String apellidos_madre;

    public CertificadoNacimiento(String ci, String nombres, String fecha_nacimiento, String nombre_padre, String apellidos_padre, String nombre_madre, String apellidos_madre)
    {
        this.ci = ci;
        this.nombres = nombres;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nombre_padre = nombre_padre;
        this.apellidos_padre = apellidos_padre;
        this.nombre_madre = nombre_madre;
        this.apellidos_madre = apellidos_madre;
    }

    public CertificadoNacimiento()
    {
        this.ci = "";
        this.nombres = "";
        this.fecha_nacimiento = "";
        this.nombre_padre = "";
        this.apellidos_padre = "";
        this.nombre_madre = "";
        this.apellidos_madre = "";
    }
    public override string ToString()
    {
        return $"CI: {ci}, Nombres: {nombres}, Fecha Nacimiento: {fecha_nacimiento}, Nombre Padre: {nombre_padre}, Apellidos Padre: {apellidos_padre}, Nombre Madre: {nombre_madre}, Apellidos Madre: {apellidos_madre}";
    }   
}