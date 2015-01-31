package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author David
 */
public class Servidor3 {
	public static void main(String args[]) {
		ServerSocket servidor;
		Socket conexion;
		DataOutputStream salida;
		DataInputStream entrada;
		int num = 0;
		try {
			servidor = new ServerSocket(5000);
			System.out.println("Servidor Arrancado correctamente");
			while (true) {
				conexion = servidor.accept();
				num++;
				System.out.println("Conexión número" + num + " desde: " + conexion.getInetAddress().getHostName());

				entrada = new DataInputStream(conexion.getInputStream());
				salida = new DataOutputStream(conexion.getOutputStream());

				int mensaje = entrada.readInt();
				int mensaje1 = entrada.readInt();

				System.out.println("Conexión n." + num + " mensaje: \n" + "Números enviados:" + mensaje + " y " + mensaje1);

				int solucion = mensaje + mensaje1;

				salida.writeUTF("La suma de los dos números es " + solucion);

				conexion.close();

			}
		} catch (IOException e) {
		}
	}
}
