package simplethreads;

public class SimpleThreads {
	// Se muestra un mensaje precedido por el nombre del hilo que est� en ejecuci�n
	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

	private static class MessageLoop implements Runnable {
		public void run() {
			String importantInfo[] = { "Las yeguas comen avena", "S�, comen avena", "Los corderos peque�os comen hiedra",
					"Por tanto, un ni�o tambi�n comer� hiedra" };
			try {
				for (int i = 0; i < importantInfo.length; i++) {
					// Se genera una pausa de 4 segundos
					Thread.sleep(4000);
					// Se muestra un mensaje
					threadMessage(importantInfo[i]);
				}
			} catch (InterruptedException e) {
				threadMessage("No hab�a terminado a�n!");
			}
		}
	}

	public static void main(String args[]) throws InterruptedException {
		// Tiempo, en milisegundos, antes de interrumpir el hilo MessageLoop
		// Por defecto, es una hora.
		long patience = 1000 * 60 * 60;
		// Si el argumento en l�nea de comandos est� presente, se pone patience en segundos
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
			threadMessage("Todav�a esperando...");
			// Se espera como mucho 1 segundo a que el hilo MessageLoop acabe.
			t.join(1000);
			if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
				threadMessage("Cansado de esperar!");
				t.interrupt();
				// No deber�a tardar tanto en acabar -- espera indefinidamente
				t.join();
			}
		}
		threadMessage("Por fin acab�!!");
	}
}