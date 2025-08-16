package com.alura.literalura.service;

import com.alura.literalura.EntradaDTO.ListaLibrosDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class LlamadoAPI {
    RestTemplate restTemplate = new RestTemplate();
    private final String URL_BASE = "https://gutendex.com/books";

    public ListaLibrosDTO obtenerDatos(String busqueda) {
        URI uri = UriComponentsBuilder
                .fromHttpUrl(URL_BASE)
                .queryParam("search", busqueda)
                .encode()
                .build()
                .toUri();

        var respuesta = restTemplate.getForObject(uri, ListaLibrosDTO.class);
        System.out.println("DEBUG JSON: " + respuesta);
        return respuesta;
    };
};
