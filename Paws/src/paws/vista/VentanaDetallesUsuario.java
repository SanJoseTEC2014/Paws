package paws.vista;

import java.awt.*;
import java.text.*;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.border.*;

import paws.control.Acceso;
import paws.control.Imagenes;
import paws.control.Principal;
import paws.modelo.*;
import paws.recursos.Diseno;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class VentanaDetallesUsuario extends JFrame {
	
	private JButton botonCondicionesRefugio;
	private JButton botonDetalles;
	private JButton botonGuardarCambios;
	private JButton botonSalir;
	private JButton botonVerMascotas;
	private JButton btnActualizarFoto;
	private JComboBox<String> comboLapsos;
	private JFormattedTextField formatCedula;
	private JFormattedTextField formatTelefono;
	private JLabel labelApellidos;
	private JLabel labelCedula;
	private JLabel labelCorreo;
	private JLabel labelEstrellas;
	private JLabel labelFoto;
	private JLabel labelLapsos;
	private JLabel labelNickname;
	private JLabel labelNombre;
	private JLabel labelPromedio;
	private JLabel labelTelefono; 
	private JLabel labelTitulo1;
	private JLabel labelTitulo2;
	private JPanel marcoContenido;
	private JPanel marcoDetalles;
	private JPanel marcoFotoPerfil;
	private JPanel marcoOperaciones;
	private JPanel marcoTitulos;
	private boolean modoEdicion;
	private JTextField textApellidos;
	
	private JTextField textCorreo;
	private JTextField textNickname;
	private JTextField textNombre;
	private Usuario usuarioActual;
	private JButton btnAgregarCalificacion;

	public VentanaDetallesUsuario(){
		setSize(800,500);
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		marcoTitulos = new JPanel();
		marcoTitulos.setLayout(new BorderLayout(0, 0));
		marcoTitulos.setOpaque(false);
		getContentPane().add(marcoTitulos, BorderLayout.NORTH);
		
				labelTitulo1 = new JLabel("Información");
				labelTitulo1.setFont(Diseno.fuenteTitulosVentanas);
				labelTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
				labelTitulo1.setOpaque(false);
				marcoTitulos.add(labelTitulo1, BorderLayout.NORTH);
				
				labelTitulo2 = new JLabel("Contacto");
				labelTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
				labelTitulo2.setFont(Diseno.fuenteTitulosVentanas);
				labelTitulo2.setOpaque(false);
				marcoTitulos.add(labelTitulo2, BorderLayout.SOUTH);
				
		marcoContenido = new JPanel();
		marcoContenido.setLayout(new BorderLayout(0, 0));
		marcoContenido.setOpaque(false);
		getContentPane().add(marcoContenido, BorderLayout.CENTER);
				
				marcoFotoPerfil = new JPanel();
				marcoFotoPerfil.setBorder(new TitledBorder(null, "Foto de Perfil", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				marcoFotoPerfil.setLayout(new BoxLayout(marcoFotoPerfil, BoxLayout.Y_AXIS));
				marcoFotoPerfil.setOpaque(false);
				marcoContenido.add(marcoFotoPerfil, BorderLayout.WEST);
				
						labelFoto = new JLabel("No disponible");
						labelFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
						labelFoto.setOpaque(false);
						marcoFotoPerfil.add(labelFoto);
						
						btnActualizarFoto = new JButton("Actualizar Foto");
						btnActualizarFoto.setAlignmentX(Component.CENTER_ALIGNMENT);
						btnActualizarFoto.setOpaque(false);
						marcoFotoPerfil.add(btnActualizarFoto);
				
				marcoDetalles = new JPanel();
				marcoDetalles.setBorder(new TitledBorder(null, "Detalles:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				marcoDetalles.setLayout(new GridLayout(0, 2, 3, 3));
				marcoDetalles.setOpaque(false);
				marcoContenido.add(marcoDetalles, BorderLayout.CENTER);
				
						labelNickname = new JLabel("Nickname: ");
						labelNickname.setOpaque(false);
						marcoDetalles.add(labelNickname);
						
						textNickname = new JTextField();
						textNickname.setEditable(false);
						textNickname.setHorizontalAlignment(SwingConstants.CENTER);
						textNickname.setColumns(10);
						marcoDetalles.add(textNickname);
						
						labelNombre = new JLabel("Nombre: ");
						labelNombre.setOpaque(false);
						marcoDetalles.add(labelNombre);
						
						textNombre = new JTextField();
						textNombre.setHorizontalAlignment(SwingConstants.CENTER);
						textNombre.setColumns(10);
						marcoDetalles.add(textNombre);
						
						labelApellidos = new JLabel("Apellidos:");
						labelApellidos.setOpaque(false);
						marcoDetalles.add(labelApellidos);
						
						textApellidos = new JTextField();
						textApellidos.setHorizontalAlignment(SwingConstants.CENTER);
						textApellidos.setColumns(10);
						marcoDetalles.add(textApellidos);
						
						labelCedula = new JLabel("N\u00FAmero de C\u00E9dula: ");
						labelCedula.setOpaque(false);
						marcoDetalles.add(labelCedula);
						
						try {
							formatCedula = new JFormattedTextField(new MaskFormatter("#########"));
							formatCedula.setHorizontalAlignment(SwingConstants.CENTER);
							marcoDetalles.add(formatCedula);
						} catch (ParseException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
							JLabel error = new JLabel("Error inesperado en el sistema."); 
							error.setOpaque(false);
							marcoDetalles.add(error);
						}
						
						labelTelefono = new JLabel("N\u00FAmero de Tel\u00E9fono:");
						labelTelefono.setOpaque(false);
						marcoDetalles.add(labelTelefono);
						
						try {
							formatTelefono = new JFormattedTextField(new MaskFormatter("########"));
							formatTelefono.setHorizontalAlignment(SwingConstants.CENTER);
							marcoDetalles.add(formatTelefono);
						} catch (ParseException e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
							marcoDetalles.add(new JLabel("Error inesperado en el sistema."));
						}
						
						labelCorreo = new JLabel("Correo Electr\u00F3nico:");
						labelCorreo.setOpaque(false);
						marcoDetalles.add(labelCorreo);
						
						textCorreo = new JTextField();
						textCorreo.setHorizontalAlignment(SwingConstants.CENTER);
						marcoDetalles.add(textCorreo);
						
						labelPromedio = new JLabel("Promedio de Calificaciones: ");
						labelPromedio.setOpaque(false);
						marcoDetalles.add(labelPromedio);
						
						labelEstrellas = new JLabel("");
						labelEstrellas.setHorizontalAlignment(SwingConstants.CENTER);
						labelEstrellas.setOpaque(false);
						marcoDetalles.add(labelEstrellas);
						
						labelLapsos = new JLabel("Peridiocidad de Emparejamientos");
						labelLapsos.setOpaque(false);
						marcoDetalles.add(labelLapsos);
						
						comboLapsos = new JComboBox<String>();
						// Esto permite que cuando el ComboBox esté desactivado, pueda
						// seguir viéndose el texto del lapso del Usuario claramente.
						comboLapsos.setRenderer(new DefaultListCellRenderer(){
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
						// Aquí se obtienen los datos directamente del Modelo
						String[] lapsos = (String[]) Usuario.lapsos.toArray();
						comboLapsos.setModel(new DefaultComboBoxModel<String>(lapsos));
						marcoDetalles.add(comboLapsos);
				
		marcoOperaciones = new JPanel();
		marcoOperaciones.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		marcoOperaciones.setOpaque(false);
		getContentPane().add(marcoOperaciones, BorderLayout.SOUTH);
				
				botonGuardarCambios = new JButton("Guardar Cambios");
				botonGuardarCambios.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Acceso.getUsuarioActivo().setApellidos(textApellidos.getText());
						Acceso.getUsuarioActivo().setNombre(textNombre.getText());
						Acceso.getUsuarioActivo().setCorreo(textCorreo.getText());
						Acceso.getUsuarioActivo().setCedula(Integer.parseInt(formatCedula.getText()));
						Acceso.getUsuarioActivo().setTelefono(Integer.parseInt(formatTelefono.getText()));
						Acceso.getUsuarioActivo().setLapsoEmparejamiento((String)comboLapsos.getSelectedItem());
					}
				});
				
				btnAgregarCalificacion = new JButton("Agregar Calificacion");
				btnAgregarCalificacion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Principal.coordinador.mostrarAgregarComentario(usuarioActual);
					}
				});
				marcoOperaciones.add(btnAgregarCalificacion);
				marcoOperaciones.add(botonGuardarCambios);
				
				botonCondicionesRefugio = new JButton("Ver Condiciones de Refugio");
				botonCondicionesRefugio.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Principal.coordinador.mostrarCondicionesRefugio(usuarioActual);
					}
				});
				marcoOperaciones.add(botonCondicionesRefugio);
				
				botonDetalles = new JButton("Ver Detalles de Calificaciones");
				botonDetalles.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Principal.coordinador.mostrarCalificaciones(usuarioActual);
					}
				});
				marcoOperaciones.add(botonDetalles);
				
				botonVerMascotas = new JButton("Ver Mascotas Asociadas");
				botonVerMascotas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Principal.coordinador.mostrarMascotasAsociadas(usuarioActual);
					}
				});
				marcoOperaciones.add(botonVerMascotas);
				
				botonSalir = new JButton("Salir");
				botonSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						close();
					}
				});
				marcoOperaciones.add(botonSalir);
	}
	
	private void close(){
		setVisible(false);
	}
	
	public void setDatosIniciales(Usuario pUsuario) {
		
		botonCondicionesRefugio.setVisible(pUsuario.isRefugiante());
		
		usuarioActual = pUsuario;
		textNickname.setText(usuarioActual.getNickname());
		textNombre.setText(usuarioActual.getNombre());
		textApellidos.setText(usuarioActual.getApellidos());
		formatCedula.setText(usuarioActual.getCedula().toString());
		formatTelefono.setText(usuarioActual.getTelefono().toString());
		textCorreo.setText(usuarioActual.getCorreo());
		try {
			labelEstrellas.setIcon(new ImageIcon(Imagenes.getEstrellas(usuarioActual.getPonderadoCalificacion())));
		} catch (IOException e) {
			labelEstrellas.setText("Error inesperado al cargar imagen. \n" +
								   "Ponderado: " + usuarioActual.getPonderadoCalificacion());
		}
		comboLapsos.setSelectedIndex(Usuario.lapsos.indexOf(usuarioActual.getLapsoEmparejamiento()));
		botonCondicionesRefugio.setVisible(usuarioActual.isRefugiante());
		btnAgregarCalificacion.setVisible(usuarioActual != Acceso.getUsuarioActivo());
	}
	
	public void setModoEdicion(boolean pModo){
		modoEdicion = pModo;
		textNombre.setEditable(modoEdicion);
		textApellidos.setEditable(modoEdicion);
		formatCedula.setEditable(modoEdicion);
		formatTelefono.setEditable(modoEdicion);
		textCorreo.setEditable(modoEdicion);
		//comboLapsos.setEditable(modoEdicion);
		comboLapsos.setEnabled(modoEdicion);
		//botonGuardarCambios.setEnabled(modoEdicion);
		botonGuardarCambios.setVisible(modoEdicion);
		if (usuarioActual == Acceso.getUsuarioActivo()) {botonCondicionesRefugio.setText("Editar mis condiciones registro");}
	}
}
