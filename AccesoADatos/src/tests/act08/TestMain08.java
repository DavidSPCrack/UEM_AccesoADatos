package tests.act08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tests.TestEncapsulado;
import ejercicios.act07.Bicicleta;
import ejercicios.act07.ManagerBicicletaRandom;

public class TestMain08 {

	private static InputStreamReader iSR = new InputStreamReader(System.in);
	private static BufferedReader bR = new BufferedReader(iSR);

	private static ManagerBicicletaRandom manager = new ManagerBicicletaRandom();

	public static String readLine() throws IOException {
		return bR.readLine();
	}

	public static void main(String[] args) throws IOException {
		initializeTestEjercicio08();
		System.exit(0);
	}

	public static void initializeTestEjercicio08() {
		testManager01();
		testManager02();
		testManager03();
	}

	private static void testManager01() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {

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

	private static void testManager02() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				Bicicleta bici = manager.obtenerBici(34);
				return bici == null ? "" : bici.toString();
			}

		}
		DefaultTestEncapsulado test = new DefaultTestEncapsulado("");
		test.run();
	}

	private static void testManager03() {
		class DefaultTestEncapsulado extends TestEncapsulado {

			public DefaultTestEncapsulado(String textoDetalle) {
				super(textoDetalle);
			}

			public String executeMethod() {
				Bicicleta bici1 = new Bicicleta(47, false, "24/08/2013", 57);
				Bicicleta bici2 = manager.obtenerBici(78);
				Bicicleta bici3 = new Bicicleta(103, false, "15/07/2013", 87);
				Bicicleta bici4 = new Bicicleta(107, false, "19/09/2013", 34);

				manager.modificarBici(bici1);
				manager.eliminarBici(bici2);
				manager.guardarBici(bici3);
				manager.guardarBici(bici4);

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
