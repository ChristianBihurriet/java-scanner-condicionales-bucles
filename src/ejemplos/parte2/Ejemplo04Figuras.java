package ejemplos.parte2;

public class Ejemplo04Figuras {
    public static void main(String[] args) {
        int altura = 5;

        for (int fila = 1; fila <= altura; fila++) {
            for (int columna = 1; columna <= fila; columna++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
