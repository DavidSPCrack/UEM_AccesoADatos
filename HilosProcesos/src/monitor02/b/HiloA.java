package monitor02.b;

import java.util.concurrent.Semaphore;

public class HiloA extends Thread {

	private Contador contador;
	private Semaphore s;

	public HiloA(String n, Contador c, Semaphore s) {
		setName(n);
		contador = c;
		this.s = s;
	}

	public void run() {
		try {
			s.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int j = 0; j < 300; j++) {
			// Se incrementa el contador
			contador.incrementa();
			try {
				sleep(1);
			} catch (InterruptedException e) {
			}
		}
		System.out.println(getName() + " el contador vale " + contador.getValor());
		s.release();
	}
}
