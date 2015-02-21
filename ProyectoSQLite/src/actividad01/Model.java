package actividad01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model extends Database {

	public Model() {
		super();
		createTableTiempo();
	}

	final static String CREATE_TABLE_TIEMPO = "create table tiempo (id integer PRIMARY KEY AUTOINCREMENT, "
			+ "fecha String NOT NULL, min Float NOT NULL, max Float NOT NULL, " + "humedad FLOAT NOT NULL, presion Float NOT NULL)";

	public void createTableTiempo() {
		Statement st = null;
		try {
			st = conn.createStatement();
			st.executeUpdate(CREATE_TABLE_TIEMPO);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

	public void insertPrediccion(String fecha, float min, float max, float humedad, float presion, String table) {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(getUrl());

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);

			String sql = "INSERT INTO " + table + " (fecha, min, max, humedad, presion) values" + "('" + fecha + "'," + min + "," + max + "," + humedad + ","
					+ presion + ")";
			System.out.println(sql);
			statement.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void imprimirPrediciones(String table) {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(getUrl());

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);

			ResultSet rs = statement.executeQuery("SELECT * FROM " + table);

			while (rs.next()) {
				System.out.println("id = " + rs.getString("id"));
				System.out.println("fecha = " + rs.getInt("fecha"));
				System.out.println("tempMin = " + rs.getInt("min"));
				System.out.println("tempMax = " + rs.getInt("max"));
				System.out.println("humedad = " + rs.getInt("humedad"));
				System.out.println("presion = " + rs.getInt("presion"));
				System.out.println("");
			}
			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void borrarTabla(String table) {

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(getUrl());

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);

			String sql = "DROP TABLE IF EXISTS " + table;

			statement.executeUpdate(sql);

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
