package TeamProjectApplication;

import java.sql.*;

public class Garda extends Person implements DatabaseFunctionality{
	
	private String idNo, barracksID, phoneNo;
	
	
	public Garda(String idNo, String barracksId, String name, String address, String phoneNo, String ppsNumber)
	{
		super(ppsNumber,name, address);
		this.idNo = idNo;
		this.barracksID = barracksId;
		this.phoneNo = phoneNo;
	}
	
	/**
	 * Setter for id Number, used for updating records
	 * @param id
	 */
	public void setidNo(String id)
	{
		this.idNo = id;
	}
	
	/**
	 * setter for barracksID, used for updating records
	 * @param barracksID
	 */
	public void setBarrack(String barracksID)
	{
		this.barracksID = barracksID;
	}
	
	/**
	 * setter for phone number, used for updating records
	 * @param phoneNo
	 */
	public void setPhoneNo(String phoneNo)
	{
		this.phoneNo = phoneNo;
	}
	
	/**
	 * getter for idNo, used for Display purposes
	 * @return
	 */
	public String getidNo()
	{
		return this.idNo;
	}
	
	/**
	 * getter for idNo, used for Display purposes
	 * @return
	 */
	public String getBarracks()
	{
		return this.barracksID;
	}
	
	/**
	 * getter for idNo, used for Display purposes
	 * @return
	 */
	public String getPhoneNo()
	{
		return this.phoneNo;
	}
	
	/**
	 * Method for adding the class to the database, takes all the values assigned into 
	 * the class and concatenates them onto an INSERT SQL Statement.
	 */
	@Override
	public void addToDatabase()
	{
		//instance variables for database
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
			
			//sql statement for inserting data into the garda table in the database
			sql = "INSERT INTO garda.garda values(" + "'" + this.idNo + "', '" + this.barracksID + "', '" + this.name + "', '"
			+ this.address +  "', '" + this.phoneNo + "', '"+ this.ppsNumber + "');";
			
			//executes the above sql statement
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
	 * method for removing a garda from the database, all removing is to
	 * be based of the id number of the garda
	 * @param idNo
	 */
	@Override
	public void deleteFromDatabase(String idNo)
	{
		//instance variables for database
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
			
			//sql for deleting a record from the garda table based on the id number entered
			sql = "DELETE FROM garda.garda " + "WHERE idNo = '" + idNo +"';";
			
			//executes the above sql statement
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
