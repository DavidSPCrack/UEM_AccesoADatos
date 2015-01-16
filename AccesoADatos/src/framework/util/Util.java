package framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import framework.ErrorGeneral;
import framework.util.interfaces.IOrdenacion;
import framework.xml.DocumentoXML;

/**
 * 
 * @author David
 */
public final class Util {

	private static char char1E = (0x1E);

	private static String[] entityPre = { "\"", "<", ">", "Ã¡", "Ã©", "Ã­", "Ã³", "Ãº", "Ã±", "Ã?", "Ã‰", "Ã?", "Ã“", "Ãš", "Ã‘", "Ã ", "Ã¨", "Ã¬", "Ã²", "Ã¹",
			"Ã€", "Ãˆ", "ÃŒ", "Ã’", "Ã™", "Ã¼", "Ãœ", "'", "Ã§", "Ã‡", "Âº", "Âª", Character.toString(char1E) };

	private static String[] entityPreUni = { "34", "60", "62", "225", "233", "237", "243", "250", "241", "193", "201", "205", "211", "218", "209", "224", "232",
			"236", "242", "249", "192", "200", "204", "210", "217", "252", "220", "39", "231", "199", "186", "170", "32" };

	private static final String DEFAULT_LINE_BREAK_ESCAPE = "~CSVLB~";
	private static final String DEFAULT_CARRIAGE_RETURN_ESCAPE = "~CSVCR~";

	public Util() {
	}

	public static ImageIcon getImageIcon(String fileName, String jarNameImages, String pathImagenes) {
		String path = pathImagenes;
		String jarName = jarNameImages;
		int c, i = 0;
		byte buffer[];
		JarFile jfile;
		JarEntry jentry;
		InputStream in = null;
		ImageIcon m_image;
		try {
			jfile = new JarFile(jarName);
		} catch (Exception e) {
			return getImageIcon(path + "/" + fileName);
		}
		try {
			jentry = jfile.getJarEntry(fileName);
			in = jfile.getInputStream(jentry);
			buffer = new byte[(int) jentry.getSize()];
			// write int-value 'c' (casted to byte) with read() and while-loop
			// in byte-Array
			while ((c = in.read()) != -1) {
				buffer[i] = (byte) c;
				i++;
			}
			m_image = new ImageIcon(buffer);
			jfile.close();
			return m_image;
		} catch (Exception ex) {
			return getImageIcon(path + "/" + fileName);
		}
	}

	public static ImageIcon getResourceImage(String image) {
		String resource = "/images/" + image;
		Class<?> clase = resource.getClass();
		URL url = clase.getResource(resource);
		if (url == null) {
			System.out.println("No se encuentra imagen " + image);
			return getImageIcon(image, null, null);
		}
		return new ImageIcon(url);
	}

	private static ImageIcon getImageIcon(String fileName) {
		ImageIcon imagen = new ImageIcon(fileName);
		return imagen;
	}

	public static String obtenerLocalHost() {
		try {
			InetAddress inet = InetAddress.getLocalHost();
			return inet.getHostName();
		} catch (Exception e) {
			return "";
		}
	}

	public static DocumentoXML getXML(String nameXML) throws ErrorGeneral {
		URL url = null;
		if (url == null) {
			String aux = "/com/sis/xml/configuracion/" + nameXML;
			url = Util.class.getResource(aux);
			if (url != null) {
				System.out.println(url.getPath());
			}
		}
		if (url != null) {
			try {
				return new DocumentoXML(url);
			} catch (Exception e) {
				throw new ErrorGeneral(e);
			}

		} else {
			return null;
		}
	}

