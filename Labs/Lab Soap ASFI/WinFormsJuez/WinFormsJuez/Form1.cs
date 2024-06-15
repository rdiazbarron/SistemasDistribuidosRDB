
//using System.Collections.Generic;

//namespace WinFormsJuez
//{
//    public partial class Form1 : Form
//    {
//        public Form1()
//        {
//            InitializeComponent();
//        }

//        private void button1_Click(object sender, EventArgs e)
//        {
//            try
//            {
//                ServiceASFIR.WebServiceSoapClient cliente = new ServiceASFIR.WebServiceSoapClient
//                    (new ServiceASFIR.WebServiceSoapClient.EndpointConfiguration());

//                String a = textBox1.Text;
//                String b = textBox2.Text;
//                String c = textBox3.Text;

//                ServiceASFIR.Cuenta[] cuentasRecibidas = cliente.buscar(a, b, c);

//                dataGridView1.Rows.Clear();
//                dataGridView1.Columns.Clear();

//                // Configurar columnas si aún no están configuradas
//                if (dataGridView1.Columns.Count == 0)
//                {
//                    dataGridView1.Columns.Add("Banco", "Banco");
//                    dataGridView1.Columns.Add("nrocuenta", "Número de Cuenta");
//                    dataGridView1.Columns.Add("ci", "CI");
//                    dataGridView1.Columns.Add("nombres", "Nombres");
//                    dataGridView1.Columns.Add("apellidos", "Apellidos");
//                    dataGridView1.Columns.Add("saldo", "Saldo");
//                }

//                // Añadir las cuentas al DataGridView
//                foreach (ServiceASFIR.Cuenta cuenta in cuentasRecibidas)
//                {
//                    dataGridView1.Rows.Add(cuenta.Banco, cuenta.nrocuenta, cuenta.ci, cuenta.nombres, cuenta.apellidos, cuenta.saldo.ToString("N2"));
//                }
//            }
//            catch (Exception ex)
//            {
//                MessageBox.Show("Error: " + ex.Message);
//            }
//        }
//    }
//}

using System;
using System.Collections.Generic;
using System.Windows.Forms;
using System.Runtime.CompilerServices;
using System.Web;
using Newtonsoft.Json;
using System.Net.Http;
using System.Threading.Tasks;

namespace WinFormsJuez
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private async void button1_Click(object sender, EventArgs e)  // Añade async aquí
        {
            try
            {
                ServiceASFIR.WebServiceSoapClient cliente = new ServiceASFIR.WebServiceSoapClient(new ServiceASFIR.WebServiceSoapClient.EndpointConfiguration());

                String a = textBox1.Text;
                String b = textBox2.Text;
                String c = textBox3.Text;

                // Usar await para obtener el resultado asincrónicamente
                ServiceASFIR.Cuenta[] cuentasRecibidas = await cliente.buscarAsync(a, b, c);

                dataGridView1.Rows.Clear();
                dataGridView1.Columns.Clear();

                if (dataGridView1.Columns.Count == 0)
                {
                    dataGridView1.Columns.Add("Banco", "Banco");
                    dataGridView1.Columns.Add("nrocuenta", "Número de Cuenta");
                    dataGridView1.Columns.Add("ci", "CI");
                    dataGridView1.Columns.Add("nombres", "Nombres");
                    dataGridView1.Columns.Add("apellidos", "Apellidos");
                    dataGridView1.Columns.Add("saldo", "Saldo");
                }

                foreach (ServiceASFIR.Cuenta cuenta in cuentasRecibidas)
                {
                    dataGridView1.Rows.Add(cuenta.Banco, cuenta.nrocuenta, cuenta.ci, cuenta.nombres, cuenta.apellidos, cuenta.saldo.ToString("N2"));
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error: " + ex.Message);
            }
        }
    }
}

