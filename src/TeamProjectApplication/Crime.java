package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Crime implements DatabaseFunctionality{

		protected int crimeCode;
		String crimeName, crimeDescription;
	
		public Crime(int crimeCode, String crimeName, String crimeDescription)
		{
			this.crimeCode = crimeCode;
			this.crimeName = crimeName;
			this.crimeDescription = crimeDescription;
		}
		
		public void setCrimeCode(int crimeCode)
		{
			this.crimeCode = crimeCode;
		}
		
		public void setCrimeName(String crimeName)
		{
			this.crimeName = crimeName;
		}
		
		public void setCrimeDescription(String crimeDescription)
		{
			this.crimeDescription = crimeDescription;
		}
		
		public int getCrimeCode()
		{
			return this.crimeCode;
		}

		public String getCrimeName()
		{
			return this.crimeName;
		}
		
		public String getCrimeDescription()
		{
			return this.crimeDescription;
		}
		@Override
		public void addToDatabase() {
			Statement st;
			ResultSet rs;
			String sql;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
				st = con.createStatement();
				rs = st.executeQuery("select * from garda.crime");
				
				sql = "INSERT INTO garda.crime values(" + "'" + this.crimeCode + "', '" + this.crimeName + "', '" + this.crimeDescription  + "');";
				
				st.execute(sql);
				
				rs.close();
				con.close();
				
			}
			catch(Exception e)
			{
				System.out.println("Error: " + e.getMessage());
			}
			
		}

		@Override
		public void deleteFromDatabase(String identifier) {

			Statement st;
			ResultSet rs;
			String sql;
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
				st = con.createStatement();
				rs = st.executeQuery("select * from garda.vehicle");	
				
				sql = "DELETE FROM garda.crime " + "WHERE crimeCode = '" + this.crimeCode +"';";
				st.execute(sql);
				
				rs.close();
				con.close();
			}
			catch(Exception e)
			{			
				System.out.println("Error: " + e.getMessage());
			}
			
		}

}
