/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio1;

import java.util.Scanner;

/**
 *
 * @author diazb
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese un número: ");
        int numero = scanner.nextInt();

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1 Calcular Serie Fibonacci");
            System.out.println("2 Calcular Factorial");
            System.out.println("3 Calcular Sumatoria");
            System.out.println("4 Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println(calcularFibonacci(numero));
                    break;

                case 2:
                    System.out.println(calcularFactorial(numero));
                    break;

                case 3:
                    System.out.println(calcularSumatoria(numero));
                    break;

                case 4:
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    private static int calcularFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
        }
    }

    private static int calcularFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calcularFactorial(n - 1);
        }
    }

    private static int calcularSumatoria(int n) {
        if (n==0){
            return 0;
        }
        else{        
            return (n + calcularSumatoria(n-1));

    }
    }
}