package ejemplos.parte1;

import java.util.Scanner;

public class Ejemplo02Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n=== MENÚ DE EJEMPLO ===");
            System.out.println("1. Saludar");
            System.out.println("2. Mostrar los números del 1 al 5");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Introduce un número válido.");
                scanner.nextLine(); // Descarta la entrada no numérica.
                continue;
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consume el salto de línea pendiente.

            switch (opcion) {
                case 1:
                    System.out.println("¡Hola!");
                    break;
                case 2:
                    for (int i = 1; i <= 5; i++) {
                        System.out.println(i);
                    }
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción desconocida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
