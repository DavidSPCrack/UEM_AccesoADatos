package resueltos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class ServidorMC2 {

	public static void main(String[] args) throws Exception {

		// Flujo de Entrada Estándar
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String cadena, cadena2 = "";
		// El Servidor recibe los datos de un Cliente
		DatagramSocket ds = null;
		try {
			byte[] buf = new byte[128];
			System.out.println("El Servidor está esperando datos...");
			ds = new DatagramSocket(12346);
			while (true) {

				// Formamos el datagrama para empezar a recibir datos
				DatagramPacket paquete = new DatagramPacket(buf, buf.length);

				// Orden para recibir paquetes
				ds.receive(paquete);
				cadena = new String(paquete.getData());
				System.out.println("Datos Recibidos ..." + cadena);
				String trozo[] = cadena.split(":");
				String nombre = trozo[0];
				int numero = Integer.parseInt(trozo[1]);

				System.out.println("Datos recibidos del " + nombre + " >> Numero = " + numero);

				if (numero % 2 == 0) {
					cadena2 = "Mensaje del Servidor:  el " + nombre + " ha enviado un número PAR";
					System.out.println(cadena2);

				} else {
					cadena2 = "Mensaje del Servidor:  el " + nombre + " ha enviado un número IMPAR";
					System.out.println(cadena2);
				}

				// buf.equals("");

				// Se crea el Socket Multicast
				MulticastSocket ms = new MulticastSocket();
				System.out.println("Socket Multicast creado...");

				// Puerto Multicast
				int Puerto = 12345;

				// Grupo Multicast
				InetAddress grupo = InetAddress.getByName("225.0.0.1");

				// ENVÍO DE MENSAJES MULTICAST
				// while (!cadena2.trim().equals("fin")) {
				System.out.println("Datos a enviar al Grupo Multicast");
				System.out.println(cadena2);
				// Enviando al grupo Multicast
				DatagramPacket paquete2 = new DatagramPacket(cadena2.getBytes(), cadena2.length(), grupo, Puerto);
				ms.send(paquete2);
				cadena = "";
				// }

				// Se cierra el Socket Multicast
				ms.close();
				System.out.println("Socket Multicast cerrado ...");
			}

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (ds != null)
				ds.close();
		}

	}
}
