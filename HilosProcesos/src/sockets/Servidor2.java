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
public class Servidor2 {

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
				String mensaje = entrada.readUTF();
				System.out.println("Conexión n." + num + " mensaje: " + mensaje);

				int numero = Integer.parseInt(mensaje);
				int solucion = 0;
				for (int i = 0; i <= numero; i++) {
					solucion += i;
				}

				salida.writeUTF("La suma de todos los números enteros hasta " + numero + " es " + solucion);
				conexion.close();
			}
		} catch (IOException e) {
		}
	}

}
