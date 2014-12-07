package paws.vista;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import paws.control.Busqueda;
import paws.control.Imagenes;
import paws.control.Principal;
import paws.control.excepciones.MascotaNoEncontradaException;
import paws.control.excepciones.UsuarioNoExisteException;
import paws.modelo.Mascota;
import paws.modelo.ModeloTablaMascotas;
import paws.modelo.ModeloTablaUsuarios;
import paws.recursos.Dialogos;
import paws.recursos.Diseno;

public class VentanaBusqueda extends JFrame {

	private static final Integer altoVentanaContraida = 395;
	private static final Integer anchoVentana = 750;
	private JButton btnAyuda;
	private JButton btnBuscar;
	private JButton btnContraerVentana;
	private JButton btnVerDetalles;
	private JButton button;
	private JTextField campoApellidos;
	private JTextField campoCedula;
	private JTextField campoCorreoElectronico;
	private JTextField campoNickName;
	private JTextField campoNombreUsuario;
	private JTextField campoNumeroTelefonico;
	private JPanel campos;
	private JCheckBox checkApellidos;
	private JCheckBox checkCedula;
	private JCheckBox checkCorreoElectronico;
	private JCheckBox checkEspecie;
	private JCheckBox checkLugar;
	private JCheckBox checkMascotasAdoptadas;
	private JCheckBox checkMascotasMuertas;
	private JCheckBox checkMascotasEncontradas;
	private JCheckBox checkMascotasRefugiadas;
	private JCheckBox checkMascotasDesaparecidas;
	private JCheckBox checkNickName;
	private JCheckBox checkNombreMascota;
	private JCheckBox checkNombreUsuario;
	private JCheckBox checkNumeroChip;
	private JCheckBox checkNumeroTelefonico;
	private JCheckBox checkRaza;
	private JCheckBox checkSoloUsuariosRefugiantes;
	private JComboBox<String> comboEspecies;
	private JComboBox<String> comboRazas;
	Thread hiloBarraProgreso;
	Thread hiloExpandirVentana;
	private JLabel labelTitulo;
	private JPanel marcoCampos;
	private JPanel marcoContenidoMascotas;
	private JPanel marcoContenidoUsuarios;
	private JPanel marcoListas;
	private JPanel marcoOperaciones;
	private JPanel marcoParametrosMascota;
	private JPanel marcoParametrosUsuario;
	private JScrollPane marcoResultados;
	private JPanel marcoTituloMascotas;
	private JPanel marcoTituloUsuarios;
	private ModeloTablaMascotas modeloMascotas;
	private ModeloTablaUsuarios modeloUsuarios;
	private JPanel pestaniaMascotas;
	private JTabbedPane pestanias;
	private JPanel pestaniaUsuarios;
	private JScrollPane scrollPane;
	protected boolean soloUsusariosRefugiantes;
	private JTable tablaResultadosMascotas;
	private JTable tablaResultadosUsuarios;
	private JTextField textLugar;
	private JTextField textNombre;
	private JTextField textNumeroChip;
	boolean ventanaContraida;
	private JCheckBox checkMascotasLocalizadas;
	private JLabel labelCantidadResultadosMascotas;
	private JLabel labelTotalMascotas;
	private JLabel labelCantidadResultadosUsuarios;
	private JLabel labelTotalUsuarios;

	public VentanaBusqueda() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(anchoVentana, altoVentanaContraida);
		setIconImage(Imagenes.getIconoSistema().getImage());

		getContentPane().setBackground(Diseno.fondoVentanas);
		ventanaContraida = true;
		
		labelTitulo = new JLabel("B\u00FAsqueda");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(Diseno.fuenteTitulosVentanas);
		getContentPane().add(labelTitulo, BorderLayout.NORTH);

		pestanias = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(pestanias, BorderLayout.CENTER);

		pestaniaMascotas = new JPanel();
		pestaniaMascotas.setOpaque(false);
		pestaniaMascotas.setLayout(new BorderLayout(0, 0));
		pestaniaMascotas.setBackground(Diseno.fondoVentanas);

