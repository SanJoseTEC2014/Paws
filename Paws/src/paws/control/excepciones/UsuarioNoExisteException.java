package paws.control.excepciones;

@SuppressWarnings("serial")
public class UsuarioNoExisteException extends Exception {
	public UsuarioNoExisteException(String pString) {
		super(pString);
	}
}
