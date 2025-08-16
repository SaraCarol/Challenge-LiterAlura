package com.alura.literalura.client;

import com.alura.literalura.service.LlamadoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuConsola implements CommandLineRunner {
    @Autowired
    private LlamadoAPI llamadoAPI;

    @Override
    public void run(String... args) throws Exception {

        try (Scanner sc = new Scanner(System.in)) {
            int opcion = -1;
            while (opcion != 0) {
                System.out.println("1) Buscar libro ");
                System.out.println("0) Salir");
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion){
                    case 1:
                        System.out.print("Título: ");
                        String busqueda = sc.nextLine();
                        llamadoAPI.obtenerDatos(busqueda);
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
