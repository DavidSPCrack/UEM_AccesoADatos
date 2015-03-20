package examen.act01;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteMesa {

	private static InputStreamReader iSR = new InputStreamReader(System.in);
	private static BufferedReader bR = new BufferedReader(iSR);

	public static void main(String[] args) {
		Socket socket = null;
		DataOutputStream salida = null;
		DataInputStream entrada = null;
		int puerto = 12345;
		int numeroMesa = 1;
		try {
			socket = new Socket(InetAddress.getLocalHost(), puerto);

			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());

			System.out.print("¿Cuantos comensales van a ser?: ");
			int nComensales = Integer.parseInt(bR.readLine());
			salida.writeInt(nComensales);
			salida.writeInt(numeroMesa);
			salida.writeUTF("Cliente 1");

			int resultado = entrada.readInt();

			//System.out.println("El cuadrado del número " + numero + " es " + resultado);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("Número introducido no correcto");
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {

				}
			}
		}

	}
}
