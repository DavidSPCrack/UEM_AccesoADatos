package resueltos;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int num[] = new int[3];
		if (args.length != 1) {
			System.out.println("Uso: cliente <host>");
			System.exit(0);
		}
		try {
			String host = args[0];
			Socket sc = new Socket(host, 2500); // socket servidor
			OutputStream ostream = sc.getOutputStream();
			ObjectOutput s = new ObjectOutputStream(ostream);
			// prepara la petición
			System.out.println("Se va a calcular el Factorial de tres números introducidos por tecladdo");
			System.out.print("Introduce el primer numero: ");
			num[0] = Integer.parseInt(br.readLine());
			System.out.print("Introduce el segundo numero: ");
			num[1] = Integer.parseInt(br.readLine());
			System.out.print("Introduce el tercer numero: ");
			num[2] = Integer.parseInt(br.readLine());
			s.writeObject(num);
			s.flush();
			DataInputStream istream = new DataInputStream(sc.getInputStream());
			String res = istream.readUTF();
			sc.close();
			System.out.println("El factorial de " + num[0] + " - " + num[1] + " - " + num[2] + " es ");
			System.out.println(res);
		} catch (Exception e) {
			System.err.println("excepcion " + e.toString());
			e.printStackTrace();
		}
	}
}
