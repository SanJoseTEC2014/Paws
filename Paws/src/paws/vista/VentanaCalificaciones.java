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
	private JScrollPane panelScroll;
	String Titulos[] = {"Nick Calificante", "Calificación", "Comentario"};
	private JLabel labelCalificaciones;

	public VentanaCalificaciones() {
		getContentPane().setBackground(Diseno.fondoVentanas);
		setSize(400,400);
		setIconImage(Imagenes.getIconoSistema().getImage());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		labelCalificaciones = new JLabel("Calificaciones");
		labelCalificaciones.setFont(Diseno.fuenteTitulosVentanas);
		labelCalificaciones.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelCalificaciones, BorderLayout.NORTH);
		
		panelScroll = new JScrollPane();
		panelScroll.setOpaque(false);
		panelScroll.setViewportView(tablaCalificaciones);
		getContentPane().add(panelScroll);
		
		tablaCalificaciones = new JTable();
		tablaCalificaciones.setOpaque(false);
		panelScroll.add(tablaCalificaciones);
	}
	public void setUsuario(Usuario usuarioActual) {
		tablaCalificaciones = new JTable(new ModeloTablaCalificaciones(usuarioActual.getCalificaciones()));
	}
}
	
