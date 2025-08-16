package com.alura.literalura.EntradaDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AutorEntradaDTO(
        @JsonProperty("name") String nombre
) {
}
