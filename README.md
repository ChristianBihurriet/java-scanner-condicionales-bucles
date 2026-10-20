# Prácticas de Java: Scanner, condicionales y bucles

**1.º DAM · Java 21 · Sin Maven ni dependencias externas**

## Parte 1 — Fundamentos e integración inicial

## Objetivo

Practicar `if`, `else`, `switch`, `for`, `while` y `do-while` con programas que reciben datos por teclado. No hace falta saber POO: cada archivo contiene un `main` independiente.

## Cómo empezar

1. Abre esta carpeta en VS Code con el soporte de Java instalado.
2. Empieza por `src/ejemplos/parte1` y modifica los datos para observar qué cambia.
3. Resuelve los ejercicios de `src/ejercicios/parte1` en orden. **Los ejercicios
   no contienen las soluciones**.
4. Cuando termines la Parte 1, continúa con la Parte 2 que aparece más abajo.
5. Antes de pedir ayuda, imprime los valores de tus variables y lee el mensaje de error.

### Desde la terminal (en la raíz del repositorio)

```bash
javac -d out $(find src -name '*.java')
java -cp out ejemplos.parte1.Ejemplo01Scanner
java -cp out ejemplos.parte1.Ejemplo02Menu
java -cp out ejercicios.parte1.Ejercicio01TablaMultiplicar
```

**Nota:** los ejercicios compilan pero no hacen nada: debes implementar el `main`.

## Leer datos con `Scanner`

### ¿Qué es?

`Scanner` es una clase de Java que permite leer lo que una persona escribe por
teclado. Para utilizarla hay que importarla y crear un objeto asociado a
`System.in`, que representa la entrada estándar del programa.

### ¿Para qué sirve?

Gracias a `Scanner`, el programa puede pedir un nombre, una edad, una opción de
un menú o cualquier otro dato en vez de trabajar siempre con valores escritos
directamente en el código.

### Sintaxis

```java
import java.util.Scanner;

public class Ejemplo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Escribe tu nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Escribe tu edad: ");
        int edad = Integer.parseInt(scanner.nextLine());

        System.out.println("Hola, " + nombre + ". Tienes " + edad + " años.");

        scanner.close();
    }
}
```

`nextLine()` lee una línea completa y la devuelve como `String`. Si esperamos
un entero, podemos convertir ese texto con `Integer.parseInt(...)`. Para un
decimal se puede utilizar `Double.parseDouble(...)`.

Otra posibilidad es leer números directamente:

```java
int edad = scanner.nextInt();
double precio = scanner.nextDouble();
```

Sin embargo, al mezclar `nextInt()` o `nextDouble()` con `nextLine()` queda
pendiente el salto de línea que se produce al pulsar Intro. Hay que consumirlo
antes de leer el siguiente texto:

```java
int edad = scanner.nextInt();
scanner.nextLine(); // Consume el salto de línea pendiente.
String nombre = scanner.nextLine();
```

Puedes ver un programa completo en
[`Ejemplo01Scanner.java`](src/ejemplos/parte1/Ejemplo01Scanner.java).

### Errores frecuentes

- Olvidar `import java.util.Scanner;`.
- Intentar guardar directamente el resultado de `nextLine()` en un `int`.
- Mezclar `nextInt()` y `nextLine()` sin consumir el salto de línea pendiente.
- Crear un `Scanner` nuevo para cada dato. Basta con crear uno y reutilizarlo.
- Cerrar el `Scanner` antes de terminar todas las lecturas.

### Referencia ampliada

#### Crear un `Scanner`

