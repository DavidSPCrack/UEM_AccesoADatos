package framework.util;

public class Tiempo {

	public static final double milisegundosDia = (int) Fecha.milisegundosDia;
	public static final double milisegundosHora = milisegundosDia / 24.0;
	public static final double milisegundosMinuto = milisegundosHora / 60.0;

	private int segundos = 0;
	private int minutos = 0;
	private int horas = 0;
	private int dias = 0;

	public Tiempo(long milisegundos) {
		double resto = 0;
		dias = (int) ((double) milisegundos / milisegundosDia);
		resto = milisegundos - (dias * milisegundosDia);
		if (resto > 0) {
			horas = (int) (resto / milisegundosHora);
			resto = resto - (horas * milisegundosHora);
			if (resto > 0) {
				minutos = (int) (resto / milisegundosMinuto);
				resto = resto - (minutos * milisegundosMinuto);
			}
			if (resto > 0) {
				segundos = (int) (resto / 1000.0);
			}
		}
	}

	public Tiempo(int horas, int minutos, int segundos) {
		this.dias = 0;
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
	}

	public Tiempo(int dias, int horas, int minutos, int segundos) {
		this.dias = dias;
		this.horas = horas;
		this.minutos = minutos;
		this.segundos = segundos;
	}

	public String getTiempoHoras(boolean precision_minutos, boolean precision_segundos) {
		StringBuffer tiempo = new StringBuffer();
		tiempo.append(horas);
		tiempo.append("h");
		if (precision_minutos) {
			tiempo.append(" ");
			tiempo.append(minutos);
			tiempo.append("m");
			if (precision_segundos) {
				tiempo.append(" ");
				tiempo.append(segundos);
				tiempo.append("s");
			}
		}
		return tiempo.toString();
	}

	public int getTotalMinutos() {
		int minutosTotales = dias * 24 + horas * 60 + minutos;
		return minutosTotales;
	}

}
