package TeamProjectApplication;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class Garda extends Person implements DatabaseFunctionality{
	
	private String idNo, barracksID, phoneNo, status = "Active", password, Certified = "False";
	
	
	public Garda()
	{
		super(null,null, null);
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
	
	
	public String getStatus()
	{
		return this.status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public void setPassword(String password)
	{
		
		MessageDigest m;
		try 
		{
			m = MessageDigest.getInstance("SHA-512");
			m.update(password.getBytes(),0,password.length());
			this.password = new BigInteger(1, m.digest()).toString(16);
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
	
	}
	
	public String getPassword()
	{
		return this.password;
	}
	
	public void setCertified(String certified)
	{
		this.Certified = certified;
	}
	
	public String getCertification()
	{
		return this.Certified;
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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			
			//sql statement for inserting data into the garda table in the database
			sql = "INSERT INTO garda.garda values(" + "'" + this.idNo + "', '" + this.barracksID + "', '" + this.name + "', '"
			+ this.address +  "', '" + this.phoneNo + "', '"+ this.ppsNumber + "', '"+ this.status+"', '"+ this.password+"', '"+ this.Certified + "');";
			
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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
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
	
	public int getGarda(String identifier)
	{
		//instance variables for database
		Statement st;
		String sql;
		ResultSet rs;
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			rs = st.executeQuery("Select * from garda.garda where idNo = '" + identifier + "';");
			
			int count = 0;
			
			//assigns the retrieved data to the class variables
			while(rs.next())
			{
				System.out.print("here");
				this.idNo = rs.getString(1);
				this.barracksID = rs.getString(2);
				this.name = rs.getString(3);
				this.address = rs.getString(4);
				this.phoneNo = rs.getString(5);
				this.ppsNumber = rs.getString(6);
				this.status = rs.getString(7);
				this.password = rs.getString(8);
				this.Certified = rs.getString(9);
				count++;
			}
			
			if(count < 1)
			{
				return 0;
			}
			
			
			
			
			//closes the result set and connection
			rs.close();
			con.close();
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		return -1;
	}	
	
	public void updateGarda()
	{
		//instance variables for database
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
			
			//sql for Updating a record from the garda table based on the id number entered
			sql = "UPDATE garda.garda SET " +
			"idNo = '" + this.idNo
			+"',barracksId = '" + this.barracksID
			+"',Fullname = '" + this.name 
			+"',address = '" + this.address
			+"',phoneNo = '" + this.phoneNo
			+"',PPSNumber = '" + this.ppsNumber 
			+"',Status = '" + this.status 
			+"',Certified = '" + this.Certified 
			+ "' WHERE idNo = '" + this.idNo +	"';";
			
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
	
	//method for assigning gardaí to cases
	public int assignToCase(String CaseID)
	{
		//instance variables for database
		Statement st;
		String sql;
		ResultSet rs;
		String getCase = null, getID= null;
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			//sql query that searches and checks if the entered id and case numbers are already in a record
			rs = st.executeQuery("SELECT * FROM garda.assignedgarda WHERE gardaID = '" + this.idNo + "' AND caseID = " + CaseID);
			
		
			//assigns the values(if any) to the variables for comparison
			while(rs.next())
			{
				getID = rs.getString(1);
				getCase = String.valueOf(rs.getInt(2));
			}
			
			//checks to see if the record is already in the table, if it is returns a 0, this is used by the assign class to signify that the garda is already assigned to this case
			if(idNo.equals(getID) && CaseID.equals(getCase))
			{
				return 0;
			}
			
			//checks to see if the garda is active as non-active gardaí should not be assigned cases.
			if(!this.status.equals("Active"))
			{
				return 1;
			}
			
			//sql statement to check if the caseID that the user is attempting to assign a garda to exists
			sql = "Select caseID from activeCase where caseID = ' " + CaseID +"';";
			//executes the above statement and stores it in a result set
			rs = st.executeQuery(sql);
	
			//if the result set is empty it returns a 2, this is used by the assign class to signify that the case doesn't exist
			if(!rs.next())
			{
				return 2;
			}
			
			//sql statement for assigning the garda to the case.
			sql = "Insert into garda.assignedgarda values ('" + this.idNo + "','" + CaseID + "');";
			//executes the above statement
			st.execute(sql);
			
			//closes the connection and result set
			con.close();
			rs.close();
	
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
		//default return value
		return -1;
	}
}
