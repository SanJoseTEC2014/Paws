package paws.control.excepciones;

import java.io.IOException;

@SuppressWarnings("serial")
public class ImagenNoEncontradaException extends IOException {
	public ImagenNoEncontradaException(String msg){
		super(msg);
	}
}
