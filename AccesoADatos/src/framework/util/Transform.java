package framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public final class Transform {

	private static char DOT_SEPARATION = ' ';

	public static long toLong(String cadena) {
		return getDatoLong(cadena);
	}

	public static String toString(long valor) {
		return Long.toString(valor);
	}

	public static String toString(int valor) {
		return Integer.toString(valor);
	}

	public static String toString(char valor) {
		return valor + "";
	}

	public static String toString(double valor) {
		String patron = "############.############";
		char separador = '.';
		return toString(valor, patron, separador);
	}

	public static String toString(double valor, String patron, char separador) {
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator(separador);
		dfs.setGroupingSeparator('\0');
		DecimalFormat df = new DecimalFormat(patron, dfs);
		String result = df.format(valor);
		return result;
	}

	public static int toInt(char c) {
		String cadena = "" + c;
		return toInt(cadena);
	}

	public static int toInt(String cadena) {
		return getDatoInteger(cadena);
	}

	private static int getDatoInteger(String dato) {
		if (dato == null)
			return 0;

		try {
			return Integer.parseInt(dato);
		} catch (NumberFormatException e) {
			if (dato.length() > 0)
				return (int) getDatoDouble(dato);
			return 0;
		}
	}

	public static double toDouble(String cadena) {
		return getDatoDouble(cadena);
	}

	public static double toDouble(Object obj) {
		if (obj != null) {
			if (obj instanceof Double) {
				return ((Double) obj).doubleValue();
			}
			if (obj instanceof String) {
				return getDatoDouble((String) obj);
			}
			if (obj instanceof Float) {
				return ((Float) obj).doubleValue();
			}
			if (obj instanceof Integer) {
				return ((Integer) obj).doubleValue();
			}
			if (obj instanceof BigDecimal) {
				return ((BigDecimal) obj).doubleValue();
			}
		}
		return 0;
	}

	public static double toDoubleFromImporteHTML(String texto) {
		if (texto == null || texto.length() == 0)
			return 0.0;

		char COMA = getDotSeparation();
		char PUNTO = getGroupingSeparator();

		StringBuilder entero = new StringBuilder();
		StringBuilder decimal = new StringBuilder();
		boolean decimales = false;
		char[] cars = texto.toCharArray();
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] == COMA) {
				decimales = true;
			} else if (cars[i] == PUNTO) {

			} else {
				if (decimales) {
					decimal.append(cars[i]);
				} else {
					entero.append(cars[i]);
				}
			}
		}

		if (!decimales) {
			decimal.append('0');
		}
		if (entero.length() == 0) {
			entero.append("0");
		}
		String cadenaDouble = entero.toString() + "." + decimal.toString();
		return Double.parseDouble(cadenaDouble);
	}

	private static double getDatoDouble(String dato) {
		if ((dato == null) || ("".equals(dato))) {
			return 0;
		}
		try {
			return Double.parseDouble(dato);
		} catch (NumberFormatException e) {
			try {
				return Double.parseDouble(tratarStringImporte(dato));
			} catch (NumberFormatException e2) {
				return 0.0;
			}
		}
	}

	private static long getDatoLong(String dato) {
		if (dato == null)
			return 0;
		try {
			return Long.parseLong(dato);
		} catch (NumberFormatException e) {
			if (dato.length() > 0)
				return (long) getDatoDouble(dato);
			return 0;
		}
	}

	private static String tratarStringImporte(String dato) {
		char[] cars = dato.toCharArray();
		StringBuffer sb = new StringBuffer();
		char dotSeparator = getDotSeparation();
		char groupingSeparator = getGroupingSeparator();
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] != groupingSeparator) {
				if (cars[i] == dotSeparator) {
					sb.append('.');
				} else {
					sb.append(cars[i]);
				}
			}
		}
		return sb.toString();
	}

	public static char getDotSeparation() {
		if (DOT_SEPARATION != ' ') {
			return DOT_SEPARATION;
		}
		char dot = ',';
		DOT_SEPARATION = dot;
		return DOT_SEPARATION;
	}

	public static char getGroupingSeparator() {
		char dotSeparation = getDotSeparation();
		char groupingSeparator = dotSeparation == '.' ? ',' : '.';
		return groupingSeparator;
	}

}
