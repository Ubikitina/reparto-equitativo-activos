import java.util.Vector;

/**
 * Clase auxiliar del programa Reparto
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 2) 
 * @Descripción: Clase auxiliar. Contiene los métodos utilizados para la validación de los datos y creación del vector.
 */

public class ValidaDatos {
	
	private int longitud_vector; //Número de activos esperados en los valores_introducidos
	private String valores_introducidos; //Cadena de caracteres introducida, contiene los activos
	private String[] valores_introducidos_split; //Resultado de la división de los valores introducidos, tras dividirlos por cada activo
	
	/* Constructor*/
	public ValidaDatos(int longitud_vector, String valores_introducidos) {
		this.longitud_vector = longitud_vector;
		this.valores_introducidos = valores_introducidos;
		valores_introducidos_split = new String[1];
	}
	
	/* Método comprobación valores. Divide los valores introducidos (String) en array de String-s (String[]). 
	 * Después, comprueba que la longitud introducida corresponde con la longitud del array.
	 * Y, también que los caracteres introducidos en el array sean números naturales.
	 * @return Boolean de si ambas condiciones se cumplen (true) o no (false).*/
	public boolean comprobacionValores(boolean verTraza) {
		
		//Si hay valores introducidos, divide dichos valores en el array de Strings. Si no hay valores introducidos, rellena el array de strings con una cadena vacía en la posición 0.
		if(valores_introducidos != null) {
			valores_introducidos_split = valores_introducidos.split(" ");
		}else {
			valores_introducidos_split[0] = "0";	
		}
		
		// Comprobamos que la longitud sea correcta
		boolean longitud_ok = false;
		if(valores_introducidos_split.length == longitud_vector) {
			if(verTraza) { System.out.println("> Longitud: OK"); }
			longitud_ok = true;
		}else {
			if(verTraza) { System.out.println("> Longitud: NO OK"); }
			longitud_ok = false;
		}
		
		//Comprobamos que los caracteres introducidos sean correctos (que no haya letras ni números no naturales)
		boolean datos_ok = false;
		try {
			for (int i = 0; i < valores_introducidos_split.length; i++)    {     
	            if(Integer.parseInt(valores_introducidos_split[i])<=0) {
	            	throw new Exception();
	            }
	        }
			datos_ok = true;
			if(verTraza) { System.out.println("> Vector: OK"); }
		}catch(Exception e) {
			if(verTraza) { System.out.println("> Vector: NO OK"); }
			datos_ok = false;
		}
		return longitud_ok && datos_ok;
	}
	
	/* Método para generar el vector.
	 * La generación del vector la hace a partir de su longitud y los valores introducidos en el array de Strings.
	 * @return Vector<Integer>, devuelve el vector generado.*/
	public Vector<Integer> crearYDevolverVector(boolean verTraza) {
		//Generar el vector
		Vector<Integer> v = new Vector<Integer>(longitud_vector);
		for (int i = 0; i < valores_introducidos_split.length; i++)    {     
			v.add(Integer.parseInt(valores_introducidos_split[i]));
        }
		return v;
	}

}
