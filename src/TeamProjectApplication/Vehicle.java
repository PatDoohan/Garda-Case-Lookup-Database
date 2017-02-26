package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Vehicle implements DatabaseFunctionality{

	protected String reg, type, make, model, colour, description;
	
	public Vehicle(String reg, String type, String make, String model, String colour, String description)
	{
		this.reg = reg;
		this.type = type;
		this.make = make;
		this.model = model;
		this.colour = colour;
		this.description = description;
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
			rs = st.executeQuery("select * from garda.vehicle");
			
			sql = "INSERT INTO garda.vehicle values(" + "'" + this.reg + "', '" + this.type + "', '" + this.make + "', '"
					+ this.model +  "', '" + this.colour + "', '"+ this.description + "');";
			
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

}
