package tests;

import java.io.IOException;

import ejercicios.Ejercicio03;

public class TestMain02 {

	public static void main(String[] args) throws IOException {
		String path = "C:\\test2\\fichero.txt";
		int numCar = 30;
		String contenido = Ejercicio03.readTextFile(path, numCar);

		System.out.println("Contenido del fichero:");
		System.out.println(contenido);
		System.exit(0);
	}

}
