package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import exm01.Alumno;
import exm01.GestorAlumno;

public class TestGestorAlumno {

	@Test
	public void testRegistroCSV() throws Exception {
		GestorAlumno ga = new GestorAlumno();

		ga.generarCabecera();

		ga.guardarAlumnoCSV(1, "Carlos", "Acceso Datos", 9);
		ga.guardarAlumnoCSV(2, "Pepe", "Acceso Datos", 8);
		ga.guardarAlumnoCSV(3, "Maria", "Sistemas de Gestión Empresarial", 8);
		ga.guardarAlumnoCSV(4, "Lucia", "Inglés", 8);

		ArrayList<Alumno> alumnos = ga.obtenerAlumnosCSV();
		for (int i = 0; i < alumnos.size(); i++) {
			Alumno alumno = alumnos.get(i);
			if (i == 0) {
				Alumno alumnoTest = new Alumno(1, "Carlos", "Acceso Datos", 9);
				assertEquals(alumno, alumnoTest);
			} else if (i == 1) {
				Alumno alumnoTest = new Alumno(2, "Pepe", "Acceso Datos", 8);
				assertEquals(alumno, alumnoTest);
			} else if (i == 2) {
				Alumno alumnoTest = new Alumno(3, "Maria", "Sistemas de Gestión Empresarial", 8);
				assertEquals(alumno, alumnoTest);
			} else if (i == 3) {
				Alumno alumnoTest = new Alumno(4, "Lucia", "Inglés", 8);
				assertEquals(alumno, alumnoTest);
			}
		}
	}

	@Test
	public void testRandomAcessFile() throws Exception {
		GestorAlumno ga = new GestorAlumno();

		ga.migrateFromCSV();

		Alumno carlos = new Alumno(5, "Carlos", "Acceso Datos", 9);
		Alumno pepe = new Alumno(6, "Pepe", "Acceso Datos", 8);
		Alumno maria = new Alumno(7, "Maria", "Sistemas de Gestión Empresarial", 8);
		Alumno lucia = new Alumno(8, "Lucia", "Inglés", 8);

		ga.guardarAlumno(carlos);
		ga.guardarAlumno(pepe);
		ga.guardarAlumno(maria);
		ga.guardarAlumno(lucia);

		Alumno[] alumnos = ga.obtenerAlumnos();
		for (int i = 0; i < alumnos.length; i++) {
			Alumno alumno = alumnos[i];
			if (i == 0) {
				Alumno alumnoTest = new Alumno(1, "Carlos", "Acceso Datos", 9);
				assertEquals(alumno, alumnoTest);
			} else if (i == 1) {
				Alumno alumnoTest = new Alumno(2, "Pepe", "Acceso Datos", 8);
				assertEquals(alumno, alumnoTest);
			} else if (i == 2) {
				Alumno alumnoTest = new Alumno(3, "Maria", "Sistemas de Gestión Empresarial", 8);
				assertEquals(alumno, alumnoTest);
			} else if (i == 3) {
				Alumno alumnoTest = new Alumno(4, "Lucia", "Inglés", 8);
				assertEquals(alumno, alumnoTest);
			} else if (i == 4) {
				Alumno alumnoTest = new Alumno(5, "Carlos", "Acceso Datos", 9);
				assertEquals(alumno, alumnoTest);
			} else if (i == 5) {
				Alumno alumnoTest = new Alumno(6, "Pepe", "Acceso Datos", 8);
				assertEquals(alumno, alumnoTest);
			} else if (i == 6) {
				Alumno alumnoTest = new Alumno(7, "Maria", "Sistemas de Gestión Empresarial", 8);
				assertEquals(alumno, alumnoTest);
			} else if (i == 7) {
				Alumno alumnoTest = new Alumno(8, "Lucia", "Inglés", 8);
				assertEquals(alumno, alumnoTest);
			}
		}

	}
}
