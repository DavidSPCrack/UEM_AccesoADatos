package sockets_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author David
 */
public class Ejercicio1_Cliente_2 {

	public static void main(String[] args) {

		Socket cliente;
		DataInputStream entrada;
		DataOutputStream salida;
		String mensaje = "";
		String respuesta = "";

		Scanner teclado = new Scanner(System.in);

		try {
			cliente = new Socket(InetAddress.getLocalHost(), 5000);

			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());

			int contador = 0;
			int[] arrayInt = new int[3];

			while ((contador <= 2)) {
				try {
					System.out.println("Introduzca un valor entre 0-20:");
					arrayInt[contador] = teclado.nextInt();

					if ((arrayInt[contador] >= 0) && (arrayInt[contador] < 21)) {
						mensaje += arrayInt[contador] + ", ";
						contador++;
					} else {
						System.out.println("Número fuera de rango. Repita por favor.");
					}
				} catch (NumberFormatException e) {
					System.out.println("No es un número. Repita por favor.");
				}

			}

			for (int i = 0; i < arrayInt.length; i++) {
				salida.writeInt(arrayInt[i]);

			}
			int[] arrayRespC = new int[3];
			for (int i = 0; i < 3; i++) {
				arrayRespC[i] = entrada.readInt();
				respuesta += arrayRespC[i] + ", ";
			}

			System.out.println("Mi mensaje: " + mensaje);
			System.out.println("Respuesta del Servidor: " + respuesta);

			cliente.close();

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
