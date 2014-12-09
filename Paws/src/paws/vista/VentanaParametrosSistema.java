package paws.vista;

import com.toedter.calendar.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import paws.control.*;
import paws.control.excepciones.*;
import paws.modelo.*;
import paws.recursos.*;

@SuppressWarnings("serial")
public class VentanaParametrosSistema extends JFrame {
	private static String[] calificaciones = new String[]{"5.0",
							"4.5", "4.0", "3.5", "3.0", "2.5",
							"2.0", "1.5", "1.0", "0.5", "0.0"};
	
	private JButton botonGuardarFecha;
	private JComboBox<String> comboCalificaciones;
	private JButton boton1Dia;
	private JButton boton7Dias;
	private JButton boton30Dias;
	private JButton botonCalificacion;
	private JLabel labelTitulo;
	private JPanel marcoInferior;
	private JPanel pestaniaFecha;
	private JCalendar calendar;
	private JPanel pestaniaGeneral;
	private JPanel panelCalendar;
	private JTabbedPane marcoPestanias;
	private JButton botonCasos;
	private JButton botonColorFondoCambiar;
	private JTextField textFecha;
	private JPanel panelMensajeNuevo;
	private JTextArea mensajeCorreoNuevo;
	private JButton btnGuardarMensaje;
	private JButton botonColorFondoGuardar;
	private JPanel panelCalificacion;
	private JPanel panelSecundario;
	private JPanel panelColorFondo;
	private JPanel panelFechaActual;
	
	private Color nuevoColorFondo;
	
	public VentanaParametrosSistema() {
		setSize(700, 450);
		setIconImage(Imagenes.getIconoSistema().getImage());
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		labelTitulo = new JLabel("Par\u00E1metros del Sistema");
		labelTitulo.setFont(Diseno.fuenteTitulosVentanas);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelTitulo, BorderLayout.NORTH);
		
		marcoPestanias = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(marcoPestanias, BorderLayout.CENTER);
		
		pestaniaGeneral = new JPanel();
		marcoPestanias.addTab("General", null, pestaniaGeneral, null);
		pestaniaGeneral.setAlignmentX(Component.RIGHT_ALIGNMENT);
		pestaniaGeneral.setOpaque(false);
		pestaniaGeneral.setLayout(new BorderLayout(20, 0));
		
		panelSecundario = new JPanel();
		panelSecundario.setOpaque(false);
		pestaniaGeneral.add(panelSecundario, BorderLayout.WEST);
		panelSecundario.setLayout(new BoxLayout(panelSecundario, BoxLayout.Y_AXIS));
		
