using ServiceBCP1;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Web;
using System.Web.Services;
using System.Web.UI;

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
    public Cuenta[] buscar(String ci, String nombres, String apellidos)
    {
        
        ServiceBCP1.WebServiceBCPSoapClient clienteBCP = new ServiceBCP1.WebServiceBCPSoapClient();
        ServiceBCP1.Cuenta[] cuentasBCP = clienteBCP.buscar(ci, nombres, apellidos);

        ServiceMercantil1.WebServiceMercantilSoapClient clienteMercantil = new ServiceMercantil1.WebServiceMercantilSoapClient();
        ServiceMercantil1.Cuenta[] cuentasMercantil = clienteMercantil.buscar(ci, nombres, apellidos);

        
        List<Cuenta> cuentas = new List<Cuenta>();

        foreach (ServiceBCP1.Cuenta c in cuentasBCP)
        {
            cuentas.Add(new Cuenta("BCP", c.nrocuenta, c.ci, c.nombres, c.apellidos, c.saldo));
        }

        foreach (ServiceMercantil1.Cuenta c in cuentasMercantil)
        {
            cuentas.Add(new Cuenta("Mercantil", c.nrocuenta, c.ci, c.nombres, c.apellidos, c.saldo));
        }

       
        return cuentas.ToArray();
    }

}
