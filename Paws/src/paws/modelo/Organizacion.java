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
	private String correo;
	private String paginaWeb;
	private Double totalDonaciones = 0.0;
    private ArrayList<Donacion> donaciones;
	
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
	
	public String getCorreo(){
		return correo;
	}
	
	public void setCorreo(String correo){
		this.correo = correo;
	}
	
	public String getPaginaWeb(){
		return paginaWeb;
	}
	
	public void setPaginaWeb(String pagina){
		this.paginaWeb = pagina;
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
	
	public Organizacion(String pNombre, String pDireccion,String pCorreo, Integer pNumeroContacto, String pPaginaWeb) {
        id = ++totalIDsRegistradas; // El total no es un parámetro, se copia de la variable static.
        nombre = pNombre;
		numeroContacto = pNumeroContacto;
		direccion = pDireccion;
		correo = pCorreo;
		paginaWeb = pPaginaWeb;
        donaciones = new ArrayList<Donacion>();
        
	}
	
	public void addDonacion(Donacion pDonacion) {
		donaciones.add(pDonacion);
	}
	
	public void setMontoTotalDonaciones(Double monto){
		this.totalDonaciones += monto;
	}
	
	public Double getMontoTotalDonaciones(){
		return totalDonaciones;
	}
	
	public static Integer getTotalOrganizaciones(){
		return totalIDsRegistradas;
	}
}