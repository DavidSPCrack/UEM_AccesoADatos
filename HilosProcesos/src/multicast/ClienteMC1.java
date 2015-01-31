package multicast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMC1 {
	public static void main(String[] args) throws Exception {
		// Puerto Multicast
		int Puerto = 12345;
		// Se crea el Socket Multicast
		MulticastSocket ms = new MulticastSocket(Puerto);
		// Grupo Multicast
		InetAddress grupo = InetAddress.getByName("225.0.0.7");
		// Este Cliente se une al Grupo Multicast
		ms.joinGroup(grupo);
		System.out.println("Socket abierto. Cliente1 unido al grupo multicast...");
		String msg = "";
		byte[] buf = new byte[1000];
		while (!msg.trim().equals("fin")) {
			// Recibe el paquete del Servidor Multicast
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			ms.receive(paquete);
			msg = new String(paquete.getData());
			System.out.println("Cliente 1 - Recibo el mensaje: " + msg.trim());
		}
		// Se abandona el Grupo Multicast
		ms.leaveGroup(grupo);
		// Se cierra el Socket Multicast
		ms.close();
		System.out.println("Socket Multicast cerrado ...");
	}
}