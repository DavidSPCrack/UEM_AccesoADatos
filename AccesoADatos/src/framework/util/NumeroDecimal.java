package framework.util;

import framework.util.interfaces.IOrdenacion;

/**
 * 
 * @author David
 * 
 */

public final class NumeroDecimal implements IOrdenacion {

	private final static char COMA = ',';
	private final static char PUNTO = '.';
	private double numero;
	private String entero;
	private String decimales;

	public NumeroDecimal(double numero) {
		this.numero = numero;
		split(numero, 9);
	}

	public NumeroDecimal(double numero, int precision) {
		this.numero = numero;
		split(numero, precision);
	}

	public NumeroDecimal() {
		this.numero = 0;
		this.entero = "0";
		this.decimales = "0";
	}

	private void split(double numero, int precision) {
		String[] items = Util.splitToEnteroDecimal(numero, precision);
		this.entero = items[0];
		this.decimales = items[1];
	}

	public int getLongitudDecimales() {
		char[] decs = getPartdec().toCharArray();
		int longitud = decs.length;
		for (int i = longitud - 1; i >= 0; i--) {
			if (decs[i] != '0') {
				return i + 1;
			}
		}
		return 0;
	}

	public NumeroDecimal(String entero, String decimales) {
		this.entero = entero;
		this.decimales = decimales;
		StringBuilder sb = new StringBuilder();
		sb.append(entero);
		sb.append('.');
		sb.append(decimales);
		this.numero = Double.parseDouble(sb.toString());
	}

	public static NumeroDecimal parsear(String importeTexto) {
		if (importeTexto == null || importeTexto.length() == 0)
			return new NumeroDecimal();
		int puntos = 0;
		int comas = 0;
		boolean errorFormato = false;
		char last = '0';
		char[] cars = importeTexto.toCharArray();
		for (int i = 0; i < cars.length; i++) {
			switch (cars[i]) {
			case COMA:
				last = COMA;
				comas++;
				break;
			case PUNTO:
				last = PUNTO;
				puntos++;
				break;
			default:
				if (!Util.isDigitoNumerico(cars[i])) {
					errorFormato = true;
				}
				break;
			}
		}
		if (!errorFormato)
			if (puntos > 1 & comas > 1) {
				errorFormato = true;
			}
		if (errorFormato) {
			return new NumeroDecimal();
		}
		if (puntos == 0 && comas == 0) {
			// Es un número entero.
			return new NumeroDecimal(Long.parseLong(importeTexto));
		}
		if (last == PUNTO && comas == 0) {
			// Es un texto convertido desde un Double.
			return new NumeroDecimal(Double.parseDouble(importeTexto));
		}
		if (last == COMA && comas == 1) {
			// Es un texto que viene desde HTML.
			return new NumeroDecimal(Transform.toDoubleFromImporteHTML(importeTexto));
		}
		return new NumeroDecimal(Transform.toDouble(importeTexto));
	}

	public long getPartInt() {
		return Long.parseLong(entero);
	}

	public String getPartdec() {
		return decimales;
	}

	public String getPartIntToString(int numCars) {
		return Util.ajustarDerecha(numCars, entero, '0');
	}

	public String getPartDecToString(int numCars) {
		return Util.ajustarDerecha(numCars, decimales, '0');
	}

	public boolean isCero() {
		if (Transform.toLong(entero) == 0) {
			if (Transform.toLong(decimales) == 0) {
				return true;
			}
		}
		return false;
	}

	public double getNumero() {
		return numero;
	}

	public double getDoubleWithPrecision() {
		StringBuilder sb = new StringBuilder();
		sb.append(entero);
		sb.append('.');
		sb.append(decimales);
		return Transform.toDouble(sb.toString());
	}

	public String getCadenaParaOrdenacion() {
		StringBuilder sb = new StringBuilder();
		sb.append(Util.getZeroesNumber(9, getPartInt()));
		sb.append(Util.ajustarIzquierda(9, decimales, '0'));
		return sb.toString();
	}

	public String getCadenaParaOrdenacionSigno() {
		StringBuilder sb = new StringBuilder();
		if (numero >= 0) {
			sb.append(Util.getZeroesNumber(9, getPartInt()));
			sb.append(Util.ajustarIzquierda(9, decimales, '0'));
		} else {
			sb.append("-");
			sb.append(Util.getZeroesNumber(8, 99999999 + getPartInt()));
			String deci9 = Util.ajustarIzquierda(9, decimales, '0');
			long dec = 999999999 - Long.parseLong(deci9);
			sb.append(Util.getZeroesNumber(9, dec));
		}
		return sb.toString();
	}

	public String toStringParaTarifa() {
		long entero = getPartInt();
		long decimales = Long.parseLong(this.decimales);
		StringBuilder sb = new StringBuilder();
		sb.append(entero);
		if (decimales > 0) {
			sb.append(",");
			sb.append(this.decimales);
		}
		return sb.toString();
	}

	public String formatString() {
		int decimales = getLongitudDecimales();
		return decimales < 3 ? Util.formatImportes(getNumero()) : Util.formatTasa(getNumero());
	}

	public static NumeroDecimal parsearConSigno(String importeTexto) {
		if (importeTexto == null || importeTexto.length() == 0)
			return new NumeroDecimal();
		int puntos = 0;
		int comas = 0;
		boolean errorFormato = false;
		char last = '0';
		importeTexto.trim();
		char[] cars = importeTexto.toCharArray();
		int iniNumber = 0;
		if (cars[0] == '-' || cars[0] == '+') {
			iniNumber++;
		}
		for (int i = iniNumber; i < cars.length; i++) {
			switch (cars[i]) {
			case COMA:
				last = COMA;
				comas++;
				break;
			case PUNTO:
				last = PUNTO;
				puntos++;
				break;
			default:
				if (!Util.isDigitoNumerico(cars[i])) {
					errorFormato = true;
				}
				break;
			}
		}
		if (!errorFormato)
			if (puntos > 1 & comas > 1) {
				errorFormato = true;
			}
		if (errorFormato) {
			return new NumeroDecimal();
		}
		if (puntos == 0 && comas == 0) {
			// Es un número entero.
			return new NumeroDecimal(Long.parseLong(importeTexto));
		}
		if (last == PUNTO && comas == 0) {
			// Es un texto convertido desde un Double.
			return new NumeroDecimal(Double.parseDouble(importeTexto));
		}
		if (last == COMA && comas == 1) {
			// Es un texto que viene desde HTML.
			return new NumeroDecimal(Transform.toDoubleFromImporteHTML(importeTexto));
		}
		return new NumeroDecimal(Transform.toDouble(importeTexto));
	}

	public String toString4Tarifa() {
		// long entero = getPartInt();
		long decimales = Long.parseLong(this.decimales);
		StringBuilder sb = new StringBuilder();
		sb.append(entero);
		if (decimales > 0) {
			sb.append(",");
			sb.append(this.decimales);
		}
		return sb.toString();
	}

	public String getKeyOrder(int orden) {
		return getCadenaParaOrdenacion();
	}
}
