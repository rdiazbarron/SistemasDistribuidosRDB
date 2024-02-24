package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int opcion = 1;
        int salario;
        String nombre, dep;
        Empresa empresa1 = new Empresa();

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner sc = new Scanner(System.in);
        while (opcion != 0) {


            System.out.println("1.agregar empleado a empresa");
            System.out.println("2 Mostrar empleados");
            System.out.println("3. ver suma total de sueldos");
            System.out.println("Que desea ahcer"
            );
            opcion = sc.nextInt();

            sc.nextLine();
            switch (opcion) {
                case 1:

                    System.out.println("Nombre del empleado");
                    nombre = sc.nextLine();
                    System.out.println("salario del emple");
                    salario = sc.nextInt();
                    System.out.println("dep del empleado");
                    dep = sc.next();

                    Empleado p1 = new Empleado(nombre, salario, dep);
                    empresa1.agregarEmpleado(p1);
                    break;
                case 2:
                    System.out.println("todos los emplados");
                    empresa1.devolverTodos();
                    break;
                case 3:
                    double suma = empresa1.calcularSalario();
                    System.out.println("La suma de todos los salraios es:" + suma);
                    break;


            }


        }
    }}