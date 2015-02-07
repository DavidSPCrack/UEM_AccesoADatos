package sockets_2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author David
 */
public class Ejercicio2_Servidor_Multicast {

	public static void main(String[] args) throws Exception {

		ServerSocket servidor;
		Socket conexion = null;
		DataInputStream entrada;
		int num = 0;
		MulticastSocket ms = new MulticastSocket();
		System.out.println("Socket Multicast abierto. El Servidor está arrancado...");

		int Puerto = 12345;

		InetAddress grupo = InetAddress.getByName("225.0.0.7");
		String cadena = "";
		String mensaje = "";

		while (!cadena.trim().equals("fin")) {

			try {
				servidor = new ServerSocket(5000);
				System.out.println("Servidor Arrancado correctamente");
				while (true) {
					conexion = servidor.accept();
					num++;
					System.out.println("Conexión número" + num + " desde: " + conexion.getInetAddress().getHostName());
					entrada = new DataInputStream(conexion.getInputStream());

					mensaje = entrada.readUTF();

					int valor = Integer.parseInt(mensaje);
					String resultado = "";

					if (valor % 2 == 0) {
						resultado = "PAR";
					} else {
						resultado = "IMPAR";
					}
					System.out.println("Conexión n." + num + " mensaje: " + mensaje + "\nResultado: " + resultado);

					DatagramPacket paquete = new DatagramPacket(resultado.getBytes(), resultado.length(), grupo, Puerto);
					ms.send(paquete);
				}
			} catch (IOException e) {
			}

		}

		ms.close();
		System.out.println("Socket Multicast cerrado ...");
		conexion.close();

	}

}
