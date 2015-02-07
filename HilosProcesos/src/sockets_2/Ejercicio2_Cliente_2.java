package sockets_2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.Socket;

/**
 *
 * @author David
 */
public class Ejercicio2_Cliente_2 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int Puerto = 12345;
		MulticastSocket ms = new MulticastSocket(Puerto);
		InetAddress grupo = InetAddress.getByName("225.0.0.7");
		ms.joinGroup(grupo);
		System.out.println("Socket abierto. Cliente1 unido al grupo multicast...");
		String msg = "";
		byte[] buf = new byte[1000];
		while (!msg.trim().equals("fin")) {

			Socket cliente;
			DataOutputStream salida;
			String mensaje;

			int contador = 0;

			DatagramPacket paquete = new DatagramPacket(buf, buf.length);
			ms.receive(paquete);
			msg = new String(paquete.getData());
			System.out.println("Cliente 2 - Recibo el mensaje: " + msg.trim());
			contador++;

			if (contador != 0) {
				try {
					cliente = new Socket(InetAddress.getLocalHost(), 5000);

					salida = new DataOutputStream(cliente.getOutputStream());
					System.out.println("Escribr un texto a enviar");
					mensaje = in.readLine();
					salida.writeUTF(mensaje);

					cliente.close();

				} catch (IOException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}

		}
		ms.leaveGroup(grupo);
		ms.close();
		System.out.println("Socket Multicast cerrado ...");
	}

}
