package paws.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import paws.control.Principal;

public class Organizacion implements Serializable {
	
	private static final long serialVersionUID = 888L;
	
	private Integer id;
	private String nombre;
	private String direccion;
	private Integer numeroContacto;
	private Double montoTotalDonaciones = 0.0;
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getNumeroContacto() {
		return numeroContacto;
	}
	public void setNumeroContacto(Integer numeroContacto) {
		this.numeroContacto = numeroContacto;
	}
	public Integer getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	
	public Double getMontoTotalDonaciones(){
		return montoTotalDonaciones;
	}
	
	public void setMontoTotalDonaciones(Double pMonto){
		montoTotalDonaciones += pMonto;
	}
	
	public ArrayList<Organizacion> getOrganizaciones(){
		ArrayList<Organizacion> asociaciones = new ArrayList<Organizacion>();
		for(Organizacion organizacion : Principal.organizaciones){
			asociaciones.add(organizacion);	
		}
		return asociaciones;
	}
	public Organizacion(String pNombre, Integer pID, Integer pNumeroContacto, String pDireccion) {
		nombre = pNombre;
		id = pID;
		numeroContacto = pNumeroContacto;
		direccion = pDireccion;
	}
	
	public void recibirDonacion(Donacion pDonacion){
		Principal.donaciones.add(pDonacion);
	}
	
	public void montoTotalDonaciones(Integer pOrganizacionID){
		for(Donacion donacion : Principal.donaciones){
			if(donacion.getOrganizacionID() == pOrganizacionID){
				setMontoTotalDonaciones(donacion.getMonto());
			}
		}
	}
}