		marcoTituloMascotas = new JPanel();
		marcoTituloMascotas.setOpaque(false);
		pestaniaMascotas.add(marcoTituloMascotas, BorderLayout.NORTH);
		marcoTituloMascotas.setLayout(new BorderLayout(0, 0));
		// Perdidas, Encontradas, Adoptadas, Adoptables, Refugiadas

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LinkedList<String> terminos = new LinkedList<String>();
				boolean[] estadosBusqueda = new boolean[6];
				
				if (checkNombreMascota.isSelected()) {
					terminos.add(textNombre.getText());
				} else {
					terminos.add("");
				}
				if (checkLugar.isSelected()) {
					terminos.add(textLugar.getText());
				} else {
					terminos.add("");
				}
				if (checkNumeroChip.isSelected()) {
					terminos.add(textNumeroChip.getText());
				} else {
					terminos.add("");
				}
				if (checkEspecie.isSelected()) {
					terminos.add((String) comboEspecies.getSelectedItem());
					if (checkRaza.isSelected()) {
						terminos.add((String) comboRazas.getSelectedItem());
					} else {
						terminos.add("");
					}
				} else {
					// Sin especie y sin raza.
					terminos.add("");
					terminos.add("");
				}

				estadosBusqueda[0] = checkMascotasDesaparecidas.isSelected();
				estadosBusqueda[1] = checkMascotasEncontradas.isSelected();
				estadosBusqueda[2] = checkMascotasRefugiadas.isSelected();
				estadosBusqueda[3] = checkMascotasLocalizadas.isSelected();
				estadosBusqueda[4] = checkMascotasAdoptadas.isSelected();
				estadosBusqueda[5] = checkMascotasMuertas.isSelected();

