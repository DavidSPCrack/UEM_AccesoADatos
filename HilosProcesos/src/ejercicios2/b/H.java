package ejercicios2.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class H {

	static final int MULTIPLE_THREE = 3;
	static final int MULTIPLE_SEVEN = 7;
	static final int MULTIPLE_ELEVEN = 11;

	private int[] multiplers = new int[0];
	private int[] multiplersEnded = new int[0];
	private int multTurn = 0;

	private boolean occupied;
	private boolean noItems;
	private int nThreadEnded = 0;
	private List<String> multiplos = Collections.synchronizedList(new ArrayList<String>());

	public H() {
		this.noItems = true;
		this.occupied = false;
	}

	public synchronized void calculate(Multipler mult) {
		while (!isMultiplerTurn(mult)) {
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
		if (mult.isEnded()) {
			addEnded(mult.getMultiple());
			this.nThreadEnded++;
		}
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

	public void add(int multipler) {
		int[] aux = new int[this.multiplers.length + 1];
		System.arraycopy(this.multiplers, 0, aux, 0, this.multiplers.length);
		aux[this.multiplers.length] = multipler;
		this.multiplers = aux;
	}

	public void addEnded(int multipler) {
		int[] aux = new int[this.multiplersEnded.length + 1];
		System.arraycopy(this.multiplersEnded, 0, aux, 0, this.multiplersEnded.length);
		aux[this.multiplersEnded.length] = multipler;
		this.multiplersEnded = aux;
	}

	public boolean isMultiplerTurn(Multipler mult) {
		if (!(occupied || !noItems)) {
			if (mult.getMultiple() == getActualMultipler()) {
				advanceMultiplerTurn();
				return true;
			}
		}
		return false;
	}

	public void advanceMultiplerTurn() {
		if (this.multiplers.length - 1 == this.multTurn++) {
			this.multTurn = 0;
		}
	}

	public int getActualMultipler() {
		while (true) {
			int mult = this.multiplers[this.multTurn];
			if (!hasEnded(mult)) {
				return mult;
			}
			advanceMultiplerTurn();
		}
	}

	public boolean hasEnded(int mult) {
		for (int i = 0; i < this.multiplersEnded.length; i++) {
			if (this.multiplersEnded[i] == mult) {
				return true;
			}
		}
		return false;
	}

}
