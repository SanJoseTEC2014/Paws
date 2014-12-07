package paws.modelo;

import java.io.Serializable;
import java.math.*;
import java.text.*;
import java.util.*;

import paws.control.Principal;
import paws.control.excepciones.UsuarioNoExisteException;

public class Usuario implements Serializable, Comunicable {

	private static final long serialVersionUID = 222L;
	
	public static final List<String> lapsos = Arrays.asList("Diario", "Semanal", "Mensual");
	private static Double calificacionMinimaPermitida;
	
	public static Double getCalificacionMinimaPermitida() {
		return calificacionMinimaPermitida;
	}

	public static void setCalificacionMinimaPermitidaUsuarios(Double pCalificacion) {
		calificacionMinimaPermitida = pCalificacion;
	}
	
	private String nickname;
	private String nombre;
	private String apellidos;
	private Integer cedula;
	private String contrasenia;
	private Integer telefono;
	private String correo;
	private String direccion;
	private String lapsoEmparejamiento;
	private Integer diasUltimoEmparejamiento;
	private ArrayList<Calificacion> calificaciones;
	private BandejaMensajes buzon;
	private Boolean refugiante;
	private Boolean bloqueado;
	private Boolean administrador;
	private CondicionesRefugio condicionesRef;
	private Donacion donacion;
	private Double ponderadoCalificacion;

