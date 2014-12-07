package paws.vista;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import paws.control.Acceso;
import paws.control.Imagenes;
import paws.modelo.Organizacion;
import paws.modelo.Usuario;
import paws.recursos.Diseno;

public class VentanaDetallesAsociacion extends JFrame {
	private JTextField textNombre;
	private JTextField textDireccion;
	private JTextField textContacto;
	private Organizacion asociacion;
	public VentanaDetallesAsociacion() {
		setSize(600,400);
		getContentPane().setBackground(Diseno.fondoVentanas);
		Panel marcoTitulo = new Panel();
		
		JLabel lblInformacin = new JLabel("Informaci\u00F3n\r\n");
		lblInformacin.setFont(Diseno.fuenteTitulosVentanas);
		marcoTitulo.add(lblInformacin);
		
		JLabel lblDetalles = new JLabel("Detalles de Asociaci\u00F3n");
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		
		JLabel lblNmeroDeContacto = new JLabel("N\u00FAmero de contacto:");
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		
		textContacto = new JTextField();
		textContacto.setColumns(10);
		
		JButton btnCerrar = new JButton("Cerrar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(marcoTitulo, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCerrar)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDetalles)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNmeroDeContacto)
										.addComponent(lblDireccin, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textContacto, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
										.addComponent(textNombre, GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
										.addComponent(textDireccion))))
							.addGap(10)))
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(marcoTitulo, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDetalles)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDireccin, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(textDireccion, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmeroDeContacto, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(textContacto, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
					.addComponent(btnCerrar)
					.addGap(22))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	public void setDatosIniciales(Organizacion pOrganizacion) {
		
		asociacion = pOrganizacion;
		textNombre.setText(asociacion.getNombre());
		textDireccion.setText(asociacion.getDireccion());
		textContacto.setText(asociacion.getNumeroContacto().toString());
	}
	
	
}
