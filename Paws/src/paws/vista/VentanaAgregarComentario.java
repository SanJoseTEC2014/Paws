package paws.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.JEditorPane;

import paws.control.Acceso;
import paws.control.Imagenes;
import paws.modelo.Calificacion;
import paws.modelo.Usuario;
import paws.recursos.Diseno;

public class VentanaAgregarComentario extends JFrame {
	private JButton btnCancelar;
	private JButton btnComentar;
	private JPanel cajaComentarios;
	private JComboBox<Integer> comboCalificacion;
	private JLabel lblCalificacion;
	private JLabel lblTitulo;
	private JPanel panelBotones;
	private JPanel panelContenido;
	private JPanel panelTitulo;
	private JEditorPane textComentario;
	private Usuario usuarioACalificar;
	
	public VentanaAgregarComentario() {
		setSize(480,299);
		setIconImage(Imagenes.getIconoSistema().getImage());
		getContentPane().setBackground(Diseno.fondoVentanas);
		setTitle("Agregar Comentario");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelTitulo = new JPanel();
		panelTitulo.setOpaque(false);
		getContentPane().add(panelTitulo, BorderLayout.NORTH);
		panelTitulo.setLayout(new BorderLayout(0, 0));
		
		lblTitulo = new JLabel("Dejar Comentario");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(Diseno.fuenteTitulosVentanas);
		panelTitulo.add(lblTitulo);
		
		panelContenido = new JPanel();
		panelContenido.setOpaque(false);
		getContentPane().add(panelContenido, BorderLayout.CENTER);
		panelContenido.setLayout(new BorderLayout(20, 20));
		
		cajaComentarios = new JPanel();
		panelContenido.add(cajaComentarios);
		cajaComentarios.setOpaque(false);
		cajaComentarios.setBorder(new TitledBorder(null, "Escribir Comentario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cajaComentarios.setLayout(new BorderLayout(20, 20));
		
		textComentario = new JEditorPane();
		cajaComentarios.add(textComentario);
		
		JPanel panelCalificacion = new JPanel();
		panelContenido.add(panelCalificacion, BorderLayout.SOUTH);
		panelCalificacion.setOpaque(false);
		panelCalificacion.setLayout(new BorderLayout(20, 20));
		
		lblCalificacion = new JLabel("Calificaci\u00F3n");
		lblCalificacion.setHorizontalAlignment(SwingConstants.RIGHT);
		panelCalificacion.add(lblCalificacion, BorderLayout.CENTER);
		
		comboCalificacion = new JComboBox<Integer>();
		comboCalificacion.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3, 4, 5}));
		panelCalificacion.add(comboCalificacion, BorderLayout.EAST);
		
		panelBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setVgap(20);
		flowLayout.setHgap(20);
		panelBotones.setOpaque(false);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				close();
			}
		});
		panelBotones.add(btnCancelar);
		btnCancelar.setOpaque(false);
		
		btnComentar = new JButton("Comentar");
		btnComentar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usuarioACalificar.addCalificacion(new Calificacion(Acceso.getUsuarioActivo().getNickname(), (Integer) comboCalificacion.getSelectedItem(), textComentario.getText()));
				close();
			}
		});
		panelBotones.add(btnComentar);
		btnComentar.setOpaque(false);
	}
	protected void close() {
		this.dispose();
		
	}
	public void setUsuarioACalificar(Usuario pUsuarioACalificar) {
		usuarioACalificar = pUsuarioACalificar;
	}
}
