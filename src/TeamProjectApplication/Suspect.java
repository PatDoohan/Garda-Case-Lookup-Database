package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Suspect extends Person implements DatabaseFunctionality{
	
	private int SuspectId = 0;
	private String suspectPPS, suspectName, suspectAddress, description, priorConvictions;
	private String[] status = {"Suspect", "Fugitive", "Arrested", "Processed"};
	private String currentStatus;
	private JComboBox statusBox = new JComboBox(status);
	private int EvidenceID = 0;
	
	
	public Suspect()
	{
		super("", "", "");
		this.description = "";
		this.priorConvictions = "";
		//currentStatus = (String) JOptionPane.showInputDialog(null, "What is the current status of the suspect?", "Suspect Status", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
	
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
	
	@Override
	public void setPPS(String PPS)
	{
		this.suspectPPS = PPS;
	}

	@Override
	public void addToDatabase() {
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			st = con.createStatement();
			rs = st.executeQuery("select * from garda.suspect");
			
			sql = "INSERT INTO garda.suspect values(" + "'" + this.SuspectId + "', '" + this.suspectPPS + "', '" + this.name + "', '"
					+ this.address +  "', '" + this.description + "', '"+ this.priorConvictions + "', '" + this.currentStatus+"',"+ this.EvidenceID +");";
			
			
			st.execute(sql);
			
			rs.close();
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error:" + e.getMessage());
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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			st = con.createStatement();
			rs = st.executeQuery("select * from garda.suspect");	
			
			sql = "DELETE FROM garda.suspect " + "WHERE suspectID = '" + this.SuspectId +"';";
			st.execute(sql);
			
			rs.close();
			con.close();
		}
		catch(Exception e)
		{
		System.out.println("Error: " + e.getMessage());
		}
		
	}
	
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
					//suspectPPS, suspectName, suspectAddress, description, priorConvictions;
					//assigns the values from the result set to the class variables
										
						while(rs.next())
						{
							this.SuspectId = rs.getInt(1);
							//System.out.println(rs.getString(2));
							this.suspectPPS = (rs.getString(2));
							this.setName(rs.getString(3));
							this.setAddress(rs.getString(4));
							this.description = rs.getString(5);
							this.priorConvictions = rs.getString(6);
							this.currentStatus = rs.getString(7);
							this.EvidenceID = rs.getInt(8);
						}
						
						if(this.SuspectId == 0)
						{
							JOptionPane.showMessageDialog(null,"Suspect Not Found, Please Enter a valid suspect ID", "Suspect Not Found",  JOptionPane.ERROR_MESSAGE);
						}
						
					rs.close();
					con.close();
					
				}
				//catches errors thrown by database
				catch(Exception f)
				{
					System.out.println("Error: " + f.getMessage());
				}
	}
	
	public void updateSuspect()
	{
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			st = con.createStatement();
			
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
			
			con.close();
		}
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
		
			while(rs.next()){
				this.EvidenceID = rs.getInt(1);
			}
			
			if(EvidenceID == 0)
			{
				rs = st.executeQuery("SELECT MAX(EvidenceID) FROM garda.caseevidence");
				
				while(rs.next()){
					this.EvidenceID = rs.getInt(1)+1;
				}
				
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
