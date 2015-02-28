package socketudp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorUDP {

	public static void main(String[] args) {

		try {
			// Puerto por el que escucha el servidor 9876
			DatagramSocket serverSocket = new DatagramSocket(9876);
			byte[] recibidos = new byte[1024];
			String cadena;

			while (true) {
				System.out.println("Esperando datagrama....");

				// Recibo datagrama
				DatagramPacket paqRecibido = new DatagramPacket(recibidos, recibidos.length);
				serverSocket.receive(paqRecibido);
				ConexionUDP conexion = new ConexionUDP(serverSocket, paqRecibido);

				cadena = conexion.getCadena();

				// Para terminar
				if (cadena.trim().equals("*")) {
					break;
				}

			}

			serverSocket.close();
			System.out.println("Socket cerrado....");

		} catch (SocketException ex) {
			Logger.getLogger(ServidorUDP.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception e) {

		}

	}
}