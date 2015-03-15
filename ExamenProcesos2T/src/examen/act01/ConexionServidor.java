package examen.act01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConexionServidor implements Runnable {

	private Socket socket;
	private int numConexion;

	public ConexionServidor(Socket socket, int numConexion) {
		this.socket = socket;
		this.numConexion = numConexion;
		new Thread(this).start();
	}

	public void run() {
		DataOutputStream salida = null;
		DataInputStream entrada = null;

		try {
			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());

			int nComensales = entrada.readInt();
			int numMesa = entrada.readInt();
			PedidoComensal[] pedidos = new PedidoComensal[nComensales];
			for (int i = 0; i < nComensales; i++) {
				String param = entrada.readUTF();
				pedidos[i] = new PedidoComensal(param);
			}

			double total = 0;
			for (int i = 0; i < pedidos.length; i++) {
				total += pedidos[i].getPrecioTotal();
			}

			System.out.println("Conexión nº " + numConexion + " pedido para la mesa " + numMesa);

			String fecha = getFechaHora();

			salida.writeInt(numMesa);
			salida.writeInt(numConexion);
			salida.writeDouble(total);
			salida.writeUTF(fecha);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (entrada != null)
				try {
					entrada.close();
				} catch (IOException e) {
				}
			if (salida != null)
				try {
					salida.close();
				} catch (IOException e) {
				}
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
				}
		}
	}

	public static final String getFechaHora() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		return sdf.format(date);
	}

}
