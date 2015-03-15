package cifrado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejemplo2 {
	public static void main(String[] args) {
		// Gestor de Seguridad de Java
		System.setSecurityManager(new SecurityManager());
		try {
			// Escritura en archivo
			BufferedWriter archivo1 = new BufferedWriter(new FileWriter("C://javaseg//politica1.txt"));
			archivo1.write("Línea 1 ... Escritura de una línea en el archivo ...");
			archivo1.newLine();
			archivo1.close();
			System.out.println("Final del proceso de escritura …");
			// Lectura del archivo
			BufferedReader archivo2 = new BufferedReader(new FileReader("C://javaseg//politica1.txt"));
			String linea = archivo2.readLine();
			System.out.println("Contenido del archivo: ");
			System.out.println("\t" + linea);
			archivo2.close();
			System.out.println("Final del proceso de lectura …");
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el archivo ");
		} catch (IOException io) {
			System.out.println("Error de E/S ");
		} catch (Exception e) {
			System.out.println("ERROR ---> " + e.toString());
		}
	}
}