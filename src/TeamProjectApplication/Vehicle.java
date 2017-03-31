package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Vehicle implements DatabaseFunctionality{

	protected String reg, type, make, model, colour, description;
	protected int EvidenceID;
	
	public Vehicle()
	{
		this.EvidenceID = 0;
	}
	
	public void setReg(String reg)
	{
		this.reg = reg;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public void setMake(String make)
	{
		this.make = make;
	}
	
	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void setColour(String colour)
	{
		this.colour = colour;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getReg()
	{
		return this.reg;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public String getMake()
	{
		return this.make;
	}
	
	public String getModel()
	{
		return this.model;
	}
	
	public String getColour()
	{
		return this.colour;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void getVehicle(String regIn)
	{
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			st = con.createStatement();
			rs = st.executeQuery("select * from garda.vehicle where reg = '" + regIn+ "';");
			
			while(rs.next())
			{
				this.reg = rs.getString(1);
				this.type = (rs.getString(2));
				this.make =(rs.getString(3));
				this.model =(rs.getString(4));
				this.colour = rs.getString(5);
				this.description = rs.getString(6);
				this.EvidenceID = rs.getInt(7);
			}
			
			rs.close();
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
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
			rs = st.executeQuery("select * from garda.vehicle");
			
			sql = "INSERT INTO garda.vehicle values(" + "'" + this.reg + "', '" + this.type + "', '" + this.make + "', '"
					+ this.model +  "', '" + this.colour + "', '"+ this.description + "'," + this.EvidenceID + ");";
			
			//System.out.println(sql);
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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			st = con.createStatement();
			rs = st.executeQuery("select * from garda.vehicle");	
			
			sql = "DELETE FROM garda.vehicle " + "WHERE reg = '" + this.reg +"';";
			st.execute(sql);
			
			rs.close();
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
	
	public void updateVehicle()
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
			
			//reg, type, make, model, colour, desc, caseIn;
				
			sql = "UPDATE garda.vehicle SET " +
					"reg = '" + this.reg
					+"',type = '" + this.type
					+"',make = '" + this.make
					+"',model = '" + this.model
					+"',colour = '" + this.colour
					+"',description = '" + this.description
					+"',EvidenceID = '" + this.EvidenceID +
					"' WHERE reg = '" + this.reg +"';";
					st.execute(sql);
				st.execute(sql);
				
			
			
			
			//closes result set and connection
			
			con.close();
		
		}
		//catches errors thrown by database
		catch(Exception f)
		{
			System.out.println("Error: " + f.getMessage());
		}
	}

}
