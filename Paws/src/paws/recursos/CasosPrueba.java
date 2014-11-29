package paws.recursos;

/**	 Clase CasosPrueba: 
 * 	 Esta clase implementa mï¿½todos para poder cargar
 * 	 los casos de prueba tanto de mascotas como de usuarios
 * 	 al sistema.
 * 
 *	Fecha de creaciï¿½n: 8/11/2014
 * 
 *	@author Isaac Antonio Campos Mesï¿½n 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa Marï¿½a Molina Corrales 2013006074
 *	@author Luis Andrï¿½s Peï¿½a Castillo 2014057250 
 *  
 */

import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

import paws.control.EstadosMascotas;
import paws.control.Principal;
import paws.modelo.Mascota;
import paws.modelo.Suceso;
import paws.modelo.Usuario;

public class CasosPrueba {
	public static void cargarDocumentoUsuariosPrueba(){
		try {
			DocumentoCasosPrueba usuariosPrueba = new DocumentoCasosPrueba("usuarios.csv");
			for (int i = 0; i < usuariosPrueba.getDocumentoSize(); i++){
				LinkedList<String> registro = usuariosPrueba.getRegistro(i);
				Usuario porRegistrar = new Usuario(registro.get(0), registro.get(1),
						registro.get(2) + " " + registro.get(3),
						Integer.parseInt(registro.get(4)), registro.get(5), 
						Integer.parseInt(registro.get(6)), registro.get(7), registro.get(8));
				Principal.usuarios.add(porRegistrar);
			}
			JOptionPane.showMessageDialog(null,
					"Casos de prueba de usuarios cargados satisfactoriamente.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
				e.getMessage(),
				"Error de casos de prueba",
				JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void cargarDocumentoMascotasPrueba(){
		try {
			DocumentoCasosPrueba mascotasPrueba = new DocumentoCasosPrueba("mascotas.csv");
			for (int i = 0; i < mascotasPrueba.getDocumentoSize(); i++){
				LinkedList<String> registro = mascotasPrueba.getRegistro(i);
				
				Mascota porRegistrar = new Mascota(registro.get(0), registro.get(1), registro.get(2), Integer.parseInt(registro.get(3)));
				switch(registro.get(10)){
				case "DESAPARECIDA":
					porRegistrar.addNuevoSuceso(new Suceso(registro.get(4), EstadosMascotas.estadoDESAPARECIDA, registro.get(6), registro.get(5)));
					break;
				case "ENCONTRADA":
					porRegistrar.addNuevoSuceso(new Suceso(registro.get(4), EstadosMascotas.estadoENCONTRADA, registro.get(6), registro.get(5)));
					break;
				}
				porRegistrar.setSexo(registro.get(7));
				porRegistrar.setColor(registro.get(8));
				porRegistrar.setTamanio(registro.get(9));
				porRegistrar.setCastrada(registro.get(11).equals("SI") ? true : false);
				porRegistrar.setVacunada(registro.get(12).equals("SI") ? true : false);
				porRegistrar.setDesparacitada(registro.get(13).equals("SI") ? true : false);
				porRegistrar.setNumeroChip(registro.get(14));
				Principal.mascotas.add(porRegistrar);
			}
			JOptionPane.showMessageDialog(null,
					"Casos de prueba de mascotas cargados satisfactoriamente.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
				e.getMessage(),
				"Error de casos de prueba",
				JOptionPane.ERROR_MESSAGE);
		}
				
	}
}

/*
 * registro.get(0) Nombre
 * registro.get(1) Especie
 * registro.get(2) Raza
 * registro.get(3) Recompensa
 * 
 * SUCESO:
 * registro.get(4) Nickname Registrante
 * registro.get(5) Descripción del Suceso
 * registro.get(6) Lugar
 * 
 * registro.get(7) Sexo
 * registro.get(8) Color
 * registro.get(9) Tamaño
 * registro.get(10) Estado Inicial
 * 
 * Características
 * registro.get(11) Castrada
 * registro.get(12) Vacunada
 * registro.get(13) Desparacitada
 * 
 * registro.get(14) Numero Chip
 */