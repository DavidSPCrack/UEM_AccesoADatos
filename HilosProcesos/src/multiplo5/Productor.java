package multiplo5;

public class Productor implements Runnable {

	H h;

	Productor(H h) {
		this.h = h;
		new Thread(this, "Productor").start();
	}

	public void run() {
		int cont = 1;

		while (cont < 101) {
			System.out.println(h.getTexto("Mensaje del hilo1:: ", "", cont));
			h.put(cont++);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}

	}

}
