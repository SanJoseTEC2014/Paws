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
	
	private JButton botonGuardarFecha;
	private JComboBox<String> comboCalificaciones;
	private JButton botonAvanzarUnDia;
	private JButton botonCalificacion;
	private JPanel marcoTitulo;
	private JLabel labelTitulo1;
	private JLabel labelTitulo2;
	private JPanel marcoInferior;
	private JPanel panelFecha;
	
	private static String[] calificaciones = new String[]{"5.0",
							"4.5", "4.0", "3.5", "3.0", "2.5",
							"2.0", "1.5", "1.0", "0.5", "0.0"};
	private static boolean fechaEstablecida;
	
	private JCalendar calendar;
	private JPanel panelGeneral;
	private JPanel marcoBotonesFecha;
	private JPanel panelCalendar;
	private JTabbedPane marcoPestanias;
	private JLabel labelMinima;
	private JLabel labelCasos;
	private JButton botonCasos;
	private JButton botonColorFondo;
	private JLabel labelFecha;
	private JTextField textFecha;
	private JPanel panelMensajeCorreo;
	private JPanel mensajeNuevo;
	private JTextArea mensajeCorreoNuevo;
	private JButton btnGuardarMensaje;
	private JLabel labelColorFondo;
	
	public VentanaParametrosSistema() {
		getContentPane().setBackground(Diseno.fondoVentanas);
		setSize(570, 464);
		
		marcoTitulo = new JPanel();
		marcoTitulo.setOpaque(false);
		getContentPane().add(marcoTitulo, BorderLayout.NORTH);
		marcoTitulo.setLayout(new BorderLayout(0, 0));
		
		labelTitulo1 = new JLabel("Par\u00E1metros");
		labelTitulo1.setFont(Diseno.fuenteTitulosVentanas);
		labelTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
		marcoTitulo.add(labelTitulo1, BorderLayout.NORTH);
		
		labelTitulo2 = new JLabel("Sistema");
		labelTitulo2.setFont(Diseno.fuenteTitulosVentanas);
		labelTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
		marcoTitulo.add(labelTitulo2, BorderLayout.SOUTH);
		
		marcoPestanias = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(marcoPestanias, BorderLayout.CENTER);
		
		panelGeneral = new JPanel();
		marcoPestanias.addTab("General", null, panelGeneral, null);
		panelGeneral.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panelGeneral.setOpaque(false);
		panelGeneral.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		labelMinima = new JLabel("Calificaci\u00F3n M\u00EDnima para degradar a un Usuario a Lista Negra:");
		panelGeneral.add(labelMinima);
		
		comboCalificaciones = new JComboBox<String>();
		panelGeneral.add(comboCalificaciones);
		comboCalificaciones.setModel(new DefaultComboBoxModel<String>(calificaciones));
		
		botonCalificacion = new JButton("Cambiar");
		botonCalificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario.setCalificacionMinimaPermitidaUsuarios(
						Double.valueOf((String) comboCalificaciones.getSelectedItem()));
			}
		});
		
		panelGeneral.add(botonCalificacion);
		botonCalificacion.setOpaque(false);
		
		labelCasos = new JLabel("Casos de Prueba de Usuarios:");
		panelGeneral.add(labelCasos);
		
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
		panelGeneral.add(botonCasos);
		botonColorFondo = new JButton("Cambiar Color de Fondo de las Ventanas");
		botonColorFondo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Diseno.fondoVentanas = JColorChooser.showDialog(getContentPane(), "Seleccione un Color", Color.gray);
				getContentPane().setBackground(Diseno.fondoVentanas);
			}
		});
		
		labelColorFondo = new JLabel("Casos de Prueba de Usuarios:");
		panelGeneral.add(labelColorFondo);
		panelGeneral.add(botonColorFondo);
		
		panelFecha = new JPanel();
		panelFecha.setOpaque(false);
		marcoPestanias.addTab("Fecha Actual de Producci\u00F3n", null, panelFecha, null);
		panelFecha.setLayout(new BoxLayout(panelFecha, BoxLayout.PAGE_AXIS));
		
		panelCalendar = new JPanel();
		panelCalendar.setOpaque(false);
		panelFecha.add(panelCalendar);
		panelCalendar.setLayout(new BoxLayout(panelCalendar, BoxLayout.X_AXIS));
		
		calendar = new JCalendar();
		calendar.getDayChooser().setOpaque(false);
		calendar.getYearChooser().setOpaque(false);
		calendar.getMonthChooser().setOpaque(false);
		calendar.setOpaque(false);
		calendar.setBackground(Diseno.fondoVentanas);
		calendar.getDayChooser().getDayPanel().setOpaque(false);
		panelCalendar.add(calendar);
		calendar.getDayChooser().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		calendar.getYearChooser().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		calendar.getMonthChooser().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		calendar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		marcoBotonesFecha = new JPanel();
		panelFecha.add(marcoBotonesFecha);
		marcoBotonesFecha.setOpaque(false);
		
		botonGuardarFecha = new JButton("Guardar Fecha");
		marcoBotonesFecha.add(botonGuardarFecha);
		botonGuardarFecha.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		botonGuardarFecha.setAlignmentX(Component.RIGHT_ALIGNMENT);
		botonGuardarFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tiempo.setFechaInicioProduccion(calendar.getCalendar());
				fechaEstablecida = false;
				calendar.setEnabled(fechaEstablecida);
			}
		});
		botonGuardarFecha.setOpaque(false);
		
		botonAvanzarUnDia = new JButton("Avanzar un D\u00EDa");
		marcoBotonesFecha.add(botonAvanzarUnDia);
		botonAvanzarUnDia.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		botonAvanzarUnDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tiempo.avanzarDia();
				textFecha.setText(Tiempo.getStringFechaSistema());
				textFecha.setEnabled(true);
			}
		});
		botonAvanzarUnDia.setOpaque(false);
		
		labelFecha = new JLabel("Fecha Actual:");
		marcoBotonesFecha.add(labelFecha);
		
		textFecha = new JTextField();
		textFecha.setEnabled(false);
		textFecha.setEditable(false);
		marcoBotonesFecha.add(textFecha);
		textFecha.setColumns(10);
		
		panelMensajeCorreo = new JPanel();
		panelMensajeCorreo.setOpaque(false);
		marcoPestanias.addTab("Mensaje del Correo de Coincidencias", null, panelMensajeCorreo, null);
		panelMensajeCorreo.setLayout(null);
		
		mensajeNuevo = new JPanel();
		mensajeNuevo.setOpaque(false);
		mensajeNuevo.setBorder(new TitledBorder(null, "Nuevo Mensaje", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		mensajeNuevo.setBounds(10, 10, 529, 179);
		panelMensajeCorreo.add(mensajeNuevo);
		mensajeNuevo.setLayout(null);
		
		mensajeCorreoNuevo = new JTextArea();
		mensajeCorreoNuevo.setLineWrap(true);
		mensajeCorreoNuevo.setAlignmentY(0.0f);
		mensajeCorreoNuevo.setBounds(10, 21, 509, 141);
		mensajeNuevo.add(mensajeCorreoNuevo);
		mensajeCorreoNuevo.setColumns(10);
		
		btnGuardarMensaje = new JButton("Guardar Mensaje");
		btnGuardarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Correo.setMensaje(mensajeCorreoNuevo.getText());
			}
		});
		btnGuardarMensaje.setBounds(20, 191, 123, 28);
		panelMensajeCorreo.add(btnGuardarMensaje);
		
		marcoInferior = new JPanel();
		marcoInferior.setOpaque(false);
		getContentPane().add(marcoInferior, BorderLayout.SOUTH);
		
		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		botonCerrar.setOpaque(false);
		marcoInferior.add(botonCerrar);
	}
	
	protected void close() {
		this.dispose();
	}

	public void setDatosIniciales() {
		calendar.setEnabled(fechaEstablecida);
		mensajeCorreoNuevo.setText(Correo.getMensaje());
	}
	
}





