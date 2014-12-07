package paws.control.excepciones;

@SuppressWarnings("serial")
public class EventoNoExisteException extends IllegalArgumentException {
	public EventoNoExisteException(String pString) {
		super(pString);
	}
}