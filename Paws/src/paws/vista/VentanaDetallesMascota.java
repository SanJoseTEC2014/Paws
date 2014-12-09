package paws.vista;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.border.*;

import paws.control.*;
import paws.control.excepciones.*;
import paws.modelo.Mascota;
import paws.recursos.Diseno;

@SuppressWarnings("serial")
public class VentanaDetallesMascota extends JFrame {
	private JTextField campoColor;
	private JCheckBox checkVacunada;
	private JCheckBox checkCastrada;
	private JCheckBox checkDesparacitada;
	private JRadioButton radioPerdida;
	private JRadioButton radioEncontrada;
	private JRadioButton radioLocalizada;
	private JRadioButton radioAdoptada;
	private JRadioButton radioRefugiada;
	private JComboBox<String> comboEspecie;
	private JComboBox<String> comboRaza;
	private JComboBox<String> comboTamanio;
	private JComboBox<String> comboSexo;
	private final ButtonGroup buttonGroupEstados = new ButtonGroup();
	private JTextField campoNumeroChip;
	private JPanel marcoCentro;
	private JPanel marcoFoto;
	private JLabel labelFotoMascota;
	private JButton botonActualizarFoto;
	private JPanel marcoInformacionPrincipal;
	private JLabel labelNombre;
	private JTextField campoNombre;
	private JLabel labelNumeroChip;
	private JLabel labelColor;
	private JLabel lblEspecie;
	private JLabel labelRaza;
	private JLabel labelTamanio;
	private JLabel labelSexo;
	private JPanel marcoInferior;
	private JPanel marcoRadios;
	private JPanel marcoChecks;
	private JPanel marcoOperaciones;
	private JButton botonSoyDuenio;
	private JPanel marcoOperacionesBasicas;
	private JButton botonCancelar;
	private JButton botonGuardarCambios;
	private JLabel labelMontoRecompensa;
	private JTextField campoMontoRecompensa;
	private JButton botonQuieroAdoptarla;
	private JRadioButton radioAdoptable;
	
	public VentanaDetallesMascota() {
		setSize(700, 500);
		getContentPane().setBackground(Diseno.fondoVentanas);
		getContentPane().setLayout(new BorderLayout(20, 10));

		marcoCentro = new JPanel();
		marcoCentro.setOpaque(false);
		getContentPane().add(marcoCentro, BorderLayout.CENTER);
		marcoCentro.setLayout(new BorderLayout(10, 10));

		marcoFoto = new JPanel();
		marcoFoto.setBorder(new TitledBorder(null, "Detalles de Mascota", TitledBorder.CENTER, TitledBorder.TOP,
		Diseno.fuenteTitulosVentanas, null));
		marcoFoto.setOpaque(false);
		marcoCentro.add(marcoFoto, BorderLayout.CENTER);
		marcoFoto.setLayout(new BorderLayout(10, 10));

		labelFotoMascota = new JLabel("");
		marcoFoto.add(labelFotoMascota, BorderLayout.CENTER);
		labelFotoMascota.setHorizontalAlignment(SwingConstants.CENTER);

		botonActualizarFoto = new JButton("Actualizar Foto");
		botonActualizarFoto.setOpaque(false);
		marcoFoto.add(botonActualizarFoto, BorderLayout.SOUTH);

		marcoInformacionPrincipal = new JPanel();
		marcoInformacionPrincipal.setOpaque(false);
		marcoCentro.add(marcoInformacionPrincipal, BorderLayout.EAST);
		marcoInformacionPrincipal.setBorder(new TitledBorder(null, "Informaci\u00F3n Principal", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		marcoInformacionPrincipal.setLayout(new GridLayout(8, 2, 0, 0));

		labelNombre = new JLabel("Nombre");
		marcoInformacionPrincipal.add(labelNombre);

		campoNombre = new JTextField();
		marcoInformacionPrincipal.add(campoNombre);
		campoNombre.setColumns(12);

		labelNumeroChip = new JLabel("N\u00FAmero del Chip");
		marcoInformacionPrincipal.add(labelNumeroChip);

		campoNumeroChip = new JTextField();
		campoNumeroChip.setColumns(12);
		marcoInformacionPrincipal.add(campoNumeroChip);

		labelColor = new JLabel("Color");
		marcoInformacionPrincipal.add(labelColor);

		campoColor = new JTextField();
		marcoInformacionPrincipal.add(campoColor);
		campoColor.setColumns(12);

		lblEspecie = new JLabel("Especie");
		marcoInformacionPrincipal.add(lblEspecie);

		comboEspecie = new JComboBox<String>();
		comboEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboRaza.setModel(Mascota.getModeloRazas((String) comboEspecie.getSelectedItem()));
			}
		});
		// Esto permite que cuando el ComboBox está desactivado, pueda
		// seguir viéndose el texto de la especie de la Mascota claramente.
		comboEspecie.setRenderer(new DefaultListCellRenderer(){
		    @Override
		    public Component
		    getListCellRendererComponent(JList<?> list, Object value, int index,
		    							boolean isSelected, boolean cellHasFocus)
		    {
		        JComponent result = (JComponent)super.getListCellRendererComponent
		        					(list, value, index, isSelected, cellHasFocus);
		        result.setOpaque(false);
		        return result;
		    }
		});
		comboEspecie.setToolTipText("");
		marcoInformacionPrincipal.add(comboEspecie);

