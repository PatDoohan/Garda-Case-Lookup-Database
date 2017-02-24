package TeamProjectApplication;

import java.sql.*;

public class Connection {

	public static void main(String[] args) {
		
		Statement st;
		ResultSet rs;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			st = con.createStatement();
			rs = st.executeQuery("select * from activecase");
			
			while(rs.next()){
				System.out.println("ID: " + rs.getInt(1));
				System.out.println("CrimeCode: " + rs.getInt(2));
				System.out.println("Date and Time: " + rs.getString(3));
				System.out.println("Location: " + rs.getString(4));
				System.out.println("Status: " + rs.getString(5));
				System.out.println("Eircode: " + rs.getString(6));
			}
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}

}
