namespace WinFormsSereci
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Certificados.WebServiceSoapClient cert = new Certificados.WebServiceSoapClient(
                new Certificados.WebServiceSoapClient.EndpointConfiguration());
            
            String text1 = textBox1.Text;
            String resultado = "";
            switch (comboBox1.SelectedIndex)
            {
                case 0:
                    Certificados.CertificadoDefuncion certdef = cert.ObtenerCertificadoDefuncion(text1);
                    resultado = $"{certdef.ci},{certdef.nombres},{certdef.primer_apellido},{certdef.segundo_apellido},{certdef.fecha_defuncion.ToString("dd/MM/yyyy")},{certdef.causa_defuncion}";
                    break;
                case 1:
                    Certificados.CertificadoMatrimonio certmat = cert.ObtenerCertificadoMatrimonio(text1);
                    resultado = $"{certmat.ci_esposo},{certmat.nombres_esposo},{certmat.primer_apellido_esposo},{certmat.segundo_apellido_esposo},{certmat.ci_esposa},{certmat.nombres_esposa},{certmat.primer_apellido_esposa},{certmat.segundo_apellido_esposa}";
                    break;
                case 2:
                    Certificados.CertificadoNacimiento certnac = cert.ObtenerCertificadoNacimiento(text1);
                    resultado = $"{certnac.ci},{certnac.nombres},{certnac.fecha_nacimiento},{certnac.nombre_padre},{certnac.apellidos_padre},{certnac.nombre_madre},{certnac.apellidos_madre}";
                    break;
                case 3:
                    Certificados.Persona datos = cert.ObtenerDatos(text1);
                    resultado = $"{datos.ci},{datos.nombres},{datos.primer_apellido},{datos.segundo_apellido},{datos.fecha_nacimiento},{datos.sexo},{datos.estado_civil}";
                    break;
                default:
                    break;
            }
            label3.Text = resultado;

        }
    }
}
