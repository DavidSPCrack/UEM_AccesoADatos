package examenes.exm01;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Bar {

	private final static String SEPARATOR = ";";

	private final static String NAME = "NAME";
	private final static String ADDRESS = "ADDRESS";
	private final static String TYPE = "TYPE";
	private final static String ID = "ID";

	private final static Bar BAR_VOID = new Bar();

	private final static int STRING_LENGTH = 20;

	private final static int TAM_REGISTRO = ((STRING_LENGTH * 2) * 3) + Integer.BYTES;

	private String name;
	private String address;
	private String type;
	private int id;

	public Bar(String name, String address, String type) {
		this.name = name;
		this.address = address;
		this.type = type;
	}

	public Bar(String name, String address, String type, int id) {
		this(name, address, type);
		this.id = id;
	}

	public Bar() {
		this("", "", "", 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistroCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName());
		sb.append(getSeparador());
		sb.append(getAddress());
		sb.append(getSeparador());
		sb.append(getType());
		sb.append(getSeparador());
		sb.append(getId());
		return sb.toString();
	}

	public static final String getNombresColumnasCSV() {
		StringBuilder sb = new StringBuilder();
		sb.append(NAME);
		sb.append(getSeparador());
		sb.append(ADDRESS);
		sb.append(getSeparador());
		sb.append(TYPE);
		sb.append(getSeparador());
		sb.append(ID);
		return sb.toString();
	}

	public static final String getSeparador() {
		return SEPARATOR;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Bar other = (Bar) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bar [name=" + name + ", address=" + address + ", type=" + type + ", id=" + id + "]";
	}

	public static final Bar loadBarFromCsv(String[] data) {
		String name = recoverData(data, 0);
		String address = recoverData(data, 1);
		String type = recoverData(data, 2);
		int id = recoverDataInt(data, 3);
		Bar bar = new Bar(name, address, type, id);
		return bar;
	}

	public static final String recoverData(String[] data, int i) {
		if (i < data.length) {
			return data[i];
		}
		return "";
	}

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

	public void writeObject(RandomAccessFile raf) throws IOException {
		int id = getId();
		String name = formatString(getName());
		String address = formatString(getAddress());
		String type = formatString(getType());
		raf.writeInt(id);
		raf.writeChars(name);
		raf.writeChars(address);
		raf.writeChars(type);
	}

	private static final String formatString(String dato) {
		StringBuilder sb = new StringBuilder(dato);
		sb.setLength(STRING_LENGTH);
		return sb.toString();
	}

	public static void writeVoidObject(RandomAccessFile raf) throws IOException {
		Bar.BAR_VOID.writeObject(raf);
	}

	public static final Bar readObject(RandomAccessFile raf) throws IOException {
		int id = raf.readInt();
		String name = readDato(raf);
		String address = readDato(raf);
		String type = readDato(raf);
		Bar bici = new Bar(name, address, type, id);
		return bici;
	}

	private static final String readDato(RandomAccessFile raf) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < STRING_LENGTH; i++) {
			sb.append(raf.readChar());
		}
		return sb.toString().trim();
	}

	public static int getRegisterSize() {
		return TAM_REGISTRO;
	}
}
