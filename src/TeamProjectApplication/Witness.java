package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Witness extends Person implements DatabaseFunctionality{
	
	String contactInfo;
	int EvidenceID;
	
	public Witness()
	{
		super(null, null, null);
	}
	
	public void setWitnessName(String nameIn)
	{
		this.name = nameIn;
	}
	
	public void setPPs(String ppsIn)
	{
		this.ppsNumber = ppsIn;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setContactInfo(String contactInfoIn)
	{
		this.contactInfo = contactInfoIn;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getPPS()
	{
		return this.ppsNumber;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public String getContactInfo()
	{
		return this.contactInfo;
	}
	
	public int getEvidenceID()
	{
		return this.EvidenceID;
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
			
			sql = "INSERT INTO garda.witness values(" + "'" + this.ppsNumber + "', '" + this.name  + "', '"
					+ this.address +  "', '" + this.contactInfo + "', "+ this.EvidenceID + ");";
			
			
			st.execute(sql);
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error:" + e.getMessage());
		}
		
	}
	
	public void UpdateWitness(String ppsIN)
	{
		
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			st = con.createStatement();
			
			sql = "UPDATE garda.witness SET " +
			"witnessPPS = '" + this.ppsNumber
			+"',name = '" + this.name
			+"',address = '" + this.address
			+"',contactInfo = '" + this.contactInfo
			+"',EvidenceID = '" + this.EvidenceID +
			"' WHERE witnessPPS = '" + ppsIN +"';";
			st.execute(sql);
			
			con.close();
		}
		catch(Exception e)
		{
		System.out.println("Error: " + e.getMessage());
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
	
	public void getWitness(String pps)
	{
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			st = con.createStatement();
			rs = st.executeQuery("Select * from garda.witness where witnessPPS = '" + pps + "';");
			
			while(rs.next())
			{
				this.ppsNumber = rs.getString(1);
				this.name = rs.getString(2);
				this.address = rs.getString(3);
				this.contactInfo = rs.getString(4);
				this.EvidenceID = rs.getInt(5);
			}
			
			rs.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println("Error:" + e.getMessage());
		}
	}


	@Override
	public void deleteFromDatabase(String identifier) 
	{
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			st = con.createStatement();
			
			sql = "Delete from garda.witness where witnessPPS = '" + identifier + "';";
			
			
			st.execute(sql);
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error:" + e.getMessage());
		}
		
		
	}
}
