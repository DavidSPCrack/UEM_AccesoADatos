package socketservidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

import socketcliente.ClienteUDP;

public class ServidorUDP {
	public static void main(String[] args) throws SocketException {
		// Creamos el socket UDP
		DatagramSocket socket = new DatagramSocket(5001);
		System.out.println("Servidor iniciado correctamente …");
		int i = 0;
		while (true) {
			try {
				byte[] buf = new byte[128];
				// Formamos el datagrama para empezar a recibir datos
				DatagramPacket paquete = new DatagramPacket(buf, buf.length);
				// Orden para recibir paquetes
				socket.receive(paquete);
				String cadena = new String(paquete.getData());
				i++;
				System.out.println("Recibido de cliente: " + cadena);
				String trozo[] = cadena.split(":");
				String trozoBueno[] = trozo[0].split("<");
				int comprobar = Integer.parseInt(trozoBueno[1]);
				if (i == comprobar) {
					System.out.println("Correcto");
				} else {
					System.out.println("Ha habido un error");
				}
			} catch (IOException ex) {
				Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}