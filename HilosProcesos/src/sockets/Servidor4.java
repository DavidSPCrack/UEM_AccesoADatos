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
public class Servidor4 {

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
				Double mensaje = entrada.readDouble();
				String mensaje1 = entrada.readUTF();

				System.out.println("Conexión n." + num + " mensaje: \n" + "Temperatura:" + mensaje + " grados " + mensaje1);

				Double solucion;
				String respuesta;

				if (mensaje1.equals("Celsius")) {
					solucion = ((1.8) * mensaje) + 32;
					respuesta = mensaje + "Âº " + mensaje1 + " son " + solucion + "Âº Farenheit";
				} else {
					solucion = (mensaje - 32) / (1.8);
					respuesta = mensaje + "Âº " + mensaje1 + " son " + solucion + "Âº Celsius";
				}

				salida.writeUTF(respuesta);

				conexion.close();

			}
		} catch (IOException e) {
		}
	}
}
