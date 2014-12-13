package paws.vista;

import java.awt.Font;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import paws.control.Acceso;
import paws.control.EstadosMascotas;
import paws.control.Imagenes;
import paws.control.Principal;
import paws.control.excepciones.ImagenNoEncontradaException;
import paws.control.excepciones.TiempoSinEstablecerException;
import paws.modelo.Mascota;
import paws.modelo.Suceso;
import paws.recursos.Diseno;

import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

@SuppressWarnings("serial")
public class VentanaRegistroMascotas extends JFrame {
	private boolean fotoSeleccionada = false;
	private JTextField campoNombre;
	private JTextField campoNumeroDeChip;
	private JFormattedTextField campoRecompensa;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton radioButtonEncontrada;
	private JRadioButton radioButtonPerdida;
	private JEditorPane campoDetallesSuceso;
	private JComboBox<String> comboBoxEspecie;
	private JComboBox<String> comboBoxRaza;
	private JComboBox<String> comboBoxTamanio;
	private JComboBox<String> comboBoxColor;
	private JComboBox<String> comboBoxSexo;
	private JCheckBox checkBoxCastrada;
	private JCheckBox checkBoxVacunada;
	private JCheckBox checkBoxDesparacitada;
	private String imagenSeleccionada;
	private JLabel labelRegistrarUnaMascota;
	private JLabel labelEspacioIzq;
	private JLabel labelEspacioDer;
	private JPanel panelContenido;
	private JPanel columnaIzq;
	private JPanel datosIniciales;
	private JLabel lblNombre;
	private JLabel lblNmeroDeChip;
	private JPanel caracteristicas;
	private JLabel lblEspecie;
	private JLabel lblRaza;
	private JLabel lblTamao;
	private JLabel lblColor;
	private JLabel lblSexo;
	private JPanel estado;
	private JPanel condicionesfisicas;
	private JPanel panelRecompensa;
	private JLabel lblRecompensa;
	private JEditorPane campoDetallesMascota;
	private JEditorPane editorDireccionSuceso;
	private JLabel labelDetallesMascota;
	
