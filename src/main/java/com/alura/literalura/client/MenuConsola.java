package com.alura.literalura.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuConsola implements CommandLineRunner {

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
                        //gutendexClient.searchBooks(q);
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida 😅");
                };
            }
        }


    }
}
