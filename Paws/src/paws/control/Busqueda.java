package paws.control;

/**	 Controlador Bï¿½squeda: 
 * 	 Esta clase implementa los mï¿½todos que se encargan
 * 	 de realizar la bï¿½squeda de usuarios y mascotas en 
 *   el sistema.
 * 
 *	Fecha de creaciï¿½n: 17/10/2014
 * 
 *	@author Isaac Antonio Campos Mesï¿½n 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa Marï¿½a Molina Corrales 2013006074
 *	@author Luis Andrï¿½s Peï¿½a Castillo 2014057250 
 *  
 */

import java.util.ArrayList;
import java.util.LinkedList;

import paws.modelo.Mascota;
import paws.modelo.Usuario;

public class Busqueda {

	public static ArrayList<Mascota> buscarMascotas(LinkedList<String> pTerminos, boolean[] pEstadosBusqueda) {
		
		ArrayList<Mascota> resultados = new ArrayList<Mascota>();
	
		for (int numeroTermino = 0; numeroTermino < pTerminos.size(); numeroTermino++){
			String criterio = pTerminos.get(numeroTermino);
			if (criterio != "") {
				for (Mascota porFiltrar : Principal.mascotas) {
					if ((pEstadosBusqueda[0] && porFiltrar.getMarcadoresEstado()[0]) ||
						(pEstadosBusqueda[1] && porFiltrar.getMarcadoresEstado()[1]) ||
						(pEstadosBusqueda[2] && porFiltrar.getMarcadoresEstado()[2]) ||
						(pEstadosBusqueda[3] && porFiltrar.getMarcadoresEstado()[3]) ||
						(pEstadosBusqueda[4] && porFiltrar.getMarcadoresEstado()[4]) ||
						(pEstadosBusqueda[5] && porFiltrar.getMarcadoresEstado()[5]))
					{   // Las mascotas que están siendo filtradas deben tener uno de los estados de la búsqueda						
						// Verificación de los resultados de una búsqueda
						/*
						String debugmsg = nombreCoincide(porFiltrar, criterio) ? "true " : "false ";
						debugmsg += lugarEncuentroCoincide(porFiltrar, criterio) ? "true " : "false ";
						debugmsg += lugarPerdidaCoincide(porFiltrar, criterio) ? "true " : "false ";
						debugmsg += numeroChipCoincide(porFiltrar, criterio) ? "true " : "false ";
						debugmsg += especieCoincide(porFiltrar, criterio) ? "true " : "false ";
						debugmsg += razaCoincide(porFiltrar, criterio) ? "true " : "false ";
						System.out.println(debugmsg);
						*/
						switch (numeroTermino){
							case 0: {
								if (nombreCoincide(porFiltrar, criterio)) resultados.add(porFiltrar.clone());
							}
							break;
							case 1: {
								if (lugarEncuentroCoincide(porFiltrar, criterio) ||
									lugarPerdidaCoincide(porFiltrar, criterio))
								{
									resultados.add(porFiltrar.clone());
								}
							}
							break;
							case 2: {
								if (numeroChipCoincide(porFiltrar, criterio)) resultados.add(porFiltrar.clone());
							}
							break;
							case 3: {
								if (especieCoincide(porFiltrar, criterio)) resultados.add(porFiltrar.clone());
							}
							break;
							case 4: {
								if (razaCoincide(porFiltrar, criterio)) resultados.add(porFiltrar.clone());
							}
							break;
						}
					}
				}
			}
		}
		
		return resultados;
	}
	
	private static boolean nombreCoincide(Mascota pMascota, String pCriterio){
		return pMascota.getNombre().toLowerCase().contains(pCriterio.toLowerCase());
	}
	
	private static boolean lugarPerdidaCoincide(Mascota pMascota, String pCriterio){
		if (pMascota.getMarcadoresEstado()[0]) { // Desaparecida
			return pMascota.getTodosSucesos()[0].getLugar().toLowerCase().contains(pCriterio.toLowerCase());
		}
		return false;
	}
	
	private static boolean lugarEncuentroCoincide(Mascota pMascota, String pCriterio){
		if (pMascota.getMarcadoresEstado()[1]) { // Encontrada
			return pMascota.getTodosSucesos()[1].getLugar().toLowerCase().contains(pCriterio.toLowerCase());
		}
		return false;
	}
	
