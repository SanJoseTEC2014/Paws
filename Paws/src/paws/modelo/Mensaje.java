package paws.modelo;

import java.io.Serializable;

import paws.control.Principal;
import paws.control.excepciones.MascotaNoEncontradaException;

public class Mensaje implements Serializable {
	
	private static final long serialVersionUID = 555L;
	
	public static final String solicitud_REFUGIO = "desea solicitar refugio para";
	public static final String confirmacion_REFUGIO = "confirma haber dado refugio a";
	public static final String rechazo_REFUGIO = "rechaza su solicitud de refugio para";
	
	public static final String solicitud_ADOPCION = "solicita adoptar a";
	public static final String confirmacion_ADOPCION = "le confirma que le cede en adopcion a";
	public static final String rechazo_ADOPCION = "rechaza cederle en adopcion a";
	
	public static final String posible_LOCALIZACION =
		" notificarle que puede haber localizado a ";
	public static final String confirmacion_LOCALIZACION = 
		" confirmarle que se ha localizado a ";
	public static final String rechazo_LOCALIZACION = 
		" informarle que niega haber localizado a ";	
	
	private Integer mascotaAsociada;
	private String detalle;
	private String nickEmisor;
	
	public Mensaje(String pDetalle, String pNickEmisor,	Mascota pMascota) {
		detalle = pDetalle;
		nickEmisor = pNickEmisor;
		mascotaAsociada = pMascota.getID();
	}
	public String getDetalle() {
		return detalle;
	}
	public String getNickEmisor() {
		return nickEmisor;
	}
	public Integer getIDMascotaAsociada() {
		return mascotaAsociada;
	}
	public String toString(){
		try {
			return "El usuario " + nickEmisor + detalle + " la siguiente mascota: \n" + Principal.getMascotaID(mascotaAsociada);
		} catch (MascotaNoEncontradaException e) {
			return "El usuario " + nickEmisor + detalle + " una mascota desconocida. ";
		}
	}
}
