# Prácticas de Java: Scanner, condicionales y bucles

**1.º DAM · Java 21 · Sin Maven ni dependencias externas**

> 📚 **Material didáctico | CFGS Desarrollo de Aplicaciones Multiplataforma**
>
> **Curso:** 1.º DAM  
> **Módulo:** Programación  
> **Profesor y autor:** Christian Bihurriet  
> **Centro educativo:** CESUR
>
> Repositorio de material docente elaborado para la enseñanza de la
> programación en el Ciclo Formativo de Grado Superior en Desarrollo de
> Aplicaciones Multiplataforma (DAM).
>
> Incluye explicaciones, ejemplos progresivos y ejercicios diseñados para
> estudiantes que están aprendiendo a programar.

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

#### Comprobar el formato

`hasNextInt()` y `hasNextDouble()` permiten saber si el siguiente dato puede
interpretarse como un número. Estos métodos no consumen la entrada. En la Parte
1 basta con conocer su finalidad; la aplicación con bucles se reserva para la
Parte 2.

#### Qué estrategia utilizar en cada parte

En los primeros ejercicios conviene leer líneas y convertirlas:

```java
int numero = Integer.parseInt(scanner.nextLine());
```

Es una estrategia sencilla y consistente para la Parte 1. En la Parte 2
utilizaremos `hasNextInt()` o `hasNextDouble()` junto con bucles para controlar
entradas incorrectas y rangos no permitidos. El ejemplo completo de validación
se encuentra en
[`Ejemplo01ValidacionEntrada.java`](src/ejemplos/parte2/Ejemplo01ValidacionEntrada.java).

**Ahora practica:**
[`Ejercicio 01 — Tabla de multiplicar`](src/ejercicios/parte1/Ejercicio01TablaMultiplicar.java).

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

**Ahora practica:**
[`Ejercicio 08 — Cajero automático`](src/ejercicios/parte1/Ejercicio08CajeroAutomatico.java)
y
[`Ejercicio 10 — Menú de operaciones`](src/ejercicios/parte1/Ejercicio10MenuOperaciones.java).

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

**Ahora practica:**
[`Ejercicio 06 — Suma acumulada`](src/ejercicios/parte1/Ejercicio06SumaAcumulada.java),
[`Ejercicio 07 — Adivina el número`](src/ejercicios/parte1/Ejercicio07AdivinaNumero.java),
[`Ejercicio 11 — Tienda`](src/ejercicios/parte1/Ejercicio11Tienda.java) y
[`Ejercicio 12 — Gestión de notas`](src/ejercicios/parte1/Ejercicio12GestionNotas.java).

## Cadenas de texto (`String`)

### ¿Qué es una cadena?

Una cadena, o `String`, es una secuencia de caracteres: letras, números,
espacios y símbolos. Se escribe entre comillas dobles:

```java
String nombre = "Ana";
String mensaje = "Hola, mundo";
String codigo = "A-123";
```

Aunque una cadena contenga números, sigue siendo texto. Por ejemplo,
`"123"` no es lo mismo que el entero `123`: con el primero se trabaja como
texto y con el segundo se pueden realizar operaciones matemáticas.

### Leer cadenas con `Scanner`

`nextLine()` lee una línea completa, incluidos los espacios. `next()` lee solo
la siguiente palabra, hasta encontrar un espacio o separador:

```java
System.out.print("Escribe tu nombre completo: ");
String nombre = scanner.nextLine();

System.out.print("Escribe una palabra: ");
String palabra = scanner.next();
scanner.nextLine(); // Consume el resto de la línea si vamos a seguir leyendo.
```

Para nombres, frases y respuestas completas suele ser más cómodo utilizar
`nextLine()`. Al mezclarlo con `nextInt()` o `nextDouble()` hay que recordar el
salto de línea pendiente explicado en la sección de `Scanner`.

### Concatenar cadenas

Concatenar significa unir textos. El operador `+` permite construir mensajes:

```java
String nombre = "Ana";
int edad = 20;

String mensaje = "Me llamo " + nombre + " y tengo " + edad + " años.";
System.out.println(mensaje);
```

Cuando aparece una cadena, Java convierte automáticamente los valores que se
encuentran después en texto. El orden de las operaciones puede ser importante:

```java
System.out.println("Resultado: " + 2 + 3); // Resultado: 23
System.out.println("Resultado: " + (2 + 3)); // Resultado: 5
```

Los paréntesis indican que primero se hace la suma y después se concatena el
resultado.

### Caracteres especiales

