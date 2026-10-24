package com.example.biblioteca.service;

import com.example.biblioteca.model.Libro;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.biblioteca.repository.LibroRepository;
import com.example.biblioteca.model.Libro;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizarLibro(Long id, Libro libro) {
        return libroRepository.findById(id)
                .map(libroExistente -> {
                    libroExistente.setTitulo(libro.getTitulo());
                    libroExistente.setAutor(libro.getAutor());
                    libroExistente.setAnioPublicacion(libro.getAnioPublicacion());
                    libroExistente.setGenero(libro.getGenero());

                    return libroRepository.save(libroExistente);
                })
                .orElse(null);
    }

    @Override
    public void eliminarLibro(Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
        }
    }
}