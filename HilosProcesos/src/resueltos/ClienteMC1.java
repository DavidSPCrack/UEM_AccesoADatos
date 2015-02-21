package resueltos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class ClienteMC1 {

	public static void main(String[] args) throws Exception {

		System.out.println("Cliente 1 iniciado correctamente …");

		// ENVÍO DE DATOS A TRAVÉS DE SOCKET UDP

		// El Cliente enviará un número par o impar al Servidor
		// Creamos el datagrama y lo enviamos con los datos señalados
		// (argumentos)
		DatagramSocket ds = null;

		ds = new DatagramSocket();

		InputStreamReader FlujoIn = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(FlujoIn);

		System.out.print("Introduce un numero: ");
		String num;
		// num = Integer.parseInt(br.readLine());
		num = br.readLine();
		String cadena = "Cliente 1:" + num + ":>";
		System.out.println("Número introducido: " + num);
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

		// RECEPCIÓN DE MENSAJES MULTICAST
		// ==========================================
		// Puerto Multicast
		int Puerto = 12345;
		// Se crea el Socket Multicast
		MulticastSocket ms = new MulticastSocket(Puerto);
		// Grupo Multicast
		InetAddress grupo = InetAddress.getByName("225.0.0.1");
		// Este Cliente se une al Grupo Multicast
		ms.joinGroup(grupo);

		// El Cliente recibe un mensaje del Servidor

		String msg = "";
		byte[] buf = new byte[1000];

		while (!msg.trim().equals("fin")) {
			buf.equals("");
			// Recibe el paquete del Servidor Multicast
			DatagramPacket paquete2 = new DatagramPacket(buf, buf.length);
			ms.receive(paquete2);
			msg = new String(paquete2.getData());
			System.out.println("Cliente 1 - Mensaje del Servidor: " + msg.trim());

		}

		// Se abandona el Grupo Multicast
		ms.leaveGroup(grupo);
		// Se cierra el Socket Multicast

		ms.close();
		System.out.println("Socket Multicast cerrado ...");
	}
}
