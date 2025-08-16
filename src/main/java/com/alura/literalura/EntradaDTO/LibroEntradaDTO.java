package com.alura.literalura.EntradaDTO;

import com.alura.literalura.SalidaDTO.LibroSalidaDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record LibroEntradaDTO(
        @JsonProperty("title") String titulo,
        @JsonProperty("authors") List<AutorEntradaDTO> autores,
        @JsonProperty("languages") List<String> idiomas,
        @JsonProperty("download_count") Integer numeroDescargas
) {
    public static LibroSalidaDTO convertirDatosSalida(LibroEntradaDTO datos) {
        return new LibroSalidaDTO(
                datos.titulo(),
                datos.autores() != null && !datos.autores().isEmpty() ? datos.autores().get(0) : null,
                datos.idiomas() != null && !datos.idiomas().isEmpty() ? datos.idiomas().get(0) : null,
                datos.numeroDescargas()
        );
    }

}