Primero importamos la clase y después creamos un objeto asociado a `System.in`:

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
```

`Scanner` es la clase, `scanner` es el nombre de nuestra variable y
`new Scanner(System.in)` crea el lector asociado al teclado. Normalmente
creamos un único objeto y lo reutilizamos durante todo el programa.

#### Métodos principales

| Método | Qué lee o comprueba | Ejemplo de resultado |
| --- | --- | --- |
| `nextLine()` | Una línea completa de texto | `"Hola mundo"` |
| `next()` | Una palabra hasta el siguiente separador | `"Hola"` |
| `nextInt()` | Un número entero | `25` |
| `nextDouble()` | Un número decimal | `3.14` |
| `hasNextInt()` | Si el siguiente dato puede ser un entero | `true` o `false` |
| `hasNextDouble()` | Si el siguiente dato puede ser decimal | `true` o `false` |

Los métodos `next...()` leen y consumen el dato. Los métodos `hasNext...()`
solo comprueban si tiene el formato esperado; no lo consumen.

#### Leer directamente un número

Cuando confiamos en que la entrada será correcta podemos utilizar `nextInt()`:

```java
System.out.print("Introduce tu edad: ");
int edad = scanner.nextInt();
System.out.println("Tienes " + edad + " años.");
```

Es sencillo, pero si el usuario escribe `hola` se produce un error porque Java
esperaba un entero.

#### Leer una línea y convertirla

Otra estrategia consiste en leer siempre una línea y convertirla después:

```java
String entrada = scanner.nextLine();
int edad = Integer.parseInt(entrada);
double precio = Double.parseDouble(scanner.nextLine());
```

También podemos escribir `int edad = Integer.parseInt(scanner.nextLine());`.
Esta forma evita el problema de mezclar `nextInt()` y `nextLine()`, pero no
valida automáticamente el texto. Si se introduce `hola`,
`Integer.parseInt()` produce una `NumberFormatException`. En esta unidad todavía
no necesitamos estudiar `try-catch`.

#### El salto de línea pendiente

`nextInt()` lee el número, pero deja pendiente el salto de línea escrito al
pulsar Intro. Por eso este código no lee el nombre como esperamos:

```java
int edad = scanner.nextInt();
String nombre = scanner.nextLine(); // Lee el salto pendiente: queda vacío.
```

Hay que consumirlo de forma explícita:

```java
int edad = scanner.nextInt();
scanner.nextLine(); // Consume el salto de línea pendiente.
String nombre = scanner.nextLine();
```

#### Comprobar antes de leer

`hasNextInt()` permite comprobar el formato antes de llamar a `nextInt()`:

```java
if (scanner.hasNextInt()) {
    int edad = scanner.nextInt();
    System.out.println("Tu edad es: " + edad);
} else {
    System.out.println("Debes introducir un número entero.");
    scanner.nextLine(); // Descarta la entrada incorrecta.
}
```

El método `hasNextInt()` no consume el dato. Si devuelve `false`, debemos
descartarlo con `nextLine()` antes de volver a preguntar.

#### Validar utilizando un bucle

Podemos seguir preguntando hasta recibir un entero válido:

```java
System.out.print("Introduce tu edad: ");

while (!scanner.hasNextInt()) {
    System.out.println("Error: introduce un número entero.");
    scanner.nextLine(); // Descarta la entrada incorrecta.
    System.out.print("Introduce tu edad: ");
}

int edad = scanner.nextInt();
```

Si no descartamos la entrada incorrecta, se comprobaría repetidamente el mismo
texto y el programa podría quedarse atrapado en un bucle infinito.

#### Validar también el rango

El tipo correcto no garantiza que el valor sea válido. Una edad `-50` es un
entero, pero no está dentro del rango permitido:

```java
int edad;

do {
    System.out.print("Introduce una edad entre 0 y 120: ");

    while (!scanner.hasNextInt()) {
        System.out.println("Debes introducir un número entero.");
        scanner.nextLine();
        System.out.print("Introduce una edad entre 0 y 120: ");
    }

    edad = scanner.nextInt();
    scanner.nextLine();

    if (edad < 0 || edad > 120) {
        System.out.println("La edad debe estar entre 0 y 120.");
    }
} while (edad < 0 || edad > 120);

System.out.println("Edad válida: " + edad);
```

Aquí se comprueban dos cosas diferentes:

- **Tipo:** ¿se ha introducido un entero?
- **Rango:** ¿ese entero está entre 0 y 120?

#### Qué estrategia utilizar en cada parte

En los primeros ejercicios conviene leer líneas y convertirlas:

```java
int numero = Integer.parseInt(scanner.nextLine());
```

Es una estrategia sencilla y consistente. En la Parte 2 utilizaremos
`hasNextInt()` o `hasNextDouble()` junto con bucles para controlar entradas
incorrectas y rangos no permitidos. El ejemplo completo de validación se
encuentra en [`Ejemplo01ValidacionEntrada.java`](src/ejemplos/parte2/Ejemplo01ValidacionEntrada.java).

## Bucle `do-while`

### ¿Qué es?

`do-while` repite un bloque mientras una condición sea verdadera. A diferencia
de `while`, comprueba la condición **después** de ejecutar el bloque. Por eso el
código se ejecuta al menos una vez.

### ¿Para qué sirve?

Es especialmente útil para menús y para preguntas que deben realizarse antes
de saber si hay que repetir. Por ejemplo, primero mostramos un menú y después
comprobamos si la opción elegida era salir.

### Sintaxis

```java
int opcion;

