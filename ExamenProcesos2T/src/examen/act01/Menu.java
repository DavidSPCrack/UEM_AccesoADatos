package examen.act01;

public class Menu {

	public static void printMenu() {
		System.out.println();
		System.out.println("¿Que quiere añadir?:");
		System.out.println(getListaMenu());
	}

	public static void printMenuPlatos() {
		System.out.println();
		System.out.println("¿Que plato desea?:");
		System.out.println(Plato.getListaMenu());
	}

	public static void printMenuBebidas() {
		System.out.println();
		System.out.println("¿Que bebida desea?:");
		System.out.println(Bebida.getListaMenu());
	}

	public static void printMenuPostres() {
		System.out.println();
		System.out.println("¿Que postre desea?:");
		System.out.println(Postre.getListaMenu());
	}

	public static void printCantidad() {
		System.out.println();
		System.out.print("¿Cuantas unidades desea?: ");
	}

	public static String getListaMenu() {
		String[] listas = new String[3];
		listas[0] = Plato.getLista(1);
		listas[1] = Bebida.getLista(2);
		listas[2] = Postre.getLista(3);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < listas.length; i++) {
			sb.append(listas[i]);
		}
		return sb.toString();
	}

	public enum Plato {
		PLATO1(1, "Plato 1", 3.40), PLATO2(2, "Plato 2", 5.70), PLATO3(3, "Plato 3", 4.50);

		private int id;
		private String nombre;
		private double precio;

		private Plato(int id, String nombre, double precio) {
			this.id = id;
			this.nombre = nombre;
			this.precio = precio;
		}

		public int getId() {
			return id;
		}

		public String getNombre() {
			return nombre;
		}

		public double getPrecio() {
			return precio;
		}

		public static Plato valueOf(int id) {
			Plato[] platos = values();
			for (int i = 0; i < platos.length; i++) {
				if (platos[i].getId() == id) {
					return platos[i];
				}
			}
			return null;
		}

		public static String getNombre(int id) {
			Plato plato = valueOf(id);
			return plato == null ? "" : plato.getNombre();
		}

		public static double getPrecio(int id) {
			Plato plato = valueOf(id);
			return plato == null ? 0 : plato.getPrecio();
		}

		public static String getLista(int pos) {
			Plato[] platos = values();
			StringBuilder sb = new StringBuilder();
			sb.append("   ");
			sb.append(pos);
			sb.append(") ");
			for (int i = 0; i < platos.length; i++) {
				if (i > 0)
					sb.append(", ");
				sb.append(platos[i].getNombre());
			}
			return sb.toString();
		}

		public static String getListaMenu() {
			Plato[] platos = values();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < platos.length; i++) {
				sb.append("   ");
				sb.append(i + 1);
				sb.append(") ");
				sb.append(platos[i].getNombre());
				sb.append("\r\n");
			}
			return sb.toString();
		}

	}

	public enum Bebida {

		REFRESCO(1, "Refresco", 2.10), VINO(2, "Vino", 2.50), AGUA(3, "Agua", 1.50);

		private int id;
		private String nombre;
		private double precio;

		private Bebida(int id, String nombre, double precio) {
			this.id = id;
			this.nombre = nombre;
			this.precio = precio;
		}

		public int getId() {
			return id;
		}

		public String getNombre() {
			return nombre;
		}

		public double getPrecio() {
			return precio;
		}

		public static Bebida valueOf(int id) {
			Bebida[] bebidas = values();
			for (int i = 0; i < bebidas.length; i++) {
				if (bebidas[i].getId() == id) {
					return bebidas[i];
				}
			}
			return null;
		}

		public static String getNombre(int id) {
			Bebida bebida = valueOf(id);
			return bebida == null ? "" : bebida.getNombre();
		}

		public static double getPrecio(int id) {
			Bebida bebida = valueOf(id);
			return bebida == null ? 0 : bebida.getPrecio();
		}

		public static String getLista(int pos) {
			Bebida[] bebidas = values();
			StringBuilder sb = new StringBuilder();
			sb.append("   ");
			sb.append(pos);
			sb.append(") ");
			for (int i = 0; i < bebidas.length; i++) {
				if (i > 0)
					sb.append(", ");
				sb.append(bebidas[i].getNombre());
			}
			return sb.toString();
		}

		public static String getListaMenu() {
			Bebida[] bebidas = values();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bebidas.length; i++) {
				sb.append("   ");
				sb.append(i + 1);
				sb.append(") ");
				sb.append(bebidas[i].getNombre());
				sb.append("\r\n");
			}
			return sb.toString();
		}
	}

	public enum Postre {

		TARTA(1, "Tarta", 2.50), HELADO(2, "Helado", 1.90), CAFE(3, "Café", 1.20), COCTEL_CASA(4, "Coctel de la casa", 5.70);

		private int id;
		private String nombre;
		private double precio;

		private Postre(int id, String nombre, double precio) {
			this.id = id;
			this.nombre = nombre;
			this.precio = precio;
		}

		public int getId() {
			return id;
		}

		public String getNombre() {
			return nombre;
		}

		public double getPrecio() {
			return precio;
		}

		public static Postre valueOf(int id) {
			Postre[] postres = values();
			for (int i = 0; i < postres.length; i++) {
				if (postres[i].getId() == id) {
					return postres[i];
				}
			}
			return null;
		}

		public static String getNombre(int id) {
			Postre postre = valueOf(id);
			return postre == null ? "" : postre.getNombre();
		}

		public static double getPrecio(int id) {
			Postre postre = valueOf(id);
			return postre == null ? 0 : postre.getPrecio();
		}

		public static String getLista(int pos) {
			Postre[] postres = values();
			StringBuilder sb = new StringBuilder();
			sb.append("   ");
			sb.append(pos);
			sb.append(") ");
			for (int i = 0; i < postres.length; i++) {
				if (i > 0)
					sb.append(", ");
				sb.append(postres[i].getNombre());
			}
			return sb.toString();
		}

		public static String getListaMenu() {
			Postre[] postres = values();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < postres.length; i++) {
				sb.append("   ");
				sb.append(i + 1);
				sb.append(") ");
				sb.append(postres[i].getNombre());
				sb.append("\r\n");
			}
			return sb.toString();
		}
	}
}
