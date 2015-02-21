package sockets3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionServidor implements Runnable {

	private Socket socket;

	public ConexionServidor(Socket socket) {
		this.socket = socket;
		new Thread(this).start();
	}

	public void run() {
		DataOutputStream salida = null;
		DataInputStream entrada = null;

		try {

			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());

			int numero = entrada.readInt();
			String cliente = entrada.readUTF();

			int cuadrado = (int) Math.pow(numero, 2);
			System.out.println("El " + cliente + " ha mandado el número " + numero);
			salida.writeInt(cuadrado);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (entrada != null)
				try {
					entrada.close();
				} catch (IOException e) {
				}
			if (salida != null)
				try {
					salida.close();
				} catch (IOException e) {
				}
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
				}
		}
	}

}
