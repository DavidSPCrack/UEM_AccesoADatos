package ejercicios.act07;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ManagerBicicleta implements IADBicicleta {

	private final static String BICI_NULL = "No se puede guardar bicicleta NULL";
	private final static String BICI_DUPLICADA = "Ya existe una bicicleta con ese ID";
	private final static String IO_EXCEPTION = "Error I/O";
	private final static String CLASS_NOT_FOUND_EXCEPTION = "Clase no encontrada";
	private final static String PATH_FILE = "./resources/bicicletas.dat";

	public ManagerBicicleta() {
	}

	public void dummy() {
		Bicicleta[] bicisOld = obtenerBicis();
		for (Bicicleta bicicleta : bicisOld) {
			eliminarBici(bicicleta);
		}
		ArrayList<Bicicleta> bicicletas = new ArrayList<Bicicleta>();
		bicicletas.add(new Bicicleta(1, true, "02/03/2014", 10));
		bicicletas.add(new Bicicleta(2, true, "02/03/2014", 6));
		bicicletas.add(new Bicicleta(3, true, "02/03/2014", 8));
		bicicletas.add(new Bicicleta(4, true, "07/05/2014", 11));
		bicicletas.add(new Bicicleta(5, true, "01/02/2014", 13));
		bicicletas.add(new Bicicleta(6, true, "04/08/2014", 9));
		bicicletas.add(new Bicicleta(7, true, "14/12/2013", 10));
		bicicletas.add(new Bicicleta(8, true, "11/11/2013", 7));
		bicicletas.add(new Bicicleta(9, true, "18/06/2014", 4));
		bicicletas.add(new Bicicleta(10, true, "19/09/2014", 3));

		guardarBicis(bicicletas.toArray(new Bicicleta[bicicletas.size()]));
	}

	private boolean guardarBicis(Bicicleta[] bicis) {
		boolean ok = false;
		ObjectOutputStream streamOut = null;
		try {
			streamOut = new ObjectOutputStream(new FileOutputStream(PATH_FILE));
			for (Bicicleta bicicleta : bicis) {
				streamOut.writeObject(bicicleta);
			}
			ok = true;
		} catch (IOException e) {
			System.err.println(IO_EXCEPTION);
		} finally {
			if (streamOut != null) {
				try {
					streamOut.close();
				} catch (IOException e) {

				}
			}
		}
		return ok;
	}

	@Override
	public boolean guardarBici(Bicicleta bici) {
		if (bici == null) {
			System.err.println(BICI_NULL);
			return false;
		}
		Bicicleta[] bicicletasOriginal = obtenerBicis();
		for (Bicicleta bicicleta : bicicletasOriginal) {
			if (bici.getId() == bicicleta.getId()) {
				System.err.println(BICI_DUPLICADA);
				return false;
			}
		}
		Bicicleta[] bicicletas = Arrays.copyOf(bicicletasOriginal, bicicletasOriginal.length + 1);
		bicicletas[bicicletas.length - 1] = bici;
		return guardarBicis(bicicletas);
	}

	@Override
	public boolean modificarBici(Bicicleta bici) {
		if (bici == null) {
			return false;
		}
		Bicicleta[] bicicletas = obtenerBicis();
		for (int i = 0; i < bicicletas.length; i++) {
			if (bici.getId() == bicicletas[i].getId()) {
				bicicletas[i] = bici;
			}
		}
		return guardarBicis(bicicletas);
	}

	@Override
	public boolean eliminarBici(Bicicleta bici) {
		if (bici == null) {
			return false;
		}
		Bicicleta[] bicicletas = obtenerBicis();
		ArrayList<Bicicleta> lista = new ArrayList<Bicicleta>();
		for (Bicicleta bicicleta : bicicletas) {
			if (bici.getId() != bicicleta.getId()) {
				lista.add(bicicleta);
			}
		}
		return guardarBicis(lista.toArray(new Bicicleta[lista.size()]));
	}

	@Override
	public Bicicleta obtenerBici(int id) {
		Bicicleta[] bicicletas = obtenerBicis();
		for (Bicicleta bicicleta : bicicletas) {
			if (id == bicicleta.getId()) {
				return bicicleta;
			}
		}
		return null;
	}

	@Override
	public Bicicleta[] obtenerBicis() {
		ArrayList<Bicicleta> bicicletas = new ArrayList<Bicicleta>();

		ObjectInputStream streamIn = null;
		try {
			streamIn = new ObjectInputStream(new FileInputStream(PATH_FILE));

			Object objRead = null;
			while ((objRead = streamIn.readObject()) != null) {
				if (objRead instanceof Bicicleta) {
					Bicicleta bicicleta = (Bicicleta) objRead;
					bicicletas.add(bicicleta);
				}
			}
		} catch (EOFException e) {
		} catch (IOException e) {
			System.err.println(IO_EXCEPTION);
		} catch (ClassNotFoundException e) {
			System.err.println(CLASS_NOT_FOUND_EXCEPTION);
		} finally {
			if (streamIn != null) {
				try {
					streamIn.close();
				} catch (IOException e) {

				}
			}
		}
		return bicicletas.toArray(new Bicicleta[bicicletas.size()]);
	}

}
