package tests.act06;

import ejercicios.act06.Ejercicio06;

public class TestMain06 {
	final static String File = "./resources/Double.bin";
	final static String File4 = "./resources/EnterosLE.bin";

	public static void main(String[] args) {

		Ejercicio06 e = new Ejercicio06();

		// 1
		e.crearFicheroDouble(File);
		System.out.println("");
		// 2
		e.leerFicheroDouble(File);
		System.out.println("");
		// 3
		e.creaEnterosBin();
		System.out.println("");
		// 4
		e.guardarFichero(File4);
		e.leerfichero(File4);
		System.out.println("");
		// 5

	}
}
