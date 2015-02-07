package multicast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMC1 {

	public static class ServidorRecepcion implements Runnable {

		public ServidorRecepcion() {
			new Thread(this).start();
		}

		public void run() {
			ServerSocket servidor;
			Socket conexion;
			DataInputStream entrada;
			int num = 0;
			try {
				servidor = new ServerSocket(5000);
				System.out.println("Servidor Arrancado correctamente");
				while (true) {
					conexion = servidor.accept();
					num++;
					System.out.println("Conexi�n n�mero " + num + " desde: " + conexion.getInetAddress().getHostName());
					entrada = new DataInputStream(conexion.getInputStream());
					String mensaje = entrada.readUTF();
					System.out.println(mensaje);
				}
			} catch (IOException e) {

			}
		}

	}

	public static void main(String[] args) throws Exception {
		new ServidorRecepcion();
		// Flujo de Entrada Est�ndar
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// Se crea el Socket Multicast
		MulticastSocket ms = new MulticastSocket();
		System.out.println("Socket Multicast abierto. El Servidor est� arrancado...");
		// Puerto Multicast
		int Puerto = 12345;
		// Grupo Multicast
		InetAddress grupo = InetAddress.getByName("225.0.0.7");
		String cadena = "";
		while (!cadena.trim().equals("fin")) {
			System.out.println("Datos a enviar al Grupo Multicast. Introduce un mensaje aqu�: ");
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