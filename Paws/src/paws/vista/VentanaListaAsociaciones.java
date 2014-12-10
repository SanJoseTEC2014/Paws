package paws.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import java.awt.Panel;
import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JTable;

import paws.control.Busqueda;
import paws.control.Principal;
import paws.control.excepciones.OrganizacionNoEncontradaException;
import paws.modelo.ModeloTablaAsociaciones;
import paws.modelo.ModeloTablaMascotas;
import paws.modelo.ModeloTablaUsuarios;
import paws.modelo.Organizacion;
import paws.modelo.Usuario;
import paws.recursos.Diseno;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.ScrollPane;

import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JToolBar;

import java.awt.FlowLayout;
@SuppressWarnings("serial")
public class VentanaListaAsociaciones extends JFrame {
	private ModeloTablaAsociaciones modeloAsociaciones;
	private JTable tablaAsociaciones;
	
	public VentanaListaAsociaciones() {
		setSize(1044, 400);
		getContentPane().setBackground(Diseno.fondoVentanas);
		
		Panel marcoTitulo = new Panel();
		marcoTitulo.setBounds(0, 0, 1028, 33);
		
		JLabel lblAsociaciones = new JLabel("Asociaciones");
		lblAsociaciones.setFont(Diseno.fuenteTitulosVentanas);
		marcoTitulo.add(lblAsociaciones);
		
		Panel marcoBotonSuperior = new Panel();
		marcoBotonSuperior.setBounds(0, 39, 1028, 33);
		
		JButton btnMostrarAsociaciones = new JButton("Mostrar Asociaciones");
		btnMostrarAsociaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modeloAsociaciones = new ModeloTablaAsociaciones(Organizacion.getOrganizaciones());
				tablaAsociaciones.setModel(modeloAsociaciones);			
			}
		});
		marcoBotonSuperior.add(btnMostrarAsociaciones);
		getContentPane().setLayout(null);
		getContentPane().add(marcoTitulo);
		getContentPane().add(marcoBotonSuperior);
		
		JScrollPane scrollPane = new JScrollPane(tablaAsociaciones);
		scrollPane.setOpaque(false);
		//scrollPane.setBounds(25, 78, 979, 516);
		scrollPane.setBounds(25, 78, 979, 200);
		getContentPane().add(scrollPane);
		
		tablaAsociaciones = new JTable();
		tablaAsociaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//scrollPane.setColumnHeaderView(tablaAsociaciones);
		scrollPane.setViewportView(tablaAsociaciones);
		
		Panel marcoBotonesInferiores = new Panel();
		marcoBotonesInferiores.setBounds(25, 312, 979, 33);
		getContentPane().add(marcoBotonesInferiores);
		
		JButton btnVerDetallesDeAsociacion = new JButton("Ver detalles de asociaci\u00F3n");
		btnVerDetallesDeAsociacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerDetallesDeAsociacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaAsociaciones.getSelectedRow();
				if (fila != -1){
					String IDOrganizacion = (String) tablaAsociaciones.getValueAt(fila, 0);
					try {
						Principal.coordinador.mostrarDetallesAsociaciones(Principal.getOrganizacionID(Integer.parseInt(IDOrganizacion)));
					} catch (OrganizacionNoEncontradaException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Información", "Debe seleccionar una organización primero", JOptionPane.INFORMATION_MESSAGE);			
				}
			}
		});
		marcoBotonesInferiores.add(btnVerDetallesDeAsociacion);
		
		JButton btnRealizarUnaDonacion = new JButton("Realizar una donaci\u00F3n");
		btnRealizarUnaDonacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRealizarUnaDonacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaAsociaciones.getSelectedRow();
				if (fila != -1){
					String IDOrganizacion = (String) tablaAsociaciones.getValueAt(fila, 0);
					try {
						Principal.coordinador.mostrarRealizarDonacion();
					} catch (OrganizacionNoEncontradaException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Información", "Debe seleccionar una organización primero", JOptionPane.INFORMATION_MESSAGE);			
				}
			}
		});
		marcoBotonesInferiores.add(btnRealizarUnaDonacion);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}		
		   });
		marcoBotonesInferiores.add(btnCerrar);
		
		
	}
	protected void close() {
		this.dispose();
		
	}
	
	
	
}
