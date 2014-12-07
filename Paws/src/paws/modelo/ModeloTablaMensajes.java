package paws.modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeloTablaMensajes extends AbstractTableModel {
	private ArrayList<Mascota> listaMensajes;
	private String[] titulos = {"", "Nombre Mascota", "Lugar de Encuentro", "Especie", "Raza" };

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
			int estado = 0;
			while (!listaMensajes.get(rowIndex).getMarcadoresEstado()[estado] && estado < 6){
				estado++; // Busca el estado actual
			}
			return listaMensajes.get(rowIndex).getTodosSucesos()[estado].getLugar();
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
