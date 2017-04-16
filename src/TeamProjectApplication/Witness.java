package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Witness extends Person implements DatabaseFunctionality{
	
	//instance variabls for class
	String contactInfo;
	int EvidenceID;
	
	//default constructor for witness
	public Witness()
	{
		super(null, null, null);
	}
	
	/**
	 * setter method for witness name
	 * @param nameIn
	 */
	public void setWitnessName(String nameIn)
	{
		this.name = nameIn;
	}
	
	/**
	 * setter method for witness pps
	 * @param ppsIn
	 */
	public void setPPs(String ppsIn)
	{
		this.ppsNumber = ppsIn;
	}

	/**
	 * setter method for witness 
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	 * setter method for witness contact info	
	 * @param contactInfoIn
	 */
	public void setContactInfo(String contactInfoIn)
	{
		this.contactInfo = contactInfoIn;
	}
	
	/**
	 * getter method for witness name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * getter method for witness pss
	 */
	public String getPPS()
	{
		return this.ppsNumber;
	}
	
	/**
	 * getter method for witness address
	 */
	public String getAddress()
	{
		return this.address;
	}
	
	/**
	 * getter method for witness contact info
	 * @return
	 */
	public String getContactInfo()
	{
		return this.contactInfo;
	}
	
	/**
	 * getter method for evidenceID
	 * @return
	 */
	public int getEvidenceID()
	{
		return this.EvidenceID;
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
			
			//sql statement for inserting values into the witness table in the database
			sql = "INSERT INTO garda.witness values(" + "'" + this.ppsNumber + "', '" + this.name  + "', '"
			+ this.address +  "', '" + this.contactInfo + "', "+ this.EvidenceID + ");";
			
			//executes the above sql statement
			st.execute(sql);
			
			//closes the connection
			con.close();
			
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error:" + e.getMessage());
		}
		
	}
	
	/**
	 * Implemented method from database functionality interface to delete a record from the database
	 * based on the unique identifier from that record, in this case it is the caseID.
	 */
	public void UpdateWitness(String ppsIN)
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
			
			//sql statement for updating the values in the database from the values inputted in the form
			sql = "UPDATE garda.witness SET " +
			"witnessPPS = '" + this.ppsNumber
			+"',name = '" + this.name
			+"',address = '" + this.address
			+"',contactInfo = '" + this.contactInfo
			+"',EvidenceID = '" + this.EvidenceID +
			"' WHERE witnessPPS = '" + ppsIN +"';";
			
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
	
	public void linkToCase(int caseNumber)
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
			//create result set from statement, gets the highest value from all the case numbers
			rs = st.executeQuery("SELECT EvidenceID FROM garda.caseevidence WHERE caseID = " + caseNumber);
		
			//assigns results of the above statement to the evidence id field
			while(rs.next())
			{
				this.EvidenceID = rs.getInt(1);
			}
			
			//if evidence id is still 0 at this point then an evidenceID was not found and a new one has to be made.
			if(EvidenceID == 0)
			{
				//sql statement that retrieves the max value for the evidenceID column
				rs = st.executeQuery("SELECT MAX(EvidenceID) FROM garda.caseevidence");
				
				while(rs.next())
				{	
					//incremenets the retrieved max value and assigns it to the evidenceID
					this.EvidenceID = rs.getInt(1)+1;
				}
				
				//inserts the new evidenceID and case number it links to into the case evidence field
				sql = "INSERT INTO garda.caseevidence Values("+ EvidenceID + "," + caseNumber + ");";
				st.execute(sql);
				
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
	
	//method for getting the witness information from the database and assigning it to the class variables
	public void getWitness(String pps)
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
			//creates a result set from an sql statement that retrieves all data based on where the witness pps entered
			rs = st.executeQuery("Select * from garda.witness where witnessPPS = '" + pps + "';");
			
			//assigns the values from the result set to the class variables				
			while(rs.next())
			{
				this.ppsNumber = rs.getString(1);
				this.name = rs.getString(2);
				this.address = rs.getString(3);
				this.contactInfo = rs.getString(4);
				this.EvidenceID = rs.getInt(5);
			}
			
			//closes the result set and connection
			rs.close();
			con.close();
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error:" + e.getMessage());
		}
	}


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
			
			//sql for deleting a record from the database based on witness pps entered
			sql = "Delete from garda.witness where witnessPPS = '" + identifier + "';";
			
			//executes the above sql statement
			st.execute(sql);
			
			//closes the connection
			con.close();
			
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error:" + e.getMessage());
		}
		
		
	}
}
