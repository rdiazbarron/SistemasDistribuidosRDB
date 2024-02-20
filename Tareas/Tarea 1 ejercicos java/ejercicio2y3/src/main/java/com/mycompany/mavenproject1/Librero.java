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
public class Librero {
    private String codigo;
    private TipoLibrero tipolibrero;
    private final ArrayList<Libro> libros;
    
    public Librero(String codigo,TipoLibrero tipolibrero){
        this.codigo = codigo;
        this.libros = new ArrayList<>();
        this.tipolibrero = tipolibrero;
    }

    public TipoLibrero getTipolibrero() {
        return tipolibrero;
    }

    public void setTipolibrero(TipoLibrero tipolibrero) {
        this.tipolibrero = tipolibrero;
    }
    
    public void añadirLibro(Libro libro) {
        libros.add(libro);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void mostrar(){
        
        for (Libro libro : libros){
            System.out.println(libro.getTitulo());
        }
    }
    
    
}
