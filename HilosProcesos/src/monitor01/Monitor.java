package monitor01;

public class Monitor {

	private int actual = 0;

	public synchronized void escribir(String texto, int orden) {

		while (orden != actual) {
			try {
				wait();
			} catch (InterruptedException ex) {

			}
		}

		System.out.print(texto + "\r");
		actual = (actual + 1) % 3;
		notifyAll();
	}

}
