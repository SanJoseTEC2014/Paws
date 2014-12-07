package paws.vista;

import javax.swing.*;

import paws.control.*;
import paws.control.excepciones.*;
import paws.recursos.Diseno;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.swing.border.*;

@SuppressWarnings("serial")
public class VentanaInicioSesion extends JFrame {
	
	// Atributos
	private Integer anchoVentana;
	private Integer altoVentana;
	private JTextField nicknameTextBox;
	private JPasswordField passwordTextBox;
	private JLabel labelPasswordError;
	private JLabel labelNicknameError;
	private JPanel marcoLogotipo;
	private JButton botonRegistrarNuevoUsuario;
	private JButton botonInicioSesion;
	private JPanel marcoContenido;
	private JLabel labelLogotipo;
	private JButton botonSalir;
	private JPanel panelNickname;
	private JPanel panelPassword;
	private JPanel panelBotones;

	public VentanaInicioSesion() {
		anchoVentana = 400;
		altoVentana = 600;
		setTitle("Inicio");
		setIconImage(Imagenes.getIconoSistema().getImage());
		setSize(anchoVentana, altoVentana);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setSize(anchoVentana, altoVentana);
		getContentPane().setBackground(Diseno.fondoVentanas);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		marcoLogotipo = new JPanel();
		marcoLogotipo.setBackground(Diseno.fondoVentanas);
		marcoLogotipo.setLayout(new BorderLayout(0, 0));
		
		labelLogotipo = new JLabel("");
		labelLogotipo.setHorizontalAlignment(SwingConstants.CENTER);
		marcoLogotipo.add(labelLogotipo);
		
		marcoContenido = new JPanel();
		marcoContenido.setBackground(Diseno.fondoVentanas);
		marcoContenido.setLayout(new BoxLayout(marcoContenido, BoxLayout.Y_AXIS));
		
		panelNickname = new JPanel();
		marcoContenido.add(panelNickname);
		panelNickname.setOpaque(false);
		panelNickname.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ingrese su Nickname:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelNickname.setLayout(new BorderLayout(0, 0));
		
		nicknameTextBox = new JTextField();
		panelNickname.add(nicknameTextBox, BorderLayout.CENTER);
		nicknameTextBox.setFont(new Font("Calibri", Font.PLAIN, 20));
		
		labelNicknameError = new JLabel("");
		panelNickname.add(labelNicknameError, BorderLayout.SOUTH);
		
		panelPassword = new JPanel();
		marcoContenido.add(panelPassword);
		panelPassword.setOpaque(false);
		panelPassword.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ingrese su contrase\u00F1a:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPassword.setLayout(new BorderLayout(0, 0));
		
		passwordTextBox = new JPasswordField();
		panelPassword.add(passwordTextBox, BorderLayout.CENTER);
		passwordTextBox.setFont(new Font("Calibri", Font.PLAIN, 20));
		passwordTextBox.setHorizontalAlignment(SwingConstants.LEFT);
		
		labelPasswordError = new JLabel("");
		panelPassword.add(labelPasswordError, BorderLayout.SOUTH);
		
		panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		
		botonInicioSesion = new JButton("Ingresar");
		botonInicioSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Acceso.validarCredenciales(nicknameTextBox.getText(),
							new String(passwordTextBox.getPassword()));
					Principal.coordinador.ocultarInicioSesion();
					Principal.coordinador.mostrarMenuPrincipal();
				} catch (UsuarioNoExisteException e) {
					labelNicknameError.setText(e.getMessage());
				} catch (ContraseniaIncorrectaException e) {
					labelPasswordError.setText(e.getMessage());
				} catch (TiempoSinEstablecerException e) {
					JOptionPane.showMessageDialog(getContentPane(),
					e.getMessage(), "Error al Ingresar.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonInicioSesion.setFont(Diseno.fuenteBotones);
		botonInicioSesion.setOpaque(false);
		panelBotones.add(botonInicioSesion);
		
		botonRegistrarNuevoUsuario = new JButton("Registrarse");
		botonRegistrarNuevoUsuario.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			VentanaRegistroUsuarios window = new VentanaRegistroUsuarios();
			window.setVisible(true);	
			}
		});
		botonRegistrarNuevoUsuario.setFont(Diseno.fuenteBotones);
		botonRegistrarNuevoUsuario.setOpaque(false);
		panelBotones.add(botonRegistrarNuevoUsuario);
		
		botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				System.exit(0);
			}
		});
		botonSalir.setOpaque(false);
		botonSalir.setFont(Diseno.fuenteBotones);
		panelBotones.add(botonSalir);
		
		marcoContenido.add(panelBotones);
		getContentPane().add(marcoContenido, BorderLayout.SOUTH);
		getContentPane().add(marcoLogotipo, BorderLayout.CENTER);
	}
	
	public void cargarLogo(){
		try {
			BufferedImage logo = Imagenes.getLogo1();
			Dimension nuevoTamanio = Imagenes.getNuevaDimension(
					anchoVentana - 2,
					altoVentana - marcoContenido.getHeight(),
					logo.getSampleModel().getWidth(),
					logo.getSampleModel().getHeight());
			labelLogotipo.setIcon(new ImageIcon(Imagenes.redimensionar(logo, nuevoTamanio.width, nuevoTamanio.height)));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(),
				"Logotipo no encontrado.", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void limpiarCampos() {
		nicknameTextBox.setText("");
		labelNicknameError.setText("");
		passwordTextBox.setText("");
		labelPasswordError.setText("");
	}
}