package ejemplos.parte1;

import java.util.Scanner;

public class Ejemplo05Cadenas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escribe una frase: ");
        String frase = scanner.nextLine().trim();

        if (frase.isEmpty()) {
            System.out.println("No has escrito ningún texto.");
        } else {
            System.out.println("Texto limpio: " + frase);
            System.out.println("Número de caracteres: " + frase.length());
            System.out.println("Primera letra: " + frase.charAt(0));
            System.out.println("Mayúsculas: " + frase.toUpperCase());
            System.out.println("Minúsculas: " + frase.toLowerCase());
        }

        scanner.close();
    }
}
