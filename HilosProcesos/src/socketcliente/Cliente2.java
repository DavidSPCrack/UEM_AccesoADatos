package socketcliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente2 {
	public static void main(String args[]) {
		Socket cliente;
		DataInputStream entrada;
		DataOutputStream salida;
		String mensaje, respuesta;
		try {
			// Creamos el socket para conectarnos al puerto 5000 del servidor
			cliente = new Socket(InetAddress.getLocalHost(), 5000);
			entrada = new DataInputStream(cliente.getInputStream());
			// Creamos los canales de entrada/salida
			salida = new DataOutputStream(cliente.getOutputStream());
			mensaje = "Profesor de la UPM";
			// Enviamos un mensaje al servidor
			salida.writeUTF(mensaje);
			// Leemos la respuesta
			respuesta = entrada.readUTF();
			System.out.println("Mi mensaje: " + mensaje);
			System.out.println("Respuesta del Servidor: " + respuesta);
			// Se cierra la conexión
			cliente.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}