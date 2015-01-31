package sockets;

import static java.lang.Math.sqrt;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author David
 */
public class Servidor7 {

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
				int num1 = entrada.readInt();

				System.out.println("Conexión n." + num + " mensaje: Nº enviado - " + num1);

				String solucion = "";
				int i = 0, j = 0, div = 0, raiz = 0;
				for (i = 1; i < 101; i++) {
					div = 0;
					raiz = (int) sqrt(i);
					for (j = 1; j <= raiz; j++) {
						if (i % j == 0) {
							div++;
						}
					}
					if (div <= 1) {
						solucion += i + ", ";
					}
				}

				String respuesta = "Los números primos de " + num1 + " son " + solucion;

				salida.writeUTF(respuesta);

				conexion.close();

			}
		} catch (IOException e) {
		}
	}
}
