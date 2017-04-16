package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Vehicle implements DatabaseFunctionality{

	protected String reg, type, make, model, colour, description;
	protected int EvidenceID;
	
	//default constructor for vehicle
	public Vehicle()
	{
		this.EvidenceID = 0;
	}
	
	/**
	 * setter for vehicle reg
	 * @param reg
	 */
	public void setReg(String reg)
	{
		this.reg = reg;
	}
	
	/**
	 * setter for vehicle type
	 * @param type
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	
	/**
	 * setter for vehicle make
	 * @param make
	 */
	public void setMake(String make)
	{
		this.make = make;
	}
	
	/**
	 * setter for vehicle model
	 * @param model
	 */
	public void setModel(String model)
	{
		this.model = model;
	}
	
	/**
	 * setter for vehicle colour
	 * @param colour
	 */
	public void setColour(String colour)
	{
		this.colour = colour;
	}
	
	/**
	 * setter for vehicle description
	 * @param description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * getter for vehicle reg
	 * @return
	 */
	public String getReg()
	{
		return this.reg;
	}
	
	/**
	 * getter for vehicle type
	 * @return
	 */
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * getter for vehicle make
	 * @return
	 */
	public String getMake()
	{
		return this.make;
	}
	
	/**
	 * getter for vehicle model
	 * @return
	 */
	public String getModel()
	{
		return this.model;
	}
	
	/**
	 * getter for vehicle colour
	 * @return
	 */
	public String getColour()
	{
		return this.colour;
	}
	
	/**
	 * getter for vehicle description
	 * @return
	 */
	public String getDescription()
	{
		return this.description;
	}

	//method for getting the vehicle information from the database and assigning it to the class variables
	public void getVehicle(String regIn)
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
			//creates a result set from an sql statement that retrieves all data based on where the vehicle reg entered
			rs = st.executeQuery("select * from garda.vehicle where reg = '" + regIn+ "';");
			
			//assigns the values from the result set to the class variables				
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
			
			//closes the result set and the connection
			rs.close();
			con.close();
			
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	}
	
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
			
			//sql statement for inserting data into the vehicle table based on the data entered in the form
			sql = "INSERT INTO garda.vehicle values(" + "'" + this.reg + "', '" + this.type + "', '" + this.make + "', '"
					+ this.model +  "', '" + this.colour + "', '"+ this.description + "'," + this.EvidenceID + ");";
			
			//System.out.println(sql);
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
			
			//sql statment for deleting a record from the vehicle table based on the reg entered
			sql = "DELETE FROM garda.vehicle " + "WHERE reg = '" + identifier +"';";
			
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
	
	public void updateVehicle()
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

			//creates an sql statement that updates the values in the database to the values inputed by the user
			sql = "UPDATE garda.vehicle SET " +
			"reg = '" + this.reg
			+"',type = '" + this.type
			+"',make = '" + this.make
			+"',model = '" + this.model
			+"',colour = '" + this.colour
			+"',description = '" + this.description
			+"',EvidenceID = '" + this.EvidenceID +
			"' WHERE reg = '" + this.reg +"';";
			
			//executes the above sql statement
			st.execute(sql);
	
					
			//closes the connection
			con.close();
		
		}
		//catches errors thrown by database
		catch(Exception f)
		{
			System.out.println("Error: " + f.getMessage());
		}
	}

}
