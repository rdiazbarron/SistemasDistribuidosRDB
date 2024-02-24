package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorDeTareas gestor = new GestorDeTareas();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        while (true) {
            
            System.out.println("1. agregar Tarea");
            System.out.println("2. Marcar Tarea como Completada");
            System.out.println("3.Mostrar Tareas");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea nueva

            switch (opcion) {
                case 1:
                    System.out.print("Introduce la descripción de la tarea: ");
                    String descripcion = scanner.nextLine();

                    System.out.print("Introduce la fecha límite (d/MM/yyyy): ");
                    String fecha = scanner.nextLine();
                    LocalDate fechaLimite = LocalDate.parse(fecha, formatter);

                    gestor.agregarTarea(new Tarea(descripcion, fechaLimite));

                    break;

                case 2:
                    System.out.print("Introduce la descripción de la tarea a completar: ");
                    String descripcionParaCompletar = scanner.nextLine();

                    gestor.marcarTareaComoCompletada(descripcionParaCompletar);
                    break;

                case 3:
                    System.out.println("Lista de Tareas:");
                    gestor.mostrarTareas();
                    break;

                case 4:

                   break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }
}
