/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.ArrayList;

/**
 *
 * @author diazb
 */
class Biblioteca {
    private int id; 
    private String nombre;
    private int tamaño;
    private final ArrayList<Librero> libreros;

    public Biblioteca(int id,String nombre, int tamaño) {
        this.id = id;
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.libreros = new ArrayList<>();
    }

    public void añadirLibrero(Librero librero) {
        libreros.add(librero);
    }

    public void cargarLibroEnLibrero(Libro libro, String codigoLibrero) {
        for(Librero librero : libreros){
            if (librero.getCodigo().equals(codigoLibrero))
            {
                librero.añadirLibro(libro);
                System.out.println("Libro "+libro.getTitulo()+"anadido al libero de codigo"+codigoLibrero);
                break;
            }
            
        }
        System.out.println("No se encontró el librero con código " + codigoLibrero + ".");
    }
    
    public void mostrarLibros() {
        int count =0;
        for(Librero librero : libreros){
            count +=1;
            System.out.println("Librero de cod: "+librero.getCodigo());
            librero.mostrar();
            
           
        }
        
    }
    public void mostrarLibreros() {
        
        for(Librero librero : libreros){
    
            System.out.println("Librero de codigo:"+librero.getCodigo()+" tipo: "+librero.getTipolibrero());
            
        }
        
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
}