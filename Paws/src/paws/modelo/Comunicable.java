package paws.modelo;

public interface Comunicable {
	public void enviarMensaje(String pTipoMensaje, Mascota pMascota, String pNickDestino);
	public void recibirMensaje(Mensaje pMensaje);
}