	public VentanaRegistroMascotas() {
		setResizable(false);
		setTitle("Registro de mascotas");
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(660,660);
		getContentPane().setLayout(new BorderLayout(20, 10));
		
		labelRegistrarUnaMascota = new JLabel("Registrar una mascota");
		labelRegistrarUnaMascota.setHorizontalAlignment(SwingConstants.CENTER);
		labelRegistrarUnaMascota.setFont(Diseno.fuenteTitulosVentanas);
		getContentPane().add(labelRegistrarUnaMascota, BorderLayout.NORTH);
		
		
		MaskFormatter formatter = null;
		try {
			formatter = new MaskFormatter("######");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		labelEspacioIzq = new JLabel(" ");
		getContentPane().add(labelEspacioIzq, BorderLayout.WEST);
		
		labelEspacioDer = new JLabel(" ");
		getContentPane().add(labelEspacioDer, BorderLayout.EAST);
		
		panelContenido = new JPanel();
		panelContenido.setOpaque(false);
		getContentPane().add(panelContenido, BorderLayout.CENTER);
		panelContenido.setLayout(new BorderLayout(0, 0));
		
		columnaIzq = new JPanel();
		panelContenido.add(columnaIzq, BorderLayout.WEST);
		columnaIzq.setOpaque(false);
		columnaIzq.setLayout(new BoxLayout(columnaIzq, BoxLayout.Y_AXIS));
		
		datosIniciales = new JPanel();
		datosIniciales.setOpaque(false);
		columnaIzq.add(datosIniciales);
		datosIniciales.setLayout(new GridLayout(4, 1, 0, 0));
		
		lblNombre = new JLabel("Nombre");
		datosIniciales.add(lblNombre);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		campoNombre = new JTextField();
		datosIniciales.add(campoNombre);
		campoNombre.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoNombre.setColumns(10);
		
		lblNmeroDeChip = new JLabel("N\u00FAmero de chip");
		datosIniciales.add(lblNmeroDeChip);
		lblNmeroDeChip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		campoNumeroDeChip = new JTextField();
		datosIniciales.add(campoNumeroDeChip);
		campoNumeroDeChip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		campoNumeroDeChip.setColumns(10);
		
		caracteristicas = new JPanel();
		columnaIzq.add(caracteristicas);
		caracteristicas.setOpaque(false);
		caracteristicas.setBorder(new TitledBorder(null, "Caracter\u00EDsticas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		caracteristicas.setLayout(new GridLayout(5, 2, 0, 5));
		
		lblEspecie = new JLabel("Especie");
		caracteristicas.add(lblEspecie);
		lblEspecie.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxEspecie = new JComboBox<String>();
		comboBoxEspecie.setModel(Mascota.getModeloEspecies());
		comboBoxEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxRaza.setModel(Mascota.getModeloRazas((String) comboBoxEspecie.getSelectedItem()));
			}
		});
		caracteristicas.add(comboBoxEspecie);
		comboBoxEspecie.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblRaza = new JLabel("Raza");
		caracteristicas.add(lblRaza);
		lblRaza.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxRaza = new JComboBox<String>();
		comboBoxRaza.setModel(Mascota.getModeloRazas((String) comboBoxEspecie.getSelectedItem()));
		comboBoxRaza.setFont(new Font("Tahoma", Font.PLAIN, 12));
		caracteristicas.add(comboBoxRaza);
		
		lblTamao = new JLabel("Tama\u00F1o");
		caracteristicas.add(lblTamao);
		lblTamao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxTamanio = new JComboBox<String>();
		caracteristicas.add(comboBoxTamanio);
		comboBoxTamanio.setModel(Mascota.getModeloTamanios());
		comboBoxTamanio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblColor = new JLabel("Color");
		caracteristicas.add(lblColor);
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxColor = new JComboBox<String>();
		caracteristicas.add(comboBoxColor);
		comboBoxColor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxColor.setModel(Mascota.getModeloColores());
		
		lblSexo = new JLabel("Sexo");
		caracteristicas.add(lblSexo);
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		comboBoxSexo = new JComboBox<String>();
		caracteristicas.add(comboBoxSexo);
		comboBoxSexo.setModel(Mascota.getModeloSexos());
		comboBoxSexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		estado = new JPanel();
		columnaIzq.add(estado);
		estado.setBorder(new TitledBorder(null, "\u00BFC\u00F3mo desea registrar la mascota?", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		estado.setOpaque(false);
		
		radioButtonEncontrada = new JRadioButton("Encontrada");
		radioButtonEncontrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				condicionesfisicas.setVisible(false);
				panelRecompensa.setVisible(false);
			}
		});
		radioButtonEncontrada.setSelected(true);
		radioButtonEncontrada.setOpaque(false);
		radioButtonEncontrada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonGroup.add(radioButtonEncontrada);
		estado.add(radioButtonEncontrada);
		
