package paws.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import paws.recursos.Diseno;

import java.awt.Label;
import java.awt.TextField;
import java.awt.Font;

public class VentanaDonaciones extends JFrame {
	private JTextField textField;
	public VentanaDonaciones() {
		setSize(400, 216);
		getContentPane().setBackground(Diseno.fondoVentanas);
		JLabel lblRealizarUnaDonacin = new JLabel("Realizar una donaci\u00F3n");
		lblRealizarUnaDonacin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRealizarUnaDonacin.setHorizontalAlignment(SwingConstants.CENTER);
		
		Panel panel = new Panel();
		
		JButton btnOk = new JButton("OK");
		panel.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		panel.add(btnCancelar);
		
		JLabel lblIngreseElMonto = new JLabel("Ingrese el monto que desea donar:");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblRealizarUnaDonacin, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIngreseElMonto)
					.addGap(18)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblRealizarUnaDonacin)
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIngreseElMonto)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(61)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}
}