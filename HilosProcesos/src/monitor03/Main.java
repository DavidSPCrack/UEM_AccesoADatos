package monitor03;

public class Main {

	public static void main(String[] args) {
		Monitor monitor = new Monitor();
		for (int i = 0; i < 10; i++) {
			String nameAlumno = "Alumno".concat(Integer.toString(i + 1));
			new Alumno(nameAlumno, i, monitor);
		}
		new Profesor("Jose Antonio Perez", 10, monitor);
	}

}
