package ejercicios1;

public class Ejercicio1 implements Runnable {

	private String titulo;

	public Ejercicio1(String titulo) {
		this.titulo = titulo;
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			StringBuilder sb = new StringBuilder();
			sb.append("Comenzando por el ");
			sb.append(titulo);
			sb.append(", i=");
			sb.append(i);
			System.out.println(sb.toString());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread hilo1 = new Thread(new Ejercicio1("Hilo1"));
		Thread hilo2 = new Thread(new Ejercicio1("Hilo2"));
		Thread hilo3 = new Thread(new Ejercicio1("Hilo3"));
		
		hilo1.start();
		hilo1.join();
		hilo2.start();
		hilo2.join();
		hilo3.start();
		hilo3.join();
	}

}
