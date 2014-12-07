package paws.control;

import paws.control.excepciones.EventoNoExisteException;
import paws.control.excepciones.TiempoSinEstablecerException;
import paws.modelo.Mascota;
import paws.modelo.Mensaje;
import paws.modelo.Suceso;

public class EstadosMascotas {
	// Constantes que representan los estados
	public static final String estadoDESAPARECIDA = "Desaparecida";
	public static final String estadoENCONTRADA = "Encontrada";
	public static final String estadoREFUGIADA = "Refugiada";
	public static final String estadoLOCALIZADA = "Localizada";
	public static final String estadoADOPTADA = "Adoptada";
	public static final String estadoMUERTA = "Muerta";
	
	private static String getEstadoActual(boolean[] pSucesos){
		if (pSucesos[0]) return estadoDESAPARECIDA;
		if (pSucesos[1]) return estadoENCONTRADA;
		if (pSucesos[2]) return estadoREFUGIADA;
		if (pSucesos[3]) return estadoLOCALIZADA;
		if (pSucesos[4]) return estadoADOPTADA;
		if (pSucesos[5]) return estadoMUERTA;
		return "INDEFINIDO";
	}
	
	public static Suceso reportarEvento(Mascota pMascotaActual, String pNickEmisor, String pMensaje) throws EventoNoExisteException, TiempoSinEstablecerException {
		String estadoActual = getEstadoActual(pMascotaActual.getMarcadoresEstado());
		switch (pMensaje)
		{
																										// Eventos de Localizacion
			case Mensaje.posible_LOCALIZACION:
				if (estadoActual.equals(estadoDESAPARECIDA) || estadoActual.equals(estadoENCONTRADA)) {
					pMascotaActual.setEsperaLocalizacion();
					return new Suceso();
				}
			break;
			case Mensaje.confirmacion_LOCALIZACION:
				if (pMascotaActual.isEsperandoLocalizacion()) {
					pMascotaActual.setEsperaNinguna();
					return new Suceso(pNickEmisor, estadoLOCALIZADA, "", "");
				}
			break;
			case Mensaje.rechazo_LOCALIZACION:
				if (pMascotaActual.isEsperandoLocalizacion()) {
					pMascotaActual.setEsperaNinguna();
					return new Suceso();
				}
			break;
																										// Eventos de Refugio
			case Mensaje.solicitud_REFUGIO:
				if (estadoActual.equals(estadoENCONTRADA)) {
					pMascotaActual.setEsperaRefugio();
					return new Suceso();
				}
			break;
			case Mensaje.confirmacion_REFUGIO:
				if (pMascotaActual.isEsperandoRefugio()) {
					pMascotaActual.setEsperaNinguna();
					return new Suceso(pNickEmisor, estadoREFUGIADA, "", "");
				}
			break;
			case Mensaje.rechazo_REFUGIO:
				if (pMascotaActual.isEsperandoRefugio()) {
					pMascotaActual.setEsperaNinguna();
					return new Suceso();
				}
			break;
																										// Eventos de Adopcion
			case Mensaje.solicitud_ADOPCION:
				if (estadoActual.equals(estadoENCONTRADA) || estadoActual.equals(estadoREFUGIADA)) {
					pMascotaActual.setEsperaAdopcion();
					return new Suceso();
				}
			break;
			case Mensaje.confirmacion_ADOPCION:
				if (pMascotaActual.isEsperandoAdopcion()) {
					pMascotaActual.setEsperaNinguna();
					return new Suceso(pNickEmisor, estadoADOPTADA, "", "");
				}
			break;
			case Mensaje.rechazo_ADOPCION:
				if (pMascotaActual.isEsperandoAdopcion()) {
					pMascotaActual.setEsperaNinguna();
					return new Suceso();
				}
			break;
			default:
				throw new EventoNoExisteException("Contacte al equipo de desarrollo.\nCaso no contemplado en el dise�o.");
		}
		return null;
	}

}
	


