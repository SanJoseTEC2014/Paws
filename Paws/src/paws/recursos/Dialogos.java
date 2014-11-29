package paws.recursos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dialogos {
	public static String getCondicionesUso() throws IOException {
		FileInputStream archivo = new FileInputStream(RutasArchivo.recursosRoot + "condiciones.nfo");
		BufferedReader lector = new BufferedReader(new InputStreamReader(archivo, "UTF-8"));
		String condiciones = new String();
		
		String lineaActual;
		while ((lineaActual = lector.readLine()) != null ) {
			condiciones += lineaActual + "\n";
		}
		archivo.close();
		return condiciones;
	}
	
	public static String getAyudaBusqueda() throws IOException {
		FileInputStream archivo = new FileInputStream(RutasArchivo.recursosRoot + "ayudabusqueda.nfo");
		BufferedReader lector = new BufferedReader(new InputStreamReader(archivo, "UTF-8"));
		String ayuda = new String();
		
		String lineaActual;
		while ( (lineaActual = lector.readLine()) != null ) {
			ayuda += lineaActual + "\n";
		}
		archivo.close();
		return ayuda;
	}
}
