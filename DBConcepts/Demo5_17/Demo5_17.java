package Demo5_17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Demo5_17 {
	public static void main(String[] args) {

		findTotalCost("CS-347");
	}

	public static void findTotalCost(String course_id) {

		HashMap<String, Integer> c_prereq = new HashMap<String, Integer>();
		ArrayList<String> new_c_prereq = new ArrayList<String>();
		String query1 = "select	prereq_id from prereq where	course_id = ?";
		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db-book?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
				"root", "a05003624958"); PreparedStatement stmt1 = conn.prepareStatement(query1);) {

			ResultSet rset;
			boolean more;

			stmt1.setString(1, course_id);
			rset = stmt1.executeQuery();

			do {
				more = rset.next();
				if (more) {
					new_c_prereq.add(rset.getString(1));
				}
			} while (more);

			int level = 0;

			do {
				moveNew_c_prereqToC_prereq(new_c_prereq, c_prereq, level);
				level += 1;
				ArrayList<String> temp = new ArrayList<String>();
				temp = createTemp(new_c_prereq, c_prereq, query1);
				new_c_prereq = new ArrayList<String>(temp);
			} while (!new_c_prereq.isEmpty());

			int sum = 0;
			for (Map.Entry<String, Integer> entry : c_prereq.entrySet()) {
				System.out.println(entry.getKey() + " " + entry.getValue());
				sum += entry.getValue();
			}

			System.out.println("Total cost of " + course_id + " is: " + sum);

			stmt1.close();
			conn.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static ArrayList<String> createTemp(ArrayList<String> new_c_prereq, HashMap<String, Integer> c_prereq,
			String Query) {
		ArrayList<String> result = new ArrayList<String>();
		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db-book?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
				"root", "a05003624958");

				PreparedStatement stmt = conn.prepareStatement(Query);) {

			ResultSet rset;
			boolean more;

			for (int i = 0; i < new_c_prereq.size(); i++) {
				String condition = new_c_prereq.get(i);
				stmt.setString(1, condition);
				rset = stmt.executeQuery();
				do {
					more = rset.next();
					if (more) {
						result.add(rset.getString(1));
					}
				} while (more);

			}

			stmt.close();
			conn.close();

			for (int i = 0; i < result.size(); i++) {
				if (c_prereq.containsKey(result.get(i))) {
					result.remove(i);
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static void moveNew_c_prereqToC_prereq(ArrayList<String> new_c_prereq, HashMap<String, Integer> c_prereq,
			int level) {
		for (int i = 0; i < new_c_prereq.size(); i++) {
			c_prereq.put(new_c_prereq.get(i), level);
		}
	}

	public static void printResultSet(ResultSet rs) throws SQLException {
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
