package framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import framework.ErrorGeneral;
import framework.util.interfaces.NamingStrategy;

/**
 * @author David
 */
public class FicheroUtil {

	private final static char[] carsSaltoLinea = { 0x0D, 0x0A };
	public final static String saltoLinea = new String(carsSaltoLinea);
	private final static char[] carsSaltoLinea2 = { 0x0A };
	public final static String saltoLinea2 = new String(carsSaltoLinea2);

	public static void saveFile(File file, String datos) throws ErrorGeneral {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write(datos.getBytes());
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e1) {
			throw new ErrorGeneral(e1);
		}
	}

	public static void saveFile(File file, byte[] datos) throws ErrorGeneral {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write(datos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e1) {
			throw new ErrorGeneral(e1);
		}
	}

	public static void saveFile(File file, StringBuilder datos) throws ErrorGeneral {
		FileWriter grabador;
		try {
			grabador = new FileWriter(file);
			grabador.write(datos.toString());
			grabador.close();
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static void saveFile(File file, String[] datos) throws ErrorGeneral {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			for (int i = 0; i < datos.length; i++) {
				fos.write(datos[i].getBytes());
				fos.write(saltoLinea.getBytes());
			}
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e1) {
			throw new ErrorGeneral(e1);
		}
	}

	/*
	 * 
	 * Devuelve el File de un directorio, si no existe lo crea. ¡OJO! Hay que
	 * 
	 * enviar el path de un directorio, no el de un archivo.
	 */

	public static File getFileDirectorio(String pathDirectorio) {
		File filedir = new File(pathDirectorio);
		if (!filedir.exists())
			filedir.mkdirs();
		return filedir;
	}

	public static FileWriter crearFichero(File file) throws ErrorGeneral {
		try {
			return new FileWriter(file);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static FileWriter abrirFichero(File file) throws ErrorGeneral {
		try {
			return new FileWriter(file, true);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static FileWriter escribirFichero(FileWriter writer, String[] regs) throws ErrorGeneral {
		return escribirFichero(writer, regs, true);
	}

	public static FileWriter escribirFichero(FileWriter writer, String[] regs, boolean swSaltoDLinea) throws ErrorGeneral {
		try {
			for (int i = 0; i < regs.length; i++) {
				writer.write(regs[i]);
				if (swSaltoDLinea == true)
					writer.write(saltoLinea);
			}
			writer.flush();
			return writer;
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static void writeLine(FileWriter writer, String reg) throws ErrorGeneral {
		writeLine(writer, reg, true);
	}

	public static void writeLine(FileWriter writer, String reg, boolean bSaltoLinea) throws ErrorGeneral {
		try {
			writer.write(reg);
			if (bSaltoLinea)
				writer.write(saltoLinea);
			writer.flush();
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static void crearFichero(File file, String reg) throws ErrorGeneral {
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(reg);
			writer.close();
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static File crearFichero(String directorio, String fileName, String[] regs) throws ErrorGeneral {
		File file = new File(directorio, fileName);
		crearFichero(file, regs);
		return file;
	}

	public static File crearFichero(String directorio, String fileName, String regs) throws ErrorGeneral {
		File file = new File(directorio, fileName);
		crearFichero(file, regs);
		return file;
	}

	public static void crearFichero(File file, String[] regs) throws ErrorGeneral {
		FileWriter writer = crearFichero(file);
		escribirFichero(writer, regs);
		cerrarFichero(writer);
	}

	public static void cerrarFichero(FileWriter writer) throws ErrorGeneral {
		try {
			if (writer != null)
				writer.close();
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static void reEscribirFichero(File file, String datos, int posicion) throws ErrorGeneral {
		reEscribirFichero(file, datos.getBytes(), posicion);
	}

	/**
	 * 
	 * @param posicion
	 * 
	 *            - lugar en el que se empiza a reescribir el fichero
	 */

	public static void reEscribirFichero(File file, byte[] datos, int posicion) throws ErrorGeneral {
		try {
			RandomAccessFile acces = new RandomAccessFile(file, "rw");
			acces.skipBytes(posicion);
			acces.write(datos);
			acces.close();
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static String[] getLeerRegistrosFichero(String path) throws ErrorGeneral {
		return getLeerRegistrosFichero(new File(path));
	}

	public static String[] getLeerRegistrosFichero(File file) throws ErrorGeneral {
		try {
			ArrayList<String> lista = new ArrayList<String>();
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String reg = reader.readLine();
			while (reg != null) {
				lista.add(reg);
				reg = reader.readLine();
			}
			reader.close();
			return lista.toArray(new String[lista.size()]);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static String[] getLeerRegistrosFichero(File file, int reglength) throws ErrorGeneral {
		try {
			ArrayList<String> lista = new ArrayList<String>();
			char[] buffer = new char[reglength];
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			int flag = reader.read(buffer, 0, reglength);
			while (flag == reglength) {
				String reg = new String(buffer);
				lista.add(reg);
				flag = reader.read(buffer, 0, reglength);
			}
			reader.close();
			return lista.toArray(new String[lista.size()]);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static String[] getLeerRegistrosFichero(File file, int reglength, boolean swLeeTodo) throws ErrorGeneral {
		try {
			ArrayList<String> lista = new ArrayList<String>();
			char[] buffer = new char[reglength];
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			int flag = reader.read(buffer, 0, reglength);
			while (flag == reglength) {
				String reg = new String(buffer);
				lista.add(reg);
				flag = reader.read(buffer, 0, reglength);
			}
			if (swLeeTodo) {
				if (flag != -1 && flag < reglength) {
					String reg = new String(buffer);
					lista.add(reg);
				}
			}
			reader.close();
			return lista.toArray(new String[lista.size()]);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static String[] getFicherosDirectorio(String directorio_carga) {
		File directorio = getFileDirectorio(directorio_carga);
		File ficheros[] = directorio.listFiles();
		String[] lista = new String[ficheros.length];
		for (int i = 0; i < ficheros.length; i++) {
			lista[i] = ficheros[i].getPath();
		}
		return lista;
	}

	public static File getFicheroDirectorio(String directorio_carga, String fileName) {
		File ficheros[] = getFilesDirectorio(directorio_carga);
		for (int i = 0; i < ficheros.length; i++) {
			if (ficheros[i].getName().equals(fileName))
				return ficheros[i];
		}
		return null;
	}

	/** Mueve un fichero de un lugar a otro */

	public static void moverFichero(File origen, File destino) throws ErrorGeneral {
		copiarFichero(origen, destino);
		origen.delete();
	}

	public static void moverFichero(File origen, String pathDestino) throws ErrorGeneral {
		File fileDir = getFileDirectorio(pathDestino);
		File destino = new File(fileDir, origen.getName());
		moverFichero(origen, destino);
	}

	/** Copiar un fichero de un sitio a otro */

	public static void copiarFichero(File origen, File destino) throws ErrorGeneral {
		String[] registros = getLeerRegistrosFichero(origen);
		FileWriter writer = escribirFichero(crearFichero(destino), registros);
		cerrarFichero(writer);
	}

	public static boolean moverFicherosDirectorio(String directorioOrigen, String directorioDestino, NamingStrategy namingStrategy, boolean continuarSiHayError)
			throws ErrorGeneral {
		boolean success = true;
		File[] files = FicheroUtil.getFilesDirectorio(directorioOrigen);
		for (File file : files) {
			String fileDestinoName = directorioDestino + "//" + namingStrategy.getName(file.getName());
			try {
				FicheroUtil.moverFichero(file, new File(fileDestinoName));
			} catch (ErrorGeneral exception) {
				success = false;
				if (!continuarSiHayError) {
					throw new ErrorGeneral(exception);
				}
			}
		}
		return success;
	}

	public static boolean moverFicherosDirectorio(String directorioOrigen, String directorioDestin) throws ErrorGeneral {
		return moverFicherosDirectorio(directorioOrigen, directorioDestin, new DefaultNamingStrategy(), false);
	}

	public static void copiarFicheroBinario(File origen, File destino) throws ErrorGeneral {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(origen);
			fos = new FileOutputStream(destino);
			byte[] buffer = new byte[8192];
			int caracteres = fis.read(buffer);
			while (caracteres > -1) {
				fos.write(buffer, 0, caracteres);
				caracteres = fis.read(buffer);
			}
		} catch (IOException io) {
			throw new ErrorGeneral(io);
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				throw new ErrorGeneral(e);
			}
		}
	}

	/**
	 * 
	 * The method try to close safely an object that implementes Closable
	 * interface. If an error occurs, the method will NOT throw any exception,
	 * but will
	 * 
	 * return false
	 * 
	 * 
	 * 
	 * @param closeable
	 * 
	 * @return False if fail, otherwise return true.
	 */

	public static boolean close(java.io.Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException ex) {
				return false;
			}
		}
		return true;
	}

	/*
	 * 
	 * Devuelve un array de nombres de fichero sin el path.
	 */

	public static String[] getNames(File[] files) {
		String[] lista = new String[files.length];
		for (int i = 0; i < lista.length; i++) {
			lista[i] = files[i].getName();
		}
		return lista;
	}

	/*
	 * 
	 * Parte en dos trozos el nombre de un fichero. Primer trozo: Nombre sin
	 * 
	 * extensión. Segundo trozo: Extensión.
	 */

	public static String[] split(String fileName) {
		StringBuffer nombre = new StringBuffer();
		StringBuffer extension = new StringBuffer();
		boolean ext = false;
		char[] cars = fileName.toCharArray();
		for (int i = 0; i < cars.length; i++) {
			if (cars[i] == '.') {
				if (ext) {
					nombre.append('.');
					nombre.append(extension.toString());
					extension = new StringBuffer();
				} else {
					ext = true;
				}
			} else {
				if (ext) {
					extension.append(cars[i]);
				} else {
					nombre.append(cars[i]);
				}
			}
		}
		String[] split = new String[2];
		split[0] = nombre.toString();
		split[1] = extension.toString();
		return split;
	}

	public static File[] getFilesDirectorio(String nombreDirectorio) {
		File directorio = new File(nombreDirectorio);
		File[] files = directorio.listFiles();
		ArrayList<File> filtro = new ArrayList<File>();
		if (files != null)
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					filtro.add(files[i]);
				}
			}
		return filtro.toArray(new File[filtro.size()]);
	}

	public static StringBuilder getFicheroCompleto(File file) throws ErrorGeneral {
		int longitud = (int) file.length();
		StringBuilder contenido = new StringBuilder(longitud);
		FileReader lector = null;
		try {
			lector = new FileReader(file);
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		}
		for (int aux = 0; aux < longitud; aux++) {
			try {
				contenido.append((char) lector.read());
				lector.close();
			} catch (IOException e) {
				throw new ErrorGeneral(e);
			}
		}
		return contenido;
	}

	public static String getTextoFichero(File file) throws ErrorGeneral {
		int sizeBuffer = 8192;
		FileInputStream is;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		}
		byte[] saco = new byte[0], aux = null;
		byte[] bytes = new byte[sizeBuffer];
		try {
			int length = 0;
			int car = is.read(bytes);
			while (car > 0) {
				aux = saco;
				length = length + car;
				saco = new byte[length];
				System.arraycopy(aux, 0, saco, 0, aux.length);
				System.arraycopy(bytes, 0, saco, aux.length, car);
				car = is.read(bytes);
			}
		} catch (IOException io) {
			throw new ErrorGeneral(io);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return new String(saco);
	}

	public static String getTextoIS(InputStream is) throws ErrorGeneral {
		int sizeBuffer = 8192;
		byte[] saco = new byte[0], aux = null;
		byte[] bytes = new byte[sizeBuffer];
		try {
			int length = 0;
			int car = is.read(bytes);
			while (car > 0) {
				aux = saco;
				length = length + car;
				saco = new byte[length];
				System.arraycopy(aux, 0, saco, 0, aux.length);
				System.arraycopy(bytes, 0, saco, aux.length, car);
				car = is.read(bytes);
			}
		} catch (IOException io) {
			throw new ErrorGeneral(io);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return new String(saco);
	}

	public static byte[] getBytesIS(InputStream is) throws ErrorGeneral {
		int sizeBuffer = 8192;
		byte[] saco = new byte[0], aux = null;
		byte[] bytes = new byte[sizeBuffer];
		try {
			int length = 0;
			int car = is.read(bytes);
			while (car > 0) {
				aux = saco;
				length = length + car;
				saco = new byte[length];
				System.arraycopy(aux, 0, saco, 0, aux.length);
				System.arraycopy(bytes, 0, saco, aux.length, car);
				car = is.read(bytes);
			}
		} catch (IOException io) {
			throw new ErrorGeneral(io);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return saco;
	}

	public static File crearFichero(String path, String fileName, byte[] datos) throws ErrorGeneral {
		File directorio = getFileDirectorio(path);
		File file = new File(directorio, fileName);
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(file);
			writer.write(datos);
			writer.close();
			return file;
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static File crearFichero(File file, byte[] datos) throws ErrorGeneral {
		FileOutputStream writer;
		try {
			writer = new FileOutputStream(file);
			writer.write(datos);
			writer.close();
			return file;
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static byte[] getBytesFile(File file) throws ErrorGeneral {
		FileInputStream dis = null;
		try {
			dis = new FileInputStream(file);
			byte[] saco = new byte[0], aux = null;
			byte[] bytes = new byte[8192];
			int length = 0;
			int car = dis.read(bytes);
			while (car > 0) {
				aux = saco;
				length = length + car;
				saco = new byte[length];
				System.arraycopy(aux, 0, saco, 0, aux.length);
				System.arraycopy(bytes, 0, saco, aux.length, car);
				car = dis.read(bytes);
			}
			return saco;
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		} catch (IOException io) {
			throw new ErrorGeneral(io);
		} finally {
			try {
				dis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String reeplazarBackSlash(String ruta) {
		try {
			return ruta.replaceAll("\\\\", "/");
		} catch (Exception exc) {
			return ruta;
		}
	}

	public static boolean isMismoContenido(File file1, File file2) throws ErrorGeneral {
		return getCRC32(file1) == getCRC32(file2);
	}

	public static boolean isMismoContenido(InputStream is, File file) throws ErrorGeneral {
		return getCRC32(is) == getCRC32(file);
	}

	public static boolean isMismoContenido(InputStream is1, InputStream is2) throws ErrorGeneral {
		return getCRC32(is1) == getCRC32(is2);
	}

	public static long getCRC32(File file) throws ErrorGeneral {
		try {
			return getCRC32(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static long getCRC32(InputStream is) throws ErrorGeneral {
		try {
			CheckedInputStream cis = new CheckedInputStream(is, new CRC32());
			byte[] buf = new byte[128];
			while (cis.read(buf) >= 0) {
			}
			long checksum = cis.getChecksum().getValue();
			return checksum;
		} catch (IOException e) {
			throw new ErrorGeneral(e);
		}
	}

	public static String getFormatoFichero(File file) {
		int index = file.getName().lastIndexOf(".");
		return file.getName().substring(index + 1).toUpperCase();
	}

	public static void borrarFichero(File file) throws ErrorGeneral {
		try {
			file.delete();
		} catch (Exception e) {
			throw new ErrorGeneral(e);
		}
	}

	public static void borrarFicheros(List<File> files) throws ErrorGeneral {
		if (files == null)
			return;
		for (File file : files) {
			file.delete();
		}
	}

	/**
	 * 
	 * Extract the file name without the extension
	 * 
	 * 
	 * 
	 * @param fullFileName
	 * 
	 * @return
	 */

	public static String extractFileNameWithoutExtension(String fullFileName) {
		String shortFileName = extractFileName(fullFileName);
		int index = shortFileName.lastIndexOf('.');
		return shortFileName.substring(0, index);
	}

	/**
	 * 
	 * Extract file name from the total file name
	 * 
	 * 
	 * 
	 * @param fullFileName
	 * 
	 * @return
	 */

	public static String extractFileName(String fullFileName) {
		File file = new File(fullFileName);
		return file.getName();
	}

}