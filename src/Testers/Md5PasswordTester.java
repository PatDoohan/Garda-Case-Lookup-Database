package Testers;

import java.security.MessageDigest;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.math.*;

public class Md5PasswordTester {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		Statement st;
		ResultSet rs;
		String sql;
		
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			//depending on what computer you are on the password may be either Password, password or blank. If you set a custom password it will also be here.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			st = con.createStatement();
			rs = st.executeQuery("select * from activecase");
			
			
			
			String password = "password";
			MessageDigest m = MessageDigest.getInstance("SHA-512");
			m.update(password.getBytes(),0,password.length());
			String inputPassword = new BigInteger(1, m.digest()).toString(16);
			System.out.println(inputPassword);
			sql = "INSERT INTO garda.authentication values ('DN101', '"+ inputPassword +"');";
			st.execute(sql);
		
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		
	}

}
