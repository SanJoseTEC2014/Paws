package paws.modelo;

import java.io.Serializable;

public class Organizacion implements Serializable {
	
	private static final long serialVersionUID = 888L;
	
	private Integer id;
	private String nombre;
	private String direccion;
	private Integer numeroContacto;
	
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
	
	public Organizacion(String pNombre, Integer pID, Integer pNumeroContacto, String pDireccion) {
		nombre = pNombre;
		id = pID;
		numeroContacto = pNumeroContacto;
		direccion = pDireccion;
	}

}
