package Draft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Random;

public class draft {
	public static void main(String[] args) {
		String query = "insert into s values (?, ?, ?)";
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", "root",
				"a05003624958");
			PreparedStatement stmt = conn.prepareStatement(query);)
		{
			Random rand = new Random(100);
			
			for(int i = 0; i < 100; i++) {
				int value1 = rand.nextInt(100);
				int value2 = rand.nextInt(100);
				int value3 = rand.nextInt(100);
				
				stmt.setInt(1, value1);
				stmt.setInt(2, value2);
				stmt.setInt(3, value3);
				
				stmt.execute();
			}
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
		}
}
