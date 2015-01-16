package framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import framework.util.interfaces.IOrdenacion;

public class Fecha implements Cloneable, IOrdenacion {

	public static final double milisegundosDia = 86400000.0;

	public static final int ORDEN_FECHA = 0;
	public static final int ORDEN_FECHA_DESC = 1;

	public static final Fecha fInicioMesActual = getFechaSistema().getInicioMes();
	public static final Fecha fInicioAñoActual = fInicioMesActual.getInicioAño();

	public static Fecha getFechaNull() {
		return new Fecha(1900, 1, 1);
	}

	public static Fecha getFechaFinalDLosTiempos() {
		return new Fecha(2099, 12, 31);
	}

	/**
	 * Devuelve la fecha del día sin horas, minutos ni segundos.
	 */
	public static Fecha getFechaSistema() {
		Fecha fecha = new Fecha();
		int ann = fecha.getYear();
		int mes = fecha.getMes();
		int dia = fecha.getDia();
		return new Fecha(ann, mes, dia);
	}

	public static Fecha getInstanceFecha(String _fecha) throws ParseException {
		return new Fecha(_fecha);
	}

	private Calendar calendar = null;
	private int[] dias_mes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static final String formatoFechaOracle = "dd/MM/yyyy";
	public static final String formatoFechaInvertida = "yyyyMMdd";
	public static final String formatoFechaInvertidaConBarras = "yyyy/MM/dd";

	public Fecha() {
		// Construye un calendario con la fecha actual
		calendar = new GregorianCalendar();
	}

	public Fecha(Date date) {
		calendar = new GregorianCalendar();
		if (date == null)
			setTime(1900, 01, 01);
		else
			setTime(date);
	}

	public Fecha(Fecha fecha) {
		calendar = new GregorianCalendar();
		setTime(fecha.getDate());
	}

	public Fecha(int year, int month, int day) {
		calendar = new GregorianCalendar(year, (month - 1), day);
	}

	public Fecha(int year, int month, int day, int hour, int minute) {
		calendar = new GregorianCalendar(year, (month - 1), day, hour, minute);
	}

	public Fecha(int year, int month, int day, int hour, int minute, int second) {
		calendar = new GregorianCalendar(year, (month - 1), day, hour, minute, second);
	}

	public Fecha(String fecha) {
		this(fecha, getFormatoFechaOracle());
	}

	public Fecha(String fecha, String formato) {
		try {
			Date fech;
			SimpleDateFormat sdf = new SimpleDateFormat(formato);
			calendar = new GregorianCalendar();
			if (fecha == null) {
				fecha = "";
			}
			fech = sdf.parse(fecha);
			setTime(fech);

			if (!fecha.equals(toChar(formato))) {
				setTime(1900, 01, 01);
			}

		} catch (ParseException pe) {
			if (fecha != null && fecha.startsWith("TIME_")) {
				try {
					long datemiles = Long.parseLong(fecha.substring(5));
					Date date = new Date(datemiles);
					calendar = new GregorianCalendar();
					setTime(date);
				} catch (Exception e) {
					calendar = new GregorianCalendar();
					setTime(1900, 01, 01);
				}
			} else if (fecha != null && fecha.length() == 4) {
				int year = Transform.toInt(fecha);
				if (year > 1900 && year < 2200) {
					calendar = new GregorianCalendar();
					setTime(year, 01, 01);
				} else {
					calendar = new GregorianCalendar();
					setTime(1900, 01, 01);
				}

			} else {
				calendar = new GregorianCalendar();
				setTime(1900, 01, 01);
			}

		}

	}

	public Fecha(int year, int month) {
		this(year == 0 ? Fecha.getFechaNull() : month == 0 ? Fecha.getFechaNull() : new Fecha(year, month, 1).getFinMes());
	}

	public void addAños(int numeroAnn) {
		calendar.add(Calendar.YEAR, numeroAnn);
	}

	public void addYears(int numberOffYearsToAdd) {
		addAños(numberOffYearsToAdd);
	}

	public void addDias(int numeroDias) {
		calendar.add(Calendar.DATE, numeroDias);
	}

	public static Fecha addDias(Fecha fecha, int nDias) {
		Fecha aux = (Fecha) fecha.clone();
		aux.addDias(nDias);
		return aux;
	}

	public void addDiasHabiles(int numeroDias) {
		for (int aux = 0; aux < numeroDias; aux++) {
			addDias(1);
			if (isSabado()) {
				addDias(1);
			}
			if (isDomingo()) {
				addDias(1);
			}
		}
	}

	public static Fecha addDiasHabiles(Fecha fecha, int numeroDias) {
		Fecha auxFecha = (Fecha) fecha.clone();
		for (int aux = 0; aux < numeroDias; aux++) {
			auxFecha.addDias(1);
			if (auxFecha.isSabado()) {
				auxFecha.addDias(1);
			}
			if (auxFecha.isDomingo()) {
				auxFecha.addDias(1);
			}
		}
		return auxFecha;
	}

