package paws.vista;

import java.awt.*;
import java.text.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;

import paws.control.Acceso;
import paws.control.Imagenes;
import paws.control.Principal;
import paws.modelo.*;
import paws.recursos.Diseno;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class VentanaDetallesOrganizacion extends JFrame {
	private JButton botonDetalles;
	private JButton botonSalir;
	private JFormattedTextField formatTelefono;
	private JLabel labelCorreo;
	private JLabel labelNombre;
	private JLabel labelDireccion;
	private JLabel labelContacto; 
	private JLabel labelTitulo1;
	private JLabel labelTitulo2;
	private JPanel marcoContenido;
	private JPanel marcoDetalles;
	private JPanel marcoOperaciones;
	private JPanel marcoTitulos;
	
	private JTextField textCorreo;
	private JTextField textPagina;
	private JTextField textNombre;
	private JTextField textDireccion;
	private Organizacion organizacionActual;

	public VentanaDetallesOrganizacion(){
		setSize(800,500);
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		marcoTitulos = new JPanel();
		marcoTitulos.setLayout(new BorderLayout(0, 0));
		marcoTitulos.setOpaque(false);
		getContentPane().add(marcoTitulos, BorderLayout.NORTH);
		
				labelTitulo1 = new JLabel("Información");
				labelTitulo1.setFont(Diseno.fuenteTitulosVentanas);
				labelTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
				labelTitulo1.setOpaque(false);
				marcoTitulos.add(labelTitulo1, BorderLayout.NORTH);
				
				labelTitulo2 = new JLabel("Asociaci\u00F3n");
				labelTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
				labelTitulo2.setFont(Diseno.fuenteTitulosVentanas);
				labelTitulo2.setOpaque(false);
				marcoTitulos.add(labelTitulo2, BorderLayout.SOUTH);
				
		marcoContenido = new JPanel();
		marcoContenido.setLayout(new BorderLayout(0, 0));
		marcoContenido.setOpaque(false);
		getContentPane().add(marcoContenido, BorderLayout.CENTER);
				
				marcoDetalles = new JPanel();
				marcoDetalles.setBorder(new TitledBorder(null, "Detalles:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				marcoDetalles.setLayout(new GridLayout(0, 2, 3, 3));
				marcoDetalles.setOpaque(false);
				marcoContenido.add(marcoDetalles, BorderLayout.CENTER);
				
						labelNombre = new JLabel("Nombre:");
						labelNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
						labelNombre.setHorizontalAlignment(SwingConstants.LEFT);
						labelNombre.setOpaque(false);
						marcoDetalles.add(labelNombre);
						
						textNombre = new JTextField();
						textNombre.setHorizontalAlignment(SwingConstants.CENTER);
						textNombre.setEditable(false);
						textNombre.setColumns(10);
						marcoDetalles.add(textNombre);
						
						labelDireccion = new JLabel("Direcci\u00F3n:");
						labelDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
						labelDireccion.setHorizontalAlignment(SwingConstants.LEFT);
						labelDireccion.setOpaque(false);
						marcoDetalles.add(labelDireccion);
						
						textDireccion = new JTextField();
						textDireccion.setHorizontalAlignment(SwingConstants.CENTER);
						textDireccion.setColumns(10);
						marcoDetalles.add(textDireccion);
						
						labelContacto = new JLabel("N\u00FAmero de Tel\u00E9fono:");
						labelContacto.setFont(new Font("Tahoma", Font.PLAIN, 12));
						labelContacto.setHorizontalAlignment(SwingConstants.LEFT);
						labelContacto.setOpaque(false);
						marcoDetalles.add(labelContacto);
						
						try {
							formatTelefono = new JFormattedTextField(new MaskFormatter("########"));
							formatTelefono.setHorizontalAlignment(SwingConstants.CENTER);
							marcoDetalles.add(formatTelefono);
						} catch (ParseException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
							marcoDetalles.add(new JLabel("Error inesperado en el sistema."));
						}
						
						labelCorreo = new JLabel("Correo Electr\u00F3nico:");
						labelCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
						labelCorreo.setHorizontalAlignment(SwingConstants.LEFT);
						labelCorreo.setOpaque(false);
						marcoDetalles.add(labelCorreo);
						
						textCorreo = new JTextField();
						textCorreo.setHorizontalAlignment(SwingConstants.CENTER);
						marcoDetalles.add(textCorreo);
						
						
				
		marcoOperaciones = new JPanel();
		marcoOperaciones.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		marcoOperaciones.setOpaque(false);
		getContentPane().add(marcoOperaciones, BorderLayout.SOUTH);
				
				botonDetalles = new JButton("Ver Donaciones realizadas");
				botonDetalles.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Principal.coordinador.mostrarDonaciones(organizacionActual);
					}
				});
				marcoOperaciones.add(botonDetalles);
				
				botonSalir = new JButton("Salir");
				botonSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				marcoOperaciones.add(botonSalir);
	}
	
	private void close(){
		setVisible(false);
	}
	
	public void setDatosIniciales(Organizacion pOrganizacion) {
		
		organizacionActual = pOrganizacion;
		textNombre.setText(organizacionActual.getNombre());
		textNombre.setEditable(false);
		textDireccion.setText(organizacionActual.getDireccion());
		textDireccion.setEditable(false);
		formatTelefono.setText(organizacionActual.getNumeroContacto().toString());
		formatTelefono.setEditable(false);
		textCorreo.setText(organizacionActual.getCorreo());
		textCorreo.setEditable(false);
		
	}
	
}

