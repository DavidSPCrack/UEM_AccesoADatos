package simplethreads;

public class SimpleThreads {
	// Se muestra un mensaje precedido por el nombre del hilo que está en ejecución
	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

	private static class MessageLoop implements Runnable {
		public void run() {
			String importantInfo[] = { "Las yeguas comen avena", "Sí, comen avena", "Los corderos pequeños comen hiedra",
					"Por tanto, un niño también comerá hiedra" };
			try {
				for (int i = 0; i < importantInfo.length; i++) {
					// Se genera una pausa de 4 segundos
					Thread.sleep(4000);
					// Se muestra un mensaje
					threadMessage(importantInfo[i]);
				}
			} catch (InterruptedException e) {
				threadMessage("No había terminado aún!");
			}
		}
	}

	public static void main(String args[]) throws InterruptedException {
		// Tiempo, en milisegundos, antes de interrumpir el hilo MessageLoop
		// Por defecto, es una hora.
		long patience = 1000 * 60 * 60;
		// Si el argumento en línea de comandos está presente, se pone patience en segundos
		if (args.length > 0) {
			try {
				patience = Long.parseLong(args[0]) * 1000;
			} catch (NumberFormatException e) {
				System.err.println("El argumento debe ser un entero.");
				System.exit(1);
			}
		}
		threadMessage("Se ejecuta el hilo MessageLoop");
		long startTime = System.currentTimeMillis();
		Thread t = new Thread(new MessageLoop());
		t.start();
		threadMessage("Se espera a que el hilo MessageLoop acabe");
		// Se hace un bucle hasta que el hilo MessageLoop acabe
		while (t.isAlive()) {
			threadMessage("Todavía esperando...");
			// Se espera como mucho 1 segundo a que el hilo MessageLoop acabe.
			t.join(1000);
			if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
				threadMessage("Cansado de esperar!");
				t.interrupt();
				// No debería tardar tanto en acabar -- espera indefinidamente
				t.join();
			}
		}
		threadMessage("Por fin acabó!!");
	}
}