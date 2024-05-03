using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

/// <summary>
/// Descripción breve de WebServiceMercantil
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
// [System.Web.Script.Services.ScriptService]
public class WebServiceMercantil : System.Web.Services.WebService
{

    public WebServiceMercantil()
    {

        //Elimine la marca de comentario de la línea siguiente si utiliza los componentes diseñados 
        //InitializeComponent(); 
    }

    [WebMethod]
    public Cuenta[] buscar(String ci, String nombres, String apellidos)
    {
        

        if ((ci == "7687682") && (nombres == "Juan") && (apellidos == "Segobia"))
        {
            Cuenta[] cuentas = new Cuenta[1];

            Cuenta cuenta1 = new Cuenta("Mercantil", "1112450", "7687682", "Juan", "Segobia", 50000);
            
            cuentas[0] = cuenta1;
            
            return cuentas;
        }
        if ((ci == "54654") && (nombres == "Maria") && (apellidos == "Parra"))
        {
            Cuenta[] cuentas = new Cuenta[1];

            Cuenta cuenta1= new Cuenta("Mercantil", "1121454", "54654", "Maria", "Parra", 3000);
           
            cuentas[0] = cuenta1;
            
            return cuentas;
        }
        return null;

    }
    [WebMethod]
    public Boolean congelar(String cuenta, int monto)
    {
        if ((cuenta == "1112450") && (monto <= 3000))
        {
            return true;
        }
        if ((cuenta == "1121454") && (monto <= 50000))
        {
            return true;
        }
        return false;
    }

}
