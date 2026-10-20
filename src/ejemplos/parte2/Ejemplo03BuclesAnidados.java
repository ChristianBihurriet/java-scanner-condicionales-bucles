package ejemplos.parte2;

public class Ejemplo03BuclesAnidados {
    public static void main(String[] args) {
        for (int tabla = 1; tabla <= 3; tabla++) {
            System.out.println("Tabla del " + tabla + ":");

            for (int multiplicador = 1; multiplicador <= 10; multiplicador++) {
                System.out.println(tabla + " x " + multiplicador + " = "
                        + (tabla * multiplicador));
            }

            System.out.println();
        }
    }
}