		radioButtonPerdida = new JRadioButton("Perdida");
		radioButtonPerdida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				condicionesfisicas.setVisible(true);
				panelRecompensa.setVisible(true);
			}
		});
		buttonGroup.add(radioButtonPerdida);
		radioButtonPerdida.setOpaque(false);
		estado.add(radioButtonPerdida);
		radioButtonPerdida.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		condicionesfisicas = new JPanel();
		condicionesfisicas.setOpaque(false);
		condicionesfisicas.setBorder(new TitledBorder(null, "Condiciones F\u00EDsicas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		condicionesfisicas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		condicionesfisicas.setVisible(false);
		columnaIzq.add(condicionesfisicas);
		
		checkBoxCastrada = new JCheckBox("Castrada");
		condicionesfisicas.add(checkBoxCastrada);
		checkBoxCastrada.setOpaque(false);
		checkBoxCastrada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		checkBoxVacunada = new JCheckBox("Vacunada");
		condicionesfisicas.add(checkBoxVacunada);
		checkBoxVacunada.setOpaque(false);
		checkBoxVacunada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		checkBoxDesparacitada = new JCheckBox("Desparacitada");
		condicionesfisicas.add(checkBoxDesparacitada);
		checkBoxDesparacitada.setOpaque(false);
		checkBoxDesparacitada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		panelRecompensa = new JPanel();
		panelRecompensa.setVisible(false);
		panelRecompensa.setOpaque(false);
		columnaIzq.add(panelRecompensa);
		panelRecompensa.setLayout(new GridLayout(2, 1, 0, 0));
		
		lblRecompensa = new JLabel("Recompensa (colones)");
		panelRecompensa.add(lblRecompensa);
		lblRecompensa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
				
				campoRecompensa = new JFormattedTextField(formatter);
				panelRecompensa.add(campoRecompensa);
				campoRecompensa.setFont(new Font("Tahoma", Font.PLAIN, 13));
				campoRecompensa.setColumns(10);
				
				JPanel columnaDer = new JPanel();
				panelContenido.add(columnaDer, BorderLayout.CENTER);
				columnaDer.setOpaque(false);
				columnaDer.setLayout(new BoxLayout(columnaDer, BoxLayout.Y_AXIS));
				
				JPanel panelSecundario = new JPanel();
				panelSecundario.setBorder(new TitledBorder(null, "Detalles", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				panelSecundario.setOpaque(false);
				columnaDer.add(panelSecundario);
				panelSecundario.setLayout(new BoxLayout(panelSecundario, BoxLayout.Y_AXIS));
				
				JPanel panelDireccion = new JPanel();
				panelDireccion.setOpaque(false);
				panelSecundario.add(panelDireccion);
				panelDireccion.setLayout(new BoxLayout(panelDireccion, BoxLayout.Y_AXIS));
				
				JLabel labelDireccion = new JLabel("Direcci\u00F3n del suceso:");
				labelDireccion.setAlignmentX(Component.CENTER_ALIGNMENT);
				labelDireccion.setFont(new Font("Tahoma", Font.PLAIN, 13));
				labelDireccion.setHorizontalAlignment(SwingConstants.CENTER);
				panelDireccion.add(labelDireccion);
				
				editorDireccionSuceso = new JEditorPane();
				panelDireccion.add(editorDireccionSuceso);
				
				JPanel panelDetallesSuceso = new JPanel();
				panelDetallesSuceso.setOpaque(false);
				panelSecundario.add(panelDetallesSuceso);
				panelDetallesSuceso.setLayout(new BoxLayout(panelDetallesSuceso, BoxLayout.Y_AXIS));
				
				JLabel labelDetallesSuceso = new JLabel("Detalles del suceso:");
				labelDetallesSuceso.setHorizontalAlignment(SwingConstants.CENTER);
				panelDetallesSuceso.add(labelDetallesSuceso);
				labelDetallesSuceso.setAlignmentX(Component.CENTER_ALIGNMENT);
				labelDetallesSuceso.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
				campoDetallesSuceso = new JEditorPane();
				panelDetallesSuceso.add(campoDetallesSuceso);
				campoDetallesSuceso.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
				JPanel panelDetallesMascota = new JPanel();
				panelDetallesMascota.setOpaque(false);
				panelSecundario.add(panelDetallesMascota);
				panelDetallesMascota.setLayout(new BoxLayout(panelDetallesMascota, BoxLayout.Y_AXIS));
				
				labelDetallesMascota = new JLabel("Notas adicionales sobre la mascota:");
				labelDetallesMascota.setHorizontalAlignment(SwingConstants.CENTER);
				panelDetallesMascota.add(labelDetallesMascota);
				labelDetallesMascota.setAlignmentX(Component.CENTER_ALIGNMENT);
				labelDetallesMascota.setFont(new Font("Tahoma", Font.PLAIN, 13));
				
				campoDetallesMascota = new JEditorPane();
				campoDetallesMascota.setFont(new Font("Dialog", Font.PLAIN, 13));
				panelDetallesMascota.add(campoDetallesMascota);
				
				JPanel panelFoto = new JPanel();
				panelFoto.setOpaque(false);
				columnaDer.add(panelFoto);
				panelFoto.setBorder(new TitledBorder(null, "Foto de su Mascota", TitledBorder.CENTER, TitledBorder.TOP, null, null));
				panelFoto.setLayout(new BorderLayout(0, 0));
				
				JLabel labelFoto = new JLabel("Ninguna Seleccionada");
				labelFoto.setHorizontalAlignment(SwingConstants.CENTER);
				labelFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelFoto.add(labelFoto);
				
				JButton botonSeleccionarImagen = new JButton("Seleccionar Imagen");
				botonSeleccionarImagen.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							imagenSeleccionada = Imagenes.seleccionarImagen();
							int ancho = labelFoto.getSize().width;
							int alto = labelFoto.getSize().height;
							BufferedImage porInsertar = Imagenes.redimensionar(
									Imagenes.cargarImagen(imagenSeleccionada), ancho, alto);
							labelFoto.setText("");
							labelFoto.setIcon(new ImageIcon(porInsertar));
							fotoSeleccionada = true;
						} catch (ImagenNoEncontradaException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(),
								"Advertencia", JOptionPane.WARNING_MESSAGE);
							imagenSeleccionada = "";
							labelFoto.setIcon(null);
							labelFoto.setText("Ninguna Seleccionada");
						}
					}
				});
				botonSeleccionarImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
				panelFoto.add(botonSeleccionarImagen, BorderLayout.SOUTH);
		
		JPanel panelOperaciones = new JPanel();
		panelOperaciones.setOpaque(false);
		getContentPane().add(panelOperaciones, BorderLayout.SOUTH);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		panelOperaciones.add(botonCancelar);
		botonCancelar.setOpaque(false);
		botonCancelar.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JButton botonRegistrar = new JButton("Registrar");
		panelOperaciones.add(botonRegistrar);
		botonRegistrar.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent arg0) {
				
				if (fotoSeleccionada)
					try {
						Mascota mascota = new Mascota(
								campoNombre.getText(),
								(String) comboBoxEspecie.getSelectedItem(),
								(String)comboBoxRaza.getSelectedItem(),
								Integer.getInteger(campoRecompensa.getText() == null ? "0" : campoRecompensa.getText()));
						
						
						mascota.setImagen(imagenSeleccionada);
						mascota.setCastrada(checkBoxCastrada.isSelected());
						mascota.setColor((String) comboBoxColor.getSelectedItem());
						mascota.setDesparacitada(checkBoxDesparacitada.isSelected());
						mascota.setNumeroChip(campoNumeroDeChip.getText());
						mascota.setSexo((String) comboBoxSexo.getSelectedItem());
						mascota.setTamanio((String) comboBoxTamanio.getSelectedItem());
						mascota.setVacunada(checkBoxVacunada.isSelected());
						
						
						Suceso registro;
						if (radioButtonEncontrada.isSelected()){
							registro = new Suceso(Acceso.getUsuarioActivo().getNickname(), EstadosMascotas.estadoENCONTRADA,
									  editorDireccionSuceso.getText(), campoDetallesSuceso.getText());
							mascota.addNuevoSuceso(registro);
						}
						
						if (radioButtonPerdida.isSelected()){
							registro = new Suceso(Acceso.getUsuarioActivo().getNickname(), EstadosMascotas.estadoDESAPARECIDA,
									  editorDireccionSuceso.getText(), campoDetallesSuceso.getText());
							mascota.addNuevoSuceso(registro);
						}
						Principal.mascotas.add(mascota);
						JOptionPane.showMessageDialog(getContentPane(), 
							"Le recordamos que puede editar la información\n" +
							"de ésta mascota buscándola en la ventana de Mis mascotas.", "Registro Satisfactorio",
							 JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						
					} catch (TiempoSinEstablecerException ex) {
						JOptionPane.showMessageDialog(null,
								"No se pueden crear sucesos.\n" + ex.getMessage(),
								"ERROR INESPERADO: Tiempo del Sistema",
								JOptionPane.ERROR_MESSAGE);
					} else{
						JOptionPane.showMessageDialog(null,
								"Debe cargar una imagen antes de registrar",
								"INFORMACIÓN",
								JOptionPane.INFORMATION_MESSAGE);
					}
			}
		});
		botonRegistrar.setOpaque(false);
		botonRegistrar.setFont(new Font("Tahoma", Font.BOLD, 13));
	}
	
	public void close(){
		this.dispose();
	}
}
