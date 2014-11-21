package hilosejemplo;

public class Productor implements Runnable {

	public Productor() {
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Yo soy el Productor. Producto producto número " + i);
			Thread.yield();
		}
	}
}