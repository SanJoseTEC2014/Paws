package paws.vista;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.MaskFormatter;
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
	private JLabel lblCorreo;
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
		botonLeerCondicionesUso.setAlignmentX(Component.CENTER_ALIGNMENT);
		marcoOperaciones.add(botonLeerCondicionesUso);
		
		checkBoxAceptarCondicionesUso = new JCheckBox("Aceptar Condiciones de Uso");
		checkBoxAceptarCondicionesUso.setEnabled(false);
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
		marcoOperaciones.add(checkBoxAceptarCondicionesUso);
		checkBoxAceptarCondicionesUso.setOpaque(false);
		
		marcoBotones = new JPanel();
		marcoBotones.setOpaque(false);
		marcoOperaciones.add(marcoBotones);
		
		botonRegistrar = new JButton("Registrar");
		botonRegistrar.setOpaque(false);
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.usuarios.add(
					new Usuario(
						nicknameTextBox.getText(),
						nombreTextBox.getText(),
						apellidosTextBox.getText(),
						Integer.parseInt(cedulaTextBox.getText()),
						new String(passwordTextBox.getPassword()),
						Integer.parseInt(telefonoTextBox.getText()),
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
		marcoCampos.setBorder(new TitledBorder(null, "Ingrese sus Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		marcoCampos.setOpaque(false);
		marcoCampos.setLayout(new BoxLayout(marcoCampos, BoxLayout.PAGE_AXIS));
		
		lblNombre = new JLabel("Nombre");
		marcoCampos.add(lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		nombreTextBox = new JTextField();
		nombreTextBox.setColumns(10);
		marcoCampos.add(nombreTextBox);
		nombreTextBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblApellidos = new JLabel("Apellidos");
		marcoCampos.add(lblApellidos);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		apellidosTextBox = new JTextField();
		marcoCampos.add(apellidosTextBox);
		apellidosTextBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		apellidosTextBox.setColumns(10);
		
		lblCedula = new JLabel("C\u00E9dula");
		marcoCampos.add(lblCedula);
		lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		MaskFormatter formatoCedula = null;
		try {
			formatoCedula = new MaskFormatter("#########");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cedulaTextBox = new JFormattedTextField(formatoCedula);
		marcoCampos.add(cedulaTextBox);
		cedulaTextBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cedulaTextBox.setColumns(10);
		
		lblNickname = new JLabel("Nickname");
		marcoCampos.add(lblNickname);
		lblNickname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		nicknameTextBox = new JTextField();
		marcoCampos.add(nicknameTextBox);
		nicknameTextBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nicknameTextBox.setColumns(10);
		
		lblContrasenia = new JLabel("Contrase\u00F1a");
		marcoCampos.add(lblContrasenia);
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		passwordTextBox = new JPasswordField();
		marcoCampos.add(passwordTextBox);
		passwordTextBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lblTelefono = new JLabel("Tel\u00E9fono");
		marcoCampos.add(lblTelefono);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		MaskFormatter formatoTelefono = null;
		try {
			formatoTelefono = new MaskFormatter("########");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		telefonoTextBox = new JFormattedTextField(formatoTelefono);
		marcoCampos.add(telefonoTextBox);
		telefonoTextBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		telefonoTextBox.setColumns(10);
		
		lblCorreo = new JLabel("Correo");
		marcoCampos.add(lblCorreo);
		lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		correoTextBox = new JTextField();
		marcoCampos.add(correoTextBox);
		correoTextBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		correoTextBox.setColumns(10);
		getContentPane().add(marcoOperaciones, BorderLayout.SOUTH);
		
		getContentPane().add(marcoCampos);
		
		lblDireccion = new JLabel("Direcci\u00F3n");
		marcoCampos.add(lblDireccion);
		
		direccionTextBox = new JTextField();
		direccionTextBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		direccionTextBox.setColumns(10);
		marcoCampos.add(direccionTextBox);
	}
	
}

