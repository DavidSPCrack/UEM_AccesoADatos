package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author David
 * 
 */
public class Ejercicio7_1 {

	public static void main(String[] args) {

		Socket cliente;
		DataInputStream entrada;
		DataOutputStream salida;
		String respuesta;

		try {
			cliente = new Socket(InetAddress.getLocalHost(), 5000);

			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());

			int num = (int) Math.floor(Math.random() * 100);

			salida.writeInt(num);

			respuesta = entrada.readUTF();
			System.out.println("Mi mensaje: " + num);
			System.out.println("Respuesta del Servidor: " + respuesta);

			cliente.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
