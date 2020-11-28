package Demo5_13;

import java.sql.SQLException;

public class Test {
	public static void main(String[] args){
		String id = "root";
		String password = "a05003624958";
		String tableName = "student";
		
		try {
		MetaDisplay m1 = new MetaDisplay(id, password);
		m1.printTable(tableName);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
}	
