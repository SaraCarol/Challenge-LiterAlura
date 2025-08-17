package com.alura.literalura.repository;

import com.alura.literalura.entity.Autor;
import com.alura.literalura.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloAndAutor(String titulo, Autor autor);
}
