package com.alura.literalura.service;

import com.alura.literalura.EntradaDTO.AutorEntradaDTO;
import com.alura.literalura.EntradaDTO.LibroEntradaDTO;
import com.alura.literalura.SalidaDTO.LibroSalidaDTO;
import com.alura.literalura.entity.Autor;
import com.alura.literalura.entity.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardarBusquedaService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Transactional
    public String guardarLibro(LibroSalidaDTO libro, AutorEntradaDTO autorDTO) {
        // Verificar si el autor ya existe
        Autor autor = autorRepository.findByNombre(autorDTO.nombre())
                .orElseGet(() -> {
                    Autor autorNuevo = new Autor();
                    autorNuevo.setNombre(autorDTO.nombre());
                    autorNuevo.setAnioNacimiento(autorDTO.anioNacimiento());
                    autorNuevo.setAnioFallecimiento(autorDTO.anioFallecimiento());
                    return autorRepository.save(autorNuevo);
                });

        // Verificar si el libro ya existe con ese autor
        return libroRepository.findByTituloAndAutor(libro.titulo(), autor)
                .map(l -> "El libro '" + libro.titulo() + "' ya está guardado en la BD ✅")
                .orElseGet(() -> {
                    Libro libroNuevo = new Libro();
                    libroNuevo.setTitulo(libro.titulo());
                    libroNuevo.setAutor(autor);
                    libroNuevo.setDescargas(libro.numeroDescargas());
                    libroNuevo.setIdioma(libro.idioma());
                    libroRepository.save(libroNuevo);
                    return "Se guardó el libro '" + libroNuevo.getTitulo() +
                            "' con su autor " + autor.getNombre() + " 📚";
                });
    }
}
