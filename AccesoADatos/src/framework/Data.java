package framework;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

public final class Data {

	private ArrayList<Object> vData;
	private ColData[] colsData;
	private HashMap<String, ColData> colsmap;

	public Data() {
		this.colsmap = new HashMap<String, ColData>();
	}

	public void establecerDatos(ArrayList<Object> datos) {
		this.vData = datos;
	}

	void establecerDefinicionColumnas(String[] _cols) {
		colsData = new ColData[_cols.length];
		for (int i = 0; i < colsData.length; i++) {
			colsData[i] = new ColData(_cols[i], Types.VARCHAR);
			colsData[i].setIndex(i);
			colsmap.put(_cols[i].toUpperCase(), colsData[i]);
		}
	}

	public void establecerDefinicionColumnas(String[] _cols, int[] _types) {
		colsData = new ColData[_cols.length];
		for (int i = 0; i < colsData.length; i++) {
			colsData[i] = new ColData(_cols[i], _types[i]);
			colsData[i].setIndex(i);
			colsmap.put(_cols[i].toUpperCase(), colsData[i]);
		}
	}

	public void establecerDefinicionColumnas(String[] _cols, int[] _types, int[] length) {
		colsData = new ColData[_cols.length];
		for (int i = 0; i < colsData.length; i++) {
			colsData[i] = new ColData(_cols[i], _types[i]);
			colsData[i].setIndex(i);
			colsData[i].setLength(length[i]);
			colsmap.put(_cols[i].toUpperCase(), colsData[i]);
		}
	}

	boolean existenDatos() {
		return !vData.isEmpty();
	}

	int nrows() {
		return vData.size();
	}

	int ncols() {
		return colsData.length;
	}

	String obtenerNombreColumna(int index) {
		return colsData[index].getNombreColumna();
	}

	int obtenerTipoColumna(int index) {
		return colsData[index].getType();
	}

	int obtenerLongitudColumna(int index) {
		return colsData[index].getLength();
	}

	int obtenerIndiceColumna(String nombreColumna) throws ErrorGeneral {
		return obtenerIndiceColumna(nombreColumna, true);
	}

	int obtenerIndiceColumna(String nombreColumna, boolean errorSinoExiste) throws ErrorGeneral {
		ColData col = (ColData) this.colsmap.get(nombreColumna);
		if (col == null) {
			col = (ColData) this.colsmap.get(nombreColumna.toUpperCase());
			if (col == null) {
				if (errorSinoExiste)
					throw new ErrorGeneral("Nombre de columna $1 no encontrada");
				else
					return -1;
			}
		}
		return col.getIndex();
	}

	Object obtenerDato(int fila, String colname) throws ErrorGeneral {
		int col = obtenerIndiceColumna(colname);
		return obtenerDato(fila, col);
	}

	Object obtenerDato(int fila, int col) {
		Object object = ((Object[]) vData.get(fila))[col];
		return object;
	}

	public int size() {
		return vData == null ? 0 : vData.size();
	}

	private class ColData {
		private String nombreColumna;
		private int type;
		private int index;
		private int length;

		public ColData(String _nombreColumna, int _type) {
			this.nombreColumna = _nombreColumna;
			this.type = _type;
		}

		public java.lang.String getNombreColumna() {
			return nombreColumna;
		}

		public int getType() {
			return type;
		}

		void setIndex(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		void setLength(int length) {
			this.length = length;
		}

		public int getLength() {
			return length;
		}

	}

}
