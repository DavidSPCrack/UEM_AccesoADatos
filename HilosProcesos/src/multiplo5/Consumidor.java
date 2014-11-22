package multiplo5;

public class Consumidor implements Runnable {

	H h;

	Consumidor(H h) {
		this.h = h;
		new Thread(this, "Consumidor").start();
	}

	public void run() {
		int cont = 1;
		do {
			cont = h.get();
		} while (cont < 100);
	}
}
