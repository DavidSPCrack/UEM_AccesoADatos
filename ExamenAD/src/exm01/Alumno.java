package exm01;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Alumno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final static String LS = System.getProperty("line.separator");
	private final static String SEPARATOR = ";";

	private final static String MATRICULA = "MATRICULA";
	private final static String NOMBRE = "NOMBRE";
	private final static String ASIGNATURA = "ASIGNATURA";
	private final static String NOTA = "NOTA";

	private final static Alumno ALUMNO_VOID = new Alumno();

	/**
	 * Pongo longitud de Strings a 40 para que no se corten las cadenas
	 */
	private final static int STRING_LENGTH = 40;
	private final static int STRING_BYTES = STRING_LENGTH * 2;

	private final static int TAM_REGISTRO = Integer.BYTES + (STRING_BYTES * 2) + Float.BYTES;

	private int matricula;
	private String nombre;
	private String asignatura;
	private float nota;

	/**
	 * Constructor del alumno
	 * 
	 * @param matricula
	 *            indica la matricula del alumno
	 * @param nombre
	 *            indica la nombre del alumno
	 * @param asignatura
	 *            indica la asignatura del alumno
	 * @param nota
	 *            indica la nota del alumno
	 */
	public Alumno(int matricula, String nombre, String asignatura, float nota) {
		this.matricula = matricula;
		this.nombre = nombre;
		this.asignatura = asignatura;
		this.nota = roundFloat(nota);
	}

	/**
	 * Constructor del alumno vacio
	 */
	public Alumno() {
		this(0, "", "", 0);
	}

	/**
	 * Devuelve la matricula del alumno
	 * 
	 * @return matricula del alumno
	 */
	public int getMatricula() {
		return matricula;
	}

	/**
	 * Establece matricula del alumno
	 * 
	 * @param matricula
	 *            establece la matricula del alumno
	 */
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	/**
	 * Devuelve el nombre del alumno
	 * 
	 * @return nombre del alumno
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece nombre del alumno
	 * 
	 * @param nombre
	 *            establece el nombre del alumno
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la asignatura del alumno
	 * 
	 * @return asignatura del alumno
	 */
	public String getAsignatura() {
		return asignatura;
	}

	/**
	 * Establece asignatura del alumno
	 * 
	 * @param asignatura
	 *            establece la asignatura del alumno
	 */
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	/**
	 * Devuelve la nota del alumno
	 * 
	 * @return nota del alumno
	 */
	public float getNota() {
		return nota;
	}

	/**
	 * Establece nota del alumno
	 * 
	 * @param nota
	 *            establece la nota del alumno
	 */
	public void setNota(float nota) {
		this.nota = roundFloat(nota);
	}

	/**
	 * Metodo para obtener registro CSV del alumno
	 * 
	 * @return devuelve registro CSV del alumno en String
	 */
	public String getRegistroCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(getMatricula());
		sb.append(getSeparador());
		sb.append(getNombre());
		sb.append(getSeparador());
		sb.append(getAsignatura());
		sb.append(getSeparador());
		sb.append(getNota());
		return sb.toString();
	}

	/**
	 * Metodo para devolver la cabecera del fichero CSV
	 * 
	 * @return devuelve la cabecera del fichero CSV en String
	 */
	public static final String getNombresColumnasCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(MATRICULA);
		sb.append(getSeparador());
		sb.append(NOMBRE);
		sb.append(getSeparador());
		sb.append(ASIGNATURA);
		sb.append(getSeparador());
		sb.append(NOTA);
		return sb.toString();
	}

	/**
	 * Metodo para recoger el separador usado en fichero CSV
	 * 
	 * @return devuelve el separador
	 */
	public static final String getSeparador() {
		return SEPARATOR;
	}

	/**
	 * Metodo para a traves de los datos de un registro CSV construir un alumno
	 * 
	 * @param data
	 *            datos del CSV
	 * @return devuelve el alumno correspondiente
	 */
	public static final Alumno loadAlumnoFromCsv(String[] data) {
		int matricula = recoverDataInt(data, 0);
		String nombre = recoverData(data, 1);
		String asignatura = recoverData(data, 2);
		float nota = recoverDataFloat(data, 3);
		Alumno alumno = new Alumno(matricula, nombre, asignatura, nota);
		return alumno;
	}

	/**
	 * 
	 * Metodo para a traves de los datos del CSV recuperar una celda
	 * 
	 * @param data
	 *            datos del CSV
	 * @param i
	 *            celda a recuperar
	 * @return devuelve el dato correspondiente en String
	 */
	public static final String recoverData(String[] data, int i) {
		if (i < data.length) {
			return data[i];
		}
		return "";
	}

	/**
	 * 
	 * Metodo para a traves de los datos del CSV recuperar una celda
	 * 
	 * @param data
	 *            datos del CSV
	 * @param i
	 *            celda a recuperar
	 * @return devuelve el dato correspondiente en Int
	 */
	public static final int recoverDataInt(String[] data, int i) {
		if (i < data.length) {
			try {
				return Integer.parseInt(data[i]);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}

	/**
	 * 
	 * Metodo para a traves de los datos del CSV recuperar una celda
	 * 
	 * @param data
	 *            datos del CSV
	 * @param i
	 *            celda a recuperar
	 * @return devuelve el dato correspondiente en Float
	 */
	public static final float recoverDataFloat(String[] data, int i) {
		if (i < data.length) {
			try {
				return Float.parseFloat(data[i]);
			} catch (NumberFormatException e) {
				return 0;
			}
		}
		return 0;
	}

	/**
	 * Metodo para escribir el objeto en un fichero binario usando
	 * RandomAccessFile
	 * 
	 * @param raf
	 *            se le pasa el RandomAccessFile actual
	 * 
	 * @throws IOException
	 *             se lanza cuando hay un error al escribir el alumno en el
	 *             fichero
	 */
	public void writeObject(RandomAccessFile raf) throws IOException {
		int matricula = getMatricula();
		String nombre = formatString(getNombre());
		String asignatura = formatString(getAsignatura());
		float nota = getNota();
		raf.writeInt(matricula);
		raf.writeChars(nombre);
		raf.writeChars(asignatura);
		raf.writeFloat(nota);
	}

	/**
	 * Metodo para redondear un float a un decimal
	 * 
	 * @param n
	 *            valor del float en cuestion
	 * @return devuelve float redondeado
	 */
	private float roundFloat(float n) {
		n = n * 10;
		n = Math.round(n);
		n = n / 10;
		return n;
	}

	/**
	 * Metodo para establecer una longitud fija a un String
	 * 
	 * @param dato
	 *            dato a tratar
	 * @return devuelve el mismo dato con una longitud establecida
	 */
	private static final String formatString(String dato) {
		StringBuilder sb = new StringBuilder(dato);
		sb.setLength(STRING_LENGTH);
		return sb.toString();
	}

	/**
	 * Metodo para escribir un alumno void en un fichero binario
	 * 
	 * @param raf
	 *            se le pasa el RandomAccessFile actual
	 * @throws IOException
	 *             se lanza cuando hay un error al escribir el alumno en el
	 *             fichero
	 */
	public static void writeVoidObject(RandomAccessFile raf) throws IOException {
		Alumno.ALUMNO_VOID.writeObject(raf);
	}

	/**
	 * Metodo para recuperar un alumno dentro de un fichero binario
	 * 
	 * @param raf
	 *            se le pasa el RandomAccessFile actual
	 * @return devuelve el alumno correspondiente
	 * @throws IOException
	 *             se lanza cuando hay un error al escribir el alumno en el
	 *             fichero
	 */
	public static final Alumno readObject(RandomAccessFile raf) throws IOException {
		int matricula = raf.readInt();
		String nombre = readDato(raf);
		String asignatura = readDato(raf);
		float nota = raf.readFloat();
		Alumno bici = new Alumno(matricula, nombre, asignatura, nota);
		return bici;
	}

	/**
	 * Metodo para leer un String de un fichero binario
	 * 
	 * @param raf
	 *            se le pasa el RandomAccessFile actual
	 * @return devuelve el String correspondiente
	 * @throws IOException
	 *             se lanza cuando hay un error al escribir el alumno en el
	 *             fichero
	 */
	private static final String readDato(RandomAccessFile raf) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < STRING_LENGTH; i++) {
			sb.append(raf.readChar());
		}
		return sb.toString().trim();
	}

	/**
	 * Metodo que devuelve el tamaño de un alumno dentro de un fichero binario
	 * 
	 * @return devuelve el tamaño
	 */
	public static int getRegisterSize() {
		return TAM_REGISTRO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + matricula;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + Float.floatToIntBits(nota);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Alumno))
			return false;
		Alumno other = (Alumno) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (matricula != other.matricula)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Float.floatToIntBits(nota) != Float.floatToIntBits(other.nota))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Matrícula: ");
		sb.append(getMatricula());
		sb.append(LS);
		sb.append("Nombre: ");
		sb.append(getNombre());
		sb.append(LS);
		sb.append("Asignatura: ");
		sb.append(getAsignatura());
		sb.append(LS);
		sb.append("Nota: ");
		sb.append(getNota());
		return sb.toString();
	}
}
