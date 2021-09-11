import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Clase auxiliar del programa Reparto
 * @Autor: Ubikitina
 * @version: 1.0
 * @Asignatura: Programación y Estructura de Datos Avanzadas (PED 2) 
 * @Descripción: Clase auxiliar. Contiene los métodos utilizados para la lectura por archivo y escritura en consola y escritura en archivo.
 */
public class LecturaEscritura {
	private int longitud_vector;
	private String valores_introducidos;

	/* Constructor*/
	public LecturaEscritura() {
		longitud_vector = 0;
		valores_introducidos = "";
	}
	
	/* Método para la lectura por fichero.*/
	public void lecturaFichero(String rutaEntrada, boolean verTraza) throws Exception {
		
        // Apertura del fichero de entrada para poder volcar su contenido sobre las variables correspondientes
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(rutaEntrada)));
        }
        catch(FileNotFoundException e) {
            throw new IllegalArgumentException("No se ha podido encontrar el fichero '" + rutaEntrada + "'", e);
        }
        
        // Lectura primera línea
		try {
			longitud_vector = Integer.parseInt(bufferedReader.readLine());
			if(verTraza) { System.out.println("Linea 1: "+ longitud_vector); }
		} catch (Exception e1) {
			longitud_vector = 0;
		}
		// Lectura segunda línea
		valores_introducidos = bufferedReader.readLine();
		if(verTraza) { System.out.println("Linea 2: "+ valores_introducidos); }
        
        // Cierre del fichero de entrada
        try {
            bufferedReader.close();
        }
        catch(IOException e) {
            throw new IllegalStateException("No se ha podido cerrar correctamente el fichero '" + rutaEntrada + "'.", e);
        }
    }
	
	/* @return Entero (int) de la longitud del vector*/
	public int getLongitud_vector() {
		return longitud_vector;
	}

	/* @return String con los valores introducidos en el vector*/
	public String getValores_introducidos() {
		return valores_introducidos;
	}
	
	/* Método para escribir en consola el resultado*/
	public void escrituraConsola(String resultado) {
		System.out.println(resultado);		
	}
	
	/* Método para la escritura del string en el fichero de salida.*/
	public void escrituraFichero(String resultado, String rutaSalida) throws Exception {
		try {
			FileWriter escritor = new FileWriter(rutaSalida);
			for (int i =0; i<resultado.length(); i++){
				escritor.write(resultado.charAt(i));
			}
			escritor.close();			
		}catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	/* Método para comprobar si existe un fichero.
	 * @return Boolean indicando si el fichero existe (true) o no (false).*/
	public boolean existeFichero(String rutaSalida) {
		File f = new File (rutaSalida);
		if(f.exists()){
			return true;
		}else {
			return false;
		}
	}
}
