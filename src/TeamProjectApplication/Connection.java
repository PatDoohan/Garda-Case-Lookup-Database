package TeamProjectApplication;

import java.sql.*;

public class Connection {

	public static void main(String[] args) {
		
		Statement st;
		ResultSet rs;
		String sql;
		//int caseID = 3;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","");
			st = con.createStatement();
			rs = st.executeQuery("select * from activecase");
			
			
			/*con.createStatement();
			sql = "INSERT INTO crime(crimeCode, crimeName, crimeDescription)" + "VALUES (102, 'Theft', 'was seen in a black hoody')";
			st.executeUpdate(sql);*/
			
		    /*sql = "INSERT INTO activecase(caseID, crimeCode, date, address, status, eirCode)" + "VALUES (4, 102, '2016-02-23 23:30:00', 'Letterkenny', 'Active', 'F93 LKLK')";
		    st.executeUpdate(sql);

	        System.out.println("Inserted records into the table...");
	        //caseID++;*/
		      
		    /*con.createStatement();
		    sql = "UPDATE activecase" + "SET location = 'Ballybofey' WHERE caseID = 0";
		    st.execute(sql);*/
		    
	        sql = "DELETE FROM activecase " + "WHERE caseID = 0";
		    st.executeUpdate(sql);

		      
			while(rs.next()){
				System.out.println("ID: " + rs.getInt(1));
				System.out.println("CrimeCode: " + rs.getInt(2));
				System.out.println("Date and Time: " + rs.getString(3));
				System.out.println("Location: " + rs.getString(4));
				System.out.println("Status: " + rs.getString(5));
				System.out.println("Eircode: " + rs.getString(6));
				System.out.println();
			}
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}

}
