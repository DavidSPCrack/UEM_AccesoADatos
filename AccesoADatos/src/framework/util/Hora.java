package framework.util; 

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author David
 */
public final class Hora extends Fecha {

	public final static String formatoFechaHora = "dd/MM/yyyy HH:mm:ss";
	public static final int milisegundosHora = 86400000 / 24;
	public static final int ORDEN_HORA = 10;
	public static final int ORDEN_HORA_DESC = 11;

	public Hora() {
		super();
	}

	public static Hora getInstanceHora(String _fechaHora) throws ParseException {
		SimpleDateFormat sdf = null;
		if (_fechaHora.length() == 10)
			sdf = new SimpleDateFormat("dd/MM/yyyy");
		else
			sdf = new SimpleDateFormat(formatoFechaHora);
		sdf.parse(_fechaHora);
		return new Hora(_fechaHora);
	}

	public Hora(String fecha) {
		super(fecha, fecha.length() == 10 ? "dd/MM/yyyy" : formatoFechaHora);
	}

	public Hora(int year, int month, int date, int hour, int minute, int second) {
		super(year, month, date, hour, minute, second);
	}

	public Hora(Date date) {
		super(date);
	}

	public Hora(Fecha fecha) {
		super(fecha.getDate());
	}

	public String toChar() {
		SimpleDateFormat sdf = new SimpleDateFormat(formatoFechaHora);
		return sdf.format(getDate());
	}

	public String toString() {
		if (isNull())
			return "";
		return toChar();
	}

	public static Hora getHoraNull() {
		return new Hora(1900, 1, 1, 0, 0, 0);
	}

	public boolean isNull() {
		return toChar("dd/MM/yyyy").equals("01/01/1900");
	}
	
	public static Hora getFechaHoraSistema(){
		return new Hora();
	}

	/**
	 * Devuelve la diferencia entre el numero 99999999 y el número formado por
	 * la AAAAMMDD. (Se utiliza para ordenación descendente);
	 * 
	 * @return
	 */
	private final static BigInteger numeroAlto = new BigInteger("99999999999999");

	public String getValorInverso() {
		BigInteger big = new BigInteger(this.toChar("yyyyMMddHHmmss"));
		return numeroAlto.subtract(big).toString();
	}

	public Hora addHoras(int numeroHoras) {
		long dateinmills = getDateInMillis();
		long aux = numeroHoras * milisegundosHora;
		Hora nueva = new Hora(new Date(dateinmills + aux));
		return nueva;
	}

	public Fecha truncar() {
		return new Fecha(getYear(), getMes(), getDia());
	}
	
	public String getKeyOrder(int orden) {
		if (orden == ORDEN_HORA)
			return toChar("yyyyMMddHHmmss");
		if (orden == ORDEN_HORA_DESC)
			return getValorInverso();
		return super.getKeyOrder(orden);
	}

	public boolean isMayorIgual(Hora hora) {
		return (compare(hora) >= 0);
	}

	public boolean isMayor(Hora hora) {
		return (compare(hora) > 0);
	}

	public boolean isMenorIgual(Hora hora) {
		return (compare(hora) <= 0);
	}

	public boolean isMenor(Hora hora) {
		return (compare(hora) < 0);
	}

	private long compare(Hora hora) {
		long hora1 = this.getDate().getTime();
		long hora2 = hora.getDate().getTime();
		return (hora1 - hora2);
	}

}