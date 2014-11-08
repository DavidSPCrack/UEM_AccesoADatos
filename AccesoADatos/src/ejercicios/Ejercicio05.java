package ejercicios;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio05 {

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
