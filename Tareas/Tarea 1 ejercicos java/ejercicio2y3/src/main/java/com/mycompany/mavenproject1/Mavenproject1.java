/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author diazb
 */
public class Mavenproject1 {
    

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(1,"Biblioteca publica", 200);
        Scanner sc = new Scanner(System.in);
        String autor,librito;
        int anio;
        String tipo;
        Librero librero1 = null;
        Libro libro1 = null;
        LibreroDAO libreroDAO =null;
        LibroDAO libroDAO = null;
        int opcion = 0;
        while(opcion !=6){
            System.out.println("--------------------------");
               System.out.println("Bienvenido elija la opcion que desea: ");
               System.out.println("Opcion 1: Crear un librero");
               System.out.println("Opcion 2: Anadir librero a biblioteca");
               System.out.println("Opcion 3: crear libro");
               System.out.println("Opcion 4: anadir libro a librero");
               System.out.println("Opcion 5: mostrar libros");
               System.out.println("Opcion 6: Salir");
               System.out.println("Introduzca opcion :");
             
               opcion = sc.nextInt();
               switch(opcion){
               case 1: 
                   System.out.println("Introduzca el nuevo codigo de su librero:");
                   String codigo = sc.next();
                   System.out.println("Introduzca el tipo de librero: 1 Madera 2 Metalico");
                   sc.nextLine();
                   int tipon = sc.nextInt();
                   if(tipon == 1){
                      tipo = "MADERA";
                       
                   }
                   else{
                      tipo = "METALICO";
                   }
                   TipoLibrero tipolibrero = TipoLibrero.valueOf(tipo);
                   librero1 = new Librero(codigo,tipolibrero);
                   libreroDAO = new LibreroDAO();
                   libreroDAO.añadirLibrero(librero1);
                   System.out.println("LIBRERO DE CODIGO "+codigo+" CREADO!");
                  
                   break;
               case 2:
                   

                   if (librero1 == null){
                       System.out.println("No hay libreros creados");
                   }
                   else
                   {
                        biblioteca.añadirLibrero(librero1);
                       System.out.println("LIBRERO "+librero1.getCodigo()+ "ANADIDO A LA BIBLIOTECA");
                   }
                   break;

               case 3:
                   
                    System.out.println("Introduzca nombre del libro");
                    sc.nextLine(); 
                    librito = sc.nextLine();
                    System.out.println("Introduzca autor del libro");
                    autor = sc.nextLine();
                    System.out.println("Introduzca anio del libro");
                    anio = sc.nextInt();
                    sc.nextLine(); 
                    libro1 = new Libro(autor, librito, anio);
                    
                    System.out.println("LIBRO " + libro1.getTitulo() + " CREADO CON  EXITO");
                    break;
               case 4:
                   System.out.println("LIBREROS DISPONIBLES: ");
                   biblioteca.mostrarLibreros();
                   System.out.println("Introduzca el codigo de un librero para agregar el libro:");
                   String codi = sc.next();
                   biblioteca.cargarLibroEnLibrero(libro1, codi);
                   libroDAO = new LibroDAO();
                   libroDAO.añadirLibrero(libro1, codi);
               case 5:
                   biblioteca.mostrarLibros();
                   break;  
               case 6:
                   break;
               default:
                   System.out.println("Opcion incorrecta");
                   break;
               }
               
        }
        
        
        
     
    }
    
}
