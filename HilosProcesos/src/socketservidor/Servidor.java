package socketservidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String args[]) {
		ServerSocket servidor;
		Socket conexion;
		DataOutputStream salida;
		DataInputStream entrada;
		int num = 0;
		try {
			// Creamos un ServerSocket en el puerto especificado
			servidor = new ServerSocket(5000);
			System.out.println("Servidor Arrancado correctamente");
			while (true) {
				// Se espera la conexi�n de alg�n cliente
				conexion = servidor.accept();
				num++;
				System.out.println("Conexi�n n�mero" + num + " desde: " + conexion.getInetAddress().getHostName());
				// Abrimos los canales de entrada y salida
				entrada = new DataInputStream(conexion.getInputStream());
				salida = new DataOutputStream(conexion.getOutputStream());
				// Leemos el mensaje del cliente
				String mensaje = entrada.readUTF();
				System.out.println("Conexi�n n." + num + " mensaje: " + mensaje);
				// Le respondemos al cliente
				salida.writeUTF("Buenos d�as " + mensaje + " , Hoy es s�bado, d�a de clase");
				// Se cierra la conexi�n
				conexion.close();
			}
		} catch (IOException e) {
		}
	}
}