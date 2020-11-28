package Demo5_12;

import java.sql.*;
import java.util.*;

public class Demo5_12 {
	private static Connection connection;

	public static void main(String[] args) {
		String id = "root";
		String password = "a05003624958";
		String substring = "a";
//		methodAB(id, password, substring);
		int IDnumber = methodAC(id, password);
		System.out.println("");
		methodAD(id, password, IDnumber);

	}

	public static void printArray(ArrayList<String> ar) {
		for (int i = 0; i < ar.size(); i++) {
			System.out.println(ar.get(i));
		}

	}

	public static int enterValidIDInstructor() {
		int ID = 0;
		boolean check = true;
		Scanner sc = new Scanner(System.in);
		while (check) {
			System.out.println("Pls, enter the ID number: ");
			ID = sc.nextInt();
			if ((ID >= 0) && (ID <= 99999))
				check = false;
			else {
				System.out.println("ID number has to be in range of (0-99999)");
				check = true;
			}
		}
		return ID;
	}

	public static void methodAB(String id, String password, String substring) {

		ArrayList<LinkedHashMap<String, String>> result = new ArrayList();
		String query = "select * from instructor where name like '%" + substring + "%'";
		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db-book?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", id,
				password); PreparedStatement stmt = conn.prepareStatement(query);) {

			ResultSet rset;
			boolean more;

//			stmt.setString(1, substring);
			rset = stmt.executeQuery();
			ResultSetMetaData rsmd = rset.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			do {
				more = rset.next();
				if (more) {
					LinkedHashMap<String, String> rowVallue = new LinkedHashMap<String, String>(columnsNumber);
					for (int i = 1; i <= columnsNumber; i++) {
						rowVallue.put(rsmd.getColumnName(i), rset.getString(i));
					}
					result.add(rowVallue);
				}
			} while (more);

			printResultSet(result);

			stmt.close();
			conn.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	// return valid ID
	public static int methodAC(String id, String password) {
		boolean loop = true;
		String query = "select * from instructor where ID = ?";
		while (loop) {
			ArrayList<LinkedHashMap<String, String>> result = new ArrayList();
			int ID = enterValidIDInstructor();

			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/db-book?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
					id, password); PreparedStatement stmt = conn.prepareStatement(query);) {

				ResultSet rset;
				boolean more;
				stmt.setInt(1, ID);
				rset = stmt.executeQuery();
				ResultSetMetaData rsmd = rset.getMetaData();
				boolean exist = true;
				boolean hasRun = false;

				int columnsNumber = rsmd.getColumnCount();

				do {
					more = rset.next();
					if (more) {
						LinkedHashMap<String, String> rowVallue = new LinkedHashMap<String, String>(columnsNumber);
						for (int i = 1; i <= columnsNumber; i++) {
							rowVallue.put(rsmd.getColumnName(i), rset.getString(i));
						}
						result.add(rowVallue);
						hasRun = true;
					} else {
						if (!hasRun) {
							exist = false;
							hasRun = true;
						}
					}
				} while (more);

				if (exist) {
					printResultSet(result);
					return ID;
				} else {
					System.out.println("ID number not exist!");
					loop = true;
				}

				stmt.close();
				conn.close();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return 0;

	}

	public static void methodAD(String id, String password, int ID) {
		String queryCheckTeaches = "select distinct ID from teaches where ID = ?";
		String queryFindTeachingInfor = "select 	instructor.dept_name, course_id, title, sec_id, semester, year, sum(coalesce(takes.ID, 0)) as 'total enrollment' " + 
				"from 	instructor inner join teaches using (ID) " + 
				"		left join takes using(course_id, sec_id, semester, year) " + 
				"		left join course using(course_id) " + 
				"where	instructor.ID = ? " + 
				"order by dept_name, course_id, year, semester";
		ArrayList<LinkedHashMap<String, String>> result = new ArrayList();

		try (Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db-book?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", id,
				password); 
				PreparedStatement checkTeaches = conn.prepareStatement(queryCheckTeaches);
				PreparedStatement findTeachingInfor = conn.prepareStatement(queryFindTeachingInfor);) {
			// check ID exist in teaches
	
			checkTeaches.setInt(1, ID);
			ResultSet rsetCheckTeaches = checkTeaches.executeQuery();
			

			boolean exist = rsetCheckTeaches.next();
			if(!exist) {
				System.out.println("Intructors with Id number: " + ID + " teach no course!");
				checkTeaches.close();
			}
			else {
				findTeachingInfor.setInt(1, ID);
				ResultSet rsetFindTeachingInfor = findTeachingInfor.executeQuery();
				ResultSetMetaData rsmd = rsetFindTeachingInfor.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				boolean more;
				do {
					more = rsetFindTeachingInfor.next();
					if (more) {
						LinkedHashMap<String, String> rowVallue = new LinkedHashMap<String, String>(columnsNumber);
						for (int i = 1; i <= columnsNumber; i++) {
							rowVallue.put(rsmd.getColumnName(i), rsetFindTeachingInfor.getString(i));
						}
						result.add(rowVallue);

					}
				} while (more);

				if (exist) {
					printResultSet(result);
				} else {
					System.out.println("ID number not exist!");
				}
			}

			conn.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public static void printResultSet(ArrayList<LinkedHashMap<String, String>> result) {
		LinkedHashMap<String, String> setColumnName = new LinkedHashMap();
		setColumnName = result.get(0);

		for (Map.Entry<String, String> entry : setColumnName.entrySet()) {
			String columnName = entry.getKey();
			System.out.print(columnName + '\t');
			// do stuff
		}
		System.out.println("");

		for (int i = 0; i < result.size(); i++) {
			setColumnName = result.get(i);
			for (Map.Entry<String, String> entry : setColumnName.entrySet()) {
				System.out.print(entry.getValue() + '\t');
			}
			System.out.println("");
		}

	}

}
