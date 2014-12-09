package paws.modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeloTablaMascotas extends AbstractTableModel {
	private ArrayList<Mascota> listaMascotas;
	private String[] titulos = {"ID", "Nombre", "Especie", "Raza", "Estado Actual (registrado por)"};

	public ModeloTablaMascotas(ArrayList<Mascota> pListaMascotas){
		listaMascotas = pListaMascotas;
	}
	public String getColumnName(int column) {
		return titulos[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listaMascotas.get(rowIndex).getID();
		case 1:
			return listaMascotas.get(rowIndex).getNombre();
		case 3:
			return listaMascotas.get(rowIndex).getEspecie();
		case 4:
			return listaMascotas.get(rowIndex).getRaza();
		case 5:
			int estado = 0;
			while (!listaMascotas.get(rowIndex).getMarcadoresEstado()[estado] && estado < 6){
				estado++; // Busca el estado actual
			}
			return listaMascotas.get(rowIndex).getTodosSucesos()[estado].getEstado()
				+ "(" + listaMascotas.get(rowIndex).getTodosSucesos()[estado].getNick() + ")";
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
		return listaMascotas.size();
	}
}
