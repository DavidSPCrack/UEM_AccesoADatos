package examen.act04;

public class Hilo1 implements Runnable {

	H h;

	Hilo1(H h) {
		this.h = h;
		new Thread(this, "Productor").start();
	}

	public void run() {
		System.out.println("El Hilo1 ha comenzado a generar números pares");
		int cont = 0;

		while (cont < 101) {
			cont += 2;
			h.put(cont);
		}

	}

}
