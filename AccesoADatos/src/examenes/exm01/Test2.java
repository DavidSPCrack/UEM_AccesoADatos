package examenes.exm01;

import java.io.File;

public class Test2 {

	public static void main(String[] args) {
		Test.main(args);

		GestorBar gb = new GestorBar();

		gb.migrateFromCSV();

		System.out.println("añadir bar");

		Bar bar = new Bar("La céltica", "Alcalá 35", "Cervecería", 6);

		gb.guardarBar(bar);

		gb.imprimir();

		System.out.println("eliminar bar");

		bar = new Bar("Casa Blas", "Plaza España 3", "Tapas", 3);

		gb.eliminarBar(bar.getId());

		gb.imprimir();

		File file = new File(GestorBar.FICHERO_BINARIO);
		file.delete();
		file = new File(GestorBar.FICHERO);
		file.delete();

	}

}
