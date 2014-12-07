package paws.vista;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import paws.control.Imagenes;
import paws.modelo.Usuario;

import java.awt.BorderLayout;

public class VentanaCalificaciones extends JFrame {
	private JTable tablaCalificaciones;
	private JScrollPane panelScrol;
	String Titulos[] = {"Nick Calificante", "Calificación", "Comentario"};

	public VentanaCalificaciones() {
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		setSize(400,400);
		setIconImage(Imagenes.getIconoSistema().getImage());
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		tablaCalificaciones = new JTable();
		panelScrol = new JScrollPane();
		panel.add(panelScrol);
		panelScrol.setViewportView(tablaCalificaciones);
	}
	public void setUsuario(Usuario usuarioActual) {
		tablaCalificaciones = new JTable(usuarioActual.getArrayCalificaciones(), Titulos);
	}
}
	
