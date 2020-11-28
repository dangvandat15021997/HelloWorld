package Demo5_13;

import java.sql.*;

public class MetaDisplay {
	Connection conn;
	PreparedStatement stmt;

	public MetaDisplay(String id, String password) throws SQLException {
		this.conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db-book?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", id,
				password);
	}

	public void setConnection(String id, String password) throws SQLException {
		this.conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db-book?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", id,
				password);
	}

	private boolean checkTable(String table) throws SQLException {
		boolean check = false;
		DatabaseMetaData DBMetaData = conn.getMetaData();
		ResultSet rs = DBMetaData.getTables(null, null, "%", null);
		boolean more;

		do {
			more = rs.next();
			if (more) {
				if (table.equalsIgnoreCase(rs.getString(3)))
					return true;
			}
		} while (more);

		return check;
	}

	public void printTable(String tableName) {
		tableName = tableName.toLowerCase();
		try {
			String query = "select * from " + tableName;

			if (!checkTable(tableName)) {
				System.out.println("Relation not exists!");
			} 
			else {
				this.stmt = this.conn.prepareStatement(query);
				ResultSet rs = this.stmt.executeQuery();
				printResultSet(rs);
				this.stmt.close();
				this.conn.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}

	public void printResultSet(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();

		int numberOfColumns = rsmd.getColumnCount();

		for (int i = 1; i <= numberOfColumns; i++) {
			if (i > 1)
				System.out.print(",  ");
			String columnName = rsmd.getColumnName(i);
			System.out.print(columnName);
		}
		System.out.println("");

		while (rs.next()) {
			for (int i = 1; i <= numberOfColumns; i++) {
				if (i > 1)
					System.out.print(",  ");
				String columnValue = rs.getString(i);
				System.out.print(columnValue);
			}
			System.out.println("");
		}
	}

}
