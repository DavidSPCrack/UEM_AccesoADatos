package tests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ejercicios.Ejercicio05;

public class TestMain05 {

	private final static String PATH_FILE_LOREM_IPSUM = "./resources/lorem_ipsum.txt";
	private final static String PATH_FILE_QUINIELA = "./resources/quiniela.txt";
	private final static String PATH_FILE_FIBONACCI = "./resources/fibonacci.txt";
	private final static String PATH_FILE_CSV = "./resources/plantilla.csv";

	private final static String[][] CONTENIDO_CSV = { { "NOMBRE", "PRIMER_APELLIDO", "SEGUNDO_APELLIDO" }, { "Andres", "Alvarez", "Perez" },
			{ "Raul", "Pedraza", "León" }, { "Luis", "Salgado", "Manzano" }, { "Victor", "Uriarte", "Riaño" }, { "Ignacio Luis", "Papay", "Diaz" },
			{ "Samuel Matias", "Garcia", "Saboya" }, { "Ivan", "Reyes", "Garcia" }, { "David", "Sancho", "Pino" }, { "Daniel", "Sanchez", "Lopez" },
			{ "Antonio", "Toril", "Calvo" }, { "Benjamin", "Garrido", "Berreiro" }, { "Carlos", "Fernandez", "Tessier" } };

	private final static String[] PARTIDOS = { "Partido 1", "Partido 2", "Partido 3", "Partido 4", "Partido 5", "Partido 6", "Partido 7", "Partido 8", "Partido 9",
			"Partido 10", "Partido 11", "Partido 12", "Partido 13", "Partido 14", "Partido 15", "Partido 16" };

	private static InputStreamReader iSR = new InputStreamReader(System.in);
	private static BufferedReader bR = new BufferedReader(iSR);

	public static String readLine() throws IOException {
		return bR.readLine();
	}

	public static void main(String[] args) throws IOException {
		initializeTestEjercicio05();
		System.exit(0);
	}

	public static void initializeTestEjercicio05() {
		testCrearCsv();
		testFibonacci();
		testQuiniela();
		testLoremIpsum();
	}

	private static void testCrearCsv() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				Ejercicio05.guardarCsv(CONTENIDO_CSV, PATH_FILE_CSV);
				StringBuilder sb = new StringBuilder();
				sb.append("Fichero ");
				sb.append(PATH_FILE_CSV);
				sb.append(" creado con exito");
				return sb.toString();
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("");
		test.run();
	}

	private static void testFibonacci() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				Ejercicio05.writeFibonacci(20, PATH_FILE_FIBONACCI);
				String secFibo = Ejercicio05.readFibonacci(PATH_FILE_FIBONACCI);
				return secFibo;
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("");
		test.run();
	}

	private static void testQuiniela() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				Ejercicio05.writeQuiniela(PARTIDOS, PATH_FILE_QUINIELA);
				return "";
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Quiniela guardada con exito!");
		test.run();
	}

	private static void testLoremIpsum() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				Ejercicio05.writeLoremIpsum(20, 10, PATH_FILE_LOREM_IPSUM);
				return "";
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Lorem Ipsum creado con exito!");
		test.run();
	}
}
