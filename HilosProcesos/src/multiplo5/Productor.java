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
			h.put(cont++);
		}

	}

}
