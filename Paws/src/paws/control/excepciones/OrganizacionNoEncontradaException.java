package paws.control.excepciones;

@SuppressWarnings("serial")
public class OrganizacionNoEncontradaException extends Exception {
	public OrganizacionNoEncontradaException(String string){
		super (string);
	}
}