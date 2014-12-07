package paws.control;

import java.text.SimpleDateFormat;
import java.util.*;

import paws.control.excepciones.TiempoSinEstablecerException;
import paws.modelo.Usuario;

public class Tiempo {
	private static Calendar fechaSistema;

	public static void setFechaInicioProduccion(Calendar pFechaInicio){
		fechaSistema = pFechaInicio;
	}

	public static void avanzarDia() {
		fechaSistema.add(Calendar.DAY_OF_MONTH, 1);
		for (Usuario i : Principal.usuarios) {
			Emparejador.rutinaMatch(i);
		}
	}

	public static Calendar getFechaSistema() throws TiempoSinEstablecerException{
		if (fechaSistema == null) {
			throw new TiempoSinEstablecerException("No se ha establecido la fecha del sistema.");
		}
		return fechaSistema;
	}
	
	public static String getStringFechaSistema(){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/YYYY");
		return sdf.format(fechaSistema.getTime());
	}

	public static boolean isFechaEstablecida() {
		return (fechaSistema != null);
	}

}
