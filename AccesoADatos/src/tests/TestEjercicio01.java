package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import ejercicios.Ejercicio01;

public class TestEjercicio01 {

	@Test
	public void testListarDirectorio01() {
		String directorio = "C:\\test2\\";
		File file = new File(directorio);
		String[] ficheros1 = file.exists() ? file.list() : new String[0];
		Ejercicio01 ejercicio = new Ejercicio01();
		String[] ficheros2 = ejercicio.listarDirectorio(directorio);
		assertArrayEquals(ficheros1, ficheros2);
	}
	
	@Test
	public void testListarDirectorio02() {
		String directorio = "C:\\qqqqqq\\";
		File file = new File(directorio);
		String[] ficheros1 = file.exists() ? file.list() : new String[0];
		Ejercicio01 ejercicio = new Ejercicio01();
		String[] ficheros2 = ejercicio.listarDirectorio(directorio);
		assertArrayEquals(ficheros1, ficheros2);
	}
}
