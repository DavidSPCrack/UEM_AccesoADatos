package examenes.exm01;

import java.io.File;

public class Test2 {

	public static void main(String[] args) {
		Test.main(args);

		GestorBar gb = new GestorBar();

		gb.migrateFromCSV();

		System.out.println("a�adir bar");

		Bar bar = new Bar("La c�ltica", "Alcal� 35", "Cervecer�a", 6);

		gb.guardarBar(bar);

		gb.imprimir();

		System.out.println("eliminar bar");

		bar = new Bar("Casa Blas", "Plaza Espa�a 3", "Tapas", 3);

		gb.eliminarBar(bar.getId());

		gb.imprimir();

		File file = new File(GestorBar.FICHERO_BINARIO);
		file.delete();
		file = new File(GestorBar.FICHERO);
		file.delete();

	}

}
