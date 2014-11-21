package hilosejemplo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class HilosEjemplo implements Runnable {

	private static final CyclicBarrier cb = new CyclicBarrier(4);

	private String texto;

	private HilosEjemplo(String texto) {
		this.texto = texto;
	}

	@Override
	public void run() {
		try {
			HilosEjemplo.cb.await();
		} catch (InterruptedException e) {
		} catch (BrokenBarrierException e) {
		}
		System.out.println(texto);
	}

	public static void main(String[] args) {
		Thread hilo1 = new Thread(new HilosEjemplo("Buenos dias! 1"));
		Thread hilo2 = new Thread(new HilosEjemplo("Buenos dias! 2"));
		Thread hilo3 = new Thread(new HilosEjemplo("Buenos dias! 3"));

		hilo1.setPriority(Thread.NORM_PRIORITY);
		hilo2.setPriority(Thread.MIN_PRIORITY);
		hilo3.setPriority(Thread.MAX_PRIORITY);

		hilo1.start();
		hilo2.start();
		hilo3.start();

		try {
			HilosEjemplo.cb.await();
		} catch (InterruptedException e) {
		} catch (BrokenBarrierException e) {
		}
	}
}
