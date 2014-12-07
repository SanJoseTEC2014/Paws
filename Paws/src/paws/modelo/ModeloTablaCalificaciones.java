package paws.modelo;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import paws.control.Imagenes;

public class ModeloTablaCalificaciones extends AbstractTableModel {

	private ArrayList<Calificacion> listaCalificaciones;
	private String[] titulos = {"Nickname Calificante", "Mensaje", "Estrellas"};
	
	public ModeloTablaCalificaciones(ArrayList<Calificacion> pCalificaciones) {
		listaCalificaciones = pCalificaciones;
	}

	public String getColumnName(int column) {
		return titulos[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		
		case 0:
			return listaCalificaciones.get(rowIndex).getNicknameCalificante();
		case 1:
			return listaCalificaciones.get(rowIndex).getMensaje();
		case 2:
			try {
				return new ImageIcon(Imagenes.getEstrellas(listaCalificaciones.get(rowIndex).getEstrellas()));
			} catch (IOException e) {
				return listaCalificaciones.get(rowIndex).getEstrellas();
			}
			
		default:
			return null;
		}
	}
	
	@Override
	public int getRowCount() {
		return listaCalificaciones.size();
	}
	
	@Override
	public int getColumnCount() {
		return titulos.length;
	}

}
