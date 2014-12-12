package paws.vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import paws.control.Acceso;
import paws.control.Principal;
import paws.control.excepciones.OrganizacionNoEncontradaException;
import paws.modelo.Donacion;
import paws.modelo.ModeloTablaOrganizaciones;
import paws.recursos.Diseno;

@SuppressWarnings("serial")
public class VentanaListaOrganizaciones extends JFrame {
	private JTable tablaOrganizaciones;
	private final JLabel lblEspacio2 = new JLabel(" ");
	
	public VentanaListaOrganizaciones() {
		setSize(1044, 400);
		getContentPane().setBackground(Diseno.fondoVentanas);
		getContentPane().setLayout(new BorderLayout(10, 10));
		
		Panel marcoTitulo = new Panel();
		
		JLabel labelOrganizaciones = new JLabel("Organizaciones");
		labelOrganizaciones.setFont(Diseno.fuenteTitulosVentanas);
		marcoTitulo.add(labelOrganizaciones);
		getContentPane().add(marcoTitulo, BorderLayout.NORTH);
		
		JLabel lblEspacio1 = new JLabel(" ");
		getContentPane().add(lblEspacio1, BorderLayout.WEST);
		getContentPane().add(lblEspacio2, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane(tablaOrganizaciones);
		scrollPane.setOpaque(false);
		getContentPane().add(scrollPane);
		
		tablaOrganizaciones = new JTable();
		tablaOrganizaciones.setModel(new ModeloTablaOrganizaciones(Principal.organizaciones));
		tablaOrganizaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tablaOrganizaciones);
		
		Panel marcoBotonesInferiores = new Panel();
		getContentPane().add(marcoBotonesInferiores, BorderLayout.SOUTH);
		
		JButton btnVerDetallesDeAsociacion = new JButton("Ver detalles de asociaci\u00F3n");
		btnVerDetallesDeAsociacion.setFont(Diseno.fuenteBotones);
		btnVerDetallesDeAsociacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaOrganizaciones.getSelectedRow();
				if (fila != -1){
					try {
						Principal.coordinador.mostrarDetallesAsociaciones(
							Principal.getOrganizacionID(
								(Integer) tablaOrganizaciones.getValueAt(fila, 0)));
					} catch (OrganizacionNoEncontradaException e) {
						JOptionPane.showMessageDialog(getContentPane(),
							e.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
						"Información", "Debe seleccionar una organización primero",
						JOptionPane.INFORMATION_MESSAGE);			
				}
			}
		});
		marcoBotonesInferiores.add(btnVerDetallesDeAsociacion);
		
		JButton btnRealizarUnaDonacion = new JButton("Realizar una donaci\u00F3n");
		btnRealizarUnaDonacion.setFont(Diseno.fuenteBotones);
		btnRealizarUnaDonacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaOrganizaciones.getSelectedRow();
				if (fila != -1){
					try {
						Double pMonto = Double.parseDouble(
							JOptionPane.showInputDialog(getContentPane(),
								"Digite el monto que desea donar:",
								"Donación en curso", JOptionPane.QUESTION_MESSAGE));
						if (pMonto <= 0.0) {
							throw new NumberFormatException("El valor ingresado es menor o igual a cero.");
						}
						Donacion pDonacion = new Donacion(
							Acceso.getUsuarioActivo().getNickname(), pMonto);
						Principal.getOrganizacionID(
							(Integer) tablaOrganizaciones.getValueAt(fila, 0)).addDonacion(pDonacion);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(getContentPane(),
						"El monto ingresado no es válido.\n" + e.getMessage(),
						"Error numérico.", JOptionPane.ERROR_MESSAGE);
					} catch (OrganizacionNoEncontradaException e) {
						JOptionPane.showMessageDialog(getContentPane(),
						"No se encuentra la organización. Contacte con el equipo PAWS.",
						"Error inesperado.", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(),
						"Información", "Debe seleccionar una organización primero.", JOptionPane.INFORMATION_MESSAGE);			
				}
			}
		});
		marcoBotonesInferiores.add(btnRealizarUnaDonacion);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setFont(Diseno.fuenteBotones);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}		
		});
		marcoBotonesInferiores.add(btnCerrar);
	}
}
