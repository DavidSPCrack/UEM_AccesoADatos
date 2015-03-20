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

	public static final String CHOOSE_OPTION = "Seleccione una de las opciones: ";
	public static final String RETRY_SELECTION = "Vuelva a intentarlo, introduzca una de las opciones: ";

	public static final String CHOOSE_QUANTITY = "¿Cuantos quiere? (1-100): ";
	public static final String RETRY_QUANTITY = "Vuelva a intentarlo, introduzca un número: ";

	public class ItemPedido {
		private int tipo;
		private int id;
		private int numero;

		public ItemPedido(String[] datos) {
			this.tipo = getIntParam(datos, 0);
			this.id = getIntParam(datos, 1);
			this.numero = getIntParam(datos, 2);
		}

		public ItemPedido(int tipo, int id, int numero) {
			this.id = id;
			this.numero = numero;
		}

		public int getId() {
			return id;
		}

		public int getTipo() {
			return tipo;
		}

		public void setTipo(int tipo) {
			this.tipo = tipo;
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

		public double getPrecioIndividual() {
			double precio = 0;
			if (tipo == Menu.TIPO_PLATO) {
				precio = Plato.getPrecio(getId());
			} else if (tipo == Menu.TIPO_BEBIDA) {
				precio = Bebida.getPrecio(getId());
			} else if (tipo == Menu.TIPO_POSTRE) {
				precio = Postre.getPrecio(getId());
			}
			return precio;
		}

		public double getPrecioTotal() {
			double precioIndividual = getPrecioIndividual();
			return precioIndividual * numero;
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
		int tipo = 0;
		while (tipo != Menu.SALIR) {
			Menu.printMenu();
			tipo = readInt(CHOOSE_OPTION, RETRY_SELECTION, 1, 4);
			if (tipo == Menu.TIPO_PLATO) {
				Menu.printMenuPlatos();
				Plato[] platos = Plato.values();
				int item = readInt(CHOOSE_OPTION, RETRY_SELECTION, 1, platos.length);
				int cantidad = readInt(CHOOSE_QUANTITY, RETRY_QUANTITY, 1, 100);
				ItemPedido itemPedido = new ItemPedido(tipo, item, cantidad);
				addPlato(itemPedido);
			} else if (tipo == Menu.TIPO_BEBIDA) {
				Menu.printMenuBebidas();
				Bebida[] bebidas = Bebida.values();
				int item = readInt(CHOOSE_OPTION, RETRY_SELECTION, 1, bebidas.length);
				int cantidad = readInt(CHOOSE_QUANTITY, RETRY_QUANTITY, 1, 100);
				ItemPedido itemPedido = new ItemPedido(tipo, item, cantidad);
				addBebida(itemPedido);
			} else if (tipo == Menu.TIPO_POSTRE) {
				Menu.printMenuPostres();
				Postre[] postres = Postre.values();
				int item = readInt(CHOOSE_OPTION, RETRY_SELECTION, 1, postres.length);
				int cantidad = readInt(CHOOSE_QUANTITY, RETRY_QUANTITY, 1, 100);
				ItemPedido itemPedido = new ItemPedido(tipo, item, cantidad);
				addPostre(itemPedido);
			}
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
				String[] datosComplex = getStringParam(paramsSimple, 1).split(";");
				if (param.equals(PLATOS)) {
					for (int j = 0; j < datosComplex.length; j++) {
						String[] datos = datosComplex[i].split("%");
						ItemPedido item = new ItemPedido(datos);
						addPlato(item);
					}
				} else if (param.equals(BEBIDAS)) {
					for (int j = 0; j < datosComplex.length; j++) {
						String[] datos = datosComplex[i].split("%");
						ItemPedido item = new ItemPedido(datos);
						addBebida(item);
					}
				} else if (param.equals(POSTRES)) {
					for (int j = 0; j < datosComplex.length; j++) {
						String[] datos = datosComplex[i].split("%");
						ItemPedido item = new ItemPedido(datos);
						addPostre(item);
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
				sb.append(";");
			sb.append(c[i].getTipo());
			sb.append("%");
			sb.append(c[i].getId());
			sb.append("%");
			sb.append(c[i].getNumero());
		}
		return sb.toString();
	}

	public void sendDatos() {

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

	public ItemPedido[] getPlatos() {
		return platos.toArray(new ItemPedido[platos.size()]);
	}

	public ItemPedido[] getBebidas() {
		return bebidas.toArray(new ItemPedido[bebidas.size()]);
	}

	public ItemPedido[] getPostres() {
		return postres.toArray(new ItemPedido[postres.size()]);
	}

	public double getPrecioTotal() {
		double total = 0;
		ItemPedido[] platos = getPlatos();
		for (ItemPedido plato : platos) {
			total += plato.getPrecioTotal();
		}
		ItemPedido[] bebidas = getBebidas();
		for (ItemPedido bebida : bebidas) {
			total += bebida.getPrecioTotal();
		}
		ItemPedido[] postres = getPostres();
		for (ItemPedido postre : postres) {
			total += postre.getPrecioTotal();
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

	public static int readInt(String question, String rQuestion, int min, int max) {
		int resp = 0;
		boolean ok = true;
		System.out.println(question);
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
