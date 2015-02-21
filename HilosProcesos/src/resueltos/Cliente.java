package resueltos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Cliente {

	public static void main(String[] args) throws Exception {

		long numero;

		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
		Socket s = new Socket("localhost", 6677);

		// Pongo el nombre del cliente
		String nombre = "Cliente 1";

		// Introduzco un n�mero por consola
		System.out.print("N�mero a enviar: ");
		numero = Integer.parseInt(b.readLine());
		System.out.println(numero);

		// Env�o el nombre del cliente y el n�mero al Servidor
		DataOutputStream os = new DataOutputStream(s.getOutputStream());
		os.writeUTF(nombre);
		os.writeLong(numero);
		os.flush();

		// LECTURA DEL ARRAY ENVIADO POR EL SERVIDOR

		ArrayList<Integer> fPrimos = new ArrayList<Integer>();

		InputStream is = s.getInputStream();
		ObjectInput in = new ObjectInputStream(is);
		fPrimos = (ArrayList<Integer>) in.readObject();

		System.out.println("Factores Primos del n�mero " + numero + ":");
		System.out.print(fPrimos + "\n");

		s.close();
	}
}
