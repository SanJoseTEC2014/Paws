package paws.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import paws.control.*;
import paws.modelo.*;
import paws.recursos.Diseno;

@SuppressWarnings("serial")
public class VentanaCondicionesRefugio extends JFrame {
	private JEditorPane textDetalles;
	private JButton botonGuardarCambios;
	private JCheckBox checkSoloDesparacitada;
	private JCheckBox checkSoloCastrada;
	private JCheckBox checkSoloVacunada;
	private JCheckBox checkNecesitaAlimentos;
	private Usuario usuarioSeleccionado;
	private JPanel checks;
	private JLabel textNecesitaAlimentos;
	private JLabel textSoloVacunadas;
	private JLabel textSoloCastradas;
	private JLabel textSoloDesparacitada;
	private JPanel labels;
	private JPanel panel_2;
	private JPanel titulo;
	private JLabel labelCondicionesRefugio;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel opciones;
	
	public VentanaCondicionesRefugio() {
		
		setSize(500, 320);
		setIconImage(Imagenes.getIconoSistema().getImage());
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(Diseno.fondoVentanas);
		titulo = new JPanel();
		titulo.setOpaque(false);
		getContentPane().add(titulo, BorderLayout.NORTH);
		titulo.setLayout(new GridLayout(2, 1, 0, 0));
		
		labelCondicionesRefugio = new JLabel("Condiciones");
		titulo.add(labelCondicionesRefugio);
		labelCondicionesRefugio.setHorizontalAlignment(SwingConstants.CENTER);
		labelCondicionesRefugio.setFont(Diseno.fuenteTitulosVentanas);
			
		lblNewLabel = new JLabel("Los requisitos para refugiar unas mascota son:");
		lblNewLabel.setEnabled(false);
		titulo.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setOpaque(false);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		panel_1.setOpaque(false);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		opciones = new JPanel();
		opciones.setOpaque(false);
		panel_1.add(opciones);
		opciones.setLayout(new BorderLayout(0, 0));
		
		checks = new JPanel();
		checks.setOpaque(false);
		opciones.add(checks, BorderLayout.WEST);
		checks.setAlignmentX(Component.RIGHT_ALIGNMENT);
		checks.setLayout(new GridLayout(0, 1, 0, 0));
		
		checkNecesitaAlimentos = new JCheckBox("");
		checkNecesitaAlimentos.setEnabled(false);
		checks.add(checkNecesitaAlimentos);
		
		checkSoloVacunada = new JCheckBox("");
		checks.add(checkSoloVacunada);
		checkSoloVacunada.setEnabled(false);
		
		checkSoloCastrada = new JCheckBox("");
		checks.add(checkSoloCastrada);
		checkSoloCastrada.setEnabled(false);
		
		checkSoloDesparacitada = new JCheckBox("");
		checks.add(checkSoloDesparacitada);
		checkSoloDesparacitada.setEnabled(false);
		
		labels = new JPanel();
		labels.setOpaque(false);
		opciones.add(labels, BorderLayout.CENTER);
		labels.setLayout(new GridLayout(0, 1, 0, 0));
		
		textNecesitaAlimentos = new JLabel("Se debe entregar alimento para la mascota");
		labels.add(textNecesitaAlimentos);
		
		textSoloVacunadas = new JLabel("La mascota debe estar vacunada");
		labels.add(textSoloVacunadas);
		
		textSoloCastradas = new JLabel("La mascota debe estar Castrada");
		labels.add(textSoloCastradas);
		
		textSoloDesparacitada = new JLabel("La mascota debe estar desparacitada");
		labels.add(textSoloDesparacitada);
		
		panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBorder(new TitledBorder(null, "Detalles Adicionales", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(59, 59, 59)));
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		textDetalles = new JEditorPane();
		panel_2.add(textDetalles);
		textDetalles.setEditable(false);
		textDetalles.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		botonGuardarCambios = new JButton("Guardar cambios");
		botonGuardarCambios.setOpaque(false);
		getContentPane().add(botonGuardarCambios, BorderLayout.SOUTH);
		botonGuardarCambios.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarCambios();
			}
		});
	}
	protected void guardarCambios() {
		Acceso.getUsuarioActivo().getCondicionesRefugio().setDetallesAdicionales(textDetalles.getText());
		Acceso.getUsuarioActivo().getCondicionesRefugio().setSoloDesparacitada(checkSoloDesparacitada.isSelected());
		Acceso.getUsuarioActivo().getCondicionesRefugio().setSoloCastrada(checkSoloCastrada.isSelected());
		Acceso.getUsuarioActivo().getCondicionesRefugio().setSoloVacunada(checkSoloVacunada.isSelected());
		Acceso.getUsuarioActivo().getCondicionesRefugio().setNecesitaAlimentos(checkNecesitaAlimentos.isSelected());
		setVisible(false);
	}
	public void setDatos(Usuario pUsuarioSeleccionado) {
		
		this.usuarioSeleccionado = pUsuarioSeleccionado;
		
		textDetalles.setText(usuarioSeleccionado.getCondicionesRefugio().getDetallesAdicionales() == null? "" : usuarioSeleccionado.getCondicionesRefugio().getDetallesAdicionales());
		
		botonGuardarCambios.setVisible(usuarioSeleccionado == Acceso.getUsuarioActivo());

		checkSoloDesparacitada.setSelected(usuarioSeleccionado.getCondicionesRefugio().isSoloDesparacitada());

		checkSoloCastrada.setSelected(usuarioSeleccionado.getCondicionesRefugio().isSoloDesparacitada());

		checkSoloVacunada.setSelected(usuarioSeleccionado.getCondicionesRefugio().isSoloVacunada());

		checkNecesitaAlimentos.setSelected(usuarioSeleccionado.getCondicionesRefugio().isNecesitaAlimentos());
	}
	
	public void setModoEdicion(boolean opcion) {
		
		textDetalles.setEditable(opcion);
		
		checkSoloDesparacitada.setEnabled(opcion);

		checkSoloCastrada.setEnabled(opcion);

		checkSoloVacunada.setEnabled(opcion);

		checkNecesitaAlimentos.setEnabled(opcion);

	}
	

}
