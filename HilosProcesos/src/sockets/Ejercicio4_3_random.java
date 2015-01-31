package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author David
 */
public class Ejercicio4_3_random {

	public static void main(String[] args) {

		Socket cliente;
		DataInputStream entrada;
		DataOutputStream salida;
		String respuesta;
		Double mensaje;
		String mensaje1;

		try {
			cliente = new Socket(InetAddress.getLocalHost(), 5000);

			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());

			int num = (int) Math.floor(Math.random() * (1 - (2 + 1)) + (2 + 1));

			if (num == 1) {
				mensaje1 = "Farenheit";
			} else {
				mensaje1 = "Celsius";
			}

			mensaje = Math.floor(Math.random() * 100);

			salida.writeDouble(mensaje);
			salida.writeUTF(mensaje1);

			respuesta = entrada.readUTF();
			System.out.println("Mi mensaje: \n" + "Temperatura: " + mensaje + "º " + mensaje1);
			System.out.println("Respuesta del Servidor: " + respuesta);

			cliente.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
