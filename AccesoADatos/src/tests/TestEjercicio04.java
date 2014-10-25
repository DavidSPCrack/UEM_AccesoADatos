package tests;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import ejercicios.Ejercicio03;

public class TestEjercicio04 {

	@Test
	public void testLeerFichero1() {
		String path = "C:\\test2\\fichero.txt";
		int numCar = 10;

		StringBuilder sb = new StringBuilder();
		FileReader fR = null;
		try {
			fR = new FileReader(path);
			int car = 0;
			int i = 1;
			while ((car = fR.read()) != -1) {
				sb.append((char) car);
				if (i % numCar == 0) {
					sb.append("\n");
				}
				i++;
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
		String contenido = Ejercicio03.readTextFile(path);
		assertEquals(sb.toString(), contenido);
	}

	@Test
	public void testLeerFichero2() {
		String path = "C:\\test2\\fichero.txt";
		int numCar = 20;

		StringBuilder sb = new StringBuilder();
		FileReader fR = null;
		try {
			fR = new FileReader(path);
			int car = 0;
			int i = 1;
			while ((car = fR.read()) != -1) {
				sb.append((char) car);
				if (i % numCar == 0) {
					sb.append("\n");
				}
				i++;
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
		String contenido = Ejercicio03.readTextFile(path, numCar);
		assertEquals(sb.toString(), contenido);
	}

	@Test
	public void testLeerFichero3() {
		String path = "C:\\test2\\ficheroNoExiste.txt";
		int numCar = 20;

		StringBuilder sb = new StringBuilder();
		FileReader fR = null;
		try {
			fR = new FileReader(path);
			int car = 0;
			int i = 1;
			while ((car = fR.read()) != -1) {
				sb.append((char) car);
				if (i % numCar == 0) {
					sb.append("\n");
				}
				i++;
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
		String contenido = Ejercicio03.readTextFile(path, numCar);
		assertEquals(sb.toString(), contenido);
	}
}
