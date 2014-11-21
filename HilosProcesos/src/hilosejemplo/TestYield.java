package hilosejemplo;

public class TestYield {

	public static void main(String[] args) {
		Thread hilo1 = new Thread(new Productor());
		Thread hilo2 = new Thread(new Consumidor());

		hilo1.start();
		hilo2.start();

	}

}
