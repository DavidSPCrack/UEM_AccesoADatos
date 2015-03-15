package examen.act02;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Calendar;

/**
 *
 * @author David
 */
public class Servidor {

	public static void main(String[] args) throws Exception {

		String cadena = "";

		DatagramSocket ds = null;
		try {
			byte[] buf = new byte[128];
			System.out.println("El Servidor está esperando datos...");
			ds = new DatagramSocket(12346);
			while (true) {

				DatagramPacket paquete = new DatagramPacket(buf, buf.length);

				ds.receive(paquete);
				cadena = new String(paquete.getData());
				System.out.println("Datos Recibidos ..." + cadena);
				String trozo[] = cadena.split("-");
				String nombre = trozo[0];

				Calendar calendario = Calendar.getInstance();
				int dia = calendario.get(Calendar.DATE);
				int mes = calendario.get(Calendar.MONTH);
				int ano = calendario.get(Calendar.YEAR);
				int hora = calendario.get(Calendar.HOUR_OF_DAY);
				int minutos = calendario.get(Calendar.MINUTE);
				System.out.println("Mensaje recibido de Id_cliente: " + nombre + " fecha y hora de recepción: " + dia + "/" + mes + "/" + ano + " " + hora + ":"
						+ minutos);

				MulticastSocket ms = new MulticastSocket();
				System.out.println("Socket Multicast creado...");

				int Puerto = 12345;

				InetAddress grupo = InetAddress.getByName("225.0.0.1");

				System.out.println("Datos a enviar al Grupo Multicast");
				cadena = "El Cliente " + nombre + " está preparado. fecha y hora del aviso al servidor " + dia + "/" + mes + "/" + ano + " " + hora + ":" + minutos;
				System.out.println(cadena);

				DatagramPacket paquete2 = new DatagramPacket(cadena.getBytes(), cadena.length(), grupo, Puerto);
				ms.send(paquete2);

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
