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

import paws.control.Acceso;
import paws.modelo.Calificacion;
import paws.modelo.Organizacion;
import paws.modelo.Donacion;
import paws.modelo.Usuario;
import paws.recursos.Diseno;

import java.awt.Label;
import java.awt.TextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaDonaciones extends JFrame {
	private JTextField textMonto;
	private Organizacion organizacionADonar;
	public VentanaDonaciones() {
		setSize(400, 300);
		getContentPane().setBackground(Diseno.fondoVentanas);
		JLabel lblRealizarUnaDonacin = new JLabel("Realizar una donaci\u00F3n");
		lblRealizarUnaDonacin.setFont(Diseno.fuenteTitulosVentanas);
		lblRealizarUnaDonacin.setHorizontalAlignment(SwingConstants.CENTER);
		
		Panel panel = new Panel();
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				organizacionADonar.addDonacion(new Donacion(Acceso.getUsuarioActivo().getNickname(),Double.parseDouble(textMonto.getText()),Organizacion.getId()));
				close();
			}
		});
		panel.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}		
		   });
		panel.add(btnCancelar);
		btnCancelar.setOpaque(false);
		
		
		
		JLabel lblIngreseElMonto = new JLabel("Ingrese el monto que desea donar:");
		
		textMonto = new JTextField();
		textMonto.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblRealizarUnaDonacin, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblIngreseElMonto)
					.addGap(18)
					.addComponent(textMonto, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblRealizarUnaDonacin)
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIngreseElMonto)
						.addComponent(textMonto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(61)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}
	protected void close() {
		this.dispose();
		
	}
	
	public void setOrganizacionADonar(Organizacion pOrganizacionADonar) {
		organizacionADonar= pOrganizacionADonar;
	}
}