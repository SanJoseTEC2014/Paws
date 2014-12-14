package paws.vista;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import javax.mail.MessagingException;

import paws.control.Correo;
import paws.control.Principal;
import paws.control.Registro;
import paws.modelo.Usuario;
import paws.recursos.Dialogos;
import paws.recursos.Diseno;

@SuppressWarnings("serial")
public class VentanaRegistroUsuarios extends JFrame {
	private JTextField apellidosTextBox;
	private JButton botonCancelar;
	private JButton botonLeerCondicionesUso;
	private JButton botonRegistrar;
	private JButton botonValidacionCampos;
	private JFormattedTextField cedulaTextBox;
	private JCheckBox checkBoxAceptarCondicionesUso;
	private JTextField correoTextBox;
	private JTextField direccionTextBox;
	private JLabel lblApellidos;
	private JLabel lblCedula;
	private JLabel lblContrasenia;
	private JLabel labelCorreo;
	private JLabel lblDireccion;
	private JLabel lblNickname;
	private JLabel lblNombre;
	private JLabel labelRegistrarse;
	private JLabel lblTelefono;
	private JPanel marcoBotones;
	private JPanel marcoCampos;
	private JPanel marcoOperaciones;
	private JTextField nicknameTextBox;
	private JTextField nombreTextBox;
	private JPasswordField passwordTextBox;
	private JFormattedTextField telefonoTextBox;
	
