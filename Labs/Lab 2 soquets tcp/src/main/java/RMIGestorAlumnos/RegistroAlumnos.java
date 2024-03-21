package RMIGestorAlumnos;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RegistroAlumnos extends UnicastRemoteObject implements IRegistroAlumnos {

    ArrayList<Alumno> listaAlumnos;
    public RegistroAlumnos() throws RemoteException {
        super();
        listaAlumnos = new ArrayList<>();

    }

    @Override
    public Alumno registrarAlumno(Alumno alumno) throws RemoteException {
        listaAlumnos.add(alumno);
        return alumno;
    }

    @Override
    public ArrayList<Alumno> mostrarAlumnos() throws RemoteException {
        return listaAlumnos;
    }
}
