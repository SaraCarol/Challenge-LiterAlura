package com.alura.literalura.SalidaDTO;

import com.alura.literalura.EntradaDTO.AutorEntradaDTO;
import com.alura.literalura.EntradaDTO.LibroEntradaDTO;

public record LibroSalidaDTO(
        String titulo,
        AutorEntradaDTO autor,
        String idioma,
        Integer numeroDescargas
) {

}