do {
    System.out.println("1. Saludar");
    System.out.println("0. Salir");
    System.out.print("Elige una opción: ");
    opcion = Integer.parseInt(scanner.nextLine());

    if (opcion == 1) {
        System.out.println("¡Hola!");
    }
} while (opcion != 0);
```

El orden es el siguiente:

1. Se ejecuta el bloque situado entre las llaves.
2. Se comprueba `opcion != 0`.
3. Si la condición es `true`, comienza otra iteración; si es `false`, termina.

| Estructura | Momento de comprobar la condición | Mínimo de iteraciones |
| --- | --- | ---: |
| `while` | Antes del bloque | 0 |
| `do-while` | Después del bloque | 1 |

En [`Ejemplo02Menu.java`](src/ejemplos/parte1/Ejemplo02Menu.java) hay un menú completo
que combina `do-while`, `Scanner` y `switch`.

### Errores frecuentes

- Olvidar el punto y coma final de `while (condicion);`.
- No cambiar dentro del bucle la variable utilizada en la condición y crear un
  bucle infinito.
- Escribir una condición de salida al revés. Si `0` significa salir, el menú se
  repite **mientras la opción sea distinta de 0**.
- Utilizar una variable antes de darle un valor. En un `do-while` normalmente se
  lee o actualiza dentro del bloque antes de comprobarla.

## Contadores y acumuladores

### ¿Qué son?

Un **contador** registra cuántas veces ocurre algo. Normalmente aumenta de uno
en uno. Un **acumulador** guarda un resultado que va creciendo con distintos
valores, por ejemplo la suma de varios números o el precio total de una compra.

| Variable | Pregunta que responde | Actualización habitual |
| --- | --- | --- |
| Contador | ¿Cuántos valores se han leído? | `cantidad++` |
| Acumulador | ¿Cuánto suman esos valores? | `suma += numero` |

`suma += numero` es una forma abreviada de escribir
`suma = suma + numero`.

### ¿Para qué sirven?

Se utilizan dentro de los bucles para conservar información entre una
iteración y la siguiente. El contador permite calcular cantidades; el
acumulador permite obtener sumas, totales y, junto con un contador, medias.

### Sintaxis y ejemplo

```java
int suma = 0;
int cantidad = 0;

for (int i = 1; i <= 3; i++) {
    System.out.print("Introduce un número: ");
    int numero = Integer.parseInt(scanner.nextLine());

    suma += numero;
    cantidad++;
}

System.out.println("Suma: " + suma);
System.out.println("Cantidad: " + cantidad);

if (cantidad > 0) {
    double media = (double) suma / cantidad;
    System.out.println("Media: " + media);
}
```

Antes del bucle se inicializan las variables. Dentro del bucle, cada número se
añade a `suma` y se incrementa `cantidad`. La conversión `(double)` evita que
Java realice una división entera y pierda la parte decimal. Además, se comprueba
que `cantidad` sea mayor que cero para no dividir entre cero.

### Seguimiento paso a paso

Si los tres números son `3`, `5` y `-2`, ocurre lo siguiente:

| Número leído | `suma` después de acumular | `cantidad` |
| ---: | ---: | ---: |
| 3 | 3 | 1 |
| 5 | 8 | 2 |
| -2 | 6 | 3 |

El resultado final es una suma de 6 y una cantidad de 3. Esta idea aparece en
los ejercicios 06, 07, 11 y 12.

Puedes ver estos dos tipos de variables funcionando juntos en
[`Ejemplo03ContadoresAcumuladores.java`](src/ejemplos/parte1/Ejemplo03ContadoresAcumuladores.java).

### Errores frecuentes

- Declarar el contador o el acumulador dentro del bucle: volvería a empezar en
  cada iteración.
- Sustituir el valor en vez de acumularlo: `suma = numero` no conserva la suma
  anterior.
- Contar también un valor que solo sirve para terminar un bucle, como puede ser
  un `0`.
- Confundir el contador con el acumulador: uno cuenta datos y el otro suma sus
  valores.
- Calcular una media sin comprobar antes que la cantidad sea mayor que cero.

## Comparar cadenas con `equals()`

### ¿Qué es?

Una variable `String` contiene texto. Para comprobar si dos cadenas tienen el
mismo contenido se utiliza el método `equals()`.

```java
String respuesta = "sí";

