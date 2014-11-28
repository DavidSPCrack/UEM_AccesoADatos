package ejercicios2.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class H {

	static final int MULTIPLE_THREE = 3;
	static final int MULTIPLE_SEVEN = 7;
	static final int MULTIPLE_ELEVEN = 11;

	private boolean occupied;
	private boolean noItems;
	private int nThreadEnded = 0;
	private List<String> multiplos = Collections.synchronizedList(new ArrayList<String>());

	public H() {
		this.noItems = true;
		this.occupied = false;
	}

	public synchronized void calculate(Multipler mult) {
		while (occupied || !noItems) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		this.occupied = true;
		if (mult.isMultiple()) {
			addMultipleNumber(mult);
			mult.increment();
			this.noItems = false;
		} else {
			mult.increment();
			this.occupied = false;
			this.noItems = false;
		}
		if (mult.isEnded())
			this.nThreadEnded++;
		notifyAll();
	}

	public synchronized void printNumber() {
		while (noItems) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		for (int i = 0; i < this.multiplos.size(); i++) {
			String mult = this.multiplos.get(i);
			System.out.println(mult);
			this.multiplos.remove(i--);
		}
		this.occupied = false;
		this.noItems = true;
		notifyAll();
	}

	public boolean isEnded() {
		return nThreadEnded == 3;
	}

	private void addMultipleNumber(Multipler mult) {
		StringBuffer sb = new StringBuffer();
		sb.append("El número ");
		sb.append(mult.getN());
		sb.append(" es múltiplo de ");
		sb.append(mult.getMultiple());
		this.multiplos.add(sb.toString());
	}

	public synchronized String getTexto(String title, int n) {
		StringBuffer sb = new StringBuffer();
		sb.append(title);
		sb.append(n);
		return sb.toString();
	}

	public static void main(String[] args) {
		H h = new H();
		new Multipler(h, MULTIPLE_ELEVEN);
		new Multipler(h, MULTIPLE_SEVEN);
		new Multipler(h, MULTIPLE_THREE);
		new Printer(h);
	}

}
