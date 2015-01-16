package monitor01;

public class Escribir extends Thread {

	private Monitor monitor;
	private String texto;
	private int orden;

	public Escribir(String texto, int orden, Monitor monitor) {
		this.texto = texto;
		this.monitor = monitor;
		this.orden = orden;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				monitor.escribir(texto + " ", orden);
				sleep((int) (Math.random() * 10));
			} catch (InterruptedException ex) {

			}
		}
	}

}
