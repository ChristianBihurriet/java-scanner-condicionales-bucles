package ejemplos.parte2;

import java.util.Random;
import java.util.Scanner;

public class Ejemplo06PiedraPapelTijera {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Elige piedra, papel o tijera: ");
        String jugador = scanner.nextLine();
        int numeroMaquina = random.nextInt(3);
        String maquina;

        if (numeroMaquina == 0) {
            maquina = "piedra";
        } else if (numeroMaquina == 1) {
            maquina = "papel";
        } else {
            maquina = "tijera";
        }

        System.out.println("La máquina ha elegido: " + maquina);

        if (!jugador.equalsIgnoreCase("piedra")
                && !jugador.equalsIgnoreCase("papel")
                && !jugador.equalsIgnoreCase("tijera")) {
            System.out.println("Opción no válida.");
        } else if (jugador.equalsIgnoreCase(maquina)) {
            System.out.println("Empate.");
        } else if ((jugador.equalsIgnoreCase("piedra") && maquina.equals("tijera"))
                || (jugador.equalsIgnoreCase("papel") && maquina.equals("piedra"))
                || (jugador.equalsIgnoreCase("tijera") && maquina.equals("papel"))) {
            System.out.println("Ganas tú.");
        } else {
            System.out.println("Gana la máquina.");
        }

        scanner.close();
    }
}
