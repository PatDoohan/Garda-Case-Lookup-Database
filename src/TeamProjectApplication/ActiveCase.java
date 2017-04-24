package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ActiveCase implements DatabaseFunctionality{

	//Instance Varibales for use in methods
	private int caseID = 0;
	private String date, time, address, eirCode, activeStatus;
	private String[] status = {"Open", "Active", "In Court", "Closed"};

	/**
	 * Constructor only sets default status, variables must be initialized using setter methods
	 */
	public ActiveCase()
	{
		activeStatus = status[0];
	}

	/**
	 * setter method for date and time
	 * @param dateTime
	 */
	public void setDate(String date)
	{
		this.date = date;
	}
	
	/**
	 * setter method for time
	 * @param time
	 */
	public void setTime(String time)
	{
		this.time = time;
	}
	
	/**
	 * Setter method for address
	 * @param address
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	 * setter method for eircode
	 * @param eirCode
	 */
	public void setEirCode(String eirCode)
	{
		this.eirCode = eirCode;
	}

	/**
	 * setter for active status
	 * @param object
	 */
	public void setActiveStatus(Object object)
	{
		this.activeStatus = (String) object;
	}

	/**
	 * Getter for CaseID
	 * @return
	 */
	public int getCaseID()
	{
		return this.caseID;
	}
	
	/**
	 * getter for DateTime
	 * @return
	 */
	public String getDate()
	{
		return this.date;
	}
	
	/**
	 * getter for address
	 * @return
	 */
	public String getAddress()
	{
		return this.address;
	}
	
	/**
	 * getter for status
	 * @return
	 */
	public String getStatus()
	{
		return this.activeStatus;
	}
	
	/**
	 * getter method for time
	 * @return
	 */
	public String getTime()
	{
		return this.time;
	}
	
	/**
	 * getter for eircode
	 * @return
	 */
	public String getEircode()
	{
		return this.eirCode;
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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			
			/* create a statement to be used in the statement object, this statements creates a full
			 * SQL statlement that will insert all the data from the class into the correct columns in the database*/
			sql = "INSERT INTO garda.activecase values(" + "'" + this.caseID + "', '"  + this.address + "', '" +  this.date + "', '" + this.time + "', '" +this.activeStatus + "', '" + this.eirCode + "');";
			
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
	 * based on the unique identifier from that record, in this case it is the caseID.
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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
		
			//creates a sql statement that uses the unique identifier entered to delete a specific row from the datatbase.
			sql = "DELETE FROM garda.activecase " + "WHERE caseID = '" + identifier +"';";
			
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
	 * method for assigning unique values to caseID variable
	 */
	public void assignID()
	{
		//instance variables for use with SQL connection
		Statement st;
		ResultSet rs;
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from statement, gets the highest value from all the case numbers
			rs = st.executeQuery("SELECT MAX(caseID) FROM garda.activeCase");
			
			//gets the value from the result set, increments it and assigns it to the new CaseID in the class
			while(rs.next())
			{
				caseID = rs.getInt(1)+1;
			}
			
			if(caseID == 0)
			{
				caseID = 1;
			}
	
			//closes result set and connection
			rs.close();
			con.close();
		}
		//catches errors thrown by database
		catch(Exception f)
		{
			System.out.println("Error: " + f.getMessage());
		}
	}
	
	/**
	 * method for retriving the case information from the database, used for editing and viewing information
	 * @param identifier
	 * @return
	 */
	public int GetCase(String identifier)
	{
		//instance variables for use with SQL connection
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from statement, statement returns the row that is identified by the caseID entered by the user
			rs = st.executeQuery("SELECT * FROM garda.activeCase WHERE caseID = " + "'" + identifier +"'");
			
			//count for handling if the result set is empty
			int count = 0;
			
			//assigns the values from the result set to the class variables
			while(rs.next())
			{
				this.caseID = rs.getInt(1);
				this.address = rs.getString(2);
				this.date = rs.getString(3);
				this.time = rs.getString(4);
				this.activeStatus = rs.getString(5);
				this.eirCode = rs.getString(6);
				count++;
			}
			
			//if no data was retrived return 0 for error handling
			if(count < 1)
			{
				return 0;
			}
			
			rs.close();
			con.close();
			
		}
		//catches errors thrown by database
		catch(Exception f)
		{
			System.out.println("Error: " + f.getMessage());
		}
		//return -1 as default
		return -1;
	}
	
	/**
	 * method for updating case in databse.
	 * @param identifier
	 */
	public void updateDatabase(String identifier)
	{
		//instance variables for use with SQL connection
		Statement st;
		String sql;
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
	
			
			/*sql statement that updates selected row with new values, values will be inputed to class by the appropriate
			 * form in this case the edit case form will use the set methods of the class to add the values to the class*/
			sql = "UPDATE garda.activecase SET " +
			"',address = '" + this.address + 
			"Date = '" + this.date +
			"Time = '" + this.time +
			"',status = '" + this.activeStatus +
			"',eirCode = '" + this.eirCode + "' WHERE caseID = '" + identifier + "';";
			

			//executes the above sql statement
			st.execute(sql);

			//closes the connection
			con.close();	
		}
		//catches errors thrown by database
		catch(Exception f)
		{
			System.out.println("Error: " + f.getMessage());
		}
		
	}
}
