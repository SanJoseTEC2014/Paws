package paws.modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeloTablaOrganizaciones extends AbstractTableModel {
	private ArrayList<Organizacion> listaOrganizaciones;
	private String[] titulos = {"ID","Nombre", "Dirección", "Numero de teléfono","Correo", "Monto total de Donaciones"};

	public ModeloTablaOrganizaciones(ArrayList<Organizacion> pListaAsociaciones){
		listaOrganizaciones = pListaAsociaciones;
	}
	
	public String getColumnName(int column) {
		return titulos[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return listaOrganizaciones.get(rowIndex).getID().toString();
		case 1:
			return listaOrganizaciones.get(rowIndex).getNombre();
		case 2:
			return listaOrganizaciones.get(rowIndex).getDireccion();
		case 3:
			return listaOrganizaciones.get(rowIndex).getNumeroContacto();	
		case 4:
			return listaOrganizaciones.get(rowIndex).getCorreo();
		case 5:
			return listaOrganizaciones.get(rowIndex).getMontoTotalDonaciones();
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

