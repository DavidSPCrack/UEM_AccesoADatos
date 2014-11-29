package examenes.exm01;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {

		GestorBar gb = new GestorBar();

		ArrayList<Bar> bares = new ArrayList<Bar>();

		bares.add(new Bar("El rebote", "gran v�a 3", "Tapas", 1));
		bares.add(new Bar("La competencia", "ave mar�a 23", "Restaurante", 2));
		bares.add(new Bar("Casa Blas", "Plaza Espa�a 3", "Tapas", 3));
		bares.add(new Bar("Dominos pizza", "Fuencarral 34", "Comida r�pida", 4));
		bares.add(new Bar("Burguer Queen", "Montera 12", "Comida r�pida", 5));

		gb.escribirCSVbar(bares);

		bares = gb.cargarCSVbar();

		for (Bar bar : bares)
			System.out.println(bar);
	}

}
