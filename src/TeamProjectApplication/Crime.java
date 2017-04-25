package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Crime implements DatabaseFunctionality{
	
	//instance variables to be used in
	protected int crimeCode;
	String crimeName, crimeDescription;

	//setter method for crime code
	public void setCrimeCode(int crimeCode)
	{
		this.crimeCode = crimeCode;
	}
	
	//setter method for crime name
	public void setCrimeName(String crimeName)
	{
		this.crimeName = crimeName;
	}
	
	//setter method for crime description
	public void setCrimeDescription(String crimeDescription)
	{
		this.crimeDescription = crimeDescription;
	}
	
	//getter method for crime code
	public int getCrimeCode()
	{
		return this.crimeCode;
	}

	//getter method for crime name
	public String getCrimeName()
	{
		return this.crimeName;
	}
	
	//getter method for crime description
	public String getCrimeDescription()
	{
		return this.crimeDescription;
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
			
			/* create a statement to be used in the statement object, this statements creates a full
			 * SQL statlement that will insert all the data from the class into the correct columns in the database*/
			sql = "INSERT INTO garda.crime values(" + "'" + this.crimeCode + "', '" + this.crimeName + "', '" + this.crimeDescription  + "');";
			
			//uses sql string to execute the statement
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
	 * Implemented method from database functionality interface to delete a record from the database
	 * based on the unique identifier from that record, in this case it is the crime code.
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
			
			//creates a sql statement that uses the unique identifier entered to delete a specific row from the datatbase.
			sql = "DELETE FROM garda.crime " + "WHERE crimeCode = '" + this.crimeCode +"';";
			
			//uses sql string to execute the statement
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
	
	public int getCrime(String indentifier)
	{
		//instance variables for use with SQL connection
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
			rs = st.executeQuery("Select * from garda.crime where crimeCode = '" + indentifier + "';");
			
			
			int count = 0;
			//assigns the retrieved data to the class variables
			while(rs.next())
			{
				this.crimeCode = rs.getInt(1);
				this.crimeName = rs.getString(2);
				this.crimeDescription = rs.getString(3);
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
	
	public void updateCrime()
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
			
			//sql statement for updating the values in the database from the values inputed in the form
			sql = "UPDATE garda.Crime SET " +
			"crimeCode = '" + this.crimeCode
			+"',crimeName = '" + this.crimeName
			+"',crimeDescription = '" + this.crimeDescription 
			+ "' WHERE crimeCode = '" + crimeCode +	"';";
			
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
	
	public int checkCrime(String Identifier)
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
			if(Identifier.equals(null))
			{
				return 2;
			}
			
			else
			{
				rs = st.executeQuery("Select * from garda.crime where crimeCode = '" + Identifier + "';");
				
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
				
				//closes the connection
				con.close();
			}
	
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
	
		return -1;
	}
	
	public int linkToCase(String Identifier)
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
			rs = st.executeQuery("Select * from garda.committedcrime where crimeCode = '" + this.crimeCode + " AND caseID = "+ Identifier +  "';");
			int crimeretrieved = 0;
			int caseidretrieved = 0;
			
			while(rs.next())
			{
				crimeretrieved = rs.getInt(1);
				caseidretrieved = rs.getInt(2);
			}
			
			if(Identifier.equals(null))
			{
			}
			
			else if(crimeretrieved == this.crimeCode && caseidretrieved == Integer.valueOf(Identifier))
			{
				JOptionPane.showMessageDialog(null,"Crime is already assigned to this case", "Crime already assigned to case",  JOptionPane.ERROR_MESSAGE);
			}
			
			else
			{
				sql = "Insert into garda.committedcrime Values('"+ this.crimeCode + "','" + Identifier + "');";
				
				st.execute(sql);
				
				
				//closes the connection
				con.close();
			}
	
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
		return -1;
		
	}
}
