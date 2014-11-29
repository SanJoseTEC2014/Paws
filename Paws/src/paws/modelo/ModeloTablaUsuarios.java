package paws.modelo;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ModeloTablaUsuarios extends AbstractTableModel {
	private ArrayList<Usuario> listaUsuarios;
	private String[] titulos = {"NickName", "Nombre", "Apellidos", "cedula", "Telefono", "Correo"};

	public ModeloTablaUsuarios(ArrayList<Usuario> pListaUsusarios){
		listaUsuarios = pListaUsusarios;
	}
	
	public String getColumnName(int column) {
		return titulos[column];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		
		case 0:
			return listaUsuarios.get(rowIndex).getNickname();
		case 1:
			return listaUsuarios.get(rowIndex).getNombre();
		case 2:
			return listaUsuarios.get(rowIndex).getApellidos();
		case 3:
			return listaUsuarios.get(rowIndex).getCedula();
		case 4:
			return listaUsuarios.get(rowIndex).getTelefono();
		case 5:
			return listaUsuarios.get(rowIndex).getCorreo();
				
		default:
			return null;
		}
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		return listaUsuarios.size();
	}
	
	public int getCantidadResultados(){
		return listaUsuarios.size();
	}
}
