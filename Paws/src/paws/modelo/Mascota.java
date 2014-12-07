package paws.modelo;

/**	Clase Mascota: 
 * 	Esta clase implementa cada uno de los atributos,
 *  listas y m�todos correspondientes a las mascotas.
 * 
 *	Fecha de creaci�n: 24/10/2014
 * 
 *	@author Isaac Antonio Campos Mes�n 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa Mar�a Molina Corrales 2013006074
 *	@author Luis Andr�s Pe�a Castillo 2014057250 
 *  
 */

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import paws.control.EstadosMascotas;
import paws.control.Imagenes;
import paws.control.excepciones.EventoNoExisteException;
import paws.control.excepciones.ImagenNoEncontradaException;
import paws.control.excepciones.TiempoSinEstablecerException;
import paws.modelo.Suceso;

public class Mascota implements Serializable {

	private static final long serialVersionUID = 111L;
	
	private static LinkedList<String> tamanios = new LinkedList<String>();
	private static LinkedList<String> colores = new LinkedList<String>();
	private static LinkedList<String> especies = new LinkedList<String>();
	private static LinkedList<LinkedList<String>> razas = new LinkedList<LinkedList<String>>();
	
	private static Integer totalIDsRegistradas = 0;

	private Integer id;
	private String nombre;
	private String numeroChip;
	private String especie;
	private String raza;
	private String color;
	private String edad;
	private String sexo;
	private boolean castrada;
	private boolean vacunada;
	private boolean desparacitada;
	private String tamanio;
	
	private Suceso[] sucesos = new Suceso[6]; // Para cada suceso del array corresponde un estado
	private boolean[] marcadoresEstado = new boolean[6];
	private boolean[] marcadoresEspera = new boolean[3]; //0 = Localizacion, 1 = Refugio, 2 = Adopcion
	private Integer recompensa;

	public Mascota(String pNombre, String pEspecie, String pRaza, Integer pRecompensa) {
		id = ++totalIDsRegistradas;
		nombre = pNombre;
		especie = pEspecie;
		raza = pRaza;
		recompensa = pRecompensa;
	}

	public Integer getID() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroChip() {
		return numeroChip;
	}

