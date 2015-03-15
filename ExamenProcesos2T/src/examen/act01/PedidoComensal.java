package examen.act01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import examen.act01.Menu.Bebida;
import examen.act01.Menu.Plato;
import examen.act01.Menu.Postre;

public class PedidoComensal {

	public class ItemPedido {
		private int id;
		private int numero;

		public ItemPedido(int id, int numero) {
			this.id = id;
			this.numero = numero;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getNumero() {
			return numero;
		}

		public void setNumero(int numero) {
			this.numero = numero;
		}

	}

	private static InputStreamReader iSR = new InputStreamReader(System.in);
	private static BufferedReader bR = new BufferedReader(iSR);

	public static final String FECHA = "fecha";
	public static final String COMENSAL = "comensal";
	public static final String PLATOS = "platos";
	public static final String BEBIDAS = "bebidas";
	public static final String POSTRES = "postres";

	public String fecha;
	public int nComensal;

	public ArrayList<ItemPedido> platos = new ArrayList<ItemPedido>();
	public ArrayList<ItemPedido> bebidas = new ArrayList<ItemPedido>();
	public ArrayList<ItemPedido> postres = new ArrayList<ItemPedido>();

	public PedidoComensal(String params) {
		parseParametros(params);
	}

	public PedidoComensal(int nComensal) {
		this.nComensal = nComensal;
		loadPedido();
	}

	private void loadPedido() {
		Menu.printMenu();
		int tipo = readInt("Vuelva a intentarlo, introduzca una de las opciones: ", 1, 3);
		if (tipo == 1) {
			Menu.printMenuPlatos();
			Plato[] platos = Plato.values();
			int item = readInt("Vuelva a intentarlo, introduzca una de las opciones: ", 1, platos.length);

		} else if (tipo == 2) {

		} else if (tipo == 3) {

		}
	}

	public void parseParametros(String params) {
		String[] paramsComplex = params.split("&");
		for (int i = 0; i < paramsComplex.length; i++) {
			String[] paramsSimple = paramsComplex[i].split("=");
			String param = getStringParam(paramsSimple, 0);
			if (param.equals(FECHA)) {
				fecha = getStringParam(paramsSimple, 1);
			} else if (param.equals(COMENSAL)) {
				nComensal = getIntParam(paramsSimple, 1);
			} else {
				String[] ids = getStringParam(paramsSimple, 1).split(",");
				if (param.equals(PLATOS)) {
					for (int j = 0; j < ids.length; j++) {
						addPlato(ids[i]);
					}
				} else if (param.equals(BEBIDAS)) {
					for (int j = 0; j < ids.length; j++) {
						addBebida(ids[i]);
					}
				} else if (param.equals(POSTRES)) {
					for (int j = 0; j < ids.length; j++) {
						addPostre(ids[i]);
					}
				}
			}
		}
	}

	public String getParametros() {
		StringBuilder sb = new StringBuilder();
		sb.append(FECHA);
		sb.append("=");
		sb.append(fecha);
		sb.append("&");
		sb.append(COMENSAL);
		sb.append("=");
		sb.append(nComensal);
		sb.append("&");
		sb.append(PLATOS);
		sb.append("=");
		sb.append(getComplexParm(platos.toArray(new ItemPedido[platos.size()])));
		sb.append("&");
		sb.append(BEBIDAS);
		sb.append("=");
		sb.append(getComplexParm(bebidas.toArray(new ItemPedido[bebidas.size()])));
		sb.append("&");
		sb.append(POSTRES);
		sb.append("=");
		sb.append(getComplexParm(postres.toArray(new ItemPedido[postres.size()])));
		return sb.toString();
	}

	public String getComplexParm(ItemPedido[] c) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < c.length; i++) {
			if (i > 0)
				sb.append(",");
			sb.append(c[i].getId());
			sb.append("%");
			sb.append(c[i].getNumero());
		}
		return sb.toString();
	}

	public void sendDatos() {

	}

	public void addPlato(String strId) {
		try {
			int id = Integer.parseInt(strId);
			//addPlato(id);
		} catch (Throwable t) {
		}
	}

	public void addPostre(String strId) {
		try {
			int id = Integer.parseInt(strId);
			//addPostre(id);
		} catch (Throwable t) {
		}
	}

	public void addBebida(String strId) {
		try {
			int id = Integer.parseInt(strId);
			//addBebida(id);
		} catch (Throwable t) {
		}
	}

	public void addPlato(ItemPedido item) {
		platos.add(item);
	}

	public void addPostre(ItemPedido item) {
		postres.add(item);
	}

	public void addBebida(ItemPedido item) {
		bebidas.add(item);
	}

	public Plato[] getPlatos() {
		ArrayList<Plato> lista = new ArrayList<Plato>();
		for (ItemPedido item : platos) {
			Plato plato = Plato.valueOf(item.getId());
			if (plato != null)
				lista.add(plato);
		}
		return lista.toArray(new Plato[lista.size()]);
	}

	public Bebida[] getBebidas() {
		ArrayList<Bebida> lista = new ArrayList<Bebida>();
		for (ItemPedido item : bebidas) {
			Bebida bebida = Bebida.valueOf(item.getId());
			if (bebida != null)
				lista.add(bebida);
		}
		return lista.toArray(new Bebida[lista.size()]);
	}

	public Postre[] getPostres() {
		ArrayList<Postre> lista = new ArrayList<Postre>();
		for (ItemPedido item : postres) {
			Postre postres = Postre.valueOf(item.getId());
			if (postres != null)
				lista.add(postres);
		}
		return lista.toArray(new Postre[lista.size()]);
	}

	public double getPrecioTotal() {
		double total = 0;
		Plato[] platos = getPlatos();
		for (Plato plato : platos) {
			total += plato.getPrecio();
		}
		Bebida[] bebidas = getBebidas();
		for (Bebida bebida : bebidas) {
			total += bebida.getPrecio();
		}
		Postre[] postres = getPostres();
		for (Postre postre : postres) {
			total += postre.getPrecio();
		}

		return total;
	}

	public static final String getFechaHora() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
		return sdf.format(date);
	}

	public String getStringParam(String[] parms, int pos) {
		return parms.length > pos ? parms[pos] : "";
	}

	public int getIntParam(String[] parms, int pos) {
		String value = getStringParam(parms, pos);
		try {
			return Integer.parseInt(value);
		} catch (Throwable t) {
			return 0;
		}
	}

	public static int readInt(String rQuestion, int min, int max) {
		int resp = 0;
		boolean ok = true;
		do {
			try {
				resp = readInt();
				if (resp >= min && resp <= max)
					ok = true;
				else {
					System.out.println(rQuestion);
					ok = false;
				}
			} catch (IOException e) {
				System.out.println(rQuestion);
				ok = false;
			} catch (NumberFormatException e) {
				System.out.println(rQuestion);
				ok = false;
			}
		} while (!ok);
		return resp;
	}

	public static int readInt() throws NumberFormatException, IOException {
		return Integer.parseInt(readLine());
	}

	public static String readLine() throws IOException {
		return bR.readLine();
	}

}
