package framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import framework.util.Fecha;
import framework.util.Transform;

public class SentenciaSQL {

	private String sql;
	private ParametroSQL[] parametros;

	private SentenciaSQL(String sql) {
		this.sql = sql;
	}

	private void addParameter(String key, String value, int type) {
		key = "&".concat(key);

		ArrayList<ParametroSQL> parametersList = new ArrayList<ParametroSQL>();
		ParametroSQL parametro = new ParametroSQL(key, value, type);
		if (parametros != null) {
			for (ParametroSQL params : parametros) {
				parametersList.add(params);
			}
		}
		parametersList.add(parametro);
		parametros = parametersList.toArray(new ParametroSQL[0]);
	}

	public void addParameterString(String key, String value, int type) {
		addParameter(key, value, type);
	}

	public void addParameterInt(String key, int value, int type) {
		addParameter(key, Transform.toString(value), type);
	}

	public void addParameterLong(String key, long value, int type) {
		addParameter(key, Transform.toString(value), type);
	}

	public void addParameterDouble(String key, double value, int type) {
		addParameter(key, Transform.toString(value), type);
	}

	public void addParameterBoolean(String key, boolean value, int type) {
		addParameter(key, value ? "S" : "N", type);
	}

	public void addParameterDate(String key, Fecha value, int type) {
		addParameter(key, value.toChar("dd/MM/yyyy"), type);
	}

	public void removeParameter(String key) {
		ArrayList<ParametroSQL> parametersList = new ArrayList<ParametroSQL>();
		if (parametros != null) {
			for (ParametroSQL params : parametros) {
				if (!params.getKey().equals(key))
					parametersList.add(params);
			}
		}
		parametros = parametersList.toArray(new ParametroSQL[0]);
	}

	protected static SentenciaSQL getInstance(String sqlFile) throws ErrorGeneral {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream iS = classLoader.getResourceAsStream("framework/sql/".concat(sqlFile));

			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(iS));

			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			return new SentenciaSQL(sb.toString());
		} catch (IOException e) {
			throw new ErrorGeneral(e.getMessage());
		}
	}

	protected String getSql() {
		return sql;
	}

	protected void setSql(String sql) {
		this.sql = sql;
	}

	protected void prepareSQL() {
		if (parametros == null)
			parametros = new ParametroSQL[0];
		replaceParameters();
	}

	private void replaceParameters() {
		for (ParametroSQL parametro : parametros) {
			sql = sql.replace(parametro.getKey(), parametro.getValue());
		}
	}
}
