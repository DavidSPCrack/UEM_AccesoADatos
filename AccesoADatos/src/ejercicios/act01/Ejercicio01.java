package ejercicios.act01;

import java.io.File;

public class Ejercicio01 {

	public String[] listarDirectorio(String directorio) {
		File file = new File(directorio);
		if (file.exists() && file.isDirectory()) {
			String[] ficheros = file.list();
			return ficheros;
		}
		return new String[0];
	}
}
