package paws.control;

/**	 Controlador Acceso: 
 * 	 Esta clase implementa diversos mï¿½todos que permiten validar
 *   el acceso del usuario al sistema.
 * 
 *	 Fecha de creaciï¿½n: 28/10/2014
 * 
 *	@author Isaac Antonio Campos Mesï¿½n 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa Marï¿½a Molina Corrales 2013006074
 *	@author Luis Andrï¿½s Peï¿½a Castillo 2014057250 
 *  
 */

import paws.control.excepciones.*;
import paws.modelo.Usuario;

public class Acceso {
	private static Usuario usuarioActivo;	
	private static Usuario superUsuario;
	
	// Aplicación de Singleton para el superUsuario
	private static void inicializarSuperUsuario(){
		superUsuario = new Usuario("pitbull", "Superusuario", "Paws", 111111111,
				"terrier", 25505033, "pawsconsultas@gmail.com", "Barrio Amón");
		superUsuario.setLapsoEmparejamiento(Usuario.lapsos.get(2));
		superUsuario.setAdministrador(true);
		superUsuario.setRefugiante(true);
	}
	
	private static Usuario getInstanciaSuperUsuario(){
		if (superUsuario == null){
			inicializarSuperUsuario();
		}
		return superUsuario;
	}
	
	public static void validarCredenciales(String pNickname, String pContrasenia) 
	throws UsuarioNoExisteException, TiempoSinEstablecerException, ContraseniaIncorrectaException
	{
		if (isSuperUsuario(pNickname, pContrasenia)){
			usuarioActivo = getInstanciaSuperUsuario();
		} else {
			Usuario usuarioPorAcceder = validarUsuarioRegistrado(pNickname);
			if (usuarioPorAcceder.getContrasenia().equals(pContrasenia)){
				if (!Tiempo.isFechaEstablecida()){
					throw new TiempoSinEstablecerException(
						"Debe haber establecido una fecha inicial previamente.\n" +
						"Contacte al administrador de su sistema.");
				}
				usuarioActivo = usuarioPorAcceder;
			} else {
				throw new ContraseniaIncorrectaException("Contraseña incorrecta.");
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
	
	private static Usuario validarUsuarioRegistrado(String pNickname) throws UsuarioNoExisteException {
		for (Usuario i : Principal.usuarios) {
			if (pNickname.equals(i.getNickname())) return i;
		}
		throw new UsuarioNoExisteException("Usuario no registrado en el sistema.");
	}
	
	public static boolean isAdministradorActivo(){
		return usuarioActivo.isAdministrador();
	}
	
	public static Usuario getUsuarioActivo(){
		return usuarioActivo;
	}
	
}