package Pregunta2;

import org.example.EjerciciosEnClases.ServerMultihiloP1;
import org.example.EjerciciosEnClases.ServerMultihiloP3V2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ManejadorClienteP2 extends Thread {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

   public static ArrayList <String> tareas = new ArrayList<String>();

    public ManejadorClienteP2(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    @Override
    public void run() {
        try {
            // Enviar mensaje de bienvenida
            dos.writeUTF("Bienvenido al chat, escribe 1 para escribir una tarea, 2 para eliminar y 3 para salir");

            String cadenaEnviadaPorCliente;
            // Escuchar mensajes del cliente y retransmitirlos
            while ((cadenaEnviadaPorCliente = dis.readUTF()) != null && !cadenaEnviadaPorCliente.equals("salir")) {
                // Reenviar mensaje a todos los clientes conectados
                    String[] cadenaPartida= cadenaEnviadaPorCliente.split(":");
                    switch(cadenaPartida[0]){
                        case "1": agregarTarea(cadenaPartida[1]);
                            for( ManejadorClienteP2 cliente : ServerMultihiloP2.clientesConectados){
                                cliente.dos.writeUTF(mostrarTareas());
                            }
                            break;
                        case "2": eliminarTarea(cadenaPartida[1]);
                            for( ManejadorClienteP2 cliente : ServerMultihiloP2.clientesConectados){
                                cliente.dos.writeUTF(mostrarTareas());
                            }
                            break;
                        case "3":
                            for( ManejadorClienteP2 cliente : ServerMultihiloP2.clientesConectados){
                                cliente.dos.writeUTF(mostrarTareas());
                            }
                            break;
                        default: break;
                    }
                }
            // Manejar la desconexion del cliente
            System.out.println("Cliente " + this.s + " se ha desconectado.");
            ServerMultihiloP1.clientesConectados.remove(this);
            this.dis.close();
            this.dos.close();
            this.s.close();
        }

        catch (IOException e ){
            e.printStackTrace();
        }

        }

    public void agregarTarea(String tarea){
        tareas.add(tarea);
    }
    public void eliminarTarea(String tarea){
        tareas.remove(tarea);
    }
    public String mostrarTareas() {
        StringBuilder listaDeTareas = new StringBuilder();
        for (String tarea : tareas) {
            listaDeTareas.append(tarea).append("\n");
        }
        return listaDeTareas.toString().trim(); // Para eliminar el último salto de línea
    }
    }


