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
public class Servidor6 {

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
				entrada = new DataInputStream(conexion.getInputStream());
				salida = new DataOutputStream(conexion.getOutputStream());
				Double num1 = entrada.readDouble();
				String mensaje = entrada.readUTF();

				System.out.println("Conexión n." + num + " mensaje:" + mensaje + " " + num1);

				Double area = Math.rint(Math.PI * num1 * num1) / 100;
				Double perim = Math.rint(2 * Math.PI * num1) / 100;

				String respuesta = "El área de la circunferencia es " + area + " y el perímetro " + perim;

				salida.writeUTF(respuesta);

				conexion.close();

			}
		} catch (IOException e) {
		}
	}

}
