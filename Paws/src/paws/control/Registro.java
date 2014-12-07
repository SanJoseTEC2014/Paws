package paws.control;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import paws.modelo.Mascota;
import paws.modelo.Usuario;

public class Registro {

	public static boolean isCorreoInvalido(String email) {
		final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		 // Interpreta la expresión regular dada como un Pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        // Compara la entrada dada contra este Pattern
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
	}

	public static boolean hayCamposVacios(LinkedList<String> camposPorValidar) {
		for (String campo : camposPorValidar) {
			if (campo.trim().isEmpty()) return true;
		}
		return false;
	}

	public static boolean isNicknameRepetido(String pNickname){
		for (Usuario x : Principal.usuarios) {
			if (x.getNickname().equals(pNickname)) return true;
		}
		return false;
	}
	
	public static boolean isNicknameRestringido(String pNickname){
		return (pNickname.equals("pitbull"));
	}
	
	public static boolean isNumeroChipMascotaRepetido(String pNumeroChip){
		for (Mascota mascota : Principal.mascotas) {
			if (mascota.getNumeroChip().equals(pNumeroChip)) return true;
		}
		return false;
	}
}
