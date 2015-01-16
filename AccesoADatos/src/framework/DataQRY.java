package framework;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import framework.util.Fecha;

public class DataQRY {

	private Data datos;

	public DataQRY(ResultSet rs) throws ErrorGeneral {
		String[] cols = null;
		int[] tipos = null;
		int lenght[] = null;
		ArrayList<Object> rows = null;
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			cols = new String[rsMetaData.getColumnCount()];
			tipos = new int[cols.length];
			lenght = new int[cols.length];
			for (int i = 0; i < cols.length; i++) {
				cols[i] = rsMetaData.getColumnName(i + 1);
				tipos[i] = rsMetaData.getColumnType(i + 1);
				lenght[i] = rsMetaData.getPrecision(i + 1);
			}
			rows = obtenerRegistros(rs, 0, cols.length, tipos);
		} catch (SQLException sqle) {
			throw new ErrorGeneral(sqle.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException sqle) {
				throw new ErrorGeneral(sqle.getMessage());
			}
		}
		Data data = new Data();
		data.establecerDatos(rows);
		data.establecerDefinicionColumnas(cols, tipos, lenght);

		this.datos = data;
	}

	private ArrayList<Object> obtenerRegistros(ResultSet rs, long numRows, int numCols, int[] tipos) throws SQLException {
		if (numRows == 0)
			numRows = Long.MAX_VALUE;
		ArrayList<Object> registros = new ArrayList<Object>();
		int count = 0;

		while ((rs.next()) && (numRows > count)) {
			Object[] fila = new Object[numCols];
			for (int i = 0; i < numCols; i++) {
				if (tipos[i] == Types.TIME) {
					fila[i] = rs.getTimestamp(i + 1);
				} else if (tipos[i] == Types.DATE) {
					fila[i] = rs.getTimestamp(i + 1);
				} else if (tipos[i] == Types.DECIMAL) {
					fila[i] = rs.getDouble(i + 1);
				} else if (tipos[i] == Types.CLOB) {
					Clob clob = rs.getClob(i + 1);
					if (clob != null) {
						fila[i] = clob.getSubString(1, (int) clob.length());
					} else {
						fila[i] = "";
					}
				} else {
					fila[i] = rs.getObject(i + 1);
				}
			}
			registros.add(fila);
			count++;
		}
		return registros;
	}

	public boolean existenDatos() {
		return datos.existenDatos();
	}

	public int numeroRegistros() {
		return datos.nrows();
	}

	public int numeroColumnas() {
		return datos.ncols();
	}

	public String obtenerNombreColumna(int index) {
		return datos.obtenerNombreColumna(index);
	}

	public int obtenerTipoColumna(int index) {
		return datos.obtenerTipoColumna(index);
	}

	public int obtenerLongitudColumna(int index) {
		return datos.obtenerLongitudColumna(index);
	}

	public String getString(int fila, String col) throws ErrorGeneral {
		int columna = datos.obtenerIndiceColumna(col, false);
		return columna < 0 ? "" : getString(fila, columna);
	}

	public String getString(int fila, int col) throws ErrorGeneral {
		Object aux = null;
		if (obtenerTipoColumna(col) == Types.CLOB) {
			aux = getStringClob(fila, col);
		} else if (obtenerTipoColumna(col) == Types.TIMESTAMP) {
			Fecha fecha = getFecha(fila, col);
			aux = fecha.toChar();
		} else {
			aux = datos.obtenerDato(fila, col);
		}
		return aux == null ? "" : aux.toString();
	}

	public String getStringClob(int fila, String col) throws ErrorGeneral {
		int columna = datos.obtenerIndiceColumna(col, false);
		return columna < 0 ? "" : getStringClob(fila, columna);
	}

	public String getStringClob(int fila, int col) throws ErrorGeneral {
		if (obtenerTipoColumna(col) == Types.VARCHAR) {
			String auxcadena = (String) datos.obtenerDato(fila, col);
			return auxcadena == null ? "" : auxcadena;
		}
		if (obtenerTipoColumna(col) == Types.LONGVARCHAR) {
			String auxcadena = (String) datos.obtenerDato(fila, col);
			return auxcadena == null ? "" : auxcadena;
		}

		Object aux = datos.obtenerDato(fila, col);
		if (null == aux)
			return "";

		return aux.toString();
	}

	public byte[] getBytesBlob(int fila, String col) throws ErrorGeneral {
		int columna = datos.obtenerIndiceColumna(col, false);
		return columna < 0 ? new byte[0] : getBytesBlob(fila, columna);
	}

	public byte[] getBytesBlob(int fila, int col) throws ErrorGeneral {
		Object aux = datos.obtenerDato(fila, col);
		if (aux instanceof byte[]) {
			return (byte[]) aux;
		}
		Blob blob = (Blob) aux;
		try {
			if (blob.length() > 0)
				return blob.getBytes(((long) 1), (int) blob.length());
			else
				return new byte[0];
		} catch (SQLException sqle) {
			throw new ErrorGeneral(sqle.getMessage());
		}
	}

	public int getInt(int fila, String col) throws ErrorGeneral {
		int columna = datos.obtenerIndiceColumna(col, false);
		return columna < 0 ? 0 : getInt(fila, columna);
	}

	public int getInt(int fila, int col) throws ErrorGeneral {
		Object aux = datos.obtenerDato(fila, col);
		if (null == aux)
			return 0;
		if (obtenerTipoColumna(col) == Types.NUMERIC)
			return ((BigDecimal) aux).intValue();
		if (obtenerTipoColumna(col) == Types.DOUBLE)
			return ((Double) aux).intValue();
		if (obtenerTipoColumna(col) == Types.INTEGER)
			return ((Integer) aux).intValue();
		if (obtenerTipoColumna(col) == Types.DECIMAL)
			return ((Double) aux).intValue();
		try {
			return Integer.parseInt(aux.toString());
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	public long getLong(int fila, String col) throws ErrorGeneral {
		int columna = datos.obtenerIndiceColumna(col, false);
		return columna < 0 ? 0 : getLong(fila, columna);
	}

	public long getLong(int fila, int col) throws ErrorGeneral {
		Object aux = datos.obtenerDato(fila, col);
		if (null == aux)
			return 0;

		if (obtenerTipoColumna(col) == Types.NUMERIC)
			return ((BigDecimal) aux).longValue();
		if (obtenerTipoColumna(col) == Types.DOUBLE)
			return ((Double) aux).longValue();
		if (obtenerTipoColumna(col) == Types.INTEGER)
			return ((Integer) aux).longValue();
		if (obtenerTipoColumna(col) == Types.DECIMAL)
			return ((Double) aux).longValue();

		try {
			return Long.parseLong(aux.toString());
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	public float getFloat(int fila, String col) throws ErrorGeneral {
		int columna = datos.obtenerIndiceColumna(col);
		return getLong(fila, columna);

	}

	public float getFloat(int fila, int col) throws ErrorGeneral {
		Object aux = datos.obtenerDato(fila, col);
		if (null == aux)
			return 0;
		if (obtenerTipoColumna(col) == Types.NUMERIC)
			return ((BigDecimal) aux).floatValue();
		try {
			return Float.parseFloat(aux.toString());
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	public double getDouble(int fila, String col) throws ErrorGeneral {
		int columna = datos.obtenerIndiceColumna(col, false);
		return columna < 0 ? 0 : getDouble(fila, columna);
	}

	public double getDouble(int fila, int col) throws ErrorGeneral {
		Object aux = datos.obtenerDato(fila, col);
		if (null == aux)
			return 0;
		if (obtenerTipoColumna(col) == Types.NUMERIC)
			return ((BigDecimal) aux).doubleValue();
		try {
			return Double.parseDouble(aux.toString());
		} catch (NumberFormatException nfe) {
			return 0;
		}
	}

	public Fecha getFecha(int fila, String col) throws ErrorGeneral {
		int columna = datos.obtenerIndiceColumna(col, false);
		return columna < 0 ? Fecha.getFechaNull() : getFecha(fila, columna);
	}

	public Fecha getFecha(int fila, int col) throws ErrorGeneral {
		Object aux = datos.obtenerDato(fila, col);
		if (null == aux)
			return new Fecha(1900, 1, 1);
		if (obtenerTipoColumna(col) == Types.TIMESTAMP)
			return new Fecha((java.util.Date) aux);
		if (obtenerTipoColumna(col) == Types.DATE)
			return new Fecha((java.util.Date) aux);
		if (obtenerTipoColumna(col) == Types.VARCHAR)
			return new Fecha((String) aux);
		return new Fecha(1900, 1, 1);
	}

	public long getLongTime(int fila, String col) throws ErrorGeneral {
		int columna = datos.obtenerIndiceColumna(col, false);
		return columna < 0 ? 0 : getLongTime(fila, columna);
	}

	public long getLongTime(int fila, int col) throws ErrorGeneral {
		Object aux = datos.obtenerDato(fila, col);
		if (null == aux)
			return 0;
		if (aux instanceof java.util.Date) {
			return ((java.util.Date) aux).getTime();
		}
		return 0;
	}

	public boolean isNumerico(int colIndex) {
		if (obtenerTipoColumna(colIndex) == Types.NUMERIC)
			return true;
		if (obtenerTipoColumna(colIndex) == Types.DECIMAL)
			return true;
		if (obtenerTipoColumna(colIndex) == Types.INTEGER)
			return true;
		if (obtenerTipoColumna(colIndex) == Types.DOUBLE)
			return true;
		if (obtenerTipoColumna(colIndex) == Types.BIGINT)
			return true;
		if (obtenerTipoColumna(colIndex) == Types.FLOAT)
			return true;
		return false;
	}

	public boolean isFecha(int colIndex) {
		if (obtenerTipoColumna(colIndex) == Types.TIMESTAMP)
			return true;
		if (obtenerTipoColumna(colIndex) == Types.DATE)
			return true;
		return false;
	}

}
