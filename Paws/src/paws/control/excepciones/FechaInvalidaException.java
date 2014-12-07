package paws.control.excepciones;

@SuppressWarnings("serial")
public class FechaInvalidaException extends Exception {
	public FechaInvalidaException(String msg){
		super(msg);
	}
	public FechaInvalidaException(String msg, Throwable cause){
		super(msg, cause);
	}
}
