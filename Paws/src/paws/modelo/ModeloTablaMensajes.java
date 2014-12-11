package paws.modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import paws.control.EstadosMascotas;

@SuppressWarnings("serial")
public class ModeloTablaMensajes extends AbstractTableModel {
	private ArrayList<Mascota> listaMensajes;
	private String[] titulos = {"", "Nombre Mascota", "Lugar del Último Suceso", "Especie", "Raza" };

	public ModeloTablaMensajes(ArrayList<Mascota> pListaMascotas){
		listaMensajes = pListaMascotas;
	}
	public String getColumnName(int column) {
		return titulos[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listaMensajes.get(rowIndex).getID().toString();
		case 1:
			return listaMensajes.get(rowIndex).getNombre();
		case 2:
			return listaMensajes.get(rowIndex).getUltimoSuceso().getLugar();
		case 3:
			return listaMensajes.get(rowIndex).getEspecie();
		case 4:
			return listaMensajes.get(rowIndex).getRaza();
		default:
			return null;
		}
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return listaMensajes.size();
	}
	
}
