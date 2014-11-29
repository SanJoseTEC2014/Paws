package paws.recursos;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Diseno {
	public static Color fondoVentanas = new Color(150,192,150);
	public static Color fondoMarcosVentanas = new Color(176,196,222);
	public static Color letras = new Color(25,25,112);
	private static final File archivoFuenteHuellas = new File(RutasArchivo.fuentes + "Coustard-Regular.ttf");
	public static Font fuenteTitulosVentanas;
	
	public static void inicializarFuentePaws(){
		// Intenta cargar la fuente "Ennobled Pet" para usarla de tï¿½tulo en las ventanas.
		try {
			GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(getFuentePaws());
			fuenteTitulosVentanas = getFuentePaws();
		} catch (FontFormatException | IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),
				"Advertencia", JOptionPane.WARNING_MESSAGE);
			fuenteTitulosVentanas = new Font("Segoe UI Light", Font.PLAIN, 30);
		}
	}
	
	private static Font getFuentePaws() throws FontFormatException, IOException {
		return Font.createFont(Font.TRUETYPE_FONT, archivoFuenteHuellas);
		// Se usa de la siguiente manera:
		// label.setFont(Diseno.fuenteTitulosVentanas.deriveFont(30f));
	}
	
	public static void inicializarLookAndFeel(){
		// Establece un look and feel metálico, si no lo encuentra, establece el look and feel del sistema operativo.
		try {
			UIManager.setLookAndFeel(javax.swing.plaf.nimbus.NimbusLookAndFeel.class.getName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage(),
					"Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	
}
