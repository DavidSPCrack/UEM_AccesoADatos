package hilosejemplo;

public class Consumidor implements Runnable {

	public Consumidor() {
	}
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Yo soy el Consumidor. Consumido producto número " + i);
			Thread.yield();
		}
	}
}
