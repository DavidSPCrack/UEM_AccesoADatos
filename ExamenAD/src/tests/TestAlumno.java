package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import exm01.Alumno;

public class TestAlumno {

	private final static String LS = System.getProperty("line.separator");

	@Test
	public void testAlumnoVoid() {
		Alumno alumno = new Alumno();
		assertEquals(alumno.getMatricula(), 0);
		assertEquals(alumno.getNombre(), "");
		assertEquals(alumno.getAsignatura(), "");
		assertEquals(alumno.getNota(), 0, 0);
	}

	@Test
	public void testAlumno() {
		Alumno alumno = new Alumno(1, "Carlos", "Acceso Datos", 9);
		assertEquals(alumno.getMatricula(), 1);
		assertEquals(alumno.getNombre(), "Carlos");
		assertEquals(alumno.getAsignatura(), "Acceso Datos");
		assertEquals(alumno.getNota(), 9, 0);
	}

	@Test
	public void testAlumnoToString() {
		Alumno alumno = new Alumno(1, "Carlos", "Acceso Datos", 9);

		StringBuilder expected = new StringBuilder();
		expected.append("Matrícula: ");
		expected.append(1);
		expected.append(LS);
		expected.append("Nombre: ");
		expected.append("Carlos");
		expected.append(LS);
		expected.append("Asignatura: ");
		expected.append("Acceso Datos");
		expected.append(LS);
		expected.append("Nota: ");
		expected.append(9.0);

		assertEquals(alumno.toString(), expected.toString());
	}

	@Test
	public void testAlumnoEquals() {
		Alumno alumno1 = new Alumno(1, "Carlos", "Acceso Datos", 9);
		Alumno alumno2 = new Alumno(1, "Carlos", "Acceso Datos", 9);

		assertEquals(alumno1, alumno2);
	}

	@Test
	public void testAlumnoNotEquals() {
		Alumno alumno1 = new Alumno(1, "Carlos", "Acceso Datos", 9);
		Alumno alumno2 = new Alumno(2, "Pepe", "Acceso Datos", 8);

		assertNotEquals(alumno1, alumno2);
	}

	@Test
	public void testAlumnoHashCode() {
		Alumno alumno1 = new Alumno(1, "Carlos", "Acceso Datos", 9);
		Alumno alumno2 = new Alumno(1, "Carlos", "Acceso Datos", 9);

		assertEquals(alumno1.hashCode(), alumno2.hashCode());
	}

	@Test
	public void testAlumnoNotHashCode() {
		Alumno alumno1 = new Alumno(1, "Carlos", "Acceso Datos", 9);
		Alumno alumno2 = new Alumno(2, "Pepe", "Acceso Datos", 8);

		assertNotEquals(alumno1.hashCode(), alumno2.hashCode());
	}

	@Test
	public void testAlumnoRegistroCSV() {
		Alumno alumno = new Alumno(1, "Carlos", "Acceso Datos", 9);

		StringBuilder expected = new StringBuilder();
		expected.append(1);
		expected.append(Alumno.getSeparador());
		expected.append("Carlos");
		expected.append(Alumno.getSeparador());
		expected.append("Acceso Datos");
		expected.append(Alumno.getSeparador());
		expected.append(9.0);

		assertEquals(alumno.getRegistroCSV(), expected.toString());
	}

}
