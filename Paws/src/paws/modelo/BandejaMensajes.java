package paws.modelo;

import java.io.Serializable;
import java.util.LinkedList;

public class BandejaMensajes implements Serializable {
	
	private static final long serialVersionUID = 666L;
	
	private LinkedList<Mensaje> entradaNotificacionesLocalizacion;
	private LinkedList<Mensaje> entradaConfirmacionesLocalizacion;
	private LinkedList<Mensaje> entradaRechazosLocalizacion;
	private LinkedList<Mensaje> entradaSolicitudesRefugio;
	private LinkedList<Mensaje> entradaConfirmacionesRefugio;
	private LinkedList<Mensaje> entradaRechazosRefugio;
	private LinkedList<Mensaje> entradaSolicitudesAdopcion;
	private LinkedList<Mensaje> entradaConfirmacionesAdopcion;
	private LinkedList<Mensaje> entradaRechazosAdopcion;
	private LinkedList<Mensaje> entradaErroresMediador;
	public BandejaMensajes() {
		entradaNotificacionesLocalizacion 	= new LinkedList<Mensaje>();
		entradaConfirmacionesLocalizacion 	= new LinkedList<Mensaje>();
		entradaRechazosLocalizacion 		= new LinkedList<Mensaje>();
		entradaSolicitudesRefugio 			= new LinkedList<Mensaje>();
		entradaConfirmacionesRefugio 		= new LinkedList<Mensaje>();
		entradaRechazosRefugio 				= new LinkedList<Mensaje>();
		entradaSolicitudesAdopcion 			= new LinkedList<Mensaje>();
		entradaConfirmacionesAdopcion 		= new LinkedList<Mensaje>();
		entradaRechazosAdopcion 			= new LinkedList<Mensaje>();
		entradaErroresMediador 				= new LinkedList<Mensaje>();
	}
	
	public void recibirNotificacionLocalizacion(Mensaje pNotificacion){
		entradaNotificacionesLocalizacion.add(pNotificacion);
	}
	
	public void recibirConfirmacionesLocalizacion(Mensaje pNotificacion){
		entradaConfirmacionesLocalizacion.add(pNotificacion);
	}
	
	public void recibirRechazosLocalizacion(Mensaje pNotificacion){
		entradaRechazosLocalizacion.add(pNotificacion);
	}
	
	public void recibirSolicitudRefugio(Mensaje pSolicitud){
		entradaSolicitudesRefugio.add(pSolicitud);
	}
	
	public void recibirConfirmacionRefugio(Mensaje pNotificacion){
		entradaConfirmacionesRefugio.add(pNotificacion);
	}
	
	public void recibirRechazoRefugio(Mensaje pNotificacion){
		entradaRechazosRefugio.add(pNotificacion);
	}
	
	public void recibirSolicitudAdopcion(Mensaje pSolicitud){
		entradaSolicitudesAdopcion.add(pSolicitud);
	}
	
	public void recibirConfirmacionAdopcion(Mensaje pNotificacion){
		entradaConfirmacionesAdopcion.add(pNotificacion);
	}
	
	public void recibirRechazoAdopcion(Mensaje pNotificacion){
		entradaRechazosAdopcion.add(pNotificacion);
	}
	
	public void recibirMensajeError(Mensaje pError){
		entradaErroresMediador.add(pError);
	}
	
}