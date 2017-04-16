package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.Statement;

public class Crime implements DatabaseFunctionality{
	
	//instance variables to be used in
	protected int crimeCode;
	String crimeName, crimeDescription;

	//setter method for crime code
	public void setCrimeCode(int crimeCode)
	{
		this.crimeCode = crimeCode;
	}
	
	//setter method for crime name
	public void setCrimeName(String crimeName)
	{
		this.crimeName = crimeName;
	}
	
	//setter method for crime description
	public void setCrimeDescription(String crimeDescription)
	{
		this.crimeDescription = crimeDescription;
	}
	
	//getter method for crime code
	public int getCrimeCode()
	{
		return this.crimeCode;
	}

	//getter method for crime name
	public String getCrimeName()
	{
		return this.crimeName;
	}
	
	//getter method for crime description
	public String getCrimeDescription()
	{
		return this.crimeDescription;
	}
	
	/**
	 * implemented method from database functionality to add a case to the database.
	 */
	@Override
	public void addToDatabase() 
	{
		//instance variables for use with SQL connection
		Statement st;
		String sql;
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			//Create Statement object	
			st = con.createStatement();
			
			/* create a statement to be used in the statement object, this statements creates a full
			 * SQL statlement that will insert all the data from the class into the correct columns in the database*/
			sql = "INSERT INTO garda.crime values(" + "'" + this.crimeCode + "', '" + this.crimeName + "', '" + this.crimeDescription  + "');";
			
			//uses sql string to execute the statement
			st.execute(sql);
			
			//closes the connection
			con.close();
			
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
	}

	/**
	 * Implemented method from database functionality interface to delete a record from the database
	 * based on the unique identifier from that record, in this case it is the crime code.
	 */
	@Override
	public void deleteFromDatabase(String identifier) 
	{
		//instance variables for use with SQL connection
		Statement st;
		String sql;
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			//Create Statement object	
			st = con.createStatement();
			
			//creates a sql statement that uses the unique identifier entered to delete a specific row from the datatbase.
			sql = "DELETE FROM garda.crime " + "WHERE crimeCode = '" + this.crimeCode +"';";
			
			//uses sql string to execute the statement
			st.execute(sql);
			
			//closes the connection
			con.close();
		}
		//catches errors thrown by database
		catch(Exception e)
		{			
			System.out.println("Error: " + e.getMessage());
		}
		
	}

}