	private static boolean numeroChipCoincide(Mascota pMascota, String pCriterio){
		return pMascota.getNumeroChip() == null? false : pMascota.getNumeroChip().equals(pCriterio);
	}
	
	private static boolean especieCoincide(Mascota pMascota, String pCriterio){
		return pMascota.getEspecie().equals(pCriterio);
	}
	
	private static boolean razaCoincide(Mascota pMascota, String pCriterio){
		return pMascota.getRaza().equals(pCriterio);
	}
	
	//////////////////////////////// BLOQUE BUSQUEDA DE USUARIOS /////////////////////////////
	
	public static ArrayList<Usuario> buscarUsuarios(LinkedList<String> pTerminos, boolean refugiantes) {
		ArrayList<Usuario> resultados = new ArrayList<Usuario>();
		for (int numeroTermino = 0; numeroTermino < pTerminos.size(); numeroTermino++)
		{
			String criterio = pTerminos.get(numeroTermino);
			if (criterio != "")
			{
				for (Usuario porFiltrar : Principal.usuarios)
				{
					switch (numeroTermino){
						case 0: {
							if (nickCoincide(porFiltrar, criterio)){
								if (refugiantes) {
									if(porFiltrar.isRefugiante()) {
										resultados.add(porFiltrar.clone());
									}
								} else {
									resultados.add(porFiltrar.clone());
								}
							}
						}
						break;
						case 1: {
							if (nombreCoincide(porFiltrar, criterio)){
								if (refugiantes) {
									if(porFiltrar.isRefugiante()) {
										resultados.add(porFiltrar.clone());
									}
								} else {
									resultados.add(porFiltrar.clone());
								}
							}
						}
						break;
						case 2: {
							if (apellidosCoincide(porFiltrar, criterio)){
								if (refugiantes) {
									if(porFiltrar.isRefugiante()) {
										resultados.add(porFiltrar.clone());
									}
								} else {
									resultados.add(porFiltrar.clone());
								}
							}
						}
						break;
						case 3: {
							if (cedulaCoincide(porFiltrar, criterio)){
								if (refugiantes) {
									if(porFiltrar.isRefugiante()) {
										resultados.add(porFiltrar.clone());
									}
								} else {
									resultados.add(porFiltrar.clone());
								}
							}
						}
						break;
						case 4: {
							if (numTelefonoCoincide(porFiltrar, criterio)){
								if (refugiantes) {
									if(porFiltrar.isRefugiante()) {
										resultados.add(porFiltrar.clone());
									}
								} else {
									resultados.add(porFiltrar.clone());
								}
							}
						}
						break;
						case 5: {
							if (correoCoincide(porFiltrar, criterio)){
								if (refugiantes) {
									if(porFiltrar.isRefugiante()) {
										resultados.add(porFiltrar.clone());
									}
								} else {
									resultados.add(porFiltrar.clone());
								}
							}
						}
						break;
					}
				}
			}
		}
		
		return resultados;
	}

	private static boolean correoCoincide(Usuario pUsuario, String pCriterio) {
		return pUsuario.getCorreo().toLowerCase().contains(pCriterio.toLowerCase());
	}

	private static boolean numTelefonoCoincide(Usuario pUsuario, String pCriterio) {
		return pUsuario.getTelefono().toString().contains(pCriterio);
	}

	private static boolean cedulaCoincide(Usuario pUsuario, String pCriterio) {
		return pUsuario.getCedula().toString().contains(pCriterio);
	}

	private static boolean apellidosCoincide(Usuario pUsuario, String pCriterio) {
		return pUsuario.getApellidos().toLowerCase().contains(pCriterio.toLowerCase());
	}

	private static boolean nombreCoincide(Usuario pUsuario, String pCriterio) {
		return pUsuario.getNombre().toLowerCase().contains(pCriterio.toLowerCase());

	}

	private static boolean nickCoincide(Usuario pUsuario, String pCriterio) {
		return pUsuario.getNickname().toLowerCase().contains(pCriterio.toLowerCase());
	}
}
