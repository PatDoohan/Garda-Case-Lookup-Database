package TeamProjectApplication;

import java.sql.*;

public class Connection {

	public static void main(String[] args) {
		
		Statement st;
		ResultSet rs;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://local:3306/GardaDB","root","password");
			st = con.createStatement();
			rs = st.executeQuery("select * from case");
			
			while(rs.next()){
				System.out.println("\nID: " + rs.getInt(1));
				System.out.println("\tStatus: " + rs.getString(6));
			}
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}

}
