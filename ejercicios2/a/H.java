package ejercicios2.a;

public class H {

	private int n;
	private boolean producerTurn;

	public H() {
		this.n = 0;
		this.producerTurn = true;
	}

	public synchronized int increment(String title, boolean producer) {
		while (this.producerTurn != producer) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.producerTurn = !this.producerTurn;
		System.out.println(getTexto(title, n++));
		notify();
		return this.n;
	}

	public static void main(String[] args) {
		H h = new H();
		new Incrementer(h, "Hilo1", true);
		new Incrementer(h, "Hilo2", false);
	}

	public synchronized String getTexto(String title, int n) {
		StringBuffer sb = new StringBuffer();
		sb.append(title);
		sb.append(n);
		return sb.toString();
	}

}
