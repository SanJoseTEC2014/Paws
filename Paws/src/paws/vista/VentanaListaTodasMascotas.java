package paws.vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;

import paws.control.Principal;
import paws.control.excepciones.MascotaNoEncontradaException;
import paws.modelo.Mascota;
import paws.modelo.ModeloTablaMascotas;
import paws.recursos.Diseno;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class VentanaListaTodasMascotas extends JFrame {
	private JTable tablaMascotas;
	private JLabel labelTitulo;
	private JButton botonVerDetallesMascota;
	private JButton botonLocalizacion;
	private JLabel espacioIzq;
	public VentanaListaTodasMascotas() {
		setSize(575,480);
		getContentPane().setBackground(Diseno.fondoVentanas);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setHgap(20);
		borderLayout.setVgap(20);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		labelTitulo = new JLabel();
		labelTitulo.setFont(Diseno.fuenteTitulosVentanas);
		labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labelTitulo.setOpaque(false);
		panel.add(labelTitulo, BorderLayout.NORTH);
		
		tablaMascotas = new JTable();
		getContentPane().add(tablaMascotas, BorderLayout.CENTER);
		
		espacioIzq = new JLabel(" ");
		getContentPane().add(espacioIzq, BorderLayout.WEST);
		
		JLabel espacioDer = new JLabel(" ");
		getContentPane().add(espacioDer, BorderLayout.EAST);
		
		JPanel marcoBotones = new JPanel();
		marcoBotones.setOpaque(false);
		getContentPane().add(marcoBotones, BorderLayout.SOUTH);
		
		botonVerDetallesMascota = new JButton("Ver detalles de la Mascota Seleccionada");
		botonVerDetallesMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarDetallesMascota();
			}
		});
		marcoBotones.add(botonVerDetallesMascota);
		
		botonLocalizacion = new JButton("\u00A1Soy el due\u00F1o!");
		marcoBotones.add(botonLocalizacion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		marcoBotones.add(btnCancelar);
	}
	
	public void setDatosIniciales(ArrayList<Mascota> pLista, boolean emparejamiento){
		if (emparejamiento) {
			labelTitulo.setText("Resultados Emparejamiento");
		} else {
			labelTitulo.setText("Mascotas");
		}
		tablaMascotas.setModel(new ModeloTablaMascotas(pLista));
		tablaMascotas.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaMascotas.setAutoCreateRowSorter(true);
	}
	private void mostrarDetallesMascota(){
		int filaSeleccionada = tablaMascotas.getSelectedRow();
		if (filaSeleccionada != -1){
			Integer idMascota = (Integer) tablaMascotas.getValueAt(filaSeleccionada, 0);
			try {
				Principal.coordinador.mostrarDetallesMascota(Principal.getMascotaID(idMascota));
			} catch (MascotaNoEncontradaException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
					e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(getContentPane(),
				"Debe seleccionar una mascota primero",
				"Informaci�n", JOptionPane.INFORMATION_MESSAGE);			
		}
	}
	
}