		labelRaza = new JLabel("Raza");
		marcoInformacionPrincipal.add(labelRaza);

		comboRaza = new JComboBox<String>();
		// Esto permite que cuando el ComboBox está desactivado, pueda
		// seguir viéndose el texto de la raza de la Mascota claramente.
		comboRaza.setRenderer(new DefaultListCellRenderer(){
		    @Override
		    public Component
		    getListCellRendererComponent(JList<?> list, Object value, int index,
		    							boolean isSelected, boolean cellHasFocus)
		    {
		        JComponent result = (JComponent)super.getListCellRendererComponent
		        					(list, value, index, isSelected, cellHasFocus);
		        result.setOpaque(false);
		        return result;
		    }
		});
		marcoInformacionPrincipal.add(comboRaza);

		labelTamanio = new JLabel("Tama\u00F1o");
		marcoInformacionPrincipal.add(labelTamanio);

		comboTamanio = new JComboBox<String>();
		marcoInformacionPrincipal.add(comboTamanio);

		labelSexo = new JLabel("Sexo");
		marcoInformacionPrincipal.add(labelSexo);

		comboSexo = new JComboBox<String>();
		marcoInformacionPrincipal.add(comboSexo);
		
		labelMontoRecompensa = new JLabel("Monto Recompensa:");
		marcoInformacionPrincipal.add(labelMontoRecompensa);
		
