package RMIFirst;

import RMIFirst.ISaludo;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class Cliente {//
    public static void main(String[] args) throws IOException, NotBoundException {
//        ISaludo nuevosaludo; //a traves de la interfaz puedo....convierte este objeto que esta en el rmlocalhost
//        nuevosaludo = (ISaludo)Naming.lookup("rmi://localhost/Saludo");//es como si instanciaramos
//        System.out.println(nuevosaludo.saludar());


        IFactorial nuevofactorial;
        nuevofactorial =(IFactorial)Naming.lookup("rmi://localhost/Factorial");//insntiaciar un obj remoto
        System.out.println("El factorial que envia: ");
        System.out.println(nuevofactorial.calcularFactorial(3));
    }
}
