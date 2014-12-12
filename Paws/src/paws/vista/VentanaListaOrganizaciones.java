package paws.vista;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import paws.control.Acceso;
import paws.control.CoordinadorVisual;
import paws.control.Principal;
import paws.control.excepciones.OrganizacionNoEncontradaException;
import paws.modelo.Donacion;
import paws.modelo.ModeloTablaOrganizaciones;
import paws.recursos.Diseno;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class VentanaListaOrganizaciones extends JFrame {
	private final JLabel lblEspacio2 = new JLabel(" ");
	private JTable tablaOrganizaciones;
	
	public VentanaListaOrganizaciones() {
		setSize(1044, 450);
		getContentPane().setBackground(Diseno.fondoVentanas);
		
		Panel marcoTitulo = new Panel();
		marcoTitulo.setBounds(0, 0, 1028, 41);
		
		JLabel labelOrganizaciones = new JLabel("Organizaciones");
		labelOrganizaciones.setFont(Diseno.fuenteTitulosVentanas);
		marcoTitulo.add(labelOrganizaciones);
		
		JLabel lblEspacio1 = new JLabel(" ");
		lblEspacio1.setBounds(0, 34, 3, 317);
		
		Panel marcoBotonesInferiores = new Panel();
		marcoBotonesInferiores.setBounds(0, 378, 1028, 33);
		
		JButton btnVerDetallesDeAsociacion = new JButton("Ver detalles");
		btnVerDetallesDeAsociacion.setFont(Diseno.fuenteBotones);
		btnVerDetallesDeAsociacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaOrganizaciones.getSelectedRow();
				if (fila != -1){
					String IDOrganizacion = (String) tablaOrganizaciones.getValueAt(fila, 0).toString();
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
		btnRealizarUnaDonacion.setFont(Diseno.fuenteBotones);
		btnRealizarUnaDonacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaOrganizaciones.getSelectedRow();
				if (fila != -1){
					String IDOrganizacion = (String) tablaOrganizaciones.getValueAt(fila, 0).toString();
					try {
						Double pMonto = Double.parseDouble(
							JOptionPane.showInputDialog(getContentPane(),
								"Digite el monto que desea donar:", "Donación en curso", JOptionPane.QUESTION_MESSAGE));
						if (pMonto <= 0.0) {throw new NumberFormatException("El valor ingresado es menor o igual a cero.");}
						Donacion pDonacion = new Donacion(Acceso.getUsuarioActivo().getNickname(), pMonto);
						Principal.getOrganizacionID(Integer.parseInt(IDOrganizacion)).addDonacion(pDonacion);
						Principal.getOrganizacionID(Integer.parseInt(IDOrganizacion)).setMontoTotalDonaciones(pMonto);
						
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
					JOptionPane.showMessageDialog(getContentPane(), "Información", "Debe seleccionar una organización primero.", JOptionPane.INFORMATION_MESSAGE);			
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
		getContentPane().setLayout(null);
		getContentPane().add(marcoTitulo);
		getContentPane().add(lblEspacio1);
		lblEspacio2.setBounds(1025, 34, 3, 317);
		getContentPane().add(lblEspacio2);
		getContentPane().add(marcoBotonesInferiores);
		
		JLabel lblTotalOrganzacionesRegistradas = new JLabel("Total Organizaciones registradas: " + Principal.organizaciones.size());
		lblTotalOrganzacionesRegistradas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTotalOrganzacionesRegistradas.setBounds(30, 47, 427, 33);
		getContentPane().add(lblTotalOrganzacionesRegistradas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 80, 965, 271);
		getContentPane().add(scrollPane);
		
		tablaOrganizaciones = new JTable();
		tablaOrganizaciones.setModel(new ModeloTablaOrganizaciones(Principal.organizaciones));
		tablaOrganizaciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaOrganizaciones.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaOrganizaciones.setVisible(true);
		tablaOrganizaciones.setShowVerticalLines(true);
		scrollPane.setViewportView(tablaOrganizaciones);
	}
	
	
}
