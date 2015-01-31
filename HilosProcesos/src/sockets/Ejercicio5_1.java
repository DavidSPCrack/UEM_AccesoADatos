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
public class Ejercicio5_1 {

	public static void main(String[] args) {

		Socket cliente;
		DataInputStream entrada;
		DataOutputStream salida;
		String respuesta;

		try {
			cliente = new Socket(InetAddress.getLocalHost(), 5000);

			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());

			salida.writeDouble(1.0);
			salida.writeDouble(2.0);
			salida.writeDouble(3.0);
			salida.writeDouble(4.0);
			salida.writeDouble(5.0);
			salida.writeDouble(6.0);
			salida.writeDouble(7.0);
			salida.writeDouble(8.0);
			salida.writeDouble(9.0);
			salida.writeDouble(10.0);

			respuesta = entrada.readUTF();
			System.out.println("Mi mensaje: \n" + "10 números enviados");
			System.out.println("Respuesta del Servidor: " + respuesta);

			cliente.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
