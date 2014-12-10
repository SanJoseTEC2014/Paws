package paws.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import paws.modelo.Organizacion;
import paws.recursos.Diseno;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VentanaDetallesOrganizacion extends JFrame {
	private JTextField textNombre;
	private JTextField textDireccion;
	private JTextField textContacto;
	private Organizacion asociacion;
	public VentanaDetallesOrganizacion() {
		setSize(600,400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Diseno.fondoVentanas);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel labelInformacion = new JLabel("Informaci\u00F3n\r\n");
		labelInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		labelInformacion.setFont(Diseno.fuenteTitulosVentanas);
		getContentPane().add(labelInformacion, BorderLayout.NORTH);
		
		JPanel marcoContenido = new JPanel();
		marcoContenido.setOpaque(false);
		getContentPane().add(marcoContenido);
		GridBagLayout gbl_marcoContenido = new GridBagLayout();
		gbl_marcoContenido.columnWidths = new int[]{296, 296, 0};
		gbl_marcoContenido.rowHeights = new int[]{81, 81, 81, 0, 0};
		gbl_marcoContenido.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_marcoContenido.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		marcoContenido.setLayout(gbl_marcoContenido);
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setFont(Diseno.fuenteBotones);
		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.fill = GridBagConstraints.BOTH;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 0;
		gbc_labelNombre.gridy = 0;
		marcoContenido.add(labelNombre, gbc_labelNombre);
		
		textNombre = new JTextField();
		textNombre.setFont(Diseno.fuenteBotones);
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.fill = GridBagConstraints.BOTH;
		gbc_textNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 0;
		marcoContenido.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);
		
		JLabel labelContacto = new JLabel("N\u00FAmero de contacto:");
		labelContacto.setFont(Diseno.fuenteBotones);
		GridBagConstraints gbc_labelContacto = new GridBagConstraints();
		gbc_labelContacto.fill = GridBagConstraints.BOTH;
		gbc_labelContacto.insets = new Insets(0, 0, 5, 5);
		gbc_labelContacto.gridx = 0;
		gbc_labelContacto.gridy = 1;
		marcoContenido.add(labelContacto, gbc_labelContacto);
		
		textContacto = new JTextField();
		textContacto.setFont(Diseno.fuenteBotones);
		GridBagConstraints gbc_textContacto = new GridBagConstraints();
		gbc_textContacto.fill = GridBagConstraints.BOTH;
		gbc_textContacto.insets = new Insets(0, 0, 5, 0);
		gbc_textContacto.gridx = 1;
		gbc_textContacto.gridy = 1;
		marcoContenido.add(textContacto, gbc_textContacto);
		textContacto.setColumns(10);
		
		JLabel labelDireccion = new JLabel("Direcci\u00F3n:");
		labelDireccion.setFont(Diseno.fuenteBotones);
		GridBagConstraints gbc_labelDireccion = new GridBagConstraints();
		gbc_labelDireccion.fill = GridBagConstraints.BOTH;
		gbc_labelDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_labelDireccion.gridx = 0;
		gbc_labelDireccion.gridy = 2;
		marcoContenido.add(labelDireccion, gbc_labelDireccion);
		
		textDireccion = new JTextField();
		textDireccion.setFont(Diseno.fuenteBotones);
		GridBagConstraints gbc_textDireccion = new GridBagConstraints();
		gbc_textDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_textDireccion.fill = GridBagConstraints.BOTH;
		gbc_textDireccion.gridx = 1;
		gbc_textDireccion.gridy = 2;
		marcoContenido.add(textDireccion, gbc_textDireccion);
		textDireccion.setColumns(10);
		
		JPanel marcoTabla = new JPanel();
		marcoTabla.setOpaque(false);
		marcoTabla.setBorder(new TitledBorder(null,
				"Donaciones", TitledBorder.CENTER,
				TitledBorder.TOP, Diseno.fuenteBotones, new Color(0, 0, 0)));
		GridBagConstraints gbc_marcoTabla = new GridBagConstraints();
		gbc_marcoTabla.fill = GridBagConstraints.BOTH;
		gbc_marcoTabla.gridwidth = 2;
		gbc_marcoTabla.insets = new Insets(0, 0, 0, 5);
		gbc_marcoTabla.gridx = 0;
		gbc_marcoTabla.gridy = 3;
		marcoContenido.add(marcoTabla, gbc_marcoTabla);
		GridBagLayout gbl_marcoTabla = new GridBagLayout();
		gbl_marcoTabla.columnWidths = new int[]{296, 296, 0};
		gbl_marcoTabla.rowHeights = new int[]{0, 0};
		gbl_marcoTabla.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_marcoTabla.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		marcoTabla.setLayout(gbl_marcoTabla);
		
		JTable tablaDonaciones = new JTable();
		GridBagConstraints gbc_tablaDonaciones = new GridBagConstraints();
		gbc_tablaDonaciones.fill = GridBagConstraints.BOTH;
		gbc_tablaDonaciones.gridwidth = 2;
		gbc_tablaDonaciones.insets = new Insets(0, 0, 0, 5);
		gbc_tablaDonaciones.gridx = 0;
		gbc_tablaDonaciones.gridy = 0;
		marcoTabla.add(tablaDonaciones, gbc_tablaDonaciones);
		
		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelBotones.setOpaque(false);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		botonCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotones.add(botonCerrar);
	}
	
	public void setDatosIniciales(Organizacion pOrganizacion) {
		asociacion = pOrganizacion;
		textNombre.setText(asociacion.getNombre());
		textDireccion.setText(asociacion.getDireccion());
		textContacto.setText(asociacion.getNumeroContacto().toString());
	}
}
