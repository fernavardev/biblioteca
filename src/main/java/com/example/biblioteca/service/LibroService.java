package com.example.biblioteca.service;

import com.example.biblioteca.model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<Libro> listarLibros();

    Optional<Libro> obtenerLibroPorId(Long id);

    Libro guardarLibro(Libro libro);

    Libro actualizarLibro(Long id, Libro libro);

    void eliminarLibro(Long id);

}