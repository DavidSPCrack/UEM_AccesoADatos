package socketcliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {
	public static void main(String[] args) throws SocketException, UnknownHostException, IOException, InterruptedException {
		// Creamos el socket UDP
		DatagramSocket socket = new DatagramSocket();
		int i = 0;
		System.out.println("Cliente iniciado correctamente …");
		while (true) {
			i++;
			Thread.sleep(100);
			if (i < 10) {
				int aleatorio = (int) (Math.random() * 1000);
				String cadena = "<00" + i + ":" + aleatorio + ">";
				// Creamos el datagrama y lo enviamos con los datos señalados
				// (argumentos)
				DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), InetAddress.getLocalHost(), 5001);
				socket.send(paquete);
			} else if (i >= 10 && i < 100) {
				int aleatorio = (int) (Math.random() * 1000);
				String cadena = "<0" + i + ":" + aleatorio + ">";
				DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), InetAddress.getLocalHost(), 5001);
				socket.send(paquete);
			} else if (i >= 100) {
				int aleatorio = (int) (Math.random() * 1000);
				String cadena = "<" + i + ":" + aleatorio + ">";
				DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), InetAddress.getLocalHost(), 5001);
				socket.send(paquete);
			}
		}
	}
}