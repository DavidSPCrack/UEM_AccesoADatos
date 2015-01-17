package direccionesip;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class DireccionesIP {
	public static void main(String[] args) {
		byte[] direccionLocal = { 127, 0, 0, 1 }; // Direcci�n IP del Localhost
		InetAddress equipo;
		try {
			// M�todos est�ticos de InetAddress para obtener el objeto equipo
			equipo = InetAddress.getLocalHost(); // Creamos el objeto equipo de
													// la clase InetAddress
			System.out.println("Mi equipo es: " + equipo);
			System.out.println("Su direcci�n IP es: " + equipo.getHostAddress());
			System.out.println("Su nombre es: " + equipo.getHostName());
			System.out.println("Y su nombre can�nico: " + equipo.getCanonicalHostName());
			System.out.println();
			// Obtenemos el equipo a partir del nombre
			equipo = InetAddress.getByName("www.google.com");
			System.out.println("el equipo www.google.es es: " + equipo);
			System.out.println("Su direcci�n IP es: " + equipo.getHostAddress());
			System.out.println("Su nombre es: " + equipo.getHostName());
			System.out.println("Y su nombre can�nico: " + equipo.getCanonicalHostName());
			System.out.println();
			// Obtenemos el equipo a partir de su direcci�n IP
			equipo = InetAddress.getByAddress(direccionLocal);
			System.out.println("Mi equipo es: " + equipo);
			System.out.println("Su direcci�n IP es: " + equipo.getHostAddress());
			System.out.println();
			// Obtenemos todas las direcciones IP de un equipo
			InetAddress[] identidades;
			equipo = InetAddress.getLocalHost();
			identidades = InetAddress.getAllByName(equipo.getHostName());
			for (int i = 0; i < identidades.length; i++)
				System.out.println("Id" + i + ": " + identidades[i]);
			System.out.println();
		} catch (UnknownHostException e) {
			System.out.println("Error de conexi�n");
			System.out.println(e.toString());
		}
	}
}