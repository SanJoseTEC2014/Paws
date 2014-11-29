package paws.vista;

import javax.swing.*;

import paws.control.*;
import paws.control.excepciones.ImagenNoEncontradaException;
import paws.recursos.Diseno;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;


public class VentanaInicioSesion extends JFrame {
	
	// Atributos
	private JTextField nicknameTextBox;
	private JPasswordField passwordTextBox;
	private JLabel labelPasswordError;
	private JLabel labelUsuarioError;
	private JLabel labelNickname;
	private JLabel labelPassword;
	private JPanel marcoDatosIngresados;
	private JButton botonRegistrarNuevoUsuario;
	private JButton botonInicioSesion;
	private JPanel marcoBotones;
	private JLabel labelLogotipo;
	private ImageIcon imagenLogotipo;
	private JButton botonSalir;

	public VentanaInicioSesion() {
		setResizable(false);
		setTitle("Inicio");
		try {
			setIconImage(Imagenes.getIconoSistema());
		} catch (ImagenNoEncontradaException e2) {
		}
		
		setSize(500,446);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Diseno.fondoVentanas);
		getContentPane().setLayout(new BorderLayout(0, 0));
		try {
			imagenLogotipo = new ImageIcon(Imagenes.getLogo().getScaledInstance(240, 170, BufferedImage.SCALE_FAST));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(),
				"Error inesperado del sistema.", JOptionPane.ERROR_MESSAGE);
		}
		
		labelLogotipo = new JLabel("");
		labelLogotipo.setIcon(imagenLogotipo);
		labelLogotipo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelLogotipo, BorderLayout.NORTH);
		
		marcoDatosIngresados = new JPanel();
		marcoDatosIngresados.setBackground(Diseno.fondoVentanas);
		getContentPane().add(marcoDatosIngresados, BorderLayout.CENTER);
		marcoDatosIngresados.setLayout(null);
		
		labelNickname = new JLabel("Nickname:");
		labelNickname.setBounds(14, 40, 87, 26);
		marcoDatosIngresados.add(labelNickname);
		labelNickname.setFont(Diseno.fuenteTitulosVentanas.deriveFont(16f));
		
		nicknameTextBox = new JTextField();
		nicknameTextBox.setBounds(0, 69, 494, 46);
		nicknameTextBox.setFont(new Font("Calibri", Font.PLAIN, 20));
		marcoDatosIngresados.add(nicknameTextBox);
		
		labelUsuarioError = new JLabel("");
		labelUsuarioError.setBounds(14, 217, 0, 0);
		marcoDatosIngresados.add(labelUsuarioError);
		
		labelPassword = new JLabel("Contrase\u00F1a:");
		labelPassword.setBounds(14, 123, 101, 26);
		marcoDatosIngresados.add(labelPassword);
		labelPassword.setFont(Diseno.fuenteTitulosVentanas.deriveFont(17f));
		
		passwordTextBox = new JPasswordField();
		passwordTextBox.setBounds(0, 160, 494, 46);
		passwordTextBox.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordTextBox.setHorizontalAlignment(SwingConstants.LEFT);
		marcoDatosIngresados.add(passwordTextBox);
		
		labelPasswordError = new JLabel("");
		labelPasswordError.setBounds(14, 434, 0, 0);
		marcoDatosIngresados.add(labelPasswordError);
		
		marcoBotones = new JPanel();
		marcoBotones.setBackground(Diseno.fondoVentanas);
		getContentPane().add(marcoBotones, BorderLayout.SOUTH);
		
		botonInicioSesion = new JButton("Iniciar Sesi\u00F3n");
		marcoBotones.add(botonInicioSesion);
		botonInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Acceso.validarCredenciales(nicknameTextBox.getText(), new String(passwordTextBox.getPassword()));
					Principal.coordinador.ocultarInicioSesion();
					Principal.coordinador.mostrarMenuPrincipal();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error de Acceso", JOptionPane.ERROR_MESSAGE);
					labelUsuarioError.setText(e.getMessage());
				}
			}
		});
		botonInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 15));
		botonInicioSesion.setOpaque(false);
		
		botonRegistrarNuevoUsuario = new JButton("Registrar Nuevo Usuario");
		botonRegistrarNuevoUsuario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			VentanaRegistroUsuarios window = new VentanaRegistroUsuarios();
			window.setVisible(true);	
			}
		});
		
		marcoBotones.add(botonRegistrarNuevoUsuario);
		botonRegistrarNuevoUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		botonRegistrarNuevoUsuario.setOpaque(false);
		
		botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				System.exit(0);
			}
		});
		botonSalir.setOpaque(false);
		botonSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		marcoBotones.add(botonSalir);
	}
	
	private void cerrarVentana() {
		setVisible(false);
	}

	public void limpiarCampos() {
		nicknameTextBox.setText("");
		passwordTextBox.setText("");
	}
	
}