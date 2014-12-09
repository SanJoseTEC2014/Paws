package paws.control;

/**	 Controlador Coordinador Visual: 
 * 	 Esta clase implementa diversos m�todos que permiten
 * 	 mostrar y ocultar cada una de las ventanas de la aplicaci�n.
 * 
 *	Fecha de creaci�n: 7/11/2014
 * 
 *	@author Isaac Antonio Campos Mes�n 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa Mar�a Molina Corrales 2013006074
 *	@author Luis Andr�s Pe�a Castillo 2014057250 
 *  
 */

import paws.modelo.Mascota;
import paws.modelo.Usuario;
import paws.modelo.Organizacion;
import paws.vista.*;

public class CoordinadorVisual {
	private VentanaDetallesUsuario detallesUsuario;
	private VentanaDetallesMascota detallesMascota;
	private VentanaCondicionesRefugio condicionesRefugio;
	private VentanaMascotasDeUsuario mascotasAsociadas;
	private VentanaListaAsociaciones asociaciones;
	private VentanaDonaciones donaciones;
	private VentanaDetallesAsociacion detallesAsociacion;

	public CoordinadorVisual(){
		detallesUsuario = new VentanaDetallesUsuario();
		detallesMascota = new VentanaDetallesMascota();
		condicionesRefugio = new VentanaCondicionesRefugio();
		mascotasAsociadas = new VentanaMascotasDeUsuario();
	}
	
	public synchronized void mostrarInicioSesion() {
		VentanaInicioSesion inicioSesion = new VentanaInicioSesion();
		inicioSesion.setVisible(true);
		inicioSesion.cargarLogo();
	}

	public synchronized void mostrarParametrosSistema() {
		VentanaParametrosSistema parametrosSistema = new VentanaParametrosSistema();
		parametrosSistema.setVisible(true);
	}

	public synchronized void mostrarMenuPrincipal() {
		VentanaMenuPrincipal menuPrincipal = new VentanaMenuPrincipal();
		menuPrincipal.setVisible(true);
	}

	public synchronized void mostrarDetallesUsuario(Usuario usuarioActual) {
		detallesUsuario.setDatosIniciales(usuarioActual);
		detallesUsuario.setModoEdicion(usuarioActual == Acceso.getUsuarioActivo());
		detallesUsuario.setVisible(true);
	}

	public synchronized void mostrarRegistroUsuarios() {
		VentanaRegistroUsuarios registroUsuarios = new VentanaRegistroUsuarios();
		registroUsuarios.setVisible(true);
	}

	public synchronized void mostrarRegistroMascotas() {
		VentanaRegistroMascotas registroMascotas = new VentanaRegistroMascotas();
		registroMascotas.setVisible(true);
	}

	public synchronized void mostrarBusqueda() {
		VentanaBusqueda busqueda = new VentanaBusqueda();
		busqueda.setVisible(true);
	}
	
	public synchronized void mostrarListaAsociaciones() {
		VentanaListaAsociaciones organizaciones = new VentanaListaAsociaciones();
		organizaciones.setVisible(true);
	}
	
	public synchronized void mostrarRealizarDonacion() {
		VentanaDonaciones donacionesAsociaciones = new VentanaDonaciones();
		donacionesAsociaciones.setVisible(true);
	}
	
	public synchronized void mostrarAgregarComentario(Usuario usuarioACalificar){
		VentanaAgregarComentario agregarComentario = new VentanaAgregarComentario();
		agregarComentario.setUsuarioACalificar(usuarioACalificar);
		agregarComentario.setVisible(true);		
	}
	
	public synchronized void ocultarVentanas() {
		detallesUsuario.setVisible(false);
		detallesMascota.setVisible(false);
		detallesAsociacion.setVisible(false);
		asociaciones.setVisible(false);
		donaciones.setVisible(false);
	}
	
	public synchronized void mostrarVentanas() {
		detallesUsuario.setVisible(true);
		detallesMascota.setVisible(true);
		detallesAsociacion.setVisible(true);
		asociaciones.setVisible(true);
		donaciones.setVisible(true);
	}
	

	public void mostrarCondicionesRefugio(Usuario usuarioActual) {
		condicionesRefugio.setDatos(usuarioActual);
		condicionesRefugio.setVisible(true);
		condicionesRefugio.setModoEdicion(usuarioActual == Acceso.getUsuarioActivo());
	}
	
	public void mostrarMascotasAsociadas(Usuario usuarioActual){
		mascotasAsociadas.setUsuario(usuarioActual);
		mascotasAsociadas.setVisible(true);
	}

	public void mostrarCalificaciones(Usuario pUsuario) {
		VentanaCalificaciones cals = new VentanaCalificaciones();
		cals.setUsuario(pUsuario);
		cals.setVisible(true);
	}

	public void mostrarDetallesMascota(Mascota mascota) {
		detallesMascota.setDatosIniciales(mascota);
		detallesMascota.setVisible(true);
	}
	
	public void mostrarDetallesAsociaciones(Organizacion organizacion) {
		detallesAsociacion.setDatosIniciales(organizacion);
		detallesAsociacion.setVisible(true);
	}
	
}