	public void addMeses(int numeroMeses) {
		calendar.add(Calendar.MONTH, numeroMeses);
	}

	public Object clone() {
		Fecha fecha;
		fecha = new Fecha(this.getDate());
		return fecha;
	}

	public long compare(Fecha fecha) {
		long fecha1 = this.getDate().getTime();
		long fecha2 = fecha.getDate().getTime();
		return (fecha1 - fecha2);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Fecha) {
			Fecha aux = (Fecha) obj;
			return toString().equals(aux.toString());
		}
		return false;
	}

	public Date getDate() {
		return calendar.getTime();
	}

	public long getDateInMillis() {
		return calendar.getTimeInMillis();
	}

	public int getDia() {
		return calendar.get(Calendar.DATE);
	}

	public int getDiasDiferencia(Fecha fecha) {
		double fecha1 = this.getDate().getTime();
		double fecha2 = fecha.getDate().getTime();
		double dif = (fecha1 - fecha2) / milisegundosDia;
		int dias = (int) Math.round(dif);
		return dias;
	}

	public int getDiaAnyo() {
		return getDiasDiferencia(getInicioAño());
	}

	public int getDiasHabilesDiferencia(Fecha fecha) {
		int difDias = 0;
		if (this.compare(fecha) == 0)
			return difDias;
		Fecha fechaOrigen;
		Fecha fechaDestino;
		if (this.isMenor(fecha)) {
			fechaOrigen = (Fecha) this.clone();
			fechaDestino = fecha;
		} else {
			fechaOrigen = fecha;
			fechaDestino = this;
		}
		do {
			fechaOrigen.addDias(1);
			if (!fechaOrigen.isDomingo())
				difDias++;
		} while (fechaOrigen.isMenor(fechaDestino));
		return difDias;
	}

	public int getMesesDiferencia(Fecha fecha) {
		int anyos = this.getYear() - fecha.getYear();
		int meses = this.getMes() - fecha.getMes();
		boolean finMesHasta = this.isFinMes();
		boolean finMesDesde = fecha.isFinMes();
		boolean diaUnoDesde = fecha.getDia() == 1;
		int exceso = 0;
		if (finMesHasta && diaUnoDesde) {
			exceso = 1;
		} else if (finMesDesde && finMesHasta) {
			exceso = 0;
		} else if (fecha.getDia() > this.getDia()) {
			exceso = -1;
		}
		int nMeses = (anyos * 12) + meses + exceso;
		return nMeses;
	}

	public boolean isDomingo() {
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	public boolean isSabado() {
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}

	public int getDiaSemana() {
		int dayInt = calendar.get(Calendar.DAY_OF_WEEK);
		switch (dayInt) {
		case Calendar.MONDAY:
			return 0;
		case Calendar.TUESDAY:
			return 1;
		case Calendar.WEDNESDAY:
			return 2;
		case Calendar.THURSDAY:
			return 3;
		case Calendar.FRIDAY:
			return 4;
		case Calendar.SATURDAY:
			return 5;
		case Calendar.SUNDAY:
			return 6;
		}
		return -1;
	}

	public int getSemanaMes() {
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}

	public int getDiasMes() {
		int mes = getMes();
		int ann = getYear();
		int dia = dias_mes[mes - 1];
		if (mes == 2 && ann % 4 == 0) {
			dia++;
		}
		return dia;
	}

	public Fecha getFinMes() {
		int mes = getMes();
		int ann = getYear();
		int dia = dias_mes[mes - 1];
		if (mes == 2 && ann % 4 == 0) {
			dia++;
		}
		return new Fecha(ann, mes, dia);
	}

	public String getFormatoCorto() {
		return this.toChar(getFormatoFechaOracle().replaceAll("yyyy", "yy"));
	}

	public String getFormatoInvertido() {
		return this.toChar(formatoFechaInvertida);
	}

	public static String getFormatoFechaOracle() {
		return formatoFechaOracle;
	}

	public int getHora() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public Fecha getInicioAño() {
		int ann = getYear();
		int mes = 1;
		int dia = 1;
		return new Fecha(ann, mes, dia);
	}

	public Fecha getFinalAño() {
		int ann = getYear();
		int mes = 12;
		int dia = 31;
		return new Fecha(ann, mes, dia);
	}

	public Fecha getInicioMes() {
		int mes = getMes();
		int ann = getYear();
		int dia = 1;
		return new Fecha(ann, mes, dia);
	}

	public int getMes() {
		return (calendar.get(Calendar.MONTH)) + 1;
	}

	public int getMinuto() {
		return calendar.get(Calendar.MINUTE);
	}

	public int getSegundo() {
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * Devuelve la diferencia entre el numero 99999999 y el número formado por
	 * la AAAAMMDD. (Se utiliza para ordenación descendente);
	 * 
	 * @return
	 */
	public String getValorInverso() {
		long fecha = Long.parseLong(this.toChar("yyyyMMdd"));
		return Long.toString(99999999 - fecha);
	}

	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	public boolean isFinMes() {
		return getFinMes().getDia() == this.getDia();
	}

	public boolean isHoy() {
		return toChar().equals(getFechaSistema().toChar());
	}

	public boolean isMayorIgual(Fecha fecha) {
		return (compare(fecha) >= 0);
	}

	public boolean isMayor(Fecha fecha) {
		return (compare(fecha) > 0);
	}

	public boolean isMayorIgualQueHoy() {
		return isMayorIgual(getFechaSistema());
	}

	public boolean isMenorQueHoy() {
		return isMenor(getFechaSistema());
	}

	public boolean isMenorIgualQueHoy() {
		return isMenorIgual(getFechaSistema());
	}

	public boolean isMayorQueHoy() {
		return isMayor(getFechaSistema());
	}

	public boolean isMenorIgual(Fecha fecha) {
		return (compare(fecha) <= 0);
	}

	public boolean isMenor(Fecha fecha) {
		return (compare(fecha) < 0);
	}

	public boolean isNull() {
		return toChar().equals(getFechaNull().toChar());
	}

	public boolean isFinalDLosTiempos() {
		return toChar().equals(getFechaFinalDLosTiempos().toChar());
	}

	public Tiempo getDuracion(Fecha fecha) {
		long fecha1 = this.getDate().getTime();
		long fecha2 = fecha.getDate().getTime();
		long duracion = Math.abs(fecha1 - fecha2);
		return new Tiempo(duracion);
	}

	public void setHora(int hour) {
		calendar.set(Calendar.HOUR_OF_DAY, hour);
	}

	public void setMiliSegundo(int milliSecond) {
		calendar.set(Calendar.MILLISECOND, milliSecond);
	}

	public void setMinuto(int minute) {
		calendar.set(Calendar.MINUTE, minute);
	}

	public void setSegundo(int second) {
		calendar.set(Calendar.SECOND, second);
	}

	public void setTime(Date date) {
		calendar.setTime(date);
	}

	public void setTime(int year, int month, int day) {
		calendar.set(year, (month - 1), day);
	}

	public void setTime(int year, int month, int day, int hour, int minute) {
		calendar.set(year, (month - 1), day, hour, minute);
	}

	public void setTime(int year, int month, int day, int hour, int minute, int second) {
		calendar.set(year, (month - 1), day, hour, minute, second);
	}

	public String toChar() {
		SimpleDateFormat sdf = new SimpleDateFormat(getFormatoFechaOracle());
		return sdf.format(getDate());
	}

	public String toChar(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(getDate());
	}

	public String toString() {
		if (isNull())
			return "";
		return toChar();
	}

	public int getTrimestre() {
		int mes = getMes();
		return mes < 4 ? 1 : mes < 7 ? 2 : mes < 10 ? 3 : 4;
	}

	public static int getYearToday() {
		return new Fecha().getYear();
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

	public static boolean isDigitosNumericos(String cadena) {
		char[] cars = cadena.toCharArray();
		for (int i = 0; i < cars.length; i++) {
			if (!isDigitoNumerico(cars[i]))
				return false;
		}
		return true;
	}

	public static String getCaracteresNumericos(String cadena) {
		char[] cars = cadena.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cars.length; i++) {
			if (isDigitoNumerico(cars[i]))
				sb.append(cars[i]);
		}
		return sb.toString();
	}

	public static Fecha parsear(String fechaTexto) {
		String aux = getCaracteresNumericos(fechaTexto);
		if (aux.length() == 8)
			return new Fecha(aux, "ddMMyyyy");
		if (aux.length() == 6)
			return new Fecha(aux, "ddMMyy");
		return Fecha.getFechaNull();
	}

	public static Fecha getTomorrow() {
		Fecha tomorrow = getFechaSistema();
		tomorrow.addDias(1);
		return tomorrow;
	}

	public boolean isPrimeroDEnero() {
		return (getDia() + getMes()) == 2;
	}

	public boolean isFinAnyo() {
		return getMes() == 12 && getDia() == 31;
	}

	public boolean isFechaVencimientoCompatible(Fecha fecha) {
		int diasAnyo1 = getDiaAnyo();
		int diasAnyo2 = fecha.getDiaAnyo();
		int diferencia = Math.abs(diasAnyo1 - diasAnyo2);
		return diferencia < 2;
	}

	public int getDiferenciaEnDias(Fecha fecha) {
		return getDiasDiferencia(fecha);
	}

	public static boolean isNull(Fecha fecha) {
		if (fecha == null) {
			return true;
		}
		if (fecha.isNull()) {
			return true;
		}
		return false;
	}

	public void trunc() {
		this.calendar = new GregorianCalendar(getYear(), (getMes() - 1), getDia());
	}

	public String getKeyOrder(int orden) {
		if (orden == ORDEN_FECHA)
			return toChar("yyyyMMdd");
		if (orden == ORDEN_FECHA_DESC)
			return getValorInverso();
		return toChar("yyyyMMdd");
	}

}