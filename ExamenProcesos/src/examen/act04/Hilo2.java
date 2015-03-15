package examen.act04;

public class Hilo2 implements Runnable {

	H h;

	Hilo2(H h) {
		this.h = h;
		new Thread(this, "Hilo2").start();
	}

	public void run() {
		System.out.println("El Hilo2 muestra solo los números que son múltiplos de 7");
		int cont = 1;
		do {
			cont = h.get();
		} while (cont < 100);
	}
}
