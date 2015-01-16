package ejercicios.act09;

import framework.ErrorGeneral;

public class Main {

	public static void main(String[] args) {
		try {
			ADTiempo adatos = new ADTiempo();
			adatos.createTable();
			adatos.selectTiempo();
		} catch (ErrorGeneral e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
