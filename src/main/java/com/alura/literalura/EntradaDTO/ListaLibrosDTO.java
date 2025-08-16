package com.alura.literalura.EntradaDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.util.List;


public record ListaLibrosDTO(
        @JsonProperty("results") List<LibroEntradaDTO> resultadoLibros
) {

}
