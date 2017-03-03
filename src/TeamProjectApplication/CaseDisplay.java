package TeamProjectApplication;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableModel;

import java.awt.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;

public class CaseDisplay extends JPanel{
	
	private Border b1, b2;
	private JTable caseIDTable, assignedGarda, crimes, suspects, witness, forensics, vehicles, garda;
	private JPanel evidence, activecase, caseCrimes, caseGuards, container, button; 
	protected int caseIDEntered;
	private JScrollPane caseTable, forensicsTable, suspectTable, witnessTable, vehicleTable, guardTable, crimeTable;
	private JLabel labelCaseID, labelEvidence, labelForensics, labalWitness, labelSuspects, labelVehicles, labelGuard, labelCrime;
	protected JButton back;
	private Statement st;
	private ResultSet rs;		
	private String sql;
	
	public CaseDisplay()
	{
		this.setLayout(new BorderLayout());
		//Integer.valueOf(JOptionPane.showInputDialog("Please enter the id of the case you want to view"));
		//caseIDEntered=Integer.valueOf(JOptionPane.showInputDialog("Please enter the id of the case you want to view"));
		//this.caseIDEntered = caseIn;
		b1  = new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new LineBorder(Color.black, 1), new EmptyBorder(5,5,5,5)));
		b2 = new CompoundBorder(new EmptyBorder(5,5,5,5), new CompoundBorder(new EtchedBorder(), new EmptyBorder(5,5,5,5)));
		caseIDTable = new JTable();
		garda = new JTable();
		crimes = new JTable();
		
		//instance variables for use with SQL connection
		
		
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
					
			sql = "SELECT crime.crimeCode, crimeName from crime, committedcrime where committedcrime.CaseID =  " + caseIDEntered  
					+ " and committedcrime.CrimeCode = crime.crimeCode;";
			
			rs = st.executeQuery(sql);
			
			crimes.setModel(DbUtils.resultSetToTableModel(rs));
			
			sql ="select garda.idNo, Fullname from garda.garda, assignedgarda where assignedgarda.caseID = " + caseIDEntered + " and assignedgarda.gardaID = garda.idNo; ";
			//closes result set and connection
			
			rs = st.executeQuery(sql);
			
			garda.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			con.close();
					
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
				
		activecase = new JPanel();
		
		//back.setSize(30, 30);
		activecase.setLayout(new GridLayout(0,2, 10, 10));
		activecase.setBorder(b1);
		
		labelCaseID = new JLabel("Currently Viewing Case: " );
		labelCaseID.setFont(new Font("Sans-Serif", 0, 25));
		
		caseIDTable.getColumnModel().getColumn(1).setPreferredWidth(250);
		caseTable = new JScrollPane(caseIDTable);
		caseTable.setPreferredSize(new Dimension(70, 39));
		
		activecase.add(labelCaseID);
		//activecase.add(back);
		activecase.add(caseTable);
		
		caseCrimes = new JPanel();
		caseCrimes.setLayout(new GridLayout(0,1, 0, 5));

		labelGuard = new JLabel("Gardaí assigned to this case:");
		labelGuard.setFont(new Font("Sans-Serif", 0, 25));
		guardTable = new JScrollPane(garda);
		guardTable.setPreferredSize(new Dimension(50, 39));
		caseCrimes.add(labelGuard);
		caseCrimes.add(guardTable);
		
		labelCrime = new JLabel("Crimes Committed in this case: ");
		labelCrime.setFont(new Font("Sans-Serif", 0, 25));
		caseCrimes.add(labelCrime);
		crimeTable = new JScrollPane(crimes);
		crimeTable.setPreferredSize(new Dimension(50, 39));
		caseCrimes.add(crimeTable);
		
		caseGuards = new JPanel();
		caseGuards.setLayout(new BorderLayout());
		caseGuards.setBorder(b2);
		caseGuards.add(caseCrimes);
		
		forensics = new JTable();
		suspects = new JTable();
		witness = new JTable();
		vehicles = new JTable();

		try
		{
			
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			//create result set from statement
			rs = st.executeQuery("SELECT BiologicalEvidence, prints, TrackEvidence, DigitalEvidence, ToolMarkEvidence, NarcoticEvidence, FirearmEvidence "
					+ "from forensics, caseevidence where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = forensics.evidenceID;");
					
			//create a statement to be used in the statement object
			forensics.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			sql = "SELECT suspectPPS, name, address, description, priorConvictions, status from suspect s, caseevidence "
					+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = s.EvidenceID;";
			
			rs = st.executeQuery(sql);
			
			suspects.setModel(DbUtils.resultSetToTableModel(rs));
			
			sql = "SELECT witnessPPS, name, address, contactInfo from witness, caseevidence "
					+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = witness.EvidenceID;";
			
			rs = st.executeQuery(sql);
			
			witness.setModel(DbUtils.resultSetToTableModel(rs));
					
			sql = "SELECT reg, type, make, model, colour, description from vehicle, caseevidence "
					+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = vehicle.EvidenceID;";
			
			rs = st.executeQuery(sql);
			
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
		
		evidence = new JPanel();
		evidence.setLayout(new GridLayout(0,1, 0, 0));
		evidence.setBorder(b2);
		
		labelEvidence = new JLabel("Evidence for this case:");
		labelEvidence.setAlignmentY(CENTER_ALIGNMENT);
		labelEvidence.setFont(new Font("Sans-Serif", 0, 25));
		
		evidence.add(labelEvidence);
		
	
		forensicsTable = new JScrollPane(forensics);
		forensicsTable.setPreferredSize(new Dimension(50, 39));
		suspects.getColumnModel().getColumn(3).setPreferredWidth(250);
		suspectTable = new JScrollPane(suspects);
		witnessTable = new JScrollPane(witness);
		vehicleTable = new JScrollPane(vehicles);
		
		labelForensics = new JLabel("Forensic Evidence");
		labelSuspects = new JLabel("Possible Suspects");
		labalWitness = new JLabel("Current Witnesses");
		labelVehicles = new JLabel("Monitored Vehicles for this case");
		
		evidence.add(labelForensics);
		evidence.add(forensicsTable);
		
		evidence.add(labelSuspects);
		evidence.add(suspectTable);
		
		evidence.add(labalWitness);
		evidence.add(witnessTable);
		
		evidence.add(labelVehicles);
		evidence.add(vehicleTable);
		//evidence.add(back);
		
		container = new JPanel();
		container.setLayout(new BorderLayout());
		container.setBorder(b1);
		container.add(caseGuards, BorderLayout.NORTH);
		container.add(evidence, BorderLayout.CENTER);
		add(activecase, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
		
		back = new JButton("Return To Case Menu");
		back.setPreferredSize(new Dimension(180, 30));
		button = new JPanel();
		button.setBorder(new EmptyBorder(0,5,5,5));
		button.add(back);
		add(button, BorderLayout.SOUTH);
	}
	
	public void getInt(int caseIn)
	{
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
			rs = st.executeQuery("SELECT BiologicalEvidence, prints, TrackEvidence, DigitalEvidence, ToolMarkEvidence, NarcoticEvidence, FirearmEvidence "
					+ "from forensics, caseevidence where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = forensics.evidenceID;");
					
			//create a statement to be used in the statement object
			forensics.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			sql = "SELECT suspectPPS, name, address, description, priorConvictions, status from suspect s, caseevidence "
					+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = s.EvidenceID;";
			
			rs = st.executeQuery(sql);
			
			suspects.setModel(DbUtils.resultSetToTableModel(rs));
			
			sql = "SELECT witnessPPS, name, address, contactInfo from witness, caseevidence "
					+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = witness.EvidenceID;";
			
			rs = st.executeQuery(sql);
			
			witness.setModel(DbUtils.resultSetToTableModel(rs));
					
			sql = "SELECT reg, type, make, model, colour, description from vehicle, caseevidence "
					+ "where caseevidence.caseID = " + caseIDEntered + " and caseevidence.EvidenceID = vehicle.EvidenceID;";
			
			rs = st.executeQuery(sql);
			
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
					
			sql = "SELECT crime.crimeCode, crimeName from crime, committedcrime where committedcrime.CaseID =  " + caseIDEntered  
					+ " and committedcrime.CrimeCode = crime.crimeCode;";
			
			rs = st.executeQuery(sql);
			
			crimes.setModel(DbUtils.resultSetToTableModel(rs));
			
			sql ="select garda.idNo, Fullname from garda.garda, assignedgarda where assignedgarda.caseID = " + caseIDEntered + " and assignedgarda.gardaID = garda.idNo; ";
			//closes result set and connection
			
			rs = st.executeQuery(sql);
			
			garda.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			con.close();
					
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
		//this.repaint();
	}
//	public static void main (String [] args)
//	{
//		CaseDisplay frame = new CaseDisplay(2);
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	    frame.setTitle("Garda Case Tracking Database");
//	    frame.setSize(1000,800);
//	    frame.setLocationRelativeTo(null);
//	    frame.setVisible(true);
//		
//	}
}
