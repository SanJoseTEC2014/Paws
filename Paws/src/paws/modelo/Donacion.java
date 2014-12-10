package paws.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import paws.control.Principal;

public class Donacion implements Serializable {
	
	private static final long serialVersionUID = 999L;
	
	private Double monto;
	private String nicknameDonante;
	
	public Donacion(String pNick, Double pMonto) {
		nicknameDonante = pNick;
		monto = pMonto;
	}
    
	public String getNicknameDonante() {
		return nicknameDonante;
	}
    
	public Double getMonto() {
		return monto;
	}
	
}