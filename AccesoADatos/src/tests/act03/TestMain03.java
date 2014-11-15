package tests.act03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ejercicios.act03.Ejercicio03;

public class TestMain03 {

	private static InputStreamReader iSR = new InputStreamReader(System.in);
	private static BufferedReader bR = new BufferedReader(iSR);

	public static String readLine() throws IOException {
		return bR.readLine();
	}

	public static void main(String[] args) throws IOException {
		initializeTestEjercicio03();
		System.exit(0);
	}

	public static void initializeTestEjercicio03() throws IOException {
		mostrarMenu();
	}

	public static void mostrarMenu() throws IOException {
		String response = "";
		boolean continueExec = true;
		do {
			System.out.println("Introduzca un fichero: ");
			response = readLine();
			continueExec = !response.equals("-1");
			if (continueExec)
				testEjercicio03(response);
			else
				System.out.println("¡Hasta luego!");
		} while (continueExec);
	}

	public static void testEjercicio03(String path) throws IOException {
		String contenido = Ejercicio03.readTextFile(path, Integer.MAX_VALUE);

		System.out.println("Contenido del fichero:");
		System.out.println(contenido);
	}

}
