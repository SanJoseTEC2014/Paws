package paws.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import java.awt.Panel;
import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JTable;

import paws.control.Principal;
import paws.control.excepciones.OrganizacionNoEncontradaException;
import paws.recursos.Diseno;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaListaAsociaciones extends JFrame {
	private JTable table;
	public VentanaListaAsociaciones() {
		setSize(591, 479);
		getContentPane().setBackground(Diseno.fondoVentanas);
		JLabel lblAsociaciones = new JLabel("Asociaciones\r\n\r\n\r\n\r\n\r\n");
		lblAsociaciones.setBackground(new Color(240, 240, 240));
		lblAsociaciones.setFont(Diseno.fuenteTitulosVentanas);
		lblAsociaciones.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblAsociaciones, BorderLayout.NORTH);
		
		Panel panel = new Panel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnVerDetallesDeAsociacion = new JButton("Ver detalles de Asociaci\u00F3n");
		btnVerDetallesDeAsociacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVerDetallesDeAsociacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				if (fila != -1){
					String IDOrganizacion = (String) table.getValueAt(fila, 0);
					try {
						Principal.coordinador.mostrarDetallesAsociaciones(Principal.getOrganizacionID(Integer.parseInt(IDOrganizacion)));
					} catch (OrganizacionNoEncontradaException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Información", "Debe seleccionar una mascota primero", JOptionPane.INFORMATION_MESSAGE);			
				}
			}
		});
		panel.add(btnVerDetallesDeAsociacion);
		
		JButton btndonacion = new JButton("Realizar una donaci\u00F3n");
		btndonacion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btndonacion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Principal.coordinador.mostrarRealizarDonacion();
			}		
		});
		   panel.add(btndonacion);
		
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCerrar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			close();
		}		
	   });
	   panel.add(btnCerrar);
	   btnCerrar.setOpaque(false);
		
		JLabel espacioIzq = new JLabel("            ");
		getContentPane().add(espacioIzq, BorderLayout.WEST);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		getContentPane().add(table, BorderLayout.CENTER);
		
		JLabel espacioDer = new JLabel("          ");
		getContentPane().add(espacioDer, BorderLayout.EAST);
	}
	
	protected void close() {
		this.dispose();
		
	}

}