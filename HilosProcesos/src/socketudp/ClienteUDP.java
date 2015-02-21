package socketudp;

/**
 *
 * @author David
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteUDP {

	public static void main(String[] args) {

		// Flujo para entrada estándar
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		try {

			while (true) {
				// Socket cliente
				DatagramSocket clientSocket = new DatagramSocket();

				byte[] enviados = new byte[1024];
				byte[] recibidos = new byte[1024];

				// Datos del servidor al que enviar el mensaje
				// Loclahost
				InetAddress IPServidor = InetAddress.getLocalHost();
				int puerto = 9876;

				// Introducir datos por el teclado
				System.out.println("Introduce mensaje: ");
				String cadena = in.readLine();
				enviados = cadena.getBytes();

				// Enviando cadena al servidor
				System.out.println("Enviando: " + enviados.length + " bytes al servidor.");
				DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);
				clientSocket.send(envio);

				// Recibiendo datagrama del servidor
				DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
				System.out.println("Esperando Datagrama....");
				clientSocket.receive(recibo);
				String mayuscula = new String(recibo.getData());

				// Obteniendo información del datagrama
				InetAddress IPOrigen = recibo.getAddress();
				int puertoOrigen = recibo.getPort();
				System.out.println("\tProcedente de: " + IPOrigen + ": " + puertoOrigen);
				System.out.println("\tDatos: " + mayuscula.trim());

				clientSocket.close();
			}

		} catch (SocketException ex) {
			Logger.getLogger(ClienteUDP.class.getName()).log(Level.SEVERE, null, ex);
		} catch (Exception e) {

		}

	}
}