package paws.modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeloTablaDonaciones extends AbstractTableModel {
	private ArrayList<Donacion> listaDonaciones;
	private String[] titulos = {" Nickname Donante", " Monto donado"};

	public ModeloTablaDonaciones(ArrayList<Donacion> pListaDonaciones){
		listaDonaciones = pListaDonaciones;
	}
	
	public String getColumnName(int column) {
		return titulos[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		
		case 0:
			return listaDonaciones.get(rowIndex).getNicknameDonante();
		case 1:
			return listaDonaciones.get(rowIndex).getMonto();
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
		return listaDonaciones.size();
	}
}
