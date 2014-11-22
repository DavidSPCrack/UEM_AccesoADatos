package ejercicios.act07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ManagerBicicletaRandom implements IADBicicleta {

	private final static String BICI_NULL = "No se puede guardar bicicleta NULL";
	private final static String BICI_DUPLICADA = "Ya existe una bicicleta con ese ID";
	private final static String IO_EXCEPTION = "Error I/O";
	private final static String FILE_NOT_FOUND = "Fichero no encontrado";
	private final static String PATH_FILE = "./resources/bicicletas.dat";
	private final static String READ_WRITE = "rw";
	private final static String READ = "r";

	private LinkedHashMap<Integer, Integer> mapaIds = new LinkedHashMap<Integer, Integer>();
	private LinkedHashMap<Integer, Boolean> mapaObjs = new LinkedHashMap<Integer, Boolean>();

	public int getRandomNumber(int max) {
		SecureRandom sRnd = new SecureRandom();
		return sRnd.nextInt(max);
	}

	public ManagerBicicletaRandom() {
	}

	public long getPosicionBytes(int i) {
		return i > 0 ? i * Bicicleta.getRegisterSize() : 0;
	}

	public void dummy() {
		ArrayList<Bicicleta> bicicletas = new ArrayList<Bicicleta>();
		for (int i = 0; i < 100; i++) {
			int id = i + 1;
			bicicletas.add(new Bicicleta(id, getRandomNumber(2) == 1, "02/03/2014", getRandomNumber(100) + 1));
		}
		File file = new File(PATH_FILE);
		if (file.exists()) {
			file.delete();
		}
		for (Bicicleta bicicleta : bicicletas) {
			guardarBici(bicicleta);
		}
	}

	public boolean guardarBici(Bicicleta bici) {
		boolean ok = false;
		RandomAccessFile raf = null;
		try {
			if (bici == null) {
				throw new Exception(BICI_NULL);
			}
			int id = bici.getId();
			int pos = getPosicion(id);
			if (pos > 0) {
				boolean existBici = existObject(pos);
				if (existBici) {
					throw new Exception(BICI_DUPLICADA);
				}
			}
			raf = new RandomAccessFile(PATH_FILE, READ_WRITE);
			pos = getFirstPosicionVacia(raf);
			raf.seek(getPosicionBytes(pos));
			bici.writeObject(raf);
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

	public boolean modificarBici(Bicicleta bici) {
		boolean ok = false;
		RandomAccessFile raf = null;
		try {
			int id = bici.getId();
			int pos = getPosicion(id);
			boolean existBici = existObject(pos);
			if (existBici) {
				raf = new RandomAccessFile(PATH_FILE, READ);
				raf.seek(getPosicionBytes(pos));
				bici.writeObject(raf);
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

	public boolean eliminarBici(Bicicleta bici) {
		boolean ok = false;
		RandomAccessFile raf = null;
		try {
			int id = bici.getId();
			int pos = getPosicion(id);
			boolean existBici = existObject(pos);
			if (existBici) {
				raf = new RandomAccessFile(PATH_FILE, READ);
				raf.seek(getPosicionBytes(pos));
				Bicicleta.writeVoidObject(raf);
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

	public Bicicleta obtenerBici(int id) {
		RandomAccessFile raf = null;
		Bicicleta bici = null;
		try {
			int pos = getPosicion(id);
			boolean existBici = existObject(pos);
			if (existBici) {
				raf = new RandomAccessFile(PATH_FILE, READ);
				raf.seek(getPosicionBytes(pos));
				bici = Bicicleta.readObject(raf);
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
		return bici;
	}

	public Bicicleta[] obtenerBicis() {
		ArrayList<Bicicleta> lista = new ArrayList<Bicicleta>();
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(PATH_FILE, READ);
			while (raf.getFilePointer() < raf.length()) {
				if (!isRegistroVacio(raf)) {
					lista.add(Bicicleta.readObject(raf));
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
		return lista.toArray(new Bicicleta[lista.size()]);
	}

	public boolean isRegistroVacio(RandomAccessFile raf) throws IOException {
		int id = raf.readInt();
		raf.seek(raf.getFilePointer() - Integer.BYTES);
		return id == 0;
	}

	public void nextRegistro(RandomAccessFile raf) throws IOException {
		raf.skipBytes(Bicicleta.getRegisterSize());
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
