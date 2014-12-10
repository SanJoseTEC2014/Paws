package paws.modelo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.table.AbstractTableModel;

import paws.control.Imagenes;

@SuppressWarnings("serial")
public class ModeloTablaCalificaciones extends AbstractTableModel {

	private Object[][] calificaciones;
	private String[] titulos = {"Nickname Calificante", "Mensaje", "Estrellas"};
	
	public ModeloTablaCalificaciones(ArrayList<Calificacion> pCalificaciones) throws IOException {
		calificaciones = new Object[pCalificaciones.size()][3];
		for (int i = 0; i < pCalificaciones.size(); i++){
			BufferedImage estrellas = Imagenes.getEstrellas(pCalificaciones.get(i).getEstrellas());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(estrellas, "png", baos);
			baos.flush();
			calificaciones[i] = new Object[]
			{
				pCalificaciones.get(i).getNicknameCalificante(),
				pCalificaciones.get(i).getMensaje(),
				baos.toByteArray()
			};
			baos.close();
		}
		
	}

	public String getColumnName(int column) {
		return titulos[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return calificaciones[rowIndex][columnIndex];
	}
	
	@Override
	public int getRowCount() {
		return calificaciones.length;
	}
	
	@Override
	public int getColumnCount() {
		return titulos.length;
	}

}
