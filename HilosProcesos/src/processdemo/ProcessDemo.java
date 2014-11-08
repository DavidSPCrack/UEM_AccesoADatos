package processdemo;

import java.io.InputStream;

/**
 *
 * @author José Antonio Pérez
 */
public class ProcessDemo {
	public static void main(String[] args) {
		try {
			// Se crea un nuevo proceso
			System.out.println("Creando los Procesos p1, p2, p3 ...");
			Process p1 = Runtime.getRuntime().exec("notepad.exe");
			Process p2 = Runtime.getRuntime().exec("calc.exe");
			Process p3 = Runtime.getRuntime().exec("taskmgr.exe");
			// Se obtiene el flujo de entrada del proceso p1 y se muestra por pantalla
			InputStream in = p1.getInputStream();
			for (int i = 0; i < in.available(); i++) {
				System.out.println("" + in.read());
			}
			// Se espera 10 segundos y luego se destruye el proceso p1
			Thread.sleep(10000);
			p1.destroy();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
