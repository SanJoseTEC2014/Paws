package paws.vista;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

import javax.swing.*;
import javax.swing.text.*;

import paws.recursos.Diseno;

@SuppressWarnings("serial")
public class VentanaRegistroOrganizaciones extends JFrame {
	private JPanel marcoBotones;
	private JButton botonGuardar;
	private JButton botonCancelar;
	private JLabel labelTitulo;
	private JLabel labelEspacioIzq;
	private JLabel labelEspacioDer;
	private JPanel marcoContenido;
	private JLabel labelNombre;
	private JTextField textNombre;
	private JLabel labelNumeroContacto;
	private JLabel labelDireccion;
	private JFormattedTextField textNumeroContacto;
	private JTextArea textDireccion;
	public VentanaRegistroOrganizaciones() {
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		marcoBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) marcoBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		marcoBotones.setOpaque(false);
		getContentPane().add(marcoBotones, BorderLayout.SOUTH);
		
		botonGuardar = new JButton("Guardar");
		marcoBotones.add(botonGuardar);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		marcoBotones.add(botonCancelar);
		
		labelTitulo = new JLabel("Registrar Nueva Organizaci\u00F3n");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(Diseno.fuenteTitulosVentanas);
		getContentPane().add(labelTitulo, BorderLayout.NORTH);
		
		labelEspacioIzq = new JLabel(" ");
		getContentPane().add(labelEspacioIzq, BorderLayout.WEST);
		
		labelEspacioDer = new JLabel(" ");
		getContentPane().add(labelEspacioDer, BorderLayout.EAST);
		
		marcoContenido = new JPanel();
		marcoContenido.setOpaque(false);
		getContentPane().add(marcoContenido, BorderLayout.CENTER);
		marcoContenido.setLayout(new GridLayout(3, 2, 0, 5));
		
		labelNombre = new JLabel("Nombre:");
		labelNombre.setFont(Diseno.fuenteBotones);
		labelNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		marcoContenido.add(labelNombre);
		
		textNombre = new JTextField();
		marcoContenido.add(textNombre);
		
		labelNumeroContacto = new JLabel("N\u00FAmero de Contacto:");
		labelNumeroContacto.setAlignmentX(Component.CENTER_ALIGNMENT);
		marcoContenido.add(labelNumeroContacto);
		
		try {
			MaskFormatter telefonoFormat = new MaskFormatter("####-####");
			telefonoFormat.setValidCharacters("123456789");
			telefonoFormat.setPlaceholderCharacter('_');
			telefonoFormat.setAllowsInvalid(false);
			textNumeroContacto = new JFormattedTextField(telefonoFormat);
			textNumeroContacto.setFont(Diseno.fuenteBotones);
			marcoContenido.add(textNumeroContacto);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(getContentPane(),
				"Hubo un error inesperado. No se podrán procesar números de teléfono.",
				"Error inesperado", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		
		labelDireccion = new JLabel("Direcci\u00F3n:");
		labelDireccion.setAlignmentX(Component.CENTER_ALIGNMENT);
		marcoContenido.add(labelDireccion);
		
		textDireccion = new JTextArea("");
		textDireccion.setWrapStyleWord(true);
		textDireccion.setLineWrap(true);
		marcoContenido.add(textDireccion);
	}

}
