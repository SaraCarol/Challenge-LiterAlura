package com.alura.literalura.EntradaDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AutorEntradaDTO(
        @JsonProperty("name") String nombre,
        @JsonProperty("birth_year") Integer anioNacimiento,
        @JsonProperty("death_year") Integer anioFallecimiento

) {
}
