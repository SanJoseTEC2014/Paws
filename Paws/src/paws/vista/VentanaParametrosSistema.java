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
	private JPanel panelFecha;
	private JCalendar calendar;
	private JPanel panelGeneral;
	private JPanel marcoBotonesFecha;
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
	private JPanel panelCasos;
	private JPanel panelColorFondo;
	private JPanel panelFechaActual;
	
	public VentanaParametrosSistema() {
		setSize(700, 500);
		setIconImage(Imagenes.getIconoSistema().getImage());
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		labelTitulo = new JLabel("Par\u00E1metros del Sistema");
		labelTitulo.setFont(Diseno.fuenteTitulosVentanas);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelTitulo, BorderLayout.NORTH);
		
		marcoPestanias = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(marcoPestanias, BorderLayout.CENTER);
		
		panelGeneral = new JPanel();
		marcoPestanias.addTab("General", null, panelGeneral, null);
		panelGeneral.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelGeneral.setOpaque(false);
		panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
		
		panelCalificacion = new JPanel();
		panelCalificacion.setOpaque(false);
		panelCalificacion.setBorder(new TitledBorder(null, "Calificaci\u00F3n M\u00EDnima para bloquear a un Usuario:",
			TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		panelGeneral.add(panelCalificacion);
		
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
		
		panelCasos = new JPanel();
		panelCasos.setOpaque(false);
		panelCasos.setBorder(new TitledBorder(null, "Casos de Prueba:",
			TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		panelGeneral.add(panelCasos);
		
		botonCasos = new JButton("Cargar");
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
		panelCasos.add(botonCasos);
		
		panelColorFondo = new JPanel();
		panelColorFondo.setOpaque(false);
		panelColorFondo.setBorder(new TitledBorder(null, "Color de Fondo de las Ventanas:",
			TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		panelGeneral.add(panelColorFondo);
		
		botonColorFondoCambiar = new JButton("Cambiar");
		botonColorFondoCambiar.setFont(Diseno.fuenteBotones);
		panelColorFondo.add(botonColorFondoCambiar);
		
		botonColorFondoGuardar = new JButton("Guardar");
		botonColorFondoGuardar.setFont(Diseno.fuenteBotones);
		botonColorFondoCambiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diseno.fondoVentanas = JColorChooser.showDialog(getContentPane(), "Seleccione un Color: ", Diseno.fondoVentanas);				
				getContentPane().setBackground(Diseno.fondoVentanas);
			}
		});
		panelColorFondo.add(botonColorFondoGuardar);
		
		panelMensajeNuevo = new JPanel();
		panelMensajeNuevo.setOpaque(false);
		panelMensajeNuevo.setBorder(new TitledBorder(null, "Mensaje del Correo de Coincidencias:",
				TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		panelMensajeNuevo.setLayout(new BorderLayout(0, 0));
		panelGeneral.add(panelMensajeNuevo);
		
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
		
		panelFecha = new JPanel();
		panelFecha.setOpaque(false);
		marcoPestanias.addTab("Fecha Actual de Producci\u00F3n", null, panelFecha, null);
		panelFecha.setLayout(new BoxLayout(panelFecha, BoxLayout.PAGE_AXIS));
		
		panelCalendar = new JPanel();
		panelCalendar.setOpaque(false);
		panelFecha.add(panelCalendar);
		panelCalendar.setLayout(new BoxLayout(panelCalendar, BoxLayout.X_AXIS));
		
		calendar = new JCalendar();
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
		calendar.getDayChooser().setBorder(new TitledBorder(null, "Seleccione la fecha de inicio: ",
			TitledBorder.LEADING, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		calendar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		calendar.getDayChooser().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		calendar.getYearChooser().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		calendar.getMonthChooser().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		calendar.setEnabled(!Tiempo.isFechaEstablecida());
		panelCalendar.add(calendar);
		
		marcoBotonesFecha = new JPanel();
		marcoBotonesFecha.setOpaque(false);
		panelFecha.add(marcoBotonesFecha);
		
		botonGuardarFecha = new JButton("Guardar Fecha");
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
		marcoBotonesFecha.add(botonGuardarFecha);
		
		panelFechaActual = new JPanel();
		panelFechaActual.setOpaque(false);
		panelFechaActual.setBorder(new TitledBorder(null, "Fecha Actual:",
				TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones.deriveFont(Font.PLAIN), new Color(0, 0, 0)));
		marcoBotonesFecha.add(panelFechaActual);
		
		textFecha = new JTextField();
		if (Tiempo.isFechaEstablecida()) {
			textFecha.setText(Tiempo.getStringFechaSistema());
		}
		textFecha.setEnabled(Tiempo.isFechaEstablecida());
		textFecha.setEditable(false);
		textFecha.setColumns(10);
		textFecha.setFont(Diseno.fuenteBotones);
		panelFechaActual.add(textFecha);
		
		boton1Dia = new JButton("Avanzar un D\u00EDa");
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
		panelFechaActual.add(boton1Dia);
		
		boton7Dias = new JButton("Avanzar 7 D\u00EDas");
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
		panelFechaActual.add(boton7Dias);
		
		boton30Dias = new JButton("Avanzar 30 D\u00EDas");
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
		panelFechaActual.add(boton30Dias);
		
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





