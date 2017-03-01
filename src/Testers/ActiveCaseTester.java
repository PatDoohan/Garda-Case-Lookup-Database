package Testers;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import TeamProjectApplication.ActiveCase;

public class ActiveCaseTester {
	
	public static void main(String [] args)
	{
		
		
		Statement st;
		ResultSet rs;
		String sql;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//depending on what computer you are on the password may be either Password, password or blank. If you set a custom password it will also be here.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			st = con.createStatement();
			rs = st.executeQuery("SELECT MAX(caseID) FROM garda.activeCase");
		
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
			
//			sql = "SELECT MAX(caseID) FROM garda.activeCase";
//			st.execute(sql);
			int caseid = 0;
			while(rs.next()){
			//System.out.print(rs.getInt(1));
			caseid = rs.getInt(1)+1;
			}
			
		
			System.out.print(caseid);
			
//			ActiveCase case1 = new  ActiveCase();
//			case1.setCaseID(rs.getInt(1));
//			case1.setCrimeCode(rs.getInt(2));
//			case1.setDateTime(rs.getString(3));
//			case1.setAddress(rs.getString(4));
//			case1.setActiveStatus(rs.getString(5));
//			case1.setEirCode(rs.getString(6));
//			//case1.deleteFromDatabase("2");
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		
	}
}
