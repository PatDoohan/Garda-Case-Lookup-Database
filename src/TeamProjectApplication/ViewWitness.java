package TeamProjectApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

public class ViewWitness extends JFrame{
	
	private JTable witness;
	private JScrollPane witnessTable;
	private int caseID;
	private JButton close;
	private JPanel container, buttons;
	private JLabel caseLabel;
	private Statement st;
	private String sql;
	private ResultSet rs;
	
	public ViewWitness(int caseNumber)
	{
		caseID = caseNumber;
		witness = new JTable();
		container = new JPanel();
		container.setLayout(new GridLayout(0,1));
		container.setBorder(new EmptyBorder(20,20,20,20));
		
		try
		{
			//Load the JDBC driver, Initialize a driver to open a communications channel with the database.
			Class.forName("com.mysql.jdbc.Driver");
			//connection for MYSQL workbench.
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/garda?autoReconnect=true&useSSL=false","root","password");
			//Create Statement object	
			st = con.createStatement();
			
			sql = "SELECT witnessPPS, name, address, contactInfo from witness, caseevidence "
					+ "where caseevidence.caseID = " + caseID + " and caseevidence.EvidenceID = witness.EvidenceID;";
			
			//executes the above sql statement
			rs = st.executeQuery(sql);
			
			
			if(!rs.next())
			{
				JOptionPane.showMessageDialog(null, "No witnesses to display for this case", "No witnesses on file for case", JOptionPane.ERROR_MESSAGE);
			}
			rs.previous();
			witness.setModel(DbUtils.resultSetToTableModel(rs));
			witness.setFont(new Font("Sans-Serif", 0, 14));
			
			//closes the connection
			con.close();
		}
		//catches errors thrown by database
		catch(Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		
		caseLabel = new JLabel("Viewing Witnesses for case number: " + caseID);
		caseLabel.setFont(new Font("Sans-Serif", 0, 25));
	
		close = new JButton("Close");
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}	
		});
		//close.setPreferredSize(new Dimension(100, 100));
		close.setSize(new Dimension(100, 100));
		
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.add(close);
	
		witnessTable = new JScrollPane(witness);
		container.add(caseLabel);
		container.add(witnessTable);
		container.add(buttons);
		
		add(container);
		
	}
}
