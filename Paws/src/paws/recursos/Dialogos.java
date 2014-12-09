package paws.recursos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Dialogos {
	private static String getDialogo(String pNombreArchivo) throws IOException {
		FileInputStream archivo = new FileInputStream(RutasArchivo.recursosRoot + pNombreArchivo);
		BufferedReader lector = new BufferedReader(new InputStreamReader(archivo, "UTF-8"));
		String resultado = new String();
		
		String lineaActual;
		while ((lineaActual = lector.readLine()) != null ) {
			resultado += lineaActual + "\n";
		}
		archivo.close();
		return resultado;
	}
	
	public static String getCondicionesUso() throws IOException {
		return getDialogo("condiciones.nfo");
	}
	
	public static String getAyudaBusqueda() throws IOException {
		return getDialogo("ayudabusqueda.nfo");
	}
	
	public static String getMensajeCorreo() throws IOException {
		return getDialogo("correo_coincidencias.nfo");
	}
}
