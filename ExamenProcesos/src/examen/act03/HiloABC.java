package examen.act03;

public class HiloABC implements Runnable {

	public static final class H {

		private static final String A = "A";
		private static final String B = "B";
		private static final String C = "C";

		private int count = 0;
		private String letraCurrent;

		public H() {
			this.letraCurrent = C;
		}

		public synchronized void printHilo(HiloABC hilo) {
			while (!hilo.isLetraTurn(letraCurrent)) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			System.out.print(hilo.toString());
			nextLetra();
			notifyAll();
		}

		public void nextLetra() {
			if (this.letraCurrent.equals(C)) {
				this.letraCurrent = A;
			} else if (this.letraCurrent.equals(A)) {
				this.letraCurrent = B;
			} else if (this.letraCurrent.equals(B)) {
				this.letraCurrent = C;
				count++;
			}
		}

		public boolean isFinished() {
			int finishInteger = this.letraCurrent.equals(C) ? 5 : 4;
			return count >= finishInteger;
		}
	}

	private H h;
	private String titulo;
	private String letra;

	public HiloABC(H h, String titulo, String letra) {
		this.titulo = titulo;
		this.letra = letra;
		this.h = h;
		new Thread(this, titulo).start();
	}

	public void run() {
		while (!h.isFinished()) {
			h.printHilo(this);
		}
	}

	public boolean isLetraTurn(String letra) {
		return this.letra.equals(letra);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(titulo);
		sb.append(letra);
		sb.append(" ");
		return sb.toString();
	}

	public static void main(String[] args) {
		H h = new H();
		new HiloABC(h, "hilo1", H.A);
		new HiloABC(h, "hilo2", H.B);
		new HiloABC(h, "hilo3", H.C);
	}
}
