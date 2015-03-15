package examen.act02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author David
 */
public class Cliente {

	public static void main(String[] args) throws Exception {

		System.out.println("Cliente 1 iniciado correctamente …");

		DatagramSocket ds = null;

		ds = new DatagramSocket();

		String cadena = "1-Estoy preparado!!";
		System.out.println("Dato enviado: " + cadena);
		System.out.println(cadena.getBytes() + " " + cadena.length() + " " + InetAddress.getLocalHost() + " " + 12346);

		try {
			DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), InetAddress.getLocalHost(), 12346);
			ds.send(paquete);
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (ds != null)
				ds.close();
		}

		int Puerto = 12345;
		MulticastSocket ms = new MulticastSocket(Puerto);
		InetAddress grupo = InetAddress.getByName("225.0.0.1");
		ms.joinGroup(grupo);

		String msg = "";
		byte[] buf = new byte[1000];

		while (!msg.trim().equals("fin")) {
			buf.equals("");
			DatagramPacket paquete2 = new DatagramPacket(buf, buf.length);
			ms.receive(paquete2);
			msg = new String(paquete2.getData());
			System.out.println("Cliente 1 - Mensaje del Servidor: " + msg.trim());

		}

		ms.leaveGroup(grupo);

		ms.close();
		System.out.println("Socket Multicast cerrado ...");
	}

}
