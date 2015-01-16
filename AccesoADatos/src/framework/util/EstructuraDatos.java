package framework.util;

import java.util.ArrayList;
import java.util.HashMap;

import framework.DataQRY;
import framework.ErrorGeneral;

public class EstructuraDatos {

	private String nombreEstructura;
	private int identacion = 0;
	private HashMap<String, Object> datos;

	public EstructuraDatos(String nombreEstructura) {
		this.nombreEstructura = nombreEstructura;
		datos = new HashMap<String, Object>();
	}

	public void add(String key, Object dato) {
		datos.put(key, dato);
	}

	public void add(String key, String dato) {
		datos.put(key, dato == null ? "" : dato);
	}

	public void add(String key, long dato) {
		add(key, Long.toString(dato));
	}

	public void add(String key, int dato) {
		add(key, Integer.toString(dato));
	}

	public void add(String key, double dato) {
		add(key, Double.toString(dato));
	}

	public void add(String key, boolean dato) {
		add(key, dato ? "S" : "N");
	}

	public void add(String key, EstructuraDatos dato) {
		datos.put(key, dato);
	}

	public void remove(String key) {
		datos.remove(key);
	}

	public void vaciar() {
		datos.clear();
	}

	public String getString(String key) {
		Object aux = getObject(key);
		return aux == null ? "" : aux.toString();
	}

	public String getString0ToSpace(String key) {
		String aux = getString(key);
		if (aux.equals("0"))
			return "";
		return aux;
	}

	public Fecha getFecha(String key) {
		Object aux = getObject(key);
		return aux == null ? new Fecha(1900, 1, 1) : new Fecha(aux.toString());
	}

	public long getLong(String key) {
		Object aux = getObject(key);
		try {
			return aux == null ? 0 : Long.parseLong(aux.toString());
		} catch (NumberFormatException nfe) {
			return new Double(convert(aux.toString())).longValue();
		}
	}

	public String getDataForeignKey(String key) {
		long aux = getLong(key);
		if (aux == 0) {
			return "null";
		} else {
			return Long.toString(aux);
		}
	}

	public int getInteger(String key) {
		Object aux = getObject(key);
		try {
			return aux == null ? 0 : Integer.parseInt(aux.toString());
		} catch (NumberFormatException nfe) {
			return new Double(convert(aux.toString())).intValue();
		}
	}

	public double getDouble(String key) {
		Object aux = getObject(key);
		if (aux == null)
			return 0;
		String cadNum = null;
		if (aux instanceof String) {
			cadNum = (String) aux;
			if (cadNum.length() == 0) {
				return 0;
			}
		}
		return Transform.toDouble(aux);
	}

	public boolean getBoolean(String key) {
		Object aux = getObject(key);
		return aux == null ? false : aux.toString().equalsIgnoreCase("S");
	}

	public EstructuraDatos getEstructuraDatos(String key) {
		Object aux = getObject(key);
		return (EstructuraDatos) (aux == null ? new EstructuraDatos(key) : aux);
	}

	public Object getObject(String key) {
		Object obj = datos.get(key);
		return obj;
	}

	public String[] getFields() {
		if (datos == null)
			return new String[0];
		Object[] keys = datos.keySet().toArray();
		ArrayList<String> fields = new ArrayList<String>();
		for (Object key : keys) {
			if (key instanceof String) {
				fields.add(key.toString());
			}
		}
		return fields.toArray(new String[0]);
	}

	@SuppressWarnings("unchecked")
	public Object clone() {
		EstructuraDatos eDatos = new EstructuraDatos(nombreEstructura);
		eDatos.datos = (HashMap<String, Object>) this.datos.clone();
		return eDatos;
	}

	public void SetEstructuraDatos(EstructuraDatos eDatos) {
		datos = eDatos.datos;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(nombreEstructura);
		String[] keys = (String[]) datos.keySet().toArray(new String[datos.size()]);
		for (int i = 0; i < keys.length; i++) {
			sb.append('\n');
			Object aux = getObject(keys[i]);
			if (aux instanceof EstructuraDatos) {
				sb.append(((EstructuraDatos) aux).toString());
				sb.append('\n');
			}
			sb.append(keys[i] + " " + aux.toString());
		}
		return sb.toString();
	}

	public void print() {
		identar(0);
		System.out.println(nombreEstructura);
		identar(3);
		String[] keys = (String[]) datos.keySet().toArray(new String[datos.size()]);
		for (int i = 0; i < keys.length; i++) {
			Object aux = getObject(keys[i]);
			if (aux instanceof EstructuraDatos)
				((EstructuraDatos) aux).print();
			System.out.println(keys[i] + " " + aux.toString());
		}
	}

	private void identar(int aumentar) {
		identacion = identacion + aumentar;
		for (int i = 0; i < identacion; i++) {
			System.out.println(" ");
		}
	}

	public void addStringDataQry(String col, DataQRY dataQry, int row) throws ErrorGeneral {
		add(col, dataQry.getString(row, col));
	}

	public void addDoubleDataQry(String col, DataQRY dataQry, int row) throws ErrorGeneral {
		add(col, dataQry.getDouble(row, col));
	}

	public void addFechaDataQry(String col, DataQRY dataQry, int row) throws ErrorGeneral {
		add(col, dataQry.getFecha(row, col));
	}

	public void addIntDataQry(String col, DataQRY dataQry, int row) throws ErrorGeneral {
		add(col, dataQry.getInt(row, col));
	}

	public void addLongDataQry(String col, DataQRY dataQry, int row) throws ErrorGeneral {
		add(col, dataQry.getLong(row, col));
	}

	public void addBooleanDataQry(String col, DataQRY dataQry, int row) throws ErrorGeneral {
		String aux = dataQry.getString(row, col);
		if (aux == null)
			aux = "";
		add(col, aux.equals("true") ? "S" : "N");
	}

	private double convert(String dato) {
		return Transform.toDouble(dato);
	}

	public void setOrden(String valor) {
		add("ORDEN", valor);
	}

	public String getKeyOrder(int orden) {
		return getString("ORDEN");
	}

	public void toUpper(String key) {
		add(key, getString(key).toUpperCase());
	}

	public int getSize() {
		return datos.size();
	}

	public boolean esIgual(EstructuraDatos eDatos, String field) {
		return eDatos.getString(field).equals(getString(field));
	}

	public boolean esIgualDouble(EstructuraDatos eDatos, String field) {
		return eDatos.getDouble(field) == getDouble(field);
	}
}
