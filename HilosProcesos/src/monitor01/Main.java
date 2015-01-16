package monitor01;

public class Main {
	
	public static void main(String[] args) {
		Monitor monitor = new Monitor();
		Escribir hilo1 = new Escribir("soy el hilo1", 0, monitor);
		hilo1.start();
		Escribir hilo2 = new Escribir("soy el hilo2", 1, monitor);
		hilo2.start();
		Escribir hilo3 = new Escribir("soy el hilo3", 2, monitor);
		hilo3.start();
	}

}
