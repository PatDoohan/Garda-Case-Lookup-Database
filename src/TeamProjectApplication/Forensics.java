package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Forensics implements DatabaseFunctionality{

	int	ForensicID, EvidenceID;;
	String bioEvidence, prints, trackEvidence, digitalEvidence, toolMarkEvidence, narcoticEvidence, firearmEvidence;
	
	/**
	 * Setter method for biological evidence
	 */
	public void setBiologicalEvidence(String confirmation)
	{
		this.bioEvidence = confirmation;
	}
	
	/**
	 * Setter method for print evidence
	 */
	public void setPrintEvidence(String confirmation)
	{
		this.prints = confirmation;
	}
	
	/**
	 * Setter method for track evidence
	 */
	public void setTrackEvidence(String confirmation)
	{
		this.trackEvidence = confirmation;
	}
	
	/**
	 * Setter method for digital evidence
	 */
	public void setDigitalEvidence(String confirmation)
	{
		this.digitalEvidence = confirmation;
	}
	
	/**
	 * Setter method for tool mark evidence
	 */
	public void setToolMarkEvidence(String confirmation)
	{
		this.toolMarkEvidence = confirmation;
	}
	
	/**
	 * Setter method for Narcotic evidence
	 */
	public void setNarcoticEvidence(String confirmation)
	{
		this.narcoticEvidence = confirmation;
	}
	
	/**
	 * Setter method for Firearm evidence
	 */
	public void setFirearmEvidence(String confirmation)
	{
		this.firearmEvidence = confirmation;
	}
	
	/**
	 * getter method for forensic id
	 * @return
	 */
	public int getForensicID()
	{
		return this.ForensicID;
	}
	
	/**
	 * getter method for biological evidence
	 * @return
	 */
	public String getBioEvidence()
	{
		return this.bioEvidence;
	}
	
	/**
	 * getter method for finger print evidence
	 * @return
	 */
	public String getPrints()
	{
		return this.prints;
	}
	
	/**
	 * getter method for track evidence
	 * @return
	 */
	public String getTrackEvidence()
	{
		return this.trackEvidence;
	}
	
	/**
	 * getter for digital evidence
	 * @return
	 */
	public String getDigitalEvidence()
	{
		return this.digitalEvidence;
	}
	
	/**
	 * getter for tool mark evidence
	 * @return
	 */
	public String getToolMarkEvidence()
	{
		return this.toolMarkEvidence;
	}
	
	/**
	 * getter for narcotic evidence
	 * @return
	 */
	public String getNarcoticEvidence()
	{
		return this.narcoticEvidence;
	}
	
	/**
	 * getter for firearm evidence
	 * @return
	 */
	public String getfirearmEvidence()
	{
		return this.firearmEvidence;
	}
	
	//method that gets a forensic file from the database and assigns the retrieved values to the class variables
	public int getForensicFile(int ForensicIDIn)
	{
		//instance variables for database
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
			//create result set from statement, returns all the column from the specified row
			rs = st.executeQuery("SELECT * FROM garda.forensics where ForensicID = " + ForensicIDIn);
			
			int count = 0;
			//sets the values returned to the class variables
			while(rs.next())
			{
				ForensicID = rs.getInt(1);
				bioEvidence = rs.getString(2);
				prints = rs.getString(3);
				trackEvidence = rs.getString(4);
				digitalEvidence = rs.getString(5);
				toolMarkEvidence = rs.getString(6);
				narcoticEvidence = rs.getString(7);
				firearmEvidence = rs.getString(8);
				EvidenceID = rs.getInt(9);
				count++;
			}
		
			if(count < 1)
			{
				return 0;
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
		return -1;
	}
	
	//method that updates the values in the database with the new values inputted from the form
	public void updateForensics()
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
			
			//creates the sql statement that inserts the updates class variables into the database
			sql = "UPDATE garda.forensics SET " +
			"BiologicalEvidence = '" + this.bioEvidence
			+"',Prints = '" + this.prints
			+"',TrackEvidence = '" + this.trackEvidence
			+"',DigitalEvidence = '" + this.digitalEvidence
			+"',ToolMarkEvidence = '" + this.toolMarkEvidence
			+"',NarcoticEvidence = '" + this.narcoticEvidence
			+"',FirearmEvidence = '" + this.firearmEvidence
			+"',EvidenceID = '" + this.EvidenceID +
			"' WHERE ForensicID = '" + this.ForensicID +"';";
			
			//executes the above sql statement
			st.execute(sql);
			
			//closes the conneciton
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
			rs = st.executeQuery("SELECT MAX(ForensicID) FROM garda.forensics");
			
			//gets the value from the result set, increments it and assigns it to the new SuspectID
			while(rs.next())
			{
				ForensicID = rs.getInt(1)+1;
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
	
	public int linkToCase(int caseNumber)
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
			//create result set from statement, tries to retrieve the evidenceID from the evidence ID linked to the case NUmber
			

			rs = st.executeQuery("Select caseID from activeCase where caseID = ' " + caseNumber +"';");
			
			int count = 0;
			
			while(rs.next())
			{
				rs.getString(1);
				count++;
			}
			
			if(count < 1)
			{
				return 0;
			}
			
			else
			{
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
		return -1;
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
					
			//sql statement for inserting values into the forensic table in the database
			sql = "INSERT INTO garda.forensics values(" + "'" + this.ForensicID + "', '" + this.bioEvidence + "', '" + this.prints + "', '"
			+ this.trackEvidence +  "', '" + this.digitalEvidence + "', '"+ this.toolMarkEvidence + "', '" 
			+ this.narcoticEvidence+"','"+ this.firearmEvidence +"',"+this.EvidenceID +");";
			
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
			
			//sql for deleting a forensic file based on the identifier entered
			sql = "DELETE FROM garda.forensics " + "WHERE ForensicID = '" + identifier +"';";
			
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
