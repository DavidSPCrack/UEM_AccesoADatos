package ejercicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ejercicio04 {

	static final String LETRAS_MINUSCULAS = "abcdefghijklmn�opqrstuvwxyz";
	static final String LETRAS_MINUSCULAS_ACENT = "��������������������";
	static final String LETRAS_MAYUSCULAS = "ABCDEFGHIJKLMN�OPQRSTUVWXYZ";
	static final String LETRAS_MAYUSCULAS_ACENT = "��������������������";
	static final String CIFRAS = "0123456789";

	static String palabraMasLarga = "";
	static int posPalabraMasLarga = 0;

	private static final int BUFFER_SIZE = 1024;

	public static String quitarTildes(String cadena) {
		char[] caracteres = cadena.toCharArray();
		for (int i = 0; i < caracteres.length; i++) {
			switch (caracteres[i]) {
			case '�':
				caracteres[i] = 'A';
				break;
			case '�':
				caracteres[i] = 'E';
				break;
			case '�':
				caracteres[i] = 'I';
				break;
			case '�':
				caracteres[i] = 'O';
				break;
			case '�':
				caracteres[i] = 'U';
				break;
			case '�':
				caracteres[i] = 'a';
				break;
			case '�':
				caracteres[i] = 'e';
				break;
			case '�':
				caracteres[i] = 'i';
				break;
			case '�':
				caracteres[i] = 'o';
				break;
			case '�':
				caracteres[i] = 'u';
				break;
			case '�':
				caracteres[i] = 'A';
				break;
			case '�':
				caracteres[i] = 'E';
				break;
			case '�':
				caracteres[i] = 'I';
				break;
			case '�':
				caracteres[i] = 'O';
				break;
			case '�':
				caracteres[i] = 'U';
				break;
			case '�':
				caracteres[i] = 'a';
				break;
			case '�':
				caracteres[i] = 'e';
				break;
			case '�':
				caracteres[i] = 'i';
				break;
			case '�':
				caracteres[i] = 'o';
				break;
			case '�':
				caracteres[i] = 'u';
				break;
			case '�':
				caracteres[i] = 'A';
				break;
			case '�':
				caracteres[i] = 'E';
				break;
			case '�':
				caracteres[i] = 'I';
				break;
			case '�':
				caracteres[i] = 'O';
				break;
			case '�':
				caracteres[i] = 'U';
				break;
			case '�':
				caracteres[i] = 'a';
				break;
			case '�':
				caracteres[i] = 'e';
				break;
			case '�':
				caracteres[i] = 'i';
				break;
			case '�':
				caracteres[i] = 'o';
				break;
			case '�':
				caracteres[i] = 'u';
				break;
			case '�':
				caracteres[i] = 'A';
				break;
			case '�':
				caracteres[i] = 'E';
				break;
			case '�':
				caracteres[i] = 'I';
				break;
			case '�':
				caracteres[i] = 'O';
				break;
			case '�':
				caracteres[i] = 'U';
				break;
			case '�':
				caracteres[i] = 'a';
				break;
			case '�':
				caracteres[i] = 'e';
				break;
			case '�':
				caracteres[i] = 'i';
				break;
			case '�':
				caracteres[i] = 'o';
				break;
			case '�':
				caracteres[i] = 'u';
				break;
			case '�':
				caracteres[i] = 'A';
				break;
			case '�':
				caracteres[i] = 'a';
				break;
			case '�':
				caracteres[i] = 'A';
				break;
			case '�':
				caracteres[i] = 'O';
				break;
			case '�':
				caracteres[i] = 'a';
				break;
			case '�':
				caracteres[i] = 'o';
				break;
			}

		}
		return new String(caracteres);
	}

	public static boolean comprobarDiptongoAbiertoCerrado(char[] cars, int i) {
		if (cars.length > i)
			switch (cars[i]) {
			case 'h':
				return comprobarDiptongoAbiertoCerrado(cars, ++i);
			case 'i':
			case 'u':
			case 'y':
				return true;
			}
		return false;
	}

	public static boolean comprobarDiptongoCerrado(char[] cars, int i) {
		if (cars.length > i)
			switch (cars[i]) {
			case 'h':
				return comprobarDiptongoCerrado(cars, ++i);
			case 'a':
			case 'e':
			case 'o':
			case '�':
			case '�':
			case '�':
			case 'i':
			case 'u':
			case 'y':
				return true;
			}
		return false;
	}

	public static boolean comprobarDiptongo(char[] cars, int i) {
		if (cars.length > i)
			switch (cars[i]) {
			case 'a':
			case 'e':
			case 'o':
			case '�':
			case '�':
			case '�':
				return comprobarDiptongoAbiertoCerrado(cars, ++i);
			case 'i':
			case 'u':
				return comprobarDiptongoCerrado(cars, ++i);
			}
		return false;
	}

	public static boolean tieneDiptongo(String palabra) {
		palabra = palabra.toLowerCase();
		char[] cars = palabra.toCharArray();
		for (int i = 0; i < cars.length; i++) {
			if (comprobarDiptongo(cars, i)) {
				return true;
			}
		}
		return false;
	}

	public static boolean palabraTerminaEn(String palabra, String suffix) {
		String aux1 = quitarTildes(palabra).toLowerCase();
		String aux2 = quitarTildes(suffix).toLowerCase();
		return aux1.endsWith(aux2);
	}

	public static String[] getPalabras(String path) {
		return getPalabras(path, null);
	}

	public static String[] getPalabras(String path, String suffix) {
		String contenido = readTextFile(path);
		// String[] pPalabras = contenido.split("\\W+");
		// String[] pPalabras = contenido.split("(?U)[^\\p{Alpha}']+");
		String[] pPalabras = contenido.split("[^\\p{IsAlphabetic}']+");
		ArrayList<String> lista = new ArrayList<String>();
		for (int i = 0; i < pPalabras.length; i++) {
			if (pPalabras[i].matches("(.*)[a-zA-Z](.*)")) {
				if (suffix == null)
					lista.add(pPalabras[i]);
				else if (palabraTerminaEn(pPalabras[i], suffix))
					lista.add(pPalabras[i]);
			}
		}
		return lista.toArray(new String[lista.size()]);
	}

	public static String readTextFileBuffered(String path) {
		StringBuilder sb = new StringBuilder();
		FileReader fR = null;
		BufferedReader bR = null;
		try {
			fR = new FileReader(path);
			bR = new BufferedReader(fR, BUFFER_SIZE);
			String cads = null;
			while ((cads = bR.readLine()) != null) {
				sb.append(cads);
			}
		} catch (IOException ioe) {
		} finally {
			if (bR != null) {
				try {
					bR.close();
				} catch (IOException ioe) {
				}
			}
		}
		return sb.toString();
	}

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

	/**
	 * Metodo soluci�n Actividad 4.04
	 * 
	 * @param path
	 *            es la ruta al fichero de texto
	 * @return n� de lineas en total
	 */
	public static int contarLineas(String path) {
		String contenido = readTextFile(path);
		String[] pLineas = contenido.split("[\\r]+");
		int nLin = pLineas.length;
		return nLin;
	}

	/**
	 * Metodo soluci�n Actividad 4.05
	 * 
	 * @param path
	 *            es la ruta al fichero de texto
	 * @return n� de palabras en total
	 */
	public static int contarPalabras(String path) {
		return contarPalabras(path, null);
	}

	/**
	 * Metodo soluci�n Actividad 4.06
	 * 
	 * @param path
	 *            es la ruta al fichero de texto
	 * @param suffix
	 *            es el sufijo con el que debe acabar las palabras
	 * @return n� de palabras terminadas en un sufijo concreto
	 */
	public static int contarPalabras(String path, String suffix) {
		String[] palabras = getPalabras(path, suffix);
		return palabras.length;
	}

	/**
	 * Metodo soluci�n Actividad 4.07
	 * 
	 * @param path
	 *            es la ruta al fichero de texto
	 * @return n� de diptongos en el texto
	 */
	public static int contarDiptongos(String path) {
		String[] palabras = getPalabras(path);
		int nDip = 0;
		for (int i = 0; i < palabras.length; i++) {
			if (tieneDiptongo(palabras[i])) {
				nDip++;
			}
		}
		return nDip;
	}

	/**
	 * Metodo soluci�n Actividad 4.08
	 * 
	 * @param path
	 *            es la ruta al fichero de texto
	 * @return resultado de palabra mas larga, posici�n, etc.
	 */
	public static String palabraMasLarga(String path) {
		String[] palabras = getPalabras(path);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < palabras.length; i++) {
			int length1 = palabras[i].length();
			int length2 = palabraMasLarga.length();
			if (length1 > length2) {
				palabraMasLarga = palabras[i];
				posPalabraMasLarga = i;
			}
		}
		sb.append("La palabra mas larga es ");
		sb.append(palabraMasLarga.toUpperCase());
		sb.append(", tiene un total de ");
		sb.append(palabraMasLarga.length());
		sb.append(" caracteres y es la palabra n� ");
		sb.append(posPalabraMasLarga);
		return sb.toString();
	}

	/**
	 * Metodo soluci�n Actividad 4.01
	 * 
	 * @param path
	 *            es la ruta al fichero de texto
	 * @return n� de caracteres en total
	 */
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

	/**
	 * Metodo soluci�n Actividad 4.02
	 * 
	 * @param path
	 *            es la ruta al fichero de texto
	 * @return n� de letras minusculas puras
	 */
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

	/**
	 * Metodo soluci�n Actividad 4.03
	 * 
	 * @param path
	 *            es la ruta al fichero de texto
	 * @return n� de letras en total
	 */
	public static int contarLetras(String path) {
		FileReader fR = null;
		int nCar = 0;
		try {
			fR = new FileReader(path);
			int num = 0;
			while ((num = fR.read()) != -1) {
				char car = (char) num;
				if (Character.isLetter(car)) {
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
