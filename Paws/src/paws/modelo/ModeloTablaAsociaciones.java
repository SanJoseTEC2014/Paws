package paws.modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeloTablaAsociaciones extends AbstractTableModel {
	private ArrayList<Organizacion> listaAsociaciones;
	private String[] titulos = {"Nombre", "Dirección", "Contacto"};

	public ModeloTablaAsociaciones(ArrayList<Organizacion> pListaAsociaciones){
		listaAsociaciones = pListaAsociaciones;
	}
	
	public String getColumnName(int column) {
		return titulos[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		
		case 0:
			return listaAsociaciones.get(rowIndex).getNombre();
		case 1:
			return listaAsociaciones.get(rowIndex).getDireccion();
		case 2:
			return listaAsociaciones.get(rowIndex).getNumeroContacto();		
		default:
			return null;
		}
	}
	@Override
	public int getColumnCount() {
		return titulos.length;
	}

	@Override
	public int getRowCount() {
		return listaAsociaciones.size();
	}
}