	public static String obtenerIPLocal() {
		try {
			InetAddress inet = InetAddress.getLocalHost();
			return inet.getHostAddress();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Retorna el mÃ¡ximo de memoria que puede disponer la mÃ¡quina virtual de
	 * java para ejecutar procesos.
	 */
	public static double getMaximaMemoriaJVM() {
		Runtime runtime = Runtime.getRuntime();
		double maxMemory = runtime.maxMemory() / (double) (1024 * 1024);
		return truncar2Decimal(maxMemory);
	}

	/**
	 * Retorna el total de memoria que puede disponer la mÃ¡quina virtual de
	 * java para ejecutar procesos.
	 */
	public static double getTotalMemoriaJVM() {
		Runtime runtime = Runtime.getRuntime();
		double totalMemory = runtime.totalMemory() / (double) (1024 * 1024);
		return truncar2Decimal(totalMemory);
	}

	/**
	 * Retorna el total de memoria libre
	 */
	public static double getMemoriaLibre() {
		Runtime runtime = Runtime.getRuntime();
		double freeMemory = runtime.freeMemory() / (double) (1024 * 1024);
		return truncar2Decimal(freeMemory);
	}

	private static double truncar2Decimal(double numero) {
		double aux = numero * 100;
		long auxLong = (long) aux;
		double auxDouble = auxLong * 0.01;
		return auxDouble;
	}

	public static double truncarEuros(double numero) {
		return truncar2Decimal(numero);
	}

	public static double redondearEuros(double numero) {
		double aux = numero * 100;
		long auxLong = Math.round(aux);
		numero = auxLong / (double) 100;
		return numero;
	}

	public static double redondear4Decimales(double numero) {
		double aux = (numero * 10000);
		long auxLong = Math.round(aux);
		numero = auxLong / 10000.0;
		return numero;
	}

	public static double redondear3Decimales(double numero) {
		double aux = (numero * 1000);
		long auxLong = Math.round(aux);
		numero = auxLong / 1000.0;
		return numero;
	}

	public static long redondearCentimos(double importe) {
		double aux = importe / 100;
		aux = redondearEuros(aux);
		aux = aux * 100;
		return (long) aux;
	}

	public static double redondearDecimales(double importe, int numDecs) {
		return Transform.toDouble(Util.formatImportes(importe, numDecs));
	}

	public static String formatImportes(String value) {
		if (value.trim().equals(""))
			return "0,00";
		try {
			value = Transform.toString(Transform.toDouble(value));
			DecimalFormat mf = new DecimalFormat("#,##0.00");
			return mf.format(Double.parseDouble(value));
		} catch (NumberFormatException nfe) {
			return "0,00";
		}
	}

	public static String replaceEntity(String cadena) {
		String[] entityPre = getEntity();
		String[] entityPreUni = getEntityUnicode();

		// Lo generamos una primera vez para cambiar la char &
		StringBuffer sb = new StringBuffer();
		int lenstr = cadena.length();
		int lenold = 1;
		int ultimo = lenstr - lenold;
		for (int i = 0; i < lenstr; i++) {
			if (i <= ultimo && cadena.substring(i, i + lenold).compareTo("&") == 0) {
				sb.append("&#38;");
				i = i + lenold - 1;
			} else {
				sb.append(cadena.charAt(i));
			}
		}
		cadena = sb.toString();

		for (int x = 0; x < entityPre.length; x++) {
			sb = new StringBuffer();
			lenstr = cadena.length();
			lenold = 1;
			ultimo = lenstr - lenold;
			for (int i = 0; i < lenstr; i++) {
				if (i <= ultimo && cadena.substring(i, i + lenold).equals(entityPre[x])) {
					sb.append("&#" + entityPreUni[x] + ";");
					i = i + lenold - 1;
				} else {
					sb.append(cadena.charAt(i));
				}
			}
			cadena = sb.toString();
		}

		return cadena;
	}

	public static String eliminarAcentos(String cadena) {
		char[] caracteres = cadena.toCharArray();
		for (int i = 0; i < caracteres.length; i++) {
			switch (caracteres[i]) {
			case 'Á':
				caracteres[i] = 'A';
				break;
			case 'É':
				caracteres[i] = 'E';
				break;
			case 'Í':
				caracteres[i] = 'I';
				break;
			case 'Ó':
				caracteres[i] = 'O';
				break;
			case 'Ú':
				caracteres[i] = 'U';
				break;
			case 'á':
				caracteres[i] = 'a';
				break;
			case 'é':
				caracteres[i] = 'e';
				break;
			case 'í':
				caracteres[i] = 'i';
				break;
			case 'ó':
				caracteres[i] = 'o';
				break;
			case 'ú':
				caracteres[i] = 'u';
				break;
			case 'À':
				caracteres[i] = 'A';
				break;
			case 'È':
				caracteres[i] = 'E';
				break;
			case 'Ì':
				caracteres[i] = 'I';
				break;
			case 'Ò':
				caracteres[i] = 'O';
				break;
			case 'Ù':
				caracteres[i] = 'U';
				break;
			case 'à':
				caracteres[i] = 'a';
				break;
			case 'è':
				caracteres[i] = 'e';
				break;
			case 'ì':
				caracteres[i] = 'i';
				break;
			case 'ò':
				caracteres[i] = 'o';
				break;
			case 'ù':
				caracteres[i] = 'u';
				break;
			}
		}
		return new String(caracteres);
	}

	public static String QUITA_TILDES(String cadena) {
		char[] caracteres = cadena.toCharArray();
		for (int i = 0; i < caracteres.length; i++) {
			switch (caracteres[i]) {
			case 'Á':
				caracteres[i] = 'A';
				break;
			case 'É':
				caracteres[i] = 'E';
				break;
			case 'Í':
				caracteres[i] = 'I';
				break;
			case 'Ó':
				caracteres[i] = 'O';
				break;
			case 'Ú':
				caracteres[i] = 'U';
				break;
			case 'á':
				caracteres[i] = 'a';
				break;
			case 'é':
				caracteres[i] = 'e';
				break;
			case 'í':
				caracteres[i] = 'i';
				break;
			case 'ó':
				caracteres[i] = 'o';
				break;
			case 'ú':
				caracteres[i] = 'u';
				break;
			case 'À':
				caracteres[i] = 'A';
				break;
			case 'È':
				caracteres[i] = 'E';
				break;
			case 'Ì':
				caracteres[i] = 'I';
				break;
			case 'Ò':
				caracteres[i] = 'O';
				break;
			case 'Ù':
				caracteres[i] = 'U';
				break;
			case 'à':
				caracteres[i] = 'a';
				break;
			case 'è':
				caracteres[i] = 'e';
				break;
			case 'ì':
				caracteres[i] = 'i';
				break;
			case 'ò':
				caracteres[i] = 'o';
				break;
			case 'ù':
				caracteres[i] = 'u';
				break;
			case 'Ä':
				caracteres[i] = 'A';
				break;
			case 'Ë':
				caracteres[i] = 'E';
				break;
			case 'Ï':
				caracteres[i] = 'I';
				break;
			case 'Ö':
				caracteres[i] = 'O';
				break;
			case 'Ü':
				caracteres[i] = 'U';
				break;
			case 'ä':
				caracteres[i] = 'a';
				break;
			case 'ë':
				caracteres[i] = 'e';
				break;
			case 'ï':
				caracteres[i] = 'i';
				break;
			case 'ö':
				caracteres[i] = 'o';
				break;
			case 'ü':
				caracteres[i] = 'u';
				break;
			case 'Â':
				caracteres[i] = 'A';
				break;
			case 'Ê':
				caracteres[i] = 'E';
				break;
			case 'Î':
				caracteres[i] = 'I';
				break;
			case 'Ô':
				caracteres[i] = 'O';
				break;
			case 'Û':
				caracteres[i] = 'U';
				break;
			case 'â':
				caracteres[i] = 'a';
				break;
			case 'ê':
				caracteres[i] = 'e';
				break;
			case 'î':
				caracteres[i] = 'i';
				break;
			case 'ô':
				caracteres[i] = 'o';
				break;
			case 'û':
				caracteres[i] = 'u';
				break;
			case 'Å':
				caracteres[i] = 'A';
				break;
			case 'å':
				caracteres[i] = 'a';
				break;
			case 'Ã':
				caracteres[i] = 'A';
				break;
			case 'Õ':
				caracteres[i] = 'O';
				break;
			case 'ã':
				caracteres[i] = 'a';
				break;
			case 'õ':
				caracteres[i] = 'o';
				break;
			}

		}
		return new String(caracteres);
	}

	public static String eliminarCaracter(String cadena, char car) {
		StringBuilder caracteres;
		if (cadena.indexOf(new Character(car).toString()) >= 0) {
			caracteres = new StringBuilder();
			char[] todos = cadena.toCharArray();
			for (int i = 0; i < todos.length; i++)
				if (todos[i] != car)
					caracteres.append(todos[i]);
			return caracteres.toString();
		} else
			return cadena;
	}

	public static String[] getEntity() {
		return entityPre;
	}

	public static String[] getEntityUnicode() {
		return entityPreUni;
	}

	public static String replaceNullTrim(String _value) {
		return _value == null ? "" : _value.trim();
	}

	public static IOrdenacion[] ordenar(IOrdenacion[] objs, int tipoOrden) {
		if (objs.length == 0)
			return objs;
		quickSort(objs, 0, objs.length - 1, tipoOrden);
		return objs;
	}

	public static void invertir(Object[] objs) {
		if (objs.length == 0)
			return;
		Object aux;
		for (int i = 0, x = objs.length - 1; i <= x; i++, x--) {
			aux = objs[i];
			objs[i] = objs[x];
			objs[x] = aux;
		}
	}

	public static void invertir(char[] cars) {
		if (cars.length == 0)
			return;
		char aux;
		for (int i = 0, x = cars.length - 1; i <= x; i++, x--) {
			aux = cars[i];
			cars[i] = cars[x];
			cars[x] = aux;
		}
	}

	private static void quickSort(IOrdenacion[] objs, int izquierda, int derecha, int tipoOrden) {
		int i = izquierda;
		int j = derecha;
		IOrdenacion mitad = objs[(izquierda + derecha) / 2];
		do {
			while (objs[i].getKeyOrder(tipoOrden).compareTo(mitad.getKeyOrder(tipoOrden)) < 0) {
				i++;
			}
			while (objs[j].getKeyOrder(tipoOrden).compareTo(mitad.getKeyOrder(tipoOrden)) > 0) {
				j--;
			}
			if (i <= j) {
				IOrdenacion aux = objs[i];
				objs[i] = objs[j];
				objs[j] = aux;
				i++;
				j--;
			}
		} while (i <= j);
		if (j > izquierda)
			quickSort(objs, izquierda, j, tipoOrden);

		if (i < derecha)
			quickSort(objs, i, derecha, tipoOrden);
	}

	/**
	 * Devuelve un String con la representaciÃ³n de un entero con zeros a la
	 * izquieda.
	 * 
	 */
	public static String getZeroesNumber(int length, int numero) {
		return ajustarDerecha(length, Integer.toString(numero).toCharArray(), '0');
	}

	public static String getZeroesNumber(int length, long numero) {
		String numeroString = Long.toString(numero);
		char[] numeroChars = numeroString.toCharArray();
		return ajustarDerecha(length, numeroChars, '0');
	}

	public static String ajustarDerecha(int length, String valor, char car) {
		if (valor == null)
			valor = "";
		return ajustarDerecha(length, valor.toCharArray(), car);
	}

	public static String ajustarDerecha(int length, char[] valor, char car) {
		if (length == 0)
			return "";

		char[] caracteres = new char[length];
		for (int i = 0; i < caracteres.length; i++) {
			caracteres[i] = car;
		}
		/*
		 * int j = length >= valor.length ? length - valor.length : 0; int fin =
		 * valor.length; if (fin > length) fin = length; for (int i = 0; i <
		 * fin; i++, j++) { caracteres[j] = valor[i]; }
		 */
		for (int i = valor.length - 1, j = length - 1; i >= 0; i--, j--) {
			caracteres[j] = valor[i];
			if (j == 0)
				break;
		}
		return new String(caracteres);
	}

	public static String ajustarIzquierda(int length, String valor, char car) {
		if (valor == null)
			valor = "";
		return ajustarIzquierda(length, valor.toCharArray(), car);
	}

	public static String ajustarIzquierda(int length, char[] valor, char car) {
		if (length == 0)
			return "";

		char[] caracteres = new char[length];
		for (int i = 0; i < caracteres.length; i++) {
			caracteres[i] = car;
		}
		int fin = valor.length;
		if (fin > length)
			fin = length;
		for (int i = 0; i < fin; i++) {
			caracteres[i] = valor[i];
		}
		return new String(caracteres);
	}

	public static String cambiarExtension(String fileName, String newExtension) {
		String ext = getExtensionFileName(fileName);
		if (ext == null) {
			return fileName + "." + newExtension;
		}
		String fileName2 = fileName.substring(0, fileName.length() - ext.length() - 1) + "." + newExtension;
		return fileName2;
	}

	public static String getExtensionFileName(String filename) {
		boolean punto = false;
		char[] caracteres = filename.toCharArray();
		String extension;
		StringBuffer sb = new StringBuffer();
		for (int i = caracteres.length - 1; i > -1; i--) {
			if (caracteres[i] == '.') {
				punto = true;
				i = -1;
			} else {
				sb.append(caracteres[i]);
			}
		}
		if (!punto) {
			return null;
		}
		caracteres = sb.toString().toCharArray();
		sb = new StringBuffer();
		for (int i = caracteres.length - 1; i > -1; i--) {
			sb.append(caracteres[i]);
		}
		extension = sb.toString();
		return extension;
	}

	public static String[] separaPalabras(String strCadena, String delim) {
		StringTokenizer st;
		if (delim == null) {
			st = new StringTokenizer(strCadena);
		} else {
			st = new StringTokenizer(strCadena, delim);
		}
		int numPalabras = st.countTokens();
		String[] palabras = new String[numPalabras];
		if (numPalabras > 0) {
			for (int i = 0; i < numPalabras; i++) {
				palabras[i] = st.nextToken();
			}
		}
		return palabras;
	}

	/*
	 * Este mÃ©todo divide la cadena de texto en lineas de la longitud indicada
	 * como mÃ¡ximo, no divide las palabras por lo que la longitud de la linea
	 * debe ser superior al numero de caracteres de cada palabra. int longLinea
	 * > 15 (estimaciÃ³n)
	 */
	public static String[] dividirEnLineas(String cadena, int longLinea) {
		String[] palabras = separaPalabras(cadena, " ");

		ArrayList<String> lista = new ArrayList<String>();
		String aux = "";
		StringBuffer auxCadena = new StringBuffer();
		for (int i = 0; i < palabras.length; i++) {
			aux = palabras[i] + " ";
			if (auxCadena.length() + aux.length() <= longLinea) {
				auxCadena.append(aux);
			} else {
				lista.add(auxCadena.toString());
				auxCadena = new StringBuffer();
				auxCadena.append(aux);
			}
		}
		if (auxCadena.length() > 0)
			lista.add(auxCadena.toString());

		return lista.toArray(new String[lista.size()]);
	}

	public static String formatImportes(double value) {
		try {
			DecimalFormat mf = new DecimalFormat("#,##0.00");
			return mf.format(value);
		} catch (NumberFormatException nfe) {
			return "0,00";
		}
	}

	public static String formatImportes(double value, String formato) {
		DecimalFormat mf = new DecimalFormat(formato);
		try {
			return mf.format(value);
		} catch (NumberFormatException nfe) {
			return mf.format(0);
		}
	}

	public static String formatImportes(double value, int numDecs) {
		try {
			if (numDecs <= 0)
				return Integer.toString((int) value);
			String decs = "";
			for (int i = 0; i < numDecs; ++i)
				decs += "0";
			DecimalFormat mf = new DecimalFormat("#,##0." + decs);
			return mf.format(value);
		} catch (NumberFormatException nfe) {
			return "0,00";
		}
	}

	public static String formatCero(String numero) {
		if (numero.endsWith(".0")) {
			return numero.substring(0, numero.length() - 2);
		}
		return numero;
	}

	public static String formatEuroCero(double numero) {
		NumeroDecimal nDecimal = new NumeroDecimal(numero);
		String formato = "#,##0.00";
		if (Transform.toLong(nDecimal.getPartdec()) == 0) {
			formato = "#,##0";
		}
		return formatImportes(numero, formato);
	}

	public static String formatEntero(int numero) {
		try {
			DecimalFormat mf = new DecimalFormat("#,##0");
			return mf.format(Double.parseDouble(Integer.toString(numero)));
		} catch (NumberFormatException nfe) {
			return "0";
		}
	}

	public static String formatTasa(double value) {
		try {
			DecimalFormat mf = new DecimalFormat("#,##0.000000");
			return mf.format(value);
		} catch (NumberFormatException nfe) {
			return "0,00";
		}
	}

	public static String formatFour(double value) {
		try {
			DecimalFormat mf = new DecimalFormat("#,##0.0000");
			return mf.format(value);
		} catch (NumberFormatException nfe) {
			return "0,0000";
		}
	}

	public static String formatThree(double value) {
		try {
			DecimalFormat mf = new DecimalFormat("#,##0.000");
			return mf.format(value);
		} catch (NumberFormatException nfe) {
			return "0,000";
		}
	}

	public static String formatStringTasa(String tasa) {
		try {
			return formatTasa(Double.parseDouble(tasa));
		} catch (NumberFormatException nfe) {
			return "0,00";
		}
	}

	public static Object[] addArrays(Object[] lista1, Object[] lista2, Class<?> type) {
		int size = lista1.length + lista2.length;
		Object[] destino = (Object[]) java.lang.reflect.Array.newInstance(type, size);
		System.arraycopy(lista1, 0, destino, 0, lista1.length);
		System.arraycopy(lista2, 0, destino, lista1.length, lista2.length);
		return destino;
	}

	public static Object[] addItemArray(Object[] lista1, Object nuevoItem) {
		int size = lista1.length + 1;
		Object[] destino = (Object[]) java.lang.reflect.Array.newInstance(nuevoItem.getClass(), size);
		System.arraycopy(lista1, 0, destino, 0, lista1.length);
		destino[lista1.length] = nuevoItem;
		return destino;
	}

	public static long[] addArrays(long[] lista1, long[] lista2) {
		int size = lista1.length + lista2.length;
		long[] destino = new long[size];
		System.arraycopy(lista1, 0, destino, 0, lista1.length);
		System.arraycopy(lista2, 0, destino, lista1.length, lista2.length);
		return destino;
	}

	public static String com2(String text) {
		return '"' + text + '"';
	}

	public Object newInstace(String nameClass, Class<?>[] tiposArgumentos, Object[] argumentos) throws ErrorGeneral {
		return Reflexion.newInstace(nameClass, tiposArgumentos, argumentos);
	}

	public static String minusculasCapital(String valor) {
		char[] cars = valor.toLowerCase().toCharArray();
		boolean change = true;
		for (int i = 0; i < cars.length; i++) {
			if (change) {
				cars[i] = Character.toUpperCase(cars[i]);
				change = false;
			}
			if (cars[i] == ' ')
				change = true;
			if (cars[i] == '.')
				change = true;
			if (cars[i] == '-')
				change = true;
			if (cars[i] == '(')
				change = true;
		}
		return new String(cars);
	}

	public static String crTobr(String origen) {
		return origen.replaceAll("\n", "<BR>");
	}

	public static String txt2StringDouble(String numero) {
		char[] cars = numero.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] != '.') {
				if (cars[i] == ',') {
					sb.append('.');
				} else {
					sb.append(cars[i]);
				}
			}
		}
		return sb.toString();
	}

	public static String formatNIF(String numeDocu) {
		if (numeDocu == null || numeDocu.length() == 0)
			return "";
		if (numeDocu.length() >= 9)
			return numeDocu;
		return Util.ajustarDerecha(9, numeDocu, '0');
	}

	public static boolean isDigitosNumericos(String cadena) {
		char[] cars = cadena.toCharArray();
		for (int i = 0; i < cars.length; i++) {
			if (!isDigitoNumerico(cars[i]))
				return false;
		}
		return true;
	}

	public static int[] getDigitos(long numero) {
		String cadena = Long.toString(numero);
		char[] cars = cadena.toCharArray();
		int[] numeros = new int[cars.length];
		for (int i = 0; i < cars.length; i++) {
			try {
				numeros[i] = Integer.parseInt("" + cars[i]);
			} catch (Throwable t) {
				numeros[i] = 0;
			}
		}
		return numeros;
	}

	public static boolean isNumericDecimal(String number) {
		boolean isValid = false;

		String expression = "^[-+]?\\d+(\\,\\d+)?$";
		CharSequence inputStr = number;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}

		return isValid;
	}

	public static boolean isNumeric(String number) {
		boolean isValid = false;

		String expression = "\\d+$";
		CharSequence inputStr = number;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches()) {
			isValid = true;
		}

		return isValid;
	}

	// Devuelve un String que contiene todos los caracteres numÃ©ricos del
	// String
	// original
	public static String getCaracteresNumericos(String cadena) {
		char[] cars = cadena.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cars.length; i++) {
			if (isDigitoNumerico(cars[i]))
				sb.append(cars[i]);
		}
		return sb.toString();
	}

	public static boolean isDigitoNumerico(char car) {
		switch (car) {
		case '0':
			return true;
		case '1':
			return true;
		case '2':
			return true;
		case '3':
			return true;
		case '4':
			return true;
		case '5':
			return true;
		case '6':
			return true;
		case '7':
			return true;
		case '8':
			return true;
		case '9':
			return true;
		}
		return false;
	}

	public static String[] getVariables(String cadena) {
		String[] lista = cadena.split(" ");
		ArrayList<String> saco = new ArrayList<String>();
		for (int i = 0; i < lista.length; i++) {
			if (lista[i].startsWith("&")) {
				saco.add(lista[i]);
			}
		}
		return saco.toArray(new String[saco.size()]);
	}

	public static String[] splitToEnteroDecimal(double numero, int precision) {
		StringBuilder formato = new StringBuilder();
		formato.append("0.0");
		for (int i = 1; i < precision; i++) {
			formato.append("0");
		}
		DecimalFormat mf = new DecimalFormat(formato.toString());
		String valor = mf.format(numero);
		char[] cars = valor.toCharArray();
		StringBuilder entero = new StringBuilder();
		StringBuilder decimal = new StringBuilder();
		StringBuilder sb = entero;
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] == ',') {
				sb = decimal;
			} else {
				sb.append(cars[i]);
			}
		}
		String[] items = new String[2];
		items[0] = entero.length() == 0 ? "0" : entero.toString();
		items[1] = decimal.length() == 0 ? "0" : decimal.toString();
		return items;
	}

	public static boolean isCadenaVacia(String cadena) {
		return isCadenaVacia(cadena, false);
	}

	public static boolean isCadenaVacia(String cadena, boolean contarCerosComoEspacios) {
		if (cadena == null)
			return true;
		char[] cars = cadena.toCharArray();
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] == ' ') {
			} else if (cars[i] == '0') {
				if (!contarCerosComoEspacios) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	public static String eliminarSaltosLinea(String cadena) {
		if (cadena.indexOf(FicheroUtil.saltoLinea) >= 0) {
			return cadena.replaceAll(FicheroUtil.saltoLinea, " ");
		} else
			return cadena;
	}

	public static String eliminarSaltosLinea2(String cadena) {
		if (cadena.indexOf(FicheroUtil.saltoLinea2) >= 0) {
			return cadena.replaceAll(FicheroUtil.saltoLinea2, " ");
		} else
			return cadena;
	}

	public static String replace(String origen, char caracterAReemplazar, String cadenaNueva) {
		char[] cars = { caracterAReemplazar };
		String[] cadenas = { cadenaNueva };
		return replace(origen, cars, cadenas);
	}

	public static String replace(String origen, char caracterAReemplazar[], String cadenaNueva[]) {
		StringBuilder sb = new StringBuilder();
		char[] cars = origen.toCharArray();
		for (int i = 0; i < cars.length; i++) {
			boolean doAppend = true;
			for (int j = 0; j < caracterAReemplazar.length; j++) {
				if (cars[i] == caracterAReemplazar[j]) {
					if (cadenaNueva[j] != null && cadenaNueva[j].length() > 0)
						sb.append(cadenaNueva[j]);
					doAppend = false;
					j = caracterAReemplazar.length;
				}
			}
			if (doAppend)
				sb.append(cars[i]);
		}
		return sb.toString();
	}

	public static boolean contieneRuta(String nameFile) {
		int puntos = 0;
		char[] cars = nameFile.toCharArray();
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] == '/') {
				return true;
			} else if (cars[i] == '.') {
				puntos++;
			}
		}
		return puntos > 1;
	}

	public static InputStream getInputStreamUrl(String docModel) {
		URL url = getUrl(docModel);
		if (url != null) {
			try {
				return url.openStream();
			} catch (IOException e) {

			}
		}
		return null;
	}

	public static URL getUrl(String docModel) {
		URL url = null;
		char[] cars = docModel.toCharArray();
		int puntos = 0, barras = 0;
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] == '.') {
				puntos++;
			} else if (cars[i] == '/') {
				barras++;
			}
		}
		boolean plantillaEnPaquete = false;
		if (puntos > 1 || barras > 0) {
			plantillaEnPaquete = true;
		}
		if (plantillaEnPaquete) {
			String extension = Util.getExtensionFileName(docModel);
			String fileName = docModel.substring(0, docModel.length() - extension.length() - 1);
			docModel = "/" + Util.replace(fileName, '.', "/") + "." + extension;
			url = Util.class.getResource(docModel);
		}
		return url;
	}

	public static void ordenar(String[] lista) {
		Arrays.sort(lista);
	}

	public static void ordenarDescendente(String[] lista) {
		ordenar(lista);
		String[] aux = (String[]) lista.clone();
		for (int i = 0; i < lista.length; i++) {
			lista[i] = aux[lista.length - 1 - i];
		}
	}

	public static void ordenarDescendente(int[] numeros) {
		Util util = new Util();
		util.ordenarEnterosDescendente(numeros);
	}

	public static void ordenarAsscendente(int[] numeros) {
		Util util = new Util();
		util.ordenarEnterosAscendente(numeros);
	}

	private void ordenarEnterosDescendente(int[] numeros) {
		OrdenIntDesc[] orden = new OrdenIntDesc[numeros.length];
		for (int i = 0; i < numeros.length; i++) {
			orden[i] = new OrdenIntDesc(numeros[i]);
		}
		Util.ordenar(orden, 0);
		for (int i = 0; i < orden.length; i++) {
			numeros[i] = orden[i].ordenAux;
		}
	}

	private void ordenarEnterosAscendente(int[] numeros) {
		OrdenIntAsc[] orden = new OrdenIntAsc[numeros.length];
		for (int i = 0; i < numeros.length; i++) {
			orden[i] = new OrdenIntAsc(numeros[i]);
		}
		Util.ordenar(orden, 0);
		for (int i = 0; i < orden.length; i++) {
			numeros[i] = orden[i].ordenAux;
		}
	}

	private class OrdenIntDesc implements IOrdenacion {
		private int ordenAux;

		private OrdenIntDesc(int orden) {
			this.ordenAux = orden;
		}

		public String getKeyOrder(int orden) {
			return getZeroesNumber(8, 99999999 - ordenAux);
		}
	}

	private class OrdenIntAsc implements IOrdenacion {
		private int ordenAux;

		private OrdenIntAsc(int orden) {
			this.ordenAux = orden;
		}

		public String getKeyOrder(int orden) {
			return getZeroesNumber(8, ordenAux);
		}
	}

	public static String[] separarEnLineas(String texto) {
		ArrayList<String> lista = new ArrayList<String>();
		char[] cars = texto.toCharArray();
		char retornoA = 0x0A;
		char retornoD = 0x0D;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] != retornoA && cars[i] != retornoD) {
				sb.append(cars[i]);
			} else {
				lista.add(sb.toString());
				sb = new StringBuilder();
				int next = i + 1;
				if (next < cars.length) {
					if (cars[i] == retornoA) {
						if (cars[next] == retornoD) {
							i = next;
							continue;
						}
					} else if (cars[i] == retornoD) {
						if (cars[next] == retornoA) {
							i = next;
							continue;
						}
					}
				}
			}
		}
		if (sb.length() > 0) {
			lista.add(sb.toString());
		}
		return lista.toArray(new String[lista.size()]);
	}

	public static String transformTextoJasper(String texto) {
		String cadena = texto.replace("\n", DEFAULT_LINE_BREAK_ESCAPE);
		cadena = cadena.replace("\r", DEFAULT_CARRIAGE_RETURN_ESCAPE);
		return cadena;
	}

	public static int extraerNumeroDerechaEnCodigo(String codigo) {
		if (codigo == null)
			return 0;
		char[] cars = codigo.toCharArray();
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (int i = cars.length - 1; i >= 0; i--) {
			if (isDigitoNumerico(cars[i])) {
				index = i;
			} else {
				break;
			}
		}
		for (int i = index; i < cars.length; i++) {
			sb.append(cars[i]);
		}
		int numero = Transform.toInt(sb.toString());
		return numero;
	}

	public static boolean isTextoAlfabetico(String texto) {
		texto = texto.toUpperCase();
		char[] chars = texto.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (!isAlfabetico(chars[i]))
				return false;
		}
		return true;
	}

	public static boolean isAlfabetico(char car) { // caracteres alfabeticos
		String alfa = "ABCDEFGHIJKLMNOPQRSTUVWXYZÃ?Ã‰Ã?Ã“ÃšÃœÃ‘Ã‡ ";
		return (alfa.indexOf(car) != -1); // INICIO
	}

	public static double getParteDecimal(double num) {
		int entero = (int) num;
		double decimales = num - entero;
		return decimales;
	}

	public static String getSHA1(String cadena) throws ErrorGeneral {
		MessageDigest sha = null;
		try {
			sha = MessageDigest.getInstance("SHA-1");
		} catch (Exception e) {
			throw new ErrorGeneral(e);
		}
		sha.update(cadena.getBytes());
		byte[] hash = sha.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xFF & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return new String(String.valueOf(hexString));
	}

	public static String getHEXString(String cadena) {
		byte[] bytesCadena = cadena.getBytes();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < bytesCadena.length; i++) {
			String hex = Integer.toHexString(0xFF & bytesCadena[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return new String(String.valueOf(hexString));
	}

	public static String getStringFromHEX(String hString) {
		byte[] bts = new byte[hString.length() / 2];
		for (int i = 0; i < bts.length; i++) {
			bts[i] = (byte) Integer.parseInt(hString.substring(2 * i, 2 * i + 2), 16);
		}
		String cadena = new String(bts);
		return cadena;
	}

	public static String truncarCadena(String cadena, int limite) {
		if (cadena.length() <= limite)
			return cadena;
		cadena = cadena.substring(0, limite - 3);
		StringBuilder aux = new StringBuilder(cadena);
		aux.append("...");
		return aux.toString();
	}

	public static String quitarCerosIzqNif(String nif) {
		String str = "";
		int indice = -1;
		while (nif.charAt(++indice) == '0')
			;
		str = nif.substring(indice, nif.length());
		return str;
	}

	public static String quitarEspacios(String cadena) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) != ' ')
				str.append(cadena.charAt(i));
		}
		return str.toString();
	}

	public static String restarCadena(String cadena1, String cadena2) {
		if (cadena1.startsWith(cadena2)) {
			return cadena1.substring(cadena2.length());
		} else {
			return cadena1;
		}
	}

	public static String getStringStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}

	public static String convertirArrayBytesAString(byte[] arrayBytes) {
		return new String(arrayBytes);
	}

	public static String convertirArrayStringAString(String[] arrayCadenas) {
		if (arrayCadenas.length == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arrayCadenas.length; i++) {
			sb.append(arrayCadenas[i]);
			if ((i + 1) != arrayCadenas.length) {
				sb.append("|");
			}
		}
		return sb.toString();
	}

	public static String[] convertirStringAArrayString(String cadena) {
		if (cadena.equals("")) {
			return new String[0];
		}
		String[] arrayCadenas = separaPalabras(cadena, "|");
		return arrayCadenas;
	}

}