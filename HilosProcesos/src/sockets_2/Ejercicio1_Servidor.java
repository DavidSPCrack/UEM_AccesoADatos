package sockets_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author David
 */
public class Ejercicio1_Servidor {

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
				int[] arrayIntServ = new int[3];

				for (int i = 0; i < 3; i++) {
					arrayIntServ[i] = entrada.readInt();
				}

				int[] arrayResp = { 1, 1, 1 };

				for (int i = 0; i < arrayIntServ.length; i++) {

					if (arrayIntServ[i] == 0) {
						arrayResp[i] = 1;
					} else {
						for (int j = 1; j <= arrayIntServ[i]; j++) {
							arrayResp[i] *= j;
						}
					}
				}

				System.out.println("Conexión n." + num + " mensaje: \n" + "factorial 1: " + arrayResp[0] + "\nfactorial 2:" + arrayResp[1] + "\nfactorial 3:"
						+ arrayResp[2]);

				for (int i = 0; i < 3; i++) {
					salida.writeInt(arrayResp[i]);
				}
				conexion.close();
			}
		} catch (IOException e) {
		}
	}

}