	public Usuario(String pNickname, String pNombre, String pApellidos, Integer pCedula, String pContrasenia,
		Integer pTelefono, String pCorreo, String pDireccion) {

		condicionesRef = new CondicionesRefugio(false, false, false, false, " ");
		nickname = pNickname;
		nombre = pNombre;
		apellidos = pApellidos;
		cedula = pCedula;
		contrasenia = pContrasenia;
		telefono = pTelefono;
		correo = pCorreo;
		calificaciones = new ArrayList<Calificacion>();
		buzon = new BandejaMensajes();
		refugiante = false; // se instancian las personas como NO refugiantes
		administrador = false;
		bloqueado = false;
		ponderadoCalificacion = 5.0;
		lapsoEmparejamiento = Usuario.lapsos.get(2);
		diasUltimoEmparejamiento = 1;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getLapsoEmparejamiento() {
		return lapsoEmparejamiento;
	}

	public void setLapsoEmparejamiento(String lapsoEmparejamiento) {
		this.lapsoEmparejamiento = lapsoEmparejamiento;
	}

	public Integer getDiasUltimoEmparejamiento() {
		return diasUltimoEmparejamiento;
	}

	public void setDiasUltimoEmparejamiento(int diasUltimoEmparejamiento) {
		this.diasUltimoEmparejamiento = diasUltimoEmparejamiento;
	}

	public boolean isRefugiante() {
		return refugiante;
	}

	public void setRefugiante(boolean refugiante) {
		this.refugiante = refugiante;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public CondicionesRefugio getCondicionesRefugio() {
		return condicionesRef;
	}

	public void setCondicionesRefugio(CondicionesRefugio pCondiciones) {
		condicionesRef = pCondiciones;
	}
	

	public Donacion getDonacion(){
		return donacion;
	}
	
	public void setDonacion(Donacion pdonacion){
		donacion = pdonacion;
	}


	public void addDiasTranscurridos() {
		diasUltimoEmparejamiento++;
		//lapsos.get(1) == Semanal
		if (lapsoEmparejamiento.equals(lapsos.get(1))) {
			if (diasUltimoEmparejamiento == 8) {
				diasUltimoEmparejamiento = 1;
			}
		}
		//lapsos.get(2) == Mensual
		if (lapsoEmparejamiento.equals(lapsos.get(2))) {
			if (diasUltimoEmparejamiento == 31) {
				diasUltimoEmparejamiento = 1;
			}
		}

	}

	public ArrayList<Mascota> getMascotasPerdidas() {
		ArrayList<Mascota> perdidas = new ArrayList<Mascota>();
		for (Mascota mascota : Principal.mascotas){
			if (mascota.getMarcadoresEstado()[0]) {
				if (mascota.getTodosSucesos()[0].getNick().equals(nickname))
				perdidas.add(mascota);
			}
		}
		return perdidas;
	}
	
	public ArrayList<Mascota> getMascotasEncontradas() {
		ArrayList<Mascota> encontradas = new ArrayList<Mascota>();
		for (Mascota mascota : Principal.mascotas){
			if (mascota.getMarcadoresEstado()[1]) {
				if (mascota.getTodosSucesos()[1].getNick().equals(nickname))
				encontradas.add(mascota);
			}
		}
		return encontradas;
	}
	
	public ArrayList<Mascota> getMascotasRefugiadas() {
		ArrayList<Mascota> refugiadas = new ArrayList<Mascota>();
		for (Mascota mascota : Principal.mascotas){
			if (mascota.getMarcadoresEstado()[2]) {
				if (mascota.getTodosSucesos()[2].getNick().equals(nickname))
				refugiadas.add(mascota);
			}
		}
		return refugiadas;
	}
	
	public ArrayList<Mascota> getMascotasLocalizadas() {
		ArrayList<Mascota> localizadas = new ArrayList<Mascota>();
		for (Mascota mascota : Principal.mascotas){
			if (mascota.getMarcadoresEstado()[3]) {
				if (mascota.getTodosSucesos()[3].getNick().equals(nickname))
				localizadas.add(mascota);
			}
		}
		return localizadas;
	}
	
	public ArrayList<Mascota> getMascotasAdoptadas() {
		ArrayList<Mascota> adoptadas = new ArrayList<Mascota>();
		for (Mascota mascota : Principal.mascotas){
			if (mascota.getMarcadoresEstado()[4]) {
				if (mascota.getTodosSucesos()[4].getNick().equals(nickname))
				adoptadas.add(mascota);
			}
		}
		return adoptadas;
	}
	
	public ArrayList<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	
	public BandejaMensajes getBuzon(){
		return buzon;
	}

	public void addCalificacion(Calificacion pCalificacion) {
		//System.out.println(pCalificacion.getEstrellas());
		actualizarPonderado(pCalificacion.getEstrellas());
		//System.out.println(ponderadoCalificacion);
		this.calificaciones.add(pCalificacion);
		//System.out.println(ponderadoCalificacion);
	}
	
	public double getPonderadoCalificacion(){
		DecimalFormat redondeo = new DecimalFormat("#.#");
		redondeo.setRoundingMode(RoundingMode.HALF_UP);
		return Double.parseDouble(redondeo.format(ponderadoCalificacion));
		//return ponderadoCalificacion;
	}
	
	private void actualizarPonderado(Integer ultimaCalificacion){
		ponderadoCalificacion = (ponderadoCalificacion * calificaciones.size() + ultimaCalificacion)
								/ (calificaciones.size() + 1); // +1 de la calificaci�n que est� agregando
		bloqueado = (ponderadoCalificacion < calificacionMinimaPermitida);
	}
	
	public String toString(){
		return "Nombre: " + nombre + "\nNickname: " + nickname +
			   "\nContrase�a: " + contrasenia + "\nAdmin?: " + (administrador ? "S�" : "No");
	}
	
	public boolean isBloqueado() {
		return bloqueado;
	}
	
	@Override
	public void enviarMensaje(String pTipoMensaje, Mascota pMascota, String pNickDestino) throws UsuarioNoExisteException {
		pMascota.notificar(pTipoMensaje, this.nickname);		
		Principal.getUsuario(pNickDestino).recibirMensaje(new Mensaje(pTipoMensaje, this.nickname, pMascota));
	}
	
	@Override
	public void recibirMensaje(Mensaje pMensaje) {
		switch (pMensaje.getDetalle()){
			// Mensajes de mayor prioridad
			case Mensaje.posible_LOCALIZACION:
			{
				buzon.recibirNotificacionLocalizacion(pMensaje);
			}
			break;
			case Mensaje.confirmacion_LOCALIZACION:
			{
				buzon.recibirConfirmacionesLocalizacion(pMensaje);
			}
			break;
			case Mensaje.rechazo_LOCALIZACION:
			{
				buzon.recibirRechazosLocalizacion(pMensaje);
			}
			break;
			// Mensajes de menor prioridad
			case Mensaje.solicitud_REFUGIO:
			{
				buzon.recibirSolicitudRefugio(pMensaje);
			}
			break;
			case Mensaje.confirmacion_REFUGIO:
			{
				buzon.recibirConfirmacionRefugio(pMensaje);
			}
			break;
			case Mensaje.rechazo_REFUGIO:
			{
				buzon.recibirRechazoRefugio(pMensaje);
			}
			break;
			case Mensaje.solicitud_ADOPCION:
			{
				buzon.recibirSolicitudAdopcion(pMensaje);
			}
			break;
			case Mensaje.confirmacion_ADOPCION:
			{
				buzon.recibirConfirmacionAdopcion(pMensaje);
			}
			break;
			case Mensaje.rechazo_ADOPCION:
			{
				buzon.recibirRechazoAdopcion(pMensaje);
			}
			break;
		}
	}
	
	public String[][] getArrayCalificaciones(){
		String[][] ArregloCalificaciones = new String[calificaciones.size()][3];
		for (int i = 0; i < calificaciones.size(); i++){
			ArregloCalificaciones[i][0] = calificaciones.get(i).getNicknameCalificante();
			ArregloCalificaciones[i][1] = Integer.toString(calificaciones.get(i).getEstrellas());
			ArregloCalificaciones[i][2] = calificaciones.get(i).getMensaje();
		}
		return ArregloCalificaciones;
	}
	
	public Usuario clone() {
		  Usuario clone = new Usuario(nickname, nombre, apellidos, cedula, "", telefono, correo, direccion);
		  return clone;
	}
	
}
