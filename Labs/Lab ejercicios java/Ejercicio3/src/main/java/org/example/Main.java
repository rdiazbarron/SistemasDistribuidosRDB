package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Biblioteca b = new Biblioteca();
        Autor tolkien = new Autor("Tolkien");
        Autor rowling = new Autor("Rowling");

        // Libros
        Libro lotr = new Libro("Lord of the Rings", tolkien);
        Libro hp = new Libro("Harry Potter", rowling);

        // Usuarios
        Usuario juan = new Usuario("Juan");
        Usuario ana = new Usuario("Ana");

        b.agregarLibro(lotr);
        b.agregarLibro(hp);
        b.agregarUsuario(juan);
        b.agregarUsuario(ana);

        List<Libro> librosDeTolkien = b.buscarLibrosPorAutor("Tolkien");
        System.out.println("Libros Tolkien:");
        for (Libro libro : librosDeTolkien) {
            System.out.println(libro.getTitulo());
        }

        boolean prestamoExitoso = b.prestarLibro("Lord of the Rings", "Juan");
        System.out.println("Préstamo de libro a Juan: " + (prestamoExitoso ? "Exitoso" : "Fallido"));

        // Intentar prestar el mismo libro de nuevo
        prestamoExitoso = b.prestarLibro("Lord of the Rings", "Ana");
        System.out.println("Préstamo de libro a Ana: " + (prestamoExitoso ? "Exitoso" : "Fallido"));

    }
}