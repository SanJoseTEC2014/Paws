package paws.vista;

import java.awt.*;

import javax.swing.*;

import paws.control.Acceso;
import paws.control.Emparejador;
import paws.control.Principal;
import paws.control.excepciones.MascotaNoEncontradaException;
import paws.control.excepciones.UsuarioNoExisteException;
import paws.modelo.Mascota;
import paws.modelo.ModeloTablaMascotas;
import paws.modelo.Usuario;
import paws.recursos.Diseno;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class VentanaMascotasDeUsuario extends JFrame {
	private JTable tablaMascotas;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Usuario usuarioActivo;
	private JRadioButton rdbtnPerdidas;
	private JRadioButton rdbtnEncontradas;
	private JRadioButton rdbtnAdoptadas;
	private JRadioButton rdbtnRefugiadas;
	private JButton botonDetalles;
	private JButton botonEmparejamiento;
	private JLabel labelTitulo;
	private JPanel radios;
	private JPanel panelContenido;
	
	public VentanaMascotasDeUsuario() {
		setSize(500, 300);
		getContentPane().setBackground(Diseno.fondoVentanas);
		labelTitulo = new JLabel("Mis Mascotas");
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setFont(Diseno.fuenteTitulosVentanas);
		getContentPane().add(labelTitulo, BorderLayout.NORTH);
		
		panelContenido = new JPanel();
		panelContenido.setOpaque(false);
		getContentPane().add(panelContenido, BorderLayout.CENTER);
		panelContenido.setLayout(new BorderLayout(0, 0));
		
		radios = new JPanel();
		radios.setOpaque(false);
		panelContenido.add(radios, BorderLayout.NORTH);
		
		rdbtnPerdidas = new JRadioButton("Perdidas");
		rdbtnPerdidas.setOpaque(false);
		rdbtnPerdidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tablaMascotas.setModel(new ModeloTablaMascotas(usuarioActivo.getMascotasPerdidas()));
				botonEmparejamiento.setVisible(true);

			}
		});
		buttonGroup.add(rdbtnPerdidas);
		radios.add(rdbtnPerdidas);
		
		rdbtnEncontradas = new JRadioButton("Encontradas");
		rdbtnEncontradas.setOpaque(false);
		rdbtnEncontradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				tablaMascotas.setModel(new ModeloTablaMascotas(usuarioActivo.getMascotasEncontradas()));
				botonEmparejamiento.setVisible(true);

			}
		});
		buttonGroup.add(rdbtnEncontradas);
		radios.add(rdbtnEncontradas);
		
		rdbtnRefugiadas = new JRadioButton("Refugiadas");
		rdbtnRefugiadas.setOpaque(false);
		rdbtnRefugiadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				tablaMascotas.setModel(new ModeloTablaMascotas(usuarioActivo.getMascotasRefugiadas()));
				botonEmparejamiento.setVisible(true);

			}
		});
		buttonGroup.add(rdbtnRefugiadas);
		radios.add(rdbtnRefugiadas);
		
		rdbtnAdoptadas = new JRadioButton("Adoptadas");
		rdbtnAdoptadas.setOpaque(false);
		rdbtnAdoptadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tablaMascotas.setModel(new ModeloTablaMascotas(usuarioActivo.getMascotasAdoptadas()));
				botonEmparejamiento.setVisible(false);
			}
		});
		buttonGroup.add(rdbtnAdoptadas);
		radios.add(rdbtnAdoptadas);
		
		tablaMascotas = new JTable();
		panelContenido.add(tablaMascotas, BorderLayout.CENTER);
		
		JPanel panelOperaciones = new JPanel();
		panelOperaciones.setOpaque(false);
		getContentPane().add(panelOperaciones, BorderLayout.SOUTH);
		
		botonDetalles = new JButton("Ver detalles de mascota");
		botonDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaMascotas.getSelectedRow();
				if (fila != -1){
					String IDMascota = (String) tablaMascotas.getValueAt(fila, 0);
					try {
						Principal.coordinador.mostrarDetallesMascota(Principal.getMascotaID(Integer.parseInt(IDMascota)));
					} catch (MascotaNoEncontradaException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Informaci�n", "Debe seleccionar una mascota primero", JOptionPane.INFORMATION_MESSAGE);			
				}
			}
		});
		panelOperaciones.add(botonDetalles);

		botonEmparejamiento = new JButton("Solicitar Emparejamiento bajo Demanda");
		botonEmparejamiento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaMascotas.getSelectedRow();
				if (fila != -1){
					Integer IDMascota = (Integer) tablaMascotas.getValueAt(fila, 0);
					try {
						ArrayList<Mascota> resultadosMascota = Emparejador.emparejarBajoDemanda(Principal.getMascotaID(IDMascota));
						VentanaListaTodasMascotas ventanaResultados = new VentanaListaTodasMascotas();
						ventanaResultados.setDatosIniciales(resultadosMascota, true);
						ventanaResultados.setVisible(true);
					} catch (MascotaNoEncontradaException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
						e1.getMessage(), "Error inesperado del sistema.", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Informaci�n", "Debe seleccionar una mascota primero", JOptionPane.INFORMATION_MESSAGE);			
				}
			}
		});
		botonEmparejamiento.setVisible(false);
		panelOperaciones.add(botonEmparejamiento);
	}
	
	public void setUsuario(Usuario pUsuario){
		
		usuarioActivo = pUsuario;
		if (pUsuario == Acceso.getUsuarioActivo()) botonDetalles.setText("Editar Detalles");
		
		rdbtnPerdidas.setVisible(usuarioActivo.getMascotasPerdidas().size() != 0);
		rdbtnEncontradas.setVisible(usuarioActivo.getMascotasEncontradas().size() != 0);
		rdbtnAdoptadas.setVisible(usuarioActivo.getMascotasAdoptadas().size() != 0);
		rdbtnRefugiadas.setVisible(usuarioActivo.getMascotasRefugiadas().size() != 0); 
	}

}
