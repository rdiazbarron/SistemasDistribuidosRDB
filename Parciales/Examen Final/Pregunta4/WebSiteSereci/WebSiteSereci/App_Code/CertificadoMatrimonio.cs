using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Descripción breve de CertificadoMatrimonio
/// </summary>
public class CertificadoMatrimonio
{
    public String ci_esposo;
    public String nombres_esposo;
    public String primer_apellido_esposo;
    public String segundo_apellido_esposo;
    public String ci_esposa;
    public String nombres_esposa;
    public String primer_apellido_esposa;
    public String segundo_apellido_esposa;

    public CertificadoMatrimonio(string ci_esposo, string nombres_esposo, string primer_apellido_esposo, string segundo_apellido_esposo, string ci_esposa, string nombres_esposa, string primer_apellido_esposa, string segundo_apellido_esposa)
    {
        this.ci_esposo = ci_esposo;
        this.nombres_esposo = nombres_esposo;
        this.primer_apellido_esposo = primer_apellido_esposo;
        this.segundo_apellido_esposo = segundo_apellido_esposo;
        this.ci_esposa = ci_esposa;
        this.nombres_esposa = nombres_esposa;
        this.primer_apellido_esposa = primer_apellido_esposa;
        this.segundo_apellido_esposa = segundo_apellido_esposa;
    }

    public CertificadoMatrimonio()
    {
        this.ci_esposo = "";
        this.nombres_esposo = "";
        this.primer_apellido_esposo = "";
        this.segundo_apellido_esposo = "";
        this.ci_esposa = "";
        this.nombres_esposa = "";
        this.primer_apellido_esposa = "";
        this.segundo_apellido_esposa = ""; ;
    }
    public override string ToString()
    {
        return $"CI Esposo: {ci_esposo}, Nombres Esposo: {nombres_esposo}, Primer Apellido Esposo: {primer_apellido_esposo}, Segundo Apellido Esposo: {segundo_apellido_esposo}, CI Esposa: {ci_esposa}, Nombres Esposa: {nombres_esposa}, Primer Apellido Esposa: {primer_apellido_esposa}, Segundo Apellido Esposa: {segundo_apellido_esposa}";
    }
}