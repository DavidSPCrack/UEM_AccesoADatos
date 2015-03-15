package examen.act04;

public class H {

	private int n;
	private boolean valueSet;

	public H() {
		this.n = 1;
		this.valueSet = false;
	}

	public synchronized int get() {
		while (!valueSet) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		valueSet = false;
		if (n % 7 == 0)
			System.out.println(getTexto("Hilo2: ", n));

		notify();
		return this.n;
	}

	public synchronized void put(int n) {
		while (valueSet) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		valueSet = true;
		this.n = n;
		notify();
	}

	public static void main(String[] args) {
		H h = new H();
		new Hilo1(h);
		new Hilo2(h);
	}

	public synchronized String getTexto(String titulo, int n) {
		StringBuffer sb = new StringBuffer();
		sb.append(titulo);
		sb.append(n);
		return sb.toString();
	}

}
