package paws.vista;

import java.awt.*;

import javax.swing.*;

import paws.control.Acceso;
import paws.control.Imagenes;
import paws.control.Principal;
import paws.control.excepciones.ImagenNoEncontradaException;
import paws.modelo.Mascota;
import paws.recursos.Diseno;

import java.awt.event.*;
import java.awt.image.BufferedImage;

import jflow.Configuration;
import jflow.JFlowPanel;
import jflow.Shape;
import jflow.shape.Picture;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class VentanaMenuPrincipal extends JFrame {
	private JFlowPanel coverflowPanel;
	private JLabel labelBienvenido;
	private JMenuItem mntmSolicitarSerRefugiante;
	private JMenu mnAyuda;
	private JMenuItem mntmParametrosSistema;
	private JMenu mnCuenta;
	private JMenuItem mntmCondicionesDeRefugio;
	private JMenuItem mntmCerrarSesion;
	private JMenuItem mntmVerMisDetalles;
	private JMenuItem mntmVerMisCalificaciones;
	private JMenuItem mntmRegistraTuMascota;
	private JMenuItem mntmMisMascotas;
	private JMenuItem mntmVerDesaparecidas;
	private JMenuItem mntmVerEncontradas;
	private JMenuItem mntmVerRefugiadas;
	private JMenuItem mntmBuscar;

	public VentanaMenuPrincipal() {
		setTitle("Paws");
		WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(getContentPane(),
                	"Desea cerrar su sesión activa?",
                	"Confirmación de salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                   Principal.coordinador.ocultarMenuPrincipal();
                   Principal.coordinador.mostrarInicioSesion();
                }
            }
        };
        addWindowListener(exitListener);
        int anchoVentana = 700;
        int altoVentana = 600;
		setSize(anchoVentana, altoVentana);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(Diseno.fondoVentanas);
		
		JPanel marcoContenido = new JPanel();
		marcoContenido.setOpaque(false);
		getContentPane().add(marcoContenido, BorderLayout.NORTH);
		marcoContenido.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		
		labelBienvenido = new JLabel();
		labelBienvenido.setFont(Diseno.fuenteTitulosVentanas);
		marcoContenido.add(labelBienvenido);
		
		JLabel labelLogotipo = new JLabel("");
		marcoContenido.add(labelLogotipo);
		try {
			BufferedImage logo = Imagenes.getLogo2();
			Dimension nuevoTamanio = Imagenes.getNuevaDimension(
					350, 300,
					logo.getSampleModel().getWidth(),
					logo.getSampleModel().getHeight());
			labelLogotipo.setIcon(new ImageIcon(Imagenes.redimensionar(logo, nuevoTamanio.width, nuevoTamanio.height)));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(getContentPane(), e1.getMessage(),
				"Logotipo no encontrado.", JOptionPane.ERROR_MESSAGE);
		}
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMascotas = new JMenu("Mascotas");
		menuBar.add(mnMascotas);
		
		mntmRegistraTuMascota = new JMenuItem("Registra tu mascota");
		mntmRegistraTuMascota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.coordinador.mostrarRegistroMascotas();
			}
		});
		mnMascotas.add(mntmRegistraTuMascota);
		
		mntmMisMascotas = new JMenuItem("Mis mascotas");
		mntmMisMascotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.coordinador.mostrarMascotasAsociadas(Acceso.getUsuarioActivo());
			}
		});
		mnMascotas.add(mntmMisMascotas);
		
		mntmVerDesaparecidas = new JMenuItem("Desaparecidas registradas en Paws");
		mntmVerDesaparecidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListaTodasMascotas window = new VentanaListaTodasMascotas();
				ArrayList<Mascota> desaparecidas = new ArrayList<Mascota>();
				for (Mascota x : Principal.mascotas){
					if (x.getMarcadoresEstado()[0]){
						desaparecidas.add(x.clone());
					}
				}
				window.setDatosIniciales(desaparecidas, false);
				window.setVisible(true);
			}
		});
		mnMascotas.add(mntmVerDesaparecidas);
		
		mntmVerEncontradas = new JMenuItem("Encontradas registradas en Paws");
		mntmVerEncontradas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListaTodasMascotas window = new VentanaListaTodasMascotas();
				ArrayList<Mascota> encontradas = new ArrayList<Mascota>();
				for (Mascota x : Principal.mascotas){
					if (x.getMarcadoresEstado()[1]){
						encontradas.add(x.clone());
					}
				}
				window.setDatosIniciales(encontradas, false);
				window.setVisible(true);
			}
		});
		mnMascotas.add(mntmVerEncontradas);
		
		mntmVerRefugiadas = new JMenuItem("Refugiadas registradas en Paws");
		mntmVerRefugiadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaListaTodasMascotas window = new VentanaListaTodasMascotas();
				ArrayList<Mascota> refugiadas = new ArrayList<Mascota>();
				for (Mascota x : Principal.mascotas){
					if (x.getMarcadoresEstado()[2]){
						refugiadas.add(x.clone());
					}
				}
				window.setDatosIniciales(refugiadas, false);
				window.setVisible(true);
			}
		});
		mnMascotas.add(mntmVerRefugiadas);
		
		JMenu mnBusqueda = new JMenu("B\u00FAsqueda");
		menuBar.add(mnBusqueda);
		
		mntmBuscar = new JMenuItem("Herramienta de B\u00FAsqueda");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.coordinador.mostrarBusqueda();
			}
		});
		mnBusqueda.add(mntmBuscar);
		
		JMenu mnAsociaciones = new JMenu("Asociaciones");
		menuBar.add(mnAsociaciones);
		
		JMenuItem mntmListaDeAsociaciones = new JMenuItem("Lista de Asociaciones");
		mnAsociaciones.add(mntmListaDeAsociaciones);
		
		JMenuItem mntmRealizarUnaDonacin = new JMenuItem("Realizar una donaci\u00F3n");
		mnAsociaciones.add(mntmRealizarUnaDonacin);
		
		mnCuenta = new JMenu("Cuenta");
		menuBar.add(mnCuenta);
		
		mntmVerMisDetalles = new JMenuItem("Detalles de mi cuenta");
		mntmVerMisDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.coordinador.mostrarDetallesUsuario(Acceso.getUsuarioActivo());
			}
		});
		mnCuenta.add(mntmVerMisDetalles);
		
		mntmParametrosSistema = new JMenuItem("Parametros Sistema");
			mntmParametrosSistema.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Principal.coordinador.mostrarParametrosSistema();
				}
			});
		
		mntmVerMisCalificaciones = new JMenuItem("Ver mis calificaciones");
		mntmVerMisCalificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.coordinador.mostrarCalificaciones(Acceso.getUsuarioActivo());
			}
		});
		mnCuenta.add(mntmVerMisCalificaciones);
		
		mntmCondicionesDeRefugio = new JMenuItem("Editar condiciones de refugio");
		mnCuenta.add(mntmCondicionesDeRefugio);
		
		mntmSolicitarSerRefugiante = new JMenuItem("Enviar Solicitud Refugiante");
		
		mntmCerrarSesion = new JMenuItem("Cerrar sesi\u00F3n");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.coordinador.ocultarMenuPrincipal();
				Principal.coordinador.mostrarInicioSesion();
			}
		});
		mnCuenta.add(mntmCerrarSesion);
		mntmCondicionesDeRefugio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Principal.coordinador.mostrarCondicionesRefugio(Acceso.getUsuarioActivo());
			}
		});
		
		mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		
		JMenuItem mntmManualDeUso = new JMenuItem("Manual de Uso");
		mntmManualDeUso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().browse(new URL("http://www.mylostpaws.weebly.com").toURI());
				} catch (IOException | URISyntaxException e) {
					JOptionPane.showMessageDialog(getContentPane(),
						"Puede visitarnos en la siguiente dirección:\n" +
						"http://www.mylostpaws.weebly.com");
				}
			}
		});
		mnAyuda.add(mntmManualDeUso);
		
		JMenuItem mntmContctenos = new JMenuItem("Cont\u00E1ctenos");
		mntmContctenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(getContentPane(),
					"Puede enviarnos un correo con sus dudas a:\npawsconsultas@gmail.com");
			}
		});
		mnAyuda.add(mntmContctenos);
		
		// Creación del objeto JFlowPanel		
		Configuration configTest = new Configuration();
		configTest.shapeWidth = 0.8;
		configTest.scrollFactor = 10.0;
		configTest.framesPerSecond = 60;
		
		configTest.shapes = new LinkedList<Shape>();
		// Después de muchas pruebas unitarias,
		// el máximo de imágenes que la matriz sostiene son 10.
		for (int img = 1; img < 10; img++){ 
			try {
				configTest.shapes.add(new Picture(Imagenes.getPerfilMascota(img)));
			} catch (ImagenNoEncontradaException e) {
				break;
			}
		}
		
		coverflowPanel = new JFlowPanel(configTest);
		coverflowPanel.setOpaque(false);
		getContentPane().add(coverflowPanel);
	}

	protected void close() {
		this.dispose();
	}

	public void setUsuario(){
		labelBienvenido.setText(Acceso.getUsuarioActivo().getNombre() + ", \n bienvenid@ a ");
		if(Acceso.isAdministradorActivo()) {
			mnCuenta.add(mntmParametrosSistema);
		}
		if(!Acceso.getUsuarioActivo().isRefugiante()) {
			mnCuenta.add(mntmSolicitarSerRefugiante);
		}

	}
}