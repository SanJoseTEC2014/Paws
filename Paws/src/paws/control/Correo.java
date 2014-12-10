package paws.control;

/**	 Controlador Correo: 
 * 	 Esta clase se encarga de enviar un correo a un usuario
 *   del sistema con un mensaje que contiene el código de confirmación
 *   para que el usuario pueda registrarse y se encargar de enviar
 *   otro correo que contiene una lista de coincidencias del resultado 
 *   del emparejamiento con respecto a la mascota registrada
 *   por el usuario en el sistema.
 * 
 *	 Fecha de creación: 29/10/2014
 * 
 *	@author Isaac Antonio Campos Mesén 2014004626
 *	@author Liza Elena Chaves Carranza 2013016573
 *	@author Melissa María Molina Corrales 2013006074
 *	@author Luis Andrés Peña Castillo 2014057250 
 *  
 */
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import paws.modelo.Mascota;
import paws.modelo.Usuario;

public class Correo {
	// TODO definir si estos datos serán obtenidos de algún archivo o programados en duro. 

	// Servidor SMTP
	static final String hostServidorCorreo = "smtp.gmail.com";
	// El puerto 587 nos permite utilizar STARTTLS para cifrar la conexión.
	static final int puertoComunicacionServidor = 587;
	
	private static String emisor = "pawsconsultas@gmail.com";
	private static String nombreUsuarioSMTP = "pawsconsultas@gmail.com";
	private static String passwordSMTP = "yhawfrxjuvadmjdx";
	private static String mensajeParametrizable = "";

	static void enviarCorreo(String pReceptor, String pAsunto, String pCuerpo) throws MessagingException {
		// props contendrá información de la conexión.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", puertoComunicacionServidor);

		// La sesión SMTP inicia en una conexión sin cifrar, y luego el cliente
		// emitirá un comando STARTTLS para actualizar a una conexión cifrada.
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.starttls.required", true);

		Session session = Session.getDefaultInstance(props);
		MimeMessage msg;
		
		msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(emisor));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(pReceptor));
		msg.setSubject(pAsunto);
		msg.setContent(pCuerpo, "text/plain");

		Transport transport = session.getTransport();
		transport.connect(hostServidorCorreo, nombreUsuarioSMTP, passwordSMTP);

		transport.sendMessage(msg, msg.getAllRecipients());		
		transport.close();
	}
	
	public static void enviarCoincidencias(ArrayList<Mascota> pCoincidencias, Mascota pMascota, Usuario pUsuario) throws MessagingException {
		String asunto = "Su mascota puede haber sido localizada.";
		
		String cuerpoMsg =	"Estimado usuario " + pUsuario.getNombre() + ", \n" +
							"Su mascota " + pMascota.getNombre() + " podría haber sido localizada. \n" + mensajeParametrizable;
		
		// A continuación se calculan los valores necesarios para darle formato de tabla
		// a los datos de las mascotas en la lista de coincidencias, para formar
		// el cuerpo del mensaje:
		
		int stringMaxNombre = 8;
		int stringMaxEspecie = 9;
		int stringMaxRaza = 8;
		int stringMaxColor = 7;
		int stringMaxTamanio = 9;
		int stringMaxSexo = 8;
		
		// Obtiene el tamaño maximo del modelo para el formato del mensaje
		for (int i = 0; i < pCoincidencias.size(); i++){
			if (pCoincidencias.get(i).getNombre().length() > stringMaxNombre){
				stringMaxNombre = pCoincidencias.get(i).getNombre().length()+2;
			}
			if (pCoincidencias.get(i).getEspecie().length() > stringMaxEspecie){
				stringMaxEspecie = pCoincidencias.get(i).getEspecie().length()+2;
			}
			if (pCoincidencias.get(i).getRaza().length() > stringMaxRaza){
				stringMaxRaza = pCoincidencias.get(i).getRaza().length()+2;
			}
			if (pCoincidencias.get(i).getColor().length() > stringMaxColor){
				stringMaxColor = pCoincidencias.get(i).getColor().length()+2;
			}
			if (pCoincidencias.get(i).getTamanio().length() > stringMaxTamanio){
				stringMaxTamanio = pCoincidencias.get(i).getTamanio().length()+2;
			}
			if (pCoincidencias.get(i).getSexo().length() > stringMaxSexo){
				stringMaxSexo = pCoincidencias.get(i).getSexo().length()+2;
			}
		}
		String FORMATO = "   %-"+stringMaxNombre+"s%-"+stringMaxEspecie+"s%-"+stringMaxRaza+"s%-"+stringMaxColor+"s%-"+stringMaxTamanio+"s%-"+stringMaxSexo+"s\n";
	
		// Encabezados de las columnas:
		cuerpoMsg += String.format(FORMATO, " NOMBRE", " ESPECIE", " RAZA", " COLOR", " TAMAÑO", " SEXO") +"\n";
		
		// Construcción de las líneas de la tabla con los datos de las mascotas:		
		for (int i = 0; i < pCoincidencias.size(); i++){
			cuerpoMsg += String.format(FORMATO, 
					pCoincidencias.get(i).getNombre().toUpperCase(),
					pCoincidencias.get(i).getEspecie().toUpperCase(),
					pCoincidencias.get(i).getRaza().toUpperCase(),
					pCoincidencias.get(i).getColor().toUpperCase(),
					pCoincidencias.get(i).getTamanio().toUpperCase(),
					pCoincidencias.get(i).getSexo().toUpperCase());
																						// Nótese el salto de línea
		}
		
		cuerpoMsg += "\nPor favor si desea más información sobre alguna de éstas mascotas, \n" +
					 "ingrese a nuestro sistema, y solicite un emparejamiento bajo demanda \n" +
					 "para ponerse en contacto con los usuarios encargados de dichas mascotas" +
					 "o puede contactarnos al correo pawsconsultas@gmail.com.";
		
		cuerpoMsg += "\n\n" + "Atentamente: " + "\n" + "Paws San José";
		

		enviarCorreo(pUsuario.getCorreo(), asunto, cuerpoMsg);
	}
	
	public static void setMensaje(String pMensaje) {
		mensajeParametrizable = pMensaje;
	}
	
	public static String getMensaje() {
		return mensajeParametrizable;
	}
	
	public static void enviarCodigoCorreo(String nick, String nombre, String correo) throws MessagingException {
		
		String asunto = "Codigo de activacion de registro. Paws";
				
		String cuerpoMsg = "Estimad@ " + nombre + ".\n\n" 
		+ "Para completar el registro de su cuenta en Paws debe ingresar el siguiente código.\n\n"
		+ "CODIGO ACTIVACIÓN: " + nick.hashCode() + "\n"
		+ "Tome en cuenta que puede estar incluído un signo negativo, el cual NO DEBE OMITIRSE.\n\n"
		+ "Si usted no solicitó activar una cuenta en Paws por favor ignorar este mensaje.";
		enviarCorreo(correo, asunto, cuerpoMsg);	
	}

}
