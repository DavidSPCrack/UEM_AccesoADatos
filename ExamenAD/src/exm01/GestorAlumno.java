package exm01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GestorAlumno {

	private final static String LS = System.getProperty("line.separator");

	/**
	 * fichero fichero binario de alto rendimiento que guarda alumnos
	 */
	public final static String FICHERO_BINARIO = "./res/alumnos.dat";

	/**
	 * fichero fichero de texto plano donde se encuentran los alumnos
	 */
	public final static String FICHERO = "./res/alumnos.csv";

	private final static String ALUMNO_NULL = "No se puede guardar alumno NULL";
	private final static String ALUMNO_DUPLICADO = "Ya existe un alumno con ese ID";
	private final static String FILE_NOT_FOUND = "Fichero no encontrado";
	private final static String IO_EXCEPTION = "Error I/O";
	private final static String READ_WRITE = "rw";
	private final static String READ = "r";

	private LinkedHashMap<Integer, Integer> mapaIds = new LinkedHashMap<Integer, Integer>();
	private LinkedHashMap<Integer, Boolean> mapaObjs = new LinkedHashMap<Integer, Boolean>();

	public GestorAlumno() {
	}

	public void generarCabecera() {
		BufferedWriter writer = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(Alumno.getNombresColumnasCSV());

			File fichero = new File(FICHERO);

			if (fichero.exists()) {
				fichero.delete();
			}

			FileWriter fW = new FileWriter(fichero);
			writer = new BufferedWriter(fW);
			writer.write(sb.toString());
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Método que no sobreescribe el fichero y guarda el alumno al final del
	 * fichero csv
	 * 
	 * @param matricula
	 *            indica la matricula del alumno a guardar
	 * @param nombre
	 *            indica el nombre del alumno a guardar
	 * @param asignatura
	 *            indica la asignatura del alumno a guardar
	 * @param nota
	 *            indica la nota del alumno a guardar
	 */
	public void guardarAlumnoCSV(int matricula, String nombre, String asignatura, float nota) {
		BufferedWriter writer = null;
		try {
			Alumno alumno = new Alumno(matricula, nombre, asignatura, nota);
			StringBuilder sb = new StringBuilder();
			sb.append(LS);
			sb.append(alumno.getRegistroCSV());

			File fichero = new File(FICHERO);

			FileWriter fW = new FileWriter(fichero, true);

			writer = new BufferedWriter(fW);
			writer.write(sb.toString());
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Método que lee el fichero csv FICHERO y los devuelve en una lista de
	 * objetos Alumno
	 * 
	 * @return devuelve ArrayList con todos los alumnos del CSV
	 * 
	 */
	public ArrayList<Alumno> obtenerAlumnosCSV() {
		String contenidoCsv = cargarFichero(FICHERO);
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		if (contenidoCsv.length() > 0) {
			String[] registros = contenidoCsv.split(LS);
			for (int i = 1; i < registros.length; i++) {
				String[] data = registros[i].split(Alumno.getSeparador());
				lista.add(Alumno.loadAlumnoFromCsv(data));
			}
		}
		return lista;
	}

	public static String cargarFichero(String path) {
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			FileReader fR = new FileReader(path);
			reader = new BufferedReader(fR);
			String line = null;

			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append(LS);
			}
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (Exception e) {
			}
		}
		return sb.toString();
	}

	/**
	 * Método que guarda un alumno en un fichero binario de alto rendimiento
	 * 
	 * @param alumno
	 *            alumno que se desee guardar
	 * @return devuelve true si ha añadido el alumno
	 */
	public boolean guardarAlumno(Alumno alumno) {
		boolean ok = false;
		RandomAccessFile raf = null;
		try {
			if (alumno == null) {
				throw new Exception(ALUMNO_NULL);
			}
			int matricula = alumno.getMatricula();
			int pos = getPosicion(matricula);
			if (pos > 0) {
				boolean existAlumno = existObject(pos);
				if (existAlumno) {
					throw new Exception(ALUMNO_DUPLICADO);
				}
			}
			raf = new RandomAccessFile(FICHERO_BINARIO, READ_WRITE);
			pos = getFirstPosicionVacia(raf);
			raf.seek(getPosicionBytes(pos));
			alumno.writeObject(raf);
			this.mapaIds.put(matricula, pos);
			this.mapaObjs.put(pos, true);
			ok = true;
		} catch (FileNotFoundException e) {
			System.err.println(FILE_NOT_FOUND);
		} catch (IOException e) {
			System.err.println(IO_EXCEPTION);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					System.err.println(IO_EXCEPTION);
				}
			}
		}
		return ok;
	}

	/**
	 * Método que elimina un alumno de la posición de su matricula
	 * 
	 * @param matricula
	 *            indica la matricula del alumno que se desee eliminar
	 * 
	 * @return devuelve true si se ha eliminado correctamente
	 */
	public boolean eliminarAlumno(int matricula) {
		boolean ok = false;
		RandomAccessFile raf = null;
		try {
			int pos = getPosicion(matricula);
			boolean existAlumno = existObject(pos);
			if (existAlumno) {
				raf = new RandomAccessFile(FICHERO_BINARIO, READ_WRITE);
				raf.seek(getPosicionBytes(pos));
				Alumno.writeVoidObject(raf);
				this.mapaIds.remove(matricula);
				this.mapaObjs.remove(pos);
			}
			ok = true;
		} catch (FileNotFoundException e) {
			System.err.println(FILE_NOT_FOUND);
		} catch (IOException e) {
			System.err.println(IO_EXCEPTION);
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					System.err.println(IO_EXCEPTION);
				}
			}
		}
		return ok;
	}

	/**
	 * Devuelve todos los alumnos guardados en el fichero binario
	 * 
	 * @return devuelve un array con los alumnos
	 */
	public Alumno[] obtenerAlumnos() {
		ArrayList<Alumno> lista = new ArrayList<Alumno>();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(FICHERO_BINARIO, READ);
			while (raf.getFilePointer() < raf.length()) {
				if (!isRegistroVacio(raf)) {
					lista.add(Alumno.readObject(raf));
				} else {
					nextRegistro(raf);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println(FILE_NOT_FOUND);
		} catch (IOException e) {
			System.err.println(IO_EXCEPTION);
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					System.err.println(IO_EXCEPTION);
				}
			}
		}
		return lista.toArray(new Alumno[lista.size()]);
	}

	/**
	 * Método que migra los datos del fichero FICHERO csv al FICHERO_BINARIO
	 * 
	 * @return devuelve true si lo ha migrado con éxtito
	 */
	public boolean migrateFromCSV() {
		ArrayList<Alumno> alumnos = obtenerAlumnosCSV();

		File fichero = new File(FICHERO_BINARIO);
		if (fichero.exists())
			fichero.delete();

		for (Alumno alumno : alumnos) {
			guardarAlumno(alumno);
		}
		return true;
	}

	/**
	 * Método que imprime el contenido del fichero binario de todos los alumnos
	 */
	public void imprimir() {
		Alumno[] alumnos = obtenerAlumnos();
		for (Alumno alumno : alumnos) {
			System.out.println(alumno);
			System.out.println();
		}
	}

	/**
	 * Obtiene posición en bytes por un id
	 * 
	 * @param i
	 *            id por el cual se obtendra la posición
	 * @return devuelve posición en bytes
	 */
	public long getPosicionBytes(int i) {
		return i > 0 ? i * Alumno.getRegisterSize() : 0;
	}

	/**
	 * Metodo para comprobar si un registro esta vacio
	 * 
	 * @param raf
	 *            se le pasa el RandomAccessFile actual
	 * @return devuelve true si el registro a leer esta vacio
	 * @throws IOException
	 *             si ocurre un error al leer
	 */
	public boolean isRegistroVacio(RandomAccessFile raf) throws IOException {
		int id = raf.readInt();
		raf.seek(raf.getFilePointer() - Integer.BYTES);
		return id == 0;
	}

	/**
	 * Metodo para avanzar al siguiente registro de Alumno dentro del
	 * RandomAccessFile
	 * 
	 * @param raf
	 *            se le pasa el RandomAccessFile actual
	 * @throws IOException
	 *             si ocurre un error al leer
	 */
	public void nextRegistro(RandomAccessFile raf) throws IOException {
		raf.skipBytes(Alumno.getRegisterSize());
	}

	/**
	 * Metodo para obtener la primera posición vacia dentro del fichero binario
	 * 
	 * @param raf
	 *            se le pasa el RandomAccessFile actual
	 * @return devuelve la primera posición vacia del fichero binario
	 */
	public int getFirstPosicionVacia(RandomAccessFile raf) {
		int pos = 0;
		boolean isGap = false;
		while (!isGap) {
			isGap = !existObject(pos);
			if (!isGap) {
				pos++;
			}
		}
		return pos;
	}

	/**
	 * Metodo para obtener la posición de un elemento por su id
	 * 
	 * @param id
	 *            se le pasa el id del alumno
	 * @return devuelve la posición del alumno dentro del fichero binario
	 */
	public int getPosicion(int id) {
		Integer obj = this.mapaIds.get(id);
		return obj == null ? 0 : obj.intValue();
	}

	/**
	 * Metodo para saber si existe un alumno en una posición
	 * 
	 * @param pos
	 *            se le pasa la posición del alumno
	 * @return devuelve true si existe alumno en dicha posición y false si no
	 *         existe
	 */
	public boolean existObject(int pos) {
		Boolean obj = this.mapaObjs.get(pos);
		return obj == null ? false : obj.booleanValue();
	}

}
