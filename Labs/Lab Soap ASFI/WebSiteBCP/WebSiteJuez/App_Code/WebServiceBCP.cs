using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

/// <summary>
/// Descripción breve de WebServiceBCP
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
// [System.Web.Script.Services.ScriptService]
public class WebServiceBCP : System.Web.Services.WebService
{

    public WebServiceBCP()
    {

        //Elimine la marca de comentario de la línea siguiente si utiliza los componentes diseñados 
        //InitializeComponent(); 
    }

    [WebMethod]
    public Cuenta[] buscar(String ci, String nombres, String apellidos)
    {


        if ((ci == "7687682") && (nombres == "Pedro") && (apellidos == "Segobia"))
        {
            Cuenta[] cuentas = new Cuenta[1];

            Cuenta cuenta1 = new Cuenta("BCP", "657654", "7687682", "Juan", "Segobia", 2000);

            cuentas[0] = cuenta1;

            return cuentas;
        }
        if ((ci == "455787") && (nombres == "Maria") && (apellidos == "Parra"))
        {
            Cuenta[] cuentas = new Cuenta[1];

            Cuenta cuenta1 = new Cuenta("BCP", "657255", "455787", "Ricardo", "Centellas", 5890);

            cuentas[0] = cuenta1;

            return cuentas;
        }
        return null;

    }
    [WebMethod]
    public Boolean congelar(String cuenta, int monto)
    {
        if ((cuenta == "657654") && (monto <= 2000))
        {
            return true;
        }
        if ((cuenta == "657255") && (monto <= 5890))
        {
            return true;
        }
        return false;
    }



}
