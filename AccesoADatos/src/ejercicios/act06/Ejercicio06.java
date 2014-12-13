package ejercicios.act06;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio06 {
	static Scanner teclado = new Scanner(System.in);

	/**
	 * 1 Crear un fichero de texto “DOUBLE.BIN” con números reales (double)
	 * introducidos por teclado.
	 * 
	 * @param ruta
	 */
	public void crearFicheroDouble(String ruta) {

		File archivo = new File(ruta);

		DataOutputStream dataOS = null;

		try {
			dataOS = new DataOutputStream(new FileOutputStream(archivo));

			System.out.println("¿Cuántos números va a introducir?");
			int numfin = teclado.nextInt();
			int contador = 1;

			while (contador <= numfin) {
				System.out.println("Teclee un número:");
				Double nro = teclado.nextDouble();

				dataOS.writeDouble(nro);
				contador++;
			}

		} catch (FileNotFoundException e) {
			System.out.println("El archivo no existe o está lleno.");
		} catch (IOException e) {
			System.out.println("Error de E/S del archivo.");
		} finally {
			if (dataOS != null) {
				try {
					dataOS.close();
				} catch (IOException e) {
					System.out.println("Error de E/S del archivo.");
				}
			}
		}

	}

	/**
	 * 2 Mostrar en pantalla el contenido del fichero “DOUBLE.BIN”
	 * 
	 * @param ruta
	 */
	public void leerFicheroDouble(String ruta) {
		DataInputStream dataIN = null;
		try {
			dataIN = new DataInputStream(new FileInputStream(ruta));

			Double valor;
			while ((valor = dataIN.readDouble()) != -1) {

				System.out.println(valor);
			}
			dataIN.close();
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no existe o está lleno.");
		} catch (IOException e) {

		} finally {
			if (dataIN != null) {
				try {
					dataIN.close();
				} catch (IOException e) {
					System.out.println("Error de E/S del archivo.");
				}
			}
		}
	}

	/**
	 * 3 Crear un fichero binario “ENTEROS.BIN” pasándole el contenido del
	 * fichero “ENTEROS.TXT” con números separados por puntos y coma.
	 */
	public void creaEnterosBin() {

		File archivoE = new File("./resources/ENTEROS.txt");
		File archivoB = new File("./resources/ENTEROS.bin");

		DataOutputStream dataOS = null;

		try {
			dataOS = new DataOutputStream(new FileOutputStream(archivoE));

			for (int i = 0; i <= 100; i++) {
				dataOS.writeInt(i);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dataOS != null) {
				try {
					dataOS.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		DataInputStream dataIN = null;
		DataOutputStream copia = null;

		try {
			dataIN = new DataInputStream(new FileInputStream(archivoE));
			copia = new DataOutputStream(new FileOutputStream(archivoB));

			int valor;

			while ((valor = dataIN.readInt()) != -1) {
				copia.write(valor);
				copia.writeChars(";");
				System.out.println(valor);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} finally {
			if (dataIN != null) {
				try {
					dataIN.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (copia != null) {
				try {
					copia.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 4 Mostrar en pantalla el contenido del fichero binario que contiene
	 * enteros cuyo nombre se introduce por teclado.
	 * 
	 * @param ruta
	 */
	public void guardarFichero(String ruta) {

		File file = new File(ruta);

		FileOutputStream fileout = null;
		int i;

		try {
			fileout = new FileOutputStream(file);

			// for(int i=0; i<100; i++) {
			System.out.println("¿Cuántos números va a escribir?");
			int numfin = teclado.nextInt();
			int contador = 1;
			do {
				System.out.println("Escribe un número:");
				i = teclado.nextInt();
				fileout.write(i);
				contador++;
			} while (contador <= numfin);

			// }

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileout != null) {
				try {
					fileout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void leerfichero(String ruta) {

		int i;
		File file = new File(ruta);

		FileInputStream filein = null;

		try {
			filein = new FileInputStream(file);

			while ((i = filein.read()) != -1) {
				if (i != 255) {
					System.out.println(i);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (filein != null) {
				try {
					filein.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
