package paws.control;

/**	Clase Principal: 
 * 	 Esta clase permite inicializar las mascotas, así como los usuarios,
 *   cargar los casos de prueba para el sistema e invocar la ventana de
 *   inicio de sesión de la aplicación, asimismo implementa algunos  
 *   métodos para el funcionamiento de la aplicación.
 * 
 *	Fecha de creación: 28/10/2014
 * 
 *	@author Isaac Antonio Campos Mesén 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa María Molina Corrales 2013006074
 *	@author Luis Andrés Peña Castillo 2014057250 
 *  
 */
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.swing.JOptionPane;

import paws.control.excepciones.*;
import paws.modelo.*;
import paws.recursos.CasosPrueba;
import paws.recursos.Diseno;
import paws.recursos.RutasArchivo;

public class Principal {
	public static ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
	public static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	public static ArrayList<Organizacion> organizaciones = new ArrayList<Organizacion>();
	public static ArrayList<Donacion> donaciones = new ArrayList<Donacion>();
	
	public static CoordinadorVisual coordinador;
	
	public static Usuario getUsuario(String pNickname) throws UsuarioNoExisteException{
		for (Usuario x : usuarios) {
			if (x.getNickname().equals(pNickname)) return x;
		}
		throw new UsuarioNoExisteException("El usuario no aparece registrado.");
	}
	
	public static Mascota getMascotaID(Integer IDmascota) throws MascotaNoEncontradaException {
		// TODO búsqueda binaria
		for (Mascota mascota : mascotas) if (IDmascota == mascota.getID()) return mascota;
		throw new MascotaNoEncontradaException("Error inesperado no se encuentra la mascota");
	}
	public static Organizacion getOrganizacionID(Integer IDOrganizacion) throws OrganizacionNoEncontradaException {
		// TODO búsqueda binaria
		for (Organizacion organizacion : organizaciones) if (IDOrganizacion == organizacion.getId()) return organizacion;
		throw new OrganizacionNoEncontradaException("Error inesperado no se encuentra la organización");
	}
	
	
	public static void cargarBaseDatosMascotas() {
		try(BufferedReader lector =
			new BufferedReader(
				new InputStreamReader(
					new FileInputStream(
						RutasArchivo.recursosRoot + "pets.pdb"), "UTF-8")))
		{		
			String lineaActual;
			while ((lineaActual = lector.readLine()) != null ) {
				String pEspecie = lineaActual.substring(0, lineaActual.indexOf(":"));
				LinkedList<String> pRazas = new LinkedList<String>();
				for (String token : lineaActual.substring(lineaActual.indexOf(":") + 1, lineaActual.length()).split(";")) {
					pRazas.add(token);
				}
				Mascota.addEspecie(pEspecie);
				for (String pRaza : pRazas) {
					Mascota.addRaza(pEspecie, pRaza);
				}
			}
			// JOptionPane.showMessageDialog(null,	"Base de datos de especies y razas de Mascotas cargada satisfactoriamente.");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null,
					"Hubo un error que no permite que el programa pueda arrancar.",
					"ERROR CRÍTICO DEL SISTEMA PAWS", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		RutasArchivo.inicializar();
		Diseno.inicializarLookAndFeel();
		Diseno.inicializarFuentes();
		cargarBaseDatosMascotas();
		Usuario.setCalificacionMinimaPermitidaUsuarios(3.0);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() { 
				coordinador = new CoordinadorVisual();
				coordinador.mostrarInicioSesion();
			}
		});
	}
}
