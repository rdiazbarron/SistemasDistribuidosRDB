package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Biblioteca {
    private Map<String, Libro> libros;
    private Map<String, Usuario> usuarios;

    public Biblioteca() {
        this.libros = new HashMap<>();
        this.usuarios = new HashMap<>();
    }

    public void agregarLibro(Libro libro) {
        libros.put(libro.getTitulo(), libro);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getNombre(), usuario);
    }

    public List<Libro> buscarLibrosPorAutor(String nombreAutor) {
        return libros.values().stream()
                .filter(libro -> libro.getAutor().getNombre().equals(nombreAutor))
                .collect(Collectors.toList());
    }

    public boolean prestarLibro(String titulo, String nombreUsuario) {
        Libro libro = libros.get(titulo);
        Usuario usuario = usuarios.get(nombreUsuario);

        if (libro != null && usuario != null && !libro.isPrestado()) {
            libro.prestar();
            return true;
        }
        return false;
    }

    public boolean devolverLibro(String titulo) {
        Libro libro = libros.get(titulo);
        if (libro != null && libro.isPrestado()) {
            libro.devolver();
            return true;
        }
        return false;
    }
}