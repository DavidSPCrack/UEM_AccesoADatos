package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ejercicios.Ejercicio04;

public class TestMain04 {

	private final static String PATH_FILE_TEXT = "./resources/QUIJOTE_CP1252.txt";

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
		testContarPalabrasSufijo();
		testContarLineas();
		testContarLetrasMinusculasPuras();
		testContarLetras();
		testContarDiptongos();
		testPalabraMasLarga();
	}

	private static void testContarCaracteres() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				int nume = Ejercicio04.contarCaracteres(PATH_FILE_TEXT);
				return Integer.toString(nume);
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Número de caracteres: ");
		test.run();
	}

	private static void testContarPalabras() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				int nume = Ejercicio04.contarPalabras(PATH_FILE_TEXT);
				return Integer.toString(nume);
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Número de palabras: ");
		test.run();
	}

	private static void testContarPalabrasSufijo() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				int nume = Ejercicio04.contarPalabras(PATH_FILE_TEXT, "cion");
				return Integer.toString(nume);
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Número de palabras terminadas en cion: ");
		test.run();
	}

	private static void testContarLineas() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				int nume = Ejercicio04.contarLineas(PATH_FILE_TEXT);
				return Integer.toString(nume);
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Número de lineas: ");
		test.run();
	}

	private static void testContarLetrasMinusculasPuras() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				int nume = Ejercicio04.contarLetrasMinusculasPuras(PATH_FILE_TEXT);
				return Integer.toString(nume);
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Número de letras minusculas puras: ");
		test.run();
	}

	private static void testContarLetras() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				int nume = Ejercicio04.contarLetras(PATH_FILE_TEXT);
				return Integer.toString(nume);
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Número de letras: ");
		test.run();
	}

	private static void testContarDiptongos() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				int nume = Ejercicio04.contarDiptongos(PATH_FILE_TEXT);
				return Integer.toString(nume);
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Número de diptongos: ");
		test.run();
	}

	private static void testPalabraMasLarga() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				String result = Ejercicio04.palabraMasLarga(PATH_FILE_TEXT);
				return result;
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("");
		test.run();
	}

}
