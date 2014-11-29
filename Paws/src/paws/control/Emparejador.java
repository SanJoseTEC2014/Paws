package paws.control;

/**
 * Controlador : Emparejador
 * Se encarga de realizar el match correspondiente en un lapso definido por el usuario
 * con todas las mascotas reportadas como propias con las mascotas que estï¿½n reportadas en 
 * el sistema como: "encontradas","perdidas","refugiadas", "adoptadas", "localizadas" o "muertas".
 * 
 * 	Fecha de creaciï¿½n: 28/10/2014
 * 
 *	@author Isaac Antonio Campos Mesï¿½n 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa Marï¿½a Molina Corrales 2013006074
 *	@author Luis Andrï¿½s Peï¿½a Castillo 2014057250 
 *
 */
import java.util.ArrayList;

import javax.mail.*;
import javax.swing.*;

import paws.control.Principal;
import paws.modelo.CondicionesRefugio;
import paws.modelo.Mascota;
import paws.modelo.Usuario;

public class Emparejador {

	public static void rutinaMatch(Usuario pUsuario){
		// Esta función es invocada cada vez que "transcurre un día". 
		// La rutina de emparejamiento escoge la lista de mascotas perdidas de pUsuario.
		// Se intenta enviar un correo al usuario en el lapso de tiempo definido por este.
		String diario = Usuario.lapsos.get(0);
		String semanal = Usuario.lapsos.get(1);
		String mensual = Usuario.lapsos.get(2);
		boolean usuarioOK = false;
		if (pUsuario.getLapsoEmparejamiento().equals(diario)){
			usuarioOK = true;
			for (Mascota mascota : pUsuario.getMascotasPerdidas()){
				emparejarAutomatico(mascota, pUsuario);
			}
		}
		if (pUsuario.getLapsoEmparejamiento().equals(semanal)){
			usuarioOK = true;
			if (pUsuario.getDiasUltimoEmparejamiento() == 7){
				for (Mascota mascota : pUsuario.getMascotasPerdidas()){
					emparejarAutomatico(mascota, pUsuario);
				}
			} else {
				pUsuario.addDiasTranscurridos();
			}
		}
		if (pUsuario.getLapsoEmparejamiento().equals(mensual)){
			usuarioOK = true;
			if (pUsuario.getDiasUltimoEmparejamiento() == 30){
				for (Mascota mascota : pUsuario.getMascotasPerdidas()){
					emparejarAutomatico(mascota, pUsuario);
				}
			} else {
				pUsuario.addDiasTranscurridos();
			}
		}
		if (!usuarioOK){
			JOptionPane.showMessageDialog(null, "Error inesperado del sistema",
				"Este usuario debería tener un lapso definido\n" +
				"para sus emparejamientos pre-programados: " + pUsuario.getNickname(),
			JOptionPane.ERROR_MESSAGE);
		}
	}
		
	private static void emparejarAutomatico(Mascota pMascota, Usuario pUsuario){
		
		ArrayList<Mascota> coincidencias = new ArrayList<Mascota>();
		coincidencias = emparejarBajoDemanda(pMascota);
		try {
            Correo.enviarCoincidencias(coincidencias, pMascota,  pUsuario);
        } catch (MessagingException ex) {
            JOptionPane.showMessageDialog(null, "Error",
                "Estimado Usuario " + pUsuario.getNombre() +
            	"\nHubo un error al enviarle el correo con la lista de coincidencias para" +
                "\nsu Mascota: " + pMascota.getNombre() +
                "\nPor favor, para obtener la lista de resultados sobre esta mascota," +
                "\nejecute un emparejamiento bajo demanda." +
                "\nCausa del error: " + ex.getMessage(),
            JOptionPane.WARNING_MESSAGE);
        }
		
	}
		
	public static ArrayList<Mascota> emparejarBajoDemanda(Mascota pMascotaSeleccionada) {
		
		// Empareja una mascota escogida por pUsuario con todas
		// las mascotas reportadas en el sistema como:
		// "encontradas", "perdidas", "refugiadas", "adoptadas", "localizadas" o "muertas",
		// segï¿½n el estado actual de la mascota.
				
		ArrayList<Mascota> coincidencias = new ArrayList<Mascota>();
		
		for (Mascota posibleHit : Principal.mascotas) {
			// Para evitar un duplicado de la misma mascota que se está buscando
			if (pMascotaSeleccionada != posibleHit) {
				
				if ( (pMascotaSeleccionada.getMarcadoresEstado()[0] && posibleHit.getMarcadoresEstado()[1]) ||
					 // Seleccionada: Desaparecida;  Posible Hit: Encontrada
						
					 (pMascotaSeleccionada.getMarcadoresEstado()[0] && posibleHit.getMarcadoresEstado()[2]) ||
					 // Seleccionada: Desaparecida;  Posible Hit: Refugiada
					 
					 (pMascotaSeleccionada.getMarcadoresEstado()[1] && posibleHit.getMarcadoresEstado()[0]) ||
					 // Seleccionada: Encontrada;  Posible Hit: Desaparecida
					 
					 (pMascotaSeleccionada.getMarcadoresEstado()[2] && posibleHit.getMarcadoresEstado()[0]))  
					 // Seleccionada: Refugiada;  Posible Hit: Desaparecida
				{
					if (pMascotaSeleccionada.getNumeroChip().equals(posibleHit.getNumeroChip())) {
						coincidencias.add(posibleHit);
					} else {
						if (pMascotaSeleccionada.getRaza().equals(posibleHit.getRaza()) &&
							pMascotaSeleccionada.getEspecie().equals(posibleHit.getEspecie()) &&
							pMascotaSeleccionada.getColor().equals(posibleHit.getColor()))
						{
							coincidencias.add(posibleHit);
						}
					}
				}
			}
		}
		return coincidencias;
    }
	
	public static ArrayList<Usuario> getListaRefugiantes(Mascota pMascotaAComparar){
		// Busca los usuarios que tengan condiciones de refugio
		// similares a las características de la mascota reportada.

		ArrayList<Usuario> refugiantes =  new ArrayList<Usuario>();

		for (Usuario usuario : Principal.usuarios) {
			if (!usuario.isBloqueado()){
				CondicionesRefugio pCondiciones = usuario.getCondicionesRefugio();
				
				if (pMascotaAComparar.isVacunada() == pCondiciones.isSoloVacunada()
					&& pMascotaAComparar.isCastrada() == pCondiciones.isSoloCastrada()
					&& pMascotaAComparar.isDesparacitada() == pCondiciones.isSoloDesparacitada()) {
						refugiantes.add(usuario);
				}
			}
		}

		return refugiantes;
	}
}