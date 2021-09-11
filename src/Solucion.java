/**
 * Clase auxiliar del programa Reparto
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 2) 
 * @Descripción: Clase auxiliar. Contiene la estructura de datos utilizada para la solución.
 */
public class Solucion {
	private int numero; //Número de solución
	private String socio1; //Solución para el socio 1 representado en una cadena de caracteres que incluye los activos que le corresponden.
	private String socio2; //Solución para el socio 2 representado en una cadena de caracteres que incluye los activos que le corresponden.
	
	/* Constructor*/
	public Solucion(int numero, String socio1, String socio2) {
		this.numero = numero;
		this.socio1 = socio1;
		this.socio2 = socio2;
	}
	
	/* El método toString() en Java se utiliza para convertir a String cualquier objeto Java. En este caso se reescribe este método para que 
	 * el objeto Solucion se convierta a la cadena de caracteres con un determinado formato, definido según especifica la práctica.
	 * @return String con la información de un objeto Solucion*/
	public String toString() {
		return (numero + "\n" + socio1 + "\n" + socio2);
	}

	/* Método getter de socio1, que devuelve una cadena de caracteres con los activos que corresponden al socio 1.
	 * @return String con los activos que corresponden al socio 1.*/
	public String getSocio1() {
		return socio1;
	}

	/* Método getter de socio2, que devuelve una cadena de caracteres con los activos que corresponden al socio 2.
	 * @return String con los activos que corresponden al socio 2.*/
	public String getSocio2() {
		return socio2;
	}
}
