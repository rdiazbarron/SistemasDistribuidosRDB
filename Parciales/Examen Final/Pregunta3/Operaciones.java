package org.example.EjerciciosEnClases.ExamenFinal3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Operaciones extends UnicastRemoteObject implements IOperaciones {
    static String cadena1;

    protected Operaciones() throws RemoteException {
    }

    @Override
    public boolean introducirValor(String cadena) {
        cadena1 = cadena;
        return true;
    }

    @Override
    public String invertir() {

        StringBuilder sb = new StringBuilder(cadena1);
        return sb.reverse().toString();
    }

    @Override
    public String aumentartresespacios(int num) {

        StringBuilder sb = new StringBuilder(cadena1);
        for (int i = 0; i < num; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public String aumentar(String cadena) {
        cadena1 += cadena;
        return cadena1;
    }
}
