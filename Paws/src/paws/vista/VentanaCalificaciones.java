package paws.vista;

import java.awt.*;

import javax.swing.*;

import paws.control.Imagenes;
import paws.modelo.ModeloTablaCalificaciones;
import paws.modelo.Usuario;
import paws.recursos.Diseno;

@SuppressWarnings("serial")
public class VentanaCalificaciones extends JFrame {

	private JTable tablaCalificaciones;
	String Titulos[] = {"Nick Calificante", "Calificación", "Comentario"};
	private JLabel labelCalificaciones;
	private JPanel panel;

	public VentanaCalificaciones() {
		getContentPane().setBackground(Diseno.fondoVentanas);
		setSize(400,400);
		setIconImage(Imagenes.getIconoSistema().getImage());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		labelCalificaciones = new JLabel("Calificaciones");
		labelCalificaciones.setFont(Diseno.fuenteTitulosVentanas);
		labelCalificaciones.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelCalificaciones, BorderLayout.NORTH);
		
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		tablaCalificaciones = new JTable();
		panel.add(tablaCalificaciones);
		tablaCalificaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaCalificaciones.setOpaque(false);
	}
	public void setUsuario(Usuario usuarioACalificar) {
		tablaCalificaciones.setModel(new ModeloTablaCalificaciones(usuarioACalificar.getCalificaciones()));
	}
}
	
