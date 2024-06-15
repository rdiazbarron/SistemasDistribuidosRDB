using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Web;
using System.Web.Services;
using System.Web.UI;
using Newtonsoft.Json;
using System.Net.Http;
using System.Threading.Tasks; // Importa el espacio de nombres para Task

[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
[System.Web.Script.Services.ScriptService]
public class WebService : System.Web.Services.WebService
{
    public WebService()
    {
        // Constructor
    }

    [WebMethod]
    public async Task<Cuenta[]> buscar(String ci, String nombres, String apellidos)
    {
        try
        {
            List<Cuenta> cuentas = new List<Cuenta>();

            // Inicializa los clientes SOAP para BCP y Mercantil
            ServiceBCPX.WebServiceBCPSoapClient clienteBCP = new ServiceBCPX.WebServiceBCPSoapClient();
            ServiceBCPX.Cuenta[] cuentasBCP = clienteBCP.buscar(ci, nombres, apellidos);

            ServiceMercantilX.WebServiceMercantilSoapClient clienteMercantil = new ServiceMercantilX.WebServiceMercantilSoapClient();
            ServiceMercantilX.Cuenta[] cuentasMercantil = clienteMercantil.buscar(ci, nombres, apellidos);

            // Añade cuentas de BCP
            if (cuentasBCP != null)
            {
                cuentas.AddRange(cuentasBCP.Select(c => new Cuenta("BCP", c.nrocuenta, c.ci, c.nombres, c.apellidos, c.saldo)));
            }

            // Añade cuentas de Mercantil
            if (cuentasMercantil != null)
            {
                cuentas.AddRange(cuentasMercantil.Select(c => new Cuenta("Mercantil", c.nrocuenta, c.ci, c.nombres, c.apellidos, c.saldo)));
            }

            // Llamada a la API REST del Banco Ganadero
            HttpClient client = new HttpClient();
            string baseUrl = $"http://127.0.0.1:8000/api/cliente?ci={ci}&nombres={nombres}&apellidos={apellidos}";

            HttpResponseMessage response = await client.GetAsync(baseUrl);
            if (response.IsSuccessStatusCode)
            {
                string content = await response.Content.ReadAsStringAsync();
                var cuentasGanadero = JsonConvert.DeserializeObject<List<Cuenta>>(content);
                cuentas.AddRange(cuentasGanadero);
            }

           
            return cuentas.ToArray();
        }
        catch (Exception ex)
        {
            throw new Exception("Error en el método buscar: " + ex.Message);
        }
    }
}
