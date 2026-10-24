package com.example.biblioteca.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.biblioteca.service.LibroService;
import com.example.biblioteca.model.Libro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listarLibros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerLibroPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Libro> guardarLibro(@RequestBody Libro libro) {
        Libro libroGuardado = libroService.guardarLibro(libro);
        return ResponseEntity.ok(libroGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(
            @PathVariable Long id,
            @RequestBody Libro libro) {

        Libro libroActualizado = libroService.actualizarLibro(id, libro);

        if (libroActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(libroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }

}