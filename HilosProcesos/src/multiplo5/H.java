package multiplo5;

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
		new Productor(h);
		new Consumidor(h);
	}

	public synchronized String getTexto(String titulo, String subTitulo, int n) {
		StringBuffer sb = new StringBuffer();
		sb.append(titulo);
		sb.append(n);
		sb.append(subTitulo);
		return sb.toString();
	}

}
