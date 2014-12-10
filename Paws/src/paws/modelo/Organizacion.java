package paws.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import paws.control.Principal;

public class Organizacion implements Serializable {
	
	private static final long serialVersionUID = 888L;
	
	private static Integer id;
	private String nombre;
	private String direccion;
	private ArrayList<Donacion> donaciones;
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
	public static Integer getId() {
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
	
	public static ArrayList<Organizacion> getOrganizaciones(){
		ArrayList<Organizacion> asociaciones = new ArrayList<Organizacion>();
		for(Organizacion organizacion : Principal.organizaciones){
			asociaciones.add(organizacion);	
		}
		return asociaciones;
	}
	
	public Organizacion(String pNombre, String pDireccion, Integer pNumeroContacto) {
		nombre = pNombre;
		numeroContacto = pNumeroContacto;
		direccion = pDireccion;
	}
	
	public Organizacion(String pNombre, Integer pID, Integer pNumeroContacto, String pDireccion) {
		nombre = pNombre;
		id = pID;
		numeroContacto = pNumeroContacto;
		direccion = pDireccion;
	}
	
	/*public void recibirDonacion(Donacion pDonacion){
		Principal.donaciones.add(pDonacion);
	}^*/
	
	public void addDonacion(Donacion pDonacion) {
		this.donaciones.add(pDonacion);
	}
	
	public void montoTotalDonaciones(Integer pOrganizacionID){
		for(Donacion donacion : donaciones){
			if(donacion.getOrganizacionID() == pOrganizacionID){
				setMontoTotalDonaciones(donacion.getMonto());
			}
		}
	}
}