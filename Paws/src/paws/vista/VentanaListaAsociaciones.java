package paws.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import java.awt.Panel;
import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JTable;

import paws.recursos.Diseno;

import java.awt.Font;
import java.awt.Color;

public class VentanaListaAsociaciones extends JFrame {
	
	private JTable table;
	public VentanaListaAsociaciones() {
		setSize(500, 300);
		getContentPane().setBackground(Diseno.fondoVentanas);
		JLabel lblAsociaciones = new JLabel("Asociaciones\r\n\r\n\r\n\r\n\r\n");
		lblAsociaciones.setBackground(new Color(240, 240, 240));
		lblAsociaciones.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAsociaciones.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblAsociaciones, BorderLayout.NORTH);
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnRealizarUnaDonacin = new JButton("Realizar una donaci\u00F3n");
		panel.add(btnRealizarUnaDonacin);
		
		JButton btnVerDetallesDe = new JButton("Ver detalles de Asociaci\u00F3n");
		panel.add(btnVerDetallesDe);
		
		table = new JTable();
		getContentPane().add(table, BorderLayout.CENTER);
		
		JLabel label = new JLabel("");
		getContentPane().add(label, BorderLayout.WEST);
	}

}