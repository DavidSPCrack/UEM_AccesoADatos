package hilosejemplo;

public class PingPong implements Runnable {

	private String texto;

	public PingPong(String texto) {
		this.texto = texto;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 15; i++) {
				System.out.println(texto);
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			return;
		}

	}

	public static void main(String[] args) {
		Thread hilo1 = new Thread(new PingPong("Ping"));
		Thread hilo2 = new Thread(new PingPong("Pong"));

		hilo1.start();
		hilo2.start();

	}

}
