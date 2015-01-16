package monitor02.b;

import java.util.concurrent.Semaphore;

public class CompartirInfo {

	static Semaphore s;

	public static void main(String[] args) {
		s = new Semaphore(1, true);
		Contador cont = new Contador(100);
		HiloA a = new HiloA("HiloA", cont, s);
		HiloB b = new HiloB("HiloB", cont, s);
		a.start();
		b.start();
	}

}
