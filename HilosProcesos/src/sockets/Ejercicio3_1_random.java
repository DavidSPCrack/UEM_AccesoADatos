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
public class Ejercicio3_1_random {

	public static void main(String[] args) {

		Socket cliente;
		DataInputStream entrada;
		DataOutputStream salida;
		String respuesta;
		int mensaje, mensaje1;

		try {
			cliente = new Socket(InetAddress.getLocalHost(), 5000);

			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());

			double num = Math.floor(Math.random() * 10);
			int num1 = (int) num;
			num = Math.floor(Math.random() * 10);
			int num2 = (int) num;
			mensaje = num1;
			mensaje1 = num2;

			salida.writeInt(mensaje);
			salida.writeInt(mensaje1);

			respuesta = entrada.readUTF();
			System.out.println("Mi mensaje: \n" + "Número 1: " + mensaje + "\nNúmero 2: " + mensaje1);
			System.out.println("Respuesta del Servidor: " + respuesta);

			cliente.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
