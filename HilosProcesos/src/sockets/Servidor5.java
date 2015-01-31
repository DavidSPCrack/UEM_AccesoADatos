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
public class Servidor5 {

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
				Double[] cadena = new Double[10];

				for (int i = 0; i < 10; i++) {
					cadena[i] = entrada.readDouble();
				}

				System.out.println("Conexión n." + num + " mensaje:");
				String valores = "";
				Double total = 0.0;

				for (int i = 0; i < cadena.length; i++) {
					valores += cadena[i] + ", ";
					total = total + cadena[i];
				}

				Double solucion = total / cadena.length;
				String respuesta = "La media de :" + valores + " es " + solucion;

				salida.writeUTF(respuesta);

				conexion.close();

			}
		} catch (IOException e) {
		}
	}

}