		campoMontoRecompensa = new JTextField();
		campoMontoRecompensa.setEditable(false);
		campoMontoRecompensa.setColumns(12);
		marcoInformacionPrincipal.add(campoMontoRecompensa);
		botonActualizarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String rutaFotoMascota = Imagenes.seleccionarImagen();
					labelFotoMascota.setIcon(new ImageIcon(Imagenes.cargarImagen(rutaFotoMascota)));
				} catch (ImagenNoEncontradaException e1) {
					JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(), "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		marcoInferior = new JPanel();
		marcoInferior.setOpaque(false);
		getContentPane().add(marcoInferior, BorderLayout.SOUTH);
		marcoInferior.setLayout(new BorderLayout(10, 10));

		marcoRadios = new JPanel();
		marcoRadios.setOpaque(false);
		marcoInferior.add(marcoRadios, BorderLayout.NORTH);
		marcoRadios.setBorder(new TitledBorder(null, "Estado Actual", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		marcoRadios.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		radioPerdida = new JRadioButton("Perdida");
		radioPerdida.setEnabled(false);
		radioPerdida.setOpaque(false);
		buttonGroupEstados.add(radioPerdida);
		marcoRadios.add(radioPerdida);

		radioEncontrada = new JRadioButton("Encontrada");
		radioEncontrada.setEnabled(false);
		radioEncontrada.setOpaque(false);
		buttonGroupEstados.add(radioEncontrada);
		marcoRadios.add(radioEncontrada);

		radioLocalizada = new JRadioButton("Localizada");
		radioLocalizada.setEnabled(false);
		radioLocalizada.setOpaque(false);
		buttonGroupEstados.add(radioLocalizada);
		marcoRadios.add(radioLocalizada);
		
		radioAdoptable = new JRadioButton("Adoptable");
		radioAdoptable.setOpaque(false);
		radioAdoptable.setEnabled(false);
		marcoRadios.add(radioAdoptable);

		radioAdoptada = new JRadioButton("Adoptada");
		radioAdoptada.setEnabled(false);
		radioAdoptada.setOpaque(false);
		buttonGroupEstados.add(radioAdoptada);
		marcoRadios.add(radioAdoptada);

		radioRefugiada = new JRadioButton("Refugiada");
		radioRefugiada.setEnabled(false);
		radioRefugiada.setOpaque(false);
		buttonGroupEstados.add(radioRefugiada);
		marcoRadios.add(radioRefugiada);

		marcoChecks = new JPanel();
		marcoChecks.setOpaque(false);
		marcoInferior.add(marcoChecks, BorderLayout.CENTER);
		marcoChecks.setBorder(new TitledBorder(null, "Informaci\u00F3n F\u00EDsica & Veterinaria", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		marcoChecks.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		checkVacunada = new JCheckBox("Vacunada");
		checkVacunada.setEnabled(false);
		checkVacunada.setOpaque(false);
		marcoChecks.add(checkVacunada);

		checkCastrada = new JCheckBox("Castrada");
		checkCastrada.setEnabled(false);
		checkCastrada.setOpaque(false);
		marcoChecks.add(checkCastrada);

		checkDesparacitada = new JCheckBox("Desparacitada");
		checkDesparacitada.setEnabled(false);
		checkDesparacitada.setOpaque(false);
		marcoChecks.add(checkDesparacitada);

		marcoOperaciones = new JPanel();
		marcoOperaciones.setOpaque(false);
		marcoInferior.add(marcoOperaciones, BorderLayout.SOUTH);
		marcoOperaciones.setLayout(new BorderLayout(10, 10));

		botonSoyDuenio = new JButton("\u00A1Soy el due\u00F1o!");
		botonSoyDuenio.setOpaque(false);
		botonSoyDuenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		marcoOperaciones.add(botonSoyDuenio, BorderLayout.WEST);

		marcoOperacionesBasicas = new JPanel();
		marcoOperacionesBasicas.setOpaque(false);
		marcoOperacionesBasicas.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 0));
		marcoOperaciones.add(marcoOperacionesBasicas, BorderLayout.EAST);

		botonCancelar = new JButton("Cancelar");
		botonCancelar.setOpaque(false);
		marcoOperacionesBasicas.add(botonCancelar);

		botonGuardarCambios = new JButton("Guardar Cambios");
		botonGuardarCambios.setVisible(false);
		botonGuardarCambios.setOpaque(false);
		marcoOperacionesBasicas.add(botonGuardarCambios);
		
		botonQuieroAdoptarla = new JButton("Quiero adoptarla");
		botonQuieroAdoptarla.setOpaque(false);
		marcoOperaciones.add(botonQuieroAdoptarla, BorderLayout.CENTER);
		
		/*
		if (orden == "MostrarDetalles") {
			comboEspecie.setToolTipText(mascota.getEspecie());
		} else
			comboEspecie.setModel(mascota.getModeloEspecies());
		if (orden == "MostrarDetalles") {
			comboRaza.setToolTipText(mascota.getRaza());
		} else
			comboRaza.setModel(mascota.getModeloRazas());
		if (orden == "MostrarDetalles") {
			comboEdad.setToolTipText(mascota.getEdad());
		} else
			comboEdad.setModel(mascota.getModeloEdades());
		if (orden == "MostrarDetalles") {
			comboTamanio.setToolTipText(mascota.getTamanio());
		} else
			comboTamanio.setModel(mascota.getModeloEdades());
		if (orden == "MostrarDetalles") {
			checkVacunada.setSelected(mascota.isVacunada());
		} else {
			checkVacunada.setSelected(mascota.isVacunada());

		}
		if (orden == "MostrarDetalles") {
			checkCastrada.setSelected(mascota.isCastrada());
		} else {
			checkCastrada.setSelected(mascota.isCastrada());
		}
		if (orden == "MostrarDetalles") {
			checkDesparacitada.setSelected(mascota.isDesparacitada());
		} else {
			checkDesparacitada.setSelected(mascota.isDesparacitada());
		}
		if (orden == "MostrarDetalles") {
			checkDiscapacitada.setSelected(mascota.isDiscapacitada());
		} else {
			checkDiscapacitada.setSelected(mascota.isDiscapacitada());
		}
		*/
	}
	
	public void setDatosIniciales(Mascota mascota){
		
		// El boton soy dueño aparece sii la mascota esta encontrada, adoptable o refugiada
		botonSoyDuenio.setVisible(mascota.getMarcadoresEstado()[1] || // Encontrada
								  mascota.getMarcadoresEstado()[2] ); // Refugiada

		campoNombre.setText(mascota.getNombre());
		campoNumeroChip.setText(mascota.getNumeroChip() == null ? "" : mascota.getNumeroChip().toString());
		campoColor.setText(mascota.getColor());
		
		labelMontoRecompensa.setVisible(mascota.getMarcadoresEstado()[0]); // Desaparecida
		campoMontoRecompensa.setVisible(mascota.getMarcadoresEstado()[0]); // Desaparecida
		campoMontoRecompensa.setText(mascota.getRecompensa() == null ? "0" : mascota.getRecompensa().toString());
		
		// Pone el boton de adoptar visible si la mascota está encontrada o refugiada
		botonQuieroAdoptarla.setVisible(mascota.getMarcadoresEstado()[1] || // Encontrada
				  						  mascota.getMarcadoresEstado()[2] ); // Refugiada
		
		comboEspecie.setModel(Mascota.getModeloEspecies());
		comboEspecie.setSelectedItem(mascota.getEspecie());
		
		comboRaza.setModel(Mascota.getModeloRazas(mascota.getEspecie()));
		comboRaza.setSelectedItem(mascota.getRaza());
		
		comboSexo.setModel(Mascota.getModeloSexos());
		comboSexo.setSelectedItem(mascota.getSexo());
		
		comboTamanio.setModel(Mascota.getModeloTamanios());
		comboTamanio.setSelectedItem(mascota.getTamanio());
		
		try {
			int ancho = labelFotoMascota.getSize().width;
			int alto = labelFotoMascota.getSize().height;
			BufferedImage porInsertar = mascota.getImagen();
			labelFotoMascota.setIcon(new ImageIcon(porInsertar));
		} catch (ImagenNoEncontradaException e) {
			labelFotoMascota.setIcon(null);
			labelFotoMascota.setText("Problema al Cargar la Imagen");
		}
	}
	
	public void setModoEdicion(boolean opcion){
		
		botonGuardarCambios.setEnabled(opcion);
		botonCancelar.setEnabled(opcion);
		
		campoNombre.setEditable(opcion);
		campoNumeroChip.setEditable(opcion);
		campoColor.setEditable(opcion);
		
		
	}
}
