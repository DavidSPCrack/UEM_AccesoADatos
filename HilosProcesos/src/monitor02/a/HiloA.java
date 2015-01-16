package monitor02.a;


public class HiloA extends Thread {

	private Contador contador;

	public HiloA(String n, Contador c) {
		setName(n);
		contador = c;
	}

	public void run() {
		synchronized (contador) {
			for (int j = 0; j < 300; j++) {
				// Se incrementa el contador
				contador.incrementa();
				try {
					sleep(1);
				} catch (InterruptedException e) {
				}
			}
			System.out.println(getName() + " el contador vale " + contador.getValor());
		}
	}
}
