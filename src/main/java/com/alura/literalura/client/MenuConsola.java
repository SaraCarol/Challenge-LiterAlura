package com.alura.literalura.client;

import com.alura.literalura.EntradaDTO.AutorEntradaDTO;
import com.alura.literalura.EntradaDTO.LibroEntradaDTO;
import com.alura.literalura.SalidaDTO.LibroSalidaDTO;
import com.alura.literalura.entity.Autor;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.GuardarBusquedaService;
import com.alura.literalura.service.LlamadoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class MenuConsola implements CommandLineRunner {
    @Autowired
    private LlamadoAPI llamadoAPI;

    @Autowired
    private GuardarBusquedaService guardarBusquedaService;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    List<LibroSalidaDTO> historial = new ArrayList<>();

    @Override
    public void run(String... args) throws Exception {

        try (Scanner sc = new Scanner(System.in)) {
            int opcion = -1;
            while (opcion != 0) {
                System.out.println("1. Buscar y guardar libro ");
                System.out.println("2. Historial de búsqueda de libros");
                System.out.println("3. Historial de autores de los libros buscados");
                System.out.println("4. Cantidad de libros por idioma");
                System.out.println("5. Autores vivos en un año");
                System.out.println("0. Salir");
                System.out.print("Selecciona una opción: ");
                String entrada = sc.nextLine();
                try {
                    opcion = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Opción inválida 😅 (debe ser un número)");
                    continue;
                }

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

                            var guardarBD = guardarBusquedaService.guardarLibro(libroSalida, libroSalida.autor());

                            System.out.println("\n----Resultado encontrado-----");
                            System.out.println("Título: " + libroSalida.titulo());
                            System.out.println("Autor: " + libroSalida.autor().nombre());
                            System.out.println("Idioma: " + libroSalida.idioma());
                            System.out.println("Número de descargas: " + libroSalida.numeroDescargas());
                            System.out.println("******************************");
                            System.out.println(guardarBD);
                            System.out.println("******************************");

                        }else {
                            System.out.println("No se encontraron resultados para ese título.");
                        }
                        break;

                    case 2:
                        System.out.println("\n-----Historial de búsqueda-------");
                        if (historial.isEmpty()) {
                            System.out.println("Todavía no has buscado libros.");
                        } else {
                            historial.forEach(l -> System.out.println("Libro: " + l.titulo()));
                            System.out.println("******************************");
                        }
                        break;

                    case 3:
                        if(historial.isEmpty()){
                            System.out.println("Todavía no has buscado libros.");
                        } else {
                            historial.forEach(l->
                                    System.out.println("Autor: " + l.autor().nombre()));
                            System.out.println("******************************");
                        }
                        break;

                    case 4:
                        List<Object[]> resultados = libroRepository.contarLibrosPorIdioma();
                        for (Object[] r : resultados) {
                            System.out.println("Idioma: " + r[0] + " -> " + r[1] + " libro(s)");
                        }
                        break;

                    case 5:
                        System.out.print("Escrbe el año por el que quieres buscar: ");
                        try {
                            int anio = sc.nextInt();
                            sc.nextLine();
                            List<Autor> autores = autorRepository.findAutoresVivosEnAnio(anio);
                            if (autores.isEmpty()) {
                                System.out.println("No hay autores vivos en el año " + anio);
                            } else {
                                autores.forEach(a -> System.out.println(a.getNombre()));
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("⚠️ Error: Debe ingresar un número válido.");
                            sc.nextLine();
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
