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
	private final String[] calificaciones = new String[]{"5.0",
							"4.5", "4.0", "3.5", "3.0", "2.5",
							"2.0", "1.5", "1.0", "0.5", "0.0"};
	
	private JButton botonGuardarFecha;
	private JButton boton1Dia;
	private JButton boton7Dias;
	private JButton boton30Dias;
	private JButton botonGuardarCalificacion;
	private JPanel pestaniaFecha;
	private JCalendar calendar;
	private JPanel panelCalendar;
	private JButton botonColorFondoCambiar;
	private JPanel panelMensajeNuevo;
	private JTextArea mensajeCorreoNuevo;
	private JButton btnGuardarMensaje;
	private JButton botonColorFondoGuardar;
	private JPanel panelCalificacion;
	private JPanel panelColorFondo;
	
	private Color nuevoColorFondo;
	private JButton botonCerrar;
	private JTextField textFecha;
	private JComboBox<String> comboCalificaciones;
	
	public VentanaParametrosSistema() {
		setSize(435, 420);
		setResizable(false);
		setIconImage(Imagenes.getIconoSistema().getImage());
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel labelTitulo = new JLabel("Par\u00E1metros del Sistema");
		labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelTitulo.setFont(Diseno.fuenteTitulosVentanas);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTabbedPane marcoPestanias = new JTabbedPane(JTabbedPane.TOP);
		marcoPestanias.setBorder(new EmptyBorder(0, 0, 5, 0));
		marcoPestanias.setBackground(Diseno.fondoVentanas);
		marcoPestanias.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		JPanel pestaniaGeneral = new JPanel();
		pestaniaGeneral.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pestaniaGeneral.setOpaque(false);
		marcoPestanias.addTab("General", pestaniaGeneral);
		pestaniaGeneral.setLayout(new BoxLayout(pestaniaGeneral, BoxLayout.PAGE_AXIS));
		
		JButton botonCasos = new JButton("Cargar Casos de Prueba");
		botonCasos.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		botonCasos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CasosPrueba.cargarDocumentoMascotasPrueba();
					CasosPrueba.cargarDocumentoUsuariosPrueba();
					CasosPrueba.setCargados(true);
					botonCasos.setEnabled(false);
				} catch (TiempoSinEstablecerException ex) {
					JOptionPane.showMessageDialog(null,
						"No se pueden crear sucesos.\n" + ex.getMessage(),
						"Error del Tiempo del Sistema",
						JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonCasos.setFont(Diseno.fuenteBotones);
		botonCasos.setEnabled(!CasosPrueba.isCargadosAmbosCasos());
		pestaniaGeneral.add(botonCasos);
		
		JPanel panelFechaActual = new JPanel();
		pestaniaGeneral.add(panelFechaActual);
		panelFechaActual.setOpaque(false);
		panelFechaActual.setBorder(new TitledBorder(null, "Fecha Actual:",
			TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		panelFechaActual.setLayout(new BoxLayout(panelFechaActual, BoxLayout.Y_AXIS));
		
		textFecha = new JTextField();
		textFecha.setHorizontalAlignment(SwingConstants.CENTER);
		if (Tiempo.isFechaEstablecida()){
			textFecha.setText(Tiempo.getStringFechaSistema());
		} else {
			textFecha.setEnabled(false);
		}
		panelFechaActual.add(textFecha);
		
		boton1Dia = new JButton("Avanzar un D\u00EDa");
		boton1Dia.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelFechaActual.add(boton1Dia);
		boton1Dia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tiempo.avanzarDia();
				textFecha.setText(Tiempo.getStringFechaSistema());
			}
		});
		boton1Dia.setFont(Diseno.fuenteBotones);
		boton1Dia.setEnabled(Tiempo.isFechaEstablecida());
		
		boton7Dias = new JButton("Avanzar 7 D\u00EDas");
		boton7Dias.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelFechaActual.add(boton7Dias);
		boton7Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 7; i++) { Tiempo.avanzarDia(); }
				textFecha.setText(Tiempo.getStringFechaSistema());
			}
		});
		boton7Dias.setFont(Diseno.fuenteBotones);
		boton7Dias.setEnabled(Tiempo.isFechaEstablecida());
		
		boton30Dias = new JButton("Avanzar 30 D\u00EDas");
		boton30Dias.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelFechaActual.add(boton30Dias);
		boton30Dias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 30; i++) { Tiempo.avanzarDia(); }
				textFecha.setText(Tiempo.getStringFechaSistema());
			}
		});
		boton30Dias.setFont(Diseno.fuenteBotones);
		boton30Dias.setEnabled(Tiempo.isFechaEstablecida());
		
		panelCalificacion = new JPanel();
		pestaniaGeneral.add(panelCalificacion);
		panelCalificacion.setOpaque(false);
		panelCalificacion.setBorder(new TitledBorder(null, "Bloquear Usuarios con Nota Menor a:",
			TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		panelCalificacion.setLayout(new BorderLayout(0, 0));
		
		comboCalificaciones = new JComboBox<String>();
		comboCalificaciones.setFont(Diseno.fuenteBotones);
		comboCalificaciones.setModel(new DefaultComboBoxModel<String>(calificaciones));
		panelCalificacion.add(comboCalificaciones, BorderLayout.CENTER);
		
		botonGuardarCalificacion = new JButton("Guardar");
		botonGuardarCalificacion.setFont(Diseno.fuenteBotones);
		botonGuardarCalificacion.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonGuardarCalificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario.setCalificacionMinimaPermitidaUsuarios(
					Double.valueOf((String) comboCalificaciones.getSelectedItem()));
			}
		});
		panelCalificacion.add(botonGuardarCalificacion, BorderLayout.EAST);
		
		panelColorFondo = new JPanel();
		pestaniaGeneral.add(panelColorFondo);
		panelColorFondo.setOpaque(false);
		panelColorFondo.setBorder(new TitledBorder(null, "Color de Fondo:",
			TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		
		botonColorFondoCambiar = new JButton("Cambiar");
		botonColorFondoCambiar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonColorFondoCambiar.setFont(Diseno.fuenteBotones);
		botonColorFondoCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nuevoColorFondo = JColorChooser.showDialog(getContentPane(), "Seleccione un Color: ", Diseno.fondoVentanas);				
			}
		});
		panelColorFondo.setLayout(new BoxLayout(panelColorFondo, BoxLayout.X_AXIS));
		panelColorFondo.add(botonColorFondoCambiar);
		
		botonColorFondoGuardar = new JButton("Guardar");
		botonColorFondoGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
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
		
		botonCerrar = new JButton("Cerrar");
		botonCerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		botonCerrar.setOpaque(false);
		botonCerrar.setFont(Diseno.fuenteBotones);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().add(labelTitulo);
		getContentPane().add(marcoPestanias);
		
		panelMensajeNuevo = new JPanel();
		marcoPestanias.addTab("Correos", null, panelMensajeNuevo, null);
		panelMensajeNuevo.setOpaque(false);
		panelMensajeNuevo.setBorder(new TitledBorder(null, "Mensaje del Correo de Coincidencias:",
				TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		panelMensajeNuevo.setLayout(new BoxLayout(panelMensajeNuevo, BoxLayout.Y_AXIS));
		
		mensajeCorreoNuevo = new JTextArea(Correo.getMensaje());
		mensajeCorreoNuevo.setWrapStyleWord(true);
		mensajeCorreoNuevo.setLineWrap(true);
		panelMensajeNuevo.add(mensajeCorreoNuevo);
		
		btnGuardarMensaje = new JButton("Guardar Mensaje");
		btnGuardarMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGuardarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Correo.setMensaje(mensajeCorreoNuevo.getText());
				JOptionPane.showMessageDialog(getContentPane(), "Se guardó el mensaje satisfactoriamente.");
			}
		});
		btnGuardarMensaje.setFont(Diseno.fuenteBotones);
		panelMensajeNuevo.add(btnGuardarMensaje);
		getContentPane().add(botonCerrar);
	}	
}





