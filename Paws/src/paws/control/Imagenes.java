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

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

import paws.control.excepciones.ImagenNoEncontradaException;
import paws.recursos.RutasArchivo;
import net.coobird.thumbnailator.Thumbnails;

public class Imagenes {
		
	public static BufferedImage cargarImagen(String pRuta) throws ImagenNoEncontradaException{
		try {
			//BufferedImage temp = 
			//JOptionPane.showMessageDialog(null, pRuta + "\n" + temp.getWidth() + "\n" + temp.getHeight());
			return ImageIO.read(new File(pRuta));
		} catch (IOException e) {
			throw new ImagenNoEncontradaException(e.getMessage());
		}
	}
	
	public static BufferedImage getEstrellas(Double ponderado) throws ImagenNoEncontradaException{
		String rutaEstrellas = RutasArchivo.fotosSistema + "estrellas" + RutasArchivo.slash;
		
		if (ponderado <= 5.0 && ponderado > 4.5) {
			rutaEstrellas += "5.0";
			
		} else if (ponderado == 4.5) {
			rutaEstrellas += "4.5";
			
		} else if (ponderado >= 4.0 && ponderado < 4.5) {
			rutaEstrellas += "4.0";
			
		} else if (ponderado == 3.5) {
			rutaEstrellas += "3.5";
			
		} else if (ponderado >= 3.0 && ponderado < 3.5) {
			rutaEstrellas += "3.0";
			
		} else if (ponderado == 2.5) {
			rutaEstrellas += "2.5";
			
		} else if (ponderado >= 2.0 && ponderado < 2.5) {
			rutaEstrellas += "2.0";
			
		} else if (ponderado == 1.5) {
			rutaEstrellas += "1.5";
			
		} else if (ponderado >= 1.0 && ponderado < 1.5) {
			rutaEstrellas += "1.0";
			
		} else if (ponderado == 0.5) {
			rutaEstrellas += "0.5";
			
		} else if (ponderado >= 0.0 && ponderado < 0.5) {
			rutaEstrellas += "0.0";
		}
		rutaEstrellas += ".png";
		return cargarImagen(rutaEstrellas);
	}
	
	public static BufferedImage getPerfilMascota(Integer idMascota) throws ImagenNoEncontradaException{
		return cargarImagen(RutasArchivo.fotosMascotas + idMascota.toString() + ".jpg");
	}
	
	public static BufferedImage getPerfil(String nickUsuario)  throws ImagenNoEncontradaException{
		return cargarImagen(RutasArchivo.fotosUsuarios + nickUsuario + "perfil.jpg");
	}
	
	public static BufferedImage getImagenError() throws ImagenNoEncontradaException {
		return cargarImagen(RutasArchivo.fotosSistema + "404.jpg");
	}
	
	public static BufferedImage getLogo1() throws ImagenNoEncontradaException {
		return cargarImagen(RutasArchivo.fotosSistema + "paws1.png");
	}
	
	public static BufferedImage getLogo2() throws ImagenNoEncontradaException {
		return cargarImagen(RutasArchivo.fotosSistema + "paws2.png");
	}
	
	public static ImageIcon getIconoSistema() {
		try {
			return new ImageIcon(cargarImagen(RutasArchivo.fotosSistema + "whitepaw.png"));
		} catch (ImagenNoEncontradaException e) {
			return new ImageIcon();
		}
	}
	
	public static BufferedImage getIconoBusqueda() throws ImagenNoEncontradaException {
		return cargarImagen(RutasArchivo.fotosSistema + "iconoBuscarMascota.png");
	}
	
	public static void guardarFotoPerfilUsuario(String nickUsuario, String rutaOrigen){
		String rutaDestino = RutasArchivo.fotosUsuarios + nickUsuario + "perfil.jpg";
		guardarImagen(rutaOrigen, rutaDestino);
	}
	
	public static void guardarFotoPerfilMascota(Integer IDMascota, String rutaOrigen){
		String rutaDestino = RutasArchivo.fotosMascotas + IDMascota.toString() + ".jpg";
		guardarImagen(rutaOrigen, rutaDestino);
	}
	
	private static void guardarImagen(String rutaOrigen, String rutaDestino) {
		try {
			FileInputStream flujoArchivoEntrada = new FileInputStream(rutaOrigen);
			FileOutputStream flujoArchivoSalida = new FileOutputStream(rutaDestino);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = flujoArchivoEntrada.read(buffer)) > 0) {
				flujoArchivoSalida.write(buffer, 0, length);
			}
			flujoArchivoEntrada.close();
			flujoArchivoSalida.close();
		} catch (IOException noHayArchivo) {
			JOptionPane.showMessageDialog(null,
				"No se pudo copiar la imagen seleccionada.\n" + noHayArchivo.getMessage(),
				"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static String seleccionarImagen() throws ImagenNoEncontradaException{
		JFileChooser selector = new JFileChooser();
		selector.setAcceptAllFileFilterUsed(false); // Deshabilita seleccionar todos los archivos
		selector.setFileFilter(new FileNameExtensionFilter("Sólo imágenes", ImageIO.getReaderFileSuffixes()));
		
		if (selector.showOpenDialog(null) == JFileChooser.CANCEL_OPTION){
			throw new ImagenNoEncontradaException("Se canceló la operación.");
		} else if (selector.getSelectedFile().getAbsolutePath() == null){
			throw new ImagenNoEncontradaException("No se seleccionó ninguna imagen.");
		}
		return selector.getSelectedFile().getAbsolutePath();
	}
	
	public static BufferedImage redimensionar(BufferedImage origen, int nuevoAncho, int nuevoAlto) throws ImagenNoEncontradaException {
		try {
			return Thumbnails.of(origen).size(nuevoAncho, nuevoAlto).asBufferedImage();
		} catch (IOException e) {
			throw new ImagenNoEncontradaException("No se encuentra la imagen requerida.");
		}
	}
	
	public static double getRelacionAspecto(int x, int y)		{ return (double) x / (double) y; }
	public static double getRelacionAspecto(int x, double y)	{ return (double) x / y; }
	public static double getRelacionAspecto(double x, int y)	{ return x / (double) y; }
	public static double getRelacionAspecto(double x, double y)	{ return x / y; }
	
	public static Dimension getNuevaDimension(int anchoDisponible, int altoDisponible, int anchoImagen, int altoImagen) {		
		int nuevoAncho = anchoImagen;
		if (nuevoAncho > anchoDisponible) nuevoAncho = anchoDisponible;
		
		int nuevoAlto = (int) ((double) anchoImagen / getRelacionAspecto(anchoImagen, altoImagen));

		while (nuevoAlto > altoDisponible) {
			 double porcentaje = (double) nuevoAlto / altoDisponible;
			 nuevoAncho /= porcentaje;
			 nuevoAlto /= porcentaje;
		}
		
		return new Dimension(nuevoAncho, nuevoAlto);
	}
	
}