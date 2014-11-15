package ejercicios.act07;

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

}
