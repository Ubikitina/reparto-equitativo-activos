import java.util.Vector;

/**
 * Clase principal del programa Reparto
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 2) 
 * @Descripción: Clase principal. Contiene el método main. 
 * Comprueba los argumentos de entrada y muestra la ayuda del programa.
 */

public class Main {
	private static final String COMANDO_TRAZA = "-t"; //  Traza cada paso de manera que se describa el algoritmo utilizado.
    private static final String COMANDO_AYUDA = "-h"; // Muestra la sintaxis del comando a modo ayuda.
	
    /* Método que muestra una ayuda y la sintaxis del comando.*/
	private static void mostrarAyuda() {
        System.out.println("SINTAXIS:");
        System.out.println("reparto [-t] [-h] [fichero_entrada] [fichero_salida]");
        System.out.println("  -t                Traza la selección de activos");
        System.out.println("  -h                Muestra esta ayuda");
        System.out.println("  fichero_entrada   Nombre del fichero de entrada.");
        System.out.println("  fichero_salida    Nombre del fichero de salida.");
    }

	/* Método main*/
	public static void main(String[] args) {
		String ficheroEntrada = "";
		String ficheroSalida = "";
		LecturaEscritura lectEscr = new LecturaEscritura();
		
		// Recoger los argumentos que puedan ser posibles rutas a ficheros.
        String[]rutasDeFicheros = ArrayAndStrings.buscarArgsRutasDeFicheros(args);
        
        // Buscar en los argumentos si hay uno de traza y de ayuda.
        boolean verTraza = ArrayAndStrings.contiene(args, COMANDO_TRAZA);
        boolean verAyuda = ArrayAndStrings.contiene(args, COMANDO_AYUDA);
        
        // Si ponemos el parámetro "-h" obviamos la existencia de otros parámetros y solo sacamos la ayuda.
        if (verAyuda) {
            mostrarAyuda();
            System.exit(0);
        }
        
        // Si solo hay una ruta, tengo que comprobar si existe o no. Si existe, lo incluyo como entrada. Si no existe, es un error.
        if (rutasDeFicheros != null && rutasDeFicheros.length == 1) {
        	if(lectEscr.existeFichero(rutasDeFicheros[0])) {
        		ficheroEntrada = rutasDeFicheros[0];
        		if(verTraza) { System.out.println("El fichero ENTRADA es "+ ficheroEntrada); }
        	}else {
        		System.out.println("Error en la ruta introducida.");
        	}
        	
        // Si hay dos rutas, recojo entrada y salida
        }else if (rutasDeFicheros != null && rutasDeFicheros.length == 2){
        	ficheroEntrada = rutasDeFicheros[0];
            ficheroSalida = rutasDeFicheros[1];
            if(verTraza) { System.out.println("El fichero ENTRADA es "+ ficheroEntrada); System.out.println("El fichero SALIDA es "+ ficheroSalida); }
        }//en otro caso (por ejemplo, si hay 3 o más), no hago nada porque las rutas proporcionadas no son válidas
        
        //Hacemos la lectura por fichero.
  		int longitud_vector = 0;
  		String valores_introducidos = "";
  		try {
  			lectEscr.lecturaFichero(ficheroEntrada, verTraza);
  			longitud_vector = lectEscr.getLongitud_vector();
  			valores_introducidos = lectEscr.getValores_introducidos();
  			
  		}catch (Exception e) {
  			System.out.println("Fichero de entrada erróneo. No es posible leer.");
  		}
		
  		//Hacemos la validacion de datos
		ValidaDatos v_datos = new ValidaDatos(longitud_vector, valores_introducidos);
		
		//Si los datos introducidos son correctos, procedemos a calcular reparto
		if(v_datos.comprobacionValores(verTraza)) {
			Vector<Integer> x = v_datos.crearYDevolverVector(verTraza);
			AlgoritmoVueltaAtras alg = new AlgoritmoVueltaAtras();
			alg.resolverSeparacionSocios(x, verTraza);
			
			//Almacena las soluciones en String solucion y procede a mostrar
			if(verTraza) {System.out.println("Soluciones:");}
			String solucion = alg.devolverStringArraySolucion();
			
			try {
				//Se comprueba si el fichero salida existe. Si existe, el comando dará un error.
				if(lectEscr.existeFichero(ficheroSalida)) {
					System.out.println("Error - El fichero introducido ya existe. No se re-escribirá.");
				}else {//si no existe, procede a generar fichero
					lectEscr.escrituraFichero(solucion, ficheroSalida);
					System.out.println("Se ha generado el fichero de salida con el resultado.");
				}
			}catch (Exception ex){
				System.out.println("Ruta de salida no introducida o incorrecta. No se generará fichero. Se muestra el resultado en consola.");
				lectEscr.escrituraConsola(solucion);
			}
		}else {
			System.out.println("> Reparto NO calculado, error en datos (longitud y/o vector) introducidos");
		}
	}
}
