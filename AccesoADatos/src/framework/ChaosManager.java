package framework;

import java.sql.SQLException;

public class ChaosManager {

	private static ChaosManager chaos;

	private Conexion conn;
	private Configuracion config;

	private ChaosManager() throws ErrorGeneral {
		config = Configuracion.getInstance();
	}

	public static ChaosManager getInstance() throws ErrorGeneral {
		if (chaos == null)
			chaos = new ChaosManager();
		return chaos;
	}

	public Configuracion getConfiguracion() {
		return config;
	}

	public Conexion getConexion() throws ErrorGeneral, SQLException {
		if (conn == null)
			conn = Conexion.getInstance(config);
		else if (!conn.isOk())
			conn = Conexion.getInstance(config);
		return conn;
	}

	public static Conexion getConexionThread() throws ErrorGeneral, SQLException {
		ChaosManager manager = ChaosManager.getInstance();
		return manager.getConexion();
	}
}
