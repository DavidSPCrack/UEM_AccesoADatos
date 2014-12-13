package tests;

import java.util.ArrayList;

import exm01.Alumno;
import exm01.GestorAlumno;

public class Test {

	/**
	 * Metodo main para probar la primera parte del examen
	 * 
	 * @param args
	 *            parametros del main
	 */
	public static void main(String[] args) {

		GestorAlumno ga = new GestorAlumno();

		ga.generarCabecera();

		ga.guardarAlumnoCSV(1, "Carlos", "Acceso Datos", 9);
		ga.guardarAlumnoCSV(2, "Pepe", "Acceso Datos", 8);
		ga.guardarAlumnoCSV(3, "Maria", "Sistemas de Gestión Empresarial", 8);
		ga.guardarAlumnoCSV(4, "Lucia", "Inglés", 8);

		ArrayList<Alumno> alumnos = ga.obtenerAlumnosCSV();
		for (Alumno alumno : alumnos) {
			System.out.println(alumno);
			System.out.println();
		}

	}

}
