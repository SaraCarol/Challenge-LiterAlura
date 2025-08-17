package com.alura.literalura.repository;

import com.alura.literalura.entity.Autor;
import com.alura.literalura.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloAndAutor(String titulo, Autor autor);

    @Query("SELECT l.idioma, COUNT(l) FROM Libro l GROUP BY l.idioma")
    List<Object[]> contarLibrosPorIdioma();
}
