package tests.act01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ejercicios.act01.Ejercicio01;

public class TestMain01 {

	private static InputStreamReader iSR = new InputStreamReader(System.in);
	private static BufferedReader bR = new BufferedReader(iSR);

	public static String readLine() throws IOException {
		return bR.readLine();
	}

	public static void main(String[] args) throws IOException {
		initializeTestEjercicio01();

		System.exit(0);
	}

	public static void initializeTestEjercicio01() throws IOException {
		mostrarMenuDirectorio();
	}

	public static void mostrarMenuDirectorio() throws IOException {
		String response = "";
		boolean continueExec = true;
		do {
			System.out.println("Introduzca un directorio: ");
			response = readLine();
			continueExec = !response.equals("-1");
			if (continueExec)
				testEjercicio01(response);
			else
				System.out.println("¡Hasta luego!");
		} while (continueExec);
	}

	public static void testEjercicio01(String directorio) throws IOException {
		Ejercicio01 ejercicio = new Ejercicio01();
		String[] ficheros = ejercicio.listarDirectorio(directorio);
		mostrarFicheros(directorio, ficheros);
	}

	public static void mostrarFicheros(String directorio, String[] ficheros) throws IOException {
		if (ficheros.length == 0) {
			System.out.println("No existe el directorio [" + directorio + "]");
		} else {

			System.out.println("Hay un total de " + ficheros.length + " ficheros");
			System.out.println("Los ficheros del directorio [" + directorio + "] son:");
			for (int i = 0; i < ficheros.length; i++) {
				System.out.println(ficheros[i]);
			}
		}
	}

}
