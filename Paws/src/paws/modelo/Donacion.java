package paws.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import paws.control.Principal;

public class Donacion implements Serializable {
	
	private static final long serialVersionUID = 999L;
	
	private Double monto;
	private String nicknameDonante;
	//private Suceso detalle;
	private Integer organizacionID;
	
	public Donacion(String pNick, Double pMonto, Integer pOrganizacionID) {
		nicknameDonante = pNick;
		monto = pMonto;
		//detalle = pDetalle;
		organizacionID = pOrganizacionID;
	}
	public String getNicknameDonante() {
		return nicknameDonante;
	}
	public Double getMonto() {
		return monto;
	}
	/*public Suceso getDetalle() {
		return detalle;
	}*/
	public Integer getOrganizacionID() {
		return organizacionID;
	}
	
	public ArrayList<Donacion> getDonaciones(){
		ArrayList<Donacion> listaDonaciones = new ArrayList<Donacion>();
		for(Donacion donacion : Principal.donaciones){
			listaDonaciones.add(donacion);	
		}
		return listaDonaciones;
	
    }
}