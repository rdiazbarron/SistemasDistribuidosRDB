package Pregunta2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMultihiloP2 {
    public static void main(String[] args) throws IOException {
        try {
            Scanner sc = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");
            // establish the connection with server port 5056
            Socket s = new Socket(ip, 5056);
            // obtaining input and out streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            Thread leerTareas = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        try {
                            String mensaje = dis.readUTF();
                            if (!mensaje.equals("")) {
                                System.out.println("Tareas actualizadas:\n" + mensaje);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            });

            // Hilo para enviar mensajes al servidor
            Thread acciones = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String mensaje = sc.nextLine();
                        try {
                            switch(mensaje.toLowerCase())
                            {

                                case "1":
                                    System.out.println("Introduce la tarea a agregar:");
                                    String enviarServidor = sc.next();
                                    dos.writeUTF("1:"+enviarServidor);
                                    break;
                                case "2":
                                    System.out.println("Introduce el nombre de la tarea a eliminar:");
                                    String tareaAEliminar = sc.next(); // Puedes necesitar ajustar esto según cómo identificas las tareas
                                    dos.writeUTF("2:" + tareaAEliminar);
                                    break;
                                case "3":
                                    dos.writeUTF("3:3");
                                case "4":
                                    dos.writeUTF("salir");
                                    System.out.println("Cerrando esta conexión : " + s);
                                    s.close();
                                    System.out.println("Conexión cerrada");
                                    break;

                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            leerTareas.start();
            acciones.start();
        } catch (Exception e) {
            e.printStackTrace();
        }}}