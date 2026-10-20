package ejemplos.parte2;

import java.util.Scanner;

public class Ejemplo01ValidacionEntrada {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int edad;

        do {
            System.out.print("Introduce una edad entre 0 y 120: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Debes escribir un número entero.");
                scanner.nextLine();
                System.out.print("Introduce una edad entre 0 y 120: ");
            }

            edad = scanner.nextInt();
            scanner.nextLine();

            if (edad < 0 || edad > 120) {
                System.out.println("La edad está fuera del rango permitido.");
            }
        } while (edad < 0 || edad > 120);

        System.out.println("Edad válida: " + edad);
        scanner.close();
    }
}