				if (!estadosBusqueda[0] && !estadosBusqueda[1] && !estadosBusqueda[2] &&
					!estadosBusqueda[3] && !estadosBusqueda[4] && !estadosBusqueda[5])
				{
					JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar al menos un estado posible para realizar la búsqueda.");
					modeloMascotas = new ModeloTablaMascotas(Busqueda.buscarMascotas(terminos, estadosBusqueda));
					tablaResultadosMascotas.setModel(modeloMascotas);
					tablaResultadosMascotas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					tablaResultadosMascotas.setAutoCreateRowSorter(true);
					tablaResultadosMascotas.setVisible(true);
					labelCantidadResultadosMascotas.setText("Cantidad de resultados: " + modeloMascotas.getCantidadResultados());

					if (ventanaContraida) {
						expandirVentana();
					}
				} else {
					labelCantidadResultadosMascotas.setText("");
				}
				SwingUtilities.updateComponentTreeUI(getContentPane());
			}

		});
		marcoTituloMascotas.add(btnBuscar, BorderLayout.EAST);
		
		labelTotalMascotas = new JLabel("Total de Mascotas Registradas: " + Principal.mascotas.size());
		labelTotalMascotas.setHorizontalAlignment(SwingConstants.CENTER);
		marcoTituloMascotas.add(labelTotalMascotas, BorderLayout.WEST);
		
		labelCantidadResultadosMascotas = new JLabel("");
		labelCantidadResultadosMascotas.setHorizontalAlignment(SwingConstants.CENTER);
		marcoTituloMascotas.add(labelCantidadResultadosMascotas, BorderLayout.CENTER);

		marcoContenidoMascotas = new JPanel();
		marcoContenidoMascotas.setOpaque(false);
		pestaniaMascotas.add(marcoContenidoMascotas, BorderLayout.CENTER);
		marcoContenidoMascotas.setLayout(new BorderLayout(0, 0));

		marcoParametrosMascota = new JPanel();
		marcoParametrosMascota.setOpaque(false);
		marcoParametrosMascota.setLayout(new BorderLayout(0, 0));
		marcoContenidoMascotas.add(marcoParametrosMascota, BorderLayout.NORTH);

		marcoCampos = new JPanel();
		marcoCampos.setOpaque(false);
		marcoCampos.setBorder(new TitledBorder(null, "Par\u00E1metros de B\u00FAsqueda", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		marcoCampos.setLayout(new GridLayout(5, 2, 0, 0));
		marcoParametrosMascota.add(marcoCampos, BorderLayout.CENTER);

		checkNombreMascota = new JCheckBox("Nombre");
		checkNombreMascota.setOpaque(false);
		checkNombreMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNombreMascota.isSelected()) {
					textNombre.setEnabled(true);
				} else {
					textNombre.setText("");
					textNombre.setEnabled(false);
				}
			}
		});
		marcoCampos.add(checkNombreMascota);

		textNombre = new JTextField();
		textNombre.setEnabled(false);
		textNombre.setColumns(10);
		marcoCampos.add(textNombre);

		checkLugar = new JCheckBox("Lugar P\u00E9rdida / Encuentro");
		checkLugar.setOpaque(false);
		checkLugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkLugar.isSelected()) {
					textLugar.setEnabled(true);
				} else {
					textLugar.setText("");
					textLugar.setEnabled(false);
				}
			}
		});
		marcoCampos.add(checkLugar);

		textLugar = new JTextField();
		textLugar.setEnabled(false);
		textLugar.setColumns(10);
		marcoCampos.add(textLugar);

		checkNumeroChip = new JCheckBox("N\u00FAmero de Chip");
		checkNumeroChip.setOpaque(false);
		checkNumeroChip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNumeroChip.isSelected()) {
					textNumeroChip.setEnabled(true);
				} else {
					textNumeroChip.setText("");
					textNumeroChip.setEnabled(false);
				}
			}
		});
		marcoCampos.add(checkNumeroChip);

		textNumeroChip = new JTextField();
		textNumeroChip.setEnabled(false);
		textNumeroChip.setColumns(10);
		marcoCampos.add(textNumeroChip);

		checkEspecie = new JCheckBox("Especie");
		checkEspecie.setOpaque(false);
		checkEspecie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkEspecie.isSelected()) {
					comboEspecies.setEnabled(true);
					checkRaza.setEnabled(true);
					comboEspecies.setModel(new DefaultComboBoxModel<String>(Mascota.getEspecies()
							.toArray(new String[Mascota.getEspecies().size()])));
				} else {
					comboEspecies.setEnabled(false);
					comboEspecies.setModel(new DefaultComboBoxModel<String>());
					checkRaza.setSelected(false);
					checkRaza.setEnabled(false);
					comboRazas.setModel(new DefaultComboBoxModel<String>());
				}
				comboRazas.setEnabled(false);
			}
		});
		marcoCampos.add(checkEspecie);

		comboEspecies = new JComboBox<String>();
		comboEspecies.setEnabled(false);
		marcoCampos.add(comboEspecies);

		checkRaza = new JCheckBox("Raza");
		checkRaza.setOpaque(false);
		checkRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkRaza.isSelected()) {
					comboRazas.setEnabled(true);
					comboRazas.setModel(new DefaultComboBoxModel<String>( (String[]) Mascota.getRazas().get(comboEspecies
							.getSelectedIndex()).toArray()));
				} else {
					comboRazas.setModel(new DefaultComboBoxModel<String>());
					comboRazas.setEnabled(false);
				}
			}
		});
		checkRaza.setEnabled(false);
		marcoCampos.add(checkRaza);

		comboRazas = new JComboBox<String>();
		comboRazas.setEnabled(false);
		marcoCampos.add(comboRazas);

		marcoListas = new JPanel();
		marcoListas.setOpaque(false);
		marcoListas.setBorder(new TitledBorder(null, "\u00BFD\u00F3nde desea buscar?", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) marcoListas.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		marcoParametrosMascota.add(marcoListas, BorderLayout.SOUTH);

		checkMascotasDesaparecidas = new JCheckBox("Desaparecidas");
		checkMascotasDesaparecidas.setOpaque(false);
		marcoListas.add(checkMascotasDesaparecidas);

		checkMascotasEncontradas = new JCheckBox("Encontradas por otro usuario");
		checkMascotasEncontradas.setOpaque(false);
		checkMascotasEncontradas.setSelected(true);
		marcoListas.add(checkMascotasEncontradas);
		
		checkMascotasLocalizadas = new JCheckBox("Localizadas por el due\u00F1o");
		checkMascotasLocalizadas.setSelected(true);
		checkMascotasLocalizadas.setOpaque(false);
		marcoListas.add(checkMascotasLocalizadas);

		checkMascotasAdoptadas = new JCheckBox("Adoptadas");
		checkMascotasAdoptadas.setOpaque(false);
		checkMascotasAdoptadas.setSelected(true);
		marcoListas.add(checkMascotasAdoptadas);

		checkMascotasRefugiadas = new JCheckBox("Refugiadas");
		checkMascotasRefugiadas.setOpaque(false);
		checkMascotasRefugiadas.setSelected(true);
		marcoListas.add(checkMascotasRefugiadas);
		
				checkMascotasMuertas = new JCheckBox("Muertas");
				checkMascotasMuertas.setOpaque(false);
				marcoListas.add(checkMascotasMuertas);
				checkMascotasMuertas.setSelected(true);

		marcoResultados = new JScrollPane();
		marcoResultados.setOpaque(false);
		marcoContenidoMascotas.add(marcoResultados, BorderLayout.CENTER);

		tablaResultadosMascotas = new JTable();
		tablaResultadosMascotas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaResultadosMascotas.setShowHorizontalLines(true);
		tablaResultadosMascotas.setShowVerticalLines(true);
		marcoResultados.setViewportView(tablaResultadosMascotas);

		pestaniaUsuarios = new JPanel();
		pestaniaUsuarios.setOpaque(false);
		pestaniaUsuarios.setLayout(new BorderLayout(0, 0));
		pestaniaUsuarios.setBackground(Diseno.fondoVentanas);


		marcoOperaciones = new JPanel();
		marcoOperaciones.setOpaque(false);
		getContentPane().add(marcoOperaciones, BorderLayout.SOUTH);
		marcoOperaciones.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
						
								btnAyuda = new JButton("\u00A1Necesito Ayuda!");
								btnAyuda.setOpaque(false);
								btnAyuda.setBackground(Diseno.fondoVentanas);
								marcoOperaciones.add(btnAyuda);
								btnAyuda.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent arg0) {
										mostrarMensajeAyuda();
										
									}
								});
				
						btnContraerVentana = new JButton("Ocultar Resultados");
						btnContraerVentana.setOpaque(false);
						marcoOperaciones.add(btnContraerVentana);
						btnContraerVentana.setVisible(false);
						btnContraerVentana.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								contraerVentana();
							}
						});

		btnVerDetalles = new JButton("Ver Detalles");
		btnVerDetalles.setOpaque(false);
		btnVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (pestanias.getSelectedIndex() == 0){
					int fila = tablaResultadosUsuarios.getSelectedRow();
					if (fila != -1){
						String nick = (String) tablaResultadosUsuarios.getValueAt(fila, 0);						
						try {
							Principal.coordinador.mostrarDetallesUsuario(Principal.getUsuario(nick));
						} catch (UsuarioNoExisteException e) {
							JOptionPane.showMessageDialog(getContentPane(), "error", e.getMessage(), JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Información", "Debe seleccionar un usuario primero", JOptionPane.INFORMATION_MESSAGE);			
					}
					
				}
				
				if (pestanias.getSelectedIndex() == 1){
					int fila = tablaResultadosMascotas.getSelectedRow();
					if (fila != -1){
						String IDMascota = (String) tablaResultadosMascotas.getValueAt(fila, 0);
						try {
							Principal.coordinador.mostrarDetallesMascota(Principal.getMascotaID(Integer.parseInt(IDMascota)));
						} catch (MascotaNoEncontradaException e) {
							JOptionPane.showMessageDialog(getContentPane(),  e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Información", "Debe seleccionar una mascota primero", JOptionPane.INFORMATION_MESSAGE);			
					}
					
				}
				
			}
		});
		marcoOperaciones.add(btnVerDetalles);

		pestanias.addTab("Usuarios", null, pestaniaUsuarios, null);
		
		marcoTituloUsuarios = new JPanel();
		marcoTituloUsuarios.setOpaque(false);
		pestaniaUsuarios.add(marcoTituloUsuarios, BorderLayout.NORTH);
		marcoTituloUsuarios.setLayout(new BorderLayout(0, 0));
		
		button = new JButton("Buscar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LinkedList<String> terminos = new LinkedList<String>();
				
				if (checkNickName.isSelected()) {
					terminos.add(campoNickName.getText());
				} else {
					terminos.add("");
				}
				
				if (checkNombreUsuario.isSelected()) {
					terminos.add(campoNombreUsuario.getText());
				} else {
					terminos.add("");
				}
				
				if (checkApellidos.isSelected()) {
					terminos.add(campoApellidos.getText());
				} else {
					terminos.add("");
				}
				
				if (checkCedula.isSelected()) {
					terminos.add(campoCedula.getText());
				} else {
					terminos.add("");
				}
				
				if (checkNumeroTelefonico.isSelected()) {
					terminos.add(campoNumeroTelefonico.getText());
				} else {
					terminos.add("");
				}
				
				if (checkCorreoElectronico.isSelected()) {
					terminos.add(campoCorreoElectronico.getText());
				} else {
					terminos.add("");
				}

				soloUsusariosRefugiantes = checkSoloUsuariosRefugiantes.isSelected();

				modeloUsuarios = new ModeloTablaUsuarios(Busqueda.buscarUsuarios(terminos, soloUsusariosRefugiantes));
				tablaResultadosUsuarios.setModel(modeloUsuarios);
				tablaResultadosUsuarios.setVisible(true);
				tablaResultadosUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				tablaResultadosUsuarios.setAutoCreateRowSorter(true);
				labelCantidadResultadosUsuarios.setText("Cantidad de resultados: " + modeloUsuarios.getCantidadResultados());

				if (ventanaContraida) {
					expandirVentana();
				}
				SwingUtilities.updateComponentTreeUI(getContentPane());
			}
		});
		marcoTituloUsuarios.add(button, BorderLayout.EAST);
		
		labelTotalUsuarios = new JLabel("Total de Usuarios Registrados: " + Principal.usuarios.size());
		labelTotalUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		marcoTituloUsuarios.add(labelTotalUsuarios, BorderLayout.WEST);
		
		labelCantidadResultadosUsuarios = new JLabel("");
		labelCantidadResultadosUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
		marcoTituloUsuarios.add(labelCantidadResultadosUsuarios, BorderLayout.CENTER);
		
		marcoContenidoUsuarios = new JPanel();
		marcoContenidoUsuarios.setOpaque(false);
		pestaniaUsuarios.add(marcoContenidoUsuarios, BorderLayout.CENTER);
		marcoContenidoUsuarios.setLayout(new BorderLayout(0, 0));
		
		marcoParametrosUsuario = new JPanel();
		marcoParametrosUsuario.setOpaque(false);
		marcoContenidoUsuarios.add(marcoParametrosUsuario, BorderLayout.NORTH);
		marcoParametrosUsuario.setLayout(new BorderLayout(0, 0));
		
		campos = new JPanel();
		campos.setOpaque(false);
		campos.setBorder(new TitledBorder(null, "Parametros de busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		marcoParametrosUsuario.add(campos);
		campos.setLayout(new GridLayout(7, 2, 0, 0));
		
		checkNickName = new JCheckBox("NickName");
		checkNickName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNickName.isSelected()) {
					campoNickName.setEnabled(true);
				} else {
					campoNickName.setText("");
					campoNickName.setEnabled(false);
				}
			}
		});
		campos.add(checkNickName);
		
		campoNickName = new JTextField();
		campoNickName.setEnabled(false);
		campos.add(campoNickName);
		campoNickName.setColumns(10);
		
		checkNombreUsuario = new JCheckBox("Nombre");
		checkNombreUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNombreUsuario.isSelected()) {
					campoNombreUsuario.setEnabled(true);
				} else {
					campoNombreUsuario.setText("");
					campoNombreUsuario.setEnabled(false);
				}
			}
		});
		campos.add(checkNombreUsuario);
		
		campoNombreUsuario = new JTextField();
		campoNombreUsuario.setEnabled(false);
		campoNombreUsuario.setColumns(10);
		campos.add(campoNombreUsuario);
		
		checkApellidos = new JCheckBox("Apellidos");
		checkApellidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkApellidos.isSelected()) {
					campoApellidos.setEnabled(true);
				} else {
					campoApellidos.setText("");
					campoApellidos.setEnabled(false);
				}
			}
		});
		campos.add(checkApellidos);
		
		campoApellidos = new JTextField();
		campoApellidos.setEnabled(false);
		campoApellidos.setColumns(10);
		campos.add(campoApellidos);
		
		checkCedula = new JCheckBox("C\u00E9dula");
		checkCedula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkCedula.isSelected()) {
					campoCedula.setEnabled(true);
				} else {
					campoCedula.setText("");
					campoCedula.setEnabled(false);
				}
			}
		});
		campos.add(checkCedula);
		
		campoCedula = new JTextField();
		campoCedula.setEnabled(false);
		campoCedula.setColumns(10);
		campos.add(campoCedula);
		
		checkNumeroTelefonico = new JCheckBox("N\u00FAmero Telef\u00F3nico");
		checkNumeroTelefonico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkNumeroTelefonico.isSelected()) {
					campoNumeroTelefonico.setEnabled(true);
				} else {
					campoNumeroTelefonico.setText("");
					campoNumeroTelefonico.setEnabled(false);
				}
			}
		});
		campos.add(checkNumeroTelefonico);
		
		campoNumeroTelefonico = new JTextField();
		campoNumeroTelefonico.setEnabled(false);
		campoNumeroTelefonico.setColumns(10);
		campos.add(campoNumeroTelefonico);
		
		checkCorreoElectronico = new JCheckBox("Correo Electr\u00F3nico");
		checkCorreoElectronico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkCorreoElectronico.isSelected()) {
					campoCorreoElectronico.setEnabled(true);
				} else {
					campoCorreoElectronico.setText("");
					campoCorreoElectronico.setEnabled(false);
				}
				
			}
		});
		campos.add(checkCorreoElectronico);
		
		campoCorreoElectronico = new JTextField();
		campoCorreoElectronico.setEnabled(false);
		campoCorreoElectronico.setColumns(10);
		campos.add(campoCorreoElectronico);
		
		checkSoloUsuariosRefugiantes = new JCheckBox("Debe ser refugiante");
		checkSoloUsuariosRefugiantes.setHorizontalAlignment(SwingConstants.LEFT);
		campos.add(checkSoloUsuariosRefugiantes);
		
		scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		marcoContenidoUsuarios.add(scrollPane);
		
		tablaResultadosUsuarios = new JTable();
		tablaResultadosUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tablaResultadosUsuarios);
		pestanias.addTab("Mascotas", null, pestaniaMascotas, null);

	}

