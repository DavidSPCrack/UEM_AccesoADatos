package socketudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ConexionUDP implements Runnable {

	private DatagramSocket serverSocket;
	private DatagramPacket clientPacket;
	private String cadena;

	public ConexionUDP(DatagramSocket serverSocket, DatagramPacket clientPacket) {
		this.serverSocket = serverSocket;
		this.clientPacket = clientPacket;
		this.cadena = new String(clientPacket.getData());
		new Thread(this).start();
	}

	@Override
	public void run() {
		try {
			byte[] enviados = new byte[1024];

			// Dirección de origen
			InetAddress IPOrigen = clientPacket.getAddress();
			int puerto = clientPacket.getPort();
			System.out.println("\tOrigen: " + IPOrigen + ": " + puerto);
			System.out.println("\tMensaje recibido: " + cadena.trim());

			// Convertir cadena a mayúsculas
			String mayuscula = cadena.trim().toUpperCase();
			enviados = mayuscula.getBytes();

			// Envio datagrama al cliente
			DatagramPacket paqEnviado = new DatagramPacket(enviados, enviados.length, IPOrigen, puerto);
			serverSocket.send(paqEnviado);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCadena() {
		return cadena;
	}
}
