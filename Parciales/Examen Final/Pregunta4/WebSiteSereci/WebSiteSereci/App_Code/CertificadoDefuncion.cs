using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de CertificadoDefuncion
/// </summary>
public class CertificadoDefuncion
{
    public String ci;
    public String nombres;
    public String primer_apellido;
    public String segundo_apellido;
    public DateTime fecha_defuncion;
    public String causa_defuncion;

    public CertificadoDefuncion(string ci, string nombres, string primer_apellido, string segundo_apellido, DateTime fecha_defuncion, string causa_defuncion)
    {
        this.ci = ci;
        this.nombres = nombres;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.fecha_defuncion = fecha_defuncion;
        this.causa_defuncion = causa_defuncion;
    }

    public CertificadoDefuncion()
    {
        this.ci = "";
        this.nombres = "";
        this.primer_apellido = "";
        this.segundo_apellido = "";
        this.fecha_defuncion = DateTime.Now;
        this.causa_defuncion = "";
    }
    public override string ToString()
    {
        return $"CI: {ci}, Nombres: {nombres}, Primer Apellido: {primer_apellido}, Segundo Apellido: {segundo_apellido}, Fecha Defunción: {fecha_defuncion.ToString("dd/MM/yyyy")}, Causa Defunción: {causa_defuncion}";
    }

}