//	private void actualizarBarraProgreso() {
//		for (Integer i = 0; i < modeloMascotas.getCantidadDeResultados(); i++) {
//			try {
//				progressBar.setValue(i);
//				Integer mascotas = i + 1;
//				progressBar.setString(mascotas.toString().concat(" MascotasEncontradas"));
//				java.lang.Thread.sleep(100);
//
//			} catch (InterruptedException e) {
//			}
//		}
//	}

	public void busquedaGeneral(){
		pestanias.addTab("Usuarios", null, pestaniaUsuarios, null);
		pestanias.addTab("Mascotas", null, pestaniaMascotas, null);
	}

	public void busquedaRefugiantes() {
		pestanias.addTab("Usuarios", null, pestaniaUsuarios, null);
	}

	private void expandirVentana() {
		// Expande la ventana
		for (int i = 0; i < 100; i++) {
			// Cada iteración expande la ventana 2 píxeles hasta
			// un máximo de 200 píxeles estirados
			setSize(anchoVentana, getHeight() + 2);
		}
		ventanaContraida = false;
		btnContraerVentana.setVisible(true);
	}
	
	private void contraerVentana() {
		if (!ventanaContraida) {
			// Contrae la ventana un píxel a la vez
			for (int i = 0; i < 100; i++) {
				setSize(anchoVentana, getHeight() - 2);
			}
			ventanaContraida = true;
			btnContraerVentana.setVisible(false);
		}
	}
	
	
	protected void mostrarMensajeAyuda() {
		try {
			JOptionPane.showMessageDialog(getContentPane(),
			Dialogos.getAyudaBusqueda(),
			"Ayuda", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(getContentPane(),
			"No se encuentra el documento con las condiciones de uso. \n" + 
			"Por favor contacte al equipo de desarrollo PAWS al       \n" +
			"siguiente correo para recibir asistencia: pawsconsultas@gmail.com",
			"Error inesperado del sistema.", JOptionPane.ERROR_MESSAGE);
		}
	}
}
