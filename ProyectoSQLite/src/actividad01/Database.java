package actividad01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private final static String DB = "forecast.db";
	private final static String PATH = "./resources/";
	private final static String URL = "jdbc:sqlite:" + PATH + DB;

	private final static String DRIVER = "org.sqlite.JDBC";
	protected Connection conn;

	public Database() {

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL);

			if (conn != null)
				System.out.println("Conectado");

		} catch (SQLException e) {
			System.out.println("Error al cargar el driver.");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al conectarse.");
		}
		System.out.println(URL);
	}

	public static String getUrl() {
		return URL;
	}

}
