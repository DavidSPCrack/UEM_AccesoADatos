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
			if (cont % 5 == 0) {
				System.out.println(h.getTexto("Mensaje del hilo2:: ", " es multiplo de 5", cont));
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		} while (cont < 100);
	}
}