	public void setNumeroChip(String string) {
		this.numeroChip = string;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public boolean isCastrada() {
		return castrada;
	}

	public void setCastrada(boolean castrada) {
		this.castrada = castrada;
	}

	public boolean isVacunada() {
		return vacunada;
	}

	public void setVacunada(boolean vacunada) {
		this.vacunada = vacunada;
	}

	public boolean isDesparacitada(){
		return desparacitada;
	}

	public void setDesparacitada(boolean desparacitada) {
		this.desparacitada = desparacitada;
	}

	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public void addNuevoSuceso(Suceso pSuceso) {
		switch(pSuceso.getEstado())
		{
			case EstadosMascotas.estadoDESAPARECIDA:
			{
				sucesos[0] = pSuceso;
				marcadoresEstado[0] = true;
				marcadoresEstado[1] = false;
				marcadoresEstado[2] = false;
				marcadoresEstado[3] = false;
				marcadoresEstado[4] = false;
				marcadoresEstado[5] = false;
			}
			break;
			case EstadosMascotas.estadoENCONTRADA:
			{
				sucesos[1] = pSuceso;
				marcadoresEstado[0] = false;
				marcadoresEstado[1] = true;
				marcadoresEstado[2] = false;
				marcadoresEstado[3] = false;
				marcadoresEstado[4] = false;
				marcadoresEstado[5] = false;
			}
			break;
			case EstadosMascotas.estadoREFUGIADA:
			{
				sucesos[2] = pSuceso;
				marcadoresEstado[0] = false;
				marcadoresEstado[1] = false;
				marcadoresEstado[2] = true;
				marcadoresEstado[3] = false;
				marcadoresEstado[4] = false;
				marcadoresEstado[5] = false;
			}
			break;
			case EstadosMascotas.estadoLOCALIZADA:
			{
				sucesos[3] = pSuceso;
				marcadoresEstado[0] = false;
				marcadoresEstado[1] = false;
				marcadoresEstado[2] = false;
				marcadoresEstado[3] = true;
				marcadoresEstado[4] = false;
				marcadoresEstado[5] = false;
			}
			break;
			case EstadosMascotas.estadoADOPTADA:
			{
				sucesos[4] = pSuceso;
				marcadoresEstado[0] = false;
				marcadoresEstado[1] = false;
				marcadoresEstado[2] = false;
				marcadoresEstado[3] = false;
				marcadoresEstado[4] = true;
				marcadoresEstado[5] = false;
			}
			break;
			case EstadosMascotas.estadoMUERTA:
			{
				sucesos[5] = pSuceso;
				marcadoresEstado[0] = false;
				marcadoresEstado[1] = false;
				marcadoresEstado[2] = false;
				marcadoresEstado[3] = false;
				marcadoresEstado[4] = false;
				marcadoresEstado[5] = true;
			}
			break;
			
		}
	}
	
	public boolean isEsperandoLocalizacion(){
		return marcadoresEspera[0];
	}
	
	public boolean isEsperandoRefugio(){
		return marcadoresEspera[1];
	}
	
	public boolean isEsperandoAdopcion(){
		return marcadoresEspera[2];
	}
	
	public void setEsperaNinguna(){
		marcadoresEspera[0] = false;
		marcadoresEspera[1] = false;
		marcadoresEspera[2] = false;
	}
	
	public void setEsperaLocalizacion(){
		marcadoresEspera[0] = true;
		marcadoresEspera[1] = false;
		marcadoresEspera[2] = false;
	}
	
	public void setEsperaRefugio(){
		marcadoresEspera[0] = true;
		marcadoresEspera[1] = false;
		marcadoresEspera[2] = false;
	}
	
	public void setEsperaAdopcion(){
		marcadoresEspera[0] = true;
		marcadoresEspera[1] = false;
		marcadoresEspera[2] = false;
	}
	
	public void notificar(String pMensaje, String pNickEmisor) throws EventoNoExisteException, TiempoSinEstablecerException {
		Suceso temp = EstadosMascotas.reportarEvento(this, pNickEmisor, pMensaje);
		if (!temp.getNick().equals("")) addNuevoSuceso(temp); // Si el suceso tiene que cambiar.
	}
	
	public Integer getRecompensa() {
		return recompensa;
	}

	public void setRecompensa(Integer recompensa) {
		this.recompensa = recompensa;
	}
	
	public Suceso[] getTodosSucesos() {
		return sucesos;
	}
	
	public boolean[] getMarcadoresEstado() {
		return marcadoresEstado;
	}

	public BufferedImage getImagen() throws ImagenNoEncontradaException{
		return Imagenes.getPerfilMascota(id);
	}
	
	public String toString() {
		String msg = "Nombre de la mascota: " + getNombre();
		for (int i = 0; i < 5; i++){
			if (marcadoresEstado[i]) msg += "\nEstado: " + sucesos[i].getEstado();
		}
		msg += "\nEspecie: " + getEspecie();
		msg += "\nRaza: " + getRaza();
		msg += "\n";
		return msg;
	}

	// Constructor invocado unicamente por el metodo .clone()
	private Mascota(Integer pID, String pNombre, String pEspecie, String pRaza, Integer pRecompensa) {
		id = pID;
		nombre = pNombre;
		especie = pEspecie;
		raza = pRaza;
		recompensa = pRecompensa;
	}
	
	public Mascota clone() {
		  Mascota clone = new Mascota(id, nombre, especie, raza, recompensa);
		  clone.setColor(color);
		  clone.setEdad(edad);
		  clone.setNumeroChip(numeroChip);
		  clone.setSexo(sexo);
		  clone.setTamanio(tamanio);		  
		  clone.setCastrada(castrada);
		  clone.setDesparacitada(desparacitada);
		  clone.setVacunada(vacunada);
		  return clone;
	}

	public static DefaultComboBoxModel<String> getModeloEspecies() {
		int size = especies.size();
		return new DefaultComboBoxModel<String>(especies.toArray(new String[size]));
	}
	
	// Recibe la especie seleccionada en el JComboBox para que e'ste pueda refrescarse
	// cuando se esta' editando la especie de una Mascota.
	public static DefaultComboBoxModel<String> getModeloRazas(String pEspecie) {
		//int size = razas.get(especies.indexOf(pEspecie)).size();
		//return new DefaultComboBoxModel<String>(razas.get(especies.indexOf(pEspecie)).toArray(new String[size]));
	}
	
	public static DefaultComboBoxModel<String> getModeloTamanios() {
		  return new DefaultComboBoxModel<String>(tamanios.toArray(new String[3]));
	}
	
	public static DefaultComboBoxModel<String> getModeloColores() {
		int size = colores.size();
		return new DefaultComboBoxModel<String>(colores.toArray(new String[size]));
	}
	
	public static void addTamanio(String pTamanio){
		tamanios.add(pTamanio);
	}
	
	public static void addEspecie(String pEspecie){
		especies.add(pEspecie);
		razas.add(new LinkedList<String>());
	}
	
	public static void addRaza(String pEspecie, String pRaza){
		razas.get(especies.indexOf(pEspecie)).add(pRaza);	
	}
	
	public static void addColor(String pColor){
		colores.add(pColor);
	}
	
	public static LinkedList<String> getEspecies() {
		return especies;
	}

	public static LinkedList<LinkedList<String>> getRazas() {
		return razas;
	}

	public static Integer getTotalChips() {
		return totalIDsRegistradas;
	}

	public static ComboBoxModel<String> getModeloSexos() {
		return new DefaultComboBoxModel<String>(new String[]{"Macho", "Hembra"});
	}
	
}
