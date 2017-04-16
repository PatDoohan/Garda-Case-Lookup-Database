package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;

public class CaseDisplay extends JPanel{
	
	//instance variables and components used to build panel and display data
	private Border b1, b2;
	private JTable caseIDTable, crimes, suspects, witness, forensics, vehicles, garda;
	private JPanel evidence, activecase, caseCrimes, caseGuards, container, button; 
	//this variable is set as protected as the value will be entered from the window handler.
	protected int caseIDEntered;
	private JScrollPane caseTable, forensicsTable, suspectTable, witnessTable, vehicleTable, guardTable, crimeTable;
	private JLabel labelCaseID, labelEvidence, labelForensics, labalWitness, labelSuspects, labelVehicles, labelGuard, labelCrime;
	protected JButton back;
	
	//instance variables for use with SQL connection
	private Statement st;
	private ResultSet rs;		
	private String sql;
	
	//Main class for case display
	public CaseDisplay()
	{
		//sets the layout to border layout as the default layout for a jpanel is flow.
		this.setLayout(new BorderLayout());
		
		//creates two new compound borders for wrapping the data in the panel;
		b1  = new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new LineBorder(Color.black, 1), new EmptyBorder(5,5,5,5)));
		b2 = new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5)));
		
		//creates 3 new tables for the top panels which include the case information, assigned gardaí and the crimes involved in the case
		caseIDTable = new JTable();
		garda = new JTable();
		crimes = new JTable();
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from statement
			rs = st.executeQuery("select * from garda.activecase Where caseID = " + caseIDEntered);
					
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the case table
			caseIDTable.setModel(DbUtils.resultSetToTableModel(rs));
				
			//sql statement to collect the crime codes related to this case from the database.
			sql = "SELECT crime.crimeCode, crimeName from crime, committedcrime where committedcrime.CaseID =  " + caseIDEntered  
			+ " and committedcrime.CrimeCode = crime.crimeCode;";
			
			//creating a result set from the above sql
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the crime table related to the case entered
			crimes.setModel(DbUtils.resultSetToTableModel(rs));
			
			//sql statement to collect the assigned garda related to this case from the database.
			sql ="select garda.idNo, Fullname from garda.garda, assignedgarda where assignedgarda.caseID = " + caseIDEntered + " and assignedgarda.gardaID = garda.idNo; ";

			//creating a result set from the above sql
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the garda table related to the case entered
			garda.setModel(DbUtils.resultSetToTableModel(rs));
			
			//closes result set and connection
			rs.close();
			con.close();
					
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
				
		//creating the panel that will hold the case information
		activecase = new JPanel();
		activecase.setLayout(new GridLayout(0,2, 10, 10));
		activecase.setBorder(b1);
		
		//creating the label for viewing the current case
		labelCaseID = new JLabel("Currently Viewing Case: " );
		labelCaseID.setFont(new Font("Sans-Serif", 0, 25));
		
		//creating the tables with scroll panes and preferred dimensions.
		caseIDTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		caseTable = new JScrollPane(caseIDTable);
		caseTable.setPreferredSize(new Dimension(70, 39));
		
		//adds the case label and table to the Panel
		activecase.add(labelCaseID);
		activecase.add(caseTable);
		
		//creating the panel that will hold the crimes and garda tables
		caseCrimes = new JPanel();
		caseCrimes.setLayout(new GridLayout(0,1, 0, 5));

		//creating the label and setting the preferred dimensions of the garda table and then adding them to the case crimes panel
		labelGuard = new JLabel("Gardaí assigned to this case:");
		labelGuard.setFont(new Font("Sans-Serif", 0, 25));
		guardTable = new JScrollPane(garda);
		guardTable.setPreferredSize(new Dimension(50, 39));
		caseCrimes.add(labelGuard);
		caseCrimes.add(guardTable);
		
		//creating the label and setting the preferred dimensions of the crimes table and then adding them to the case crimes panel
		labelCrime = new JLabel("Crimes Committed in this case: ");
		labelCrime.setFont(new Font("Sans-Serif", 0, 25));
		caseCrimes.add(labelCrime);
		crimeTable = new JScrollPane(crimes);
		crimeTable.setPreferredSize(new Dimension(50, 39));
		caseCrimes.add(crimeTable);
		
		//creates a new panel to wrap the border around the case crimes panel
		caseGuards = new JPanel();
		caseGuards.setLayout(new BorderLayout());
		caseGuards.setBorder(b2);
		caseGuards.add(caseCrimes);
		
		//creates the tables that will hold the information from the forensic, 
		forensics = new JTable();
		forensics.setFont(new Font("Sans-Serif", 0, 14));
		suspects = new JTable();
		suspects.setFont(new Font("Sans-Serif", 0, 14));
		witness = new JTable();
		witness.setFont(new Font("Sans-Serif", 0, 14));
		vehicles = new JTable();
		vehicles.setFont(new Font("Sans-Serif", 0, 14));

		try
		{
			
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from a statement, gets all forensic evidence that is related to the entered case ID
			rs = st.executeQuery("SELECT BiologicalEvidence, prints, TrackEvidence, DigitalEvidence, ToolMarkEvidence, NarcoticEvidence, FirearmEvidence "
					+ "from forensics, caseevidence where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = forensics.evidenceID;");
					
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			forensics.setModel(DbUtils.resultSetToTableModel(rs));
			
			//sql statement to retrieve all the related suspects for the entered case id
			sql = "SELECT suspectPPS, name, address, description, priorConvictions, status from suspect s, caseevidence "
					+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = s.EvidenceID;";
			
			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			suspects.setModel(DbUtils.resultSetToTableModel(rs));
			
			//sql statement to retrieve all the related witnesses for the entered case id
			sql = "SELECT witnessPPS, name, address, contactInfo from witness, caseevidence "
					+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = witness.EvidenceID;";
			
			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			witness.setModel(DbUtils.resultSetToTableModel(rs));
					
			//sql statement to retrieve all the related vehicles for the entered case id
			sql = "SELECT reg, type, make, model, colour, description from vehicle, caseevidence "
					+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = vehicle.EvidenceID;";
			

			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			vehicles.setModel(DbUtils.resultSetToTableModel(rs));
			
			//closes result set and connection
			rs.close();
			con.close();
					
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
		//creates the evidence panel that will hold the labels and tables for forensic, suspect, witness and vehicle data.
		evidence = new JPanel();
		evidence.setLayout(new GridLayout(0,1, 0, 0));
		evidence.setBorder(b2);
		
		//creates the label for evidence
		labelEvidence = new JLabel("Evidence for this case:");
		labelEvidence.setAlignmentY(CENTER_ALIGNMENT);
		labelEvidence.setFont(new Font("Sans-Serif", 0, 25));
		
		//adds the label to the panel
		evidence.add(labelEvidence);
		
		//creates the tables for the evidence panel
		forensicsTable = new JScrollPane(forensics);
		forensicsTable.setPreferredSize(new Dimension(50, 39));
		suspects.getColumnModel().getColumn(3).setPreferredWidth(250);
		suspectTable = new JScrollPane(suspects);
		witnessTable = new JScrollPane(witness);
		vehicleTable = new JScrollPane(vehicles);
		
		//creates the labels for the evidence panel
		labelForensics = new JLabel("Forensic Evidence");
		labelSuspects = new JLabel("Possible Suspects");
		labalWitness = new JLabel("Current Witnesses");
		labelVehicles = new JLabel("Monitored Vehicles for this case");
		
		
		//adds the tables and labels to the panel
		evidence.add(labelForensics);
		evidence.add(forensicsTable);
		evidence.add(labelSuspects);
		evidence.add(suspectTable);
		evidence.add(labalWitness);
		evidence.add(witnessTable);
		evidence.add(labelVehicles);
		evidence.add(vehicleTable);
		//evidence.add(back);
		
		//creates the container table that will hold all the information from the case
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(b1);
		container.add(caseGuards, BorderLayout.NORTH);
		container.add(evidence, BorderLayout.CENTER);
		add(activecase, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
		
		//create the back button which will allos the user to return to the previous menu
		back = new JButton("Return To Case Menu");
		back.setPreferredSize(new Dimension(180, 30));
		button = new JPanel();
		button.setBorder(new EmptyBorder(0,5,5,5));
		button.add(back);
		add(button, BorderLayout.SOUTH);
	}
	
	//create a method that will populate the tables with information based on the case ID entered by the user.
	public void getInt(int caseIn)
	{
		//sets the entered case id as the class variable
		this.caseIDEntered = caseIn;
		
		try
		{
			
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from a statement, gets all forensic evidence that is related to the entered case ID
			rs = st.executeQuery("SELECT BiologicalEvidence, prints, TrackEvidence, DigitalEvidence, ToolMarkEvidence, NarcoticEvidence, FirearmEvidence "
					+ "from forensics, caseevidence where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = forensics.evidenceID;");
					
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			forensics.setModel(DbUtils.resultSetToTableModel(rs));
						
			//sql statement to retrieve all the related suspects for the entered case id
			sql = "SELECT suspectPPS, name, address, description, priorConvictions, status from suspect s, caseevidence "
			+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = s.EvidenceID;";
			
			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			suspects.setModel(DbUtils.resultSetToTableModel(rs));
			
			//sql statement to retrieve all the related witnesses for the entered case id
			sql = "SELECT witnessPPS, name, address, contactInfo from witness, caseevidence "
			+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = witness.EvidenceID;";
			
			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			witness.setModel(DbUtils.resultSetToTableModel(rs));
					
			//sql statement to retrieve all the related vehicles for the entered case id
			sql = "SELECT reg, type, make, model, colour, description from vehicle, caseevidence "
			+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = vehicle.EvidenceID;";
			
			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			vehicles.setModel(DbUtils.resultSetToTableModel(rs));
			
			//closes result set and connection
			rs.close();
			con.close();
					
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from statement
			rs = st.executeQuery("select * from garda.activecase Where caseID = " + caseIDEntered);
					
			//create a statement to be used in the statement object
			caseIDTable.setModel(DbUtils.resultSetToTableModel(rs));
					
			//sql statement to collect the crime codes related to this case from the database.
			sql = "SELECT crime.crimeCode, crimeName from crime, committedcrime where committedcrime.CaseID =  " + caseIDEntered  
					+ " and committedcrime.CrimeCode = crime.crimeCode;";
			
			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			crimes.setModel(DbUtils.resultSetToTableModel(rs));
			
			//sql statement to collect the assigned garda related to this case from the database.
			sql ="select garda.idNo, Fullname from garda.garda, assignedgarda where assignedgarda.caseID = " + caseIDEntered + " and assignedgarda.gardaID = garda.idNo; ";

			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			garda.setModel(DbUtils.resultSetToTableModel(rs));
			
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
}
