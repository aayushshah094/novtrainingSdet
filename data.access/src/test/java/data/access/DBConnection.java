package data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection instance;
	private Connection conn;

	private DBConnection(String url) {
		try {
			this.conn = DriverManager.getConnection(url, "root", "root");
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Connection getConnection() {
		return this.conn;
	}

	public static DBConnection getInstance(String url) throws SQLException {
		if (instance == null) {
			instance = new DBConnection(url);
		} else if (instance.getConnection().isClosed()) {
			instance = new DBConnection(url);
		}
		return instance;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}