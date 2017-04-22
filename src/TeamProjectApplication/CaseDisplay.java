package TeamProjectApplication;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;

public class CaseDisplay extends JPanel{
	
	//instance variables and components used to build panel and display data
	private Border b1, b2;
	private JTable caseIDTable, crimes, garda;
	private JPanel evidence, activecase, caseCrimes, caseGuards, container, button, evidenceButtons; 
	//this variable is set as protected as the value will be entered from the window handler.
	protected int caseIDEntered;
	private JScrollPane caseTable, guardTable, crimeTable;
	private JLabel labelCaseID, labelEvidence, labelGuard, labelCrime;
	protected JButton back, viewSuspects, viewForensics, viewWitnesses, viewVehicles;
	
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
		
		
		
		viewSuspects = new JButton("View Suspects");
		viewSuspects.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				viewSuspect frame = new viewSuspect(caseIDEntered);
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setTitle("Suspect Viewer");
			    frame.setSize(900, 350);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			}
					
		});
		
		
		viewWitnesses = new JButton("View Witnesses");
		viewWitnesses.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewWitness frame = new ViewWitness(caseIDEntered);
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setTitle("Suspect Viewer");
			    frame.setSize(900, 350);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			}
					
		});
		
		viewForensics = new JButton("View Forensics");
		viewForensics.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewForensics frame = new ViewForensics(caseIDEntered);
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setTitle("Forensics Viewer");
			    frame.setSize(900, 350);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			}
					
		});
		
		viewVehicles = new JButton("View Vehicles");
		viewVehicles.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ViewVehicle frame = new ViewVehicle(caseIDEntered);
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    frame.setTitle("Vehicle Viewer");
			    frame.setSize(900, 400);
			    frame.setLocationRelativeTo(null);
			    frame.setVisible(true);
			}
					
		});
		
		viewSuspects.setSize(new Dimension(150, 100));
		viewForensics.setSize(new Dimension(150, 100));
		viewWitnesses.setSize(new Dimension(150, 100));
		viewVehicles.setSize(new Dimension(150, 100));
		evidenceButtons = new JPanel();
		evidenceButtons.setLayout(new GridLayout(1,0,10,10));
		evidenceButtons.add(viewSuspects);
		evidenceButtons.add(viewForensics);
		evidenceButtons.add(viewWitnesses);
		evidenceButtons.add(viewVehicles);
		
		evidence.add(evidenceButtons);
		
		//creates the container table that will hold all the information from the case
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(b1);
		container.add(caseGuards, BorderLayout.CENTER);
		container.add(evidence, BorderLayout.SOUTH);
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
			//create result set from statement
			rs = st.executeQuery("select * from garda.activecase Where caseID = " + caseIDEntered);
					
			//create a statement to be used in the statement object
			caseIDTable.setModel(DbUtils.resultSetToTableModel(rs));
			caseIDTable.setFont(new Font("Sans-Serif", 0, 14));
					
			//sql statement to collect the crime codes related to this case from the database.
			sql = "SELECT crime.crimeCode, crimeName from crime, committedcrime where committedcrime.CaseID =  " + caseIDEntered  
					+ " and committedcrime.CrimeCode = crime.crimeCode;";
			
			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			crimes.setModel(DbUtils.resultSetToTableModel(rs));
			crimes.setFont(new Font("Sans-Serif", 0, 14));
			//sql statement to collect the assigned garda related to this case from the database.
			sql ="select garda.idNo, Fullname, barracksId, Status from garda.garda, assignedgarda where assignedgarda.caseID = " + caseIDEntered + " and assignedgarda.gardaID = garda.idNo; ";

			//creates a result set from the above sql statement
			rs = st.executeQuery(sql);
			
			//uses the rs2xml jar to build a table from the result set, this table will hold the values from the above result set
			garda.setModel(DbUtils.resultSetToTableModel(rs));
			garda.setFont(new Font("Sans-Serif", 0, 14));
			
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
