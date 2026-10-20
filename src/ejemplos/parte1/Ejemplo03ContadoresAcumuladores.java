package ejemplos.parte1;

import java.util.Scanner;

public class Ejemplo03ContadoresAcumuladores {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalMinutos = 0;
        int cantidadDias = 0;

        System.out.println("=== TIEMPO DE ESTUDIO ===");

        for (int dia = 1; dia <= 3; dia++) {
            System.out.print("Minutos estudiados el día " + dia + ": ");
            int minutos = Integer.parseInt(scanner.nextLine());

            totalMinutos += minutos; // Acumulador: suma los minutos.
            cantidadDias++;          // Contador: cuenta los días.
        }

        double media = (double) totalMinutos / cantidadDias;

        System.out.println("\nMinutos totales: " + totalMinutos);
        System.out.println("Días registrados: " + cantidadDias);
        System.out.println("Media diaria: " + media + " minutos");

        scanner.close();
    }
}
