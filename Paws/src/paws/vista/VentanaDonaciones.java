package paws.vista;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import paws.control.Principal;
import paws.modelo.ModeloTablaCalificaciones;
import paws.modelo.Organizacion;
import paws.modelo.ModeloTablaOrganizaciones;
import paws.modelo.ModeloTablaDonaciones;
import paws.modelo.Usuario;
import paws.recursos.Diseno;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class VentanaDonaciones extends JFrame {
	private JTable tablaDonaciones;
	private Organizacion organizacionActual;
	public VentanaDonaciones() {
		setSize(813,344);
		getContentPane().setBackground(Diseno.fondoVentanas);
		
		Panel marcoTitulo = new Panel();
		marcoTitulo.setBounds(0, 0, 787, 37);
		
		JLabel lblDonaciones = new JLabel("Donaciones");
		lblDonaciones.setFont(Diseno.fuenteTitulosVentanas);
		marcoTitulo.add(lblDonaciones);
		
		Panel marcoBotones = new Panel();
		marcoBotones.setBounds(0, 269, 789, 37);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}		
		});
		marcoBotones.add(btnCerrar);
		getContentPane().setLayout(null);
		getContentPane().add(marcoTitulo);
		getContentPane().add(marcoBotones);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 43, 719, 220);
		getContentPane().add(scrollPane);
		
		tablaDonaciones = new JTable();
		tablaDonaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaDonaciones.setModel(new ModeloTablaOrganizaciones(Principal.organizaciones));
		tablaDonaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaDonaciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaDonaciones.setShowVerticalLines(true);
		scrollPane.setViewportView(tablaDonaciones);
	}
		public void setOrganizacion(Organizacion organizacionActual) {
				tablaDonaciones.setModel(new ModeloTablaDonaciones(organizacionActual.getDonaciones()));
		}
		
}
