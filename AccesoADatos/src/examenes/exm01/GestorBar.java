package examenes.exm01;

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

public class GestorBar {

	private final static String LS = System.getProperty("line.separator");

	/**
	 * fichero fichero binario de alto rendimiento que guarda bares
	 */
	public final static String FICHERO_BINARIO = "./resources/bares.dat";

	/**
	 * fichero fichero de texto plano donde se encuentran los alumnos
	 */

	public final static String FICHERO = "./resources/bares.csv";

	private final static String BAR_NULL = "No se puede guardar bar NULL";
	private final static String BAR_DUPLICADO = "Ya existe un bar con ese ID";
	private final static String FILE_NOT_FOUND = "Fichero no encontrado";
	private final static String IO_EXCEPTION = "Error I/O";
	private final static String READ_WRITE = "rw";
	private final static String READ = "r";

	private LinkedHashMap<Integer, Integer> mapaIds = new LinkedHashMap<Integer, Integer>();
	private LinkedHashMap<Integer, Boolean> mapaObjs = new LinkedHashMap<Integer, Boolean>();

	public GestorBar() {
	}

	/**
	 * Método que guarda en el csv FICHERO una lista de bares
	 * 
	 */
	public void escribirCSVbar(ArrayList<Bar> bares) {
		BufferedWriter writer = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(Bar.getNombresColumnasCSV());
			sb.append(LS);
			for (Bar bar : bares) {
				sb.append(bar.getRegistroCSV());
				sb.append(LS);
			}

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
	 * Método que lee el fichero csv FICHERO y los devuelve en una lista de
	 * objetos Bar
	 * 
	 */
	public ArrayList<Bar> cargarCSVbar() {
		String contenidoCsv = cargarFichero(FICHERO);
		ArrayList<Bar> lista = new ArrayList<Bar>();
		if (contenidoCsv.length() > 0) {
			String[] registros = contenidoCsv.split(LS);
			for (int i = 1; i < registros.length; i++) {
				String[] data = registros[i].split(Bar.getSeparador());
				lista.add(Bar.loadBarFromCsv(data));
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
	 * Método que guarda un bar en la posición de su id
	 * 
	 * @param Bar
	 *            nuevo
	 */
	public boolean guardarBar(Bar bar) {
		boolean ok = false;
		RandomAccessFile raf = null;
		try {
			if (bar == null) {
				throw new Exception(BAR_NULL);
			}
			int id = bar.getId();
			int pos = getPosicion(id);
			if (pos > 0) {
				boolean existBar = existObject(pos);
				if (existBar) {
					throw new Exception(BAR_DUPLICADO);
				}
			}
			raf = new RandomAccessFile(FICHERO_BINARIO, READ_WRITE);
			pos = getFirstPosicionVacia(raf);
			raf.seek(getPosicionBytes(pos));
			bar.writeObject(raf);
			this.mapaIds.put(id, pos);
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
	 * Método que elimina un bar de la posición de su id
	 * 
	 * @param Bar
	 *            a eliminar
	 */
	public boolean eliminarBar(int id) {
		boolean ok = false;
		RandomAccessFile raf = null;
		try {
			int pos = getPosicion(id);
			boolean existBar = existObject(pos);
			if (existBar) {
				raf = new RandomAccessFile(FICHERO_BINARIO, READ_WRITE);
				raf.seek(getPosicionBytes(pos));
				Bar.writeVoidObject(raf);
				this.mapaIds.remove(id);
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

	public Bar[] obtenerBares() {
		ArrayList<Bar> lista = new ArrayList<Bar>();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(FICHERO_BINARIO, READ);
			while (raf.getFilePointer() < raf.length()) {
				if (!isRegistroVacio(raf)) {
					lista.add(Bar.readObject(raf));
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
		return lista.toArray(new Bar[lista.size()]);
	}

	/**
	 * Método que migra los datos del fichero FICHERO csv al FICHERO_BINARIO
	 */
	public void migrateFromCSV() {
		ArrayList<Bar> bares = cargarCSVbar();

		File fichero = new File(FICHERO_BINARIO);
		if (fichero.exists())
			fichero.delete();

		for (Bar bar : bares) {
			guardarBar(bar);
		}
	}

	/**
	 * Método que imprime el contenido del fichero binario con todos los bares
	 */
	public void imprimir() {
		Bar[] bares = obtenerBares();
		for (Bar bar : bares) {
			System.out.println(bar);
		}
	}

	public long getPosicionBytes(int i) {
		return i > 0 ? i * Bar.getRegisterSize() : 0;
	}

	public boolean isRegistroVacio(RandomAccessFile raf) throws IOException {
		int id = raf.readInt();
		raf.seek(raf.getFilePointer() - Integer.BYTES);
		return id == 0;
	}

	public void nextRegistro(RandomAccessFile raf) throws IOException {
		raf.skipBytes(Bar.getRegisterSize());
	}

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

	public int getPosicion(int id) {
		Integer obj = this.mapaIds.get(id);
		return obj == null ? 0 : obj.intValue();
	}

	public boolean existObject(int pos) {
		Boolean obj = this.mapaObjs.get(pos);
		return obj == null ? false : obj.booleanValue();
	}

}
