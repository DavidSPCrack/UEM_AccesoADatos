package ejercicios2.a;

public class Incrementer implements Runnable {

	private H h;
	private String title;
	private boolean isProducer;

	Incrementer(H h, String title, boolean isProducer) {
		this.h = h;
		this.title = title.concat(":: ");
		this.isProducer = isProducer;
		new Thread(this, title).start();

	}

	public void run() {
		int cont = 1;
		while (cont < 100) {
			cont = h.increment(title, isProducer);
		}

	}

}
