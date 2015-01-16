package ejercicios.act09;

import framework.AbstractAD;
import framework.ConstantesSQL;
import framework.DataQRY;
import framework.ErrorGeneral;
import framework.SentenciaSQL;
import framework.util.EstructuraDatos;

public class ADTiempo extends AbstractAD {

	public ADTiempo() throws ErrorGeneral {
		super();
	}

	public void createTable() throws ErrorGeneral {
		SentenciaSQL sentencia = getSQL(ConstantesSQL.CREATE_TABLE_TIEMPOS);
		doUpdate(sentencia);
	}

	public EstructuraDatos[] selectTiempo() throws ErrorGeneral {
		SentenciaSQL sentencia = getSQL(ConstantesSQL.SELECT_TIEMPOS);
		DataQRY dataQry = doSelect(sentencia);

		EstructuraDatos[] eDatos = getEDatosTiempo(dataQry);
		return eDatos;
	}

	public EstructuraDatos[] getEDatosTiempo(DataQRY dataQry) throws ErrorGeneral {
		EstructuraDatos[] eDatos = new EstructuraDatos[dataQry.numeroRegistros()];
		for (int i = 0; i < eDatos.length; i++) {
			eDatos[i] = getEDatoTiempo(dataQry, i);
		}
		return eDatos;
	}

	public EstructuraDatos getEDatoTiempo(DataQRY dataQry, int i) throws ErrorGeneral {
		EstructuraDatos eDatos = new EstructuraDatos("TIEMPO");
		eDatos.add(Tiempo._ID, dataQry.getLong(i, Tiempo._ID));
		eDatos.add(Tiempo.FECHA, dataQry.getString(i, Tiempo.FECHA));
		eDatos.add(Tiempo.MIN, dataQry.getString(i, Tiempo.MIN));
		eDatos.add(Tiempo.MAX, dataQry.getString(i, Tiempo.MAX));
		eDatos.add(Tiempo.HUMEDAD, dataQry.getString(i, Tiempo.HUMEDAD));
		eDatos.add(Tiempo.PRESION, dataQry.getString(i, Tiempo.PRESION));
		return eDatos;
	}

}
