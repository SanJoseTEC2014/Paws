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
	private JButton botonGuardarCambios;
	private JButton botonQuieroAdoptarla;
	private JButton botonSoyDuenio;
	private JTextField campoColor;
	private JFormattedTextField campoMontoRecompensa;
	private JTextField campoNombre;
	private JTextField campoChip;
	private JCheckBox checkCastrada;
	private JCheckBox checkDesparacitada;
	private JCheckBox checkVacunada;
	private JComboBox<String> comboEspecie;
	private JComboBox<String> comboRaza;
	private JComboBox<String> comboSexo;
	private JComboBox<String> comboTamanio;
	private JLabel labelFotoMascota;
	private JLabel labelMontoRecompensa;
	private JPanel marcoCentro;
	private JPanel marcoDerecha;
	private Mascota mascotaActual;
	private JRadioButton radioAdoptada;
	private JRadioButton radioDesaparecida;
	private JRadioButton radioEncontrada;
	private JRadioButton radioLocalizada;
	private JRadioButton radioMuerta;
	private JRadioButton radioRefugiada;
	private String rutaFotoMascota;
	private JButton botonRemplazarImagen;
	private JButton botonSeleccionarFoto;

	public VentanaDetallesMascota() {
		setSize(700, 500);
		getContentPane().setBackground(Diseno.fondoVentanas);
		getContentPane().setLayout(new BorderLayout(5, 5));

		JLabel labelTitulo = new JLabel("Detalles de la Mascota");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(Diseno.fuenteTitulosVentanas);
		getContentPane().add(labelTitulo, BorderLayout.NORTH);

		JPanel marcoInformacionPrincipal = new JPanel();
		getContentPane().add(marcoInformacionPrincipal, BorderLayout.WEST);
		marcoInformacionPrincipal.setOpaque(false);
		marcoInformacionPrincipal.setBorder(new TitledBorder(null,
				"Informaci\u00F3n Principal", TitledBorder.CENTER,
				TitledBorder.TOP, Diseno.fuenteBotones, new Color(0, 0, 0)));
		marcoInformacionPrincipal.setLayout(new GridLayout(8, 2, 0, 10));

		JLabel labelNombre = new JLabel("Nombre");
		marcoInformacionPrincipal.add(labelNombre);

		campoNombre = new JTextField();
		marcoInformacionPrincipal.add(campoNombre);

		JLabel labelChip = new JLabel("Chip");
		marcoInformacionPrincipal.add(labelChip);

		campoChip = new JTextField();
		marcoInformacionPrincipal.add(campoChip);

		JLabel labelColor = new JLabel("Color");
		marcoInformacionPrincipal.add(labelColor);

		campoColor = new JTextField();
		marcoInformacionPrincipal.add(campoColor);

		JLabel lblEspecie = new JLabel("Especie");
		marcoInformacionPrincipal.add(lblEspecie);

		comboEspecie = new JComboBox<String>();
		comboEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboRaza.setModel(Mascota.getModeloRazas((String) comboEspecie
						.getSelectedItem()));
			}
		});
		// Esto permite que cuando el ComboBox está desactivado, pueda
		// seguir viéndose el texto de la especie de la Mascota claramente.
		comboEspecie.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JComponent result = (JComponent) super
						.getListCellRendererComponent(list, value, index,
								isSelected, cellHasFocus);
				result.setOpaque(false);
				return result;
			}
		});
		comboEspecie.setToolTipText("");
		marcoInformacionPrincipal.add(comboEspecie);

		JLabel labelRaza = new JLabel("Raza");
		marcoInformacionPrincipal.add(labelRaza);

		comboRaza = new JComboBox<String>();
		// Esto permite que cuando el ComboBox está desactivado, pueda
		// seguir viéndose el texto de la raza de la Mascota claramente.
		comboRaza.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JComponent result = (JComponent) super
						.getListCellRendererComponent(list, value, index,
								isSelected, cellHasFocus);
				result.setOpaque(false);
				return result;
			}
		});
		marcoInformacionPrincipal.add(comboRaza);

		JLabel labelTamanio = new JLabel("Tama\u00F1o");
		marcoInformacionPrincipal.add(labelTamanio);

		comboTamanio = new JComboBox<String>();
		marcoInformacionPrincipal.add(comboTamanio);

		JLabel labelSexo = new JLabel("Sexo");
		marcoInformacionPrincipal.add(labelSexo);

		comboSexo = new JComboBox<String>();
		marcoInformacionPrincipal.add(comboSexo);

		labelMontoRecompensa = new JLabel("Recompensa");
		marcoInformacionPrincipal.add(labelMontoRecompensa);

		campoMontoRecompensa = new JFormattedTextField();
		campoMontoRecompensa.setEditable(false);
		marcoInformacionPrincipal.add(campoMontoRecompensa);

		marcoCentro = new JPanel();
		marcoCentro.setOpaque(false);
		getContentPane().add(marcoCentro, BorderLayout.CENTER);
		marcoCentro.setLayout(new BorderLayout(10, 10));

		JPanel marcoFoto = new JPanel();
		marcoFoto.setBorder(new TitledBorder(null, "Fotografía",
				TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones,
				null));
		marcoFoto.setOpaque(false);
		marcoCentro.add(marcoFoto, BorderLayout.CENTER);
		marcoFoto.setLayout(new BorderLayout(10, 10));

		labelFotoMascota = new JLabel("");
		labelFotoMascota.setHorizontalAlignment(SwingConstants.CENTER);
		marcoFoto.add(labelFotoMascota, BorderLayout.CENTER);
		
		rutaFotoMascota = "";

		JPanel panelBotonesFoto = new JPanel();
		panelBotonesFoto.setOpaque(false);
		marcoFoto.add(panelBotonesFoto, BorderLayout.SOUTH);
		panelBotonesFoto.setLayout(new BoxLayout(panelBotonesFoto,
				BoxLayout.Y_AXIS));

		botonSeleccionarFoto = new JButton("Seleccionar Nueva Imagen");
		botonSeleccionarFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBotonesFoto.add(botonSeleccionarFoto);
		botonSeleccionarFoto.setOpaque(false);
		botonSeleccionarFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rutaFotoMascota = Imagenes.seleccionarImagen();
					BufferedImage porInsertar = Imagenes.cargarImagen(rutaFotoMascota);
					Dimension nuevoTamanio = Imagenes.getNuevaDimension(
							labelFotoMascota.getSize().width,
							labelFotoMascota.getSize().height,
							porInsertar.getSampleModel().getWidth(),
							porInsertar.getSampleModel().getHeight());
					labelFotoMascota.setText("");
					labelFotoMascota.setIcon(
						new ImageIcon(
							Imagenes.redimensionar(porInsertar, nuevoTamanio.width, nuevoTamanio.height)));
				} catch (ImagenNoEncontradaException ex) {
					JOptionPane.showMessageDialog(getContentPane(),
						ex.getMessage(), "Advertencia",
						JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		botonRemplazarImagen = new JButton("Reemplazar Imagen en el Sistema");
		botonRemplazarImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonRemplazarImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mascotaActual.setNombre(campoNombre.getText());
				mascotaActual.setChip(campoChip.getText());
				mascotaActual.setColor(campoColor.getText());
				mascotaActual.setEspecie(comboEspecie.getSelectedItem().toString());
				mascotaActual.setRaza(comboRaza.getSelectedItem().toString());
				mascotaActual.setTamanio(comboTamanio.getSelectedItem().toString());
				mascotaActual.setSexo(comboSexo.getSelectedItem().toString());
				mascotaActual.setRecompensa(Integer.parseInt(campoMontoRecompensa.getText()));
			}
		});
		panelBotonesFoto.add(botonRemplazarImagen);
		botonRemplazarImagen.setOpaque(false);

		JPanel marcoBotonesBasicos = new JPanel();
		getContentPane().add(marcoBotonesBasicos, BorderLayout.SOUTH);
		marcoBotonesBasicos.setOpaque(false);

		botonSoyDuenio = new JButton("\u00A1Soy el due\u00F1o!");
		botonSoyDuenio.setOpaque(false);
		botonSoyDuenio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		marcoBotonesBasicos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		marcoBotonesBasicos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		marcoBotonesBasicos.add(botonSoyDuenio);

		botonQuieroAdoptarla = new JButton("Quiero adoptarla");
		botonQuieroAdoptarla.setOpaque(false);
		marcoBotonesBasicos.add(botonQuieroAdoptarla);

		botonGuardarCambios = new JButton("Guardar Cambios");
		botonGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
			}
		});
		marcoBotonesBasicos.add(botonGuardarCambios);
		botonGuardarCambios.setVisible(false);
		botonGuardarCambios.setOpaque(false);

		JButton botonCerrar = new JButton("Cerrar");
		botonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		marcoBotonesBasicos.add(botonCerrar);
		botonCerrar.setOpaque(false);

		marcoDerecha = new JPanel();
		marcoDerecha.setOpaque(false);
		getContentPane().add(marcoDerecha, BorderLayout.EAST);
		marcoDerecha.setLayout(new BoxLayout(marcoDerecha, BoxLayout.Y_AXIS));

		JPanel marcoRadios = new JPanel();
		marcoRadios.setOpaque(false);
		marcoDerecha.add(marcoRadios);
		marcoRadios.setBorder(new TitledBorder(null, "Estado Actual",
			TitledBorder.CENTER, TitledBorder.TOP, Diseno.fuenteBotones,
			new Color(0, 0, 0)));

		ButtonGroup buttonGroupEstados = new ButtonGroup();
		marcoRadios.setLayout(new BoxLayout(marcoRadios, BoxLayout.Y_AXIS));

		radioDesaparecida = new JRadioButton("Desaparecida");
		radioDesaparecida.setEnabled(false);
		radioDesaparecida.setOpaque(false);
		buttonGroupEstados.add(radioDesaparecida);
		marcoRadios.add(radioDesaparecida);

		radioLocalizada = new JRadioButton("Localizada");
		radioLocalizada.setEnabled(false);
		radioLocalizada.setOpaque(false);
		buttonGroupEstados.add(radioLocalizada);
		marcoRadios.add(radioLocalizada);

		radioEncontrada = new JRadioButton("Encontrada");
		radioEncontrada.setEnabled(false);
		radioEncontrada.setOpaque(false);
		buttonGroupEstados.add(radioEncontrada);
		marcoRadios.add(radioEncontrada);

		radioRefugiada = new JRadioButton("Refugiada");
		radioRefugiada.setEnabled(false);
		radioRefugiada.setOpaque(false);
		buttonGroupEstados.add(radioRefugiada);
		marcoRadios.add(radioRefugiada);

		radioAdoptada = new JRadioButton("Adoptada");
		radioAdoptada.setEnabled(false);
		radioAdoptada.setOpaque(false);
		buttonGroupEstados.add(radioAdoptada);
		marcoRadios.add(radioAdoptada);

		radioMuerta = new JRadioButton("Muerta");
		radioMuerta.setOpaque(false);
		radioMuerta.setEnabled(false);
		marcoRadios.add(radioMuerta);

		JPanel marcoChecks = new JPanel();
		marcoChecks.setOpaque(false);
		marcoDerecha.add(marcoChecks);
		marcoChecks
			.setBorder(new TitledBorder(null, "Salud", TitledBorder.CENTER,
			TitledBorder.TOP, Diseno.fuenteBotones, new Color(0, 0, 0)));
		marcoChecks.setLayout(new BoxLayout(marcoChecks, BoxLayout.Y_AXIS));

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
		
	}

	public void modoEdicion(boolean isMascotaDeUsuarioActivo){
		botonGuardarCambios.setVisible(isMascotaDeUsuarioActivo);
		campoNombre.setEditable(isMascotaDeUsuarioActivo);
		campoColor.setEditable(isMascotaDeUsuarioActivo);
		campoMontoRecompensa.setEditable(isMascotaDeUsuarioActivo);
		campoChip.setEnabled(isMascotaDeUsuarioActivo);
		comboEspecie.setEnabled(isMascotaDeUsuarioActivo);
		comboRaza.setEnabled(isMascotaDeUsuarioActivo);
		comboSexo.setEnabled(isMascotaDeUsuarioActivo);
		comboTamanio.setEnabled(isMascotaDeUsuarioActivo);
		campoMontoRecompensa.setEditable(isMascotaDeUsuarioActivo);
		
		botonRemplazarImagen.setVisible(isMascotaDeUsuarioActivo);
		botonRemplazarImagen.setVisible(isMascotaDeUsuarioActivo);
		
	}
	
	public void setDatosIniciales(Mascota mascota) {
		
		mascotaActual = mascota;
		
		JOptionPane.showMessageDialog(getContentPane(), mascota.toString());
		
		botonSoyDuenio.setVisible(mascota.isEncontrada() || mascota.isRefugiada() && !mascota.getUltimoSuceso().getNick().equals(Acceso.getUsuarioActivo().getNickname()));
		botonQuieroAdoptarla.setVisible(mascota.isEncontrada() || mascota.isRefugiada() && !mascota.getUltimoSuceso().getNick().equals(Acceso.getUsuarioActivo().getNickname()));

		campoNombre.setText(mascota.getNombre());
		campoChip.setText(mascota.getNumeroChip() == null ? "" : mascota
				.getNumeroChip().toString());
		campoColor.setText(mascota.getColor());

		labelMontoRecompensa.setVisible(mascota.isDesaparecida());
		campoMontoRecompensa.setVisible(mascota.isDesaparecida());
		campoMontoRecompensa.setText(mascota.getRecompensa() == null ? "0" : mascota.getRecompensa().toString());


		comboEspecie.setModel(Mascota.getModeloEspecies());
		comboEspecie.setSelectedItem(mascota.getEspecie());

		comboRaza.setModel(Mascota.getModeloRazas(mascota.getEspecie()));
		comboRaza.setSelectedItem(mascota.getRaza());

		comboSexo.setModel(Mascota.getModeloSexos());
		comboSexo.setSelectedItem(mascota.getSexo());

		comboTamanio.setModel(Mascota.getModeloTamanios());
		comboTamanio.setSelectedItem(mascota.getTamanio());

		try {
//			int ancho = labelFotoMascota.getSize().width;
//			int alto = labelFotoMascota.getSize().height;
//			BufferedImage porInsertar = Imagenes.redimensionar(Imagenes.getPerfilMascota(mascota.getID()), ancho, alto);
//			labelFotoMascota.setText("");
//			labelFotoMascota.setIcon(new ImageIcon(porInsertar));
			BufferedImage porInsertar = Imagenes.getPerfilMascota(mascota.getID());
			Dimension nuevoTamanio = Imagenes.getNuevaDimension(
					labelFotoMascota.getSize().width,
					labelFotoMascota.getSize().height,
					porInsertar.getSampleModel().getWidth(),
					porInsertar.getSampleModel().getHeight());
			labelFotoMascota.setText("");
			labelFotoMascota.setIcon(
				new ImageIcon(
					Imagenes.redimensionar(porInsertar, nuevoTamanio.width, nuevoTamanio.height)));
		} catch (ImagenNoEncontradaException e) {
			labelFotoMascota.setText("No existe fotografía para esta mascota.");
		}
		
		radioDesaparecida.setModel(new ReadOnlyRadioButtonModel(mascota.isDesaparecida()));
		radioEncontrada.setModel(new ReadOnlyRadioButtonModel(mascota.isEncontrada()));
		radioRefugiada.setModel(new ReadOnlyRadioButtonModel(mascota.isRefugiada()));
		radioLocalizada.setModel(new ReadOnlyRadioButtonModel(mascota.isLocalizada()));
		radioAdoptada.setModel(new ReadOnlyRadioButtonModel(mascota.isAdoptada()));
		radioMuerta.setModel(new ReadOnlyRadioButtonModel(mascota.isMuerta()));
		
	}
	
}
