package ejercicios.act05;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class Ejercicio05 {

	private final static String[] RESULTADOS = { "1", "X", "2" };

	public static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
	public static final String[] LOREM_IPSUM_WORDS;
	static {
		LOREM_IPSUM_WORDS = LOREM_IPSUM.split("\\s");
	}

	public static void writeLoremIpsum(int words, int lines, String path) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines; i++) {
			for (int j = 0; j < words || j < LOREM_IPSUM_WORDS.length; j++) {
				if (j > 0) {
					sb.append(" ");
				}
				sb.append(LOREM_IPSUM_WORDS[j]);
			}
			sb.append("\r\n");
		}
		guardarFichero(sb.toString(), path);
	}

	public static void writeQuiniela(String[] partidos, String path) {
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < partidos.length; i++) {
			int res = rnd.nextInt(RESULTADOS.length);
			sb.append(partidos[i]);
			sb.append(" - ");
			sb.append(RESULTADOS[res]);
			sb.append("\r\n");
		}
		guardarFichero(sb.toString(), path);
	}

	public static String readFibonacci(String path) {
		String secFibo = leerFicheroBinario(path);
		return secFibo;
	}

	public static void writeFibonacci(int n, String path) {
		String secFibo = getFibonacci(n);
		guardarFichero(secFibo, path);
	}

	public static String getFibonacci(int n) {
		StringBuilder sb = new StringBuilder();
		int n1 = 1;
		int n2 = 1;
		for (int i = 0; i < n; i++) {
			if (i > 0) {
				sb.append(" ");
			}
			if (i == 0) {
				sb.append(n1);
			} else if (i == 1) {
				sb.append(n2);
			} else {
				int n3 = n1 + n2;
				sb.append(n3);
				n1 = n2;
				n2 = n3;
			}
		}
		return sb.toString();
	}

	public static void guardarCsv(String[][] contenidoCsv, String path) {
		StringBuilder sb = new StringBuilder();
		for (String[] rows : contenidoCsv) {
			for (int i = 0; i < rows.length; i++) {
				String col = rows[i];
				if (i > 0) {
					sb.append(";");
				}
				sb.append(col);
			}
			sb.append("\r\n");
		}
		guardarFichero(sb.toString(), path);
	}

	public static void guardarFicheroBinario(String texto, String path) {
		FileOutputStream fOS = null;
		try {
			fOS = new FileOutputStream(path);

			fOS.write(texto.getBytes());
		} catch (IOException e) {
			System.out.println("Error de I/O");
		} finally {
			try {
				if (fOS != null)
					fOS.close();
			} catch (Exception e) {
			}
		}
	}

	public static String leerFicheroBinario(String path) {
		FileInputStream fIS = null;
		StringBuilder sb = new StringBuilder();
		try {
			fIS = new FileInputStream(path);

			int car = 0;
			while ((car = fIS.read()) != -1) {
				sb.append((char) car);
			}
		} catch (IOException e) {
			System.out.println("Error de I/O");
		} finally {
			try {
				if (fIS != null)
					fIS.close();
			} catch (Exception e) {
			}
		}
		return sb.toString();
	}

	public static void guardarFichero(String texto, String path) {
		BufferedWriter writer = null;
		try {
			File fichero = new File(path);

			if (fichero.exists()) {
				fichero.delete();
			}

			FileWriter fW = new FileWriter(fichero);

			writer = new BufferedWriter(fW);
			writer.write(texto);
		} catch (IOException e) {
			System.out.println("Error de I/O");
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
			}
		}
	}
}
