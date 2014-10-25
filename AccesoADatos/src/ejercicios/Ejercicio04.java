package ejercicios;

import java.io.FileReader;
import java.io.IOException;

public class Ejercicio04 {
	
	private static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

	public static String readTextFile(String path) {
		StringBuilder sb = new StringBuilder();
		FileReader fR = null;
		try {
			fR = new FileReader(path);
			int car = 0;
			while ((car = fR.read()) != -1) {
				sb.append((char) car);
			}
		} catch (IOException ioe) {
		} finally {
			if (fR != null) {
				try {
					fR.close();
				} catch (IOException ioe) {
				}
			}
		}
		return sb.toString();
	}

	public static int contarLineas(String path) {
		String contenido = readTextFile(path);
		String[] pLineas = contenido.split("[\\r]+");
		int nLin = pLineas.length;
		return nLin;
	}

	public static int contarPalabras(String path) {
		return contarPalabras(path, null);
	}

	public static int contarPalabras(String path, String suffix) {
		String contenido = readTextFile(path);
		String[] pPalabras = contenido.split("\\W+");
		int nPal = 0;
		for (int i = 0; i < pPalabras.length; i++) {
			if (pPalabras[i].matches("[a-zA-Z]")) {
				if (suffix == null)
					nPal++;
				else if (pPalabras[i].endsWith(suffix))
					nPal++;
			}
		}
		return nPal;
	}

	public static int contarCaracteres(String path) {
		FileReader fR = null;
		int nCar = 0;
		try {
			fR = new FileReader(path);
			while ((fR.read()) != -1) {
				nCar++;
			}
		} catch (IOException ioe) {
		} finally {
			if (fR != null) {
				try {
					fR.close();
				} catch (IOException ioe) {
				}
			}
		}
		return nCar;
	}

	public static int contarLetrasMinusculasPuras(String path) {
		FileReader fR = null;
		int nCar = 0;
		try {
			fR = new FileReader(path);
			int num = 0;
			while ((num = fR.read()) != -1) {
				char car = (char) num;
				if (LETRAS_MINUSCULAS.indexOf(car) != -1) {
					nCar++;
				}
			}
		} catch (IOException ioe) {
		} finally {
			if (fR != null) {
				try {
					fR.close();
				} catch (IOException ioe) {
				}
			}
		}
		return nCar;
	}
}
