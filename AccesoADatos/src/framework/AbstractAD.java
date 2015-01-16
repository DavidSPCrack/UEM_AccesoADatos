package framework;

import java.sql.SQLException;

import framework.util.EstructuraDatos;

public abstract class AbstractAD {

	private Conexion chaosCon;

	protected AbstractAD() throws ErrorGeneral {
		recreateConnection();
	}

	protected SentenciaSQL getSQL(String sqlFile) throws ErrorGeneral {
		return SentenciaSQL.getInstance(sqlFile);
	}

	protected void doInsert(SentenciaSQL sql) throws ErrorGeneral {
		recreateConnection();

		sql.prepareSQL();

		String sentence = sql.getSql();

		chaosCon.executeInsert(sentence);
	}

	protected void doUpdate(SentenciaSQL sql) throws ErrorGeneral {
		recreateConnection();

		sql.prepareSQL();

		String sentence = sql.getSql();

		chaosCon.executeUpdate(sentence);
	}

	protected DataQRY doSelect(SentenciaSQL sql) throws ErrorGeneral {
		recreateConnection();

		sql.prepareSQL();

		String sentence = sql.getSql();

		return chaosCon.executeSelect(sentence);
	}

	protected void doDelete(SentenciaSQL sql) throws ErrorGeneral {
		recreateConnection();

		sql.prepareSQL();

		String sentence = sql.getSql();

		chaosCon.executeDelete(sentence);
	}

	protected void recreateConnection() throws ErrorGeneral {
		try {
			chaosCon = ChaosManager.getConexionThread();
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	protected void addParameterString(String value, SentenciaSQL sentence, String parametro) {
		sentence.addParameterString(parametro, value, ParametroSQL.Types.STRING);
	}

	protected void addParameterString(EstructuraDatos eDatos, SentenciaSQL sentence, String parametro) {
		sentence.addParameterString(parametro, eDatos.getString(parametro), ParametroSQL.Types.STRING);
	}

	protected void addParameterLong(long value, SentenciaSQL sentence, String parametro) {
		sentence.addParameterLong(parametro, value, ParametroSQL.Types.NUMBER);
	}

	protected void addParameterLong(EstructuraDatos eDatos, SentenciaSQL sentence, String parametro) {
		sentence.addParameterLong(parametro, eDatos.getLong(parametro), ParametroSQL.Types.NUMBER);
	}

	protected void addParameterInt(EstructuraDatos eDatos, SentenciaSQL sentence, String parametro) {
		sentence.addParameterLong(parametro, eDatos.getInteger(parametro), ParametroSQL.Types.NUMBER);
	}

	protected void addParameterDouble(EstructuraDatos eDatos, SentenciaSQL sentence, String parametro) {
		sentence.addParameterDouble(parametro, eDatos.getDouble(parametro), ParametroSQL.Types.NUMBER);
	}

	protected void addParameterBoolean(EstructuraDatos eDatos, SentenciaSQL sentence, String parametro) {
		sentence.addParameterBoolean(parametro, eDatos.getBoolean(parametro), ParametroSQL.Types.STRING);
	}

	protected void addParameterDate(EstructuraDatos eDatos, SentenciaSQL sentence, String parametro) {
		sentence.addParameterDate(parametro, eDatos.getFecha(parametro), ParametroSQL.Types.DATE);
	}
}
