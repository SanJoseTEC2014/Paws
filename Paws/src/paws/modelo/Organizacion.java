package paws.modelo;

import java.io.Serializable;
import java.util.ArrayList;

import paws.control.Principal;

public class Organizacion implements Serializable {
	
	private static final long serialVersionUID = 888L;
	private static Integer totalIDsRegistradas = 0;
    
	private Integer id; // Los IDs son propios de cada organización
	private String nombre;
	private String direccion;
	private Integer numeroContacto;
    private ArrayList<Donacion> donaciones;
	
    public Organizacion(String pNombre, String pDireccion, Integer pNumeroContacto) {
        id = ++totalIDsRegistradas; // El total no es un parámetro, se copia de la variable static.
        nombre = pNombre;
		numeroContacto = pNumeroContacto;
		direccion = pDireccion;
        donaciones = new ArrayList<Donacion>();
	}
	
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
	public Integer getID() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	
	public ArrayList<Donacion> getDonaciones(){
		return donaciones;
	}
	
	public void addDonacion(Donacion pDonacion) {
		donaciones.add(pDonacion);
	}
	
	public Double getMontoTotalDonaciones(Integer pOrganizacionID){
		Double ponderado = 0.0;
        for(Donacion donacion : donaciones){
			ponderado += donacion.getMonto();
		}
        return ponderado;
	}
	
	public static Integer getTotalOrganizaciones(){
		return totalIDsRegistradas;
	}
}