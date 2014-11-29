package paws.recursos;

public class RutasArchivo {
	public static String directorioProyecto;
	public static String slash;
	public static String recursosRoot; 
	public static String fotosMascotas;
	public static String fotosUsuarios;
	public static String fotosSistema;
	public static String fuentes;
	public static String casosprueba;
	public static String binariosMascotas;
	public static String binariosUsuarios;
	
	public static void inicializar() {
		directorioProyecto = System.getProperty("user.dir");
		slash = "";
		if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) { // Si se detecta que el sistema es Windows...
			slash = "\\";
		} else { // En caso que sea otro como Linux, o Mac OS:
			slash = "/";
		}
		recursosRoot		= directorioProyecto + slash + "src" + slash + "paws" + slash + "recursos" + slash;
		binariosMascotas	= recursosRoot + "mascotas";
		binariosUsuarios 	= recursosRoot + "usuarios";
		fotosMascotas 		= recursosRoot + "imagenes" + slash + "mascotas" + slash;
		fotosUsuarios 		= recursosRoot + "imagenes" + slash + "usuarios" + slash;
		fotosSistema  		= recursosRoot + "imagenes" + slash + "sistema"  + slash;
		fuentes 	  		= recursosRoot + "fuentes" + slash;
		casosprueba	  		= recursosRoot + "casosprueba" + slash;
	}
}