	public VentanaRegistroUsuarios() {
		getContentPane().setFont(new Font("Tahoma", Font.BOLD, 13));
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Registro de Usuarios");
		setSize(443,557);
		
		labelRegistrarse = new JLabel("Registrarse como Nuevo Usuario");
		labelRegistrarse.setHorizontalAlignment(SwingConstants.CENTER);
		labelRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelRegistrarse.setFont(Diseno.fuenteTitulosVentanas);
		
		marcoOperaciones = new JPanel();
		marcoOperaciones.setOpaque(false);
		marcoOperaciones.setLayout(new BoxLayout(marcoOperaciones, BoxLayout.Y_AXIS));
		
		botonValidacionCampos = new JButton("Verificar Campos Rellenados");
		botonValidacionCampos.setFont(Diseno.fuenteBotones);
		botonValidacionCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean hayError = false;
				
				LinkedList<String> camposPorValidar = new LinkedList<String>(); 
				camposPorValidar.add(nicknameTextBox.getText()); 
				camposPorValidar.add(nombreTextBox.getText());
				camposPorValidar.add(apellidosTextBox.getText());
				camposPorValidar.add(cedulaTextBox.getText());
				camposPorValidar.add(String.valueOf(passwordTextBox.getPassword())); 
				camposPorValidar.add(telefonoTextBox.getText());
				camposPorValidar.add(correoTextBox.getText());
				camposPorValidar.add(direccionTextBox.getText());
				
				if (Registro.isNicknameRepetido(nicknameTextBox.getText())){
					JOptionPane.showMessageDialog(getContentPane(),
						"Ya existe un usuario registrado con ese nickname.\nElija otro antes de continuar.",
						"Nickname repetido", JOptionPane.ERROR_MESSAGE);
					hayError = true;
				}
				if (Registro.isNicknameRestringido(nicknameTextBox.getText())){
					JOptionPane.showMessageDialog(getContentPane(),
						"El nickname que ha escogido no está permitido.\nElija otro antes de continuar.",
						"Nickname no permitido", JOptionPane.ERROR_MESSAGE);
					hayError = true;
				}
				if (Registro.isCorreoInvalido(correoTextBox.getText())){
					JOptionPane.showMessageDialog(getContentPane(),
							"La dirección está mal escrita.\nEscríbala correctamente antes de continuar.",
							"Correo inválido", JOptionPane.ERROR_MESSAGE);
					hayError = true;
				}
				if (Registro.hayCamposVacios(camposPorValidar)){
					JOptionPane.showMessageDialog(getContentPane(),
						"Faltan campos por llenar. Debe llenarlos todos antes de continuar.",
						"Campos vacíos", JOptionPane.ERROR_MESSAGE);
					hayError = true;
				}
				if (!hayError){
					botonLeerCondicionesUso.setEnabled(true);
				}
			}
		});
		botonValidacionCampos.setAlignmentY(Component.TOP_ALIGNMENT);
		botonValidacionCampos.setAlignmentX(Component.CENTER_ALIGNMENT);
		marcoOperaciones.add(botonValidacionCampos);
		
		botonLeerCondicionesUso = new JButton("Leer Condiciones de Uso");
		botonLeerCondicionesUso.setEnabled(false);
		botonLeerCondicionesUso.setOpaque(false);
		botonLeerCondicionesUso.setFont(Diseno.fuenteBotones);
		botonLeerCondicionesUso.setAlignmentX(Component.CENTER_ALIGNMENT);
		marcoOperaciones.add(botonLeerCondicionesUso);
		
		checkBoxAceptarCondicionesUso = new JCheckBox("Aceptar Condiciones de Uso");
		checkBoxAceptarCondicionesUso.setEnabled(false);
		checkBoxAceptarCondicionesUso.setFont(Diseno.fuenteBotones);
		checkBoxAceptarCondicionesUso.addActionListener(new ActionListener() {
			private void abrirVentanaValidacion(String msg) {
				String codigo = JOptionPane.showInputDialog(getContentPane(),
						"Ingrese el código enviado a su correo:",
						msg, JOptionPane.INFORMATION_MESSAGE);
				
				if (codigo != null) { // Si el usuario no canceló la ventana de diálogo.
					if (codigo.equals(String.valueOf(nicknameTextBox.getText().hashCode()))) {
						botonRegistrar.setEnabled(true);
					} else {
						abrirVentanaValidacion("Código incorrecto");
					}
				}
			}

			public void actionPerformed(ActionEvent arg0) {
				if (checkBoxAceptarCondicionesUso.isSelected())
				{
					try {
						Correo.enviarCodigoCorreo(nicknameTextBox.getText(), nombreTextBox.getText(), correoTextBox.getText());
						abrirVentanaValidacion("Verificación de código");
					} catch (MessagingException e) {
						JOptionPane.showMessageDialog(getContentPane(),
							e.getMessage(),
							"Hubo un problema al enviar su correo.",
							JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		checkBoxAceptarCondicionesUso.setAlignmentX(Component.CENTER_ALIGNMENT);
		checkBoxAceptarCondicionesUso.setOpaque(false);
		marcoOperaciones.add(checkBoxAceptarCondicionesUso);
		
		marcoBotones = new JPanel();
		marcoBotones.setOpaque(false);
		marcoOperaciones.add(marcoBotones);
		
		botonRegistrar = new JButton("Registrar");
		botonRegistrar.setOpaque(false);
		botonRegistrar.setFont(Diseno.fuenteBotones);
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.usuarios.add(
					new Usuario(
						nicknameTextBox.getText(),
						nombreTextBox.getText(),
						apellidosTextBox.getText(),
						Integer.parseInt(cedulaTextBox.getText().replaceAll("-", "")),
						new String(passwordTextBox.getPassword()),
						Integer.parseInt(telefonoTextBox.getText().replaceAll("-", "")),
						correoTextBox.getText(),
						direccionTextBox.getText()
						)
					);
				JOptionPane.showMessageDialog(getContentPane(),
						"Su cuenta ha sido registrada satisfactoriamente",
						"Registro finalizado",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		marcoBotones.add(botonRegistrar);
		botonRegistrar.setEnabled(false);
		
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		botonCancelar.setOpaque(false);
		botonCancelar.setFont(Diseno.fuenteBotones);
		botonCancelar.setAlignmentX(Component.RIGHT_ALIGNMENT);
		marcoBotones.add(botonCancelar);
		
		botonLeerCondicionesUso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JOptionPane.showMessageDialog(getContentPane(),
					Dialogos.getCondicionesUso(),
					"Condiciones de Uso", JOptionPane.INFORMATION_MESSAGE);
					checkBoxAceptarCondicionesUso.setEnabled(true);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(getContentPane(),
					"No se encuentra el documento con las condiciones de uso. \n" + 
					"Por favor contacte al equipo de desarrollo PAWS al       \n" +
					"siguiente correo para recibir asistencia: pawsconsultas@gmail.com",
					"Error inesperado del sistema.", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(labelRegistrarse, BorderLayout.NORTH);
		
		marcoCampos = new JPanel();
		marcoCampos.setBorder(new TitledBorder(null, "Ingrese sus Datos",
			TitledBorder.LEADING, TitledBorder.TOP, Diseno.fuenteBotones, new Color(0,0,0)));
		marcoCampos.setOpaque(false);
		marcoCampos.setLayout(new BoxLayout(marcoCampos, BoxLayout.PAGE_AXIS));
		
		lblNombre = new JLabel("Nombre");
		marcoCampos.add(lblNombre);
		lblNombre.setFont(Diseno.fuenteBotones);
		
		nombreTextBox = new JTextField();
		marcoCampos.add(nombreTextBox);
		nombreTextBox.setFont(Diseno.fuenteBotones);
		
		lblApellidos = new JLabel("Apellidos");
		marcoCampos.add(lblApellidos);
		lblApellidos.setFont(Diseno.fuenteBotones);
		
		apellidosTextBox = new JTextField();
		marcoCampos.add(apellidosTextBox);
		apellidosTextBox.setFont(Diseno.fuenteBotones);
		
		lblCedula = new JLabel("C\u00E9dula");
		marcoCampos.add(lblCedula);
		lblCedula.setFont(Diseno.fuenteBotones);
		
		try {
			MaskFormatter cedulaFormat = new MaskFormatter("#-####-####");
			cedulaFormat.setValidCharacters("1234567890");
			cedulaFormat.setPlaceholderCharacter('_');
			cedulaFormat.setAllowsInvalid(false);
			cedulaTextBox = new JFormattedTextField(cedulaFormat);
			cedulaTextBox.setFont(Diseno.fuenteBotones);
			marcoCampos.add(cedulaTextBox);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(getContentPane(),
				"Hubo un error inesperado. No se podrán procesar números de cédula.",
				"Error inesperado", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		
		lblNickname = new JLabel("Nickname");
		marcoCampos.add(lblNickname);
		lblNickname.setFont(Diseno.fuenteBotones);
		
		nicknameTextBox = new JTextField();
		marcoCampos.add(nicknameTextBox);
		nicknameTextBox.setFont(Diseno.fuenteBotones);
		
		lblContrasenia = new JLabel("Contrase\u00F1a");
		marcoCampos.add(lblContrasenia);
		lblContrasenia.setFont(Diseno.fuenteBotones);
		
		passwordTextBox = new JPasswordField();
		marcoCampos.add(passwordTextBox);
		passwordTextBox.setFont(Diseno.fuenteBotones);
		
		lblTelefono = new JLabel("Tel\u00E9fono");
		marcoCampos.add(lblTelefono);
		lblTelefono.setFont(Diseno.fuenteBotones);
		
		try {
			MaskFormatter telefonoFormat = new MaskFormatter("####-####");
			telefonoFormat.setValidCharacters("1234567890");
			telefonoFormat.setPlaceholderCharacter('_');
			telefonoFormat.setAllowsInvalid(false);
			telefonoTextBox = new JFormattedTextField(telefonoFormat);
			telefonoTextBox.setFont(Diseno.fuenteBotones);
			marcoCampos.add(telefonoTextBox);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(getContentPane(),
				"Hubo un error inesperado. No se podrán procesar números de teléfono.",
				"Error inesperado", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
		
		labelCorreo = new JLabel("Correo");
		marcoCampos.add(labelCorreo);
		labelCorreo.setFont(Diseno.fuenteBotones);
		
		correoTextBox = new JTextField();
		correoTextBox.setFont(Diseno.fuenteBotones);
		marcoCampos.add(correoTextBox);
		getContentPane().add(marcoOperaciones, BorderLayout.SOUTH);
		
		getContentPane().add(marcoCampos);
		
		lblDireccion = new JLabel("Direcci\u00F3n");
		lblDireccion.setFont(Diseno.fuenteBotones);
		marcoCampos.add(lblDireccion);
		
		direccionTextBox = new JTextField();
		direccionTextBox.setFont(Diseno.fuenteBotones);
		marcoCampos.add(direccionTextBox);
	}
	
}

