package monitor02.a;

public class Contador {

	// Atributo Contador
	private int c = 0;

	// Constructor
	Contador(int c) {
		this.c = c;
	}

	// Métodos
	public void incrementa() {
		c = c + 1;
	}

	public void decrementa() {
		c = c - 1;
	}

	public int getValor() {
		return c;
	}

}
