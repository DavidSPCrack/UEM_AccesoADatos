package sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

/**
 *
 * @author David
 */
public class Servidor1 {

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
				System.out.println("Conexión nÃºmero" + num + " desde: " + conexion.getInetAddress().getHostName());
				entrada = new DataInputStream(conexion.getInputStream());
				salida = new DataOutputStream(conexion.getOutputStream());
				String mensaje = entrada.readUTF();
				System.out.println("Conexión n." + num + " mensaje: " + mensaje);
				Calendar calendario = Calendar.getInstance();
				int dia = calendario.get(Calendar.DATE);
				int mes = calendario.get(Calendar.MONTH);
				String smes = "";
				switch (mes) {
				case 0:
					smes = "enero";
					break;
				case 1:
					smes = "febrero";
					break;
				case 2:
					smes = "marzo";
					break;
				case 3:
					smes = "abril";
					break;
				case 4:
					smes = "mayo";
					break;
				case 5:
					smes = "junio";
					break;
				case 6:
					smes = "julio";
					break;
				case 7:
					smes = "agosto";
					break;
				case 8:
					smes = "septiembre";
					break;
				case 9:
					smes = "octubre";
					break;
				case 10:
					smes = "noviembre";
					break;
				case 11:
					smes = "diciembre";
					break;

				}

				int ano = calendario.get(Calendar.YEAR);
				int hora = calendario.get(Calendar.HOUR_OF_DAY);
				int minutos = calendario.get(Calendar.MINUTE);
				salida.writeUTF("Buenos días son las " + hora + ":" + minutos + " del dia " + dia + " del " + smes + " del " + ano);
				conexion.close();
			}
		} catch (IOException e) {
		}
	}

}
