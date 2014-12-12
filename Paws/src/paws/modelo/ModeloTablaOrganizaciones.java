package paws.modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeloTablaOrganizaciones extends AbstractTableModel {
	private ArrayList<Organizacion> listaOrganizaciones;
	private String[] titulos = {"ID", "Nombre", "Dirección", "Contacto", "Total de Donaciones"};

	public ModeloTablaOrganizaciones(ArrayList<Organizacion> pListaOrganizaciones){
		listaOrganizaciones = pListaOrganizaciones;
	}
	
	public String getColumnName(int column) {
		return titulos[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listaOrganizaciones.get(rowIndex).getID();
		case 1:
			return listaOrganizaciones.get(rowIndex).getNombre();
		case 2:
			return listaOrganizaciones.get(rowIndex).getDireccion();
		case 3:
			return listaOrganizaciones.get(rowIndex).getNumeroContacto();		
		case 4:
			Double totalDonaciones = 0.0;
			for (Donacion x : listaOrganizaciones.get(rowIndex).getDonaciones()){
				totalDonaciones += x.getMonto();
			};
			return totalDonaciones;		
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
		return listaOrganizaciones.size();
	}
}

