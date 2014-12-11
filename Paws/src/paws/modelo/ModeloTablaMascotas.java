package paws.modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import paws.control.EstadosMascotas;

@SuppressWarnings("serial")
public class ModeloTablaMascotas extends AbstractTableModel {
	private ArrayList<Mascota> listaMascotas;
	private String[] titulos = {"ID", "Nombre", "Especie", "Raza", "Estado Actual", "Registrado por..."};

	public ModeloTablaMascotas(ArrayList<Mascota> pListaMascotas){
		listaMascotas = pListaMascotas;
	}
	public String getColumnName(int column) {
		return titulos[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
			case 0:
				return listaMascotas.get(rowIndex).getID().toString();
			case 1:
				return listaMascotas.get(rowIndex).getNombre();
			case 2:
				return listaMascotas.get(rowIndex).getEspecie();
			case 3:
				return listaMascotas.get(rowIndex).getRaza();
			case 4:
				return listaMascotas.get(rowIndex).getUltimoSuceso().getEstado();
			case 5:
				return listaMascotas.get(rowIndex).getUltimoSuceso().getNick();
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
