package paws.modelo;

import paws.control.excepciones.UsuarioNoExisteException;

public interface Comunicable {
	public void enviarMensaje(String pTipoMensaje, Mascota pMascota, String pNickDestino) throws UsuarioNoExisteException;
	public void recibirMensaje(Mensaje pMensaje);
}
