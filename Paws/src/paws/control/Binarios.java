package paws.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import paws.modelo.Mascota;
import paws.modelo.Usuario;
import paws.recursos.RutasArchivo;

public class Binarios {
	public static void guardarUsuario(Usuario pUsuario){
		File binario = new File(RutasArchivo.binariosUsuarios + RutasArchivo.slash + pUsuario.getNickname() + ".pub");
		try(ObjectOutputStream cargador = new ObjectOutputStream(new FileOutputStream(binario))){
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
			e.getMessage(), "Error al guardar el perfil del usuario " + pUsuario.getNickname() +".",
			JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public static void cargarUsuario(String pNickname){
		try(ObjectInputStream cargador = new ObjectInputStream(
			new FileInputStream(RutasArchivo.binariosUsuarios + RutasArchivo.slash + pNickname + ".pub")))
		{
			Principal.usuarios.add((Usuario) cargador.readObject());
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
			e.getMessage(), "Error al abrir el perfil del usuario " + pNickname +".",
			JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void cargarBaseDatosUsuarios(){
		File usuariosBinarios = new File(RutasArchivo.binariosUsuarios);
	    for (File x : usuariosBinarios.listFiles()) {
	    	if (x.isFile()) {
	    		cargarUsuario(x.getName());
	    	}
	    }
	}
	
	public static void cargarMascota(Integer idMascota){
		try(ObjectInputStream cargador = new ObjectInputStream(
			new FileInputStream(RutasArchivo.binariosMascotas
				+ RutasArchivo.slash + idMascota.toString() + ".ppb")))
		{
			Principal.mascotas.add((Mascota) cargador.readObject());
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
			e.getMessage(), "Error al abrir el perfil de la mascota #"+idMascota+".",
			JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void cargarBaseDatosMascotas(){
		File usuariosBinarios = new File(RutasArchivo.binariosUsuarios);
	    for (File x : usuariosBinarios.listFiles()) {
	    	if (x.isFile()) {
	    		cargarUsuario(x.getName());
	    	}
	    }
	}
}
