package ejemplos.parte2;

import java.util.Scanner;

public class Ejemplo07BreakContinue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int suma = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.print("Introduce un número (0 termina): ");
            int numero = Integer.parseInt(scanner.nextLine());

            if (numero == 0) {
                break;
            }
            if (numero < 0) {
                System.out.println("Los negativos se ignoran.");
                continue;
            }

            suma += numero;
        }

        System.out.println("Suma de positivos: " + suma);
        scanner.close();
    }
}
