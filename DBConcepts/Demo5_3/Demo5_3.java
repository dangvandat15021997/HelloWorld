package Demo5_3;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Demo5_3 {
	public static void main(String[] args) {
		try {
			findPrereqCourse();		

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void findPrereqCourse() throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter course_id: ");
		String course_id = sc.nextLine();
		System.out.println("Input Course id is: \n" + course_id);
		ArrayList<String> ar1 = new ArrayList();
		ar1.add(course_id);

		ArrayList<String> c_prereq = new ArrayList();
		ArrayList<String> new_c_prereq = new ArrayList();
		ArrayList<String> temp = new ArrayList();

		String query1 = "select prereq_id from prereq where course_id = ?";

		ResultSet rset;

		new_c_prereq = getResultSet(ar1, query1);

		boolean check_new_c_prereq_empty = true;

		do {
			addArrayToArray(new_c_prereq, c_prereq);
			temp = getResultSet(new_c_prereq, query1);
			deleteFromWhere(temp, c_prereq);
			new_c_prereq.clear();
			addArrayToArray(temp, new_c_prereq);
			check_new_c_prereq_empty = new_c_prereq.isEmpty();

		} while (!check_new_c_prereq_empty);
		
		System.out.println("prerequisite course_id: ");
		printArray(c_prereq);

	}

	public static void addArrayToArray(ArrayList ar1, ArrayList ar2) {
		for (int i = 0; i < ar1.size(); i++) {
			ar2.add(ar1.get(i));
		}
	}

	public static ArrayList getResultSet(ArrayList<String> ar1, String Query) throws SQLException {
		ArrayList<String> result = new ArrayList();
		try (

				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/db-book?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
						"root", "a05003624958");

				PreparedStatement stmt = conn.prepareStatement(Query);) {
		
			ResultSet rset;
			boolean more;

			for (int i = 0; i < ar1.size(); i++) {
				String condition = ar1.get(i);
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

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public static void printArray(ArrayList ar1) {
		for (int i = 0; i < ar1.size(); i++) {
			System.out.println(ar1.get(i));
		}

	}
	//delete elements of ar1 if they be in ar2
	public static void deleteFromWhere(ArrayList<String> ar1, ArrayList<String> ar2) {
		for(int i = 0; i < ar1.size(); i++) {
			String element = ar1.get(i);
			if(ar2.contains(element)) ar1.remove(element);
		}
	}

}
