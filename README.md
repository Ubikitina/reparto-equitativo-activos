# Objetivo reparto-equitativo-activos
Trabajo realizado en la asignatura Programación y Estructuras de Datos Avanzadas (PREDA) en el Grado de Ingeniería Informática de la UNED. Consiste en diseñar un algoritmo que calcule todas las posibles formas de repartir los activos de una sociedad a medias, siguiendo el esquema "**vuelta atrás**".

El programa se ha desarrollado en Java siguiendo un diseño orientado a objetos.

**Enunciado:**
> _Dos socios que forman una sociedad comercial desean disolverla. Cada uno de los n activos de la sociedad que hay que repartir tiene un valor entero positivo. Los socios quieren repartir dichos activos a medias y, para ello, quieren conocer todas las posibles formas que tienen de dividir el conjunto de activos en dos subconjuntos disjuntos, de forma que cada uno de ellos tenga el mismo valor entero._

> _Por ejemplo, considérense el siguiente conjunto de activos:_

> Activo | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10
> ------ | - | - | - | - | - | - | - | - | - | --
> Valor | 10 | 9 | 5 | 3 | 3 | 2 | 2 | 2 | 2 | 2

> _Entonces un posible reparto válido será:_
> - _Socio1: 10, 2, 2, 2, 2, 2_
> - _Socio2: 9, 5, 3, 3_

> _Se pide diseñar un algoritmo, siguiendo el esquema de vuelta atrás, que resuelva el problema de proporcionar todos los posibles repartos equitativos._


# Algoritmo
Tal y como se describe en el enunciado, el algoritmo utilizado para resolver el problema ha sido Vuelta atrás.


## Descripción del algoritmo Vuelta atrás
La estrategia de **Vuelta atrás** es una técnica algorítmica que se aplica a problemas en los que sólo podemos recurrir a una búsqueda exhaustiva, recorriendo el espacio de todas las posibles soluciones hasta que encontremos una de ellas o hasta que hayamos explorado todas las opciones, concluyendo así que no existe la solución buscada. Puesto que esta búsqueda exhaustiva es muy costosa, es importante aplicar el conocimiento disponible sobre el problema para terminar la exploración de un camino tan pronto como se sepa que dicho camino no puede alcanzar una solución.

Un **esquema general** de la técnica vuelta atrás es:
```
fun VueltaAtras(v: Secuencia, k: entero)
  {v es una secuencia k-prometedora}
  IniciarExploraciónNivel(k)
  mientras OperacionesPendientes(k) hacer
    extender v con siguiente opción
    si SoluciónCompleta(v) entonces
      ProcesarSolución(v)
    sino
      si Completable(v) entonces
        VueltaAtras(v, k+1)
      fsi
    fsi
  fmientras
ffun
```

Cuenta con las siguientes **funciones**:
- **lniciarExploraciónNivel( )**: recoge todas las opciones posibles en que se puede extender la solución k-prometedora.
- **OpcionesPendientes( )**: comprueba que quedan opciones por explorar en el nivel.
- **SoluciónCompleta( )**: comprueba que se haya completado una solución al problema.
- **ProcesarSolución( )**: representa las operaciones que se quieran realizar con la solución, como imprimirla o devolverla al punto de llamada.
- **Completable( )**: comprueba que la solución k-prometedora se puede extender con la opción elegida cumpliendo las restricciones del problema hasta llegar a completar una solución.

Nótese que se trata de un algoritmo **recursivo** debido a que incluye la llamada a `VueltaAtras(v, k+1)`.


## Aplicación del algoritmo Vuelta atrás al problema
Siguiendo los pasos indicados anteriormente en la exposición del esquema, para realizar el reparto equitativo de activos, se han aplicado los elementos de la aproximación general de la siguiente manera:
- **OpcionesPendientes( )**: para comprobar si quedan opciones pendientes por explorar en el nivel, en cada llamada recursiva se realiza k = N. Si es falso, es que quedan opciones pendientes.
- **SoluciónCompleta( )**: en este caso se realiza mediante dos if-s. En primer lugar se comprueba si k = N, y después si suma1 = suma2. Si ambas son verdaderas, es que la solución está completa.
- **ProcesarSolución( )**: representado mediante la función `Procesar(v)`.
- **Completable( )**: representado mediante la función `Completable (x: Vector, sumaParcial, sumaTotal, k: entero): booleano`. Comprueba que la asignación del activo k a un determinado socio no lleva a que los valores asignados a este socio superen la mitad del total.

