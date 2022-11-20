package data.access;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtility implements DatabaseAccessor {

	private static Statement st;

	public DatabaseUtility() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/sakila";
		DBConnection DBInstance = DBConnection.getInstance(url);
		Connection conn = DBInstance.getConnection();
		st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}

	@Override
	public String[] ExecuteSingleStringColumn(String sql) throws SQLException {
		String[] result = null;

		try {
			ResultSet r = st.executeQuery(sql);

			result = new String[getNumofRows(r)];

			int i = 0;
			r.beforeFirst();

			while (r.next()) {
				result[i] = r.getString(1);
				i++;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public int[] ExecuteSingleIntColumn(String sql) throws SQLException {
		int[] result = null;
		try {
			ResultSet r = st.executeQuery(sql);

			result = new int[getNumofRows(r)];

			int i = 0;
			r.beforeFirst();

			while (r.next()) {
				result[i] = r.getInt(1);
				i++;
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public String ExecuteSingleStringCell(String sql) throws SQLException {
		String result = null;
		try {
			ResultSet rs = st.executeQuery(sql);

			rs.first();
			result = rs.getString(1);
		} catch (SQLException e) {
			printSQLException(e);
		}
		return result;
	}

	@Override
	public int Id(String sql) throws SQLException {
		int id = 0;

		try {
			ResultSet r = st.executeQuery(sql);

			r.first();
			id = r.getInt(1);
		} catch (SQLException e) {
			printSQLException(e);
		}
		return id;
	}

	public int getNumofRows(ResultSet rs) throws SQLException {
		int rowcount = 0;
		ResultSet r = rs;

		while (r.next()) {
			rowcount++;
		}
		return rowcount;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable th = ex.getCause();
				while (th != null) {
					System.out.println("Cause: " + th);
					th = th.getCause();
				}
			}
		}
	}
}