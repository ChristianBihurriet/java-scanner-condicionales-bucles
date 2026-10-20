package ejemplos.parte1;

import java.util.Scanner;

public class Ejemplo01Scanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("¿Cómo te llamas? ");
        String nombre = scanner.nextLine();

        System.out.print("¿Cuántos años tienes? ");
        int edad = Integer.parseInt(scanner.nextLine());

        System.out.println("Hola, " + nombre + ". Tienes " + edad + " años.");
        if (edad >= 18) {
            System.out.println("Eres mayor de edad.");
        } else {
            System.out.println("Eres menor de edad.");
        }

        scanner.close();
    }
}
