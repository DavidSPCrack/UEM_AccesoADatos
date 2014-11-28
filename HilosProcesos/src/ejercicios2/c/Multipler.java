package ejercicios2.c;

public class Multipler implements Runnable {

	private H h;
	private int n;
	private int multiple;
	private int max;

	Multipler(H h, int multiple) {
		this.h = h;
		this.n = 1;
		this.multiple = multiple;
		this.max = multiple * 10;
		new Thread(this, "Hilo".concat(String.valueOf(multiple))).start();

	}

	public void run() {
		while (n < max + 1) {
			h.calculate(this);
		}
	}

	public boolean isMultiple() {
		return getN() % getMultiple() == 0;
	}

	public int getMultiple() {
		return multiple;
	}

	public int getN() {
		return n;
	}

	public int getMax() {
		return max;
	}

	public void increment() {
		this.n++;
	}

	public boolean isEnded() {
		return getN() == getMax();
	}

}
