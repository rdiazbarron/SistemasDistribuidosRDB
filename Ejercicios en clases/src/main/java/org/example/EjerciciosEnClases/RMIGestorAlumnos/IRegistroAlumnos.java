package org.example.EjerciciosEnClases.RMIGestorAlumnos;

import org.example.EjerciciosEnClases.RMIGestorAlumnos.Alumno;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IRegistroAlumnos extends Remote {
    public Alumno registrarAlumno(Alumno alumno) throws RemoteException;
    public ArrayList<Alumno> mostrarAlumnos()throws RemoteException;
}
