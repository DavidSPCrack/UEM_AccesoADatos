package ejercicios2.c;

public class Printer implements Runnable {

	private H h;

	Printer(H h) {
		this.h = h;
		new Thread(this).start();

	}

	public void run() {
		boolean end = false;
		while (!end) {
			h.printNumber();
			end = h.isEnded();
		}

	}

}
