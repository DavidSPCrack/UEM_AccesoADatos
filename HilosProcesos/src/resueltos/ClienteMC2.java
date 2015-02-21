package resueltos;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ClienteMC2 {

	public static void main(String[] args) throws Exception {

		// Puerto Multicast
		int Puerto = 12345;
		// Se crea el Socket Multicast
		MulticastSocket ms = new MulticastSocket(Puerto);
		// Grupo Multicast
		InetAddress grupo = InetAddress.getByName("225.0.0.1");
		// Este Cliente se une al Grupo Multicast
		ms.joinGroup(grupo);

		String msg = "";
		byte[] buf = new byte[1000];

		while (!msg.trim().equals("fin")) {
			buf.equals("");
			// Recibe el paquete del Servidor Multicast
			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			ms.receive(paquete);
			msg = new String(paquete.getData());
			System.out.println("Cliente 2 - Mensaje del Servidor: " + msg.trim());
			msg = "";
		}
		// Se abandona el Grupo Multicast
		ms.leaveGroup(grupo);
		// Se cierra el Socket Multicast
		ms.close();
		System.out.println("Socket Multicast cerrado ...");
	}
}
