package ejercicios;

import java.io.FileReader;
import java.io.IOException;

public class Ejercicio03 {

	public static String readTextFile(String path) {
		return readTextFile(path, 10);
	}

	public static String readTextFile(String path, int numCar) {
		StringBuilder sb = new StringBuilder();
		FileReader fR = null;
		try {
			fR = new FileReader(path);
			int car = 0;
			int i = 1;
			while ((car = fR.read()) != -1) {
				sb.append((char) car);
				if (i % numCar == 0) {
					sb.append("\n");
				}
				i++;
			}
		} catch (IOException ioe) {
		} finally {
			if (fR != null) {
				try {
					fR.close();
				} catch (IOException ioe) {
				}
			}
		}
		return sb.toString();
	}
}
