package actividad01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EjercicioSQL {
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");

		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:./resources/TablaTemp.db");

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);

			statement.executeUpdate("drop table if exists tiempo");
			statement.executeUpdate("create table tiempo (id integer PRIMARY KEY, " + "fecha String NOT NULL, min Float NOT NULL, max Float NOT NULL, "
					+ "humedad FLOAT NOT NULL, presion Float NOT NULL)");
			statement.executeUpdate("insert into tiempo values(1, '20141110',13,25,0,1029)");

			ResultSet rs = statement.executeQuery("select * from tiempo");

			while (rs.next()) {
				System.out.println("id = " + rs.getString("id"));
				System.out.println("fecha = " + rs.getInt("fecha"));
				System.out.println("tempMax = " + rs.getInt("max"));
			}
			rs.close();
			statement.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {

				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

}
