using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

/// <summary>
/// Descripción breve de WebService
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
[System.Web.Script.Services.ScriptService]
public class WebService : System.Web.Services.WebService
{

    public WebService()
    {

        //Elimine la marca de comentario de la línea siguiente si utiliza los componentes diseñados 
        //InitializeComponent(); 
    }

    

    [WebMethod]
    public Persona ObtenerDatos(String ci)
    {
        Persona[] personas = new Persona[3];


        Persona persona1 = new Persona("123456", "luis", "diaz", "lopez", "15/15/2001", "M", "Soltero");
        Persona persona2 = new Persona("123457", "miguel", "flores", "Ayma", "15/15/2002", "F", "Casado");
        Persona persona3 = new Persona("123458", "marco", "Ortega", "Arce", "15/15/2003", "M", "Soltero");
        personas[0] = persona1;
        personas[1] = persona2;
        personas[2] = persona3;

        for (int i = 0; i <= 2; i++) {

            if (personas[i].ci == ci)
            {
                return personas[i];
            }
        
        }
        return null;

    }

    [WebMethod]
    public CertificadoNacimiento ObtenerCertificadoNacimiento(String ci)
    {
        CertificadoNacimiento[] certificados = new CertificadoNacimiento[3];

        CertificadoNacimiento cert1 = new CertificadoNacimiento("1234567","luis","12/12/1999","pedro","rivera","maria","cabezas");
        CertificadoNacimiento cert2 = new CertificadoNacimiento("1234565", "lucho", "11/12/1999", "thomas", "muller", "angelica", "carrasco");
        CertificadoNacimiento cert3 = new CertificadoNacimiento("1234564", "luisito", "10/12/1999", "karim", "benzema", "lili", "caba");

        certificados[0] = cert1;
        certificados[1] = cert2;
        certificados[2] = cert3;


        for(int i = 0; i <= 2; i++)
        {

            if (certificados[i].ci == ci)
            {
                return certificados[i];
            }

        }
        return null;

    }

    [WebMethod]
    public CertificadoMatrimonio ObtenerCertificadoMatrimonio(String ci)
    {      

        CertificadoMatrimonio[] certificados = new CertificadoMatrimonio[3];

        CertificadoMatrimonio cert1 = new CertificadoMatrimonio("1234567", "luis", "diaz", "lopez", "1234568", "maria", "cabezas", "cabezas");
        CertificadoMatrimonio cert2 = new CertificadoMatrimonio("1234565", "lucho", "diaz", "lopez", "1234566", "angelica", "carrasco", "carrasco");
        CertificadoMatrimonio cert3 = new CertificadoMatrimonio("1234564", "luisito", "diaz", "lopez", "1234563", "lili", "caba", "caba");


        certificados[0] = cert1;
        certificados[1] = cert2;
        certificados[2] = cert3;

        for (int i = 0; i <= 2; i++)
        {

            if (certificados[i].ci_esposo == ci)
            {
                return certificados[i];
            }

        }
        return null;
    }

    [WebMethod]
    public CertificadoDefuncion ObtenerCertificadoDefuncion(String ci)
    {
        CertificadoDefuncion[] certificados = new CertificadoDefuncion[3];

        CertificadoDefuncion cert1 = new CertificadoDefuncion("1234567", "luis", "diaz", "lopez", DateTime.Now, "muerte natural");
        CertificadoDefuncion cert2 = new CertificadoDefuncion("1234565", "lucho", "diaz", "lopez", DateTime.Now, "muerte natural");
        CertificadoDefuncion cert3 = new CertificadoDefuncion("1234564", "luisito", "diaz", "lopez", DateTime.Now, "muerte natural");


        certificados[0] = cert1;
        certificados[1] = cert2;
        certificados[2] = cert3;

        for (int i = 0; i <= 2; i++)
        {

            if (certificados[i].ci == ci)
            {
                return certificados[i];
            }

        }
        return null;
    }






}
