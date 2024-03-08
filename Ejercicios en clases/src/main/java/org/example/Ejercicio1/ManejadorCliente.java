package org.example.Ejercicio1;

import java.io.*;
import java.net.Socket;

public class ManejadorCliente extends Thread{
    static int respuestaMandar;
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    public ManejadorCliente(Socket s, DataInputStream dis, DataOutputStream dos){
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }
    @Override
    public void run() {
        try{
            while(true){
                String solicitud;
                while( (solicitud=dis.readUTF()) !=null && !solicitud.equals("salir")){
                    String resultadoPeticion = procesarPeticion(solicitud);
                    dos.writeUTF(resultadoPeticion);
                    String recibido1 = dis.readUTF();
                    System.out.println("Respuesta del cliente:"+recibido1);
                    int respuestaCliente = Integer.parseInt(recibido1);
                    String respuesta3;
                    if (respuestaCliente == respuestaMandar) {
                        respuesta3 = "Resp:bien muchacho";
                    } else {
                        respuesta3 = "Resp:mal muy mal";
                    }
                    dos.writeUTF(respuesta3);
                }


            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static String procesarPeticion(String s){
        String[] solicitudProcesada = s.split(":");
        if(solicitudProcesada[1].equals("sumar")){
            int vara = (int)(Math.round(Math.random()*100));
            int varb = (int)(Math.round(Math.random()*100));

            respuestaMandar =vara+varb;
            return vara+"+"+varb;
        }
        if(solicitudProcesada[1].equals("restar")){
            int vara = (int)(Math.round(Math.random()*100));
            int varb = (int)(Math.round(Math.random()*100));

            respuestaMandar =vara-varb;
            return vara+"-"+varb;
        }
        if(solicitudProcesada[1].equals("multiplicar")){
            int vara = (int)(Math.round(Math.random()*100));
            int varb = (int)(Math.round(Math.random()*100));
            respuestaMandar =vara*varb;
            return vara+"*"+varb;
        }
        if(solicitudProcesada[1].equals("dividir")){
            int vara = (int)(Math.round(Math.random()*100));
            int varb = (int)(Math.round(Math.random()*100));
            respuestaMandar =vara/varb;
            return vara+"/"+varb;
        }else{
            return "protocolo no existe";
        }
    }
}
