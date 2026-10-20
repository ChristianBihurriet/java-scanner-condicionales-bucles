package ejemplos.parte1;

import java.util.Scanner;

public class Ejemplo04CompararStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escribe el nombre de un lenguaje de programación: ");
        String lenguaje = scanner.nextLine();

        if (lenguaje.equals("Java")) {
            System.out.println("Has escrito exactamente Java.");
        } else if (lenguaje.equalsIgnoreCase("Java")) {
            System.out.println("Has escrito Java usando otras mayúsculas o minúsculas.");
        } else {
            System.out.println("Has escrito otro lenguaje.");
        }

        System.out.println("Comparación exacta con equals: " + lenguaje.equals("Java"));
        System.out.println("Comparación sin distinguir mayúsculas: "
                + lenguaje.equalsIgnoreCase("Java"));

        scanner.close();
    }
}
