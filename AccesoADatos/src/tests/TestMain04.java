package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ejercicios.Ejercicio04;

public class TestMain04 {

	private final static String PATH_FILE_TEXT = "./resources/QUIJOTE.txt";

	private static InputStreamReader iSR = new InputStreamReader(System.in);
	private static BufferedReader bR = new BufferedReader(iSR);

	public static String readLine() throws IOException {
		return bR.readLine();
	}

	public static void main(String[] args) throws IOException {
		initializeTestEjercicio04();
		System.exit(0);
	}

	public static void initializeTestEjercicio04() {
		testContarCaracteres();
		testContarPalabras();
		testContarLineas();
		testContarLetrasMinusculasPuras();
	}

	public static void testContarCaracteres() {
		long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long start = System.currentTimeMillis();
		int nume = Ejercicio04.contarCaracteres(PATH_FILE_TEXT);
		long end = System.currentTimeMillis();
		long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long elapsed = end - start;
		long mem = endMem - startMem;

		StringBuilder sb = new StringBuilder();
		sb.append("Número de caracteres: ");
		sb.append(nume);
		sb.append(" (");
		sb.append(elapsed);
		sb.append("ms)");
		sb.append(" (");
		sb.append(mem / 1024 / 1024);
		sb.append(" Mb)");

		System.out.println(sb.toString());
	}

	public static void testContarPalabras() {
		long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long start = System.currentTimeMillis();
		int nume = Ejercicio04.contarPalabras(PATH_FILE_TEXT);
		long end = System.currentTimeMillis();
		long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long elapsed = end - start;
		long mem = endMem - startMem;

		StringBuilder sb = new StringBuilder();
		sb.append("Número de palabras: ");
		sb.append(nume);
		sb.append(" (");
		sb.append(elapsed);
		sb.append("ms)");
		sb.append(" (");
		sb.append(mem / 1024 / 1024);
		sb.append(" Mb)");

		System.out.println(sb.toString());
	}

	public static void testContarLineas() {
		long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long start = System.currentTimeMillis();
		int nume = Ejercicio04.contarLineas(PATH_FILE_TEXT);
		long end = System.currentTimeMillis();
		long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long elapsed = end - start;
		long mem = endMem - startMem;

		StringBuilder sb = new StringBuilder();
		sb.append("Número de lineas: ");
		sb.append(nume);
		sb.append(" (");
		sb.append(elapsed);
		sb.append("ms)");
		sb.append(" (");
		sb.append(mem / 1024 / 1024);
		sb.append(" Mb)");

		System.out.println(sb.toString());
	}

	public static void testContarLetrasMinusculasPuras() {
		long startMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long start = System.currentTimeMillis();
		int nume = Ejercicio04.contarLetrasMinusculasPuras(PATH_FILE_TEXT);
		long end = System.currentTimeMillis();
		long endMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long elapsed = end - start;
		long mem = endMem - startMem;

		StringBuilder sb = new StringBuilder();
		sb.append("Número de letras minusculas puras: ");
		sb.append(nume);
		sb.append(" (");
		sb.append(elapsed);
		sb.append("ms)");
		sb.append(" (");
		sb.append(mem / 1024 / 1024);
		sb.append(" Mb)");

		System.out.println(sb.toString());
	}

}
