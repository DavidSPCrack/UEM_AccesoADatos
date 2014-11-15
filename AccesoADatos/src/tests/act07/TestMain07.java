package tests.act07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tests.TestEncapsulado;
import ejercicios.act07.Bicicleta;
import ejercicios.act07.ManagerBicicleta;

public class TestMain07 {

	private static InputStreamReader iSR = new InputStreamReader(System.in);
	private static BufferedReader bR = new BufferedReader(iSR);

	public static String readLine() throws IOException {
		return bR.readLine();
	}

	public static void main(String[] args) throws IOException {
		initializeTestEjercicio07();
		System.exit(0);
	}

	public static void initializeTestEjercicio07() {
		testBicicleta();
		testManager();
	}

	private static void testBicicleta() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				Bicicleta bicicleta = new Bicicleta(1, true, "15/11/2014", 10);
				return bicicleta.toString();
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("Información de la bicicleta: ");
		test.run();
	}

	private static void testManager() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				ManagerBicicleta manager = new ManagerBicicleta();
				manager.dummy();
				Bicicleta[] bicis = manager.obtenerBicis();

				StringBuilder sb = new StringBuilder();
				for (Bicicleta bicicleta : bicis) {
					sb.append(bicicleta.toString());
					sb.append("\r\n");
				}
				return sb.toString();
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("");
		test.run();
	}
}
