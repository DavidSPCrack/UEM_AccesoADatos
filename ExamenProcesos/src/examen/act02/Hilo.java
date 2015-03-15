package examen.act02;

public class Hilo extends Thread {

	public void run() {
		Hilo.metodo();
	}

	public static synchronized void metodo() {
		System.out.println("HILO EJECUTADO - " + Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	}

	public static synchronized void metodo2() {
		System.out.println("SE HA EJECUTADO EL HILO - " + Thread.currentThread().getName());
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		Hilo hilo1 = new Hilo();
		Hilo hilo2 = new Hilo();

		hilo1.start();
		hilo2.start();
	}
}
