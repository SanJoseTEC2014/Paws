package paws.control.excepciones;

@SuppressWarnings("serial")
public class ContraseniaIncorrectaException extends Exception {
	public ContraseniaIncorrectaException(String pString) {
		super(pString);
	}
}