El esquema particular para realizar el reparto equitativo de activos, diseñado en base al algoritmo vuelta atrás es:
```
tipo Vector = matriz[1..N] de entero
fun DividirSociedad (x: Vector, suma1, suma2, sumaTotal, k: entero, v: Vector)
  {v es un k-prometedor}
  si k = N entonces
    si suma1 = suma2 entonces
      Procesar(v)
    fsi
  sino
    v[k+1] <- 1
    si Completable (x, suma1, sumaTotal, k+1) entonces
      DividirSociedad(x, suma1 + x[k+1], suma2, sumaTotal, k+1, v)
    fsi
    v[k+1] <- 2
    si Completable(x, suma2, sumaTotal, k+1) entonces
      DividirSociedad(x, suma1, suma2 + x[k+1], sumaTotal, k+1, v)
    fsi
  fsi
ffun
```

Donde:
- `x` corresponde al vector que almacena los valores de los activos de la sociedad.
- `v` corresponde al vector en el que vamos construyendo la solución. Contiene valores 1 y 2, que indican si el activo de la posición i se ha asignado al socio 1 o socio 2.
- `suma1` y `suma2` corresponden a las sumas de activos que se han asignado al socio 1 y socio 2 respectivamente.
- `sumaTotal` corresponde a la suma total de los valores de los activos en `x`.
- `k` es el siguiente activo a asignar.

Por tanto, el algoritmo comprueba si se ha alcanzado una solución, es decir, si se han asignado todos los activos (k = N), y si se ha hecho de tal forma que la suma asignada a cada socio es la misma. Si aún quedan activos pendientes, se construyen los vectores k+1 prometedores, que en este caso son dos y corresponden a asignar el siguiente activo k+1 al socio 1 o al socio 2.

## Funciones auxiliares
Aparte de la función de vuelta atrás `DividirSociedad`, son necesarias otras dos funciones: Completable y ResolverSeparacionSocios.

### Función Completable
Antes de llamar recursívamente a la función principal `DividirSociedad`, se comprueba si la asignación realizada hasta el momento es válida mediante una llamada a la función Completable:
```
fun Completable (x: Vector, sumaParcial, sumaTotal, k: entero): booleano
  si sumaParcial +x[k] ≤ sumaTotal div 2 entonces
    dev cierto
  sino
    dev falso
  fsi
ffun
```
Esta función comprueba que la asignación del activo k a un determinado socio no lleva a que los valores asignados a este socio superen la mitad del total.

### Función ResolverSeparacionSocios
Además, para realizar la primera llamada a la función DividirSociedad que reparte los activos, hay que realizar un procesamiento previo para inicializar las variables x, suma1, suma2, sumaTotal, y v. Además, antes de la llamada a la función DividirSociedad, hay que comprobar que el valor total sea par, para que sea posible el reparto en cantidades enteras. Es por ello que se realiza la siguiente función:
```
fun ResolverSeparacionSocios (x: Vector)
  var
    i, suma1, suma2, sumaTotal: entero
    v: Vector
  fvar
  sumaTotal <- 0
  suma1 <- 0
  suma2 <- 0
  para i <- 1 hasta N hacer
    sumaTotal <- sumaTotal + x[i]
  fpara
  si sumaTotal mod 2 = 0 entonces
    DividirSociedad(x, suma1, suma2, sumaTotal, 0, v)
  fsi
ffun
```


# Código desarrollado


# Ejecución
La práctica se invoca usando la siguiente sintaxis:
```
java reparto [-t][-h] [fichero_entrada] [fichero_salida]
```
o
```
java –jar reparto.jar [-t][-h] [fichero_entrada] [fichero_salida]
```
Los argumentos son los siguientes:
- `-t`: traza cada paso de manera que se describa la aplicación del algoritmo utilizado.
- `-h`: muestra una ayuda y la sintaxis del comando.
- `fichero_entrada`: es el nombre del fichero del que se leen los datos de entrada. Consta de una primera línea que indica el número de elementos (activos) en el vector de enteros y una segunda línea con el propio vector. Si la entrada no es correcta, el programa debe indicarlo.
- `fichero_salida`: es el nombre del fichero que se creará para almacenar la salida. Si el fichero ya existe, el comando dará un error. Si falta este argumento, el programa muestra el resultado por pantalla.

# Bibliografía
- Araujo Serna, L., Martínez Unanue, R., & Rodríguez Artacho, M. (2011). _Programación y estructuras de datos avanzadas_. Madrid: Centro de Estudios Ramón Areces.
