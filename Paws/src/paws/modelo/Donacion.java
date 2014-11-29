package paws.modelo;

import java.io.Serializable;

public class Donacion implements Serializable {
	
	private static final long serialVersionUID = 999L;
	
	private Double monto;
	private String nicknameDonante;
	private Suceso detalle;
	private Integer organizacionID;
	
	public Donacion(String pNick, Double pMonto, Suceso pDetalle, Integer pOrganizacionID) {
		nicknameDonante = pNick;
		monto = pMonto;
		detalle = pDetalle;
		organizacionID = pOrganizacionID;
	}
	public String getNicknameDonante() {
		return nicknameDonante;
	}
	public Double getMonto() {
		return monto;
	}
	public Suceso getDetalle() {
		return detalle;
	}
	public Integer getOrganizacionID() {
		return organizacionID;
	}
	
}
