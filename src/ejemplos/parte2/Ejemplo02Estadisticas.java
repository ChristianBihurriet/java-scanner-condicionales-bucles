package ejemplos.parte2;

import java.util.Scanner;

public class Ejemplo02Estadisticas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double suma = 0;
        double maximo = 0;
        double minimo = 0;
        int cantidad = 0;
        boolean hayDatos = false;
        double temperatura;

        System.out.println("Introduce temperaturas. Escribe -99 para terminar.");
        while (true) {
            System.out.print("Temperatura: ");

            while (!scanner.hasNextDouble()) {
                System.out.println("Escribe un número.");
                scanner.nextLine();
                System.out.print("Temperatura: ");
            }

            temperatura = scanner.nextDouble();
            scanner.nextLine();

            if (temperatura == -99) {
                break;
            }

            suma += temperatura;
            cantidad++;

            if (!hayDatos) {
                maximo = temperatura;
                minimo = temperatura;
                hayDatos = true;
            } else {
                if (temperatura > maximo) {
                    maximo = temperatura;
                }
                if (temperatura < minimo) {
                    minimo = temperatura;
                }
            }
        }

        if (cantidad == 0) {
            System.out.println("No se introdujeron temperaturas.");
        } else {
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Media: " + (suma / cantidad));
            System.out.println("Máxima: " + maximo);
            System.out.println("Mínima: " + minimo);
        }

        scanner.close();
    }
}