if (respuesta.equals("sí")) {
    System.out.println("La respuesta es afirmativa.");
}
```

### ¿Por qué no se utiliza `==`?

Con tipos primitivos como `int`, `double` o `boolean`, `==` compara sus valores.
En cambio, los `String` son objetos: `==` comprueba si las dos variables se
refieren al mismo objeto, no si sus textos son iguales. A veces puede parecer
que funciona, pero no es la comparación de contenido que necesitamos.

```java
String texto1 = new String("Java");
String texto2 = new String("Java");

System.out.println(texto1 == texto2);      // false
System.out.println(texto1.equals(texto2)); // true
```

`equals()` distingue entre mayúsculas y minúsculas: `"Java"` y `"java"` no
son iguales. Cuando no queremos hacer esa distinción podemos utilizar
`equalsIgnoreCase()`:

```java
String respuesta = scanner.nextLine();

if (respuesta.equalsIgnoreCase("sí")) {
    System.out.println("Continuamos.");
}
```

Esta comparación es necesaria, por ejemplo, para comprobar la contraseña del
ejercicio 09 o una respuesta de tipo sí/no.

En [`Ejemplo04CompararStrings.java`](src/ejemplos/parte1/Ejemplo04CompararStrings.java)
puedes probar `Java`, `java`, `JAVA` y cualquier otro texto para observar la
diferencia entre `equals()` y `equalsIgnoreCase()`.

### Errores frecuentes

- Comparar textos con `==` o `!=` en vez de utilizar `equals()`.
- Esperar que `equals()` ignore mayúsculas y minúsculas.
- Escribir `respuesta.equals(sí)` sin comillas: `"sí"` es un texto, mientras
  que `sí` se interpretaría como el nombre de una variable.
- Comparar antes de leer la respuesta actual dentro de un bucle.

## Ejercicios y dificultad

| N.º | Ejercicio           | Nivel           |
| --- | ------------------- | --------------- |
| 01  | TablaMultiplicar    | Básico          |
| 02  | ParImpar            | Básico          |
| 03  | MayorDeTres         | Básico          |
| 04  | Calculadora         | Intermedio      |
| 05  | ControlAcceso       | Intermedio      |
| 06  | SumaAcumulada       | Intermedio      |
| 07  | AdivinaNumero       | Intermedio-alto |
| 08  | CajeroAutomatico    | Intermedio-alto |
| 09  | ValidadorContrasena | Intermedio-alto |
| 10  | MenuOperaciones     | Integrador      |
| 11  | Tienda              | Integrador      |
| 12  | GestionNotas        | Integrador      |

## Pistas para depurar

- Comprueba qué valor ha leído realmente `Scanner`.
- Distingue `==` para primitivos y `.equals()` para comparar el contenido de cadenas.
- Si mezclas `nextInt()` y `nextLine()`, ten en cuenta el salto de línea pendiente. En el primer ejemplo se utiliza `Integer.parseInt(scanner.nextLine())` como alternativa.
- Antes de ejecutar un bucle, identifica su condición de parada.
- Comprueba entradas límite: 0, números negativos, empates y opciones desconocidas.

---

## Parte 2 — Ampliación y proyectos

Cuando hayas terminado los 12 ejercicios de la Parte 1, continúa aquí. Esta
parte reutiliza `Scanner`, condicionales, bucles, `equals()` y acumuladores, pero
añade validación más resistente, bucles anidados, números aleatorios y proyectos
más largos. Todavía no hace falta utilizar arrays, métodos propios ni clases.

### Validación de entradas

En la Parte 1 suponíamos que el usuario escribía el tipo de dato correcto. Ahora
aprenderás a comprobarlo con `hasNextInt()` y `hasNextDouble()`. Si el dato no
es válido, `nextLine()` permite descartar la entrada incorrecta antes de volver
a preguntar. También se pueden validar rangos: una edad puede estar entre 0 y
120, y una nota entre 0 y 10.

Consulta [`Ejemplo01ValidacionEntrada.java`](src/ejemplos/parte2/Ejemplo01ValidacionEntrada.java)
y completa los ejercicios 01 y 02.

### Estadísticas con cantidad desconocida

Un **valor centinela** es un dato especial que indica que la entrada ha
terminado, como `-99` para finalizar una lista de temperaturas. El centinela no
forma parte de los datos: no se suma ni se cuenta. Para obtener un máximo o un
mínimo es más seguro utilizar el primer dato real como referencia, en vez de
empezar siempre por cero.

[`Ejemplo02Estadisticas.java`](src/ejemplos/parte2/Ejemplo02Estadisticas.java)
muestra esta técnica y comprueba también el caso en que no se introduce ningún
dato. Continúa con los ejercicios 03 y 04.

### Bucles anidados

Un bucle anidado es un bucle dentro de otro. Por cada vuelta del bucle exterior
se ejecuta completo el bucle interior. Las variables `fila` y `columna` suelen
ayudar a leer el código. Esta estructura permite generar tablas y dibujar
figuras con `print()` y `println()`.

Ejecuta [`Ejemplo03BuclesAnidados.java`](src/ejemplos/parte2/Ejemplo03BuclesAnidados.java)
y [`Ejemplo04Figuras.java`](src/ejemplos/parte2/Ejemplo04Figuras.java). Después
resuelve los ejercicios 05, 06 y 07.

### Números aleatorios

La clase `Random` genera valores distintos en cada ejecución. Por ejemplo,
`random.nextInt(6) + 1` produce un número entre 1 y 6, como un dado. La suma de
los resultados sigue necesitando un acumulador, igual que en la Parte 1.

[`Ejemplo05NumerosAleatorios.java`](src/ejemplos/parte2/Ejemplo05NumerosAleatorios.java)
simula cinco lanzamientos. Completa después los ejercicios 08 y 09.

### Juegos y comparación de texto

La combinación de `Random`, `equalsIgnoreCase()`, contadores y `do-while`
permite crear juegos pequeños. Hay que distinguir entre una opción no válida,
un empate y una partida ganada o perdida.

[`Ejemplo06PiedraPapelTijera.java`](src/ejemplos/parte2/Ejemplo06PiedraPapelTijera.java)
es una demostración de una ronda. Los ejercicios 10 y 11 amplían la idea con
varias rondas y contadores.

### `break` y `continue`

`break` termina el bucle inmediatamente. `continue` descarta la iteración actual
y pasa a la siguiente. Son útiles cuando aparece una condición especial, pero
conviene usarlos solo cuando hacen el recorrido más claro.

En [`Ejemplo07BreakContinue.java`](src/ejemplos/parte2/Ejemplo07BreakContinue.java)
`break` termina al introducir cero y `continue` ignora números negativos. El
ejercicio 12 practica ambas instrucciones.

### Proyectos integradores

Los ejercicios 13, 14 y 15 combinan menús, validaciones, `switch`, bucles,
contadores y acumuladores. Elige al menos uno para realizarlo completo:

| N.º | Proyecto | Contenidos principales |
| --- | --- | --- |
| 13 | Máquina expendedora | menú, precios y total de compra |
| 14 | Aparcamiento | horas, tarifas, vehículos y recaudación |
| 15 | Entradas de cine | edades, precios y ventas |

Todos los enunciados están en
[`src/ejercicios/parte2`](src/ejercicios/parte2). Sus clases empiezan vacías a
propósito: diseña primero el algoritmo y después escribe el código.

### Ejercicios de la Parte 2

| N.º | Ejercicio | Nivel |
| --- | --- | --- |
| 01 | EntradaSegura | Básico |
| 02 | MenuValidado | Básico |
| 03 | Temperaturas | Intermedio |
| 04 | ClasificacionResultados | Intermedio |
| 05 | Rectangulo | Intermedio |
| 06 | Triangulo | Intermedio |
| 07 | TablasMultiplicar | Intermedio |
| 08 | AdivinaNumeroAleatorio | Intermedio-alto |
| 09 | LanzamientoDados | Intermedio-alto |
| 10 | PiedraPapelTijera | Intermedio-alto |
| 11 | CaraOCruz | Intermedio-alto |
| 12 | NumerosValidos | Intermedio-alto |
| 13 | MaquinaExpendedora | Integrador |
| 14 | Aparcamiento | Integrador |
| 15 | EntradasCine | Integrador |

### Ejecutar la Parte 2

Desde la raíz del repositorio se compilan ambas partes con el mismo comando:

```bash
javac -d out $(find src -name '*.java')
java -cp out ejemplos.parte2.Ejemplo01ValidacionEntrada
java -cp out ejemplos.parte2.Ejemplo05NumerosAleatorios
java -cp out ejercicios.parte2.Ejercicio01EntradaSegura
```

Los paquetes `ejemplos.parte1`, `ejemplos.parte2`, `ejercicios.parte1` y
`ejercicios.parte2` permiten reiniciar la numeración sin que existan dos clases
con el mismo nombre completo.
