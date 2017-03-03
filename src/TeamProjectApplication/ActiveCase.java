package TeamProjectApplication;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ActiveCase implements DatabaseFunctionality{

	//Instance Varibales for use in methods
	private int caseID;
	private int crimeCode;
	private String dateTime, address, eirCode, activeStatus;;
	private String[] status = {"Open", "Active", "In Court", "Closed"};

	/**
	 * Constructor only sets default status, variables must be initialized using setter methods
	 */
	public ActiveCase()
	{
		activeStatus = status[0];
	}

	/**
	 * Setter method for crimeCode
	 * @param crimeCode
	 */
	public void setCrimeCode(int crimeCode)
	{
		this.crimeCode = crimeCode;
	}
	
	/**
	 * setter method for date and time
	 * @param dateTime
	 */
	public void setDateTime(String dateTime)
	{
		this.dateTime = dateTime;
	}
	
	/**
	 * Setter method for address
	 * @param address
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	 * setter method for eircode
	 * @param eirCode
	 */
	public void setEirCode(String eirCode)
	{
		this.eirCode = eirCode;
	}

	/**
	 * setter for active status
	 * @param status
	 */
	public void setActiveStatus(String status)
	{
		this.activeStatus = status;
	}

	/**
	 * Getter for CaseID
	 * @return
	 */
	public int getCaseID()
	{
		return this.caseID;
	}
	
	/**
	 * Getter for CrimeCode()
	 * @return
	 */
	public int getCrimeCode()
	{
		return this.crimeCode;
	}
	
	/**
	 * getter for DateTime
	 * @return
	 */
	public String getDate()
	{
		return this.dateTime;
	}
	
	/**
	 * getter for address
	 * @return
	 */
	public String getAddress()
	{
		return this.address;
	}
	
	/**
	 * getter for status
	 * @return
	 */
	public String getStatus()
	{
		return this.activeStatus;
	}
	
	/**
	 * getter for eircode
	 * @return
	 */
	public String getEircode()
	{
		return this.eirCode;
	}
	
	@Override
	public void addToDatabase() {
		//instance variables for use with SQL connection
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from statement
			rs = st.executeQuery("select * from garda.activecase");
			
			//create a statement to be used in the statement object
			sql = "INSERT INTO garda.activecase values(" + "'" + this.caseID + "', '"  + this.dateTime  +  "', '" + this.address + "', '" + this.activeStatus + "', '" + this.eirCode + "');";
			
			//uses sql string to execute the statement
			st.execute(sql);
			
			//closes result set and connection
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
	public void deleteFromDatabase(String identifier) {
		//instance variables for use with SQL connection
		Statement st;
		ResultSet rs;
		String sql;
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from statement
			rs = st.executeQuery("select * from garda.activecase");	
			
			//create a statement to be used in the statement object
			sql = "DELETE FROM garda.activecase " + "WHERE caseID = '" + identifier +"';";
			
			//uses sql string to execute the statement
			st.execute(sql);
			
			//closes result set and connection
			rs.close();
			con.close();
		}
		//catches errors thrown by database
		catch(Exception e)
		{			
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	/**
	 * method for assigning unique values to caseID variable
	 */
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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from statement, gets the highest value from all the case numbers
			rs = st.executeQuery("SELECT MAX(caseID) FROM garda.activeCase");
			
			//gets the value from the result set, increments it and assigns it to the new CaseID
			while(rs.next())
			{
				caseID = rs.getInt(1)+1;
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
	
	public void updateDatabase(String identifier)
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
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","Password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from statement, statement returns the row that is identified by the caseID entered by the user
			rs = st.executeQuery("SELECT * FROM garda.activeCase WHERE caseID = " + "'" + identifier +"'");
			
			//assigns the values from the result set to the class variables
			while(rs.next()){
				this.caseID = rs.getInt(1);
				this.crimeCode = rs.getInt(2);
				this.dateTime = rs.getString(3);
				this.address = rs.getString(4);
				this.activeStatus = rs.getString(5);
				this.eirCode = rs.getString(6);
			}
			
			//menu string for displaying in a joptionpane
			String Menu = "Please choose what you want to edit: " + "\n"
			+ "1: CrimeCode" + "\n"
			+ "2: Date and Time" + "\n"
			+ "3: Address" + "\n"
			+ "4: Status" + "\n"
			+ "5: Eircode" + "\n";
			
			//boolean for quitting, used in case user wants to edit more than one field
			boolean quit = false;
			
			//while statment for looping through menu options
			while(!quit)
			{
				//prints menu to screen
				int menuChoice = Integer.valueOf(JOptionPane.showInputDialog(Menu));
				//lets user edit crime code, code must exist in crimeCode table as it is a foreign key
				if (menuChoice == 1)
				{
					this.setCrimeCode(Integer.valueOf(JOptionPane.showInputDialog("Please enter the updated Crime Code")));
				}
				//lets user edit the date and time, must be done serpately as SQL requires specific formate for datetime
				else if (menuChoice == 2)
				{
					String date, time;
					date = JOptionPane.showInputDialog("Please enter the updated date the crime took place in the format yyyy-mm-dd");
					time = JOptionPane.showInputDialog("Please enter the updated time the crime took place in the 24 hour format hh:mm");
					date = date + " " + time;
					this.setDateTime(date);
				}
				//lets the user edit the address
				else if(menuChoice == 3)
				{
					this.setAddress(JOptionPane.showInputDialog("Please enter the Address of the area the crime took place"));
				}
				//lets the user edit the status, uses the status array at the top for options to chose from.
				else if(menuChoice == 4)
				{
					this.setActiveStatus(String.valueOf(JOptionPane.showInputDialog(null,null, "Please Select the updated status of the case", JOptionPane.QUESTION_MESSAGE,null,status, status[0])));
				}
				//lets the user edit the eircode.
				else if(menuChoice == 5)
				{
					this.setEirCode(JOptionPane.showInputDialog("Please enter the updated Eircode of the area the crime took place"));
				}
				//else for invalid options
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Choice Please choose again");
				}
				
				//confirmation box to check if user wants to edit another value
				int cont = JOptionPane.showConfirmDialog(null, "would you like to change another value?", "repeat?", JOptionPane.YES_NO_OPTION);
				
				//check to see if user selected no, if they did set quit to true to exit loop
				if (cont == JOptionPane.NO_OPTION)
				{
					quit = true;
				}
			}
			
			//sql statement that updates selected row with new values
			sql = "UPDATE garda.activecase SET " +
			"crimeCode = " + this.crimeCode +
			",date = '" + this.dateTime +
			"',address = '" + this.address + 
			"',status = '" + this.activeStatus +
			"',eirCode = '" + this.eirCode + "' WHERE caseID = '" + identifier + "'";
			
			//executes the above sql statement
			st.execute(sql);
			

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
}
