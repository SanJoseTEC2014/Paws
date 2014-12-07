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

import javax.swing.JFrame;

import paws.modelo.Mascota;
import paws.modelo.Usuario;
import paws.vista.*;

public class CoordinadorVisual {
	private VentanaParametrosSistema parametrosSistema;
	private VentanaMenuPrincipal menuPrincipal;
	private VentanaDetallesUsuario detallesUsuario;
	private VentanaDetallesMascota detallesMascota;
	private VentanaInicioSesion inicioSesion;
	private VentanaCondicionesRefugio condicionesRefugio;
	private VentanaMascotasDeUsuario mascotasAsociadas;

	public CoordinadorVisual(){
		inicioSesion = new VentanaInicioSesion();
		menuPrincipal = new VentanaMenuPrincipal();
		parametrosSistema = new VentanaParametrosSistema();
		detallesUsuario = new VentanaDetallesUsuario();
		detallesMascota = new VentanaDetallesMascota();
		condicionesRefugio = new VentanaCondicionesRefugio();
		mascotasAsociadas = new VentanaMascotasDeUsuario();
	}
	
	public synchronized void mostrarInicioSesion() {
		inicioSesion.setVisible(true);
		inicioSesion.cargarLogo();
	}

	public synchronized void mostrarParametrosSistema() {
		parametrosSistema.setVisible(Acceso.isAdministradorActivo());
		parametrosSistema.setVisible(true);
	}

	public synchronized void mostrarMenuPrincipal() {
		menuPrincipal.setVisible(true);
		menuPrincipal.setUsuario();
	}
	
	public synchronized void ocultarMenuPrincipal() {
		menuPrincipal.setVisible(false);
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
	
	public synchronized void mostrarAgregarComentario(Usuario usuarioACalificar){
		VentanaAgregarComentario agregarComentario = new VentanaAgregarComentario();
		agregarComentario.setUsuarioACalificar(usuarioACalificar);
		agregarComentario.setVisible(true);		
	}
	
	public synchronized void ocultarInicioSesion(){
		inicioSesion.setVisible(false);
		inicioSesion.limpiarCampos();
	}
	
	public synchronized void ocultarVentanas() {
		parametrosSistema.setVisible(false);
		menuPrincipal.setVisible(false);
		detallesUsuario.setVisible(false);
		detallesMascota.setVisible(false);
	}
	
	public synchronized void mostrarVentanas() {
		parametrosSistema.setVisible(true);
		menuPrincipal.setVisible(true);
		detallesUsuario.setVisible(true);
		detallesMascota.setVisible(true);
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
}
