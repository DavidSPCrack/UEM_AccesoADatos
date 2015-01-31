package multicast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class ServidorMC1 {
	public static void main(String[] args) throws Exception {
		// Flujo de Entrada Estándar
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Se crea el Socket Multicast
		MulticastSocket ms = new MulticastSocket();
		System.out.println("Socket Multicast abierto. El Servidor está arrancado...");
		// Puerto Multicast
		int Puerto = 12345;
		// Grupo Multicast
		InetAddress grupo = InetAddress.getByName("225.0.0.7");
		String cadena = "";
		while (!cadena.trim().equals("fin")) {
			System.out.println("Datos a enviar al Grupo Multicast. Introduce un mensaje aquí: ");
			cadena = in.readLine();
			// Enviando al grupo Multicast
			DatagramPacket paquete = new DatagramPacket(cadena.getBytes(), cadena.length(), grupo, Puerto);
			ms.send(paquete);
		}
		// Se cierra el Socket Multicast
		ms.close();
		System.out.println("Socket Multicast cerrado ...");
	}
}