package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        int opcion = 1;
        int desicion;
        String nombre;
        int precio;
        Inventario inv = new Inventario();
        Scanner sc = new Scanner(System.in);

        while(opcion !=0){
            System.out.println("QUE Deseas HACER");

            System.out.println("1.agregar elemento a inventario");
            System.out.println("2 Mostrar inv");
            System.out.println("3. ver suma total");
            opcion =sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("1.LAPTOP   2.CELULAR");
                    desicion = sc.nextInt();
                    System.out.println("Nombre del producto");
                    nombre = sc.next();
                    System.out.println("Precio del prodcuto");
                    precio = sc.nextInt();
                    if(desicion==1){
                        Producto p1 = new Laptop(nombre,precio);
                        inv.agregarProds(p1);

                    }
                    else {
                        Producto p2 = new Celular(nombre,precio);
                        inv.agregarProds(p2);
                    }
                    break;
                case 2 :
                    inv.leerProds();
                    break;
                case 3 :
                    double result = inv.sumaTotal();
                    System.out.println("Suma total es :"+result);

            }
        }





    }
}