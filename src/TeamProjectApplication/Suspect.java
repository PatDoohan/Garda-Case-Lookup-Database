package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Suspect extends Person implements DatabaseFunctionality{
	
	private int SuspectId = 0;
	private String suspectPPS, description, priorConvictions;
	private String[] status = {"Suspect", "Fugitive", "Arrested", "Processed"};
	private String currentStatus;
	private int EvidenceID = 0;
	
	
	public Suspect()
	{
		super("", "", "");
		this.description = "";
		this.priorConvictions = "";
	}
	
	public Suspect(String suspectPPS, String suspectName, String suspectAddress, String description, String priorConvictions)
	{
		super(suspectPPS, suspectName, suspectAddress);
		this.description = description;
		this.priorConvictions = priorConvictions;
		currentStatus = (String)JOptionPane.showInputDialog(null, "What is the current status of the suspect?", "Suspect Status", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
	}
	
	/**
	 * Setter for the Description, in the case that extra details need to be added
	 * @param suspectDescription
	 */
	public void setSuspectDescription(String suspectDescription)
	{
		this.description = suspectDescription;
	}
	
	/**
	 * Setter for the prior convictions, in the case that they are unknown until later
	 * @param priorConvictions
	 */
	public void setSuspectPriors(String priorConvictions)
	{
		this.priorConvictions = priorConvictions;
	}
	
	/**
	 * Setter for the status in case the status of the suspect has changed.
	 */
	public void setSuspectStatus(Object object)
	{
		this.currentStatus = (String) object;
	}
	/**
	 * getter for the criminal ID
	 * @return
	 */
	public int getSuspectID()
	{
		return this.SuspectId;
	}
	
	public void setSuspectPPS(String pps)
	{
		this.suspectPPS = pps;
	}
	
	public String getSuspectPPS()
	{
		return this.suspectPPS;
	}
	
	
	/**
	 * Getter for the pps Number, mostly used to confirm changes
	 */
	@Override
	public String getPPS()
	{
		return this.suspectPPS;
	}
	
	/**
	 * Getter for the address, mostly used to confirm changes and displaying description on it's own screen
	 * @return
	 */
	public String getSuspectDescription()
	{
		return this.description;
	}
	
	/**
	 * Getter for the address, mostly used to confirm changes
	 * @return
	 */
	public String getSuspectPriors()
	{
		return this.priorConvictions;
	}
	/**
	 * getter for status, used along with description for a display page
	 * @return
	 */
	public String getSuspectStatus()
	{
		return this.currentStatus;
	}
	
	/**
	 * method for setting the pps of the suspect
	 */
	@Override
	public void setPPS(String PPS)
	{
		this.suspectPPS = PPS;
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
			
			//sql statement for inserting values into the suspect table in the database
			sql = "INSERT INTO garda.suspect values(" + "'" + this.SuspectId + "', '" + this.suspectPPS + "', '" + this.name + "', '"
			+ this.address +  "', '" + this.description + "', '"+ this.priorConvictions + "', '" + this.currentStatus+"',"+ this.EvidenceID +");";
			
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
			
			//sql statement for deleting the suspect from the suspect table based on the id entered
			sql = "DELETE FROM garda.suspect " + "WHERE suspectID = '" + identifier +"';";
			
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
	
	//method for getting the suspect information from the database and assigning it to the class variables
	public void getSuspect(int suspectID)
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
			rs = st.executeQuery("SELECT * FROM garda.Suspect WHERE suspectID = " + "'" + suspectID +"'");
			
			//assigns the values from the result set to the class variables				
			while(rs.next())
			{
				this.SuspectId = rs.getInt(1);
				this.suspectPPS = (rs.getString(2));
				this.setName(rs.getString(3));
				this.setAddress(rs.getString(4));
				this.description = rs.getString(5);
				this.priorConvictions = rs.getString(6);
				this.currentStatus = rs.getString(7);
				this.EvidenceID = rs.getInt(8);
			}
			
			//error handling for when a suspect is not found in the database
			if(this.SuspectId == 0)
			{
				JOptionPane.showMessageDialog(null,"Suspect Not Found, Please Enter a valid suspect ID", "Suspect Not Found",  JOptionPane.ERROR_MESSAGE);
			}
				
			//closes the connection and result set
			rs.close();
			con.close();
			
		}
		//catches errors thrown by database
		catch(Exception f)
		{
			System.out.println("Error: " + f.getMessage());
		}
	}
	
	//method that updates the values in the database with the new values inputted from the form
	public void updateSuspect()
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
			
			//sql statement for updating the values in the database based on the values entered by the user.
			sql = "UPDATE garda.suspect SET " +
			"suspectPPS = '" + this.suspectPPS
			+"',name = '" + this.name
			+"',address = '" + this.address
			+"',description = '" + this.description
			+"',priorConvictions = '" + this.priorConvictions
			+"',status = '" + this.currentStatus
			+"',EvidenceID = '" + this.EvidenceID +
			"' WHERE suspectID = '" + this.SuspectId +"';";
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
			rs = st.executeQuery("SELECT MAX(suspectID) FROM garda.suspect");
			
			//gets the value from the result set, increments it and assigns it to the new SuspectID
			while(rs.next())
			{
				SuspectId = rs.getInt(1)+1;
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
	
	public void linkToCase(int caseNumber)
	{
		//instance variables for database
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
			while(rs.next()){
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
	
	//method for clearing all the data from the class
	public void clearData()
	{
		this.SuspectId = 0;
		this.name = "";
		this.address ="";
		this.suspectPPS = "";
		this.description = "";
		this.priorConvictions = "";
		this.currentStatus = "";
		this.EvidenceID = 0;
	}
}
