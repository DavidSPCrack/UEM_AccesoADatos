package ejercicios.act07;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Bicicleta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int TAM_FECHA = 10;
	private static final int TAM_REGISTRO = Integer.BYTES + Byte.BYTES + (TAM_FECHA * 2) + Integer.BYTES;
	public static final Bicicleta BICICLETA_VOID = new Bicicleta(0, false, "", 0);

	private int id;
	private boolean disponible;
	private Date fechaRevision;
	private int idTotem;

	private SimpleDateFormat dateFormat;
	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	private Bicicleta() {
		super();
		this.dateFormat = new SimpleDateFormat(FORMATO_FECHA);
	}

	public Bicicleta(int id, boolean disponible, String fechaRevision, int idTotem) {
		this();
		this.id = id;
		this.disponible = disponible;
		setFechaRevision(fechaRevision);
		this.idTotem = idTotem;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getFechaRevision() {
		return fechaRevision == null ? "" : dateFormat.format(fechaRevision);
	}

	private String getFechaRevisionFormateada() {
		StringBuilder sb = new StringBuilder(getFechaRevision());
		sb.setLength(TAM_FECHA);
		return sb.toString();
	}

	public void setFechaRevision(String fechaRevision) {
		try {
			this.fechaRevision = dateFormat.parse(fechaRevision);
		} catch (ParseException e) {
			Calendar calendar = new GregorianCalendar(1900, 1, 1);
			this.fechaRevision = calendar.getTime();
		}
	}

	public int getIdTotem() {
		return idTotem;
	}

	public void setIdTotem(int idTotem) {
		this.idTotem = idTotem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (disponible ? 1231 : 1237);
		result = prime * result + ((fechaRevision == null) ? 0 : fechaRevision.hashCode());
		result = prime * result + id;
		result = prime * result + idTotem;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bicicleta other = (Bicicleta) obj;
		if (disponible != other.disponible)
			return false;
		if (fechaRevision == null) {
			if (other.fechaRevision != null)
				return false;
		} else if (!fechaRevision.equals(other.fechaRevision))
			return false;
		if (id != other.id)
			return false;
		if (idTotem != other.idTotem)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bicicleta [id=" + getId() + ", disponible=" + isDisponible() + ", fechaRevision=" + getFechaRevision() + ", idTotem=" + getIdTotem() + "]";
	}

	public void writeObject(RandomAccessFile raf) throws IOException {
		int id = getId();
		boolean disponible = isDisponible();
		String fechaRevision = getFechaRevisionFormateada();
		int idTotam = getIdTotem();
		raf.writeInt(id);
		raf.writeBoolean(disponible);
		raf.writeChars(fechaRevision);
		raf.writeInt(idTotam);
	}

	public static void writeVoidObject(RandomAccessFile raf) throws IOException {
		Bicicleta.BICICLETA_VOID.writeObject(raf);
	}

	public static Bicicleta readObject(RandomAccessFile raf) throws IOException {
		int id = raf.readInt();
		boolean disponible = raf.readBoolean();
		StringBuilder fechaRevision = new StringBuilder();
		for (int i = 0; i < TAM_FECHA; i++) {
			fechaRevision.append(raf.readChar());
		}
		int idTotem = raf.readInt();
		Bicicleta bici = new Bicicleta(id, disponible, fechaRevision.toString(), idTotem);
		return bici;
	}

	public static int getRegisterSize() {
		return TAM_REGISTRO;
	}

}
