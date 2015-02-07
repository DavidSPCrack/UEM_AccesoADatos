package sockets_2;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author David
 */
public class Ejercicio2_Cliente_3 {

	public static void main(String[] args) throws Exception {
		int Puerto = 12345;
		MulticastSocket ms = new MulticastSocket(Puerto);
		InetAddress grupo = InetAddress.getByName("225.0.0.7");
		ms.joinGroup(grupo);
		System.out.println("Socket abierto. Cliente1 unido al grupo multicast...");
		String msg = "";
		byte[] buf = new byte[1000];
		while (!msg.trim().equals("fin")) {
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			ms.receive(paquete);
			msg = new String(paquete.getData());
			System.out.println("Cliente 3 - Recibo el mensaje: " + msg.trim());
		}
		ms.leaveGroup(grupo);
		ms.close();
		System.out.println("Socket Multicast cerrado ...");
	}

}
