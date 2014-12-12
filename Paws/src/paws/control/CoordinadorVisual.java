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

import java.util.ArrayList;

import paws.modelo.Mascota;
import paws.modelo.Usuario;
import paws.modelo.Organizacion;
import paws.vista.*;

public class CoordinadorVisual {
	
	public CoordinadorVisual(){}
	
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
		VentanaDetallesUsuario detallesUsuario = new VentanaDetallesUsuario();
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
		VentanaListaOrganizaciones organizaciones = new VentanaListaOrganizaciones();
		organizaciones.setVisible(true);
	}
	
	public synchronized void mostrarAgregarComentario(Usuario usuarioACalificar){
		VentanaAgregarComentario agregarComentario = new VentanaAgregarComentario();
		agregarComentario.setUsuarioACalificar(usuarioACalificar);
		agregarComentario.setVisible(true);		
	}

	public void mostrarCondicionesRefugio(Usuario usuarioActual) {
		VentanaCondicionesRefugio condicionesRefugio = new VentanaCondicionesRefugio();
		condicionesRefugio.setDatos(usuarioActual);
		condicionesRefugio.setModoEdicion(usuarioActual == Acceso.getUsuarioActivo());
		condicionesRefugio.setVisible(true);
	}
	
	public void mostrarMascotasAsociadas(Usuario usuarioActual){
		VentanaMascotasDeUsuario mascotasAsociadas = new VentanaMascotasDeUsuario();
		mascotasAsociadas.setUsuario(usuarioActual);
		mascotasAsociadas.setVisible(true);
	}
	
	public void mostrarMascotasSistema(ArrayList<Mascota> pLista){
		VentanaMascotasRegistradas registradas = new VentanaMascotasRegistradas();
		registradas.setDatosIniciales(pLista, false);
		registradas.setVisible(true);
	}
	
	public void mostrarResultadosEmparejamiento(ArrayList<Mascota> pLista){
		VentanaMascotasRegistradas registradas = new VentanaMascotasRegistradas();
		registradas.setDatosIniciales(pLista, true);
		registradas.setVisible(true);
	}

	public void mostrarCalificaciones(Usuario pUsuario) {
		VentanaCalificaciones cals = new VentanaCalificaciones();
		cals.setUsuario(pUsuario);
		cals.setVisible(true);
	}
	
	public void mostrarDonaciones(Organizacion pOrganizacion) {
		VentanaDonaciones donacionesAOrganizaciones = new VentanaDonaciones();
		donacionesAOrganizaciones.setOrganizacion(pOrganizacion);
		donacionesAOrganizaciones.setVisible(true);
	}

	public void mostrarDetallesMascota(Mascota mascota) {
		VentanaDetallesMascota detallesMascota = new VentanaDetallesMascota();
		detallesMascota.setDatosIniciales(mascota);
		detallesMascota.setVisible(true);
	}
	
	public void mostrarDetallesAsociaciones(Organizacion organizacionActual) {
		//VentanaDetallesAsociacion detallesAsociacion = new VentanaDetallesAsociacion();
		VentanaDetallesOrganizacion detallesAsociacion = new VentanaDetallesOrganizacion();
		detallesAsociacion.setDatosIniciales(organizacionActual);
		detallesAsociacion.setVisible(true);
	}
	
	
	public void mostrarOrganizaciones(Organizacion organizacion) {
		VentanaListaOrganizaciones organizaciones = new VentanaListaOrganizaciones();
		organizaciones.setVisible(true);
	}
	
	public void mostrarRegistroOrganizacion(Organizacion organizacion) {
		VentanaRegistroOrganizaciones organizaciones = new VentanaRegistroOrganizaciones();
		organizaciones.setVisible(true);
	}
	
}