Algunos caracteres se escriben utilizando una barra inversa (`\`):

```java
System.out.println("Primera línea\nSegunda línea");
System.out.println("Una palabra entre \"comillas\"");
```

Los más habituales son `\n` para salto de línea, `\t` para tabulación y
`\"` para incluir comillas dobles dentro del texto.

### Métodos básicos de `String`

Las cadenas tienen métodos que permiten consultar o transformar su contenido.
Estos métodos no modifican la cadena original: devuelven un resultado nuevo.

| Método | Para qué sirve | Ejemplo |
| --- | --- | --- |
| `length()` | Cuenta los caracteres | `nombre.length()` |
| `charAt(indice)` | Obtiene un carácter | `nombre.charAt(0)` |
| `toUpperCase()` | Convierte a mayúsculas | `nombre.toUpperCase()` |
| `toLowerCase()` | Convierte a minúsculas | `nombre.toLowerCase()` |
| `trim()` | Quita espacios al principio y al final | `nombre.trim()` |
| `isEmpty()` | Comprueba si tiene cero caracteres | `nombre.isEmpty()` |

Los índices empiezan en cero. En la cadena `"Java"`, `charAt(0)` devuelve `J` y
`charAt(3)` devuelve `a`. Intentar acceder a una posición que no existe produce
un error, por lo que conviene comprobar antes la longitud cuando el índice
depende de la entrada.

Ejemplo de uso:

```java
String texto = "  Java  ";
String limpio = texto.trim();

System.out.println("Texto: " + limpio);
System.out.println("Caracteres: " + limpio.length());
System.out.println("Primera letra: " + limpio.charAt(0));
System.out.println("Mayúsculas: " + limpio.toUpperCase());
System.out.println("¿Está vacío? " + limpio.isEmpty());
```

Como las cadenas son inmutables, `trim()` no cambia `texto`; por eso guardamos
su resultado en `limpio`. Si queremos conservar el cambio, debemos asignarlo:

```java
texto = texto.trim();
```

### Cadenas vacías y espacios

Una cadena vacía (`""`) tiene longitud cero. Una cadena formada por espacios no
está vacía hasta aplicar `trim()`:

```java
String entrada = scanner.nextLine().trim();

if (entrada.isEmpty()) {
    System.out.println("No has escrito ningún texto.");
}
```

Este patrón resulta útil para validar nombres y respuestas antes de procesarlas.

### Errores frecuentes al trabajar con cadenas

- Confundir `"123"` con el número `123`.
- Usar `next()` cuando se necesita leer una frase completa.
- Olvidar el salto de línea pendiente al mezclar métodos de `Scanner`.
- Pensar que `toUpperCase()` o `trim()` modifican la variable original.
- Intentar usar `charAt(0)` sobre una cadena vacía.
- Confundir concatenar (`+`) con sumar números.
- Olvidar las comillas dobles al escribir un texto.

Puedes practicar estas operaciones en
[`Ejemplo05Cadenas.java`](src/ejemplos/parte1/Ejemplo05Cadenas.java). Después
resuelve
[`Ejercicio 13 — Analizador de texto`](src/ejercicios/parte1/Ejercicio13AnalizadorTexto.java),
[`Ejercicio 14 — Validador de nombre`](src/ejercicios/parte1/Ejercicio14ValidadorNombre.java),
[`Ejercicio 15 — Respuesta`](src/ejercicios/parte1/Ejercicio15Respuesta.java) y
[`Ejercicio 16 — Contar vocales`](src/ejercicios/parte1/Ejercicio16ContarVocales.java).

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

**Ahora practica:**
[`Ejercicio 09 — Validador de contraseña`](src/ejercicios/parte1/Ejercicio09ValidadorContrasena.java)
y
[`Ejercicio 15 — Respuesta`](src/ejercicios/parte1/Ejercicio15Respuesta.java).

## Ejercicios y dificultad

| N.º | Ejercicio           | Nivel           |
| --- | ------------------- | --------------- |
| 01  | [TablaMultiplicar](src/ejercicios/parte1/Ejercicio01TablaMultiplicar.java) | Básico          |
| 02  | [ParImpar](src/ejercicios/parte1/Ejercicio02ParImpar.java) | Básico          |
| 03  | [MayorDeTres](src/ejercicios/parte1/Ejercicio03MayorDeTres.java) | Básico          |
| 04  | [Calculadora](src/ejercicios/parte1/Ejercicio04Calculadora.java) | Intermedio      |
| 05  | [ControlAcceso](src/ejercicios/parte1/Ejercicio05ControlAcceso.java) | Intermedio      |
| 06  | [SumaAcumulada](src/ejercicios/parte1/Ejercicio06SumaAcumulada.java) | Intermedio      |
| 07  | [AdivinaNumero](src/ejercicios/parte1/Ejercicio07AdivinaNumero.java) | Intermedio-alto |
| 08  | [CajeroAutomatico](src/ejercicios/parte1/Ejercicio08CajeroAutomatico.java) | Intermedio-alto |
| 09  | [ValidadorContrasena](src/ejercicios/parte1/Ejercicio09ValidadorContrasena.java) | Intermedio-alto |
| 10  | [MenuOperaciones](src/ejercicios/parte1/Ejercicio10MenuOperaciones.java) | Integrador      |
| 11  | [Tienda](src/ejercicios/parte1/Ejercicio11Tienda.java) | Integrador      |
| 12  | [GestionNotas](src/ejercicios/parte1/Ejercicio12GestionNotas.java) | Integrador      |
| 13  | [AnalizadorTexto](src/ejercicios/parte1/Ejercicio13AnalizadorTexto.java) | Básico          |
| 14  | [ValidadorNombre](src/ejercicios/parte1/Ejercicio14ValidadorNombre.java) | Intermedio      |
| 15  | [Respuesta](src/ejercicios/parte1/Ejercicio15Respuesta.java) | Intermedio      |
| 16  | [ContarVocales](src/ejercicios/parte1/Ejercicio16ContarVocales.java) | Intermedio      |

## Pistas para depurar

- Comprueba qué valor ha leído realmente `Scanner`.
- Distingue `==` para primitivos y `.equals()` para comparar el contenido de cadenas.
- Si mezclas `nextInt()` y `nextLine()`, ten en cuenta el salto de línea pendiente. En el primer ejemplo se utiliza `Integer.parseInt(scanner.nextLine())` como alternativa.
- Antes de ejecutar un bucle, identifica su condición de parada.
- Comprueba entradas límite: 0, números negativos, empates y opciones desconocidas.

---

## Parte 2 — Ampliación y proyectos

Cuando hayas terminado los 16 ejercicios de la Parte 1, continúa aquí. Esta
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
y completa
[`Ejercicio 01 — Entrada segura`](src/ejercicios/parte2/Ejercicio01EntradaSegura.java)
y
[`Ejercicio 02 — Menú validado`](src/ejercicios/parte2/Ejercicio02MenuValidado.java).

### Estadísticas con cantidad desconocida

Un **valor centinela** es un dato especial que indica que la entrada ha
terminado, como `-99` para finalizar una lista de temperaturas. El centinela no
forma parte de los datos: no se suma ni se cuenta. Para obtener un máximo o un
mínimo es más seguro utilizar el primer dato real como referencia, en vez de
empezar siempre por cero.

[`Ejemplo02Estadisticas.java`](src/ejemplos/parte2/Ejemplo02Estadisticas.java)
muestra esta técnica y comprueba también el caso en que no se introduce ningún
dato. Continúa con
[`Ejercicio 03 — Temperaturas`](src/ejercicios/parte2/Ejercicio03Temperaturas.java)
y
[`Ejercicio 04 — Clasificación de resultados`](src/ejercicios/parte2/Ejercicio04ClasificacionResultados.java).

### Bucles anidados

Un bucle anidado es un bucle dentro de otro. Por cada vuelta del bucle exterior
se ejecuta completo el bucle interior. Las variables `fila` y `columna` suelen
ayudar a leer el código. Esta estructura permite generar tablas y dibujar
figuras con `print()` y `println()`.

Ejecuta [`Ejemplo03BuclesAnidados.java`](src/ejemplos/parte2/Ejemplo03BuclesAnidados.java)
y [`Ejemplo04Figuras.java`](src/ejemplos/parte2/Ejemplo04Figuras.java). Después
resuelve
[`Ejercicio 05 — Rectángulo`](src/ejercicios/parte2/Ejercicio05Rectangulo.java),
[`Ejercicio 06 — Triángulo`](src/ejercicios/parte2/Ejercicio06Triangulo.java) y
[`Ejercicio 07 — Tablas de multiplicar`](src/ejercicios/parte2/Ejercicio07TablasMultiplicar.java).

### Números aleatorios

La clase `Random` genera valores distintos en cada ejecución. Por ejemplo,
`random.nextInt(6) + 1` produce un número entre 1 y 6, como un dado. La suma de
los resultados sigue necesitando un acumulador, igual que en la Parte 1.

[`Ejemplo05NumerosAleatorios.java`](src/ejemplos/parte2/Ejemplo05NumerosAleatorios.java)
simula cinco lanzamientos. Completa después
[`Ejercicio 08 — Adivina el número aleatorio`](src/ejercicios/parte2/Ejercicio08AdivinaNumeroAleatorio.java)
y
[`Ejercicio 09 — Lanzamiento de dados`](src/ejercicios/parte2/Ejercicio09LanzamientoDados.java).

### Juegos y comparación de texto

La combinación de `Random`, `equalsIgnoreCase()`, contadores y `do-while`
permite crear juegos pequeños. Hay que distinguir entre una opción no válida,
un empate y una partida ganada o perdida.

[`Ejemplo06PiedraPapelTijera.java`](src/ejemplos/parte2/Ejemplo06PiedraPapelTijera.java)
es una demostración de una ronda.
[`Ejercicio 10 — Piedra, papel o tijera`](src/ejercicios/parte2/Ejercicio10PiedraPapelTijera.java)
y
[`Ejercicio 11 — Cara o cruz`](src/ejercicios/parte2/Ejercicio11CaraOCruz.java)
amplían la idea con varias rondas y contadores.

### `break` y `continue`

`break` termina el bucle inmediatamente. `continue` descarta la iteración actual
y pasa a la siguiente. Son útiles cuando aparece una condición especial, pero
conviene usarlos solo cuando hacen el recorrido más claro.

En [`Ejemplo07BreakContinue.java`](src/ejemplos/parte2/Ejemplo07BreakContinue.java)
`break` termina al introducir cero y `continue` ignora números negativos. El
[`Ejercicio 12 — Números válidos`](src/ejercicios/parte2/Ejercicio12NumerosValidos.java)
practica ambas instrucciones.

### Proyectos integradores

Los ejercicios 13, 14 y 15 combinan menús, validaciones, `switch`, bucles,
contadores y acumuladores. Elige al menos uno para realizarlo completo:

| N.º | Proyecto | Contenidos principales |
| --- | --- | --- |
| 13 | [Máquina expendedora](src/ejercicios/parte2/Ejercicio13MaquinaExpendedora.java) | menú, precios y total de compra |
| 14 | [Aparcamiento](src/ejercicios/parte2/Ejercicio14Aparcamiento.java) | horas, tarifas, vehículos y recaudación |
| 15 | [Entradas de cine](src/ejercicios/parte2/Ejercicio15EntradasCine.java) | edades, precios y ventas |

Todos los enunciados están en
[`src/ejercicios/parte2`](src/ejercicios/parte2). Sus clases empiezan vacías a
propósito: diseña primero el algoritmo y después escribe el código.

### Ejercicios de la Parte 2

| N.º | Ejercicio | Nivel |
| --- | --- | --- |
| 01 | [EntradaSegura](src/ejercicios/parte2/Ejercicio01EntradaSegura.java) | Básico |
| 02 | [MenuValidado](src/ejercicios/parte2/Ejercicio02MenuValidado.java) | Básico |
| 03 | [Temperaturas](src/ejercicios/parte2/Ejercicio03Temperaturas.java) | Intermedio |
| 04 | [ClasificacionResultados](src/ejercicios/parte2/Ejercicio04ClasificacionResultados.java) | Intermedio |
| 05 | [Rectangulo](src/ejercicios/parte2/Ejercicio05Rectangulo.java) | Intermedio |
| 06 | [Triangulo](src/ejercicios/parte2/Ejercicio06Triangulo.java) | Intermedio |
| 07 | [TablasMultiplicar](src/ejercicios/parte2/Ejercicio07TablasMultiplicar.java) | Intermedio |
| 08 | [AdivinaNumeroAleatorio](src/ejercicios/parte2/Ejercicio08AdivinaNumeroAleatorio.java) | Intermedio-alto |
| 09 | [LanzamientoDados](src/ejercicios/parte2/Ejercicio09LanzamientoDados.java) | Intermedio-alto |
| 10 | [PiedraPapelTijera](src/ejercicios/parte2/Ejercicio10PiedraPapelTijera.java) | Intermedio-alto |
| 11 | [CaraOCruz](src/ejercicios/parte2/Ejercicio11CaraOCruz.java) | Intermedio-alto |
| 12 | [NumerosValidos](src/ejercicios/parte2/Ejercicio12NumerosValidos.java) | Intermedio-alto |
| 13 | [MaquinaExpendedora](src/ejercicios/parte2/Ejercicio13MaquinaExpendedora.java) | Integrador |
| 14 | [Aparcamiento](src/ejercicios/parte2/Ejercicio14Aparcamiento.java) | Integrador |
| 15 | [EntradasCine](src/ejercicios/parte2/Ejercicio15EntradasCine.java) | Integrador |

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

## Sobre este repositorio

Este proyecto forma parte de una colección de recursos educativos desarrollados
por Christian Bihurriet para sus clases del módulo de Programación en el Ciclo
Formativo de Grado Superior en Desarrollo de Aplicaciones Multiplataforma.

El código está adaptado deliberadamente al nivel del alumnado y prioriza la
claridad didáctica sobre la complejidad técnica.
