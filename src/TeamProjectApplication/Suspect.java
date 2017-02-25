package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class Suspect extends Person implements DatabaseFunctionality{
	
	private int SuspectId = 1;
	private String suspectPPS, suspectName, suspectAddress, description, priorConvictions;
	private String[] status = {"Suspect", "Fugitive", "Arrested", "Processed"};
	private String currentStatus;
	private JComboBox statusBox = new JComboBox(status);
	
	
	public Suspect()
	{
		super("Unkown", "Unknown", "Unkown");
		this.description = "Unkown";
		this.priorConvictions = "Unkown";
		SuspectId++;
		currentStatus = (String) JOptionPane.showInputDialog(null, "What is the current status of the suspect?", "Suspect Status", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
	
	}
	
	public Suspect(String suspectPPS, String suspectName, String suspectAddress, String description, String priorConvictions)
	{
		super(suspectPPS, suspectName, suspectAddress);
		SuspectId++;	
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
	public void setSuspectStatus()
	{
		this.currentStatus = (String)JOptionPane.showInputDialog(null, "What is the current status of the suspect?", "Suspect Status", JOptionPane.QUESTION_MESSAGE, null, status, status[0]);
	}
	
	/**
	 * getter for the criminal ID
	 * @return
	 */
	public int getSuspectID()
	{
		return this.SuspectId;
	}
	
	/**
	 * Getter for the pps Number, mostly used to confirm changes
	 */
	@Override
	public String getPPS()
	{
		return this.ppsNumber;
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
	public void addToDatabase() {
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			st = con.createStatement();
			rs = st.executeQuery("select * from garda.suspect");
			
			sql = "INSERT INTO garda.suspect values(" + "'" + this.SuspectId + "', '" + this.ppsNumber + "', '" + this.name + "', '"
					+ this.address +  "', '" + this.description + "', '"+ this.priorConvictions + "', '" + this.currentStatus+ "');";
			
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
}
