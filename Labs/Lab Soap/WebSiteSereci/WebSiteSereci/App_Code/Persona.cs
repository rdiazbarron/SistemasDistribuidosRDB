using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography.X509Certificates;
using System.Web;
using System.Web.DynamicData;

/// <summary>
/// Descripción breve de Class1
/// </summary>
public class Persona
{
    public String ci;
    public String nombres;
    public String primer_apellido;
    public String segundo_apellido;
    public String fecha_nacimiento;
    public String sexo;
    public String estado_civil;

   
    
    public Persona(String ci,String nombres, String primer_apellido, String segundo_apellido,String fecha_nacimiento,String sexo,String estado_civil)
    {
        this.ci = ci;
        this.nombres = nombres;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido=segundo_apellido;
        this.fecha_nacimiento= fecha_nacimiento;
        this.sexo = sexo;
        this.estado_civil = estado_civil;
    }


    public Persona()
    {
        this.ci = "";
        this.nombres = "";
        this.primer_apellido = "";
        this.segundo_apellido = "";
        this.fecha_nacimiento = "";
        this.sexo = "";
        this.estado_civil = "";
    }
    public override string ToString()
    {
        return $"{ci},{nombres},{primer_apellido},{segundo_apellido},{fecha_nacimiento},{sexo},{estado_civil}";
    }



}