package sockets3;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {

	public static void main(String[] args) {
		ServerSocket servidor = null;

		int port = 12345;
		boolean fin = false;

		try {
			servidor = new ServerSocket(port);
			while (!fin) {
				new ConexionServidor(servidor.accept());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
