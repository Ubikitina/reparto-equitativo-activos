
import java.util.ArrayList;
import java.util.Vector;

/**
 * Clase auxiliar del programa Reparto
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 2) 
 * @Descripción: Clase auxiliar. Contiene el algoritmo recursivo vuelta atrás para el cálculo del reparto.
 */

public class AlgoritmoVueltaAtras {
	private ArrayList<Solucion> array_soluciones; //Array que contiene todas las soluciones (objetos de tipo Solucion)
	private int numSolucion; //Contador del número de solución
	
	/*Constructor*/
	public AlgoritmoVueltaAtras() {
		array_soluciones = new ArrayList<Solucion>();
		numSolucion = 0;
	}
	
	/* Método para el procesamiento previo de los datos, donde se inicializan las variables x, suma1, suma2, sumaTotal, y v.
	 * Comprobar que el valor total sea par, para que sea posible el reparto en cantidades enteras. En caso afirmativo,
	 * llama a la función DividirSociedad, para proceder con el reparto. */
	public void resolverSeparacionSocios(Vector<Integer> x, boolean verTraza) {
		int suma1 = 0;
		int suma2 = 0;
		int sumaTotal = 0;
		Vector<Integer> v = new Vector<Integer>();
		
		for(int j = 0; j < x.size(); j++) {
			v.add(0);
		}
		
		for(int i = 0; i < x.size(); i++) {
			sumaTotal = sumaTotal + x.get(i);
		}
		
		if(verTraza) {System.out.println("La suma total es: " + sumaTotal);}
		
		if (sumaTotal%2 == 0) {
			if(verTraza) {System.out.println("El módulo de 2 es igual a 0. Puede existir alguna solución.");}
			dividirSociedad(x, suma1, suma2, sumaTotal, 0, v, verTraza);
		}
		
	}
	
	/* Método recursivo para el cálculo del reparto.
	 * Recibe como parámetro los enteros suma1, suma2 y sumaTotal, con los cuales vamos controlando el transcurso de las sumas.*/
	public void dividirSociedad(Vector<Integer> x, int suma1, int suma2, int sumaTotal, int k, Vector<Integer> v, boolean verTraza) {
		if(verTraza){System.out.println(">> Transcurso vector solución: " + v);}
		if(verTraza){System.out.println(">>                      suma1: " + suma1);}
		if(verTraza){System.out.println(">>                      suma2: " + suma2);}
		
		if (k == x.size()) {
			if(suma1 == suma2) {
				if(verTraza) {System.out.println("## El vector solución es: " + v);}
				transformarVectorEnSolucionYGuardarEnArraySolucion(x, v);
			}
		}else {
			v.set(k, 1);
			if(completable(x, suma1, sumaTotal, k)) {
				dividirSociedad(x, suma1 + x.get(k), suma2, sumaTotal, k+1, v, verTraza);
				v.set(k, 0);
			}else {
				v.set(k, 0);
			}
			
			v.set(k, 2);
			if(completable(x, suma2, sumaTotal, k)) {
				dividirSociedad(x, suma1, suma2 + x.get(k), sumaTotal, k+1, v, verTraza);
				v.set(k, 0);
			}else {
				v.set(k, 0);
			}
		}
	}
	
	/* Método auxiliar del método DividirSociedad.
	 * Recibe un vector, una suma parcial, una suma total y un valor entero k. Evalúa si se puede incluir el valor entero k en la 
	 * suma parcial sin pasar el límite de que su valor sea la mitad de la sumaTotal.
	 * @return boolean true si la suma de k con la suma parcial no supera la mitad de la suma total. return false si supera.*/
	public boolean completable(Vector<Integer> x, int sumaParcial, int sumaTotal, int k) {
		if (sumaParcial + x.get(k) <= sumaTotal/2) {
			return true;
		}else {
			return false;
		}
	}
	
	/* Método que transforma el vector solucion (vector con valores 1 y 2) en un objeto 
	 * de tipo Solucion y guarda dicho objeto en el array_soluciones */
	public void transformarVectorEnSolucionYGuardarEnArraySolucion(Vector<Integer> x, Vector<Integer> v_copia) {
		String socio1 = "";
		String socio2 = "";
		for(int j = 0; j < v_copia.size(); j++) {
			if(v_copia.get(j)==1) {
				socio1 = socio1 + x.get(j) + " ";
			}else if(v_copia.get(j)==2) {
				socio2 = socio2 + x.get(j) + " ";
			}
		}
		
		//IF socio1 != a ningún socio1 del array es cuando incluye la solución. Solo con comparar un socio (socio1 en este caso) basta pq el otro son los demás
		boolean existeDuplicado = false;
		int k = 0;
		
		while ((k < array_soluciones.size()) && (existeDuplicado == false)){
			existeDuplicado = (array_soluciones.get(k).getSocio1().equals(socio1));
			k++;
		}
		
		if(existeDuplicado == false) {
			numSolucion++;		
			array_soluciones.add(new Solucion(numSolucion, socio1, socio2));
		}
	}
	
	/* Método que transforma el array_soluciones en un String para que posteriormente pueda ser utilizado en la escritura (consola o fichero)
	 * @return String con el resultado a mostrar */
	public String devolverStringArraySolucion() {
		String solucion = "";
		solucion = solucion + array_soluciones.size() + "\n";
		for(Solucion s: array_soluciones) {
			solucion = solucion + s.toString() + "\n";
		}
		return solucion;
	}
}
