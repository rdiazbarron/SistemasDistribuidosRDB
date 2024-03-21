package org.example.EjerciciosEnClases.RMIGestorAlumnos;

import RMIFirst.IFactorial;

import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    static IRegistroAlumnos registroAlumnos;
    public static void main(String[] args) throws IOException, NotBoundException {

        registroAlumnos =(IRegistroAlumnos) Naming.lookup("rmi://localhost/Registro");//insntiaciar un obj remoto
        listarMenu();

    }
    public static void listarMenu() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        int opcion =1;
        while(opcion!=3){
            System.out.println("1. Registrar alumno");
            System.out.println("2. Mostrar alumnos");
            System.out.println("3 Salir");

            System.out.println("Introduzca una opcion: ");
            String respuesta = sc.next();
            switch(respuesta){
                case "1":
                    registrarAlumno();
                    break;
                case "2":
                    mostrarAlumnos();
                    break;
                case "3":
                    break;
                default:
                    System.out.println("opcion incorrecta ingresada");
                    break;
            }
        }
    }
    public static void registrarAlumno() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el nombre del alumno: ");
        String nombre = sc.nextLine();
        System.out.println("Introduzca la carrera del estudiante: ");
        String carrera = sc.nextLine();
        System.out.println("Introduzca el CU del estudiante: ");
        String cu = sc.nextLine();
        Alumno alumno = new Alumno(nombre,carrera,Integer.parseInt(cu));
        Alumno a = registroAlumnos.registrarAlumno(alumno);

    }



    private static void mostrarAlumnos() throws RemoteException {
        ArrayList<Alumno> listaRecuperadaAlumnos = new ArrayList<>();
        System.out.println("Mostrando todos los alumnos: ");
        listaRecuperadaAlumnos= registroAlumnos.mostrarAlumnos();
        for (Alumno a :listaRecuperadaAlumnos){
            System.out.println(a.toString());
        }

    }
}

