package paws.control;

/**	 Controlador Acceso: 
 * 	 Esta clase implementa diversos m�todos que permiten validar
 *   el acceso del usuario al sistema.
 * 
 *	 Fecha de creaci�n: 28/10/2014
 * 
 *	@author Isaac Antonio Campos Mes�n 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa Mar�a Molina Corrales 2013006074
 *	@author Luis Andr�s Pe�a Castillo 2014057250 
 *  
 */

import paws.control.excepciones.*;
import paws.modelo.Usuario;

public class Acceso {
	private static Usuario usuarioActivo;
	
	private static Usuario superUsuario;
	
	private static boolean modoAdministrador;
	
	// Aplicaci�n de Singleton para el superUsuario
	private static void inicializarSuperUsuario(){
		superUsuario = new Usuario("pitbull", "Superusuario", "Paws", 111111111,
				"terrier", 25505033, "paws_TEC@gmail.com", "Barrio Am�n");
		superUsuario.setLapsoEmparejamiento(Usuario.lapsos.get(2));
		superUsuario.setAdministrador(true);
	}
	
	private static Usuario getInstanciaSuperUsuario(){
		if (superUsuario == null){
			inicializarSuperUsuario();
		}
		return superUsuario;
	}
	
	public static void validarCredenciales(String pNickname, String pContrasenia) throws Exception{
		if (isSuperUsuario(pNickname, pContrasenia)){
			usuarioActivo = getInstanciaSuperUsuario();
			setModoAdministrador(true);
		} else {
			Usuario usuarioPorAcceder = validarUsuarioRegistrado(pNickname);
			if (usuarioPorAcceder.getContrasenia().equals(pContrasenia)){
				usuarioActivo = usuarioPorAcceder;
				setModoAdministrador(usuarioActivo.isAdministrador());
			} else {
				throw new ContraseniaIncorrectaException("Contrase�a incorrecta.");
			}
		}
	}
	
	private static boolean isSuperUsuario(String pNickname, String pContrasenia){
		if (pNickname.equals(getInstanciaSuperUsuario().getNickname())){
			if (pContrasenia.equals(getInstanciaSuperUsuario().getContrasenia())) {
				return true;
			}
		} return false;
	}
	
	private static Usuario validarUsuarioRegistrado(String pNickname) throws Exception {
		for (Usuario i : Principal.usuarios) {
			if (pNickname.equals(i.getNickname())) return i;
		}
		throw new UsuarioNoExisteException("Usuario no registrado en el sistema.");
	}
	
	private static void setModoAdministrador(boolean opcion){
		modoAdministrador = opcion;
	}
	
	public static boolean isAdministradorActivo(){
		return usuarioActivo.isAdministrador();
	}
	
	public static Usuario getUsuarioActivo(){
		return usuarioActivo;
	}
	
}