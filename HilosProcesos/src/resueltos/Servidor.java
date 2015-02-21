package resueltos;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {

	public static void main(String[] args) throws IOException {

		String nombre;
		long numero;

		ArrayList<Integer> fPrimos = new ArrayList<Integer>();

		ServerSocket ss = new ServerSocket(6677);
		System.out.println("El Servidor está arrancado ...");

		while (true) {
			fPrimos.clear();
			Socket s = ss.accept();

			DataInputStream is = new DataInputStream(s.getInputStream());
			nombre = is.readUTF();
			numero = is.readLong();

			System.out.println("Número recibido de " + nombre + ": " + numero);

			for (int i = 2; i <= numero; i++) {
				while (numero % i == 0) {
					numero = numero / i;
					fPrimos.add(i);
				}
			}

			// System.out.print(fPrimos + "\n");

			// ENVIO EL ARRAY DE FACTORES PRIMOS AL CLIENTE

			try {

				// Se crean el flujo de salida y el objeto de salida
				OutputStream os = s.getOutputStream();
				ObjectOutput oo = new ObjectOutputStream(os);

				// Se envían los factores primos del número al cliente
				oo.writeObject(fPrimos);
				oo.flush();

				s.close();

			} catch (Exception e) {
				System.err.println("excepcion " + e.toString());
				e.printStackTrace();
			}
		}
	}
}