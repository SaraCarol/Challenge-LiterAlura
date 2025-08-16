package com.alura.literalura.client;

import com.alura.literalura.EntradaDTO.LibroEntradaDTO;
import com.alura.literalura.SalidaDTO.LibroSalidaDTO;
import com.alura.literalura.service.LlamadoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class MenuConsola implements CommandLineRunner {
    @Autowired
    private LlamadoAPI llamadoAPI;
    List<LibroSalidaDTO> historial = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {

        try (Scanner sc = new Scanner(System.in)) {
            int opcion = -1;
            while (opcion != 0) {
                System.out.println("1. Buscar libro ");
                System.out.println("2. Historial de búsqueda");
                System.out.println("0. Salir");
                System.out.print("Selecciona una opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion){
                    case 1:
                        System.out.print("Escrbe el título del libro que quieres buscar: ");
                        String busqueda = sc.nextLine();
                        var resultado = llamadoAPI.obtenerDatos(busqueda);

                        if (resultado != null && resultado.resultadoLibros() != null &&
                                !resultado.resultadoLibros().isEmpty()) {
                            var primerLibro = resultado.resultadoLibros().get(0);
                            LibroSalidaDTO libroSalida = LibroEntradaDTO.convertirDatosSalida(primerLibro);
                            historial.add(libroSalida);

                            System.out.println("\nResultado encontrado:");
                            System.out.println("Título: " + libroSalida.titulo());
                            System.out.println("Autor: " + libroSalida.autor().nombre());
                            System.out.println("Idioma: " + libroSalida.idioma());
                            System.out.println("Número de descargas: " + libroSalida.numeroDescargas());
                        }else {
                            System.out.println("No se encontraron resultados para ese título.");
                        }
                        break;

                    case 2:
                        System.out.println("\nHistorial de búsqueda:");
                        if (historial.isEmpty()) {
                            System.out.println("Todavía no has buscado libros.");
                        } else {
                            historial.forEach(l -> System.out.println(l.titulo()));
                        }
                        break;

                    case 0:
                        System.out.println("Saliendo del sistema...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción inválida 😅");
                };
            }
        }


    }
}
