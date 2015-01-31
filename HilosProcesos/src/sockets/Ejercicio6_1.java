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
public class Ejercicio6_1 {

	public static void main(String[] args) {

		Socket cliente;
		DataInputStream entrada;
		DataOutputStream salida;
		String respuesta, mensaje;

		try {
			cliente = new Socket(InetAddress.getLocalHost(), 5000);

			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());

			Double radioCircunf = 8.0;
			mensaje = "El radio de la circunferencia es";
			salida.writeDouble(radioCircunf);
			salida.writeUTF(mensaje);

			respuesta = entrada.readUTF();
			System.out.println("Mi mensaje: \n" + "10 números enviados");
			System.out.println("Respuesta del Servidor: " + respuesta);

			cliente.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
