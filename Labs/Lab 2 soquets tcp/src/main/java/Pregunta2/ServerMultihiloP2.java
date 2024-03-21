package Pregunta2;

import org.example.EjerciciosEnClases.ManejadorClienteP1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMultihiloP2
{

    public static ArrayList<ManejadorClienteP2> clientesConectados = new ArrayList<ManejadorClienteP2>();
    public static void main(String[] args) throws IOException
    {

        ServerSocket ss = new ServerSocket(5056);
        while (true)
        {
            Socket s = null;
            try
            {

                s = ss.accept();
                System.out.println("un nuevo cliente se ha conectado : " + s);

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Asignar unnuevo hilopara este cliente ");
                ManejadorClienteP2 mc = new ManejadorClienteP2(s, dis, dos);
                Thread t = new Thread(mc);

                System.out.println("Asignando un nuevo hilo para este cliente");
                clientesConectados.add(mc);
                t.start();
            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}
