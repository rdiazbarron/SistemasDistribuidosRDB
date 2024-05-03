using ServiceASFI;
using System.Collections.Generic;

namespace WinFormsJuez
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            ServiceASFI.WebServiceSoapClient cliente = new ServiceASFI.WebServiceSoapClient(new ServiceASFI.WebServiceSoapClient.EndpointConfiguration());
            Cuenta[] cuentasRecibidas = cliente.buscar(textBox1.Text,textBox2.Text,textBox3.Text);
            dataGridView1.Rows.Clear();
            dataGridView1.Columns.Clear();

            // Configurar columnas si aún no están configuradas
            if (dataGridView1.Columns.Count == 0)
            {
                dataGridView1.Columns.Add("Banco", "Banco");
                dataGridView1.Columns.Add("nrocuenta", "Número de Cuenta");
                dataGridView1.Columns.Add("ci", "CI");
                dataGridView1.Columns.Add("nombres", "Nombres");
                dataGridView1.Columns.Add("apellidos", "Apellidos");
                dataGridView1.Columns.Add("saldo", "Saldo");
            }

            // Añadir las cuentas al DataGridView
            foreach (Cuenta cuenta in cuentasRecibidas)
            {
                dataGridView1.Rows.Add(cuenta.Banco, cuenta.nrocuenta, cuenta.ci, cuenta.nombres, cuenta.apellidos, cuenta.saldo.ToString("N2"));
            }
        }
    }
}
