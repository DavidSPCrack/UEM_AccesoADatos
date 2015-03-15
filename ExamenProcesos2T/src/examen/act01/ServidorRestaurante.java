package examen.act01;

import java.io.IOException;
import java.net.ServerSocket;

public class ServidorRestaurante {

	public static void main(String[] args) {
		ServerSocket servidor = null;

		int port = 12345;
		boolean fin = false;

		try {
			servidor = new ServerSocket(port);
			System.out.println("Servidor del restaurante arrancado con exito");
			int nCon = 1;
			while (!fin) {
				new ConexionServidor(servidor.accept(), nCon++);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
