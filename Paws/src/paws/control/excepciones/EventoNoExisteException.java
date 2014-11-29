package paws.control.excepciones;

public class EventoNoExisteException extends IllegalArgumentException {
	public EventoNoExisteException(String pString) {
		super(pString);
	}
}