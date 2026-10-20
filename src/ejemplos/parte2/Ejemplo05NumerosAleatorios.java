package ejemplos.parte2;

import java.util.Random;

public class Ejemplo05NumerosAleatorios {
    public static void main(String[] args) {
        Random random = new Random();
        int suma = 0;

        System.out.println("Cinco lanzamientos de un dado:");
        for (int lanzamiento = 1; lanzamiento <= 5; lanzamiento++) {
            int resultado = random.nextInt(6) + 1;
            suma += resultado;
            System.out.println("Lanzamiento " + lanzamiento + ": " + resultado);
        }

        System.out.println("Suma de los lanzamientos: " + suma);
    }
}
