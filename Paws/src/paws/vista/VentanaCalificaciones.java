package paws.vista;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import paws.control.Imagenes;
import paws.modelo.ModeloTablaCalificaciones;
import paws.modelo.Usuario;
import paws.recursos.Diseno;

@SuppressWarnings("serial")
public class VentanaCalificaciones extends JFrame {
	private JLabel labelCalificaciones;
	private JTable tablaCalificaciones;

	public VentanaCalificaciones() {
		setSize(400, 400);
		getContentPane().setBackground(Diseno.fondoVentanas);
		setIconImage(Imagenes.getIconoSistema().getImage());
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		labelCalificaciones = new JLabel("Calificaciones");
		labelCalificaciones.setFont(Diseno.fuenteTitulosVentanas);
		labelCalificaciones.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelCalificaciones, BorderLayout.NORTH);
		
		tablaCalificaciones = new JTable();
		tablaCalificaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaCalificaciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaCalificaciones.setOpaque(false);
		JScrollPane barras = new javax.swing.JScrollPane(tablaCalificaciones,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		barras.setBackground(Diseno.fondoVentanas);
		barras.setOpaque(false);
		getContentPane().add(barras, BorderLayout.CENTER);
	}
	public void setUsuario(Usuario usuarioACalificar) {
		try {
			tablaCalificaciones.setModel(new ModeloTablaCalificaciones(usuarioACalificar.getCalificaciones()));
			tablaCalificaciones.getColumnModel().getColumn(2).setCellRenderer(new ImageIconCellRenderer());
			tablaCalificaciones.setRowHeight(Imagenes.getEstrellas(0).getSampleModel().getHeight());
			setSize((Imagenes.getEstrellas(0).getSampleModel().getWidth() * 3) + 50, 400);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(getContentPane(),
			"Error inesperado.\nNo se pueden cargar las estrellas.");
			
			// En caso de que ocurra un error con las imágenes de las estrellas,
			// se construye un modelo de tabla en el instante. 
			String[][] calificaciones = new String[usuarioACalificar.getCalificaciones().size()][3];
			for (int i = 0; i < usuarioACalificar.getCalificaciones().size(); i++){
				calificaciones[i] = new String[]{
					usuarioACalificar.getCalificaciones().get(i).getNicknameCalificante(),
					usuarioACalificar.getCalificaciones().get(i).getMensaje(),
					usuarioACalificar.getCalificaciones().get(i).getEstrellas().toString()
				};
			}
			tablaCalificaciones.setModel(
				new DefaultTableModel(
					calificaciones, new String[]{"Nickname Calificante", "Mensaje", "Estrellas"}));
		}
	}
}