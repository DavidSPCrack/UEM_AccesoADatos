package resueltos;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static int factorial(int n) {
		int f = 1;
		if (n == 0)
			f = 1;
		for (int i = n; i > 0; i--) {
			f *= i;
		}
		return f;
	}

	public static void main(String[] args) {
		ServerSocket serverAddr = null;
		Socket sc = null;
		int num[];
		String res;
		try {
			serverAddr = new ServerSocket(2500);
		} catch (Exception e) {
			System.err.println("Error creando socket");
		}
		System.out.println("El Servidor ha arrancado correctamente ... ");
		while (true) {
			try {
				sc = serverAddr.accept(); // esperando conexión
				InputStream istream = sc.getInputStream();
				ObjectInput in = new ObjectInputStream(istream);
				num = (int[]) in.readObject();
				System.out.println("Los números recibidos son:  " + num[0] + " , " + num[1] + " , " + num[2]);
				res = factorial(num[0]) + " - " + factorial(num[1]) + " - " + factorial(num[2]);
				Thread.sleep(2000);
				DataOutputStream ostream = new DataOutputStream(sc.getOutputStream());
				ostream.writeUTF(res);
				ostream.flush();
				sc.close();
			} catch (Exception e) {
				System.err.println("excepcion " + e.toString());
				e.printStackTrace();
			} // try
		} // while
	} // main
} // servidor