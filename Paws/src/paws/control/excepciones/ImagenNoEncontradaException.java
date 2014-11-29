package paws.control.excepciones;

import java.io.IOException;

public class ImagenNoEncontradaException extends IOException {
	public ImagenNoEncontradaException(String msg){
		super(msg);
	}
}
