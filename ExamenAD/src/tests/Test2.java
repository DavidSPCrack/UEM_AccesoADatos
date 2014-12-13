package tests;

import exm01.Alumno;
import exm01.GestorAlumno;

public class Test2 {

	/**
	 * Metodo main para probar la segunda parte del examen
	 * 
	 * @param args
	 *            parametros del main
	 */
	public static void main(String[] args) {
		GestorAlumno ga = new GestorAlumno();

		if (ga.migrateFromCSV())
			System.out.println("Migrado fichero CSV\n");

		Alumno carlos = new Alumno(5, "Carlos", "Acceso Datos", 9);
		Alumno pepe = new Alumno(6, "Pepe", "Acceso Datos", 8);
		Alumno maria = new Alumno(7, "Maria", "Sistemas de Gesti�n Empresarial", 8);
		Alumno lucia = new Alumno(8, "Lucia", "Ingl�s", 8);

		if (ga.guardarAlumno(carlos))
			System.out.println("A�adido carlos");
		if (ga.guardarAlumno(pepe))
			System.out.println("A�adido pepe");
		if (ga.guardarAlumno(maria))
			System.out.println("A�adido maria");
		if (ga.guardarAlumno(lucia))
			System.out.println("A�adido lucia");

		System.out.println();

		ga.imprimir();

	}

}
