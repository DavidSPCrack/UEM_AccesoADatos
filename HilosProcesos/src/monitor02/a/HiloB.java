package monitor02.a;

public class HiloB extends Thread {

	private Contador contador;

	public HiloB(String n, Contador c) {
		setName(n);
		contador = c;
	}

	public void run() {
		synchronized (contador) {
			for (int j = 0; j < 300; j++) {
				// Se decrementa el contador
				contador.decrementa();
				try {
					sleep(1);
				} catch (InterruptedException e) {
				}
			}
			System.out.println(getName() + " el contador vale " + contador.getValor());
		}
	}

}
