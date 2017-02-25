package TeamProjectApplication;

import java.sql.*;

public class Connection {

	public static void main(String[] args) {
		
		Statement st;
		ResultSet rs;
		String sql;

		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//depending on what computer you are on the password may be either Password, password or blank. If you set a custom password it will also be here.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			st = con.createStatement();
			rs = st.executeQuery("select * from activecase");
			
			
			//Insert into tester
//			con.createStatement();
//			sql = "INSERT INTO crime(crimeCode, crimeName, crimeDescription)" + "VALUES (102, 'Theft', 'was seen in a black hoody')";
//			st.executeUpdate(sql);
			
			//Insert into tester
//		    sql = "INSERT INTO activecase(caseID, crimeCode, date, address, status, eirCode)" + "VALUES (4, 102, '2016-02-23 23:30:00', 'Letterkenny', 'Active', 'F93 LKLK')";
//		    st.executeUpdate(sql);

		    //update row tester  
//		    con.createStatement();
//		    sql = "UPDATE activecase" + " SET address = 'Ballybofey' WHERE caseID = 4";
//		    st.execute(sql);
		    
		   
		   //Delete row tester
//	       sql = "DELETE FROM activecase " + "WHERE caseID = 4";
//		   st.executeUpdate(sql);

		      
			//print out value tester
//			while(rs.next()){
//				System.out.println("ID: " + rs.getInt(1));
//				System.out.println("CrimeCode: " + rs.getInt(2));
//				System.out.println("Date and Time: " + rs.getString(3));
//				System.out.println("Location: " + rs.getString(4));
//				System.out.println("Status: " + rs.getString(5));
//				System.out.println("Eircode: " + rs.getString(6));
//				System.out.println();
//			}
			
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}

}
