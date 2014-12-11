package paws.vista;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import paws.control.*;
import paws.control.excepciones.*;
import paws.modelo.*;
import paws.recursos.Diseno;

@SuppressWarnings("serial")
public class VentanaMascotasRegistradas extends JFrame {
	private JTable tablaMascotas;
	private JLabel labelTitulo;
	private JButton botonVerDetallesMascota;
	private JButton botonLocalizacion;
	private JLabel espacioIzq;
	public VentanaMascotasRegistradas() {
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
	
	public void setDatosIniciales(ArrayList<Mascota> pLista, boolean isEmparejamiento){
		if (isEmparejamiento) {
			labelTitulo.setText("Resultados Emparejamiento");
		} else {
			labelTitulo.setText("Mascotas Registradas en Paws");
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
				"Información", JOptionPane.INFORMATION_MESSAGE);			
		}
	}
	
}