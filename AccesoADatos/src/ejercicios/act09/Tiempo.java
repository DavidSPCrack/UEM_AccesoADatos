package ejercicios.act09;

import framework.ErrorGeneral;
import framework.util.EstructuraDatos;
import framework.util.Fecha;

public class Tiempo {

	public static final String _ID = "_ID";
	public static final String FECHA = "FECHA";
	public static final String MIN = "MIN";
	public static final String MAX = "MAX";
	public static final String HUMEDAD = "HUMEDAD";
	public static final String PRESION = "PRESION";

	private EstructuraDatos eDatos;

	Tiempo(EstructuraDatos eDatos) {
		this.eDatos = eDatos;
	}

	public Tiempo[] getInstances() throws ErrorGeneral {
		ADTiempo adatos = new ADTiempo();
		EstructuraDatos[] datos = adatos.selectTiempo();
		Tiempo[] tiempos = new Tiempo[datos.length];
		for (int i = 0; i < tiempos.length; i++) {
			tiempos[i] = new Tiempo(eDatos);
		}
		return tiempos;
	}

	public long getIdentificador() {
		return eDatos.getLong(_ID);
	}

	public Fecha getFecha() {
		return eDatos.getFecha(FECHA);
	}

	public double getMin() {
		return eDatos.getDouble(MIN);
	}

	public double getMax() {
		return eDatos.getDouble(MAX);
	}

	public double getHumedad() {
		return eDatos.getDouble(HUMEDAD);
	}

	public double getPresion() {
		return eDatos.getDouble(PRESION);
	}

	@Override
	public String toString() {
		return "Tiempo [getIdentificador()=" + getIdentificador() + ", getFecha()=" + getFecha().toString() + ", getMin()=" + getMin() + ", getMax()=" + getMax()
				+ ", getHumedad()=" + getHumedad() + ", getPresion()=" + getPresion() + "]";
	}

}
