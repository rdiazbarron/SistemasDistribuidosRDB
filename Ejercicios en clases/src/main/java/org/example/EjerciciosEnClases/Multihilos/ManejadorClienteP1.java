package org.example.EjerciciosEnClases.Multihilos;

import java.io.*;
import java.net.Socket;

public class ManejadorClienteP1 extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    public ManejadorClienteP1(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            // Enviar mensaje de bienvenida
            dos.writeUTF("Bienvenido al chat, escribe 'salir' para terminar la conversación");

            String cadenaEnviadaPorCliente;
            // Escuchar mensajes del cliente y retransmitirlos
            while ((cadenaEnviadaPorCliente = dis.readUTF()) != null && !cadenaEnviadaPorCliente.equals("salir")) {
                // Reenviar mensaje a todos los clientes conectados

                    for (ManejadorClienteP1 clienteConectado : ServerMultihiloP1.clientesConectados) {
                        if (clienteConectado != this) {
                            clienteConectado.dos.writeUTF(cadenaEnviadaPorCliente);
                        }
                    }

            }
            // Manejar la desconexion del cliente
            System.out.println("Cliente " + this.s + " se ha desconectado.");
            ServerMultihiloP1.clientesConectados.remove(this);
            this.dis.close();
            this.dos.close();
            this.s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}