		botonCasos = new JButton("Cargar Casos de Prueba");
		botonCasos.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonCasos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CasosPrueba.cargarDocumentoMascotasPrueba();
					CasosPrueba.cargarDocumentoUsuariosPrueba();
				} catch (TiempoSinEstablecerException ex) {
					JOptionPane.showMessageDialog(null,
						"No se pueden crear sucesos.\n" + ex.getMessage(),
						"Error del Tiempo del Sistema",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonCasos.setFont(Diseno.fuenteBotones);
		panelSecundario.add(botonCasos);
		
		panelFechaActual = new JPanel();
		panelSecundario.add(panelFechaActual);
		panelFechaActual.setOpaque(false);
		panelFechaActual.setBorder(new TitledBorder(null, "Fecha Actual:",
				TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		panelFechaActual.setLayout(new BoxLayout(panelFechaActual, BoxLayout.Y_AXIS));
		
		textFecha = new JTextField();
		textFecha.setEnabled(Tiempo.isFechaEstablecida());
		textFecha.setEditable(false);
		textFecha.setColumns(10);
		textFecha.setFont(Diseno.fuenteBotones);
		panelFechaActual.add(textFecha);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setOpaque(false);
		panelFechaActual.add(panelBotones);
		
		boton1Dia = new JButton("Avanzar un D\u00EDa");
		panelBotones.add(boton1Dia);
		boton1Dia.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		boton1Dia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tiempo.avanzarDia();
				textFecha.setText(Tiempo.getStringFechaSistema());
				textFecha.setEnabled(true);
			}
		});
		boton1Dia.setOpaque(false);
		boton1Dia.setFont(Diseno.fuenteBotones);
		boton1Dia.setEnabled(Tiempo.isFechaEstablecida());
		
		boton7Dias = new JButton("Avanzar 7 D\u00EDas");
		panelBotones.add(boton7Dias);
		boton7Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 7; i++) { Tiempo.avanzarDia(); }
				textFecha.setText(Tiempo.getStringFechaSistema());
				textFecha.setEnabled(true);
			}
		});
		boton7Dias.setOpaque(false);
		boton7Dias.setFont(Diseno.fuenteBotones);
		boton7Dias.setEnabled(Tiempo.isFechaEstablecida());
		boton7Dias.setAlignmentY(1.0f);
		
		boton30Dias = new JButton("Avanzar 30 D\u00EDas");
		panelBotones.add(boton30Dias);
		boton30Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 30; i++) { Tiempo.avanzarDia(); }
				textFecha.setText(Tiempo.getStringFechaSistema());
				textFecha.setEnabled(true);
			}
		});
		boton30Dias.setOpaque(false);
		boton30Dias.setFont(Diseno.fuenteBotones);
		boton30Dias.setEnabled(Tiempo.isFechaEstablecida());
		boton30Dias.setAlignmentY(1.0f);
		
		panelCalificacion = new JPanel();
		panelSecundario.add(panelCalificacion);
		panelCalificacion.setOpaque(false);
		panelCalificacion.setBorder(new TitledBorder(null, "Calificaci\u00F3n M\u00EDnima para bloquear a un Usuario:",
			TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		
		comboCalificaciones = new JComboBox<String>();
		panelCalificacion.add(comboCalificaciones);
		comboCalificaciones.setModel(new DefaultComboBoxModel<String>(calificaciones));
		
		botonCalificacion = new JButton("Guardar");
		panelCalificacion.add(botonCalificacion);
		botonCalificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario.setCalificacionMinimaPermitidaUsuarios(
						Double.valueOf((String) comboCalificaciones.getSelectedItem()));
			}
		});
		botonCalificacion.setOpaque(false);
		botonCalificacion.setFont(Diseno.fuenteBotones);
		
		panelColorFondo = new JPanel();
		panelSecundario.add(panelColorFondo);
		panelColorFondo.setOpaque(false);
		panelColorFondo.setBorder(new TitledBorder(null, "Color de Fondo de las Ventanas:",
			TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		
		botonColorFondoCambiar = new JButton("Cambiar");
		botonColorFondoCambiar.setFont(Diseno.fuenteBotones);
		botonColorFondoCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoColorFondo = JColorChooser.showDialog(getContentPane(), "Seleccione un Color: ", Diseno.fondoVentanas);				
			}
		});
		panelColorFondo.add(botonColorFondoCambiar);
		
		botonColorFondoGuardar = new JButton("Guardar");
		botonColorFondoGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Diseno.fondoVentanas = nuevoColorFondo;
				JOptionPane.showMessageDialog(getContentPane(),
					"A partir de ahora, las ventanas se desplegarán\n" +
					"con el color de fondo seleccionado.");
			}
		});
		botonColorFondoGuardar.setFont(Diseno.fuenteBotones);
		panelColorFondo.add(botonColorFondoGuardar);
		
		panelMensajeNuevo = new JPanel();
		panelMensajeNuevo.setOpaque(false);
		panelMensajeNuevo.setBorder(new TitledBorder(null, "Mensaje del Correo de Coincidencias:",
				TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		panelMensajeNuevo.setLayout(new BorderLayout(0, 0));
		pestaniaGeneral.add(panelMensajeNuevo, BorderLayout.CENTER);
		
		mensajeCorreoNuevo = new JTextArea(Correo.getMensaje());
		mensajeCorreoNuevo.setLineWrap(true);
		panelMensajeNuevo.add(mensajeCorreoNuevo);
		mensajeCorreoNuevo.setColumns(10);
		
		btnGuardarMensaje = new JButton("Guardar Mensaje");
		btnGuardarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Correo.setMensaje(mensajeCorreoNuevo.getText());
				JOptionPane.showMessageDialog(getContentPane(), "Se guardó el mensaje satisfactoriamente.");
			}
		});
		btnGuardarMensaje.setFont(Diseno.fuenteBotones);
		panelMensajeNuevo.add(btnGuardarMensaje, BorderLayout.SOUTH);
		
		if (!Tiempo.isFechaEstablecida()) { 
			pestaniaFecha = new JPanel();
			pestaniaFecha.setOpaque(false);
			pestaniaFecha.setLayout(new BoxLayout(pestaniaFecha, BoxLayout.PAGE_AXIS));
			marcoPestanias.addTab("Fecha Actual de Producci\u00F3n", null, pestaniaFecha, null);
			
			panelCalendar = new JPanel();
			panelCalendar.setOpaque(false);
			panelCalendar.setLayout(new BoxLayout(panelCalendar, BoxLayout.X_AXIS));
			pestaniaFecha.add(panelCalendar);
			
			calendar = new JCalendar();
			calendar.setBorder(new TitledBorder(null, "Seleccione la fecha de inicio: ",
				TitledBorder.LEADING, TitledBorder.TOP, Diseno.fuenteBotones, new Color(0, 0, 0)));
			calendar.setOpaque(false);
			calendar.getDayChooser().setOpaque(false);
			calendar.getYearChooser().setOpaque(false);
			calendar.getMonthChooser().setOpaque(false);
			calendar.getDayChooser().getDayPanel().setOpaque(false);
			calendar.setBackground(Diseno.fondoVentanas);
			calendar.getMonthChooser().setBackground(Diseno.fondoVentanas);
			calendar.getMonthChooser().getSpinner().setBackground(Diseno.fondoVentanas);
			calendar.getMonthChooser().getComboBox().setBackground(Diseno.fondoVentanas);
			calendar.getYearChooser().getSpinner().setBackground(Diseno.fondoVentanas);
			calendar.getDayChooser().setBackground(Diseno.fondoVentanas);
			calendar.getDayChooser().setDecorationBackgroundColor(Diseno.fondoVentanas);
			calendar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			calendar.getDayChooser().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			calendar.getYearChooser().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			calendar.getMonthChooser().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			calendar.setEnabled(!Tiempo.isFechaEstablecida());
			panelCalendar.add(calendar);
			
			botonGuardarFecha = new JButton("Guardar Fecha");
			calendar.add(botonGuardarFecha, BorderLayout.SOUTH);
			botonGuardarFecha.setAlignmentY(Component.BOTTOM_ALIGNMENT);
			botonGuardarFecha.setAlignmentX(Component.RIGHT_ALIGNMENT);
			botonGuardarFecha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Tiempo.setFechaInicioProduccion(calendar.getCalendar());
					calendar.setEnabled(!Tiempo.isFechaEstablecida());
					botonGuardarFecha.setEnabled(!Tiempo.isFechaEstablecida());
					boton1Dia.setEnabled(Tiempo.isFechaEstablecida());
					boton7Dias.setEnabled(Tiempo.isFechaEstablecida());
					boton30Dias.setEnabled(Tiempo.isFechaEstablecida());
					textFecha.setEnabled(Tiempo.isFechaEstablecida());
					textFecha.setText(Tiempo.getStringFechaSistema());
				}
			});
			botonGuardarFecha.setOpaque(false);
			botonGuardarFecha.setEnabled(!Tiempo.isFechaEstablecida());
			if (Tiempo.isFechaEstablecida()) {
				textFecha.setText(Tiempo.getStringFechaSistema());
			}
		}
		
		marcoInferior = new JPanel();
		marcoInferior.setOpaque(false);
		getContentPane().add(marcoInferior, BorderLayout.SOUTH);
		
		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botonCerrar.setOpaque(false);
		botonCerrar.setFont(Diseno.fuenteBotones);
		marcoInferior.add(botonCerrar);
	}	
}





